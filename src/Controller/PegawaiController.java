/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
    Pegawai pegawai;
    Connection con;
    
    public PegawaiController (FormDataPegawai viewData) {
        try {
            this.viewData = viewData;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearForm();
            isiCboIdKandang();
            isiCboJekel();
            viewTableData();
            viewTableInput();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void clearForm(){
        viewData.getTxtIdPegawai().setText("");
        viewData.getTxtNama().setText("");
        viewData.getTxtAsal().setText("");
        viewData.getTxtTaggalLahir().setText("");
        viewData.getTxtTaggalLahir().setEditable(false);
        viewData.getTxtNoTelepon().setText("");
        viewData.getTxtAlamat().setText("");
    }
    
    public void isiCboIdKandang() {
        viewData.getCboIdKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewData.getCboIdKandang().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboJekel() {
        viewData.getCboJekel().removeAllItems();
        viewData.getCboJekel().addItem("L");
        viewData.getCboJekel().addItem("P");
    }
    
    public void insert(){
        pegawai = new Pegawai();
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewData.getTxtIdPegawai().getText());
        pegawai.setNama(viewData.getTxtNama().getText());
        pegawai.setAsal(viewData.getTxtAsal().getText());
        pegawai.setTglLahir(viewData.getTxtTaggalLahir().getText());
        pegawai.setJekel(viewData.getCboJekel().getSelectedItem().toString());
        pegawai.setNoTelp(viewData.getTxtNoTelepon().getText());
        pegawai.setAlamat(viewData.getTxtAlamat().getText());
        pegawai.setIdKandang(viewData.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewData, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewData.getTxtIdPegawai().getText());
        pegawai.setNama(viewData.getTxtNama().getText());
        pegawai.setAsal(viewData.getTxtAsal().getText());
        pegawai.setTglLahir(viewData.getTxtTaggalLahir().getText());
        pegawai.setJekel(viewData.getCboJekel().getSelectedItem().toString());
        pegawai.setNoTelp(viewData.getTxtNoTelepon().getText());
        pegawai.setAlamat(viewData.getTxtAlamat().getText());
        pegawai.setIdKandang(viewData.getCboIdKandang().getSelectedItem().toString().split("-")[0]);
        try {
            PegawaiDao.update(con, pegawai);
            JOptionPane.showMessageDialog(viewData, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewData, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewData, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewData, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabel() {
        try {
            String kode = viewData.getTblInputPegawai().getValueAt(viewData.getTblInputPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewData.getTxtIdPegawai().setText(pegawai.getIdPegawai());
                viewData.getTxtNama().setText(pegawai.getNama());
                viewData.getTxtAsal().setText(pegawai.getAsal());
                viewData.getTxtTaggalLahir().setText(pegawai.getTglLahir());
                viewData.getTxtTaggalLahir().setEditable(true);
                viewData.getCboJekel().setSelectedItem(pegawai.getJekel());
                viewData.getTxtNoTelepon().setText(pegawai.getNoTelp());
                viewData.getTxtAlamat().setText(pegawai.getAlamat());
                viewData.getCboIdKandang().setSelectedItem(pegawai.getIdKandang());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewData, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
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
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInput(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewData.getTblInputPegawai().getModel();
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
    }
}
