/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Pakan.Pakan;
import Pakan.PakanDao;
import Form.FormDataPakan;
import Form.FormInputDataPakan;
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
    FormInputDataPakan viewInput;
    Pakan pakan;
    PakanDao PakanDao;
    Connection con;
    
    public PakanController (FormInputDataPakan viewInput) {
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
        viewInput.getTxtIdPakan().setText("");
        viewInput.getTxtNama().setText("");
        viewInput.getTxtHarga().setText("");
        viewInput.getTxtStok().setText("");
        viewInput.getTxtJenis().setText("");
    }
    
    public void insert(){
        pakan = new Pakan();
        pakan.setIdPakan(viewInput.getTxtIdPakan().getText());
        pakan.setNama(viewInput.getTxtNama().getText());
        pakan.setHarga(Integer.parseInt(viewInput.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewInput.getTxtStok().getText()));
        pakan.setJenis(viewInput.getTxtJenis().getText());
        try {
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void Update(){
        pakan = new Pakan();
        pakan.setIdPakan(viewInput.getTxtIdPakan().getText());
        pakan.setNama(viewInput.getTxtNama().getText());
        pakan.setHarga(Integer.parseInt(viewInput.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewInput.getTxtStok().getText()));
        pakan.setJenis(viewInput.getTxtJenis().getText());
        try {
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void viewTable(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTabelDataPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
