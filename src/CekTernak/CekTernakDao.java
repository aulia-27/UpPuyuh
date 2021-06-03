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
        String sql = "insert into cek_kandang values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getIdCek());
        ps.setString(2, cekKandang.getIdKandang());
        ps.setString(3, cekKandang.getIdPakan());
        ps.setInt(4, cekKandang.getJmlTelur());
        ps.setString(5, cekKandang.getKebersihan());
        ps.setString(6, cekKandang.getTglCek());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, CekTernak cekKandang) throws SQLException{
        String sql = "update cek_kandang set id_kandang=?, id_pakan=?, jml_telur=?, kebersihan=?, tgl_cek=? " + "where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getIdKandang());
        ps.setString(2, cekKandang.getIdPakan());
        ps.setInt(3, cekKandang.getJmlTelur());
        ps.setString(4, cekKandang.getKebersihan());
        ps.setString(5, cekKandang.getTglCek());
        ps.setString(6, cekKandang.getIdCek());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, CekTernak cekKandang) throws SQLException{
        String sql = "delete from cek_kandang where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cekKandang.getIdCek());
        ps.executeUpdate();
    }
    
    public static CekTernak getCekTernak(Connection con, String id_cek) throws SQLException {
        String sql = "select * from kandang where id_cek=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_cek);
        CekTernak cekTernak = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            cekTernak = new CekTernak();
            cekTernak.setIdCek(rs.getString(1));
            cekTernak.setIdKandang(rs.getString(2));
            cekTernak.setIdPakan(rs.getString(3));
            cekTernak.setJmlTelur(rs.getInt(4));
            cekTernak.setKebersihan(rs.getString(5));
            cekTernak.setTglCek(rs.getString(6));
        }
        return cekTernak;
    }
    
}
