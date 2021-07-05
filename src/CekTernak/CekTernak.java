/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CekTernak;

public class CekTernak {
    private String id_cek;
    private String nama_kandang;
    private String id_pakan;
    private int jml_pakan;
    private String id_pegawai;
    private int jml_telur;
    private String kebersihan;
    private String tgl_cek;
    
    public String getIdCek() {
        return id_cek;
    }
    
    public String getNamaKandang() {
        return nama_kandang;
    }
    
    public String getIdPakan() {
        return id_pakan;
    }
    
    public int getJmlPakan(){
        return jml_pakan;
    }
    
    public String getIdPegawai() {
        return id_pegawai;
    }
    
    public int getJmlTelur() {
        return jml_telur;
    }
    
    public String getKebersihan() {
        return kebersihan;
    }
    
    public String getTglCek() {
        return tgl_cek;
    }
    
    public void setIdCek(String id_cek) {
        this.id_cek = id_cek;
    }
    
    public void setNamaKandang(String nama_kandang) {
        this.nama_kandang = nama_kandang;
    }
    
    public void setIdPakan(String id_pakan) {
        this.id_pakan = id_pakan;
    }
    
    public void setJmlPakan(int jml_pakan) {
        this.jml_pakan = jml_pakan;
    } 
    
    public void setIdPegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }
    
    public void setJmlTelur(int jml_telur) {
        this.jml_telur = jml_telur;
    }
    
    public void setKebersihan(String kebersihan) {
        this.kebersihan = kebersihan;
    }
    
    public void setTglCek(String tgl_cek) {
        this.tgl_cek = tgl_cek;
    }
}