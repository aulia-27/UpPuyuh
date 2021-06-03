/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kesehatan;


public class Kesehatan {
    private String id_kesehatan;
    private String id_kandang;
    private String id_sakit;
    private int jml_sakit;
    private int jml_mati;
    
    public String getIdKesehatan() {
        return id_kesehatan;
    }
    
    public String getIdKandang() {
        return id_kandang;
    }
    
    public String getIdSakit() {
        return id_sakit;
    }
    
    public int getJmlSakit() {
        return jml_sakit;
    }
    
    public int getJmlMati() {
        return jml_mati;
    }
    
    public void setIdKesehatan(String id_kesehatan) {
        this.id_kesehatan = id_kesehatan;
    }
    
    public void setIdKandang(String id_kandang) {
        this.id_kandang = id_kandang;
    }
    
    public void setIdSakit(String id_sakit) {
        this.id_sakit = id_sakit;
    }
    
    public void setJmlSakit(int jml_sakit) {
        this.jml_sakit = jml_sakit;
    }
    
    public void setJmlMati(int jml_mati) {
        this.jml_mati = jml_mati;
    }
}
