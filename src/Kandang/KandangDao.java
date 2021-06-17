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
        String sql = "insert into kandang values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kandang.getNamaKandang());
        ps.setInt(2, kandang.getJmlTernak());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, Kandang kandang) throws SQLException {
        String sql = "update kandang set jml_ternak=? "+" where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, kandang.getJmlTernak());
        ps.setString(2, kandang.getNamaKandang());
        ps.executeUpdate();
        System.out.print("Done Juga");
    }
    
    public static void update(Connection con, Kandang kandang, int mati) throws SQLException {
        String sql = "update kandang set jml_ternak=? where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, kandang.getJmlTernak()-mati);
        ps.setString(2, kandang.getNamaKandang());
        ps.executeUpdate();
        System.out.print("Done");
    }
    
    public static void update(Connection con, Kandang kandang, int mati, int x) throws SQLException {
        String sql = "update kandang set jml_ternak=? where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, kandang.getJmlTernak()-mati+x);
        ps.setString(2, kandang.getNamaKandang());
        ps.executeUpdate();
        System.out.print("Done");
    }
    
    public static void updateDelete(Connection con, Kandang kandang, int mati) throws SQLException {
        String sql = "update kandang set jml_ternak=? where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, kandang.getJmlTernak()+mati);
        ps.setString(2, kandang.getNamaKandang());
        ps.executeUpdate();
        System.out.print("Done");
    }
    
    public static void delete(Connection con, Kandang kandang) throws SQLException{
        String sql = "delete from kandang where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kandang.getNamaKandang());
        ps.executeUpdate();
    }
    
    public static Kandang getKandang(Connection con, String nama_kandang) throws SQLException {
        String sql = "Select * from kandang where nama_kandang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nama_kandang);
        Kandang kandang = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            kandang = new Kandang();
            kandang.setNamaKandang(rs.getString(1));
            kandang.setJmlTernak(rs.getInt(2));
        }
        return kandang;
    }
}
