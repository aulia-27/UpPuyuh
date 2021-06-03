/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Form.FormLogin;
import User.User;
import User.UserDao;
import Koneksi.Koneksi;

import java.sql.Connection;
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
    User User;
    UserDao UserDao;
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
        viewLogin.getJpsPassword().setText("");
    }
}
