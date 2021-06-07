/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kandang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KandangDao {
    public static void insert(Connection con, Kandang kandang) throws SQLException {
        String sql = "insert into kandang values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kandang.getIdKandang());
        ps.setString(2, kandang.getNama());
        ps.setString(3, kandang.getBlokKandang());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Kandang kandang) throws SQLException {
        String sql = "update kandang set nama=?, blok_kandang=? " + "where id_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kandang.getNama());
        ps.setString(2, kandang.getBlokKandang());
        ps.setString(3, kandang.getIdKandang());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Kandang kandang) throws SQLException{
        String sql = "delete from kandang where id_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kandang.getIdKandang());
        ps.executeUpdate();
    }
    
    public static Kandang getKandang(Connection con, String id_kandang) throws SQLException {
        String sql = "select * from kandang where id_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id_kandang);
        Kandang kandang = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            kandang = new Kandang();
            kandang.setIdKandang(rs.getString(1));
            kandang.setNama(rs.getString(2));
            kandang.setBlokKandang(rs.getString(3));
        }
        return kandang;
    }
}
