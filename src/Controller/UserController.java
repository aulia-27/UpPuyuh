/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import User.User;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import FormUpPuyuh.FormMenuAdmin;
/**
 *
 * @author Aulia
 */
public class UserController {
    FormMenuAdmin viewAdmin;
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public UserController() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/uppuyuh", "root", "");
            st=con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database gagal, Terjadi kesalahan pada : \n " +e);
        }
    }
    
    public List cariLogin(String username, String password) {
        List logLogin = new ArrayList();
        int result;
        sql = "select username, password, nama, akses from user where username='" +username+ "' and password='" +password+"'";
        try {
            rs=st.executeQuery(sql);
            while (rs.next()) {                
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNamaAkun(rs.getString("nama"));
                user.setAkses(rs.getString("akses"));
                logLogin.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ternjadi kesalahan login, pada:n" +e);
        }
        return logLogin;
    }
    
    public List cariId(int id_user) {
        List logLogin = new ArrayList();
        int result;
        sql =  "select id_user from user where id_user ='" +id_user+ "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                User user = new User(); 
                user.setIdUser(rs.getInt("id_user"));
                logLogin.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pencarian data id, pada" +e);
        }
        return logLogin;
    } 
    
    public List getIdAndUsername(int id_user, String username) {
        List logLogin = new ArrayList();
        int result;
        sql =  "select id_user from user where id_user ='" +id_user+ "' and username ='"+username+"'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                User user = new User(); 
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                logLogin.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pencarian data id, pada" +e);
        }
        return logLogin;
    } 
    
    public List username(String username) {
        List logLogin = new ArrayList();
        int result;
        sql =  "select username from user where username ='" +username+ "'";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                User user = new User(); 
                user.setUsername(rs.getString("username"));
                logLogin.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pencarian data id, pada" +e);
        }
        return logLogin;
    } 
    
    public int tambah(User user) {
        sql = "insert into user  values ('"+user.getIdUser()+"','"+user.getUsername()+"','" +user.getPassword()+ "','" +user.getNamaAkun()+ "','"+user.getAkses()+ "')";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public int update(User user) {
        sql = "update user set ('"+user.getPassword()+"') where username='"+user.getUsername()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public int updatePassword(User user, String psw) {
        sql = "update user set password = '" +psw+ "' where username='"+user.getUsername()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public int delete(User user) {
        sql = "delete from user where id_user='"+user.getIdUser()+"'";
        int hasil = 0;
        try {
            hasil = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return hasil;
    }
    
    public List Tampiil () {
        List logMainMenu = new ArrayList();
        sql = "select id_user, username, password, nama, akses from user order by id_user asc";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                User user = new User(); 
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNamaAkun(rs.getString("nama"));
                user.setAkses(rs.getString("akses"));
                logMainMenu.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Tampil, pada : "+e);
        }
        return logMainMenu;
    }
    
    public User getUser(String User){
        User user = new User(); 
        sql = "select * from user where username='" +User+ "'";
        try{
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNamaAkun(rs.getString("nama"));
                user.setAkses(rs.getString("akses"));
            }
            return user;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Tampil, pada : "+e);
        }
        return user;
    }
    
    public static User getUser(Connection con, String username) throws SQLException {
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
    
    public int getNotif2(String tgl){
        try {
            
            String sql = "select notif2 from notif where tgl=?";
            java.sql.Date date=java.sql.Date.valueOf(tgl);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x = rs.getInt(1);
            }
            return x;
        } catch (SQLException e) {
            
        }
        return 2;
    }
    
    public void createDate(){
        try {
            String sql = "INSERT INTO notif VALUES (NOW(),0,0,0)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);
        } catch (Exception e){
            System.out.print(e);
        }
    }
}

