/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormLogin;
import FormUpPuyuh.FormMainMenuAdmin;
import FormUpPuyuh.FormLogin;

import User.User;
import User.UserDao;
import Koneksi.Koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Aulia
 */
public class UserController {
    FormLogin viewLogin;
    User user;
    UserDao userDao;
    Connection con;
    
    public UserController (FormLogin viewLogin) {
        try {
            this.viewLogin = viewLogin;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getKoneksi();
            clearViewLogin();
        } catch (ClassNotFoundException ec) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ec);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearViewLogin() {
        viewLogin.getTxtUsername().setText("");
        viewLogin.getTxtPassword().setText("");
    }
    
    public boolean ClickBtnLogin() {
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from user");
            FormMainMenuAdmin formMainMenuAdmin = new FormMainMenuAdmin();

        }catch (SQLException ex) {
            Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(viewLogin, "Username dan password salah !!!");
            clearViewLogin();
        }finally {
            clearViewLogin();
        }
        return false;
    }
    
}
