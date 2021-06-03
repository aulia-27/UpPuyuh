/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Form.FormDataKesehatan;
import Form.FormInputDataKesehatan;
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
    FormInputDataKesehatan viewInput;
    Kesehatan kesehatan;
    Connection con;
    
    public KesehatanController (FormInputDataKesehatan viewInput) {
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
        viewInput.getTxtIdKesehatan().setText("");
        viewInput.getTxtJumlahSakit().setText("");
        viewInput.getTxtJumlahMati().setText("");
    }
    
    public void insert(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewInput.getTxtIdKesehatan().getText());
        kesehatan.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        kesehatan.setJmlSakit(Integer.getInteger(viewInput.getTxtJumlahSakit().getText()));
        kesehatan.setJmlMati(Integer.getInteger(viewInput.getTxtJumlahMati().getText()));
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
        kesehatan.setJmlSakit(Integer.getInteger(viewInput.getTxtJumlahSakit().getText()));
        kesehatan.setJmlMati(Integer.getInteger(viewInput.getTxtJumlahMati().getText()));
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void viewTable(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTabelDataKesehatan().getModel();
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
