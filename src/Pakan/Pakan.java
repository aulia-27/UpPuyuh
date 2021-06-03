/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakan;

public class Pakan {
    private String idPakan;
    private String nama;
    private int harga;
    private int stok;
    private String jenis;
    
    public String getIdPakan() {
        return idPakan;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getHarga() {
        return harga;
    }
    
    public int getStok() {
        return stok;
    }
    
    public String getJenis() {
        return jenis;
    }
    
    public void setIdPakan(String idPakan) {
        this.idPakan = idPakan;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }
    
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
