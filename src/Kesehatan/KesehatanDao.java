/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kesehatan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class KesehatanDao {
    public static void insert(Connection con, Kesehatan kesehatan ) throws SQLException{
        String sql = "insert into kesehatan values (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kesehatan.getIdKesehatan());
        ps.setString(2, kesehatan.getIdKandang());
        ps.setString(3, kesehatan.getIdSakit());
        ps.setInt(4, kesehatan.getJmlSakit());
        ps.setInt(5, kesehatan.getJmlMati());
        ps.executeUpdate();
    }
    
    public static void update(Connection con,  Kesehatan kesehatan) throws SQLException{
        String sql = "update kesehatan set id_kandang=?, id_sakit=?, jml_sakit=?, jml_mati=? " + "where id_kesehatan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kesehatan.getIdKandang());
        ps.setString(2, kesehatan.getIdSakit());
        ps.setInt(3, kesehatan.getJmlSakit());
        ps.setInt(4, kesehatan.getJmlMati());
        ps.setString(5, kesehatan.getIdKesehatan());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con,  Kesehatan kesehatan) throws SQLException{
        String sql = "delete from kesehatan where id_kesehatan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kesehatan.getIdKesehatan());
        ps.executeUpdate();
    }
    
    public static Kesehatan getKesehatan(Connection con, String id_kesehatan) throws SQLException {
        String sql = "select * from kesehatan where id_kesehatan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_kesehatan);
        Kesehatan kesehatan = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            kesehatan = new Kesehatan();
            kesehatan.setIdKesehatan(rs.getString(1));
            kesehatan.setIdKandang(rs.getString(2));
            kesehatan.setIdSakit(rs.getString(3));
            kesehatan.setJmlSakit(rs.getInt(4));
            kesehatan.setJmlMati(rs.getInt(5));
        }
        return kesehatan;
    }
}
