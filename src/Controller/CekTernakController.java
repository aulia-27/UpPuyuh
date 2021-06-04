/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Form.FormDataCekTernak;
import Form.FormInputCekTernak;
import CekTernak.CekTernak;
import CekTernak.CekTernakDao;
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
public class CekTernakController {
    FormDataCekTernak viewData;
    FormInputCekTernak viewInput;
    CekTernak CekTernak;
    Connection con;
    
    public CekTernakController (FormInputCekTernak viewInput) {
        try {
            this.viewInput = viewInput;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdKandang();
            isiCboIdPakan();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public CekTernakController (FormDataCekTernak viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            viewTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewInput.getTxtIdTernak().setText("");
        viewInput.getTxtJmlTelur().setText("");
        viewInput.getTxtKebersihan().setText("");
        viewInput.getTxtTanggal().setText("");
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
    
    public void isiCboIdPakan() {
        viewInput.getCboIdPakan().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while (rs.next()) {                
                viewInput.getCboIdPakan().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewInput.getTxtIdTernak().getText());
        CekTernak.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        CekTernak.setIdPakan(viewInput.getCboIdPakan().getSelectedItem().toString());
        CekTernak.setJmlTelur(Integer.parseInt(viewInput.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewInput.getTxtKebersihan().getText());
        CekTernak.setTglCek(viewInput.getTxtTanggal().getText());
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewInput.getTxtIdTernak().getText());
        CekTernak.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        CekTernak.setIdPakan(viewInput.getCboIdPakan().getSelectedItem().toString());
        CekTernak.setJmlTelur(Integer.parseInt(viewInput.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewInput.getTxtKebersihan().getText());
        CekTernak.setTglCek(viewInput.getTxtTanggal().getText());
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void viewTable() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTabelCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * cek_kandang");
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)
                };
            tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
