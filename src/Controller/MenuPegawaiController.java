/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMenuPegawai;
import FormUpPuyuh.FormLaporanKesehatanPeriode;
import FormUpPuyuh.FormLaporanCekTernakPeriode;

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
import User.Enkripsi;
import User.User;
import User.UserDao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
//import java.sql.Date;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
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
    FormLaporanKesehatanPeriode formLaporanKesehatanPeriode;
    FormLaporanCekTernakPeriode formLaporanCekTernakPeriode;
    Kandang kandang;
    Pegawai pegawai;
    Pakan pakan;
    PakanDao pakanDao;
    Penyakit penyakit;
    CekTernak CekTernak;
    Kesehatan kesehatan;
    Connection con;
    KandangDao kandangdao;
    KesehatanDao kesehatandao;
    
    UserController controllerUser = new UserController();
    User user = new User();
    List<User> listPengguna = new ArrayList<User>();
    
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
            
            //         Use  //
 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public MenuPegawaiController (FormLaporanKesehatanPeriode formLaporanKesehatanPeriode) {
        try {
            this.formLaporanKesehatanPeriode = formLaporanKesehatanPeriode;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public MenuPegawaiController (FormLaporanCekTernakPeriode formLaporanCekTernakPeriode) {
        try {
            this.formLaporanCekTernakPeriode = formLaporanCekTernakPeriode;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void changePassword(){
        FormMenuPegawai formMenuPegawai = new FormMenuPegawai();
        if (viewPegawai.getTxtUsername().getText().isEmpty() || viewPegawai.getJpsPassword().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong" , "pesan", JOptionPane.WARNING_MESSAGE);
        } else {
            if (viewPegawai.getJpsPassword().getText() == null ? viewPegawai.getJpsRePassword().getText() != null : !viewPegawai.getJpsPassword().getText().equals(viewPegawai.getJpsRePassword().getText())) {
                JOptionPane.showMessageDialog(null, "Password Tidak Cocok, Perisak Kembali","pesan",JOptionPane.WARNING_MESSAGE);
            viewPegawai.getJpsPassword().requestFocus();
            } else {
                //listPengguna = controllerUser.username(viewPegawai.getTxtUsername().getText());
                user = controllerUser.getUser(viewPegawai.getTxtUsername().getText());
                controllerUser.updatePassword(user, viewPegawai.getJpsPassword().getText());
                JOptionPane.showMessageDialog(viewPegawai, "Password Sudah di Ganti");
            }
        }
    }
    
    
    public int getNotif1(String tgl){
        try {
            String sql = "select notif1 from notif where tgl=?";
            java.sql.Date date=java.sql.Date.valueOf(tgl);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x = rs.getInt(1);
            }
            return x;
        } catch (SQLException e) {
            
        }
        return 2;
    }
    
    public int getNotif2(String tgl){
        try {
            String sql = "select notif2 from notif where tgl=?";
            java.sql.Date date=java.sql.Date.valueOf(tgl);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x = rs.getInt(1);
            }
            return x;
        } catch (SQLException e) {
            
        }
        return 2;
    }
    
    public int getNotif3(String tgl){
        try {
            String sql = "select notif3 from notif where tgl=?";
            java.sql.Date date=java.sql.Date.valueOf(tgl);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x = rs.getInt(1);
            }
            return x;
        } catch (SQLException e) {
            
        }
        return 2;
    }
    
    public void toHideNotif1(){
        try {
            Date date = new Date();
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            String sql = "update notif set notif1=? where tgl=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setDate(2, sDate);
            ps.executeUpdate();
            System.out.print("Done");
        } catch (SQLException e) {
            System.out.print(e);
        } catch (Exception e){
            System.out.print(e);
        }
    }
    
    public void toHideNotif2(){
        try {
            Date date = new Date();
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            String sql = "update notif set notif2=? where tgl=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setDate(2, sDate);
            ps.executeUpdate();
            System.out.print("Done");
        } catch (SQLException e) {
            System.out.print(e);
        } catch (Exception e){
            System.out.print(e);
        }
    }
    
    public void toHideNotif3(){
        try {
            Date date = new Date();
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            String sql = "update notif set notif3=? where tgl=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setDate(2, sDate);
            ps.executeUpdate();
            System.out.print("Done");
        } catch (SQLException e) {
            System.out.print(e);
        } catch (Exception e){
            System.out.print(e);
        }
    }
    
    public void showNotif(){
        Date date = new Date();
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        String text = df.format(sDate);  
        int x = getNotif1(text);
        int y = getNotif2(text);
        int z = getNotif3(text);
        if(x == 0){
            viewPegawai.getNotifikasi1().setVisible(true);
        } else {
            viewPegawai.getNotifikasi1().setVisible(false);
        }
        if (y == 0) {
            viewPegawai.getNotifikasi2().setVisible(true);
        } else {
            viewPegawai.getNotifikasi2().setVisible(false);
        }
        if (z == 0) {
            viewPegawai.getNotifikasi3().setVisible(true);
        } else {
            viewPegawai.getNotifikasi3().setVisible(false);
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
        kandang.setJmlTernak(Integer.parseInt(viewPegawai.getTxtJumlahTernak().getText()));
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di perbaharui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteKandang() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Data Kandang = "+viewPegawai.getTxtNamaKandang().getText()+" Terikat dengan Data Kesehatan atau Data Cek Ternak");
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
                    rs.getInt(2)+ " Ekor"
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
                    rs.getInt(2)+ " Ekor"
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
            JOptionPane.showMessageDialog(viewPegawai, "ID Pegawai Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE); 
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
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Perbaharui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePegawai() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Data Pegawai = "+viewPegawai.getTxtIdPegawai().getText()+" - "+viewPegawai.getTxtNamaPegawai().getText()+" Terikat dengan Data Kesehatan atau Data Kandang");
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
    
    public void onClickBtnCariPegawai() throws ParseException {
        try {
            String kode = viewPegawai.getTxtIdPegawai().getText();
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
            ResultSet rs = con.createStatement().executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
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
            ResultSet rs = con.createStatement().executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
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
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Simpan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "ID Pakan Sudah Tersedia","pesan",JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Perbaharui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePakan() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewPegawai, "Data Pakan = "+viewPegawai.getTxtIdPakan().getText()+" - "+viewPegawai.getTxtNamaPakan().getText()+" Terikat dengan Data Cek Ternak");
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
    
    public void onClickBtnCariPakan() throws ParseException {
        try {
            String kode = viewPegawai.getTxtIdPakan().getText();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewPegawai.getTxtIdPakan().setText(pakan.getIdPakan());
                viewPegawai.getTxtNamaPakan().setText(pakan.getNama());
                viewPegawai.getTxtHarga().setText(""+pakan.getHarga());
                viewPegawai.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormPakan();
                DateNow();
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4) + " Kg"
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4)+ " Kg"
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
            JOptionPane.showMessageDialog(viewPegawai, "Data Penyakit = "+viewPegawai.getTxtNamaPenyakit().getText()+" Terikat dengan Data Kesehatan");
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
    
    public void onClickTabelDataPenyakit() {
        try {
            String kode = viewPegawai.getTblDataPenyakit().getValueAt(viewPegawai.getTblDataPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewPegawai.getJtxGejalaData().setText(penyakit.getGejala());
                viewPegawai.getJtxPenularanData().setText(penyakit.getPenularan());
                viewPegawai.getJtxPencegahanData().setText(penyakit.getPencegahan());
                viewPegawai.getJtxPengobatanData().setText(penyakit.getPengobatan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormPenyakit();
                DateNow();
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
        kesehatan.setIdPegawai(viewPegawai.getCboIdPegawai().getSelectedItem().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewPegawai.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
        kesehatan.setTglCek(viewPegawai.getTxtTglCekKesehatan().getText());
        try {
            kandang = kandangdao.getKandang(con,viewPegawai.getCboKandang().getSelectedItem().toString());
            System.out.print(kandang.getNamaKandang());
            System.out.print(kandang.getJmlTernak());
            int mati = Integer.parseInt(viewPegawai.getTxtJmlMati().getText());
            int ternak = kandang.getJmlTernak();
            if (mati > ternak) {
                JOptionPane.showMessageDialog(viewPegawai, "Jumlah Mati Sudah Melebihi Jumlah Ternak");
            } else {
                KesehatanDao.insert(con, kesehatan);
                kandangdao.update(con, kandang,Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
                //kandangdao.update(con, kandang);
                JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Simpan");
                DateNow();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah Ada Hari Ini"); 
//            JOptionPane.showMessageDialog(viewPegawai, "Error " +ex); 
        }
    }
    
    
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
            kandang = kandangdao.getKandang(con,viewPegawai.getCboKandang().getSelectedItem().toString());
            Kesehatan k1 = new Kesehatan();
            k1 = kesehatandao.getKesehatan(con,viewPegawai.getTxtIdKesehatan().getText());
            int x = k1.getJmlMati();
            kandangdao.update(con, kandang,Integer.parseInt(viewPegawai.getTxtJmlMati().getText()),x);
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Perbaharui");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteKesehatan() {
        try {
            kandang = kandangdao.getKandang(con,viewPegawai.getCboKandang().getSelectedItem().toString());
            Kesehatan k1 = new Kesehatan();
            k1 = kesehatandao.getKesehatan(con,viewPegawai.getCboKandang().getSelectedItem().toString());
            kandangdao.updateDelete(con, kandang,Integer.parseInt(viewPegawai.getTxtJmlMati().getText()));
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
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
            System.out.println("1");
            if (kesehatan != null) {
                System.out.println("2");
                viewPegawai.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewPegawai.getCboKandang().setSelectedItem(kesehatan.getNamaKandang());
                viewPegawai.getCboPenyakit().setSelectedItem(kesehatan.getNamaPenyakit());
                viewPegawai.getCboIdPegawai().setSelectedItem(kesehatan.getIdPegawai());
                viewPegawai.getTxtJmlSakit().setText(""+kesehatan.getJmlSakit());
                viewPegawai.getTxtJmlMati().setText(""+kesehatan.getJmlMati());
                viewPegawai.getTxtTglCekKesehatan().setText(kesehatan.getTglCek());
            } else {
                System.out.println("3");
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormKesehatan();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onPressBtnCariKesehatan() {
        try {
            String kode = viewPegawai.getTxtIdKesehatan().getText();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            if (kesehatan != null) {
                viewPegawai.getTxtIdKesehatan().setText(kesehatan.getIdKesehatan());
                viewPegawai.getCboKandang().setSelectedItem(kesehatan.getNamaKandang());
                viewPegawai.getCboPenyakit().setSelectedItem(kesehatan.getNamaPenyakit());
                viewPegawai.getCboIdPegawai().setSelectedItem(kesehatan.getIdPegawai());
                viewPegawai.getTxtJmlSakit().setText(""+kesehatan.getJmlSakit());
                viewPegawai.getTxtJmlMati().setText(""+kesehatan.getJmlMati());
                viewPegawai.getTxtTglCekKesehatan().setText(kesehatan.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormKesehatan();
                DateNow();
            }
        } catch (Exception ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewPegawai.getTblDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)+" Ekor",
                    rs.getInt(6)+" Ekor",
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
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)+" Ekor",
                    rs.getInt(6)+" Ekor",
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
        viewPegawai.getTxtJmlPakan().setText("");
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
        CekTernak.setJmlPakan(Integer.parseInt(viewPegawai.getTxtJmlPakan().getText()));
        CekTernak.setIdPegawai(viewPegawai.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewPegawai.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewPegawai.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewPegawai.getTxtTglCek().getText());
        try {
            Pakan pakan;
            pakan = new Pakan();
            pakan = PakanDao.getPakan(con, viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            int jml_stok = Integer.parseInt(viewPegawai.getTxtJmlPakan().getText());
            int jml_pakan = pakan.getStok();
            if (jml_stok > jml_pakan) {
                JOptionPane.showMessageDialog(viewPegawai, "Jumlah Pemakaian Sudah Melebihi Jumlah Stok");
            } else {
                CekTernakDao.insert(con, CekTernak);
                PakanDao.update(con, pakan, Integer.parseInt(viewPegawai.getTxtJmlPakan().getText()));
                JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Simpan");
                DateNow();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah Ada Hari ini !"); 
            DateNow();
        }
    }
    
    public void updateCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewPegawai.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewPegawai.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlPakan(Integer.parseInt(viewPegawai.getTxtJmlPakan().getText()));
        CekTernak.setIdPegawai(viewPegawai.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewPegawai.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewPegawai.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewPegawai.getTxtTglCek().getText());
        try {
            pakan = PakanDao.getPakan(con, viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            CekTernak cekTernak = new CekTernak();
            cekTernak = CekTernakDao.getCekTernak(con, viewPegawai.getTxtIdCekTernak().getText());
            int y = cekTernak.getJmlPakan();
            pakanDao.update(con, pakan, Integer.parseInt(viewPegawai.getTxtJmlPakan().getText()), y);
            CekTernakDao.update(con, CekTernak);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Perbaharui");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewPegawai, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteCekTernak() {
        try {
            pakan = PakanDao.getPakan(con, viewPegawai.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            CekTernak cekTernak = new CekTernak();
            cekTernak = CekTernakDao.getCekTernak(con, viewPegawai.getTxtIdCekTernak().getText());
            pakanDao.updateDelete(con, pakan, Integer.parseInt(viewPegawai.getTxtJmlPakan().getText()));
            CekTernakDao.delete(con, CekTernak);
            JOptionPane.showMessageDialog(viewPegawai, "Data Sudah di Hapus");
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
                viewPegawai.getTxtJmlPakan().setText(""+CekTernak.getJmlPakan());
                viewPegawai.getCboIdPegawaiCek().setSelectedItem(CekTernak.getIdPegawai());
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
    
    public void onClickBtnCariCekTernak() throws ParseException {
        try {
            String kode = viewPegawai.getTxtIdCekTernak().getText();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewPegawai.getTxtIdCekTernak().setText(CekTernak.getIdCek());
                viewPegawai.getCboKandangCek().setSelectedItem(CekTernak.getNamaKandang());
                viewPegawai.getCboIdPakanCek().setSelectedItem(CekTernak.getIdPakan());
                viewPegawai.getTxtJmlPakan().setText(""+CekTernak.getJmlPakan());
                viewPegawai.getCboIdPegawaiCek().setSelectedItem(CekTernak.getIdPegawai()); 
                viewPegawai.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewPegawai.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewPegawai.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewPegawai, "Data Tidak Ada");
                clearFormCekTernak();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPegawaiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewTableDataCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewPegawai.getTblDataCekTernak().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_cek, nama_kandang, concat (pakan.nama)'id_pakan', "
                    + "jml_pakan, concat(pegawai.nama)'id_pegawai', "
                    + "jml_telur, kebersihan, date_format(tgl_cek, '%d %M %Y')"
                    + "from cek_ternak "
                    + "join pakan "
                    + "using (id_pakan) "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4) + " Kg",
                    rs.getString(5),
                    rs.getInt(6) + " Buah",
                    rs.getString(7),
                    rs.getString(8)
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
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_cek, nama_kandang, concat (pakan.nama)'id_pakan', "
                    + "jml_pakan, concat(pegawai.nama)'id_pegawai', "
                    + "jml_telur, kebersihan, date_format(tgl_cek, '%d %M %Y') "
                    + "from cek_ternak "
                    + "join pakan "
                    + "using (id_pakan) "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4) + " Kg",
                    rs.getString(5),
                    rs.getInt(6) + " Buah",
                    rs.getString(7),
                    rs.getString(8)
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
            row1.createCell(6).setCellValue("Tanggal Cek");
            Row row2 ;
            ResultSet rs = statement.executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getInt(5)+" Ekor");
                row2.createCell(5).setCellValue(rs.getInt(6)+" Ekor");
                row2.createCell(6).setCellValue(rs.getString(7));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Partisi C");
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
            row1.createCell(3).setCellValue("Jumlah Pakan");
            row1.createCell(4).setCellValue("ID Pegawai");
            row1.createCell(5).setCellValue("Jumlah Telur");
            row1.createCell(6).setCellValue("Kebersihan");
            row1.createCell(7).setCellValue("Tanggal Cek");
            Row row2 ;
            ResultSet rs = statement.executeQuery(""
                    + "select id_cek, nama_kandang, concat (pakan.nama)'id_pakan', "
                    + "jml_pakan, concat(pegawai.nama)'id_pegawai', "
                    + "jml_telur, kebersihan, date_format(tgl_cek, '%d %M %Y')"
                    + "from cek_ternak "
                    + "join pakan "
                    + "using (id_pakan) "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getInt(4)+" Kg");
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getInt(6)+" Buah");
                row2.createCell(6).setCellValue(rs.getString(7));
                row2.createCell(7).setCellValue(rs.getString(8));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Partisi C");
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
            row1.createCell(1).setCellValue("Nama Pegawai");
            row1.createCell(2).setCellValue("Asal Daerah");
            row1.createCell(3).setCellValue("Tanggal Lahir");
            row1.createCell(4).setCellValue("Jenis Kelamin");
            row1.createCell(5).setCellValue("No Telepon");
            row1.createCell(6).setCellValue("Alamat");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
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
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Partisi C");
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
                row2.createCell(2).setCellValue("Rp."+rs.getInt(3));
                row2.createCell(3).setCellValue(rs.getInt(4)+ " Kg");
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Partisi C");
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
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)+" Ekor",
                    rs.getInt(6)+" Ekor",
                    rs.getString(7)
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
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_cek, nama_kandang, concat (pakan.nama)'id_pakan', "
                    + "jml_pakan, concat(pegawai.nama)'id_pegawai', "
                    + "jml_telur, kebersihan, date_format(tgl_cek, '%d %M %Y')"
                    + "from cek_ternak "
                    + "join pakan "
                    + "using (id_pakan) "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek desc"
            );
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4) + " Kg",
                    rs.getString(5),
                    rs.getInt(6) + " Buah",
                    rs.getString(7),
                    rs.getString(8)
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
            ResultSet rs = con.createStatement().executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4)+" Kg"
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
    
    public void previewLaporanKesehatanPeriode() {
        HashMap parameter = new HashMap();
        parameter.put("awal", formLaporanKesehatanPeriode.getJDateAwal().getDate());
        parameter.put("akhir", formLaporanKesehatanPeriode.getJDateAkhir().getDate());
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanKesehatanPeriode.jasper", parameter, con);
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
    
    public void previewLaporanCekTernakPeriode() {
        HashMap parameter = new HashMap();
        parameter.put("awal", formLaporanCekTernakPeriode.getJDateAwal().getDate());
        parameter.put("akhir", formLaporanCekTernakPeriode.getJDateAkhir().getDate());
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("report/LaporanCekTernakPeriode.jasper", parameter, con);
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
