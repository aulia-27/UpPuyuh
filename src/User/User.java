/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author Aulia
 */
public class User {
    private String username;
    private String password;
    private String akses;
    private String nama_akun;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAkses() {
        return akses;
    }
    
    public void setAkses(String akses) {
        this.akses = akses;
    }
    
    public String getNamaAkun() {
        return nama_akun;
    }
    
    public void setNamaAkun(String nama_akun) {
        this.nama_akun = nama_akun;
    }
}
