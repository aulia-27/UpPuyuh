/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Pakan.Pakan;
import Pakan.PakanDao;
import FormUpPuyuh.FormDataPakan;
import FormUpPuyuh.FormInputPakan;
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
public class PakanController {
    FormDataPakan viewData;
    FormInputPakan viewInput;
    Pakan pakan;
    PakanDao PakanDao;
    Connection con;
    
    public PakanController (FormInputPakan viewInput) {
        try {
            this.viewInput = viewInput;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            viewTableInput();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public PakanController (FormDataPakan viewData) {
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
        viewInput.getTxtIdPakan().setText("");
        viewInput.getTxtNamaPakan().setText("");
        viewInput.getTxtHarga().setText("");
        viewInput.getTxtStok().setText("");
        viewInput.getTxtJenis().setText("");
        viewInput.getJtxKeterangan().setText("");
    }
    
    public void insert(){
        pakan = new Pakan();
        pakan.setIdPakan(viewInput.getTxtIdPakan().getText());
        pakan.setNama(viewInput.getTxtNamaPakan().getText());
        pakan.setHarga(Integer.parseInt(viewInput.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewInput.getTxtStok().getText()));
        pakan.setJenis(viewInput.getTxtJenis().getText());
        pakan.setKeterangan(viewInput.getJtxKeterangan().getText());
        try {
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        pakan = new Pakan();
        pakan.setIdPakan(viewInput.getTxtIdPakan().getText());
        pakan.setNama(viewInput.getTxtNamaPakan().getText());
        pakan.setHarga(Integer.parseInt(viewInput.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewInput.getTxtStok().getText()));
        pakan.setJenis(viewInput.getTxtJenis().getText());
        pakan.setKeterangan(viewInput.getJtxKeterangan().getText());
        try {
            PakanDao.update(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewInput.getTblDataPakan().getValueAt(viewInput.getTblDataPakan().getSelectedRow(), 0).toString();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewInput.getTxtIdPakan().setText(pakan.getIdPakan());
                viewInput.getTxtNamaPakan().setText(pakan.getNama());
                viewInput.getTxtHarga().setText(""+pakan.getHarga());
                viewInput.getTxtStok().setText(""+pakan.getStok());
                viewInput.getTxtJenis().setText(pakan.getJenis());
                viewInput.getJtxKeterangan().setText(pakan.getKeterangan());
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
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblDataPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewInput.getTblDataPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
