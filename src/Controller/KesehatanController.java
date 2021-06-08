/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataKesehatan;
import FormUpPuyuh.FormInputKesehatan;
import Kesehatan.Kesehatan;
import Kesehatan.KesehatanDao;
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
    FormInputKesehatan viewInput;
    Kesehatan kesehatan;
    Connection con;
    
    public KesehatanController (FormInputKesehatan viewInput) {
        try {
            this.viewInput = viewInput;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdKandang();
            isiCboIdPenyakit();
            viewTableInput();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public KesehatanController (FormDataKesehatan viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            viewTableData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewInput.getTxtIdKesehatan().setText("");
        viewInput.getTxtJmlSakit().setText("");
        viewInput.getTxtJmlMati().setText("");
    }
    
    public void isiCboIdKandang() {
        viewInput.getCboIdKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewInput.getCboIdKandang().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPenyakit() {
        viewInput.getCboIdPenyakit().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                viewInput.getCboIdPenyakit().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewInput.getTxtIdKesehatan().getText());
        kesehatan.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        kesehatan.setIdSakit(viewInput.getCboIdPenyakit().getSelectedItem().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewInput.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewInput.getTxtJmlMati().getText()));
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewInput.getTxtIdKesehatan().getText());
        kesehatan.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        kesehatan.setIdSakit(viewInput.getCboIdPenyakit().getSelectedItem().toString());
        kesehatan.setJmlSakit(Integer.getInteger(viewInput.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.getInteger(viewInput.getTxtJmlMati().getText()));
        try {
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewInput.getTblDataKesehatan().getValueAt(viewInput.getTblDataKesehatan().getSelectedRow(), 0).toString();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            if (kesehatan != null) {
                viewInput.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewInput.getCboIdKandang().setSelectedItem(kesehatan.getIdKandang());
                viewInput.getCboIdPenyakit().setSelectedItem(kesehatan.getIdSakit());
                viewInput.getTxtJmlSakit().setText(""+kesehatan.getJmlMati());
                viewInput.getTxtJmlMati().setText(""+kesehatan.getJmlSakit());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
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
            DefaultTableModel tabelModel = (DefaultTableModel) viewInput.getTblDataKesehatan().getModel();
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
