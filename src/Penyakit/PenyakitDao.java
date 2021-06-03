/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Penyakit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Aulia
 */
public class PenyakitDao {
    public static void insert(Connection con, Penyakit penyakit) throws SQLException {
        String sql = "insert into penyakit values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penyakit.getIdPenyakit());
        ps.setString(2, penyakit.getNama());
        ps.setString(3, penyakit.getKeterangan());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Penyakit penyakit) throws SQLException {
        String sql = "update penyakit set nama=?, keterangan=? " + "where id_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penyakit.getNama());
        ps.setString(2, penyakit.getKeterangan());
        ps.setString(3, penyakit.getIdPenyakit());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Penyakit penyakit) throws SQLException{
        String sql = "delete from penyakit where id_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penyakit.getIdPenyakit());
        ps.executeUpdate();
    }
    
    public static Penyakit getPenyakit(Connection con, String id_penyakit) throws SQLException {
        String sql = "select * from penyakit where id_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_penyakit);
        Penyakit penyakit = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            penyakit = new Penyakit();
            penyakit.setIdPenyakit(rs.getString(1));
            penyakit.setNama(rs.getString(2));
            penyakit.setKeterangan(rs.getString(3));
        }
        return penyakit;
    }
}
