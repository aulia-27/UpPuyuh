/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMenuAdmin;

import CekTernak.CekTernak;
import CekTernak.CekTernakDao;
import Kesehatan.Kesehatan;
import Kesehatan.KesehatanDao;
import Kandang.Kandang;
import Kandang.KandangDao;
import Pegawai.Pegawai;
import Pegawai.PegawaiDao;
import Pakan.Pakan;
import Pakan.PakanDao;

import Koneksi.Koneksi;
import Penyakit.Penyakit;
import Penyakit.PenyakitDao;
import User.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
/**
 *
 * @author Aulia
 */
public class MenuAdminController {
    FormMenuAdmin viewAdmin;
    Kandang kandang;
    Pegawai pegawai;
    Pakan pakan;
    Penyakit penyakit;
    CekTernak CekTernak;
    Kesehatan kesehatan;
    Connection con;
    
    public MenuAdminController (FormMenuAdmin viewAdmin) {
        try {
            this.viewAdmin = viewAdmin;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            
            // Laporan
            cleartextLaporan();
            
            //  Kandang //
            clearFormKandang();
            viewTableDataKandang();
            viewTableInputKandang();

            //  Pegawai //
            clearFormPegawai();
            jekel();
            viewTableDataPegawai();
            viewTableInputPegawai();
            
            //  Pakan   //
            clearFormPakan();
            viewTableDataPakan();
            viewTableInputPakan();
            
            //  penyakit   //
            clearFormPenyakit();
            viewTableDataPenyakit();
            viewTableInputPenyakit();
            
            //  Kesehatan   //
            clearFormKesehatan();
            viewTableDataKesehatan();
            viewTableInputKesehatan();
            isiCboKandang();
            isiCboPenyakit();
            isiCboIdPegawai();
            
            
            //  Cek Ternak  //
            clearFormCekTernak();
            DateNow();
            isiCboIdPakanCek();
            isiCboIdPegawaiCek();
            isiCboKandangCek();
            isiCboKebersihan();
            viewTableDataCekTernak();
            viewTableInputCekTernak();
            
 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
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
    
    /*public void onClickTabelKandang() {
        try {
            String kode = viewAdmin.getTblInputDataKandang().getValueAt(viewAdmin.getTblInputDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                kandang.setNamaKandang(viewAdmin.getTxtNamaKandang().getText());
                kandang.setJmlTernak(Integer.parseInt(viewAdmin.getTxtJumlahTernak().getText()));
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormKandang();
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
                    rs.getInt(2)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void viewTableInputKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataKandang().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getInt(2)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////        Pegawai         ////////////////////////////
    
     public void clearFormPegawai(){
        viewAdmin.getTxtIdPegawai().setText("");
        viewAdmin.getTxtNamaPegawai().setText("");
        viewAdmin.getTxtAsal().setText("");
        viewAdmin.getJdtTglLahir().setDate(null);
        viewAdmin.getTxtNoTelp().setText("");
        viewAdmin.getJtxAlamat().setText("");
    }
     
     public void jekel() {
         viewAdmin.getRbLakiLaki().setText("Laki-Laki");
         viewAdmin.getRbPerempuan().setText("Perempuan");
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
            
            if(viewAdmin.getRbLakiLaki().isSelected())  {
                String Jekel;
                Jekel = "Laki-Laki";
                pegawai.setJekel(Jekel);
            }
            else if(viewAdmin.getRbPerempuan().isSelected()) {
                String Jekel;
                Jekel = "Perempuan";
                pegawai.setJekel(Jekel);
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
        
        if(viewAdmin.getRbLakiLaki().isSelected())  {
                String Jekel;
                Jekel = "Laki-Laki";
                pegawai.setJekel(Jekel);
            }
        else if(viewAdmin.getRbPerempuan().isSelected()) {
            String Jekel;
            Jekel = "Perempuan";
            pegawai.setJekel(Jekel);
        }
        
        pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
        pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
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
    
    /*public void onClickTabelPegawai() {
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPegawai(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataPegawai().getModel();
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
            pakan.setHarga(Double.parseDouble(viewAdmin.getTxtHarga().getText()));
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
        pakan.setHarga(Double.parseDouble(viewAdmin.getTxtHarga().getText()));
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
    
    /*public void onClickTabelPakan() {
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
    }*/
    
    public void viewTableDataPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataPakan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////        Penyakit        ///////////////////////////////
    
    public void clearFormPenyakit(){
        viewAdmin.getTxtNamaPenyakit().setText("");
        viewAdmin.getJtxGejala().setText("");
        viewAdmin.getJtxPenularan().setText("");
        viewAdmin.getJtxPencegahan().setText("");
        viewAdmin.getJtxPengobatan().setText("");
    }
    
    public void insertPenyakit(){
        penyakit = new Penyakit();
        penyakit.setNamaPenyakit(viewAdmin.getTxtNamaPenyakit().getText());
        penyakit.setGejala(viewAdmin.getJtxGejala().getText());
        penyakit.setPenularan(viewAdmin.getJtxPenularan().getText());
        penyakit.setPencegahan(viewAdmin.getJtxPencegahan().getText());
        penyakit.setPengobatan(viewAdmin.getJtxPengobatan().getText());
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void updatePenyakit(){
        penyakit = new Penyakit();
        penyakit.setNamaPenyakit(viewAdmin.getTxtNamaPenyakit().getText());
        penyakit.setGejala(viewAdmin.getJtxGejala().getText());
        penyakit.setPenularan(viewAdmin.getJtxPenularan().getText());
        penyakit.setPencegahan(viewAdmin.getJtxPencegahan().getText());
        penyakit.setPengobatan(viewAdmin.getJtxPengobatan().getText());
        try {
            PenyakitDao.update(con, penyakit);
            JOptionPane.showMessageDialog(viewAdmin, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePenyakit() {
        try {
            PenyakitDao.delete(con, penyakit);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    /*public void onClickTabel() {
        try {
            String kode = viewAdmin.getTblInputPenyakit().getValueAt(viewAdmin.getTblInputPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewAdmin.getTxtNamaPenyakit().setText(penyakit.getNama());
                viewAdmin.getJtxKeterangan().setText(penyakit.getKeterangan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenyakitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void viewTableDataPenyakit() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblDataPenyakit().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                Object[] data= {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPenyakit() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblInputDataPenyakit().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                Object[] data= {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /////////////////////////       Kesehatan       ////////////////////////////
    
    public void clearFormKesehatan(){
        viewAdmin.getTxtIdKesehatan().setText("");
        viewAdmin.getTxtNamaKandang().setText("");
        viewAdmin.getTxtJmlSakit().setText("");
        viewAdmin.getTxtJmlMati().setText("");
    }
    
    public void isiCboPenyakit() {
        viewAdmin.getCboPenyakit().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                viewAdmin.getCboPenyakit().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboKandang() {
        viewAdmin.getCboKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewAdmin.getCboKandang().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPegawai() {
        viewAdmin.getCboIdPegawai().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while (rs.next()) {                
                viewAdmin.getCboIdPegawai().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public void onClickTabelKandang() {
        try {
            String kodeKandang =  viewAdmin.getTblDataKandang().getValueAt(viewAdmin.getTblDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandangCek(con, kodeKandang);
            kandang.getJmlTernak();
            if (kandang != null) {
                viewAdmin.getTxtIdKandang().setText(kandang.getIdKandang());
                viewAdmin.getTxtJmlTernak().setText(""+kandang.getJmlTernak());
            } else {
                JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada, Silakan Pilih Data Pada Tabel Kandang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KesehatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
    
    public void insertKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewAdmin.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewAdmin.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewAdmin.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewAdmin.getCboIdPegawai().getSelectedItem().toString().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewAdmin.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    /*public void insert(){
        try {
            kandang = KandangDao.getKandangCek(con, kodeKandang);
            kandang.getJmlTernak();
            
            kesehatan = new Kesehatan();
            kesehatan.setIdKesehatan(viewAdmin.getTxtIdKesehatan().getText());
            kesehatan.setIdKandang(viewAdmin.getTxtIdKandang().getText());
            kesehatan.setIdSakit(viewAdmin.getCboIdPenyakit().getSelectedItem().toString().split("-")[0]);
            kesehatan.setJmlSakit(Integer.parseInt(viewAdmin.getTxtJmlSakit().getText()));
            kesehatan.setJmlMati(Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
            kandang.setJmlTernak(Integer.parseInt(viewAdmin.getTxtTotal().getText()));
            kandangDao.update(con, kandang);
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    } */
    
    
    public void updateKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewAdmin.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewAdmin.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewAdmin.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewAdmin.getCboIdPegawai().getSelectedItem().toString().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewAdmin.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
        try {
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteKesehatan() {
        try {
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    /*public void onClickTabel() {
        try {
            String kode = viewAdmin.getTblInputKesehatan().getValueAt(viewAdmin.getTblInputKesehatan().getSelectedRow(), 0).toString();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            kandang = KandangDao.getKandangCek(con, kode);
            if (kesehatan != null) {
                viewAdmin.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewAdmin.getTxtIdKandang().setText(kesehatan.getIdKandang());
                viewAdmin.getCboIdPenyakit().setSelectedItem(kesehatan.getIdSakit());
                viewAdmin.getTxtJmlSakit().setText(""+kesehatan.getJmlMati());
                viewAdmin.getTxtJmlMati().setText(""+kesehatan.getJmlSakit());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KesehatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
    
    public void viewTableDataKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////        Cek Ternak          ///////////////////////////////
    
    public void clearFormCekTernak(){
        viewAdmin.getTxtIdCekTernak().setText("");
        viewAdmin.getTxtJmlTelur().setText("");
    }
    
    public void DateNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewAdmin.getTxtTglCek().setText(sdf.format(d));
    }
    
    public void isiCboKandangCek() {
        viewAdmin.getCboKandangCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewAdmin.getCboKandangCek().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPakanCek() {
        viewAdmin.getCboIdPakanCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while (rs.next()) {                
                viewAdmin.getCboIdPakanCek().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPegawaiCek() {
        viewAdmin.getCboIdPegawaiCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while (rs.next()) {                
                viewAdmin.getCboIdPegawaiCek().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboKebersihan(){
        viewAdmin.getCboKebersihan().removeAllItems();
        viewAdmin.getCboKebersihan().addItem("Sangat bersih");
        viewAdmin.getCboKebersihan().addItem("Bersih");
        viewAdmin.getCboKebersihan().addItem("Kurang Bersih");
        viewAdmin.getCboKebersihan().addItem("Kotor");
    }
    
    public void insertCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewAdmin.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewAdmin.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPegawai(viewAdmin.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewAdmin.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewAdmin.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewAdmin.getTxtTglCek().getText());
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewAdmin, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void updateCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewAdmin.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewAdmin.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPegawai(viewAdmin.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewAdmin.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewAdmin.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewAdmin.getTxtTglCek().getText());
        try {
            CekTernakDao.update(con, CekTernak);
            JOptionPane.showMessageDialog(viewAdmin, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteCekTernak() {
        try {
            CekTernakDao.delete(con, CekTernak);
            JOptionPane.showMessageDialog(viewAdmin, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    /*public void onClickTabel() {
        try {
            String kode = viewAdmin.getTblInputCekTernak().getValueAt(viewAdmin.getTblInputCekTernak().getSelectedRow(), 0).toString();
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewAdmin.getTxtIdCek().setText(CekTernak.getIdCek());
                viewAdmin.getCboIdKandang().setSelectedItem(CekTernak.getIdKandang());
                viewAdmin.getCboIdPakan().setSelectedItem(CekTernak.getIdPakan());
                viewAdmin.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewAdmin.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewAdmin.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearForm();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void viewTableDataCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblDataCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7)
                };
            tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblInputDataCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7)
                };
            tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /////////////////////       Laporan         ////////////////////////////////
   
    public void cleartextLaporan() {
         /*viewAdmin.getTxtKolom1().setText("");
        viewAdmin.getTxtKolom2().setText("");
        viewAdmin.getTxtKolom3().setText("");
        viewAdmin.getTxtKolom4().setText("");
        viewAdmin.getTxtKolom5().setText("");
        viewAdmin.getTxtKolom6().setText("");
        viewAdmin.getTxtKolom7().setText("");    */
        viewAdmin.getTxtKodeLaporan().setText("");
    }

    
    public void setDataKesehatan(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uppuyuh","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\Laporan Kesehatan.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("ID Kesehatan") ;
            row1.createCell(1).setCellValue("Nama Kandang");
            row1.createCell(2).setCellValue("Nama Penyakit");
            row1.createCell(3).setCellValue("ID Pegawai");
            row1.createCell(4).setCellValue("Jumlah Sakit");
            row1.createCell(5).setCellValue("Jumlah Mati");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_kesehatan, nama_kandang, nama_penyakit, id_pegawai, jml_sakit, jml_mati from kesehatan");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getInt(5));
                row2.createCell(5).setCellValue(rs.getInt(6));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(viewAdmin, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewAdmin, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewAdmin, ioe.getMessage());
        }
    }
    
    public void setDataCekTernak(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uppuyuh","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\Laporan Cek Ternak.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("ID Cek") ;
            row1.createCell(1).setCellValue("Nama Kandang");
            row1.createCell(2).setCellValue("ID Pakan");
            row1.createCell(3).setCellValue("ID Pegawai");
            row1.createCell(4).setCellValue("Jumlah Telur");
            row1.createCell(5).setCellValue("Kebersihan");
            row1.createCell(6).setCellValue("Tanggal Cek");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_cek, nama_kandang, id_pakan, id_pegawai, jml_telur, kebersihan, tgl_cek from cek_ternak");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getInt(5));
                row2.createCell(5).setCellValue(rs.getString(6));
                row2.createCell(6).setCellValue(rs.getString(7));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(viewAdmin, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewAdmin, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewAdmin, ioe.getMessage());
        }
    }
     public void setDataPegawai(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uppuyuh","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\Laporan Pegawai.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("ID Pegawai") ;
            row1.createCell(1).setCellValue("Nama");
            row1.createCell(2).setCellValue("Asal");
            row1.createCell(3).setCellValue("Tanggal Lahir");
            row1.createCell(4).setCellValue("Jenis Kelamin");
            row1.createCell(5).setCellValue("No Telepon");
            row1.createCell(6).setCellValue("Alamat");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_pegawai, nama, asal, tgl_lahir, jekel, no_telp, alamat from pegawai");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
                row2.createCell(6).setCellValue(rs.getString(7));
                }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(viewAdmin, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewAdmin, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewAdmin, ioe.getMessage());
        }
    }
    public void setDataPakan(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uppuyuh","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\Laporan Pakan.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("ID Pakan") ;
            row1.createCell(1).setCellValue("Nama Pakan");
            row1.createCell(2).setCellValue("Harga");
            row1.createCell(3).setCellValue("Stok");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_pakan, nama, harga, stok from pakan ");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getInt(3));
                row2.createCell(3).setCellValue(rs.getInt(4));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(viewAdmin, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewAdmin, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewAdmin, ioe.getMessage());
        }
    }
    
    public void viewTableKesehatanLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblLaporanKesehantan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableCekTernakLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblLaporanCekTernak().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7)
                };
            tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTablePegawaiLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblLaporanPegawai().getModel();
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewTablePakanLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblLaporanPakan().getModel();
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //////////////////////          Laporan Ireport         ////////////////////
    
    public void previewLaporanKesehatan() {
        HashMap parameter = new HashMap();
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanKesehatan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            System.out.print(ex.toString());
            //Logger.getLogger(formlaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewLaporanCekTernak() {
        HashMap parameter = new HashMap();
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanKesehatan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            System.out.print(ex.toString());
            //Logger.getLogger(formlaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewLaporanPegawai() {
        HashMap parameter = new HashMap();
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanKesehatan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            System.out.print(ex.toString());
            //Logger.getLogger(formlaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewLaporanPakan() {
        HashMap parameter = new HashMap();
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanKesehatan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            System.out.print(ex.toString());
            //Logger.getLogger(formlaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
