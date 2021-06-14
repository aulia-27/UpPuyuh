/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import FormUpPuyuh.FormMenuAdmin;
import Kesehatan.Kesehatan;
import Kesehatan.KesehatanDao;
import Kandang.Kandang;
import Kandang.KandangDao;
import Pegawai.Pegawai;
import Pegawai.PegawaiDao;
import Pakan.Pakan;
import Pakan.PakanDao;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Aulia
 */
public class ControllerMenuAdmin {
    FormMenuAdmin viewAdmin;
    Kandang kandang;
    Pegawai pegawai;
    Pakan pakan;
    Connection con;
    
    public ControllerMenuAdmin (FormMenuAdmin viewAdmin) {
        try {
            this.viewAdmin = viewAdmin;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            //  Kandang //
            clearFormKandang();
            viewTableDataKandang();
            //viewTableInput();

            //  Pegawai //
            clearFormPegawai();
            viewTableDataPegawai();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KandangController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    /////////////////////       Kandang     ////////////////////////////////////
    
    public void clearFormKandang(){
        viewAdmin.getTxtNamaKandang().setText("");
        viewAdmin.getTxtJumlahTernak().setText("");
    }
    
    public void insertKandang(){
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
    
    public void updateKandang() {
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
    
    public void deleteKandang() {
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
    
    public void viewTableDataKandang(){
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
    
    ////////////////////////        Pegawai         ////////////////////////////
    
     public void clearFormPegawai(){
        viewAdmin.getTxtIdPegawai().setText("");
        viewAdmin.getTxtNamaPegawai().setText("");
        viewAdmin.getTxtAsal().setText("");
        viewAdmin.getJdtTglLahir().setDate(null);
        viewAdmin.getTxtNoTelp().setText("");
        viewAdmin.getJtxAlamat().setText("");
    }
    
    public void insertPegawai(){
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
    
    public void updatePegawai(){
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
    
    public void deletePegawai() {
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
    
    public void viewTableDataPegawai(){
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
    
    
    ////////////////                Pakan                   ////////////////////
    
    public void clearFormPakan(){
        viewAdmin.getTxtIdPakan().setText("");
        viewAdmin.getTxtNamaPakan().setText("");
        viewAdmin.getTxtHarga().setText("");
        viewAdmin.getTxtStok().setText("");
    }
    
    public void insertPakan(){
        try {
            pakan = new Pakan();
            pakan.setIdPakan(viewAdmin.getTxtIdPakan().getText());
            pakan.setNama(viewAdmin.getTxtNamaPakan().getText());
            pakan.setHarga(Integer.parseInt(viewAdmin.getTxtHarga().getText()));
            pakan.setStok(Integer.parseInt(viewAdmin.getTxtStok().getText()));
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void updatePakan(){
        pakan = new Pakan();
        pakan.setIdPakan(viewAdmin.getTxtIdPakan().getText());
        pakan.setNama(viewAdmin.getTxtNamaPakan().getText());
        pakan.setHarga(Integer.parseInt(viewAdmin.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewAdmin.getTxtStok().getText()));
        try {
            PakanDao.update(con, pakan);
            JOptionPane.showMessageDialog(viewAdmin, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePakan() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelPakan() {
        try {
            String kode = viewAdmin.getTblInputPakan().getValueAt(viewAdmin.getTblInputPakan().getSelectedRow(), 0).toString();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewAdmin.getTxtIdPakan().setText(pakan.getIdPakan());
                viewAdmin.getTxtNamaPakan().setText(pakan.getNama());
                viewAdmin.getTxtHarga().setText(""+pakan.getHarga());
                viewAdmin.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataPakan().getModel();
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
    
    public void viewTableInputPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputPakan().getModel();
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
