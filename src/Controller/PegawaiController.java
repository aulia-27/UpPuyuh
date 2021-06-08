/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormDataPegawai;
import FormUpPuyuh.FormInputPegawai;
import Pegawai.Pegawai;
import Pegawai.PegawaiDao;
import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    FormInputPegawai viewInput;
    Pegawai pegawai;
    Connection con;
    
    public PegawaiController (FormInputPegawai viewInput) {
        try {
            this.viewInput = viewInput;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdKandang();
            viewTableInput();
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
            viewTableData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewInput.getTxtIdPegawai().setText("");
        viewInput.getTxtNama().setText("");
        viewInput.getTxtTaggalLahir().setText("");
        viewInput.getTxtTaggalLahir().setEditable(false);
        viewInput.getTxtNoTelepon().setText("");
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
        pegawai.setTglLahir(viewInput.getTxtTaggalLahir().getText());
        pegawai.setNoTelp(viewInput.getTxtNoTelepon().getText());
        pegawai.setAlamat(viewInput.getTxtAlamat().getText());
        pegawai.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
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
        pegawai.setTglLahir(viewInput.getTxtTaggalLahir().getText());
        pegawai.setNoTelp(viewInput.getTxtNoTelepon().getText());
        pegawai.setAlamat(viewInput.getTxtAlamat().getText());
        pegawai.setIdKandang(viewInput.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        try {
            PegawaiDao.update(con, pegawai);
            JOptionPane.showMessageDialog(viewInput, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewInput, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewInput, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewInput, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewInput.getTblDataPegawai().getValueAt(viewInput.getTblDataPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewInput.getTxtIdPegawai().setText(pegawai.getIdPegawai());
                viewInput.getTxtNama().setText(pegawai.getNama());
                viewInput.getTxtTaggalLahir().setText(pegawai.getTglLahir());
                viewInput.getTxtTaggalLahir().setEditable(true);
                viewInput.getTxtNoTelepon().setText(pegawai.getNoTelp());
                viewInput.getTxtAlamat().setText(pegawai.getAlamat());
                viewInput.getCboIdKandang().setSelectedItem(pegawai.getIdKandang());
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
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblDataPegawai().getModel();
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
    
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewInput.getTblDataPegawai().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
