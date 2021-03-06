/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PakanDao {
    public static void insert(Connection con, Pakan pakan) throws SQLException{
        String sql = "insert into pakan values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getIdPakan());
        ps.setString(2, pakan.getNama());
        ps.setDouble(3, pakan.getHarga());
        ps.setInt(4, pakan.getStok());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Pakan pakan) throws SQLException{
        String sql = "update pakan set nama=?, harga=?, stok=? where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getNama());
        ps.setDouble(2, pakan.getHarga());
        ps.setInt(3, pakan.getStok());
        ps.setString(4, pakan.getIdPakan());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Pakan pakan, int krng) throws SQLException{
        String sql = "update pakan set nama=?, harga=?, stok=? where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getNama());
        ps.setDouble(2, pakan.getHarga());
        ps.setInt(3, pakan.getStok()-krng);
        ps.setString(4, pakan.getIdPakan());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Pakan pakan, int krng, int x) throws SQLException{
        String sql = "update pakan set nama=?, harga=?, stok=? where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getNama());
        ps.setDouble(2, pakan.getHarga());
        ps.setInt(3, pakan.getStok()-krng+x);
        ps.setString(4, pakan.getIdPakan());
        ps.executeUpdate();
    }
    
    public static void updateDelete(Connection con, Pakan pakan, int krng) throws SQLException{
        String sql = "update pakan set nama=?, harga=?, stok=? where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getNama());
        ps.setDouble(2, pakan.getHarga());
        ps.setInt(3, pakan.getStok()+krng);
        ps.setString(4, pakan.getIdPakan());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Pakan pakan) throws SQLException{
        String sql = "delete from pakan where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pakan.getIdPakan());
        ps.executeUpdate();
    }
    
    public static Pakan getPakan(Connection con, String idPakan) throws SQLException {
        String sql = "select * from pakan where id_pakan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, idPakan);
        Pakan pakan = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            pakan = new Pakan();
            pakan.setIdPakan(rs.getString(1));
            pakan.setNama(rs.getString(2));
            pakan.setHarga(rs.getDouble(3));
            pakan.setStok(rs.getInt(4));
        }
        return pakan;
    }
}