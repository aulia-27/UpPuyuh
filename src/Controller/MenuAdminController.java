/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMenuAdmin;
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
public class MenuAdminController {
    FormMenuAdmin viewAdmin;
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
    
    public MenuAdminController (FormMenuAdmin viewAdmin) {
        try {
            this.viewAdmin = viewAdmin;
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
            getAutoIncrementIdPegawai();
            
            //  Pakan   //
            clearFormPakan();
            viewTableDataPakan();
            viewTableInputPakan();
            getAutoIncrementIdPakan();
            
            //  penyakit   //
            clearFormPenyakit();
            viewTableDataPenyakit();
            viewTableInputPenyakit();
            
            //  Kesehatan   //
            clearFormKesehatan();
            viewTableDataKesehatan();
            viewTableInputKesehatan();
            getAutoIncrementIdKesehatan();
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
            getAutoIncrementIdCekTernak();
            
            //         Use  //
            clearFormUser();
            isiCboAkses();
            getAutoIncrementIdUser();
            viewTableDataUser();
 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public MenuAdminController (FormLaporanKesehatanPeriode formLaporanKesehatanPeriode) {
        try {
            this.formLaporanKesehatanPeriode = formLaporanKesehatanPeriode;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public MenuAdminController (FormLaporanCekTernakPeriode formLaporanCekTernakPeriode) {
        try {
            this.formLaporanCekTernakPeriode = formLaporanCekTernakPeriode;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void changePassword(){
        FormMenuAdmin formMenuAdmin = new FormMenuAdmin();
        if (viewAdmin.getTxtUsername().getText().isEmpty() || viewAdmin.getJpsPassword().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong" , "pesan", JOptionPane.WARNING_MESSAGE);
        } else {
            if (viewAdmin.getJpsPassword().getText() == null ? viewAdmin.getJpsRePassword().getText() != null : !viewAdmin.getJpsPassword().getText().equals(viewAdmin.getJpsRePassword().getText())) {
                JOptionPane.showMessageDialog(null, "Password Tidak Cocok, Perisak Kembali","pesan",JOptionPane.WARNING_MESSAGE);
            viewAdmin.getJpsPassword().requestFocus();
            } else {
                //listPengguna = controllerUser.username(viewAdmin.getTxtUsername().getText());
                user = controllerUser.getUser(viewAdmin.getTxtUsername().getText());
                controllerUser.updatePassword(user, viewAdmin.getJpsPassword().getText());
                JOptionPane.showMessageDialog(viewAdmin, "Password Sudah di Ganti");
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
            viewAdmin.getNotifikasi1().setVisible(true);
        } else {
            viewAdmin.getNotifikasi1().setVisible(false);
        }
        if (y == 0) {
            viewAdmin.getNotifikasi2().setVisible(true);
        } else {
            viewAdmin.getNotifikasi2().setVisible(false);
        }
        if (z == 0) {
            viewAdmin.getNotifikasi3().setVisible(true);
        } else {
            viewAdmin.getNotifikasi3().setVisible(false);
        }
        
    }
    
    public void DateNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        viewAdmin.getTxtTglCek().setText(sdf.format(d));
        viewAdmin.getTxtTglCekKesehatan().setText(sdf.format(d));
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
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Inputkan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Nama Kandang Sudah Ada", "Pesan", JOptionPane.WARNING_MESSAGE); 
        }
    }
    
    public void updateKandang() {
        kandang = new Kandang();
        kandang.setNamaKandang(viewAdmin.getTxtNamaKandang().getText());
        kandang.setJmlTernak(Integer.parseInt(viewAdmin.getTxtJumlahTernak().getText()));
        try {
            KandangDao.update(con, kandang);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di perbaharui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteKandang() {
        try {
            KandangDao.delete(con, kandang);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Data Kandang = "+viewAdmin.getTxtNamaKandang().getText()+" Terikat dengan Data Kesehatan atau Data Cek Ternak");
        }
    }
    
    public void onClickTabelKandang() {
        try {
            String kode = viewAdmin.getTblInputDataKandang().getValueAt(viewAdmin.getTblInputDataKandang().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewAdmin.getTxtNamaKandang().setText(kandang.getNamaKandang());
                viewAdmin.getTxtJumlahTernak().setText(""+kandang.getJmlTernak());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormKandang();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataKandang().getModel();
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
                    rs.getInt(2)+ " Ekor"
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////        Pegawai         ////////////////////////////
    public void clearFormPegawai(){
        viewAdmin.getTxtNamaPegawai().setText("");
        viewAdmin.getTxtAsal().setText("");
        viewAdmin.getJdtTglLahir().setDate(null);
        viewAdmin.getRbLakiLaki().setSelected(false);
        viewAdmin.getRbPerempuan().setSelected(false);
        viewAdmin.getTxtNoTelp().setText("");
        viewAdmin.getJtxAlamat().setText("");
    }
    
    public void getAutoIncrementIdPegawai() {
        viewAdmin.getTxtIdPegawai().setText("1001");
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pegawai");
            String cek_id = viewAdmin.getTxtIdPegawai().getText();
            if (cek_id != null) {
                while (rs.next()) {             
                    int id = rs.getInt(1)+1;
                    viewAdmin.getTxtIdPegawai().setText(""+id);
                }
            } else {
                viewAdmin.getTxtIdPegawai().setText("1001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     public void jekel() {
         viewAdmin.getRbLakiLaki().setText("Laki-Laki");
         viewAdmin.getRbPerempuan().setText("Perempuan");
     }
     
     public String getNoTelepon() {
        if (viewAdmin.getTxtNoTelp().getText().length() > 13) {
            return "1";
        }else {
            return viewAdmin.getTxtNoTelp().getText();
        }
    }
    
    public void insertPegawai(){
        if (viewAdmin.getTxtNamaPegawai().getText().equals("") && viewAdmin.getTxtAsal().getText().equals("") && viewAdmin.getTxtNoTelp().getText().equals("")) {
            System.out.println("1");
            JOptionPane.showMessageDialog(viewAdmin, "Silakan isi data"); 
        } else {
            if (getNoTelepon() == "1") {
                JOptionPane.showMessageDialog(viewAdmin, "Nomor Telepon Lebih dari 13 digit","Pesan",JOptionPane.WARNING_MESSAGE); 
            }else {
                pegawai = new Pegawai();
                pegawai.setIdPegawai(viewAdmin.getTxtIdPegawai().getText());
                pegawai.setNama(viewAdmin.getTxtNamaPegawai().getText());
                pegawai.setAsal(viewAdmin.getTxtAsal().getText());
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                if (viewAdmin.getJdtTglLahir().getDate().equals(null)) {
                    JOptionPane.showMessageDialog(viewAdmin, "Silakan isi Tanggal Lahir"); 
                } else {
                    String strDate = dateFormat.format(viewAdmin.getJdtTglLahir().getDate());
                    pegawai.setTglLahir(strDate);
                }
                if(viewAdmin.getRbLakiLaki().isSelected())  {
                    String Jekel;
                    Jekel = "Laki-Laki";
                    pegawai.setJekel(Jekel);
                }
                else if(viewAdmin.getRbPerempuan().isSelected()) {
                    String Jekel;
                    Jekel = "Perempuan";
                    pegawai.setJekel(Jekel);
                } else {
                    JOptionPane.showMessageDialog(viewAdmin, "Silakan Pilih Jenis Kelamin"); 
                }
                pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
                pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
                try {
                    String ID = viewAdmin.getTxtIdPegawai().getText();
                    Pegawai pegawai1 = new Pegawai();
                    pegawai1 = PegawaiDao.getPegawai(con, ID);
                    if (pegawai1 != null) {
                        JOptionPane.showMessageDialog(viewAdmin, "ID Pegawai Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE); 
                    } else {
                        PegawaiDao.insert(con, pegawai);
                        JOptionPane.showMessageDialog(viewAdmin, "Data sudah di inputkan");
                        clearFormPegawai();
                    }
                } catch (Exception ex) {
                    System.err.println("2");
                }
            }
        }
    }
    
    public void updatePegawai(){
        if (viewAdmin.getTxtNamaPegawai().getText().equals("") && viewAdmin.getTxtAsal().getText().equals("") && viewAdmin.getTxtNoTelp().getText().equals("")) {
            System.out.println("1");
            JOptionPane.showMessageDialog(viewAdmin, "Silakan isi data"); 
        } else {
            if (getNoTelepon() == "1") {
                JOptionPane.showMessageDialog(viewAdmin, "Nomor Telepon Lebih dari 13 digit","Pesan",JOptionPane.WARNING_MESSAGE); 
            }else {
                pegawai = new Pegawai();
                pegawai.setIdPegawai(viewAdmin.getTxtIdPegawai().getText());
                pegawai.setNama(viewAdmin.getTxtNamaPegawai().getText());
                pegawai.setAsal(viewAdmin.getTxtAsal().getText());
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                if (viewAdmin.getJdtTglLahir().getDate().equals(null)) {
                    JOptionPane.showMessageDialog(viewAdmin, "Silakan isi Tanggal Lahir"); 
                } else {
                    String strDate = dateFormat.format(viewAdmin.getJdtTglLahir().getDate());
                    pegawai.setTglLahir(strDate);
                }
                if(viewAdmin.getRbLakiLaki().isSelected())  {
                    String Jekel;
                    Jekel = "Laki-Laki";
                    pegawai.setJekel(Jekel);
                }
                else if(viewAdmin.getRbPerempuan().isSelected()) {
                    String Jekel;
                    Jekel = "Perempuan";
                    pegawai.setJekel(Jekel);
                } else {
                    JOptionPane.showMessageDialog(viewAdmin, "Silakan Pilih Jenis Kelamin"); 
                }
                pegawai.setNoTelp(viewAdmin.getTxtNoTelp().getText());
                pegawai.setAlamat(viewAdmin.getJtxAlamat().getText());
                try {
                    PegawaiDao.update(con, pegawai);
                    JOptionPane.showMessageDialog(viewAdmin, "Data sudah di inputkan");
                    clearFormPegawai();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
                }
            }
        }
    }
    
    public void deletePegawai() {
        try {
            PegawaiDao.delete(con, pegawai);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Data Pegawai = "+viewAdmin.getTxtIdPegawai().getText()+" - "+viewAdmin.getTxtNamaPegawai().getText()+" Terikat dengan Data Kesehatan atau Data Kandang");
        }
    }
    
    public void onClickTabelPegawai() throws ParseException{
        try {
            String kode = viewAdmin.getTblInputDataPegawai().getValueAt(viewAdmin.getTblInputDataPegawai().getSelectedRow(), 0).toString();
            pegawai = PegawaiDao.getPegawai(con, kode);
            if (pegawai != null) {
                viewAdmin.getTxtIdPegawai().setText(""+pegawai.getIdPegawai());
                viewAdmin.getTxtNamaPegawai().setText(pegawai.getNama());
                viewAdmin.getTxtAsal().setText(pegawai.getAsal());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)pegawai.getTglLahir());
                viewAdmin.getJdtTglLahir().setDate(date);
                String jekel = pegawai.getJekel().toString();
                if (jekel.equals("Laki-Laki")) {
                    viewAdmin.getRbLakiLaki().setSelected(true);
                } else {
                    viewAdmin.getRbPerempuan().setSelected(true);
                }
                viewAdmin.getTxtNoTelp().setText(pegawai.getNoTelp());
                viewAdmin.getJtxAlamat().setText(pegawai.getAlamat());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormPegawai();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickBtnCariPegawai() throws ParseException {
        try {
            if (viewAdmin.getTxtIdPegawai().getText().equals("")) {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Silakan Isi ID Pegawai");
            } else {
                String kode = viewAdmin.getTxtIdPegawai().getText();
                pegawai = PegawaiDao.getPegawai(con, kode);
                if (pegawai != null) {
                    viewAdmin.getTxtIdPegawai().setText(""+pegawai.getIdPegawai());
                    viewAdmin.getTxtNamaPegawai().setText(pegawai.getNama());
                    viewAdmin.getTxtAsal().setText(pegawai.getAsal());
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)pegawai.getTglLahir());
                    viewAdmin.getJdtTglLahir().setDate(date);
                    String jekel = pegawai.getJekel().toString();
                    if (jekel.equals("Laki-Laki")) {
                        viewAdmin.getRbLakiLaki().setSelected(true);
                    } else {
                        viewAdmin.getRbPerempuan().setSelected(true);
                    }
                    viewAdmin.getTxtNoTelp().setText(pegawai.getNoTelp());
                    viewAdmin.getJtxAlamat().setText(pegawai.getAlamat());
                } else {
                    javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                    clearFormPegawai();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void onSearchDataPegawai() {
//        String data = viewAdmin.getTxtPencarianDataPegawai().getText().toLowerCase();
//        try {
//            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataPegawai().getModel();
//            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tabelModel);
//            viewAdmin.getTblInputDataPegawai().setRowSorter(tr);
//            tr.setRowFilter(RowFilter.regexFilter(data));
//        } catch (Exception e) {
//        }
//    }
    
    public void viewTableDataPegawai(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataPegawai().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
            while(rs.next()){
                Object[] data={
                    rs.getInt(1),
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
            ResultSet rs = con.createStatement().executeQuery("select id_pegawai, nama, asal, date_format(tgl_lahir, '%d %M %Y'), jekel, no_telp, alamat from pegawai");
            while(rs.next()){
                Object[] data={
                    rs.getInt(1),
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
        viewAdmin.getTxtNamaPakan().setText("");
        viewAdmin.getTxtHarga().setText("");
        viewAdmin.getTxtStok().setText("");
    }
    
    public void getAutoIncrementIdPakan() {
        viewAdmin.getTxtIdPakan().setText("2001");
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from pakan");
            String cek_id = viewAdmin.getTxtIdPakan().getText();
            if (cek_id != null) {
                while (rs.next()) {             
                    int id = rs.getInt(1)+1;
                    viewAdmin.getTxtIdPakan().setText(""+id);
                }
            } else {
                viewAdmin.getTxtIdPakan().setText("2001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertPakan(){
        try {
            pakan = new Pakan();
            pakan.setIdPakan(viewAdmin.getTxtIdPakan().getText());
            pakan.setNama(viewAdmin.getTxtNamaPakan().getText());
            pakan.setHarga(Double.parseDouble(viewAdmin.getTxtHarga().getText()));
            pakan.setStok(Integer.parseInt(viewAdmin.getTxtStok().getText()));
            PakanDao.insert(con, pakan);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Simpan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "ID Pakan Sudah Tersedia","pesan",JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Perbaharui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
        }
    }
    
    public void deletePakan() {
        try {
            PakanDao.delete(con, pakan);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Data Pakan = "+viewAdmin.getTxtIdPakan().getText()+" - "+viewAdmin.getTxtNamaPakan().getText()+" Terikat dengan Data Cek Ternak");
        }
    }
    
    public void onClickTabelPakan() {
        try {
            String kode = viewAdmin.getTblInputDataPakan().getValueAt(viewAdmin.getTblInputDataPakan().getSelectedRow(), 0).toString();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewAdmin.getTxtIdPakan().setText(""+pakan.getIdPakan());
                viewAdmin.getTxtNamaPakan().setText(pakan.getNama());
                viewAdmin.getTxtHarga().setText(""+pakan.getHarga());
                viewAdmin.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormPakan();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickBtnCariPakan() throws ParseException {
        try {
            String kode = viewAdmin.getTxtIdPakan().getText();
            pakan = PakanDao.getPakan(con, kode);
            if (pakan != null) {
                viewAdmin.getTxtIdPakan().setText(""+pakan.getIdPakan());
                viewAdmin.getTxtNamaPakan().setText(pakan.getNama());
                viewAdmin.getTxtHarga().setText(""+pakan.getHarga());
                viewAdmin.getTxtStok().setText(""+pakan.getStok());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormPakan();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4) + " Kg"
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4)+ " Kg"
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
            JOptionPane.showMessageDialog(viewAdmin, "Nama Penyakit Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE); 
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
            JOptionPane.showMessageDialog(viewAdmin, "Data Penyakit = "+viewAdmin.getTxtNamaPenyakit().getText()+" Terikat dengan Data Kesehatan");
        }
    }
    
    public void onClickTabelPenyakit() {
        try {
            String kode = viewAdmin.getTblInputDataPenyakit().getValueAt(viewAdmin.getTblInputDataPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewAdmin.getTxtNamaPenyakit().setText(penyakit.getNamaPenyakit());
                viewAdmin.getJtxGejala().setText(penyakit.getGejala());
                viewAdmin.getJtxPenularan().setText(penyakit.getPenularan());
                viewAdmin.getJtxPencegahan().setText(penyakit.getPencegahan());
                viewAdmin.getJtxPengobatan().setText(penyakit.getPengobatan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormPenyakit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickTabelDataPenyakit() {
        try {
            String kode = viewAdmin.getTblDataPenyakit().getValueAt(viewAdmin.getTblDataPenyakit().getSelectedRow(), 0).toString();
            penyakit = PenyakitDao.getPenyakit(con, kode);
            if (penyakit != null) {
                viewAdmin.getJtxGejalaData().setText(penyakit.getGejala());
                viewAdmin.getJtxPenularanData().setText(penyakit.getPenularan());
                viewAdmin.getJtxPencegahanData().setText(penyakit.getPencegahan());
                viewAdmin.getJtxPengobatanData().setText(penyakit.getPengobatan());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormPenyakit();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        viewAdmin.getTxtNamaKandang().setText("");
        viewAdmin.getTxtJmlSakit().setText("");
        isiCboIdPegawai();
        isiCboKandang();
        isiCboPenyakit();
        viewAdmin.getTxtJmlMati().setText("");
    }
    
    public void getAutoIncrementIdKesehatan() {
        viewAdmin.getTxtIdKesehatan().setText("3001");
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            String cek_id = viewAdmin.getTxtIdKesehatan().getText();
            if (cek_id != null) {
                while (rs.next()) {             
                    int id = rs.getInt(1)+1;
                    viewAdmin.getTxtIdKesehatan().setText(""+id);
                }
            } else {
                viewAdmin.getTxtIdKesehatan().setText("3001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void insertKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewAdmin.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewAdmin.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewAdmin.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewAdmin.getCboIdPegawai().getSelectedItem().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewAdmin.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
        kesehatan.setTglCek(viewAdmin.getTxtTglCekKesehatan().getText());
        try {
            kandang = kandangdao.getKandang(con,viewAdmin.getCboKandang().getSelectedItem().toString());
            int mati = Integer.parseInt(viewAdmin.getTxtJmlMati().getText());
            int ternak = kandang.getJmlTernak();
            Kesehatan kesehatan_id = new Kesehatan();
            String id_kes = viewAdmin.getTxtIdKesehatan().getText();
            kesehatan_id = KesehatanDao.getKesehatan(con, id_kes);
            if (kesehatan_id != null) {
                JOptionPane.showMessageDialog(viewAdmin, "ID Kesehatan Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
            } else {
                if (mati > ternak) {
                    JOptionPane.showMessageDialog(viewAdmin, "Jumlah Mati Sudah Melebihi Jumlah Ternak");
                } else {
                    KesehatanDao.insert(con, kesehatan);
                    kandangdao.update(con, kandang,Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
                    //kandangdao.update(con, kandang);
                    JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Simpan");
                    DateNow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah Ada Hari Ini"); 
//            JOptionPane.showMessageDialog(viewAdmin, "Error " +ex); 
        }
    }
    
    
    public void updateKesehatan(){
        kesehatan = new Kesehatan();
        kesehatan.setIdKesehatan(viewAdmin.getTxtIdKesehatan().getText());
        kesehatan.setNamaKandang(viewAdmin.getCboKandang().getSelectedItem().toString());
        kesehatan.setNamaPenyakit(viewAdmin.getCboPenyakit().getSelectedItem().toString());
        kesehatan.setIdPegawai(viewAdmin.getCboIdPegawai().getSelectedItem().toString().toString().split("-")[0]);
        kesehatan.setJmlSakit(Integer.parseInt(viewAdmin.getTxtJmlSakit().getText()));
        kesehatan.setJmlMati(Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
        kesehatan.setTglCek(viewAdmin.getTxtTglCekKesehatan().getText());
        try {
            kandang = kandangdao.getKandang(con,viewAdmin.getCboKandang().getSelectedItem().toString());
            Kesehatan k1 = new Kesehatan();
            k1 = kesehatandao.getKesehatan(con,viewAdmin.getTxtIdKesehatan().getText());
            int x = k1.getJmlMati();
            kandangdao.update(con, kandang,Integer.parseInt(viewAdmin.getTxtJmlMati().getText()),x);
            KesehatanDao.update(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Perbaharui");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteKesehatan() {
        try {
            kandang = kandangdao.getKandang(con,viewAdmin.getCboKandang().getSelectedItem().toString());
            Kesehatan k1 = new Kesehatan();
            k1 = kesehatandao.getKesehatan(con,viewAdmin.getCboKandang().getSelectedItem().toString());
            kandangdao.updateDelete(con, kandang,Integer.parseInt(viewAdmin.getTxtJmlMati().getText()));
            KesehatanDao.delete(con, kesehatan);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
            DateNow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
            DateNow();
        }
    }
    
    public void onClickTabelKesehatan() {
        try {
            String kode = viewAdmin.getTblInputDataKesehatan().getValueAt(viewAdmin.getTblInputDataKesehatan().getSelectedRow(), 0).toString();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            System.out.println("1");
            if (kesehatan != null) {
                System.out.println("2");
                viewAdmin.getTxtIdKesehatan().setText(""+kesehatan.getIdKesehatan());
                viewAdmin.getCboKandang().setSelectedItem(kesehatan.getNamaKandang());
                viewAdmin.getCboPenyakit().setSelectedItem(kesehatan.getNamaPenyakit());
                viewAdmin.getCboIdPegawai().setSelectedItem(kesehatan.getIdPegawai());
                viewAdmin.getTxtJmlSakit().setText(""+kesehatan.getJmlSakit());
                viewAdmin.getTxtJmlMati().setText(""+kesehatan.getJmlMati());
                viewAdmin.getTxtTglCekKesehatan().setText(kesehatan.getTglCek());
            } else {
                System.out.println("3");
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormKesehatan();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickBtnCariKesehatan() {
        try {
            String kode = viewAdmin.getTxtIdKesehatan().getText();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            if (kesehatan != null) {
                viewAdmin.getTxtIdKesehatan().setText(""+kesehatan.getIdKesehatan());
                viewAdmin.getCboKandang().setSelectedItem(kesehatan.getNamaKandang());
                viewAdmin.getCboPenyakit().setSelectedItem(kesehatan.getNamaPenyakit());
                viewAdmin.getCboIdPegawai().setSelectedItem(kesehatan.getIdPegawai());
                viewAdmin.getTxtJmlSakit().setText(""+kesehatan.getJmlSakit());
                viewAdmin.getTxtJmlMati().setText(""+kesehatan.getJmlMati());
                viewAdmin.getTxtTglCekKesehatan().setText(kesehatan.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormKesehatan();
                DateNow();
            }
        } catch (Exception ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek,id_kesehatan desc"
            );
            while(rs.next()){
                Object[] data={
                    rs.getInt(1),
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataKesehatan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek,id_kesehatan desc"
            );
            while(rs.next()){
                Object[] data={
                    rs.getInt(1),
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////        Cek Ternak          ///////////////////////////////
    
    public void clearFormCekTernak(){
        viewAdmin.getTxtJmlTelur().setText("");
        isiCboKandangCek();
        isiCboIdPakanCek();
        isiCboIdPegawaiCek();
        isiCboKebersihan();
        viewAdmin.getTxtJmlPakan().setText("");
    }
    
    public void getAutoIncrementIdCekTernak() {
        viewAdmin.getTxtIdCekTernak().setText("4001");
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
            String cek_id = viewAdmin.getTxtIdCekTernak().getText();
            if (cek_id != null) {
                while (rs.next()) {             
                    int id = rs.getInt(1)+1;
                    viewAdmin.getTxtIdCekTernak().setText(""+id);
                }
            } else {
                viewAdmin.getTxtIdCekTernak().setText("3001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        CekTernak.setJmlPakan(Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
        CekTernak.setIdPegawai(viewAdmin.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewAdmin.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewAdmin.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewAdmin.getTxtTglCek().getText());
        try {
            Pakan pakan;
            pakan = new Pakan();
            pakan = PakanDao.getPakan(con, viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            int jml_stok = Integer.parseInt(viewAdmin.getTxtJmlPakan().getText());
            int jml_pakan = pakan.getStok();
            CekTernak CekTernak_id = new CekTernak();
            String id_cek = viewAdmin.getTxtIdCekTernak().getText();
            CekTernak_id = CekTernakDao.getCekTernak(con, id_cek);
            if (CekTernak_id != null) {
                JOptionPane.showMessageDialog(viewAdmin, "ID Cek Ternak Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
            } else {
                if (jml_stok > jml_pakan) {
                    JOptionPane.showMessageDialog(viewAdmin, "Jumlah Pemakaian Sudah Melebihi Jumlah Stok");
                } else {
                    CekTernakDao.insert(con, CekTernak);
                    PakanDao.update(con, pakan, Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
                    JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Simpan");
                    DateNow();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah Ada Hari ini !"); 
            DateNow();
        }
    }
    
    public void updateCekTernak(){
        CekTernak = new CekTernak();
        CekTernak.setIdCek(viewAdmin.getTxtIdCekTernak().getText());
        CekTernak.setNamaKandang(viewAdmin.getCboKandangCek().getSelectedItem().toString());
        CekTernak.setIdPakan(viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlPakan(Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
        CekTernak.setIdPegawai(viewAdmin.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
        CekTernak.setJmlTelur(Integer.parseInt(viewAdmin.getTxtJmlTelur().getText()));
        CekTernak.setKebersihan(viewAdmin.getCboKebersihan().getSelectedItem().toString());
        CekTernak.setTglCek(viewAdmin.getTxtTglCek().getText());
        try {
            pakan = PakanDao.getPakan(con, viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            CekTernak cekTernak = new CekTernak();
            cekTernak = CekTernakDao.getCekTernak(con, viewAdmin.getTxtIdCekTernak().getText());
            int y = cekTernak.getJmlPakan();
            pakanDao.update(con, pakan, Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()), y);
            CekTernakDao.update(con, CekTernak);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Perbaharui");
            DateNow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
            DateNow();
        }
    }
    
    public void deleteCekTernak() {
        try {
            pakan = PakanDao.getPakan(con, viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
            CekTernak cekTernak = new CekTernak();
            cekTernak = CekTernakDao.getCekTernak(con, viewAdmin.getTxtIdCekTernak().getText());
            pakanDao.updateDelete(con, pakan, Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
            CekTernakDao.delete(con, CekTernak);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
            DateNow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelCekTernak() {
        try {
            String kode = viewAdmin.getTblInputDataCekTernak().getValueAt(viewAdmin.getTblInputDataCekTernak().getSelectedRow(), 0).toString();
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewAdmin.getTxtIdCekTernak().setText(CekTernak.getIdCek());
                viewAdmin.getCboKandangCek().setSelectedItem(CekTernak.getNamaKandang());
                viewAdmin.getCboIdPakanCek().setSelectedItem(CekTernak.getIdPakan());
                viewAdmin.getTxtJmlPakan().setText(""+CekTernak.getJmlPakan());
                viewAdmin.getCboIdPegawaiCek().setSelectedItem(CekTernak.getIdPegawai());
                viewAdmin.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewAdmin.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewAdmin.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormCekTernak();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onClickBtnCariCekTernak() throws ParseException {
        try {
            String kode = viewAdmin.getTxtIdCekTernak().getText();
            kesehatan = KesehatanDao.getKesehatan(con, kode);
            CekTernak = CekTernakDao.getCekTernak(con, kode);
            if (CekTernak != null) {
                viewAdmin.getTxtIdCekTernak().setText(CekTernak.getIdCek());
                viewAdmin.getCboKandangCek().setSelectedItem(CekTernak.getNamaKandang());
                viewAdmin.getCboIdPakanCek().setSelectedItem(CekTernak.getIdPakan());
                viewAdmin.getTxtJmlPakan().setText(""+CekTernak.getJmlPakan());
                viewAdmin.getCboIdPegawaiCek().setSelectedItem(CekTernak.getIdPegawai()); 
                viewAdmin.getTxtJmlTelur().setText(""+CekTernak.getJmlTelur());
                viewAdmin.getCboKebersihan().setSelectedItem(CekTernak.getKebersihan());
                viewAdmin.getTxtTglCek().setText(CekTernak.getTglCek());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormCekTernak();
                DateNow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewTableDataCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblDataCekTernak().getModel();
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
                    + "order by tgl_cek,id_cek desc"
            );
            while (rs.next()) { 
                Object [] data = {
                    rs.getInt(1),
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableInputCekTernak() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblInputDataCekTernak().getModel();
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
                    + "order by tgl_cek,id_cek desc"
            );
            while (rs.next()) { 
                Object [] data = {
                    rs.getInt(1),
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void clearFormUser() {
        viewAdmin.getTxtDataUsername().setText("");
        viewAdmin.getJpsDataPassword().setText("");
        viewAdmin.getJpsReDataPassword().setText("");
        viewAdmin.getTxtDataNama().setText("");
    }
    
    public void getAutoIncrementIdUser() {
        viewAdmin.getTxtIdUser().setText("1");
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from user");
            String cek_id = viewAdmin.getTxtIdUser().getText();
            if (cek_id != null) {
                while (rs.next()) {             
                    int id = rs.getInt(1)+1;
                    viewAdmin.getTxtIdUser().setText(""+id);
                }
            } else {
                viewAdmin.getTxtIdUser().setText("1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiCboAkses() {
        viewAdmin.getCboAkses().removeAllItems();
        viewAdmin.getCboAkses().addItem("Administrator");
        viewAdmin.getCboAkses().addItem("Pegawai");
    }
    
    public String getPasswordCheck() {
        if (viewAdmin.getJpsDataPassword().getText().equals("")) {
            return "0";
        } else if (viewAdmin.getJpsDataPassword().getText().length() < 6) {
            return "1";
        } else if (!viewAdmin.getJpsDataPassword().getText().equals(viewAdmin.getJpsReDataPassword().getText())) {
            return "2";
        } else {
            return viewAdmin.getJpsDataPassword().getText();
        }
    }
    
    public void insertUser() {
        user.setIdUser(Integer.parseInt(viewAdmin.getTxtIdUser().getText()));
        user.setUsername(viewAdmin.getTxtDataUsername().getText());
        user.setPassword(viewAdmin.getJpsDataPassword().getText());
        user.setNamaAkun(viewAdmin.getTxtDataNama().getText());
        user.setAkses(viewAdmin.getCboAkses().getSelectedItem().toString());
        if (getPasswordCheck()== "0") {
            JOptionPane.showMessageDialog(viewAdmin, "Silakan Isi Password");
        } else if (getPasswordCheck() == "1") {
            JOptionPane.showMessageDialog(viewAdmin, "Password Minimal 6 Karakter");
        } else if (getPasswordCheck() == "2") {
            JOptionPane.showMessageDialog(viewAdmin, "Password Tidak Sama");
        } else {
            try {
                User userId = new User();
                User username = new User();
                int id_user = Integer.parseInt(viewAdmin.getTxtIdUser().getText());
                String user_username = viewAdmin.getTxtDataUsername().getText();
                userId = UserDao.getUser(con, id_user);
                username = UserDao.getUsername(con, user_username);
                if (userId != null) {
                    JOptionPane.showMessageDialog(viewAdmin, "ID Username Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
                } else{
                    if (username != null) {
                        JOptionPane.showMessageDialog(viewAdmin, "Username Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
                    } else {
                        UserDao.insert(con, user);
                        JOptionPane.showMessageDialog(viewAdmin, "Akun Sudah Di inputkan");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(viewAdmin, "Error "+ex.getMessage()); 
            }
        }
    }
    
    public void deleteUser() {
        try {
            UserDao.delete(con, user);
            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Hapus");
            DateNow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAdmin, "Error"+e.getMessage());
        }
    }
    
    public void onClickTabelDataUser() {
        try {
            int kode = Integer.parseInt(viewAdmin.getTblDataUser().getValueAt(viewAdmin.getTblDataUser().getSelectedRow(), 0).toString());
            user = UserDao.getUser(con, kode);
            if (user != null) {
                viewAdmin.getTxtIdUser().setText(""+user.getIdUser());
                viewAdmin.getTxtDataUsername().setText(user.getUsername());
                viewAdmin.getTxtDataNama().setText(user.getNamaAkun());
                viewAdmin.getCboAkses().setSelectedItem(user.getAkses());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormCekTernak();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableDataUser() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblDataUser().getModel();
            tableModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from user");
            while (rs.next()) { 
                Object [] data = {
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
    
//    public void insertCekTernak(){
//        CekTernak = new CekTernak();
//        CekTernak.setIdCek(viewAdmin.getTxtIdCekTernak().getText());
//        CekTernak.setNamaKandang(viewAdmin.getCboKandangCek().getSelectedItem().toString());
//        CekTernak.setIdPakan(viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
//        CekTernak.setJmlPakan(Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
//        CekTernak.setIdPegawai(viewAdmin.getCboIdPegawaiCek().getSelectedItem().toString().split("-")[0]);
//        CekTernak.setJmlTelur(Integer.parseInt(viewAdmin.getTxtJmlTelur().getText()));
//        CekTernak.setKebersihan(viewAdmin.getCboKebersihan().getSelectedItem().toString());
//        CekTernak.setTglCek(viewAdmin.getTxtTglCek().getText());
//        try {
//            Pakan pakan;
//            pakan = new Pakan();
//            pakan = PakanDao.getPakan(con, viewAdmin.getCboIdPakanCek().getSelectedItem().toString().split("-")[0]);
//            int jml_stok = Integer.parseInt(viewAdmin.getTxtJmlPakan().getText());
//            int jml_pakan = pakan.getStok();
//            CekTernak CekTernak_id = new CekTernak();
//            String id_cek = viewAdmin.getTxtIdCekTernak().getText();
//            CekTernak_id = CekTernakDao.getCekTernak(con, id_cek);
//            if (CekTernak_id != null) {
//                JOptionPane.showMessageDialog(viewAdmin, "ID Cek Ternak Sudah Ada","Pesan",JOptionPane.WARNING_MESSAGE);
//            } else {
//                if (jml_stok > jml_pakan) {
//                    JOptionPane.showMessageDialog(viewAdmin, "Jumlah Pemakaian Sudah Melebihi Jumlah Stok");
//                } else {
//                    CekTernakDao.insert(con, CekTernak);
//                    PakanDao.update(con, pakan, Integer.parseInt(viewAdmin.getTxtJmlPakan().getText()));
//                    JOptionPane.showMessageDialog(viewAdmin, "Data Sudah di Simpan");
//                    DateNow();
//                }
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(viewAdmin, "Data Sudah Ada Hari ini !"); 
//            DateNow();
//        }
//    }
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
            ResultSet rs = con.createStatement().executeQuery(""
                    + "select id_kesehatan, nama_kandang, nama_penyakit, nama, jml_sakit, jml_mati, date_format(tgl_cek, '%d %M %Y') "
                    + "from kesehatan "
                    + "join pegawai "
                    + "using (id_pegawai) "
                    + "order by tgl_cek,id_kesehatan desc"
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableCekTernakLaporan(){
        try {
            DefaultTableModel tableModel = (DefaultTableModel) viewAdmin.getTblLaporanCekTernak().getModel();
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
                    + "order by tgl_cek,id_cek desc"
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
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTablePegawaiLaporan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblLaporanPegawai().getModel();
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
                    "Rp."+rs.getDouble(3),
                    rs.getInt(4)+" Kg"
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
