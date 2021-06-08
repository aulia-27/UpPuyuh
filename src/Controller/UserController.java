/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormLogin;
import FormUpPuyuh.FormMainMenu;
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
            isiCboAkses();
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
    
    public void isiCboAkses() {
        viewLogin.getCboAkses().removeAllItems();
        viewLogin.getCboAkses().addItem("admin");
        viewLogin.getCboAkses().addItem("petugas");
        
    }
    
    public boolean ClickBtnLogin() {
        try {
            ResultSet rs = con.createStatement().executeQuery("select akses from user");
            if (true) {
                String username = viewLogin.getTxtUsername().getText();
                user = UserDao.getUser(con, username);
                String password = viewLogin.getTxtPassword().getText();
                user = UserDao.getUser(con, password);
                if (viewLogin.getCboAkses().getSelectedItem().equals("admin")) {
                    JOptionPane.showMessageDialog(viewLogin, "Login Berhasil");
                    FormMainMenu formMainMenu = new FormMainMenu();
                    formMainMenu.setVisible(true);
                    formMainMenu.toFront();
                    viewLogin.dispose();
                } else if (viewLogin.getCboAkses().getSelectedItem().equals("petugas")) {
                    javax.swing.JOptionPane.showMessageDialog(viewLogin, "Login Berhasil");
                    FormMainMenu formMainMenu = new FormMainMenu();
                    formMainMenu.setVisible(true);
                    formMainMenu.toFront();
                    viewLogin.dispose();
                } else if (username == "" || password == "") {
                    JOptionPane.showMessageDialog(viewLogin, "Username atau Password Kosong !");
                    FormLogin formLogin = new FormLogin();
                    formLogin.setVisible(true);
                    formLogin.toFront();
                } else {
                    JOptionPane.showMessageDialog(viewLogin, "Username atau Password Salah !");
                    FormLogin formLogin = new FormLogin();
                    formLogin.setVisible(true);
                    formLogin.toFront();
                }
            } else {
            }
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
