/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataKesehatan;

import Kesehatan.Kesehatan;
import Kesehatan.KesehatanDao;

import Kandang.Kandang;
import Kandang.KandangDao;

import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Aulia
 */
public class KesehatanController {
    FormDataKesehatan viewData;
    Kandang kandang;
    KandangDao kandangDao;
    Kesehatan kesehatan;
    Connection con;
    
    public KesehatanController (FormDataKesehatan viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdPenyakit();
            viewTableInput();
            viewTableData();
            viewTableKandang();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdKesehatan().setText("");
        viewData.getTxtIdKandang().setText("");
        viewData.getTxtJmlTernak().setText("");
        viewData.getTxtJmlSakit().setText("");
        viewData.getTxtJmlMati().setText("");
        viewData.getTxtTotal().setText("");
    }
    
    public void isiCboIdPenyakit() {
        viewData.getCboIdPenyakit().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                viewData.getCboIdPenyakit().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickTabelKandang() {
        try {
            String kodeKandang =  viewData.getTblDataKandang().getValueAt(viewData.getTblDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandangCek(con, kodeKandang);
            kandang.getJmlTernak();
            if (kandang != null) {
                viewData.getTxtIdKandang().setText(kandang.getIdKandang());
                viewData.getTxtJmlTernak().setText(""+kandang.getJmlTernak());
            } else {
                JOptionPane.showMessageDialog(viewData, "Data Tidak Ada, Silakan Pilih Data Pada Tabel Kandang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KesehatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* public void insert(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewData.getTxtIdKesehatan().getText());
        kesehatan.setIdKandang(viewData.getTxtIdKandang().getText());
        kesehatan.setIdSakit(viewData.getCboIdPenyakit().getSelectedItem().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewData.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewData.getTxtJmlMati().getText()));
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    } */
    
    public void insert(){
        try {
            String kodeKandang =  viewData.getTblDataKandang().getValueAt(viewData.getTblDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandangCek(con, kodeKandang);
            kandang.getJmlTernak();
            int all = Integer.parseInt(viewData.getTxtJmlTernak().getText()) - Integer.parseInt(viewData.getTxtJmlMati().getText());
            
            viewData.getTxtTotal().setText(""+all);
            
            kesehatan = new Kesehatan();
            kesehatan.setIdKesehatan(viewData.getTxtIdKesehatan().getText());
            kesehatan.setIdKandang(viewData.getTxtIdKandang().getText());
            kesehatan.setIdSakit(viewData.getCboIdPenyakit().getSelectedItem().toString().split("-")[0]);
            kesehatan.setJmlSakit(Integer.parseInt(viewData.getTxtJmlSakit().getText()));
            kesehatan.setJmlMati(Integer.parseInt(viewData.getTxtJmlMati().getText()));
            kandang.setJmlTernak(Integer.parseInt(viewData.getTxtTotal().getText()));
            kandangDao.update(con, kandang);
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    } 
    
    
    public void update(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewData.getTxtIdKesehatan().getText());
        kesehatan.setIdKandang(viewData.getTxtIdKandang().getText());
        kesehatan.setIdSakit(viewData.getCboIdPenyakit().getSelectedItem().toString());
        kesehatan.setJmlSakit(Integer.getInteger(viewData.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.getInteger(viewData.getTxtJmlMati().getText()));
        try {
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputKesehatan().getValueAt(viewData.getTblInputKesehatan().getSelectedRow(), 0).toString();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            kandang = KandangDao.getKandangCek(con, kode);
            if (kesehatan != null) {
                viewData.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewData.getTxtIdKandang().setText(kesehatan.getIdKandang());
                viewData.getCboIdPenyakit().setSelectedItem(kesehatan.getIdSakit());
                viewData.getTxtJmlSakit().setText(""+kesehatan.getJmlMati());
                viewData.getTxtJmlMati().setText(""+kesehatan.getJmlSakit());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblDataKandang().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableData(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblInputKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
