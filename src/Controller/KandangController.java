/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataKandang;

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
public class KandangController {
    FormDataKandang viewData;
    Kandang kandang;
    Connection con;
    
    public KandangController (FormDataKandang viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            viewTableInput();
            viewTableData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdKandang().setText("");
        viewData.getTxtLabelKandang().setText("");
        viewData.getTxtJmlTernak().setText("");
        viewData.getTxtBlokKandang().setText("");
    }
    
    public void insert(){
        kandang = new Kandang();
        kandang.setIdKandang(viewData.getTxtIdKandang().getText());
        kandang.setNama(viewData.getTxtLabelKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewData.getTxtJmlTernak().getText()));
        kandang.setBlokKandang(viewData.getTxtBlokKandang().getText());
        try {
            KandangDao.insert(con, kandang);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "ID Kandang Sudah Ada"); 
        }
    }
    
    public void update() {
        kandang = new Kandang();
        kandang.setIdKandang(viewData.getTxtIdKandang().getText());
        kandang.setNama(viewData.getTxtLabelKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewData.getTxtJmlTernak().getText()));
        kandang.setBlokKandang(viewData.getTxtBlokKandang().getText());
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Silakan Pilih Data Pada Tabel");
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputKandang().getValueAt(viewData.getTblInputKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewData.getTxtIdKandang().setText(kandang.getIdKandang());
                viewData.getTxtLabelKandang().setText(kandang.getNama());
                viewData.getTxtJmlTernak().setText(""+kandang.getJmlTernak());
                viewData.getTxtBlokKandang().setText(kandang.getBlokKandang());
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
     
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblInputKandang().getModel();
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
}
