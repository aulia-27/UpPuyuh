/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Pegawai.Pegawai;
import Pegawai.PegawaiDao;
import Koneksi.Koneksi;

import FormUpPuyuh.FormMenuAdmin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Aulia
 */
public class PegawaiController {
    FormMenuAdmin viewAdmin;
    Pegawai pegawai;
    Connection con;
    
    public PegawaiController (FormMenuAdmin viewAdmin) {
        try {
            this.viewAdmin = viewAdmin;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            viewTableData();
            //viewTableInput();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewAdmin.getTxtIdPegawai().setText("");
        viewAdmin.getTxtNamaPegawai().setText("");
        viewAdmin.getTxtAsal().setText("");
        viewAdmin.getJdtTglLahir().setDate(null);
        viewAdmin.getTxtNoTelp().setText("");
        viewAdmin.getJtxAlamat().setText("");
    }
    
    public void insert(){
        if (true) {
            pegawai = new Pegawai();
            pegawai.setIdPegawai(viewAdmin.getTxtIdPegawai().getText());
            pegawai.setNama(viewAdmin.getTxtNamaPegawai().getText());
            pegawai.setAsal(viewAdmin.getTxtAsal().getText());

            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(viewAdmin.getJdtTglLahir().getDate());

            pegawai.setTglLahir(strDate);
            if (viewAdmin.getRbLakiLaki().equals("Laki-Laki")) {
                pegawai.setJekel(viewAdmin.getRbLakiLaki().getText());
            } else if (viewAdmin.getRbLakiLaki().equals("Perempuan")){
                pegawai.setJekel(viewAdmin.getRbPerempuan().getText());
            }
            pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
            pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
        } else {
            JOptionPane.showMessageDialog(viewAdmin, "Silakan Isi Data");
        }
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error" +ex); 
        }
    }
    
    public void update(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewAdmin.getTxtIdPegawai().getText());
        pegawai.setNama(viewAdmin.getTxtNamaPegawai().getText());
        pegawai.setAsal(viewAdmin.getTxtAsal().getText());
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(viewAdmin.getJdtTglLahir().getDate());
        
        pegawai.setTglLahir(strDate);
        if (viewAdmin.getRbLakiLaki().equals("Laki-Laki")) {
            pegawai.setJekel(viewAdmin.getRbLakiLaki().getText());
        } else {
            pegawai.setJekel(viewAdmin.getRbPerempuan().getText());
        }
        pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
        pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
        if (viewAdmin.getRbLakiLaki().equals("Laki-Laki")) {
            pegawai.setJekel(viewAdmin.getRbLakiLaki().getText());
        } else {
            pegawai.setJekel(viewAdmin.getRbPerempuan().getText());
        }
        pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
        pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
        try {
            PegawaiDao.update(con, pegawai);
            JOptionPane.showMessageDialog(viewAdmin, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    /*public void onClickTabel() {
        try {
            String kode = viewAdmin.getTblInputPegawai().getValueAt(viewAdmin.getTblInputPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewAdmin.getTxtIdPegawai().setText(pegawai.getIdPegawai());
                viewAdmin.getTxtNama().setText(pegawai.getNama());
                viewAdmin.getTxtAsal().setText(pegawai.getAsal());
                viewAdmin.getTxtTaggalLahir().setText(pegawai.getTglLahir());
                viewAdmin.getTxtTaggalLahir().setEditable(true);
                viewAdmin.getCboJekel().setSelectedItem(pegawai.getJekel());
                viewAdmin.getTxtNoTelepon().setText(pegawai.getNoTelp());
                viewAdmin.getTxtAlamat().setText(pegawai.getAlamat());
                viewAdmin.getCboIdKandang().setSelectedItem(pegawai.getIdKandang());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void viewTableData(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataPegawai().getModel();
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
                    rs.getString(7)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputPegawai().getModel();
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
                    rs.getString(7),
                    rs.getString(8)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
