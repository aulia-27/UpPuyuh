/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pegawai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PegawaiDao {
    public static void insert(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "insert into pegawai values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getIdPegawai());
        ps.setString(2, pengawai.getNama());
        ps.setString(3, pengawai.getTglLahir());
        ps.setString(4, pengawai.getNoTelp());
        ps.setString(5, pengawai.getAlamat());
        ps.setString(6, pengawai.getIdKandang());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "update pegawai set nama=?, tgllahir=?, notelp=?, alamat=?, id_kandang=?" + "where id_pegawai=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getNama());
        ps.setString(2, pengawai.getTglLahir());
        ps.setString(3, pengawai.getNoTelp());
        ps.setString(4, pengawai.getAlamat());
        ps.setString(5, pengawai.getIdKandang());
        ps.setString(6, pengawai.getIdPegawai());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "delete from pegawai where id_pegawai=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getIdPegawai());
        ps.executeUpdate();
    }
}
