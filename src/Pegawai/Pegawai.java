/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pegawai;

import java.util.Date;


public class Pegawai {
    private String id_Pegawai;
    private String nama;
    private String asal;
    private String tgl_lahir;
    private String noTelp;
    private String jekel;
    private String alamat;
    
    public String getIdPegawai() {
        return id_Pegawai;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getAsal() {
        return asal;
    }
    
    public String getTglLahir() {
        return tgl_lahir;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getJekel() {
        return jekel;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setIdPegawai(String id_Pegawai) {
        this.id_Pegawai = id_Pegawai;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setAsal(String asal) {
        this.asal = asal;
    }
    
    public void setTglLahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
    
    public void setJekel(String jekel) {
        this.jekel = jekel;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}