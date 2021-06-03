/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Form.FormDataPegawai;
import Form.FormInputDataPegawai;
import Pegawai.Pegawai;
import Pegawai.PegawaiDao;
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
public class PegawaiController {
    FormDataPegawai viewData;
    FormInputDataPegawai viewInput;
    Pegawai pegawai;
    Connection con;
    
    public PegawaiController (FormInputDataPegawai viewInput) {
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
        viewInput.getTxtIdPegawai().setText("");
        viewInput.getTxtTglLhr().setText("");
        viewInput.getTxtNoTelp().setText("");
        viewInput.getTxtAlamat().setText("");
    }
    
    public void insert(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewInput.getTxtIdPegawai().getText());
        pegawai.setTglLahir(viewInput.getTxtTglLhr().getText());
        pegawai.setNoTelp(viewInput.getTxtNoTelp().getText());
        pegawai.setAlamat(viewInput.getTxtAlamat().getText());
        pegawai.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewInput, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewInput.getTxtIdPegawai().getText());
        pegawai.setTglLahir(viewInput.getTxtTglLhr().getText());
        pegawai.setNoTelp(viewInput.getTxtNoTelp().getText());
        pegawai.setAlamat(viewInput.getTxtAlamat().getText());
        pegawai.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString());
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void viewTable(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTableDataPegawai().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
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
