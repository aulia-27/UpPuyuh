/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataPenyakit;
import Penyakit.Penyakit;
import Penyakit.PenyakitDao;
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
public class PenyakitController {
    FormDataPenyakit viewData;
    Penyakit penyakit;
    Connection con;
    
    public PenyakitController (FormDataPenyakit viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            viewTableInput();
            viewTableData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdPenyakit().setText("");
        viewData.getTxtNamaPenyakit().setText("");
        viewData.getJtxKeterangan().setText("");
    }
    
    public void insert(){
        penyakit = new Penyakit();
        penyakit.setIdPenyakit(viewData.getTxtIdPenyakit().getText());
        penyakit.setNama(viewData.getTxtNamaPenyakit().getText());
        penyakit.setKeterangan(viewData.getJtxKeterangan().getText());
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        penyakit = new Penyakit();
        penyakit.setIdPenyakit(viewData.getTxtIdPenyakit().getText());
        penyakit.setNama(viewData.getTxtNamaPenyakit().getText());
        penyakit.setKeterangan(viewData.getJtxKeterangan().getText());
        try {
            PenyakitDao.update(con, penyakit);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PenyakitDao.delete(con, penyakit);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputPenyakit().getValueAt(viewData.getTblInputPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewData.getTxtIdPenyakit().setText(penyakit.getIdPenyakit());
                viewData.getTxtNamaPenyakit().setText(penyakit.getNama());
                viewData.getJtxKeterangan().setText(penyakit.getKeterangan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableData() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTblDataPenyakit().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                Object[] data= {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInput() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTblInputPenyakit().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                Object[] data= {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
