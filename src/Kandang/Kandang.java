/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kandang;


public class Kandang {
    private String id_Kandang;
    private String nama;
    private int jmlTernak;
    private String blok_kandang;
    
    public String getIdKandang() {
        return id_Kandang;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getJmlTernak() {
        return jmlTernak;
    }
    
    public String getBlokKandang() {
        return blok_kandang;
    }
    
    public void setIdKandang(String id_Kandang) {
        this.id_Kandang = id_Kandang;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setJmlTernak(int jmlTernak) {
        this.jmlTernak = jmlTernak;
    }
    
    public void setBlokKandang(String blok_kandang) {
        this.blok_kandang = blok_kandang;
    }
}
