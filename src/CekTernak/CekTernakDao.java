/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CekTernak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CekTernakDao {
    public static void insert(Connection con, CekTernak cekKandang ) throws SQLException{
        String sql = "insert into cek_ternak values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getIdCek());
        ps.setString(2, cekKandang.getNamaKandang());
        ps.setString(3, cekKandang.getIdPakan());
        ps.setInt(4, cekKandang.getJmlPakan());
        ps.setString(5, cekKandang.getIdPegawai());
        ps.setInt(6, cekKandang.getJmlTelur());
        ps.setString(7, cekKandang.getKebersihan());
        ps.setString(8, cekKandang.getTglCek());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, CekTernak cekKandang) throws SQLException{
        String sql = "update cek_ternak set nama_kandang=?, id_pakan=?, jml_pakan=?, id_pegawai=?, jml_telur=?, kebersihan=?, tgl_cek=? " + "where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getNamaKandang());
        ps.setString(2, cekKandang.getIdPakan());
        ps.setInt(3, cekKandang.getJmlPakan());
        ps.setString(4, cekKandang.getIdPegawai());
        ps.setInt(5, cekKandang.getJmlTelur());
        ps.setString(6, cekKandang.getKebersihan());
        ps.setString(7, cekKandang.getTglCek());
        ps.setString(8, cekKandang.getIdCek());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, CekTernak cekKandang) throws SQLException{
        String sql = "delete from cek_ternak where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getIdCek());
        ps.executeUpdate();
    }
    
    public static CekTernak getCekTernak(Connection con, String id_cek) throws SQLException {
        String sql = "select * from cek_ternak where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_cek);
        CekTernak cekTernak = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            cekTernak = new CekTernak();
            cekTernak.setIdCek(rs.getString(1));
            cekTernak.setNamaKandang(rs.getString(2));
            cekTernak.setIdPakan(rs.getString(3));
            cekTernak.setJmlPakan(rs.getInt(4));
            cekTernak.setIdPegawai(rs.getString(5));
            cekTernak.setJmlTelur(rs.getInt(6));
            cekTernak.setKebersihan(rs.getString(7));
            cekTernak.setTglCek(rs.getString(8));
        }
        return cekTernak;
    }
    
    public static CekTernak getCekTernak(Connection con, int jml_telur) throws SQLException {
        String sql = "select * from cek_ternak where jml_telur=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, jml_telur);
        CekTernak cekTernak = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            cekTernak = new CekTernak();
            cekTernak.setIdCek(rs.getString(1));
            cekTernak.setNamaKandang(rs.getString(2));
            cekTernak.setIdPakan(rs.getString(3));
            cekTernak.setJmlPakan(rs.getInt(4));
            cekTernak.setIdPegawai(rs.getString(5));
            cekTernak.setJmlTelur(rs.getInt(6));
            cekTernak.setKebersihan(rs.getString(7));
            cekTernak.setTglCek(rs.getString(8));
        }
        return cekTernak;
    }
}
