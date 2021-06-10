/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMainMenuAdmin;
import FormUpPuyuh.FormMainMenuPetugas;

import User.User;
import User.UserDao;

import Koneksi.Koneksi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Aulia
 */
public class MainMenuController {
    FormMainMenuAdmin view;
    FormMainMenuPetugas menuPetugas;
    User user;
    Connection con;
    
    public MainMenuController(FormMainMenuAdmin view) {
        try {
            this.view = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            cleartext();
            //setUser();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MainMenuController(FormMainMenuPetugas menuPetugas) {
        try {
            this.menuPetugas = menuPetugas;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            cleartext();
            //setUser();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////        User            ////////////////////////////
    
     public void cleartextUser() {
        view.getTxtUsername().setText("");
        view.getTxtNamaUser().setText("");
     }
     
     public void setUser() {
         view.getTxtUsername().setText(user.getUsername());
         view.getTxtNamaUser().setText(user.getNamaAkun());
         view.getTxtStatus().setText(user.getAkses());
     }
    
    ////////////////////////        Laporan         ////////////////////////////
    
     public void cleartext() {
        view.getKolom1().setText("");
        view.getKolom2().setText("");
        view.getKolom3().setText("");
        view.getKolom4().setText("");
        view.getKolom5().setText("");
        view.getKolom6().setText("");
        view.getKolom7().setText("");
        view.getKolom8().setText("");
        view.getTxtKodeLaporan().setText("");
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
            row1.createCell(1).setCellValue("ID Kandang");
            row1.createCell(2).setCellValue("ID Sakit");
            row1.createCell(3).setCellValue("Jumlah Sakit");
            row1.createCell(4).setCellValue("Jumlah Mati");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_kesehatan, id_kandang, id_sakit, jml_sakit, jml_mati from kesehatan");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getInt(4));
                row2.createCell(4).setCellValue(rs.getInt(5));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(view, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(view, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(view, ioe.getMessage());
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
            row1.createCell(1).setCellValue("ID Kandang");
            row1.createCell(2).setCellValue("ID Pakan");
            row1.createCell(3).setCellValue("Jumlah Telur");
            row1.createCell(4).setCellValue("Kebersihan");
            row1.createCell(5).setCellValue("Tanggal Cek");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_cek, id_kandang, id_pakan, jml_telur, kebersihan, tgl_cek from cek_ternak");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getInt(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(view, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(view, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(view, ioe.getMessage());
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
            row1.createCell(7).setCellValue("ID Kandang");
            Row row2 ;
            ResultSet rs = statement.executeQuery("select id_pegawai, nama, asal, tgl_lahir, jekel, no_telp, alamat, id_kandang from pegawai");
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
                row2.createCell(7).setCellValue(rs.getString(8));
                }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Export Berhasil ! File Tersimpan Di Folder");
        }catch(ClassNotFoundException e){
            JOptionPane.showInternalMessageDialog(view, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(view, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(view, ioe.getMessage());
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
            JOptionPane.showInternalMessageDialog(view, e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showInternalMessageDialog(view, ex.getMessage());
        }catch(IOException ioe){
            JOptionPane.showInternalMessageDialog(view, ioe.getMessage());
        }
    }
    
    public void viewTableKesehatan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) view.getTblLaporan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kesehatan");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTableCekTernak(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) view.getTblLaporan().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from cek_ternak");
            while (rs.next()) { 
                Object [] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)
                };
            tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTablePegawai(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) view.getTblLaporan().getModel();
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
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewTablePakan(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) view.getTblLaporan().getModel();
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
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////////////////////////        Laporan         ////////////////////////////

    
}
