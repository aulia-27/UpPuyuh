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
    private String nama_penyakit;
    private String gejala;
    private String penularan;
    private String pencegahan;
    private String pengobatan;

    public String getNamaPenyakit() {
        return nama_penyakit;
    }
    
    public String getGejala() {
        return gejala;
    }
    
    public String getPenularan() {
        return penularan;
    }
    
    public String getPencegahan() {
        return pencegahan;
    }
    
    public String getPengobatan() {
        return pengobatan;
    }
    
    public void setNamaPenyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }
    
    public void setGejala(String gejala) {
        this.gejala = gejala;
    }
    
    public void setPenularan(String penularan) {
        this.penularan = penularan;
    }
    
    public void setPencegahan(String pencegahan) {
        this.pencegahan = pencegahan;
    }
    
    public void setPengobatan(String pengobatan) {
        this.pengobatan = pengobatan;
    }
}
