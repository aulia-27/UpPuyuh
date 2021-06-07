/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CekTernak;

public class CekTernak {
    private int id_cek;
    private String id_kandang;
    private String id_pakan;
    private int jml_telur;
    private String kebersihan;
    private String tgl_cek;
    
    public int getIdCek() {
        return id_cek;
    }
    
    public String getIdKandang() {
        return id_kandang;
    }
    
    public String getIdPakan() {
        return id_pakan;
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
    
    public void setIdCek(int id_cek) {
        this.id_cek = id_cek;
    }
    
    public void setIdKandang(String id_kandang) {
        this.id_kandang = id_kandang;
    }
    
    public void setIdPakan(String id_pakan) {
        this.id_pakan = id_pakan;
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
