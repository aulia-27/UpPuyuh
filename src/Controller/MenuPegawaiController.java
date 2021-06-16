/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMenuPegawai;

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
import java.text.ParseException;
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
public class MenuPegawaiController {
    FormMenuPegawai viewPegawai;
    Kandang kandang;
    Pegawai pegawai;
    Pakan pakan;
    Penyakit penyakit;
    CekTernak CekTernak;
    Kesehatan kesehatan;
    Connection con;
    
    public MenuPegawaiController (FormMenuPegawai viewPegawai) {
        try {
            this.viewPegawai = viewPegawai;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            
            DateNow();
            
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
            isiCboIdPakanCek();
            isiCboIdPegawaiCek();
            isiCboKandangCek();
            isiCboKebersihan();
            viewTableDataCekTernak();
            viewTableInputCekTernak();
            
 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
        
    public void DateNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewPegawai.getTxtTglCek().setText(sdf.format(d));
        viewPegawai.getTxtTglCekKesehatan().setText(sdf.format(d));
    }
    
    /////////////////////       Kandang     ////////////////////////////////////
    
    public void clearFormKandang(){
        viewPegawai.getTxtNamaKandang().setText("");
        viewPegawai.getTxtJumlahTernak().setText("");
    }
    
    public void insertKandang(){
        kandang = new Kandang();
        kandang.setNamaKandang(viewPegawai.getTxtNamaKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewPegawai.getTxtJumlahTernak().getText()));
        try {
            KandangDao.insert(con, kandang);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Inputkan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Nama Kandang Sudah Ada", "Pesan", JOptionPane.WARNING_MESSAGE); 
        }
    }
    
    public void updateKandang() {
        kandang = new Kandang();
        kandang.setNamaKandang(viewPegawai.getTxtNamaKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewPegawai.getTxtNamaKandang().getText()));
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Pembaruan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteKandang() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error " +e);
        }
    }
    
    public void onClickTabelKandang() {
        try {
            String kode = viewPegawai.getTblInputDataKandang().getValueAt(viewPegawai.getTblInputDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewPegawai.getTxtNamaKandang().setText(kandang.getNamaKandang());
                viewPegawai.getTxtJumlahTernak().setText(""+kandang.getJmlTernak());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormKandang();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblDataKandang().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void viewTableInputKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblInputDataKandang().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////        Pegawai         ////////////////////////////
    
     public void clearFormPegawai(){
        viewPegawai.getTxtIdPegawai().setText("");
        viewPegawai.getTxtNamaPegawai().setText("");
        viewPegawai.getTxtAsal().setText("");
        viewPegawai.getJdtTglLahir().setDate(null);
        viewPegawai.getTxtNoTelp().setText("");
        viewPegawai.getJtxAlamat().setText("");
    }
     
     public void jekel() {
         viewPegawai.getRbLakiLaki().setText("Laki-Laki");
         viewPegawai.getRbPerempuan().setText("Perempuan");
     }
    
    public void insertPegawai(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewPegawai.getTxtIdPegawai().getText());
        pegawai.setNama(viewPegawai.getTxtNamaPegawai().getText());
        pegawai.setAsal(viewPegawai.getTxtAsal().getText());

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(viewPegawai.getJdtTglLahir().getDate());
        pegawai.setTglLahir(strDate);

        if(viewPegawai.getRbLakiLaki().isSelected())  {
            String Jekel;
            Jekel = "Laki-Laki";
            pegawai.setJekel(Jekel);
        }
        else if(viewPegawai.getRbPerempuan().isSelected()) {
            String Jekel;
            Jekel = "Perempuan";
            pegawai.setJekel(Jekel);
        }

        pegawai.setNoTelp(viewPegawai.getTxtNoTelp().getText());
        pegawai.setAlamat(viewPegawai.getJtxAlamat().getText());
        try {
            PegawaiDao.insert(con, pegawai);
            JOptionPane.showMessageDialog(viewPegawai, "Data sudah di inputkan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "ID Pegawai Sudah Ada"); 
        }
    }
    
    public void updatePegawai(){
        pegawai = new Pegawai();
        pegawai.setIdPegawai(viewPegawai.getTxtIdPegawai().getText());
        pegawai.setNama(viewPegawai.getTxtNamaPegawai().getText());
        pegawai.setAsal(viewPegawai.getTxtAsal().getText());

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(viewPegawai.getJdtTglLahir().getDate());
        pegawai.setTglLahir(strDate);

        if(viewPegawai.getRbLakiLaki().isSelected())  {
            String Jekel;
            Jekel = "Laki-Laki";
            pegawai.setJekel(Jekel);
        }
        else if(viewPegawai.getRbPerempuan().isSelected()) {
            String Jekel;
            Jekel = "Perempuan";
            pegawai.setJekel(Jekel);
        }

        pegawai.setNoTelp(viewPegawai.getTxtNoTelp().getText());
        pegawai.setAlamat(viewPegawai.getJtxAlamat().getText());
        try {
            PegawaiDao.update(con, pegawai);
            JOptionPane.showMessageDialog(viewPegawai, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePegawai() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewPegawai, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelPegawai() throws ParseException{
        try {
            String kode = viewPegawai.getTblInputDataPegawai().getValueAt(viewPegawai.getTblInputDataPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewPegawai.getTxtIdPegawai().setText(pegawai.getIdPegawai());
                viewPegawai.getTxtNamaPegawai().setText(pegawai.getNama());
                viewPegawai.getTxtAsal().setText(pegawai.getAsal());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)pegawai.getTglLahir());
                viewPegawai.getJdtTglLahir().setDate(date);
                String jekel = pegawai.getJekel().toString();
                if (jekel.equals("Laki-Laki")) {
                    viewPegawai.getRbLakiLaki().setSelected(true);
                } else {
                    viewPegawai.getRbPerempuan().setSelected(true);
                }
                viewPegawai.getTxtNoTelp().setText(pegawai.getNoTelp());
                viewPegawai.getJtxAlamat().setText(pegawai.getAlamat());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormPegawai();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataPegawai(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblDataPegawai().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPegawai(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblInputDataPegawai().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    ////////////////                Pakan                   ////////////////////
    
    public void clearFormPakan(){
        viewPegawai.getTxtIdPakan().setText("");
        viewPegawai.getTxtNamaPakan().setText("");
        viewPegawai.getTxtHarga().setText("");
        viewPegawai.getTxtStok().setText("");
    }
    
    public void insertPakan(){
        try {
            pakan = new Pakan();
            pakan.setIdPakan(viewPegawai.getTxtIdPakan().getText());
            pakan.setNama(viewPegawai.getTxtNamaPakan().getText());
            pakan.setHarga(Double.parseDouble(viewPegawai.getTxtHarga().getText()));
            pakan.setStok(Integer.parseInt(viewPegawai.getTxtStok().getText()));
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void updatePakan(){
        pakan = new Pakan();
        pakan.setIdPakan(viewPegawai.getTxtIdPakan().getText());
        pakan.setNama(viewPegawai.getTxtNamaPakan().getText());
        pakan.setHarga(Double.parseDouble(viewPegawai.getTxtHarga().getText()));
        pakan.setStok(Integer.parseInt(viewPegawai.getTxtStok().getText()));
        try {
            PakanDao.update(con, pakan);
            JOptionPane.showMessageDialog(viewPegawai, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePakan() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewPegawai, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelPakan() {
        try {
            String kode = viewPegawai.getTblInputDataPakan().getValueAt(viewPegawai.getTblInputDataPakan().getSelectedRow(), 0).toString();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewPegawai.getTxtIdPakan().setText(pakan.getIdPakan());
                viewPegawai.getTxtNamaPakan().setText(pakan.getNama());
                viewPegawai.getTxtHarga().setText(""+pakan.getHarga());
                viewPegawai.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormPakan();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblDataPakan().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblInputDataPakan().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////        Penyakit        ///////////////////////////////
    
    public void clearFormPenyakit(){
        viewPegawai.getTxtNamaPenyakit().setText("");
        viewPegawai.getJtxGejala().setText("");
        viewPegawai.getJtxPenularan().setText("");
        viewPegawai.getJtxPencegahan().setText("");
        viewPegawai.getJtxPengobatan().setText("");
    }
    
    public void insertPenyakit(){
        penyakit = new Penyakit();
        penyakit.setNamaPenyakit(viewPegawai.getTxtNamaPenyakit().getText());
        penyakit.setGejala(viewPegawai.getJtxGejala().getText());
        penyakit.setPenularan(viewPegawai.getJtxPenularan().getText());
        penyakit.setPencegahan(viewPegawai.getJtxPencegahan().getText());
        penyakit.setPengobatan(viewPegawai.getJtxPengobatan().getText());
        try {
            PenyakitDao.insert(con, penyakit);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void updatePenyakit(){
        penyakit = new Penyakit();
        penyakit.setNamaPenyakit(viewPegawai.getTxtNamaPenyakit().getText());
        penyakit.setGejala(viewPegawai.getJtxGejala().getText());
        penyakit.setPenularan(viewPegawai.getJtxPenularan().getText());
        penyakit.setPencegahan(viewPegawai.getJtxPencegahan().getText());
        penyakit.setPengobatan(viewPegawai.getJtxPengobatan().getText());
        try {
            PenyakitDao.update(con, penyakit);
            JOptionPane.showMessageDialog(viewPegawai, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePenyakit() {
        try {
            PenyakitDao.delete(con, penyakit);
            JOptionPane.showMessageDialog(viewPegawai, "Delete Data OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelPenyakit() {
        try {
            String kode = viewPegawai.getTblInputDataPenyakit().getValueAt(viewPegawai.getTblInputDataPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewPegawai.getTxtNamaPenyakit().setText(penyakit.getNamaPenyakit());
                viewPegawai.getJtxGejala().setText(penyakit.getGejala());
                viewPegawai.getJtxPenularan().setText(penyakit.getPenularan());
                viewPegawai.getJtxPencegahan().setText(penyakit.getPencegahan());
                viewPegawai.getJtxPengobatan().setText(penyakit.getPengobatan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormPenyakit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataPenyakit() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblDataPenyakit().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputPenyakit() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblInputDataPenyakit().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /////////////////////////       Kesehatan       ////////////////////////////
    
    public void clearFormKesehatan(){
        viewPegawai.getTxtIdKesehatan().setText("");
        viewPegawai.getTxtNamaKandang().setText("");
        viewPegawai.getTxtJmlSakit().setText("");
        viewPegawai.getTxtJmlMati().setText("");
    }
    
    public void isiCboPenyakit() {
        viewPegawai.getCboPenyakit().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from penyakit");
            while (rs.next()) {                
                viewPegawai.getCboPenyakit().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboKandang() {
        viewPegawai.getCboKandang().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewPegawai.getCboKandang().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPegawai() {
        viewPegawai.getCboIdPegawai().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while (rs.next()) {                
                viewPegawai.getCboIdPegawai().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewPegawai.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewPegawai.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewPegawai.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewPegawai.getCboIdPegawai().getSelectedItem().toString().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewPegawai.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
        kesehatan.setTglCek(viewPegawai.getTxtTglCekKesehatan().getText());
        try {
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    /*public void insert(){
        try {
            kandang = KandangDao.getKandangCek(con, kodeKandang);
            kandang.getJmlTernak();
            
            kesehatan = new Kesehatan();
            kesehatan.setIdKesehatan(viewPegawai.getTxtIdKesehatan().getText());
            kesehatan.setIdKandang(viewPegawai.getTxtIdKandang().getText());
            kesehatan.setIdSakit(viewPegawai.getCboIdPenyakit().getSelectedItem().toString().split("-")[0]);
            kesehatan.setJmlSakit(Integer.parseInt(viewPegawai.getTxtJmlSakit().getText()));
            kesehatan.setJmlMati(Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
            kandang.setJmlTernak(Integer.parseInt(viewPegawai.getTxtTotal().getText()));
            kandangDao.update(con, kandang);
            KesehatanDao.insert(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    } */
    
    
    public void updateKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewPegawai.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewPegawai.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewPegawai.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewPegawai.getCboIdPegawai().getSelectedItem().toString().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewPegawai.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
        kesehatan.setTglCek(viewPegawai.getTxtTglCekKesehatan().getText());
        try {
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteKesehatan() {
        try {
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Delete Data OK");
            DateNow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error"+e.getMessage());
            DateNow();
        }
    }
    
    public void onClickTabelKesehatan() {
        try {
            String kode = viewPegawai.getTblInputDataKesehatan().getValueAt(viewPegawai.getTblInputDataKesehatan().getSelectedRow(), 0).toString();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            if (kesehatan != null) {
                viewPegawai.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewPegawai.getCboKandang().setSelectedItem(kesehatan.getNamaKandang());
                viewPegawai.getCboPenyakit().setSelectedItem(kesehatan.getNamaPenyakit());
                viewPegawai.getCboIdPegawai().setSelectedItem(kesehatan.getIdPegawai());
                viewPegawai.getTxtJmlSakit().setText(""+kesehatan.getJmlMati());
                viewPegawai.getTxtJmlMati().setText(""+kesehatan.getJmlSakit());
                viewPegawai.getTxtTglCekKesehatan().setText(kesehatan.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormKesehatan();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblInputDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////        Cek Ternak          ///////////////////////////////
    
    public void clearFormCekTernak(){
        viewPegawai.getTxtIdCekTernak().setText("");
        viewPegawai.getTxtJmlTelur().setText("");
    }
    
    public void isiCboKandangCek() {
        viewPegawai.getCboKandangCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while (rs.next()) {                
                viewPegawai.getCboKandangCek().addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPakanCek() {
        viewPegawai.getCboIdPakanCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            while (rs.next()) {                
                viewPegawai.getCboIdPakanCek().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboIdPegawaiCek() {
        viewPegawai.getCboIdPegawaiCek().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            while (rs.next()) {                
                viewPegawai.getCboIdPegawaiCek().addItem(rs.getString(1)+" - "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboKebersihan(){
        viewPegawai.getCboKebersihan().removeAllItems();
        viewPegawai.getCboKebersihan().addItem("Sangat bersih");
        viewPegawai.getCboKebersihan().addItem("Bersih");
        viewPegawai.getCboKebersihan().addItem("Kurang Bersih");
        viewPegawai.getCboKebersihan().addItem("Kotor");
    }
    
    public void insertCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewPegawai.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewPegawai.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPegawai(viewPegawai.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewPegawai.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewPegawai.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewPegawai.getTxtTglCek().getText());
        try {
            CekTernakDao.insert(con, CekTernak);
            JOptionPane.showMessageDialog(viewPegawai, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void updateCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewPegawai.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewPegawai.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setIdPegawai(viewPegawai.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewPegawai.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewPegawai.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewPegawai.getTxtTglCek().getText());
        try {
            CekTernakDao.update(con, CekTernak);
            JOptionPane.showMessageDialog(viewPegawai, "Update Data Ok");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteCekTernak() {
        try {
            CekTernakDao.delete(con, CekTernak);
            JOptionPane.showMessageDialog(viewPegawai, "Delete Data OK");
            DateNow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelCekTernak() {
        try {
            String kode = viewPegawai.getTblInputDataCekTernak().getValueAt(viewPegawai.getTblInputDataCekTernak().getSelectedRow(), 0).toString();
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewPegawai.getTxtIdCekTernak().setText(CekTernak.getIdCek());
                viewPegawai.getCboKandangCek().setSelectedItem(CekTernak.getNamaKandang());
                viewPegawai.getCboIdPakanCek().setSelectedItem(CekTernak.getIdPakan());
                viewPegawai.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewPegawai.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewPegawai.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormCekTernak();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblDataCekTernak().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblInputDataCekTernak().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /////////////////////       Laporan         ////////////////////////////////
   
    public void cleartextLaporan() {
         /*viewPegawai.getTxtKolom1().setText("");
        viewPegawai.getTxtKolom2().setText("");
        viewPegawai.getTxtKolom3().setText("");
        viewPegawai.getTxtKolom4().setText("");
        viewPegawai.getTxtKolom5().setText("");
        viewPegawai.getTxtKolom6().setText("");
        viewPegawai.getTxtKolom7().setText("");    */
        viewPegawai.getTxtKodeLaporan().setText("");
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
            JOptionPane.showInternalMessageDialog(viewPegawai, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewPegawai, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewPegawai, ioe.getMessage());
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
            JOptionPane.showInternalMessageDialog(viewPegawai, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewPegawai, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewPegawai, ioe.getMessage());
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
            JOptionPane.showInternalMessageDialog(viewPegawai, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewPegawai, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewPegawai, ioe.getMessage());
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
            JOptionPane.showInternalMessageDialog(viewPegawai, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(viewPegawai, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(viewPegawai, ioe.getMessage());
        }
    }
    
    public void viewTableKesehatanLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblLaporanKesehantan().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableCekTernakLaporan(){
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblLaporanCekTernak().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTablePegawaiLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblLaporanPegawai().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewTablePakanLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblLaporanPakan().getModel();
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
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
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
            jasperPrint = JasperFillManager.fillReport("report/LaporanCekTernak.jasper", parameter, con);
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
            jasperPrint = JasperFillManager.fillReport("report/LaporanPegawai.jasper", parameter, con);
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
            jasperPrint = JasperFillManager.fillReport("report/LaporanPakan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            System.out.print(ex.toString());
            //Logger.getLogger(formlaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
