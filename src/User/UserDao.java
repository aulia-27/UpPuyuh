/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Aulia
 */
public class UserDao {
    public static void insert(Connection con, User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, user.getIdUser());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getNamaAkun());
        ps.setString(5, user.getAkses());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, User user) throws SQLException {
        String sql = "update user set username=?, password=?, nama=?, akses=? " + "where id_user=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getNamaAkun());
        ps.setString(4, user.getAkses());
        ps.setInt(5, user.getIdUser());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, User user) throws SQLException{
        String sql = "delete from user where id_user=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, user.getIdUser());
        ps.executeUpdate();
    } 
    
    public static User getUser(Connection con, int id_user) throws SQLException {
        String sql = "select * from user where id_user=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id_user);
        User user = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            user = new User();
            user.setIdUser(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNamaAkun(rs.getString(4));
            user.setAkses(rs.getString(5));
        }
        return user;
    }
    
    public static User getUsername(Connection con, String username) throws SQLException {
        String sql = "select * from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        User user = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            user = new User();
            user.setIdUser(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNamaAkun(rs.getString(4));
            user.setAkses(rs.getString(5));
        }
        return user;
    }

}
