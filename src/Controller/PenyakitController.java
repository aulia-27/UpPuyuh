/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Form.FormDataPenyakit;
import Form.FormInputDataPenyakit;
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
    FormInputDataPenyakit viewInput;
    Penyakit penyakit;
    Connection con;
    
    public PenyakitController (FormInputDataPenyakit viewInput) {
        try {
            this.viewInput = viewInput;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewInput.getTxtIdPenyakit().setText("");
        viewInput.getTxtNama().setText("");
    }
    
    public void insert(){
        penyakit = new Penyakit();
        penyakit.setIdPenyakit(viewInput.getTxtIdPenyakit().getText());
        penyakit.setNama(viewInput.getTxtNama().getText());
        penyakit.setKeterangan(viewInput.getJtxtKeterangan().getText());
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        penyakit = new Penyakit();
        penyakit.setIdPenyakit(viewInput.getTxtIdPenyakit().getText());
        penyakit.setNama(viewInput.getTxtNama().getText());
        penyakit.setKeterangan(viewInput.getJtxtKeterangan().getText());
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblDataPenyakit().getValueAt(viewData.getTblDataPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewInput.getTxtIdPenyakit().setText(penyakit.getIdPenyakit());
                viewInput.getTxtNama().setText(penyakit.getNama());
                viewInput.getJtxtKeterangan().setText(penyakit.getKeterangan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTable() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewData.getTblDataPenyakit().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * form penyakit");
            while (rs.next()) {                
                Object[] data= {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
