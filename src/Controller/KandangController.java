/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataKandang;
import FormUpPuyuh.FormInputKandang;
import FormUpPuyuh.FormInputKandang;

import Kandang.Kandang;
import Kandang.KandangDao;
import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
    FormInputKandang viewInput;
    Kandang kandang;
    Connection con;
    
    public KandangController (FormInputKandang viewInput) {
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
    
    public KandangController (FormDataKandang viewData) {
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
        viewInput.getTxtIdKandang().setText("");
        viewInput.getTxtLabelKandang().setText("");
        viewInput.getTxtBlokKandang().setText("");
    }
    
    public void insert(){
        kandang = new Kandang();
        kandang.setIdKandang(viewInput.getTxtIdKandang().getText());
        kandang.setNama(viewInput.getTxtLabelKandang().getText());
        kandang.setBlokKandang(viewInput.getTxtBlokKandang().getText());
        try {
            KandangDao.insert(con, kandang);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "ID Kandang Sudah Ada"); 
        }
    }
    
    public void update() {
        kandang = new Kandang();
        kandang.setIdKandang(viewInput.getTxtIdKandang().getText());
        kandang.setNama(viewInput.getTxtLabelKandang().getText());
        kandang.setBlokKandang(viewInput.getTxtBlokKandang().getText());
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Silakan Pilih Data Pada Tabel");
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewInput.getTblDataKandang().getValueAt(viewInput.getTblDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewInput.getTxtIdKandang().setText(kandang.getIdKandang());
                viewInput.getTxtLabelKandang().setText(kandang.getNama());
                viewInput.getTxtBlokKandang().setText(kandang.getBlokKandang());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewInput, "Data Tidak Ada");
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
                    rs.getString(3)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewInput.getTblDataKandang().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
