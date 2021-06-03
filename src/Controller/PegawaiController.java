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
            clearForm();
            isiCboIdKandang();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public PegawaiController (FormDataPegawai viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            viewTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewInput.getTxtIdPegawai().setText("");
        viewInput.getTxtNama().setText("");
        viewInput.getTxtTglLhr().setText("");
        viewInput.getTxtNoTelp().setText("");
        viewInput.getTxtAlamat().setText("");
    }
    
    public void isiCboIdKandang() {
        viewInput.getCboIdKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewInput.getCboIdKandang().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewInput.getTxtIdPegawai().getText());
        pegawai.setNama(viewInput.getTxtNama().getText());
        pegawai.setTglLahir(viewInput.getTxtTglLhr().getText());
        pegawai.setNoTelp(Integer.parseInt(viewInput.getTxtNoTelp().getText()));
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
        pegawai.setNama(viewInput.getTxtNama().getText());
        pegawai.setTglLahir(viewInput.getTxtTglLhr().getText());
        pegawai.setNoTelp(Integer.parseInt(viewInput.getTxtNoTelp().getText()));
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
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTableDataPegawai().getValueAt(viewData.getTableDataPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewInput.getTxtIdPegawai().setText(pegawai.getIdPegawai());
                viewInput.getTxtNama().setText(pegawai.getNama());
                viewInput.getTxtTglLhr().setText(pegawai.getTglLahir());
                viewInput.getTxtNoTelp().setText(""+pegawai.getNoTelp());
                viewInput.getTxtAlamat().setText(pegawai.getAlamat());
                viewInput.getCboIdKandang().setSelectedIndex(Integer.parseInt(pegawai.getIdKandang()));
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
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
