/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FormUpPuyuh.FormMainMenuAdmin;

import User.User;
import User.UserDao;

import Koneksi.Koneksi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aulia
 */
public class MainMenuController {
    FormMainMenuAdmin view;

    User user;
    Connection con;
    
    public MainMenuController(FormMainMenuAdmin view) {
        try {
            this.view = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            statusAdmin();
            //setUser();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////        User            ////////////////////////////
    
     public void cleartextUser() {
        view.getTxtUsername().setText("");
        view.getTxtNamaUser().setText("");
     }
     
     public void statusAdmin() {
         view.getJStatus().setText("Admin");
     }
     
     public void statusPegawai() {
         view.getJStatus().setText("Pegawai");
     }
     
     public void setUser() {
         view.getTxtUsername().setText(user.getUsername());
         view.getTxtNamaUser().setText(user.getNamaAkun());
         view.getTxtStatus().setText(user.getAkses());
     }
    
    ////////////////////////        Laporan         ////////////////////////////
    

    ////////////////////////        Laporan         ////////////////////////////
}
