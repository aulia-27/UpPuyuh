/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormUpPuyuh;

import Controller.MenuPegawaiController;

import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import User.User;
import Controller.UserController;

import User.Enkripsi;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aulia
 */
public class FormMenuPegawai extends javax.swing.JFrame {
    
    private DefaultTableModel model;
    UserController controllerUser = new UserController();
    User user = new User();
    List<User> listPengguna = new ArrayList<User>();

    /**
     * Creates new form FormMenuAdmin
     */
    
    MenuPegawaiController controller;
    public FormMenuPegawai() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        setIcon();
        showDatePanel();
        showTimePanel();
        setActiveMenu();
        
        //User
        clearText();
        buatTable();
        showTable();
        
        // Laporan
        disableTabelLaporan();
        //user
        controller = new MenuPegawaiController(this);
    }
    
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("puyuh.png")));
    }
    
    public void notificatioan() {

    }
    
    public void setActiveMenu() {
        MenuAdmin.setVisible(true);
        TabTugas.setVisible(true);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
        MenuDataPenyakit.setVisible(false);
    }
    
    public void showDatePanel() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEE, dd MMMM yyyy");
        DateKandang.setText(sdf.format(d));
        DatePegawai.setText(sdf.format(d));
        DatePakan.setText(sdf.format(d));
        DatePenyakit.setText(sdf.format(d));
        DateKesehatan.setText(sdf.format(d));
        DateCekTernak.setText(sdf.format(d));
        DateUser.setText(sdf.format(d));
    }
    
    public void showTimePanel() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ");
                TimeKandang.setText(sdf.format(d));
                TimePegawai.setText(sdf.format(d));
                TimePakan.setText(sdf.format(d));
                TimePenyakit.setText(sdf.format(d));
                TimeKesehatan.setText(sdf.format(d));
                TimeCekTernak.setText(sdf.format(d));
                TimeUser.setText(sdf.format(d));
            }
        }
        ).start();
    }
    
    public void disableTabelLaporan() {
        TabelLaporanKosong.setVisible(true);
        TabelLaporanKesehatan.setVisible(false);
        TabelLaporanCekTernak.setVisible(false);
        TabelLaporanPegawai.setVisible(false);
        TabelLaporanPakan.setVisible(false);
    }
    
    public void tampilNama(String Nama) {
        NamaAkun.setText(Nama);
    }
    
    public void tampilHakAkses(String Akses) {
        HakAkses.setText(Akses);
    }

////////////        Tab Akun            ////////////////
    
    public JTextField getTxtNamaUser() {
       return TxtNamaUser;
   }
    
    public JTextField getTxtUsername() {
       return TxtUsername;
   }
    
    public JTextField getTxtHakAkses() {
       return TxtHakAkses;
   }
    
    public JPasswordField getJpsPassword() {
       return JpsPassword;
   }
    
    public JPasswordField getJpsRePassword() {
       return JpsRePassword;
   }

////////////////         Tab Laporan     //////////////////
    
    public JTextField getTxtKodeLaporan() {
        return TxtKodeLaporan;
    }
    
    public JTable getTblLaporanKesehantan() {
        return TblLaporanKesehatan;
    }
    
    public JTable getTblLaporanCekTernak() {
        return TblLaporanCekTernak;
    }
    
    public JTable getTblLaporanPegawai() {
        return TblLaporanPegawai;
    }
    
    public JTable getTblLaporanPakan() {
        return TblLaporanPakan;
    }
    
    ////////////            Code Kandang             ////////////////
    
    public JTable getTblDataKandang() {
        return TblDataKandang;
    }
    
    public JTable getTblInputDataKandang() {
        return TblInputDataKandang;
    }
    
    public JTextField getTxtNamaKandang() {
        return TxtNamaKandang;
    }
    
    public JTextField getTxtJumlahTernak() {
        return TxtJmlTernak;
    }
    
    ///////////             Code Pegawai            ////////////////
    
    public JTextField getTxtIdPegawai() {
        return TxtIdPegawai;
    }
    
    public JTextField getTxtNamaPegawai() {
        return TxtNamaPegawai;
    }
    
    public JTextField getTxtAsal() {
        return TxtAsal;
    }
    
    public JDateChooser getJdtTglLahir() {
        return JdtTglLahir;
    }
    
    public JRadioButton getRbLakiLaki() {
        return RbLakiLaki;
    }
    
    public JRadioButton getRbPerempuan() {
        return RbPerempuan;
    }
    
    public JTextField getTxtNoTelp() {
        return TxtNoTelp;
    }
    
    public JTextArea getJtxAlamat() {
        return JtxAlamat;
    }
    
    public JTable getTblDataPegawai() {
        return TblDataPegawai;
    }
    
    public JTable getTblInputDataPegawai() {
        return TblInputDataPegawai;
    }

    ///////////////////     Pakan       ///////////////////////
    
    public JTextField getTxtIdPakan() {
        return TxtIdPakan;
    }
    
    public JTextField getTxtNamaPakan() {
        return TxtNamaPakan;
    }
    
    public JTextField getTxtStok() {
        return TxtStok;
    }
    
    public JTextField getTxtHarga() {
        return TxtHarga;
    }
    
    public JTable getTblDataPakan() {
        return TblDataPakan;
    }
    
    public JTable getTblInputDataPakan() {
        return TblInputDataPakan;
    }

    ///////////////////     Penyakit       ///////////////////////
    
    public JTextField getTxtNamaPenyakit() {
        return TxtNamaPenyakit;
    }
    
    public JTextArea getJtxGejala() {
        return JtxGejala;
    }
    
    public JTextArea getJtxPenularan() {
        return JtxPenularan;
    }
    
    public JTextArea getJtxPencegahan() {
        return JtxPencegahan;
    }
    
    public JTextArea getJtxPengobatan() {
        return JtxPengobatan;
    }
    
    public JTable getTblDataPenyakit() {
        return TblDataPenyakit;
    }
    
    public JTable getTblInputDataPenyakit() {
        return TblInputDataPenyakit;
    }
    
    ///////////////////     Kesehatan       ///////////////////////
    
    public JTextField getTxtIdKesehatan() {
        return TxtIdKesehatan;
    }
    
    public JTextField getTxtJmlSakit() {
        return TxtJmlSakit;
    }
    
    public JTextField getTxtJmlMati() {
        return TxtJmlMati;
    }
    
    public JTextField getTxtTglCekKesehatan() {
        return TxtTglCekKesehatan;
    }
    
    public JComboBox getCboKandang() {
        return CboKandang;
    }
    
    public JComboBox getCboIdPegawai() {
        return CboIdPegawai;
    }
    
    public JComboBox getCboPenyakit() {
        return CboPenyakit;
    }
    
    public JTable getTblDataKesehatan() {
        return TblDataKesehatan;
    }
    
    public JTable getTblInputDataKesehatan() {
        return TblInputDataKesehatan;
    }
    
    //////////////////          Cek Ternak          ///////////////////
    
    public JTextField getTxtIdCekTernak() {
        return TxtIdCekTernak;
    }
    
    public JTextField getTxtJmlTelur() {
        return TxtJmlTelur;
    }
    
    public JTextField getTxtTglCek() {
        return TxtTglCek;
    }
    
    public JComboBox getCboKandangCek() {
        return CboKandangCek;
    }
    
    public JComboBox getCboIdPakanCek() {
        return CboIdPakanCek;
    }
    
    public JComboBox getCboIdPegawaiCek() {
        return CboIdPegawaiCek;
    }
    
    public JComboBox getCboPakan() {
        return CboIdPakanCek;
    }
    
    public JComboBox getCboKebersihan() {
        return CboKebersihan;
    }
    
    public JTable getTblDataCekTernak() {
        return TblDataCekTernak;
    }
    
    public JTable getTblInputDataCekTernak() {
        return TblInputDataCekTernak;
    }
    
    //////////////////          User         ///////////////////
    
    public void clearText() {
        TxtIdUser.setText("");
        TxtDataUsername.setText("");
        JpsDataPassword.setText("");
        JpsDataRePassword.setText("");
        TxtDataNama.setText("");
    }
    
    private void buatTable() {
        model = new DefaultTableModel();
        model.addColumn("ID User");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Nama User");
        model.addColumn("Hak Akses");
        TblDataUser.setModel(model);
    }
    
    private void showTable() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        listPengguna.clear();
        listPengguna = controllerUser.Tampiil();
        
        for (int i = 0; i < listPengguna.size(); i++) {
            Object[] data = new Object[5];
            data[0] = listPengguna.get(i).getIdUser();
            data[1] = listPengguna.get(i).getUsername();
            data[2] = listPengguna.get(i).getPassword();
            data[3] = listPengguna.get(i).getNamaAkun();
            data[4] = listPengguna.get(i).getAkses();
            model.addRow(data);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Background = new javax.swing.JPanel();
        LabelUpPuyuh = new javax.swing.JLabel();
        SubMenu = new javax.swing.JPanel();
        MenuHome = new javax.swing.JPanel();
        jDashboard = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        MenuUser = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        MenuJadwal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MenuLaporan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MenuLogout = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        MenuUtama = new javax.swing.JPanel();
        MenuAdmin = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        TabTugas = new javax.swing.JPanel();
        Notifikasi1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        NotifBtnSdh = new javax.swing.JButton();
        Notifikasi2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        NotifInputKsh = new javax.swing.JButton();
        Notifikasi3 = new javax.swing.JPanel();
        NotifBtnCek = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        Tugas = new javax.swing.JLabel();
        BtnTakAdmin = new javax.swing.JPanel();
        BtnDataKandang = new javax.swing.JButton();
        BtnDataPegawai = new javax.swing.JButton();
        BtnDataPakan = new javax.swing.JButton();
        BtnDataPenyakit = new javax.swing.JButton();
        BtnDataKesehatan = new javax.swing.JButton();
        BtnDataCekTernak = new javax.swing.JButton();
        BtnDataUser = new javax.swing.JButton();
        TabAkun = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        TxtNamaUser = new javax.swing.JTextField();
        TxtUsername = new javax.swing.JTextField();
        TxtHakAkses = new javax.swing.JTextField();
        JpsPassword = new javax.swing.JPasswordField();
        JpsRePassword = new javax.swing.JPasswordField();
        jButton11 = new javax.swing.JButton();
        TabJadwal = new javax.swing.JPanel();
        TabLaporan = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        PanelInputLaporan = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        TxtKodeLaporan = new javax.swing.JTextField();
        BtnCekKodeLpr = new javax.swing.JButton();
        BtnExportExcel = new javax.swing.JButton();
        BtnExportPdf = new javax.swing.JButton();
        PanelTabelLaporan = new javax.swing.JPanel();
        TabelLaporanKosong = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        TblLaporanKosong = new javax.swing.JTable();
        TabelLaporanKesehatan = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        TblLaporanKesehatan = new javax.swing.JTable();
        TabelLaporanCekTernak = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        TblLaporanCekTernak = new javax.swing.JTable();
        TabelLaporanPegawai = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        TblLaporanPegawai = new javax.swing.JTable();
        TabelLaporanPakan = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        TblLaporanPakan = new javax.swing.JTable();
        ProfilAkun = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        StatusTime = new javax.swing.JLabel();
        NamaAkun = new javax.swing.JLabel();
        HakAkses = new javax.swing.JLabel();
        MenuKandang = new javax.swing.JPanel();
        MenuDataKandang = new javax.swing.JPanel();
        PanelMenuDataKandang = new javax.swing.JPanel();
        BtnInputDataKandang = new javax.swing.JButton();
        TimeKandang = new javax.swing.JLabel();
        DateKandang = new javax.swing.JLabel();
        KembaliMenuUtama1 = new javax.swing.JPanel();
        IconBack1 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        PanelTabelKandang = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblDataKandang = new javax.swing.JTable();
        MenuInputKandang = new javax.swing.JPanel();
        PanelMenuInputKandang = new javax.swing.JPanel();
        KembaliDataKandang = new javax.swing.JPanel();
        IconBackKandang = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        PanelInputKandang = new javax.swing.JPanel();
        PanelInputDataKandang = new javax.swing.JPanel();
        TxtJmlTernak = new javax.swing.JTextField();
        TxtNamaKandang = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        BtnSimpanKandang = new javax.swing.JButton();
        BtnHapusKandang = new javax.swing.JButton();
        BtnBatalKandang = new javax.swing.JButton();
        BtnUpdateKandang = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TblInputDataKandang = new javax.swing.JTable();
        MenuPegawai = new javax.swing.JPanel();
        MenuDataPegawai = new javax.swing.JPanel();
        PanelMenuDataPegawai = new javax.swing.JPanel();
        BtnInputDataPegawai = new javax.swing.JButton();
        TimePegawai = new javax.swing.JLabel();
        DatePegawai = new javax.swing.JLabel();
        KembaliMenuUtama2 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblDataPegawai = new javax.swing.JTable();
        MenuInputPegawai = new javax.swing.JPanel();
        PanelMenuInputPegawai = new javax.swing.JPanel();
        KembaliDataPegawai = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        TxtIdPegawai = new javax.swing.JTextField();
        TxtNamaPegawai = new javax.swing.JTextField();
        TxtAsal = new javax.swing.JTextField();
        JdtTglLahir = new com.toedter.calendar.JDateChooser();
        RbLakiLaki = new javax.swing.JRadioButton();
        RbPerempuan = new javax.swing.JRadioButton();
        TxtNoTelp = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        JtxAlamat = new javax.swing.JTextArea();
        BtnSimpanPegawai = new javax.swing.JButton();
        BtnHapusPegawai = new javax.swing.JButton();
        BtnBatalPegawai = new javax.swing.JButton();
        BtnUpdatePegawai = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TblInputDataPegawai = new javax.swing.JTable();
        MenuPakan = new javax.swing.JPanel();
        MenuDataPakan = new javax.swing.JPanel();
        PanelMenuDataPakan = new javax.swing.JPanel();
        BtnInputDataPakan = new javax.swing.JButton();
        TimePakan = new javax.swing.JLabel();
        DatePakan = new javax.swing.JLabel();
        KembaliMenuUtama3 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TblDataPakan = new javax.swing.JTable();
        MenuInputPakan = new javax.swing.JPanel();
        PanelMenuInputPakan = new javax.swing.JPanel();
        KembaliDataPakan = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        PanelInputPakan = new javax.swing.JPanel();
        PanelInputDataKandang1 = new javax.swing.JPanel();
        TxtHarga = new javax.swing.JTextField();
        TxtStok = new javax.swing.JTextField();
        TxtNamaPakan = new javax.swing.JTextField();
        TxtIdPakan = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        BtnSimpanPakan = new javax.swing.JButton();
        BtnHapusPakan = new javax.swing.JButton();
        BtnBatalPakan = new javax.swing.JButton();
        BtnUpdatePakan = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        TblInputDataPakan = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        MenuPenyakit = new javax.swing.JPanel();
        MenuDataPenyakit = new javax.swing.JPanel();
        PanelMenuDataPenyakit = new javax.swing.JPanel();
        BtnInputDataPenyakit = new javax.swing.JButton();
        TimePenyakit = new javax.swing.JLabel();
        DatePenyakit = new javax.swing.JLabel();
        KembaliMenuUtama4 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TblDataPenyakit = new javax.swing.JTable();
        MenuInputPenyakit = new javax.swing.JPanel();
        PanelMenuInputPenyakit = new javax.swing.JPanel();
        KembaliDataPenyakit = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        PanelInputPenyakit = new javax.swing.JPanel();
        PanelInputDataPenyaki = new javax.swing.JPanel();
        TxtNamaPenyakit = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        BtnSimpanPeenyakit = new javax.swing.JButton();
        BtnHapusPenyakit = new javax.swing.JButton();
        BtnBatalPenyakit = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        JtxGejala = new javax.swing.JTextArea();
        BtnUpdatePenyakit = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        JtxPenularan = new javax.swing.JTextArea();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        JtxPencegahan = new javax.swing.JTextArea();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        JtxPengobatan = new javax.swing.JTextArea();
        jLabel97 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TblInputDataPenyakit = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        MenuKesehatan = new javax.swing.JPanel();
        MenuDataKesehatan = new javax.swing.JPanel();
        PanelMenuDataKesehatan = new javax.swing.JPanel();
        BtnInputDataKesehatan = new javax.swing.JButton();
        TimeKesehatan = new javax.swing.JLabel();
        DateKesehatan = new javax.swing.JLabel();
        KembaliMenuUtamaKsht = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TblDataKesehatan = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        MenuInputKesehatan = new javax.swing.JPanel();
        PanelMenuInputKesehatan = new javax.swing.JPanel();
        KembaliDataKesehatan = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        PanelInputKesehatan = new javax.swing.JPanel();
        PanelInputDataKesehatan = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        TxtIdKesehatan = new javax.swing.JTextField();
        TxtJmlSakit = new javax.swing.JTextField();
        TxtJmlMati = new javax.swing.JTextField();
        CboKandang = new javax.swing.JComboBox<>();
        CboIdPegawai = new javax.swing.JComboBox<>();
        CboPenyakit = new javax.swing.JComboBox<>();
        BtnSimpanKesehatan = new javax.swing.JButton();
        BtnHapusKesehatan = new javax.swing.JButton();
        BtnBatalKesehatan = new javax.swing.JButton();
        BtnUpdateKesehatan = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        TxtTglCekKesehatan = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        TblInputDataKesehatan = new javax.swing.JTable();
        jLabel99 = new javax.swing.JLabel();
        MenuCekTernak = new javax.swing.JPanel();
        MenuDataCekTernak = new javax.swing.JPanel();
        PanelMenuDataKesehatan1 = new javax.swing.JPanel();
        BtnInputDataCekTernak = new javax.swing.JButton();
        TimeCekTernak = new javax.swing.JLabel();
        DateCekTernak = new javax.swing.JLabel();
        KembaliMenuUtamaCek = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TblDataCekTernak = new javax.swing.JTable();
        MenuInputCekTernak = new javax.swing.JPanel();
        PanelMenuInputCekTernak = new javax.swing.JPanel();
        KembaliDataCekTernak = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        PanelInputCekTernak = new javax.swing.JPanel();
        PanelInputDataKesehatan1 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        TblInputDataCekTernak = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        TxtIdCekTernak = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        CboKandangCek = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        CboIdPegawaiCek = new javax.swing.JComboBox<>();
        CboIdPakanCek = new javax.swing.JComboBox<>();
        TxtTglCek = new javax.swing.JTextField();
        CboKebersihan = new javax.swing.JComboBox<>();
        TxtJmlTelur = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        BtnSimpanCek = new javax.swing.JButton();
        BtnUpdateCek = new javax.swing.JButton();
        BtnHapusCek = new javax.swing.JButton();
        BtnBatalCek = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        MenuDataUser = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TblDataUser = new javax.swing.JTable();
        PanelMenuDataUser = new javax.swing.JPanel();
        TimeUser = new javax.swing.JLabel();
        DateUser = new javax.swing.JLabel();
        KembaliMenuUtamaUser = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        TxtIdUser = new javax.swing.JTextField();
        TxtDataUsername = new javax.swing.JTextField();
        TxtDataNama = new javax.swing.JTextField();
        JpsDataPassword = new javax.swing.JPasswordField();
        CboAkses = new javax.swing.JComboBox<>();
        JpsDataRePassword = new javax.swing.JPasswordField();
        BtnSimpan = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Up Puyuh ");

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setPreferredSize(new java.awt.Dimension(1920, 1080));

        LabelUpPuyuh.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        LabelUpPuyuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelUpPuyuh.setText("2021 Peternakan Burung Puyuh");

        SubMenu.setBackground(new java.awt.Color(247, 247, 247));

        MenuHome.setBackground(new java.awt.Color(247, 247, 247));
        MenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHomeMouseClicked(evt);
            }
        });

        jDashboard.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/Dashboard1.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 71, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Dashboard");

        javax.swing.GroupLayout MenuHomeLayout = new javax.swing.GroupLayout(MenuHome);
        MenuHome.setLayout(MenuHomeLayout);
        MenuHomeLayout.setHorizontalGroup(
            MenuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        MenuHomeLayout.setVerticalGroup(
            MenuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuHomeLayout.createSequentialGroup()
                .addComponent(jDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel16))
        );

        MenuUser.setBackground(new java.awt.Color(247, 247, 247));
        MenuUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuUserMouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/User.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User");

        javax.swing.GroupLayout MenuUserLayout = new javax.swing.GroupLayout(MenuUser);
        MenuUser.setLayout(MenuUserLayout);
        MenuUserLayout.setHorizontalGroup(
            MenuUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuUserLayout.setVerticalGroup(
            MenuUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuUserLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );

        MenuJadwal.setBackground(new java.awt.Color(247, 247, 247));
        MenuJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuJadwalMouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconCalender.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 71, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Jadwal");

        javax.swing.GroupLayout MenuJadwalLayout = new javax.swing.GroupLayout(MenuJadwal);
        MenuJadwal.setLayout(MenuJadwalLayout);
        MenuJadwalLayout.setHorizontalGroup(
            MenuJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuJadwalLayout.setVerticalGroup(
            MenuJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuJadwalLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        MenuLaporan.setBackground(new java.awt.Color(247, 247, 247));
        MenuLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuLaporanMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 71, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Laporan");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconReport.png"))); // NOI18N

        javax.swing.GroupLayout MenuLaporanLayout = new javax.swing.GroupLayout(MenuLaporan);
        MenuLaporan.setLayout(MenuLaporanLayout);
        MenuLaporanLayout.setHorizontalGroup(
            MenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuLaporanLayout.setVerticalGroup(
            MenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLaporanLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6))
        );

        MenuLogout.setBackground(new java.awt.Color(247, 247, 247));
        MenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuLogoutMouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/Icon Logout.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 80, 81));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Logout");

        javax.swing.GroupLayout MenuLogoutLayout = new javax.swing.GroupLayout(MenuLogout);
        MenuLogout.setLayout(MenuLogoutLayout);
        MenuLogoutLayout.setHorizontalGroup(
            MenuLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLogoutLayout.setVerticalGroup(
            MenuLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLogoutLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout SubMenuLayout = new javax.swing.GroupLayout(SubMenu);
        SubMenu.setLayout(SubMenuLayout);
        SubMenuLayout.setHorizontalGroup(
            SubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuJadwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(MenuLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SubMenuLayout.setVerticalGroup(
            SubMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubMenuLayout.createSequentialGroup()
                .addComponent(MenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(MenuUser, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(MenuJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(MenuLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(MenuLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MenuUtama.setBackground(new java.awt.Color(255, 255, 255));
        MenuUtama.setLayout(new java.awt.CardLayout());

        MenuAdmin.setBackground(new java.awt.Color(255, 255, 255));

        Menu.setLayout(new java.awt.CardLayout());

        Notifikasi1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel13.setText("Sudahkah Anda Mememberi Makan Burung Puyuh ?");

        NotifBtnSdh.setBackground(new java.awt.Color(68, 113, 231));
        NotifBtnSdh.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        NotifBtnSdh.setForeground(new java.awt.Color(255, 255, 255));
        NotifBtnSdh.setText("Sudah");
        NotifBtnSdh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifBtnSdhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Notifikasi1Layout = new javax.swing.GroupLayout(Notifikasi1);
        Notifikasi1.setLayout(Notifikasi1Layout);
        Notifikasi1Layout.setHorizontalGroup(
            Notifikasi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NotifBtnSdh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Notifikasi1Layout.setVerticalGroup(
            Notifikasi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Notifikasi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NotifBtnSdh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Notifikasi2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel15.setText("Anda Belum Memasukan Data Kesehatan  Burung Puyuh Hari Ini Hari Ini ");

        NotifInputKsh.setBackground(new java.awt.Color(68, 113, 231));
        NotifInputKsh.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        NotifInputKsh.setForeground(new java.awt.Color(255, 255, 255));
        NotifInputKsh.setText("Input Data");
        NotifInputKsh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifInputKshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Notifikasi2Layout = new javax.swing.GroupLayout(Notifikasi2);
        Notifikasi2.setLayout(Notifikasi2Layout);
        Notifikasi2Layout.setHorizontalGroup(
            Notifikasi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NotifInputKsh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Notifikasi2Layout.setVerticalGroup(
            Notifikasi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Notifikasi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NotifInputKsh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Notifikasi3.setBackground(new java.awt.Color(255, 255, 255));

        NotifBtnCek.setBackground(new java.awt.Color(68, 113, 231));
        NotifBtnCek.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        NotifBtnCek.setForeground(new java.awt.Color(255, 255, 255));
        NotifBtnCek.setText("Input Data");
        NotifBtnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifBtnCekActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel17.setText("Anda Belum Memasukan Data Cek Ternak Burung Puyuh Hari Ini Hari Ini ");

        javax.swing.GroupLayout Notifikasi3Layout = new javax.swing.GroupLayout(Notifikasi3);
        Notifikasi3.setLayout(Notifikasi3Layout);
        Notifikasi3Layout.setHorizontalGroup(
            Notifikasi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NotifBtnCek, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Notifikasi3Layout.setVerticalGroup(
            Notifikasi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Notifikasi3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Notifikasi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NotifBtnCek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tugas.setFont(new java.awt.Font("Montserrat", 0, 36)); // NOI18N
        Tugas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tugas.setText("Tugas");

        BtnDataKandang.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataKandang.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataKandang.setText("Data Kandang");
        BtnDataKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataKandangActionPerformed(evt);
            }
        });

        BtnDataPegawai.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataPegawai.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataPegawai.setText("Data Pegawai");
        BtnDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPegawaiActionPerformed(evt);
            }
        });

        BtnDataPakan.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataPakan.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataPakan.setText("Data Pakan");
        BtnDataPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPakanActionPerformed(evt);
            }
        });

        BtnDataPenyakit.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataPenyakit.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataPenyakit.setText("Data Penyakit");
        BtnDataPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPenyakitActionPerformed(evt);
            }
        });

        BtnDataKesehatan.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataKesehatan.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataKesehatan.setText("Data Kesehatan");
        BtnDataKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataKesehatanActionPerformed(evt);
            }
        });

        BtnDataCekTernak.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataCekTernak.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataCekTernak.setText("Data Cek Ternak");
        BtnDataCekTernak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataCekTernakActionPerformed(evt);
            }
        });

        BtnDataUser.setBackground(new java.awt.Color(250, 250, 250));
        BtnDataUser.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        BtnDataUser.setText("Data User");
        BtnDataUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BtnTakAdminLayout = new javax.swing.GroupLayout(BtnTakAdmin);
        BtnTakAdmin.setLayout(BtnTakAdminLayout);
        BtnTakAdminLayout.setHorizontalGroup(
            BtnTakAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnTakAdminLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(BtnTakAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnDataKandang, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(BtnDataPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(BtnDataPakan, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(BtnDataPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(BtnDataKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(BtnDataCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        BtnTakAdminLayout.setVerticalGroup(
            BtnTakAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnTakAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BtnTakAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDataKandang, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(BtnDataPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(BtnDataPakan, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(BtnDataKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(BtnDataCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(BtnDataPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(BtnDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );

        javax.swing.GroupLayout TabTugasLayout = new javax.swing.GroupLayout(TabTugas);
        TabTugas.setLayout(TabTugasLayout);
        TabTugasLayout.setHorizontalGroup(
            TabTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabTugasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Notifikasi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Notifikasi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Notifikasi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnTakAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(Tugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TabTugasLayout.setVerticalGroup(
            TabTugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabTugasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Notifikasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Notifikasi2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Notifikasi3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tugas)
                .addGap(18, 18, 18)
                .addComponent(BtnTakAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Menu.add(TabTugas, "card2");

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("User");

        jLabel19.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel19.setText("Ulangi Password");

        jLabel18.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel18.setText("Password");

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel12.setText("Hak Akses");

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel11.setText("Username");

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel10.setText("Nama User");

        jButton10.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jButton10.setText("Edit");

        TxtNamaUser.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        TxtNamaUser.setText("jTextField1");

        TxtUsername.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        TxtUsername.setText("jTextField1");

        TxtHakAkses.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        TxtHakAkses.setText("jTextField1");

        JpsPassword.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        JpsPassword.setText("jPasswordField1");

        JpsRePassword.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        JpsRePassword.setText("jPasswordField1");

        jButton11.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jButton11.setText("Ubah Password");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtNamaUser)
                            .addComponent(TxtUsername)
                            .addComponent(TxtHakAkses)
                            .addComponent(JpsPassword)
                            .addComponent(JpsRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(TxtNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JpsPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JpsRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabAkunLayout = new javax.swing.GroupLayout(TabAkun);
        TabAkun.setLayout(TabAkunLayout);
        TabAkunLayout.setHorizontalGroup(
            TabAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TabAkunLayout.setVerticalGroup(
            TabAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabAkunLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel14)
                .addGap(32, 32, 32)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        Menu.add(TabAkun, "card3");

        javax.swing.GroupLayout TabJadwalLayout = new javax.swing.GroupLayout(TabJadwal);
        TabJadwal.setLayout(TabJadwalLayout);
        TabJadwalLayout.setHorizontalGroup(
            TabJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1141, Short.MAX_VALUE)
        );
        TabJadwalLayout.setVerticalGroup(
            TabJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );

        Menu.add(TabJadwal, "card4");

        jLabel20.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Laporan Peternakan Burung Puyuh");

        jLabel21.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel21.setText("1. Laporan Kesehatan");

        jLabel22.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel22.setText("2. Laporan Cek Ternak");

        jLabel24.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel24.setText("4. Laporan Pakan");

        jLabel23.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel23.setText("3. Laporan Pegawai");

        jLabel25.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel25.setText("Masukan Kode Laporan");

        TxtKodeLaporan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtKodeLaporan.setText("jTextField1");
        TxtKodeLaporan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtKodeLaporanKeyTyped(evt);
            }
        });

        BtnCekKodeLpr.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnCekKodeLpr.setText("Cek");
        BtnCekKodeLpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekKodeLprActionPerformed(evt);
            }
        });

        BtnExportExcel.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnExportExcel.setText("Cetak Laporan Format Excel");
        BtnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExportExcelActionPerformed(evt);
            }
        });

        BtnExportPdf.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnExportPdf.setText("Cetak Laporan Format PDF");
        BtnExportPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExportPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInputLaporanLayout = new javax.swing.GroupLayout(PanelInputLaporan);
        PanelInputLaporan.setLayout(PanelInputLaporanLayout);
        PanelInputLaporanLayout.setHorizontalGroup(
            PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLaporanLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelInputLaporanLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(TxtKodeLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCekKodeLpr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelInputLaporanLayout.createSequentialGroup()
                        .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92)
                        .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnExportPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnExportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelInputLaporanLayout.setVerticalGroup(
            PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputLaporanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(BtnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputLaporanLayout.createSequentialGroup()
                        .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(PanelInputLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtKodeLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnCekKodeLpr, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BtnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTabelLaporan.setLayout(new java.awt.CardLayout());

        TblLaporanKosong.setAutoCreateRowSorter(true);
        TblLaporanKosong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblLaporanKosong.setRowHeight(28);
        jScrollPane24.setViewportView(TblLaporanKosong);

        javax.swing.GroupLayout TabelLaporanKosongLayout = new javax.swing.GroupLayout(TabelLaporanKosong);
        TabelLaporanKosong.setLayout(TabelLaporanKosongLayout);
        TabelLaporanKosongLayout.setHorizontalGroup(
            TabelLaporanKosongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabelLaporanKosongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabelLaporanKosongLayout.setVerticalGroup(
            TabelLaporanKosongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabelLaporanKosongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTabelLaporan.add(TabelLaporanKosong, "card6");

        TblLaporanKesehatan.setAutoCreateRowSorter(true);
        TblLaporanKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblLaporanKesehatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Kesehatan", "Nama Kandang", "Nama Penyakit", "ID Pegawai", "Jumlah Sakit", "Jumlah Mati", "Tanggal Cek"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblLaporanKesehatan.setRowHeight(28);
        jScrollPane20.setViewportView(TblLaporanKesehatan);

        javax.swing.GroupLayout TabelLaporanKesehatanLayout = new javax.swing.GroupLayout(TabelLaporanKesehatan);
        TabelLaporanKesehatan.setLayout(TabelLaporanKesehatanLayout);
        TabelLaporanKesehatanLayout.setHorizontalGroup(
            TabelLaporanKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1117, Short.MAX_VALUE)
            .addGroup(TabelLaporanKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TabelLaporanKesehatanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        TabelLaporanKesehatanLayout.setVerticalGroup(
            TabelLaporanKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
            .addGroup(TabelLaporanKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TabelLaporanKesehatanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        PanelTabelLaporan.add(TabelLaporanKesehatan, "card8");

        TblLaporanCekTernak.setAutoCreateRowSorter(true);
        TblLaporanCekTernak.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblLaporanCekTernak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Cek Ternak", "Nama Kandang", "ID Pegawai", "ID Pakan", "Jumlah Telur", "Kebersihan", "Tanggal Cek"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblLaporanCekTernak.setRowHeight(28);
        jScrollPane21.setViewportView(TblLaporanCekTernak);

        javax.swing.GroupLayout TabelLaporanCekTernakLayout = new javax.swing.GroupLayout(TabelLaporanCekTernak);
        TabelLaporanCekTernak.setLayout(TabelLaporanCekTernakLayout);
        TabelLaporanCekTernakLayout.setHorizontalGroup(
            TabelLaporanCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1117, Short.MAX_VALUE)
            .addGroup(TabelLaporanCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TabelLaporanCekTernakLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        TabelLaporanCekTernakLayout.setVerticalGroup(
            TabelLaporanCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
            .addGroup(TabelLaporanCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TabelLaporanCekTernakLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        PanelTabelLaporan.add(TabelLaporanCekTernak, "card8");

        TblLaporanPegawai.setAutoCreateRowSorter(true);
        TblLaporanPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblLaporanPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama Pegawai", "Asal", "Tanggal Lahir", "Jenis Kelamin", "No Telepon", "Alamat Kandang"
            }
        ));
        TblLaporanPegawai.setRowHeight(28);
        jScrollPane22.setViewportView(TblLaporanPegawai);

        javax.swing.GroupLayout TabelLaporanPegawaiLayout = new javax.swing.GroupLayout(TabelLaporanPegawai);
        TabelLaporanPegawai.setLayout(TabelLaporanPegawaiLayout);
        TabelLaporanPegawaiLayout.setHorizontalGroup(
            TabelLaporanPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabelLaporanPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabelLaporanPegawaiLayout.setVerticalGroup(
            TabelLaporanPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabelLaporanPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTabelLaporan.add(TabelLaporanPegawai, "card7");

        TblLaporanPakan.setAutoCreateRowSorter(true);
        TblLaporanPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblLaporanPakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pakan", "Nama Pakan", "Harga", "Stok"
            }
        ));
        TblLaporanPakan.setRowHeight(28);
        jScrollPane23.setViewportView(TblLaporanPakan);

        javax.swing.GroupLayout TabelLaporanPakanLayout = new javax.swing.GroupLayout(TabelLaporanPakan);
        TabelLaporanPakan.setLayout(TabelLaporanPakanLayout);
        TabelLaporanPakanLayout.setHorizontalGroup(
            TabelLaporanPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabelLaporanPakanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabelLaporanPakanLayout.setVerticalGroup(
            TabelLaporanPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabelLaporanPakanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTabelLaporan.add(TabelLaporanPakan, "card7");

        javax.swing.GroupLayout TabLaporanLayout = new javax.swing.GroupLayout(TabLaporan);
        TabLaporan.setLayout(TabLaporanLayout);
        TabLaporanLayout.setHorizontalGroup(
            TabLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TabLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInputLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTabelLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        TabLaporanLayout.setVerticalGroup(
            TabLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInputLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTabelLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Menu.add(TabLaporan, "card5");

        ProfilAkun.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Icon");

        StatusTime.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        StatusTime.setText("StatusTime");

        NamaAkun.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        NamaAkun.setText("Nama");

        HakAkses.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        HakAkses.setText("Hak Akses");

        javax.swing.GroupLayout ProfilAkunLayout = new javax.swing.GroupLayout(ProfilAkun);
        ProfilAkun.setLayout(ProfilAkunLayout);
        ProfilAkunLayout.setHorizontalGroup(
            ProfilAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilAkunLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel9)
                .addGap(128, 128, 128)
                .addGroup(ProfilAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NamaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusTime, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProfilAkunLayout.setVerticalGroup(
            ProfilAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilAkunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StatusTime)
                .addGap(19, 19, 19)
                .addGroup(ProfilAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(NamaAkun))
                .addGap(18, 18, 18)
                .addComponent(HakAkses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MenuAdminLayout = new javax.swing.GroupLayout(MenuAdmin);
        MenuAdmin.setLayout(MenuAdminLayout);
        MenuAdminLayout.setHorizontalGroup(
            MenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProfilAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuAdminLayout.setVerticalGroup(
            MenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuAdminLayout.createSequentialGroup()
                .addComponent(ProfilAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuUtama.add(MenuAdmin, "card3");

        MenuKandang.setLayout(new java.awt.CardLayout());

        MenuDataKandang.setBackground(new java.awt.Color(255, 255, 255));

        PanelMenuDataKandang.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataKandang.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataKandang.setText("Tambah Data");
        BtnInputDataKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataKandangActionPerformed(evt);
            }
        });

        TimeKandang.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        TimeKandang.setText("Time");

        DateKandang.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        DateKandang.setText("Date");

        KembaliMenuUtama1.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtama1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtama1MouseClicked(evt);
            }
        });

        IconBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        jLabel42.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel42.setText("Kembali");

        javax.swing.GroupLayout KembaliMenuUtama1Layout = new javax.swing.GroupLayout(KembaliMenuUtama1);
        KembaliMenuUtama1.setLayout(KembaliMenuUtama1Layout);
        KembaliMenuUtama1Layout.setHorizontalGroup(
            KembaliMenuUtama1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama1Layout.createSequentialGroup()
                .addComponent(IconBack1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42))
        );
        KembaliMenuUtama1Layout.setVerticalGroup(
            KembaliMenuUtama1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(IconBack1)
            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout PanelMenuDataKandangLayout = new javax.swing.GroupLayout(PanelMenuDataKandang);
        PanelMenuDataKandang.setLayout(PanelMenuDataKandangLayout);
        PanelMenuDataKandangLayout.setHorizontalGroup(
            PanelMenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataKandangLayout.createSequentialGroup()
                .addGroup(PanelMenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMenuDataKandangLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(DateKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(TimeKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelMenuDataKandangLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(KembaliMenuUtama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnInputDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelMenuDataKandangLayout.setVerticalGroup(
            PanelMenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataKandangLayout.createSequentialGroup()
                .addGroup(PanelMenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataKandangLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(PanelMenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DateKandang)
                            .addComponent(TimeKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(PanelMenuDataKandangLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel28.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Data Kandang Peternakan Burung Puyuh");

        TblDataKandang.setAutoCreateRowSorter(true);
        TblDataKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataKandang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Nama Kandang", "Jumlah Ternak"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDataKandang.setRowHeight(28);
        jScrollPane2.setViewportView(TblDataKandang);

        javax.swing.GroupLayout PanelTabelKandangLayout = new javax.swing.GroupLayout(PanelTabelKandang);
        PanelTabelKandang.setLayout(PanelTabelKandangLayout);
        PanelTabelKandangLayout.setHorizontalGroup(
            PanelTabelKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTabelKandangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        PanelTabelKandangLayout.setVerticalGroup(
            PanelTabelKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTabelKandangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuDataKandangLayout = new javax.swing.GroupLayout(MenuDataKandang);
        MenuDataKandang.setLayout(MenuDataKandangLayout);
        MenuDataKandangLayout.setHorizontalGroup(
            MenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataKandang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelTabelKandang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuDataKandangLayout.setVerticalGroup(
            MenuDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataKandangLayout.createSequentialGroup()
                .addComponent(PanelMenuDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTabelKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuKandang.add(MenuDataKandang, "card2");

        MenuInputKandang.setBackground(new java.awt.Color(255, 255, 255));

        PanelMenuInputKandang.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataKandang.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataKandang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataKandangMouseClicked(evt);
            }
        });

        IconBackKandang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel32.setText("Kembali");

        javax.swing.GroupLayout KembaliDataKandangLayout = new javax.swing.GroupLayout(KembaliDataKandang);
        KembaliDataKandang.setLayout(KembaliDataKandangLayout);
        KembaliDataKandangLayout.setHorizontalGroup(
            KembaliDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataKandangLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(IconBackKandang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addContainerGap())
        );
        KembaliDataKandangLayout.setVerticalGroup(
            KembaliDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataKandangLayout.createSequentialGroup()
                .addComponent(IconBackKandang)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelMenuInputKandangLayout = new javax.swing.GroupLayout(PanelMenuInputKandang);
        PanelMenuInputKandang.setLayout(PanelMenuInputKandangLayout);
        PanelMenuInputKandangLayout.setHorizontalGroup(
            PanelMenuInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputKandangLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuInputKandangLayout.setVerticalGroup(
            PanelMenuInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputKandangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TxtJmlTernak.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtJmlTernak.setText("jTextField1");
        TxtJmlTernak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtJmlTernakKeyTyped(evt);
            }
        });

        TxtNamaKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtNamaKandang.setText("jTextField1");

        jLabel29.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel29.setText("Nama Kandang");

        jLabel33.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel33.setText("Jumlah Burung Puyuh");

        BtnSimpanKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanKandang.setText("Simpan");
        BtnSimpanKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanKandangActionPerformed(evt);
            }
        });

        BtnHapusKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusKandang.setText("Hapus");
        BtnHapusKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKandangActionPerformed(evt);
            }
        });

        BtnBatalKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalKandang.setText("Batal");
        BtnBatalKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalKandangActionPerformed(evt);
            }
        });

        BtnUpdateKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdateKandang.setText("Update");
        BtnUpdateKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateKandangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInputDataKandangLayout = new javax.swing.GroupLayout(PanelInputDataKandang);
        PanelInputDataKandang.setLayout(PanelInputDataKandangLayout);
        PanelInputDataKandangLayout.setHorizontalGroup(
            PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKandangLayout.createSequentialGroup()
                .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelInputDataKandangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInputDataKandangLayout.createSequentialGroup()
                                .addComponent(TxtJmlTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(TxtNamaKandang)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelInputDataKandangLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInputDataKandangLayout.createSequentialGroup()
                                .addComponent(BtnSimpanKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnUpdateKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelInputDataKandangLayout.createSequentialGroup()
                                .addGap(266, 266, 266)
                                .addComponent(BtnHapusKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnBatalKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        PanelInputDataKandangLayout.setVerticalGroup(
            PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKandangLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNamaKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtJmlTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(PanelInputDataKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpanKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBatalKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdateKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Input Data Kandang Peternakan Burung Puyuh");

        TblInputDataKandang.setAutoCreateRowSorter(true);
        TblInputDataKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataKandang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Nama Kandang", "Jumlah Ternak"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblInputDataKandang.setRowHeight(28);
        TblInputDataKandang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataKandangMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(TblInputDataKandang);

        javax.swing.GroupLayout PanelInputKandangLayout = new javax.swing.GroupLayout(PanelInputKandang);
        PanelInputKandang.setLayout(PanelInputKandangLayout);
        PanelInputKandangLayout.setHorizontalGroup(
            PanelInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
            .addGroup(PanelInputKandangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        PanelInputKandangLayout.setVerticalGroup(
            PanelInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputKandangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addGroup(PanelInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInputDataKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuInputKandangLayout = new javax.swing.GroupLayout(MenuInputKandang);
        MenuInputKandang.setLayout(MenuInputKandangLayout);
        MenuInputKandangLayout.setHorizontalGroup(
            MenuInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelInputKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputKandangLayout.setVerticalGroup(
            MenuInputKandangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuInputKandangLayout.createSequentialGroup()
                .addComponent(PanelMenuInputKandang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(PanelInputKandang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuKandang.add(MenuInputKandang, "card3");

        MenuUtama.add(MenuKandang, "card3");

        MenuPegawai.setLayout(new java.awt.CardLayout());

        PanelMenuDataPegawai.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataPegawai.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataPegawai.setText("Tambah Data");
        BtnInputDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataPegawaiActionPerformed(evt);
            }
        });

        TimePegawai.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimePegawai.setText("Time");

        DatePegawai.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DatePegawai.setText("Date");

        KembaliMenuUtama2.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtama2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtama2MouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel44.setText("Kembali");

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtama2Layout = new javax.swing.GroupLayout(KembaliMenuUtama2);
        KembaliMenuUtama2.setLayout(KembaliMenuUtama2Layout);
        KembaliMenuUtama2Layout.setHorizontalGroup(
            KembaliMenuUtama2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtama2Layout.setVerticalGroup(
            KembaliMenuUtama2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama2Layout.createSequentialGroup()
                .addGroup(KembaliMenuUtama2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataPegawaiLayout = new javax.swing.GroupLayout(PanelMenuDataPegawai);
        PanelMenuDataPegawai.setLayout(PanelMenuDataPegawaiLayout);
        PanelMenuDataPegawaiLayout.setHorizontalGroup(
            PanelMenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataPegawaiLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(DatePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TimePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(BtnInputDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelMenuDataPegawaiLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliMenuUtama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataPegawaiLayout.setVerticalGroup(
            PanelMenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataPegawaiLayout.createSequentialGroup()
                .addGroup(PanelMenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataPegawaiLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(PanelMenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimePegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DatePegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(PanelMenuDataPegawaiLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel26.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Data Pegawai Peternakan Burung Puyuh");

        TblDataPegawai.setAutoCreateRowSorter(true);
        TblDataPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pegawai", "Nama Pegawai", "Asal", "Tanggal Lahir", "Jenis Kelamin", "No Telepon", "Alamat Kandang"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDataPegawai.setRowHeight(28);
        jScrollPane3.setViewportView(TblDataPegawai);

        javax.swing.GroupLayout MenuDataPegawaiLayout = new javax.swing.GroupLayout(MenuDataPegawai);
        MenuDataPegawai.setLayout(MenuDataPegawaiLayout);
        MenuDataPegawaiLayout.setHorizontalGroup(
            MenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        MenuDataPegawaiLayout.setVerticalGroup(
            MenuDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataPegawaiLayout.createSequentialGroup()
                .addComponent(PanelMenuDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuPegawai.add(MenuDataPegawai, "card2");

        MenuInputPegawai.setBackground(new java.awt.Color(255, 255, 255));

        PanelMenuInputPegawai.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataPegawai.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataPegawaiMouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel46.setText("Kembali");

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliDataPegawaiLayout = new javax.swing.GroupLayout(KembaliDataPegawai);
        KembaliDataPegawai.setLayout(KembaliDataPegawaiLayout);
        KembaliDataPegawaiLayout.setHorizontalGroup(
            KembaliDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPegawaiLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        KembaliDataPegawaiLayout.setVerticalGroup(
            KembaliDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPegawaiLayout.createSequentialGroup()
                .addGroup(KembaliDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel31.setText("Nama Pegawai");

        jLabel27.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel27.setText("ID Pegawai");

        jLabel38.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel38.setText("Asal");

        jLabel39.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel39.setText("Tanggal Lahir");

        jLabel40.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel40.setText("Jenis Kelamin");

        jLabel41.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel41.setText("No Telepon");

        jLabel48.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel48.setText("Alamat");

        TxtIdPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtIdPegawai.setText("jTextField1");
        TxtIdPegawai.setToolTipText("");

        TxtNamaPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtNamaPegawai.setText("jTextField1");

        TxtAsal.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtAsal.setText("jTextField1");

        JdtTglLahir.setDateFormatString("dd MMMM yyyy");
        JdtTglLahir.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

        buttonGroup1.add(RbLakiLaki);
        RbLakiLaki.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        RbLakiLaki.setText("Laki-Laki");

        buttonGroup1.add(RbPerempuan);
        RbPerempuan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        RbPerempuan.setText("Perempuan");

        TxtNoTelp.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtNoTelp.setText("jTextField4");
        TxtNoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtNoTelpKeyTyped(evt);
            }
        });

        JtxAlamat.setColumns(20);
        JtxAlamat.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        JtxAlamat.setLineWrap(true);
        JtxAlamat.setRows(5);
        jScrollPane4.setViewportView(JtxAlamat);

        BtnSimpanPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanPegawai.setText("Simpan");
        BtnSimpanPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPegawaiActionPerformed(evt);
            }
        });

        BtnHapusPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusPegawai.setText("Hapus");
        BtnHapusPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPegawaiActionPerformed(evt);
            }
        });

        BtnBatalPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalPegawai.setText("Batal");
        BtnBatalPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalPegawaiActionPerformed(evt);
            }
        });

        BtnUpdatePegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdatePegawai.setText("Update");
        BtnUpdatePegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdatePegawaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNoTelp))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JdtTglLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RbLakiLaki)
                                .addGap(18, 18, 18)
                                .addComponent(RbPerempuan))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnSimpanPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnUpdatePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnHapusPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBatalPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TxtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(TxtAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JdtTglLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(RbLakiLaki)
                    .addComponent(RbPerempuan))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(TxtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpanPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBatalPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdatePegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel34.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Input Data Pegawai Peternakan Burung Puyuh");

        TblInputDataPegawai.setAutoCreateRowSorter(true);
        TblInputDataPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pegawai", "Nama Pegawai", "Asal", "Tanggal Lahir", "Jenis Kelamin", "No Telepon", "Alamat Kandang"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblInputDataPegawai.setRowHeight(32);
        TblInputDataPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataPegawaiMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(TblInputDataPegawai);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelMenuInputPegawaiLayout = new javax.swing.GroupLayout(PanelMenuInputPegawai);
        PanelMenuInputPegawai.setLayout(PanelMenuInputPegawaiLayout);
        PanelMenuInputPegawaiLayout.setHorizontalGroup(
            PanelMenuInputPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputPegawaiLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelMenuInputPegawaiLayout.setVerticalGroup(
            PanelMenuInputPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuInputPegawaiLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MenuInputPegawaiLayout = new javax.swing.GroupLayout(MenuInputPegawai);
        MenuInputPegawai.setLayout(MenuInputPegawaiLayout);
        MenuInputPegawaiLayout.setHorizontalGroup(
            MenuInputPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputPegawai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputPegawaiLayout.setVerticalGroup(
            MenuInputPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuPegawai.add(MenuInputPegawai, "card3");

        MenuUtama.add(MenuPegawai, "card4");

        MenuPakan.setLayout(new java.awt.CardLayout());

        PanelMenuDataPakan.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataPakan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataPakan.setText("Tambah Data");
        BtnInputDataPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataPakanActionPerformed(evt);
            }
        });

        TimePakan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimePakan.setText("Time");

        DatePakan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DatePakan.setText("Date");

        KembaliMenuUtama3.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtama3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtama3MouseClicked(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel50.setText("Kembali");

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtama3Layout = new javax.swing.GroupLayout(KembaliMenuUtama3);
        KembaliMenuUtama3.setLayout(KembaliMenuUtama3Layout);
        KembaliMenuUtama3Layout.setHorizontalGroup(
            KembaliMenuUtama3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtama3Layout.setVerticalGroup(
            KembaliMenuUtama3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama3Layout.createSequentialGroup()
                .addGroup(KembaliMenuUtama3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataPakanLayout = new javax.swing.GroupLayout(PanelMenuDataPakan);
        PanelMenuDataPakan.setLayout(PanelMenuDataPakanLayout);
        PanelMenuDataPakanLayout.setHorizontalGroup(
            PanelMenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataPakanLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(DatePakan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TimePakan, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(BtnInputDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelMenuDataPakanLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliMenuUtama3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataPakanLayout.setVerticalGroup(
            PanelMenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataPakanLayout.createSequentialGroup()
                .addGroup(PanelMenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataPakanLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtama3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(PanelMenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimePakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DatePakan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(PanelMenuDataPakanLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel52.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Data Pakan Peternakan Burung Puyuh");

        TblDataPakan.setAutoCreateRowSorter(true);
        TblDataPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataPakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pakan", "Nama Pakan", "Harga", "Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDataPakan.setRowHeight(28);
        jScrollPane5.setViewportView(TblDataPakan);

        javax.swing.GroupLayout MenuDataPakanLayout = new javax.swing.GroupLayout(MenuDataPakan);
        MenuDataPakan.setLayout(MenuDataPakanLayout);
        MenuDataPakanLayout.setHorizontalGroup(
            MenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataPakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuDataPakanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        MenuDataPakanLayout.setVerticalGroup(
            MenuDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataPakanLayout.createSequentialGroup()
                .addComponent(PanelMenuDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuPakan.add(MenuDataPakan, "card2");

        PanelMenuInputPakan.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataPakan.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataPakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataPakanMouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel35.setText("Kembali");

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliDataPakanLayout = new javax.swing.GroupLayout(KembaliDataPakan);
        KembaliDataPakan.setLayout(KembaliDataPakanLayout);
        KembaliDataPakanLayout.setHorizontalGroup(
            KembaliDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPakanLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addContainerGap())
        );
        KembaliDataPakanLayout.setVerticalGroup(
            KembaliDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPakanLayout.createSequentialGroup()
                .addGroup(KembaliDataPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuInputPakanLayout = new javax.swing.GroupLayout(PanelMenuInputPakan);
        PanelMenuInputPakan.setLayout(PanelMenuInputPakanLayout);
        PanelMenuInputPakanLayout.setHorizontalGroup(
            PanelMenuInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputPakanLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuInputPakanLayout.setVerticalGroup(
            PanelMenuInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputPakanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TxtHarga.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtHarga.setText("jTextField1");
        TxtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtHargaKeyTyped(evt);
            }
        });

        TxtStok.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtStok.setText("jTextField1");
        TxtStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtStokKeyTyped(evt);
            }
        });

        TxtNamaPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtNamaPakan.setText("jTextField2");

        TxtIdPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtIdPakan.setText("jTextField1");

        jLabel54.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel54.setText("ID Pakan");

        jLabel55.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel55.setText("Nama Pakan");

        jLabel56.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel56.setText("Harga");

        jLabel57.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel57.setText("Stok");

        BtnSimpanPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanPakan.setText("Simpan");
        BtnSimpanPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPakanActionPerformed(evt);
            }
        });

        BtnHapusPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusPakan.setText("Hapus");
        BtnHapusPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPakanActionPerformed(evt);
            }
        });

        BtnBatalPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalPakan.setText("Batal");
        BtnBatalPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalPakanActionPerformed(evt);
            }
        });

        BtnUpdatePakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdatePakan.setText("Update");
        BtnUpdatePakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdatePakanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInputDataKandang1Layout = new javax.swing.GroupLayout(PanelInputDataKandang1);
        PanelInputDataKandang1.setLayout(PanelInputDataKandang1Layout);
        PanelInputDataKandang1Layout.setHorizontalGroup(
            PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKandang1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputDataKandang1Layout.createSequentialGroup()
                        .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtIdPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNamaPakan, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(TxtStok)
                            .addComponent(TxtHarga))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelInputDataKandang1Layout.createSequentialGroup()
                        .addComponent(BtnSimpanPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnUpdatePakan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(BtnHapusPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBatalPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))))
        );
        PanelInputDataKandang1Layout.setVerticalGroup(
            PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKandang1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNamaPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(PanelInputDataKandang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpanPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBatalPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdatePakan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        TblInputDataPakan.setAutoCreateRowSorter(true);
        TblInputDataPakan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataPakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pakan", "Nama Pakan", "Harga", "Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblInputDataPakan.setRowHeight(28);
        TblInputDataPakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataPakanMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(TblInputDataPakan);

        jLabel36.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Input Pakan Peternakan Burung Puyuh");

        javax.swing.GroupLayout PanelInputPakanLayout = new javax.swing.GroupLayout(PanelInputPakan);
        PanelInputPakan.setLayout(PanelInputPakanLayout);
        PanelInputPakanLayout.setHorizontalGroup(
            PanelInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputPakanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputDataKandang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13)
                .addContainerGap())
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
        );
        PanelInputPakanLayout.setVerticalGroup(
            PanelInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputPakanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addGroup(PanelInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInputDataKandang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuInputPakanLayout = new javax.swing.GroupLayout(MenuInputPakan);
        MenuInputPakan.setLayout(MenuInputPakanLayout);
        MenuInputPakanLayout.setHorizontalGroup(
            MenuInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputPakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelInputPakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputPakanLayout.setVerticalGroup(
            MenuInputPakanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuInputPakanLayout.createSequentialGroup()
                .addComponent(PanelMenuInputPakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInputPakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuPakan.add(MenuInputPakan, "card3");

        MenuUtama.add(MenuPakan, "card5");

        MenuPenyakit.setLayout(new java.awt.CardLayout());

        PanelMenuDataPenyakit.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataPenyakit.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataPenyakit.setText("Tambah Data");
        BtnInputDataPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataPenyakitActionPerformed(evt);
            }
        });

        TimePenyakit.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimePenyakit.setText("Time");

        DatePenyakit.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DatePenyakit.setText("Date");

        KembaliMenuUtama4.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtama4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtama4MouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel58.setText("Kembali");

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtama4Layout = new javax.swing.GroupLayout(KembaliMenuUtama4);
        KembaliMenuUtama4.setLayout(KembaliMenuUtama4Layout);
        KembaliMenuUtama4Layout.setHorizontalGroup(
            KembaliMenuUtama4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtama4Layout.setVerticalGroup(
            KembaliMenuUtama4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtama4Layout.createSequentialGroup()
                .addGroup(KembaliMenuUtama4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataPenyakitLayout = new javax.swing.GroupLayout(PanelMenuDataPenyakit);
        PanelMenuDataPenyakit.setLayout(PanelMenuDataPenyakitLayout);
        PanelMenuDataPenyakitLayout.setHorizontalGroup(
            PanelMenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataPenyakitLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(DatePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TimePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(BtnInputDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelMenuDataPenyakitLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliMenuUtama4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataPenyakitLayout.setVerticalGroup(
            PanelMenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataPenyakitLayout.createSequentialGroup()
                .addGroup(PanelMenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataPenyakitLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtama4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(PanelMenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimePenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DatePenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(PanelMenuDataPenyakitLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel60.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Data Penyakit Peternakan Burung Puyuh");

        TblDataPenyakit.setAutoCreateRowSorter(true);
        TblDataPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataPenyakit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Penyakit", "Gejala", "Penularan", "Pencegahan", "Pengobatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDataPenyakit.setRowHeight(28);
        TblDataPenyakit.setRowMargin(10);
        jScrollPane6.setViewportView(TblDataPenyakit);

        javax.swing.GroupLayout MenuDataPenyakitLayout = new javax.swing.GroupLayout(MenuDataPenyakit);
        MenuDataPenyakit.setLayout(MenuDataPenyakitLayout);
        MenuDataPenyakitLayout.setHorizontalGroup(
            MenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuDataPenyakitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        MenuDataPenyakitLayout.setVerticalGroup(
            MenuDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataPenyakitLayout.createSequentialGroup()
                .addComponent(PanelMenuDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel60)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuPenyakit.add(MenuDataPenyakit, "card2");

        MenuInputPenyakit.setBackground(new java.awt.Color(255, 255, 255));

        PanelMenuInputPenyakit.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataPenyakit.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataPenyakit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataPenyakitMouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel37.setText("Kembali");

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliDataPenyakitLayout = new javax.swing.GroupLayout(KembaliDataPenyakit);
        KembaliDataPenyakit.setLayout(KembaliDataPenyakitLayout);
        KembaliDataPenyakitLayout.setHorizontalGroup(
            KembaliDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPenyakitLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addContainerGap())
        );
        KembaliDataPenyakitLayout.setVerticalGroup(
            KembaliDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataPenyakitLayout.createSequentialGroup()
                .addGroup(KembaliDataPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuInputPenyakitLayout = new javax.swing.GroupLayout(PanelMenuInputPenyakit);
        PanelMenuInputPenyakit.setLayout(PanelMenuInputPenyakitLayout);
        PanelMenuInputPenyakitLayout.setHorizontalGroup(
            PanelMenuInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputPenyakitLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuInputPenyakitLayout.setVerticalGroup(
            PanelMenuInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputPenyakitLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TxtNamaPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtNamaPenyakit.setText("jTextField1");

        jLabel62.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel62.setText("Nama Penyakit");

        jLabel63.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel63.setText("Gejala");

        BtnSimpanPeenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanPeenyakit.setText("Simpan");
        BtnSimpanPeenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPeenyakitActionPerformed(evt);
            }
        });

        BtnHapusPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusPenyakit.setText("Hapus");
        BtnHapusPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPenyakitActionPerformed(evt);
            }
        });

        BtnBatalPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalPenyakit.setText("Batal");
        BtnBatalPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalPenyakitActionPerformed(evt);
            }
        });

        JtxGejala.setColumns(20);
        JtxGejala.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        JtxGejala.setLineWrap(true);
        JtxGejala.setRows(5);
        jScrollPane7.setViewportView(JtxGejala);

        BtnUpdatePenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdatePenyakit.setText("Update");
        BtnUpdatePenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdatePenyakitActionPerformed(evt);
            }
        });

        JtxPenularan.setColumns(20);
        JtxPenularan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        JtxPenularan.setLineWrap(true);
        JtxPenularan.setRows(5);
        jScrollPane17.setViewportView(JtxPenularan);

        jLabel95.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel95.setText("Penularan");

        JtxPencegahan.setColumns(20);
        JtxPencegahan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        JtxPencegahan.setLineWrap(true);
        JtxPencegahan.setRows(5);
        jScrollPane18.setViewportView(JtxPencegahan);

        jLabel96.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel96.setText("Pencegahan");

        JtxPengobatan.setColumns(20);
        JtxPengobatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        JtxPengobatan.setLineWrap(true);
        JtxPengobatan.setRows(5);
        jScrollPane19.setViewportView(JtxPengobatan);

        jLabel97.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel97.setText("Pengobatan");

        javax.swing.GroupLayout PanelInputDataPenyakiLayout = new javax.swing.GroupLayout(PanelInputDataPenyaki);
        PanelInputDataPenyaki.setLayout(PanelInputDataPenyakiLayout);
        PanelInputDataPenyakiLayout.setHorizontalGroup(
            PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                        .addComponent(BtnSimpanPeenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnUpdatePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnHapusPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(BtnBatalPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelInputDataPenyakiLayout.createSequentialGroup()
                                    .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                        .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7)
                            .addComponent(TxtNamaPenyakit))))
                .addContainerGap())
        );

        PanelInputDataPenyakiLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane17, jScrollPane18, jScrollPane19, jScrollPane7});

        PanelInputDataPenyakiLayout.setVerticalGroup(
            PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataPenyakiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNamaPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBatalPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelInputDataPenyakiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnSimpanPeenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnUpdatePenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        PanelInputDataPenyakiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane17, jScrollPane18, jScrollPane19, jScrollPane7});

        TblInputDataPenyakit.setAutoCreateRowSorter(true);
        TblInputDataPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataPenyakit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Penyakit", "Gejala", "Penularan", "Pencegahan", "Pengobatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblInputDataPenyakit.setRowHeight(28);
        TblInputDataPenyakit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataPenyakitMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(TblInputDataPenyakit);

        jLabel43.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Input Penyakit Kandang Peternakan Burung Puyuh");

        javax.swing.GroupLayout PanelInputPenyakitLayout = new javax.swing.GroupLayout(PanelInputPenyakit);
        PanelInputPenyakit.setLayout(PanelInputPenyakitLayout);
        PanelInputPenyakitLayout.setHorizontalGroup(
            PanelInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputPenyakitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputDataPenyaki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14)
                .addContainerGap())
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
        );
        PanelInputPenyakitLayout.setVerticalGroup(
            PanelInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputPenyakitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addGroup(PanelInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInputDataPenyaki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane14))
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuInputPenyakitLayout = new javax.swing.GroupLayout(MenuInputPenyakit);
        MenuInputPenyakit.setLayout(MenuInputPenyakitLayout);
        MenuInputPenyakitLayout.setHorizontalGroup(
            MenuInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelInputPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputPenyakitLayout.setVerticalGroup(
            MenuInputPenyakitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuInputPenyakitLayout.createSequentialGroup()
                .addComponent(PanelMenuInputPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelInputPenyakit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuPenyakit.add(MenuInputPenyakit, "card3");

        MenuUtama.add(MenuPenyakit, "card8");

        MenuKesehatan.setLayout(new java.awt.CardLayout());

        PanelMenuDataKesehatan.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataKesehatan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataKesehatan.setText("Tambah Data");
        BtnInputDataKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataKesehatanActionPerformed(evt);
            }
        });

        TimeKesehatan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimeKesehatan.setText("Time");

        DateKesehatan.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DateKesehatan.setText("Date");

        KembaliMenuUtamaKsht.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtamaKsht.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtamaKshtMouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel64.setText("Kembali");

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtamaKshtLayout = new javax.swing.GroupLayout(KembaliMenuUtamaKsht);
        KembaliMenuUtamaKsht.setLayout(KembaliMenuUtamaKshtLayout);
        KembaliMenuUtamaKshtLayout.setHorizontalGroup(
            KembaliMenuUtamaKshtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaKshtLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtamaKshtLayout.setVerticalGroup(
            KembaliMenuUtamaKshtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaKshtLayout.createSequentialGroup()
                .addGroup(KembaliMenuUtamaKshtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataKesehatanLayout = new javax.swing.GroupLayout(PanelMenuDataKesehatan);
        PanelMenuDataKesehatan.setLayout(PanelMenuDataKesehatanLayout);
        PanelMenuDataKesehatanLayout.setHorizontalGroup(
            PanelMenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataKesehatanLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(DateKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TimeKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                .addComponent(BtnInputDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelMenuDataKesehatanLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliMenuUtamaKsht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataKesehatanLayout.setVerticalGroup(
            PanelMenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataKesehatanLayout.createSequentialGroup()
                .addGroup(PanelMenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataKesehatanLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtamaKsht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(PanelMenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimeKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(PanelMenuDataKesehatanLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        TblDataKesehatan.setAutoCreateRowSorter(true);
        TblDataKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataKesehatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Kesehatan", "Nama Kandang", "Nama Penyakit", "ID Pegawai", "Jumlah Sakit", "Jumlah Mati", "Tanggal Cek"
            }
        ));
        TblDataKesehatan.setRowHeight(28);
        jScrollPane8.setViewportView(TblDataKesehatan);

        jLabel66.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Data Kesehatan Peternakan Burung Puyuh");

        javax.swing.GroupLayout MenuDataKesehatanLayout = new javax.swing.GroupLayout(MenuDataKesehatan);
        MenuDataKesehatan.setLayout(MenuDataKesehatanLayout);
        MenuDataKesehatanLayout.setHorizontalGroup(
            MenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuDataKesehatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        MenuDataKesehatanLayout.setVerticalGroup(
            MenuDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataKesehatanLayout.createSequentialGroup()
                .addComponent(PanelMenuDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel66)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuKesehatan.add(MenuDataKesehatan, "card2");

        PanelMenuInputKesehatan.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataKesehatan.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataKesehatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataKesehatanMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel49.setText("Kembali");

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliDataKesehatanLayout = new javax.swing.GroupLayout(KembaliDataKesehatan);
        KembaliDataKesehatan.setLayout(KembaliDataKesehatanLayout);
        KembaliDataKesehatanLayout.setHorizontalGroup(
            KembaliDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataKesehatanLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addContainerGap())
        );
        KembaliDataKesehatanLayout.setVerticalGroup(
            KembaliDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataKesehatanLayout.createSequentialGroup()
                .addGroup(KembaliDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuInputKesehatanLayout = new javax.swing.GroupLayout(PanelMenuInputKesehatan);
        PanelMenuInputKesehatan.setLayout(PanelMenuInputKesehatanLayout);
        PanelMenuInputKesehatanLayout.setHorizontalGroup(
            PanelMenuInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputKesehatanLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuInputKesehatanLayout.setVerticalGroup(
            PanelMenuInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputKesehatanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel68.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel68.setText("ID Kesehatan");

        jLabel69.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel69.setText("Nama Kandang");

        jLabel70.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel70.setText("Nama Penyakit");

        jLabel71.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel71.setText("ID Pegawai");

        jLabel72.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel72.setText("Jumlah Sakit");

        jLabel73.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel73.setText("Jumlah Mati");

        TxtIdKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtIdKesehatan.setText("jTextField1");

        TxtJmlSakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtJmlSakit.setText("jTextField1");
        TxtJmlSakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtJmlSakitKeyTyped(evt);
            }
        });

        TxtJmlMati.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtJmlMati.setText("jTextField1");
        TxtJmlMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtJmlMatiKeyTyped(evt);
            }
        });

        CboKandang.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboKandang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CboIdPegawai.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboIdPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CboPenyakit.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboPenyakit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        BtnSimpanKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanKesehatan.setText("Simpan");
        BtnSimpanKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanKesehatanActionPerformed(evt);
            }
        });

        BtnHapusKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusKesehatan.setText("Hapus");
        BtnHapusKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKesehatanActionPerformed(evt);
            }
        });

        BtnBatalKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalKesehatan.setText("Batal");
        BtnBatalKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalKesehatanActionPerformed(evt);
            }
        });

        BtnUpdateKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdateKesehatan.setText("Update");
        BtnUpdateKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateKesehatanActionPerformed(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel100.setText("Tanggal Cek");

        TxtTglCekKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtTglCekKesehatan.setText("jTextField1");
        TxtTglCekKesehatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTglCekKesehatanKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelInputDataKesehatanLayout = new javax.swing.GroupLayout(PanelInputDataKesehatan);
        PanelInputDataKesehatan.setLayout(PanelInputDataKesehatanLayout);
        PanelInputDataKesehatanLayout.setHorizontalGroup(
            PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                        .addComponent(BtnSimpanKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnUpdateKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(BtnHapusKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBatalKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                        .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtJmlSakit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtJmlMati, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CboKandang, 0, 287, Short.MAX_VALUE)
                                    .addComponent(CboPenyakit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CboIdPegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtIdKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtTglCekKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelInputDataKesehatanLayout.setVerticalGroup(
            PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKesehatanLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CboIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtJmlSakit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtJmlMati, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTglCekKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpanKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBatalKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdateKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TblInputDataKesehatan.setAutoCreateRowSorter(true);
        TblInputDataKesehatan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataKesehatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Kesehatan", "Nama Kandang", "Nama Penyakit", "ID Pegawai", "Jumlah Sakit", "Jumlah Mati", "Tanggal Cek"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblInputDataKesehatan.setRowHeight(28);
        TblInputDataKesehatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataKesehatanMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(TblInputDataKesehatan);

        jLabel99.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("Data Kesehatan Peternakan Burung Puyuh");

        javax.swing.GroupLayout PanelInputKesehatanLayout = new javax.swing.GroupLayout(PanelInputKesehatan);
        PanelInputKesehatan.setLayout(PanelInputKesehatanLayout);
        PanelInputKesehatanLayout.setHorizontalGroup(
            PanelInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputKesehatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelInputKesehatanLayout.setVerticalGroup(
            PanelInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputKesehatanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99)
                .addGap(18, 18, 18)
                .addGroup(PanelInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInputDataKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuInputKesehatanLayout = new javax.swing.GroupLayout(MenuInputKesehatan);
        MenuInputKesehatan.setLayout(MenuInputKesehatanLayout);
        MenuInputKesehatanLayout.setHorizontalGroup(
            MenuInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelInputKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputKesehatanLayout.setVerticalGroup(
            MenuInputKesehatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuInputKesehatanLayout.createSequentialGroup()
                .addComponent(PanelMenuInputKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInputKesehatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuKesehatan.add(MenuInputKesehatan, "card3");

        MenuUtama.add(MenuKesehatan, "card6");

        MenuCekTernak.setLayout(new java.awt.CardLayout());

        PanelMenuDataKesehatan1.setBackground(new java.awt.Color(255, 255, 255));

        BtnInputDataCekTernak.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        BtnInputDataCekTernak.setText("Tambah Data");
        BtnInputDataCekTernak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInputDataCekTernakActionPerformed(evt);
            }
        });

        TimeCekTernak.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimeCekTernak.setText("Time");

        DateCekTernak.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DateCekTernak.setText("Date");

        KembaliMenuUtamaCek.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtamaCek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtamaCekMouseClicked(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel74.setText("Kembali");

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtamaCekLayout = new javax.swing.GroupLayout(KembaliMenuUtamaCek);
        KembaliMenuUtamaCek.setLayout(KembaliMenuUtamaCekLayout);
        KembaliMenuUtamaCekLayout.setHorizontalGroup(
            KembaliMenuUtamaCekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaCekLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtamaCekLayout.setVerticalGroup(
            KembaliMenuUtamaCekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaCekLayout.createSequentialGroup()
                .addGroup(KembaliMenuUtamaCekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataKesehatan1Layout = new javax.swing.GroupLayout(PanelMenuDataKesehatan1);
        PanelMenuDataKesehatan1.setLayout(PanelMenuDataKesehatan1Layout);
        PanelMenuDataKesehatan1Layout.setHorizontalGroup(
            PanelMenuDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataKesehatan1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(DateCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TimeCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(BtnInputDataCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelMenuDataKesehatan1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliMenuUtamaCek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataKesehatan1Layout.setVerticalGroup(
            PanelMenuDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataKesehatan1Layout.createSequentialGroup()
                .addGroup(PanelMenuDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMenuDataKesehatan1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(KembaliMenuUtamaCek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(PanelMenuDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimeCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(PanelMenuDataKesehatan1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnInputDataCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel76.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Data Cek Ternak Peternakan Burung Puyuh");

        TblDataCekTernak.setAutoCreateRowSorter(true);
        TblDataCekTernak.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataCekTernak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Cek Ternak", "Nama Kandang", "ID Pegawai", "ID Pakan", "Jumlah Telur", "Kebersihan", "Tanggal Cek"
            }
        ));
        TblDataCekTernak.setRowHeight(28);
        jScrollPane9.setViewportView(TblDataCekTernak);

        javax.swing.GroupLayout MenuDataCekTernakLayout = new javax.swing.GroupLayout(MenuDataCekTernak);
        MenuDataCekTernak.setLayout(MenuDataCekTernakLayout);
        MenuDataCekTernakLayout.setHorizontalGroup(
            MenuDataCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataKesehatan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuDataCekTernakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        MenuDataCekTernakLayout.setVerticalGroup(
            MenuDataCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataCekTernakLayout.createSequentialGroup()
                .addComponent(PanelMenuDataKesehatan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuCekTernak.add(MenuDataCekTernak, "card2");

        PanelMenuInputCekTernak.setBackground(new java.awt.Color(255, 255, 255));

        KembaliDataCekTernak.setBackground(new java.awt.Color(255, 255, 255));
        KembaliDataCekTernak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliDataCekTernakMouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel77.setText("Kembali");

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliDataCekTernakLayout = new javax.swing.GroupLayout(KembaliDataCekTernak);
        KembaliDataCekTernak.setLayout(KembaliDataCekTernakLayout);
        KembaliDataCekTernakLayout.setHorizontalGroup(
            KembaliDataCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataCekTernakLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel77)
                .addContainerGap())
        );
        KembaliDataCekTernakLayout.setVerticalGroup(
            KembaliDataCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliDataCekTernakLayout.createSequentialGroup()
                .addGroup(KembaliDataCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuInputCekTernakLayout = new javax.swing.GroupLayout(PanelMenuInputCekTernak);
        PanelMenuInputCekTernak.setLayout(PanelMenuInputCekTernakLayout);
        PanelMenuInputCekTernakLayout.setHorizontalGroup(
            PanelMenuInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputCekTernakLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(KembaliDataCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(955, Short.MAX_VALUE))
        );
        PanelMenuInputCekTernakLayout.setVerticalGroup(
            PanelMenuInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuInputCekTernakLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliDataCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TblInputDataCekTernak.setAutoCreateRowSorter(true);
        TblInputDataCekTernak.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblInputDataCekTernak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Cek Ternak", "Nama Kandang", "ID Pegawai", "ID Pakan", "Jumlah Telur", "Kebersihan", "Tanggal Cek"
            }
        ));
        TblInputDataCekTernak.setRowHeight(28);
        TblInputDataCekTernak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblInputDataCekTernakMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(TblInputDataCekTernak);

        TxtIdCekTernak.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtIdCekTernak.setText("jTextField1");

        jLabel79.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel79.setText("ID Cek Ternak ");

        jLabel80.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel80.setText("Nama Kandang");

        CboKandangCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboKandangCek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel81.setText("ID Pakan");

        jLabel82.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel82.setText("ID Pegawai");

        CboIdPegawaiCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboIdPegawaiCek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CboIdPakanCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboIdPakanCek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TxtTglCek.setEditable(false);
        TxtTglCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtTglCek.setText("jTextField1");

        CboKebersihan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboKebersihan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TxtJmlTelur.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtJmlTelur.setText("jTextField1");
        TxtJmlTelur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtJmlTelurKeyTyped(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel83.setText("Jumlah Telur");

        jLabel84.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel84.setText("Kebersihan");

        jLabel85.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel85.setText("Tanggal Cek Ternak");

        BtnSimpanCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpanCek.setText("Simpan");
        BtnSimpanCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanCekActionPerformed(evt);
            }
        });

        BtnUpdateCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnUpdateCek.setText("Update");
        BtnUpdateCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateCekActionPerformed(evt);
            }
        });

        BtnHapusCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapusCek.setText("Hapus");
        BtnHapusCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusCekActionPerformed(evt);
            }
        });

        BtnBatalCek.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnBatalCek.setText("Batal");
        BtnBatalCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalCekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtJmlTelur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CboKebersihan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(TxtTglCek, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtIdCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CboIdPakanCek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CboKandangCek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CboIdPegawaiCek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BtnSimpanCek, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(BtnUpdateCek, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnHapusCek, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBatalCek, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtIdCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboKandangCek, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboIdPakanCek, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboIdPegawaiCek, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtJmlTelur, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboKebersihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTglCek, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpanCek, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapusCek, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBatalCek, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdateCek, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel98.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("Data Cek Ternak Peternakan Burung Puyuh");

        javax.swing.GroupLayout PanelInputDataKesehatan1Layout = new javax.swing.GroupLayout(PanelInputDataKesehatan1);
        PanelInputDataKesehatan1.setLayout(PanelInputDataKesehatan1Layout);
        PanelInputDataKesehatan1Layout.setHorizontalGroup(
            PanelInputDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputDataKesehatan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputDataKesehatan1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputDataKesehatan1Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        PanelInputDataKesehatan1Layout.setVerticalGroup(
            PanelInputDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputDataKesehatan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98)
                .addGap(18, 18, 18)
                .addGroup(PanelInputDataKesehatan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane16))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelInputCekTernakLayout = new javax.swing.GroupLayout(PanelInputCekTernak);
        PanelInputCekTernak.setLayout(PanelInputCekTernakLayout);
        PanelInputCekTernakLayout.setHorizontalGroup(
            PanelInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputCekTernakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputDataKesehatan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInputCekTernakLayout.setVerticalGroup(
            PanelInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputCekTernakLayout.createSequentialGroup()
                .addComponent(PanelInputDataKesehatan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout MenuInputCekTernakLayout = new javax.swing.GroupLayout(MenuInputCekTernak);
        MenuInputCekTernak.setLayout(MenuInputCekTernakLayout);
        MenuInputCekTernakLayout.setHorizontalGroup(
            MenuInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuInputCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelInputCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuInputCekTernakLayout.setVerticalGroup(
            MenuInputCekTernakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuInputCekTernakLayout.createSequentialGroup()
                .addComponent(PanelMenuInputCekTernak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInputCekTernak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuCekTernak.add(MenuInputCekTernak, "card3");

        MenuUtama.add(MenuCekTernak, "card7");

        TblDataUser.setAutoCreateRowSorter(true);
        TblDataUser.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TblDataUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID User", "Username", "Password", "Nama User", "Hak Akses"
            }
        ));
        TblDataUser.setRowHeight(28);
        TblDataUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDataUserMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(TblDataUser);

        PanelMenuDataUser.setBackground(new java.awt.Color(255, 255, 255));

        TimeUser.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        TimeUser.setText("Time");

        DateUser.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        DateUser.setText("Date");

        KembaliMenuUtamaUser.setBackground(new java.awt.Color(255, 255, 255));
        KembaliMenuUtamaUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMenuUtamaUserMouseClicked(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        jLabel86.setText("Kembali");

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imange/IconBack.png"))); // NOI18N

        javax.swing.GroupLayout KembaliMenuUtamaUserLayout = new javax.swing.GroupLayout(KembaliMenuUtamaUser);
        KembaliMenuUtamaUser.setLayout(KembaliMenuUtamaUserLayout);
        KembaliMenuUtamaUserLayout.setHorizontalGroup(
            KembaliMenuUtamaUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaUserLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KembaliMenuUtamaUserLayout.setVerticalGroup(
            KembaliMenuUtamaUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KembaliMenuUtamaUserLayout.createSequentialGroup()
                .addGroup(KembaliMenuUtamaUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuDataUserLayout = new javax.swing.GroupLayout(PanelMenuDataUser);
        PanelMenuDataUser.setLayout(PanelMenuDataUserLayout);
        PanelMenuDataUserLayout.setHorizontalGroup(
            PanelMenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuDataUserLayout.createSequentialGroup()
                .addGroup(PanelMenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMenuDataUserLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(DateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TimeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelMenuDataUserLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(KembaliMenuUtamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMenuDataUserLayout.setVerticalGroup(
            PanelMenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuDataUserLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(KembaliMenuUtamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelMenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DateUser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel88.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Data User Peternakan Burung Puyuh");

        jLabel89.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel89.setText("ID User");

        jLabel90.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel90.setText("Username");

        jLabel91.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel91.setText("Password");

        jLabel92.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel92.setText("Ulangi Password");

        jLabel93.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel93.setText("Nama User");

        jLabel94.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel94.setText("Hak Akses");

        TxtIdUser.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtIdUser.setText("jTextField1");

        TxtDataUsername.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtDataUsername.setText("jTextField1");

        TxtDataNama.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        TxtDataNama.setText("jTextField1");

        JpsDataPassword.setText("jPasswordField1");

        CboAkses.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        CboAkses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Administrator", "Pegawai" }));

        JpsDataRePassword.setText("jPasswordField1");

        BtnSimpan.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnHapus.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnDelete.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        BtnDelete.setText("Batal");
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboAkses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JpsDataRePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JpsDataPassword))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDataUsername))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDataNama))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(BtnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(BtnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtIdUser, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDataUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(JpsDataPassword)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(JpsDataRePassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDataNama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MenuDataUserLayout = new javax.swing.GroupLayout(MenuDataUser);
        MenuDataUser.setLayout(MenuDataUserLayout);
        MenuDataUserLayout.setHorizontalGroup(
            MenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenuDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuDataUserLayout.setVerticalGroup(
            MenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDataUserLayout.createSequentialGroup()
                .addComponent(PanelMenuDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addGroup(MenuDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        MenuUtama.add(MenuDataUser, "card9");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(SubMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelUpPuyuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(MenuUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MenuUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelUpPuyuh))
            .addComponent(SubMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1298, 767));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    ////////////////////        Panel Utama
    
    private void MenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHomeMouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_MenuHomeMouseClicked

    private void MenuUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuUserMouseClicked
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(true);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_MenuUserMouseClicked

    private void MenuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuLogoutMouseClicked
        // TODO add your handling code here:
        int pesan = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Keluar", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pesan == JOptionPane.YES_NO_OPTION) {
            FormLogin formLogin = new FormLogin();
            formLogin.setVisible(true);
            dispose();
        } else {
            
        }
    }//GEN-LAST:event_MenuLogoutMouseClicked

    private void MenuLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuLaporanMouseClicked
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(true);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_MenuLaporanMouseClicked

    private void MenuJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuJadwalMouseClicked
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(true);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_MenuJadwalMouseClicked

    private void BtnDataKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataKandangActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(true);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataKandangActionPerformed

    private void BtnDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPegawaiActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(true);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataPegawaiActionPerformed

    private void BtnDataPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPakanActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(true);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataPakanActionPerformed

    private void BtnDataKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataKesehatanActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(true);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataKesehatanActionPerformed

    private void BtnDataCekTernakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataCekTernakActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(true);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataCekTernakActionPerformed

    private void BtnDataUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataUserActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(true);
    }//GEN-LAST:event_BtnDataUserActionPerformed

    private void BtnDataPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPenyakitActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(true);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_BtnDataPenyakitActionPerformed
    
    /////////////////           Notifikasi       
    
    private void NotifBtnSdhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifBtnSdhActionPerformed
        // TODO add your handling code here:
        Notifikasi1.setVisible(false);
    }//GEN-LAST:event_NotifBtnSdhActionPerformed

    private void NotifInputKshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifInputKshActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(true);
        MenuCekTernak.setVisible(false);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_NotifInputKshActionPerformed

    private void NotifBtnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifBtnCekActionPerformed
        // TODO add your handling code here:
        MenuAdmin.setVisible(false);
        TabTugas.setVisible(false);
        TabAkun.setVisible(false);
        TabJadwal.setVisible(false);
        TabLaporan.setVisible(false);
        MenuKandang.setVisible(false);
        MenuPegawai.setVisible(false);
        MenuPakan.setVisible(false);
        MenuPenyakit.setVisible(false);
        MenuKesehatan.setVisible(false);
        MenuCekTernak.setVisible(true);
        MenuDataUser.setVisible(false);
    }//GEN-LAST:event_NotifBtnCekActionPerformed

    /////////////////       Panel Kandang     /////////////////
    private void BtnInputDataKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataKandangActionPerformed
        // TODO add your handling code here:
        MenuDataKandang.setVisible(false);
        MenuInputKandang.setVisible(true);
    }//GEN-LAST:event_BtnInputDataKandangActionPerformed

    private void KembaliMenuUtama1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtama1MouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtama1MouseClicked

    private void BtnSimpanKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanKandangActionPerformed
        // TODO add your handling code here:
        if (TxtNamaKandang.getText().equals("") || TxtJmlTernak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Kandang dan Jumlah Ternak Masih Kosong,\nSilakan Isi Terlebih Dahulu","Pesan",JOptionPane.INFORMATION_MESSAGE);
        } else {
            controller.insertKandang();
            controller.clearFormKandang();
            controller.viewTableDataKandang();
            controller.viewTableInputKandang();
        }
        
    }//GEN-LAST:event_BtnSimpanKandangActionPerformed

    private void BtnUpdateKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateKandangActionPerformed
        // TODO add your handling code here:
        if (TxtNamaKandang.getText().equals("") || TxtJmlTernak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Kandang dan Jumlah Ternak Masih Kosong,\nSilakan Pilih Data Pada Tabel Kandang","Pesan",JOptionPane.INFORMATION_MESSAGE);
        } else {
            controller.updateKandang();
            controller.clearFormKandang();
            controller.viewTableDataKandang();
            controller.viewTableInputKandang();
        }
        
    }//GEN-LAST:event_BtnUpdateKandangActionPerformed

    private void BtnHapusKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKandangActionPerformed
        // TODO add your handling code here:
        if (TxtNamaKandang.getText().equals("") || TxtJmlTernak.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Silakan Pilih Data Pada Tabel Kandang Yang Akan DiHapus","Pesan",JOptionPane.INFORMATION_MESSAGE);
        } else {
            int pesan = JOptionPane.showConfirmDialog(null, "Apakah Andi Ingin Menghapus Data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pesan == JOptionPane.YES_NO_OPTION) {
                controller.deleteKandang();
                controller.clearFormKandang();
                controller.viewTableDataKandang();
                controller.viewTableInputKandang();
            }
        }
    }//GEN-LAST:event_BtnHapusKandangActionPerformed

    private void BtnBatalKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalKandangActionPerformed
        // TODO add your handling code here:
        controller.clearFormKandang();
    }//GEN-LAST:event_BtnBatalKandangActionPerformed

    private void TblInputDataKandangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataKandangMouseClicked
        // TODO add your handling code here:
        controller.onClickTabelKandang();
    }//GEN-LAST:event_TblInputDataKandangMouseClicked

    private void KembaliDataKandangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataKandangMouseClicked
        // TODO add your handling code here:
        MenuDataKandang.setVisible(true);
        MenuInputKandang.setVisible(false);
        controller.clearFormKandang();
    }//GEN-LAST:event_KembaliDataKandangMouseClicked

    ////////////////////////  Panel Pegawai
    private void BtnInputDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataPegawaiActionPerformed
        // TODO add your handling code here:
        MenuDataPegawai.setVisible(false);
        MenuInputPegawai.setVisible(true);
    }//GEN-LAST:event_BtnInputDataPegawaiActionPerformed

    private void KembaliMenuUtama2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtama2MouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtama2MouseClicked

    private void BtnSimpanPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPegawaiActionPerformed
        // TODO add your handling code here:
        if (TxtIdPegawai.getText().equals("") || JdtTglLahir.getDate().equals(null)) {
            JOptionPane.showMessageDialog(null, "Nama Kandang dan Jumlah Ternak Masih Kosong,\nSilakan Isi data terlebih dahulu","Pesan",JOptionPane.INFORMATION_MESSAGE);
        } else {
            controller.insertPegawai();
            controller.clearFormPegawai();
            controller.viewTableDataPegawai();
            controller.viewTableInputPegawai();
        }
    }//GEN-LAST:event_BtnSimpanPegawaiActionPerformed

    private void BtnUpdatePegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdatePegawaiActionPerformed
        // TODO add your handling code here:
        if (TxtNamaKandang.getText().equals("") || TxtJmlTernak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Kandang dan Jumlah Ternak Masih Kosong,\nSilakan Pilih Data Pada Tabel Kandang","Pesan",JOptionPane.INFORMATION_MESSAGE);
        } else {
            controller.updateKandang();
            controller.clearFormKandang();
            controller.viewTableDataKandang();
            controller.viewTableInputKandang();
        }
        controller.updatePegawai();
        controller.clearFormPegawai();
        controller.viewTableDataPegawai();
        controller.viewTableInputPegawai();
    }//GEN-LAST:event_BtnUpdatePegawaiActionPerformed

    private void BtnHapusPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPegawaiActionPerformed
        // TODO add your handling code here:
        controller.deletePegawai();
        controller.clearFormPegawai();
        controller.viewTableDataPegawai();
        controller.viewTableInputPegawai();
    }//GEN-LAST:event_BtnHapusPegawaiActionPerformed

    private void BtnBatalPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalPegawaiActionPerformed
        // TODO add your handling code here:
        controller.clearFormPegawai();
    }//GEN-LAST:event_BtnBatalPegawaiActionPerformed

    private void TblInputDataPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataPegawaiMouseClicked
        try {
            // TODO add your handling code here:
            controller.onClickTabelPegawai();
        } catch (ParseException ex) {
            Logger.getLogger(FormMenuPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TblInputDataPegawaiMouseClicked

    private void KembaliDataPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataPegawaiMouseClicked
        // TODO add your handling code here:
        MenuDataPegawai.setVisible(true);
        MenuInputPegawai.setVisible(false);
    }//GEN-LAST:event_KembaliDataPegawaiMouseClicked

    /////////////////// Panel Pakan
    private void BtnInputDataPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataPakanActionPerformed
        // TODO add your handling code here:
        MenuDataPakan.setVisible(false);
        MenuInputPakan.setVisible(true);
    }//GEN-LAST:event_BtnInputDataPakanActionPerformed

    private void KembaliMenuUtama3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtama3MouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtama3MouseClicked

    private void BtnSimpanPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPakanActionPerformed
        // TODO add your handling code here:
        controller.insertPakan();
        controller.clearFormPakan();
        controller.viewTableDataPakan();
        controller.viewTableInputPakan();
    }//GEN-LAST:event_BtnSimpanPakanActionPerformed

    private void BtnUpdatePakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdatePakanActionPerformed
        // TODO add your handling code here:
        controller.updatePakan();
        controller.clearFormPakan();
        controller.viewTableDataPakan();
        controller.viewTableInputPakan();
    }//GEN-LAST:event_BtnUpdatePakanActionPerformed

    private void BtnHapusPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPakanActionPerformed
        // TODO add your handling code here:
        controller.deletePakan();
        controller.clearFormPakan();
        controller.viewTableDataPakan();
        controller.viewTableInputPakan();
    }//GEN-LAST:event_BtnHapusPakanActionPerformed

    private void BtnBatalPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalPakanActionPerformed
        // TODO add your handling code here:
        controller.clearFormPakan();
    }//GEN-LAST:event_BtnBatalPakanActionPerformed

    private void TblInputDataPakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataPakanMouseClicked
        // TODO add your handling code here:
        controller.onClickTabelPakan();
    }//GEN-LAST:event_TblInputDataPakanMouseClicked

    private void KembaliDataPakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataPakanMouseClicked
        // TODO add your handling code here:
        MenuDataPakan.setVisible(true);
        MenuInputPakan.setVisible(false);
    }//GEN-LAST:event_KembaliDataPakanMouseClicked

    /////////////////       Panel Penyakit      
    private void KembaliMenuUtama4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtama4MouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtama4MouseClicked

    private void BtnInputDataPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataPenyakitActionPerformed
        // TODO add your handling code here:
        MenuDataPenyakit.setVisible(false);
        MenuInputPenyakit.setVisible(true);
    }//GEN-LAST:event_BtnInputDataPenyakitActionPerformed

    private void BtnSimpanPeenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPeenyakitActionPerformed
        // TODO add your handling code here:
        controller.insertPenyakit();
        controller.clearFormPenyakit();
        controller.viewTableDataPenyakit();
        controller.viewTableInputPenyakit();
    }//GEN-LAST:event_BtnSimpanPeenyakitActionPerformed

    private void BtnUpdatePenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdatePenyakitActionPerformed
        // TODO add your handling code here:
        controller.updatePenyakit();
        controller.clearFormPenyakit();
        controller.viewTableDataPenyakit();
        controller.viewTableInputPenyakit();
    }//GEN-LAST:event_BtnUpdatePenyakitActionPerformed

    private void BtnHapusPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPenyakitActionPerformed
        // TODO add your handling code here:
        controller.deletePenyakit();
        controller.clearFormPenyakit();
        controller.viewTableDataPenyakit();
        controller.viewTableInputPenyakit();
    }//GEN-LAST:event_BtnHapusPenyakitActionPerformed

    private void BtnBatalPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalPenyakitActionPerformed
        // TODO add your handling code here:
        controller.clearFormPenyakit();
    }//GEN-LAST:event_BtnBatalPenyakitActionPerformed

    private void TblInputDataPenyakitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataPenyakitMouseClicked
        // TODO add your handling code here:
        controller.onClickTabelPenyakit();
    }//GEN-LAST:event_TblInputDataPenyakitMouseClicked

    private void KembaliDataPenyakitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataPenyakitMouseClicked
        // TODO add your handling code here:
        MenuDataPenyakit.setVisible(true);
        MenuInputPenyakit.setVisible(false);
    }//GEN-LAST:event_KembaliDataPenyakitMouseClicked

    ///////////////////         Kesehatan           ////////////////////////////
    private void BtnInputDataKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataKesehatanActionPerformed
        // TODO add your handling code here:
        MenuDataKesehatan.setVisible(false);
        MenuInputKesehatan.setVisible(true);
    }//GEN-LAST:event_BtnInputDataKesehatanActionPerformed

    private void KembaliMenuUtamaKshtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtamaKshtMouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtamaKshtMouseClicked

    private void BtnSimpanKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanKesehatanActionPerformed
        // TODO add your handling code here:
        controller.insertKesehatan();
        controller.clearFormKesehatan();
        controller.viewTableDataKesehatan();
        controller.viewTableInputKesehatan();
        Notifikasi2.setVisible(false);
    }//GEN-LAST:event_BtnSimpanKesehatanActionPerformed

    private void BtnUpdateKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateKesehatanActionPerformed
        // TODO add your handling code here:
        controller.updateKesehatan();
        controller.clearFormKesehatan();
        controller.viewTableDataKesehatan();
        controller.viewTableInputKesehatan();
    }//GEN-LAST:event_BtnUpdateKesehatanActionPerformed

    private void BtnHapusKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKesehatanActionPerformed
        // TODO add your handling code here:
        controller.deleteKesehatan();
        controller.clearFormKesehatan();
        controller.viewTableDataKesehatan();
        controller.viewTableInputKesehatan();
    }//GEN-LAST:event_BtnHapusKesehatanActionPerformed

    private void BtnBatalKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalKesehatanActionPerformed
        // TODO add your handling code here:
        controller.clearFormKesehatan();
    }//GEN-LAST:event_BtnBatalKesehatanActionPerformed

    private void TblInputDataKesehatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataKesehatanMouseClicked
        // TODO add your handling code here:
        controller.onClickTabelKesehatan();
    }//GEN-LAST:event_TblInputDataKesehatanMouseClicked

    private void KembaliDataKesehatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataKesehatanMouseClicked
        // TODO add your handling code here:
        MenuDataKesehatan.setVisible(true);
        MenuInputKesehatan.setVisible(false);
    }//GEN-LAST:event_KembaliDataKesehatanMouseClicked

    private void KembaliMenuUtamaCekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtamaCekMouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtamaCekMouseClicked

    private void BtnInputDataCekTernakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInputDataCekTernakActionPerformed
        // TODO add your handling code here:
        MenuDataCekTernak.setVisible(false);
        MenuInputCekTernak.setVisible(true);
    }//GEN-LAST:event_BtnInputDataCekTernakActionPerformed

    private void BtnSimpanCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanCekActionPerformed
        // TODO add your handling code here:
        controller.insertCekTernak();
        controller.clearFormCekTernak();
        controller.viewTableDataCekTernak();
        controller.viewTableInputCekTernak();
    }//GEN-LAST:event_BtnSimpanCekActionPerformed

    private void BtnUpdateCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateCekActionPerformed
        // TODO add your handling code here:
        controller.updateCekTernak();
        controller.clearFormCekTernak();
        controller.viewTableDataCekTernak();
        controller.viewTableInputCekTernak();
    }//GEN-LAST:event_BtnUpdateCekActionPerformed

    private void BtnHapusCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusCekActionPerformed
        // TODO add your handling code here:
        controller.deleteCekTernak();
        controller.clearFormCekTernak();
        controller.viewTableDataCekTernak();
        controller.viewTableInputCekTernak();
    }//GEN-LAST:event_BtnHapusCekActionPerformed

    private void BtnBatalCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalCekActionPerformed
        // TODO add your handling code here:
        controller.clearFormCekTernak();
        controller.DateNow();
    }//GEN-LAST:event_BtnBatalCekActionPerformed

    
    private void TblInputDataCekTernakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblInputDataCekTernakMouseClicked
        // TODO add your handling code here:
        controller.onClickTabelCekTernak();
    }//GEN-LAST:event_TblInputDataCekTernakMouseClicked

    private void KembaliMenuUtamaUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMenuUtamaUserMouseClicked
        // TODO add your handling code here:
        setActiveMenu();
    }//GEN-LAST:event_KembaliMenuUtamaUserMouseClicked

    ////////////////////////////// User
    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        if (TxtIdUser.getText().isEmpty() || TxtUsername.getText().isEmpty() || JpsPassword.getText().isEmpty() || CboAkses.toString().equals(null)) {
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong" , "pesan", JOptionPane.WARNING_MESSAGE);
        } else {
            if (JpsPassword.getText() == null ? JpsRePassword.getText() != null : !JpsPassword.getText().equals(JpsRePassword.getText())) {
                JOptionPane.showMessageDialog(null, "Password Tidak Cocok, Perisak Kembali","pesan",JOptionPane.WARNING_MESSAGE);
            JpsPassword.requestFocus();
            } else {
                listPengguna = controllerUser.cariId(Integer.parseInt(TxtIdUser.getText()));
                if (listPengguna.size() == 1) {
                    JOptionPane.showMessageDialog(null, "Pengguna Telah Digunakan User Lain","pesan", JOptionPane.WARNING_MESSAGE);
                    TxtIdUser.requestFocus();
                } else {
                    try {
                        user.setIdUser(Integer.parseInt(TxtIdUser.getText()));
                        user.setUsername(TxtDataUsername.getText());
                        user.setPassword(Enkripsi.getSHA1(JpsDataPassword.getText()));
                        user.setNamaAkun(TxtDataNama.getText());
                        user.setAkses(CboAkses.getSelectedItem().toString());
                        if (controllerUser.tambah(user) == 1) {
                            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                            buatTable();
                            showTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
                        }
                        clearText();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak dapat Enkripsi Password");
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        user.setIdUser(Integer.parseInt(TxtIdUser.getText()));
        int pesan = JOptionPane.showConfirmDialog(null, "Data akan diHapus ?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pesan == JOptionPane.YES_NO_OPTION) {
            if (controllerUser.delete(user) == 1) {
                buatTable();
                showTable();
                clearText();
            }else {
                JOptionPane.showMessageDialog(null, "Hapus Data Gagal");
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteActionPerformed
        // TODO add your handling code here:
        clearText();
    }//GEN-LAST:event_BtnDeleteActionPerformed

    private void TblDataUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDataUserMouseClicked
        // TODO add your handling code here:
        int baris = TblDataUser.getSelectedRow();
        TxtIdUser.setText(TblDataUser.getModel().getValueAt(baris, 0).toString());
        TxtDataUsername.setText(TblDataUser.getModel().getValueAt(baris, 1).toString());
        TxtDataNama.setText(TblDataUser.getModel().getValueAt(baris, 3).toString());
    }//GEN-LAST:event_TblDataUserMouseClicked

    //////////////////              Laporan      ////////////////////////////
    private void BtnCekKodeLprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekKodeLprActionPerformed
        // TODO add your handling code here:
        int Database=Integer.parseInt(TxtKodeLaporan.getText());
        
        switch(Database){
            case 1:
                TabelLaporanKosong.setVisible(false);
                TabelLaporanKesehatan.setVisible(true);
                TabelLaporanCekTernak.setVisible(false);
                TabelLaporanPegawai.setVisible(false);
                TabelLaporanPakan.setVisible(false);
                controller.viewTableKesehatanLaporan();
                break;
            case 2:
                TabelLaporanKosong.setVisible(false);
                TabelLaporanKesehatan.setVisible(false);
                TabelLaporanCekTernak.setVisible(true);
                TabelLaporanPegawai.setVisible(false);
                TabelLaporanPakan.setVisible(false);
                controller.viewTableCekTernakLaporan();
                break;
            case 3:
                TabelLaporanKosong.setVisible(false);
                TabelLaporanKesehatan.setVisible(false);
                TabelLaporanCekTernak.setVisible(false);
                TabelLaporanPegawai.setVisible(true);
                TabelLaporanPakan.setVisible(false);
                controller.viewTablePegawaiLaporan();
                break;
            case 4:
                TabelLaporanKosong.setVisible(false);
                TabelLaporanKesehatan.setVisible(false);
                TabelLaporanCekTernak.setVisible(false);
                TabelLaporanPegawai.setVisible(false);
                TabelLaporanPakan.setVisible(true);
                controller.viewTablePakanLaporan();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Kode Salah, Silakan Masukan Kode");
                disableTabelLaporan();
                controller.cleartextLaporan();
                break;
        }
    }//GEN-LAST:event_BtnCekKodeLprActionPerformed

    private void BtnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExportExcelActionPerformed
        // TODO add your handling code here:
        int Database=Integer.parseInt(TxtKodeLaporan.getText());
        switch(Database){
            case 1:
            controller.setDataKesehatan();
            break;
            case 2:
            controller.setDataCekTernak();
            break;
            case 3:
            controller.setDataPegawai();
            break;
            case 4:
            controller.setDataPakan();
            break;
        }
    }//GEN-LAST:event_BtnExportExcelActionPerformed

    private void BtnExportPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExportPdfActionPerformed
        // TODO add your handling code here:
        int IReport=Integer.parseInt(TxtKodeLaporan.getText());
        
        switch(IReport) {
            case 1:
                controller.previewLaporanKesehatan();
                break;
            case 2:
                controller.previewLaporanCekTernak();
                break;
            case 3:
                controller.previewLaporanPegawai();
                break;
            case 4:
                controller.previewLaporanPakan();
                break;
        }
    }//GEN-LAST:event_BtnExportPdfActionPerformed

    private void KembaliDataCekTernakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliDataCekTernakMouseClicked
        // TODO add your handling code here:
        MenuDataCekTernak.setVisible(true);
        MenuInputCekTernak.setVisible(false);
    }//GEN-LAST:event_KembaliDataCekTernakMouseClicked

    private void TxtKodeLaporanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtKodeLaporanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtKodeLaporanKeyTyped

    private void TxtJmlTernakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJmlTernakKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtJmlTernakKeyTyped

    private void TxtNoTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoTelpKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtNoTelpKeyTyped

    private void TxtHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtHargaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtHargaKeyTyped

    private void TxtStokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtStokKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtStokKeyTyped

    private void TxtJmlSakitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJmlSakitKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtJmlSakitKeyTyped

    private void TxtJmlMatiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJmlMatiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtJmlMatiKeyTyped

    private void TxtJmlTelurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJmlTelurKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtJmlTelurKeyTyped

    private void TxtTglCekKesehatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTglCekKesehatanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTglCekKesehatanKeyTyped

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMenuPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenuPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenuPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenuPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenuPegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton BtnBatalCek;
    private javax.swing.JButton BtnBatalKandang;
    private javax.swing.JButton BtnBatalKesehatan;
    private javax.swing.JButton BtnBatalPakan;
    private javax.swing.JButton BtnBatalPegawai;
    private javax.swing.JButton BtnBatalPenyakit;
    private javax.swing.JButton BtnCekKodeLpr;
    private javax.swing.JButton BtnDataCekTernak;
    private javax.swing.JButton BtnDataKandang;
    private javax.swing.JButton BtnDataKesehatan;
    private javax.swing.JButton BtnDataPakan;
    private javax.swing.JButton BtnDataPegawai;
    private javax.swing.JButton BtnDataPenyakit;
    private javax.swing.JButton BtnDataUser;
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnExportExcel;
    private javax.swing.JButton BtnExportPdf;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnHapusCek;
    private javax.swing.JButton BtnHapusKandang;
    private javax.swing.JButton BtnHapusKesehatan;
    private javax.swing.JButton BtnHapusPakan;
    private javax.swing.JButton BtnHapusPegawai;
    private javax.swing.JButton BtnHapusPenyakit;
    private javax.swing.JButton BtnInputDataCekTernak;
    private javax.swing.JButton BtnInputDataKandang;
    private javax.swing.JButton BtnInputDataKesehatan;
    private javax.swing.JButton BtnInputDataPakan;
    private javax.swing.JButton BtnInputDataPegawai;
    private javax.swing.JButton BtnInputDataPenyakit;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JButton BtnSimpanCek;
    private javax.swing.JButton BtnSimpanKandang;
    private javax.swing.JButton BtnSimpanKesehatan;
    private javax.swing.JButton BtnSimpanPakan;
    private javax.swing.JButton BtnSimpanPeenyakit;
    private javax.swing.JButton BtnSimpanPegawai;
    private javax.swing.JPanel BtnTakAdmin;
    private javax.swing.JButton BtnUpdateCek;
    private javax.swing.JButton BtnUpdateKandang;
    private javax.swing.JButton BtnUpdateKesehatan;
    private javax.swing.JButton BtnUpdatePakan;
    private javax.swing.JButton BtnUpdatePegawai;
    private javax.swing.JButton BtnUpdatePenyakit;
    private javax.swing.JComboBox<String> CboAkses;
    private javax.swing.JComboBox<String> CboIdPakanCek;
    private javax.swing.JComboBox<String> CboIdPegawai;
    private javax.swing.JComboBox<String> CboIdPegawaiCek;
    private javax.swing.JComboBox<String> CboKandang;
    private javax.swing.JComboBox<String> CboKandangCek;
    private javax.swing.JComboBox<String> CboKebersihan;
    private javax.swing.JComboBox<String> CboPenyakit;
    private javax.swing.JLabel DateCekTernak;
    private javax.swing.JLabel DateKandang;
    private javax.swing.JLabel DateKesehatan;
    private javax.swing.JLabel DatePakan;
    private javax.swing.JLabel DatePegawai;
    private javax.swing.JLabel DatePenyakit;
    private javax.swing.JLabel DateUser;
    private javax.swing.JLabel HakAkses;
    private javax.swing.JLabel IconBack1;
    private javax.swing.JLabel IconBackKandang;
    private com.toedter.calendar.JDateChooser JdtTglLahir;
    private javax.swing.JPasswordField JpsDataPassword;
    private javax.swing.JPasswordField JpsDataRePassword;
    private javax.swing.JPasswordField JpsPassword;
    private javax.swing.JPasswordField JpsRePassword;
    private javax.swing.JTextArea JtxAlamat;
    private javax.swing.JTextArea JtxGejala;
    private javax.swing.JTextArea JtxPencegahan;
    private javax.swing.JTextArea JtxPengobatan;
    private javax.swing.JTextArea JtxPenularan;
    private javax.swing.JPanel KembaliDataCekTernak;
    private javax.swing.JPanel KembaliDataKandang;
    private javax.swing.JPanel KembaliDataKesehatan;
    private javax.swing.JPanel KembaliDataPakan;
    private javax.swing.JPanel KembaliDataPegawai;
    private javax.swing.JPanel KembaliDataPenyakit;
    private javax.swing.JPanel KembaliMenuUtama1;
    private javax.swing.JPanel KembaliMenuUtama2;
    private javax.swing.JPanel KembaliMenuUtama3;
    private javax.swing.JPanel KembaliMenuUtama4;
    private javax.swing.JPanel KembaliMenuUtamaCek;
    private javax.swing.JPanel KembaliMenuUtamaKsht;
    private javax.swing.JPanel KembaliMenuUtamaUser;
    private javax.swing.JLabel LabelUpPuyuh;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel MenuAdmin;
    private javax.swing.JPanel MenuCekTernak;
    private javax.swing.JPanel MenuDataCekTernak;
    private javax.swing.JPanel MenuDataKandang;
    private javax.swing.JPanel MenuDataKesehatan;
    private javax.swing.JPanel MenuDataPakan;
    private javax.swing.JPanel MenuDataPegawai;
    private javax.swing.JPanel MenuDataPenyakit;
    private javax.swing.JPanel MenuDataUser;
    private javax.swing.JPanel MenuHome;
    private javax.swing.JPanel MenuInputCekTernak;
    private javax.swing.JPanel MenuInputKandang;
    private javax.swing.JPanel MenuInputKesehatan;
    private javax.swing.JPanel MenuInputPakan;
    private javax.swing.JPanel MenuInputPegawai;
    private javax.swing.JPanel MenuInputPenyakit;
    private javax.swing.JPanel MenuJadwal;
    private javax.swing.JPanel MenuKandang;
    private javax.swing.JPanel MenuKesehatan;
    private javax.swing.JPanel MenuLaporan;
    private javax.swing.JPanel MenuLogout;
    private javax.swing.JPanel MenuPakan;
    private javax.swing.JPanel MenuPegawai;
    private javax.swing.JPanel MenuPenyakit;
    private javax.swing.JPanel MenuUser;
    private javax.swing.JPanel MenuUtama;
    private javax.swing.JLabel NamaAkun;
    private javax.swing.JButton NotifBtnCek;
    private javax.swing.JButton NotifBtnSdh;
    private javax.swing.JButton NotifInputKsh;
    private javax.swing.JPanel Notifikasi1;
    private javax.swing.JPanel Notifikasi2;
    private javax.swing.JPanel Notifikasi3;
    private javax.swing.JPanel PanelInputCekTernak;
    private javax.swing.JPanel PanelInputDataKandang;
    private javax.swing.JPanel PanelInputDataKandang1;
    private javax.swing.JPanel PanelInputDataKesehatan;
    private javax.swing.JPanel PanelInputDataKesehatan1;
    private javax.swing.JPanel PanelInputDataPenyaki;
    private javax.swing.JPanel PanelInputKandang;
    private javax.swing.JPanel PanelInputKesehatan;
    private javax.swing.JPanel PanelInputLaporan;
    private javax.swing.JPanel PanelInputPakan;
    private javax.swing.JPanel PanelInputPenyakit;
    private javax.swing.JPanel PanelMenuDataKandang;
    private javax.swing.JPanel PanelMenuDataKesehatan;
    private javax.swing.JPanel PanelMenuDataKesehatan1;
    private javax.swing.JPanel PanelMenuDataPakan;
    private javax.swing.JPanel PanelMenuDataPegawai;
    private javax.swing.JPanel PanelMenuDataPenyakit;
    private javax.swing.JPanel PanelMenuDataUser;
    private javax.swing.JPanel PanelMenuInputCekTernak;
    private javax.swing.JPanel PanelMenuInputKandang;
    private javax.swing.JPanel PanelMenuInputKesehatan;
    private javax.swing.JPanel PanelMenuInputPakan;
    private javax.swing.JPanel PanelMenuInputPegawai;
    private javax.swing.JPanel PanelMenuInputPenyakit;
    private javax.swing.JPanel PanelTabelKandang;
    private javax.swing.JPanel PanelTabelLaporan;
    private javax.swing.JPanel ProfilAkun;
    private javax.swing.JRadioButton RbLakiLaki;
    private javax.swing.JRadioButton RbPerempuan;
    private javax.swing.JLabel StatusTime;
    private javax.swing.JPanel SubMenu;
    private javax.swing.JPanel TabAkun;
    private javax.swing.JPanel TabJadwal;
    private javax.swing.JPanel TabLaporan;
    private javax.swing.JPanel TabTugas;
    private javax.swing.JPanel TabelLaporanCekTernak;
    private javax.swing.JPanel TabelLaporanKesehatan;
    private javax.swing.JPanel TabelLaporanKosong;
    private javax.swing.JPanel TabelLaporanPakan;
    private javax.swing.JPanel TabelLaporanPegawai;
    private javax.swing.JTable TblDataCekTernak;
    private javax.swing.JTable TblDataKandang;
    private javax.swing.JTable TblDataKesehatan;
    private javax.swing.JTable TblDataPakan;
    private javax.swing.JTable TblDataPegawai;
    private javax.swing.JTable TblDataPenyakit;
    private javax.swing.JTable TblDataUser;
    private javax.swing.JTable TblInputDataCekTernak;
    private javax.swing.JTable TblInputDataKandang;
    private javax.swing.JTable TblInputDataKesehatan;
    private javax.swing.JTable TblInputDataPakan;
    private javax.swing.JTable TblInputDataPegawai;
    private javax.swing.JTable TblInputDataPenyakit;
    private javax.swing.JTable TblLaporanCekTernak;
    private javax.swing.JTable TblLaporanKesehatan;
    private javax.swing.JTable TblLaporanKosong;
    private javax.swing.JTable TblLaporanPakan;
    private javax.swing.JTable TblLaporanPegawai;
    private javax.swing.JLabel TimeCekTernak;
    private javax.swing.JLabel TimeKandang;
    private javax.swing.JLabel TimeKesehatan;
    private javax.swing.JLabel TimePakan;
    private javax.swing.JLabel TimePegawai;
    private javax.swing.JLabel TimePenyakit;
    private javax.swing.JLabel TimeUser;
    private javax.swing.JLabel Tugas;
    private javax.swing.JTextField TxtAsal;
    private javax.swing.JTextField TxtDataNama;
    private javax.swing.JTextField TxtDataUsername;
    private javax.swing.JTextField TxtHakAkses;
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtIdCekTernak;
    private javax.swing.JTextField TxtIdKesehatan;
    private javax.swing.JTextField TxtIdPakan;
    private javax.swing.JTextField TxtIdPegawai;
    private javax.swing.JTextField TxtIdUser;
    private javax.swing.JTextField TxtJmlMati;
    private javax.swing.JTextField TxtJmlSakit;
    private javax.swing.JTextField TxtJmlTelur;
    private javax.swing.JTextField TxtJmlTernak;
    private javax.swing.JTextField TxtKodeLaporan;
    private javax.swing.JTextField TxtNamaKandang;
    private javax.swing.JTextField TxtNamaPakan;
    private javax.swing.JTextField TxtNamaPegawai;
    private javax.swing.JTextField TxtNamaPenyakit;
    private javax.swing.JTextField TxtNamaUser;
    private javax.swing.JTextField TxtNoTelp;
    private javax.swing.JTextField TxtStok;
    private javax.swing.JTextField TxtTglCek;
    private javax.swing.JTextField TxtTglCekKesehatan;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jDashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
