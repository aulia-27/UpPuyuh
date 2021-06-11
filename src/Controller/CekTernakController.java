/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import CekTernak.CekTernak;
import CekTernak.CekTernakDao;
import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    CekTernak CekTernak;
    Connection con;
    
    public CekTernakController (FormDataCekTernak viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdKandang();
            isiCboIdPakan();
            isiCboKebersihan();
            DateNow();
            viewTableInput();
            viewTableData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdCek().setText("");
        viewData.getTxtJmlTelur().setText("");
    }
    
    public void DateNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewData.getTxtTglCek().setText(sdf.format(d));
    }
    
    public void isiCboIdKandang() {
        viewData.getCboIdKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewData.getCboIdKandang().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPakan() {
        viewData.getCboIdPakan().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while (rs.next()) {                
                viewData.getCboIdPakan().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboKebersihan(){
        viewData.getCboKebersihan().removeAllItems();
        viewData.getCboKebersihan().addItem("Sangat bersih");
        viewData.getCboKebersihan().addItem("Bersih");
        viewData.getCboKebersihan().addItem("Kurang Bersih");
        viewData.getCboKebersihan().addItem("Kotor");
    }
    
    public void insert(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewData.getTxtIdCek().getText());
        CekTernak.setIdKandang(viewData.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPakan(viewData.getCboIdPakan().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewData.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewData.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewData.getTxtTglCek().getText());
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewData.getTxtIdCek().getText());
        CekTernak.setIdKandang(viewData.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPakan(viewData.getCboIdPakan().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewData.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewData.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewData.getTxtTglCek().getText());
        try {
            CekTernakDao.update(con, CekTernak);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            CekTernakDao.delete(con, CekTernak);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputCekTernak().getValueAt(viewData.getTblInputCekTernak().getSelectedRow(), 0).toString();
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewData.getTxtIdCek().setText(CekTernak.getIdCek());
                viewData.getCboIdKandang().setSelectedItem(CekTernak.getIdKandang());
                viewData.getCboIdPakan().setSelectedItem(CekTernak.getIdPakan());
                viewData.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewData.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewData.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableData() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTblDataCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
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
    
    public void viewTableInput() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTblInputCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
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
