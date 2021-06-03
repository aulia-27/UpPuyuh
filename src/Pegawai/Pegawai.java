/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pegawai;


public class Pegawai {
    private String id_Pegawai;
    private String nama;
    private String tgl_lahir;
    private String noTelp;
    private String alamat;
    private String id_Kandang;
    
    public String getIdPegawai() {
        return id_Pegawai;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getTglLahir() {
        return tgl_lahir;
    }

    public String getNoTelp() {
        return noTelp;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public String getIdKandang() {
        return id_Kandang;
    }
    
    public void setIdPegawai(String id_Pegawai) {
        this.id_Pegawai = id_Pegawai;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setTglLahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setIdKandang(String id_Kandang) {
        this.id_Kandang = id_Kandang;
    }
}
