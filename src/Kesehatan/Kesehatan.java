/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kesehatan;


public class Kesehatan {
    private String id_kesehatan;
    private String nama_kandang;
    private String nama_penyakit;
    private String id_pegawai;
    private int jml_sakit;
    private int jml_mati;
    private String tgl_cek;
    
    public String getIdKesehatan() {
        return id_kesehatan;
    }
    
    public String getNamaKandang() {
        return nama_kandang;
    }
    
    public String getNamaPenyakit() {
        return nama_penyakit;
    }
    
    public String getIdPegawai() {
        return id_pegawai;
    }
    
    public int getJmlSakit() {
        return jml_sakit;
    }
    
    public int getJmlMati() {
        return jml_mati;
    }
    
    public String getTglCek() {
        return tgl_cek;
    }
    
    public void setIdKesehatan(String id_kesehatan) {
        this.id_kesehatan = id_kesehatan;
    }
    
    public void setNamaKandang(String nama_kandang) {
        this.nama_kandang = nama_kandang;
    }
    
    public void setNamaPenyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }
    
    public void setIdPegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }
    
    public void setJmlSakit(int jml_sakit) {
        this.jml_sakit = jml_sakit;
    }
    
    public void setJmlMati(int jml_mati) {
        this.jml_mati = jml_mati;
    }
    
    public void setTglCek(String tgl_cek) {
        this.tgl_cek = tgl_cek;
    }
}
