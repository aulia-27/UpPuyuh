/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakan;

public class Pakan {
    private String idPakan;
    private String nama;
    private double harga;
    private int stok;
    
    public String getIdPakan() {
        return idPakan;
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public int getStok() {
        return stok;
    }

    public void setIdPakan(String idPakan) {
        this.idPakan = idPakan;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }
}
