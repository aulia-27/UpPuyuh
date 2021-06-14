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
        String sql = "insert into penyakit values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penyakit.getNamaPenyakit());
        ps.setString(2, penyakit.getGejala());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Penyakit penyakit) throws SQLException {
        String sql = "update penyakit set keterangan=? " + "where nama_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(2, penyakit.getGejala());
        ps.setString(1, penyakit.getNamaPenyakit());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Penyakit penyakit) throws SQLException{
        String sql = "delete from penyakit where nama_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, penyakit.getNamaPenyakit());
        ps.executeUpdate();
    }
    
    public static Penyakit getPenyakit(Connection con, String id_penyakit) throws SQLException {
        String sql = "select * from penyakit where nama_penyakit=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_penyakit);
        Penyakit penyakit = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            penyakit = new Penyakit();
            penyakit.setNamaPenyakit(rs.getString(1));
            penyakit.setGejala(rs.getString(2));
        }
        return penyakit;
    }
}
