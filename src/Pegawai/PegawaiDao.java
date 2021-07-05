/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pegawai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PegawaiDao {
    public static void insert(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "insert into pegawai values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getIdPegawai());
        ps.setString(2, pengawai.getNama());
        ps.setString(3, pengawai.getAsal());
        ps.setString(4, pengawai.getTglLahir());
        ps.setString(5, pengawai.getJekel());
        ps.setString(6, pengawai.getNoTelp());
        ps.setString(7, pengawai.getAlamat());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "update pegawai set nama=?, asal=?, tgl_lahir=?, jekel=?, no_telp=?, alamat=? " + "where id_pegawai=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getNama());
        ps.setString(2, pengawai.getAsal());
        ps.setString(3, pengawai.getTglLahir());
        ps.setString(4, pengawai.getJekel());
        ps.setString(5, pengawai.getNoTelp());
        ps.setString(6, pengawai.getAlamat());
        ps.setString(7, pengawai.getIdPegawai());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Pegawai pengawai) throws SQLException{
        String sql = "delete from pegawai where id_pegawai=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengawai.getIdPegawai());
        ps.executeUpdate();
    }
    
    public static Pegawai getPegawai(Connection con, String id_Pegawai) throws SQLException {
        String sql = "select * from pegawai where id_pegawai=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_Pegawai);
        Pegawai pegawai = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            pegawai = new Pegawai();
            pegawai.setIdPegawai(rs.getString(1));
            pegawai.setNama(rs.getString(2));
            pegawai.setAsal(rs.getString(3));
            pegawai.setTglLahir(rs.getString(4));
            pegawai.setJekel(rs.getString(5));
            pegawai.setNoTelp(rs.getString(6));
            pegawai.setAlamat(rs.getString(7));
        }
        return pegawai;
    }
}