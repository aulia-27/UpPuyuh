/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Kandang.Kandang;
import Kandang.KandangDao;
import FormUpPuyuh.FormMenuAdmin;
import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Aulia
 */
public class KandangController {
    FormMenuAdmin viewAdmin;
    Kandang kandang;
    Connection con;
    
    public KandangController (FormMenuAdmin viewAdmin) {
        try {
            this.viewAdmin = viewAdmin;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            viewTableData();
            //viewTableInput();
            clearForm();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewAdmin.getTxtNamaKandang().setText("");
        viewAdmin.getTxtJumlahTernak().setText("");
    }
    
    public void insert(){
        kandang = new Kandang();
        kandang.setNamaKandang(viewAdmin.getTxtNamaKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewAdmin.getTxtJumlahTernak().getText()));
        try {
            KandangDao.insert(con, kandang);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Nama Kandang Sudah Ada"); 
        }
    }
    
    public void update() {
        kandang = new Kandang();
        kandang.setNamaKandang(viewAdmin.getTxtNamaKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewAdmin.getTxtNamaKandang().getText()));
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewAdmin, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Silakan Pilih Data Pada Tabel");
        }
    }
    
    /*public void onClickTabel() {
        try {
            String kode = viewAdmin.getTblInputKandang().getValueAt(viewAdmin.getTblInputKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewAdmin.getTxtIdKandang().setText(kandang.getIdKandang());
                viewAdmin.getTxtLabelKandang().setText(kandang.getNama());
                viewAdmin.getTxtJmlTernak().setText(""+kandang.getJmlTernak());
                viewAdmin.getTxtBlokKandang().setText(kandang.getBlokKandang());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void viewTableData(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataKandang().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getInt(2),
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    /*public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputKandang().getModel();
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
    }*/
}
