/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Penyakit;

/**
 *
 * @author Aulia
 */
public class Penyakit {
    private String id_penyakit;
    private String nama;
    private String keterangan;
    
    public String getIdPenyakit() {
        return id_penyakit;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getKeterangan() {
        return keterangan;
    }
    
    public void setIdPenyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
