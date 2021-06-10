/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Pakan.Pakan;
import Pakan.PakanDao;
import FormUpPuyuh.FormDataPakan;
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
    Pakan pakan;
    PakanDao PakanDao;
    Connection con;
    
    public PakanController (FormDataPakan viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            viewTableData();
            clearForm();
            viewTableInput();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdPakan().setText("");
        viewData.getTxtNamaPakan().setText("");
        viewData.getTxtHarga().setText("");
        viewData.getTxtStok().setText("");
    }
    
    public void insert(){
        try {
            pakan = new Pakan();
            pakan.setIdPakan(viewData.getTxtIdPakan().getText());
            pakan.setNama(viewData.getTxtNamaPakan().getText());
            pakan.setHarga(Integer.parseInt(viewData.getTxtHarga().getText()));
            pakan.setStok(Integer.parseInt(viewData.getTxtStok().getText()));
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        pakan = new Pakan();
        pakan.setIdPakan(viewData.getTxtIdPakan().getText());
        pakan.setNama(viewData.getTxtNamaPakan().getText());
        pakan.setHarga(Integer.parseInt(viewData.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewData.getTxtStok().getText()));
        try {
            PakanDao.update(con, pakan);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputPakan().getValueAt(viewData.getTblInputPakan().getSelectedRow(), 0).toString();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewData.getTxtIdPakan().setText(pakan.getIdPakan());
                viewData.getTxtNamaPakan().setText(pakan.getNama());
                viewData.getTxtHarga().setText(""+pakan.getHarga());
                viewData.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE, null, ex);
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
                    rs.getInt(4)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblInputPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
