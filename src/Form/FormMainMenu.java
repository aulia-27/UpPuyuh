/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
/**
 *
 * @author Aulia
 */
public class FormMainMenu extends javax.swing.JFrame {

    /**
     * Creates new form FormMainMenu
     */
    public FormMainMenu() {
        initComponents();
        showDate();
        showTime();
    }
    
    public void showDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateLab.setText(sdf.format(d));
    }
    
    public void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                timeLab.setText(sdf.format(d));
            }
        }
        ).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        TxtDataKandang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDataKandang = new javax.swing.JTable();
        jMenu = new javax.swing.JPanel();
        TxtDashboard = new javax.swing.JLabel();
        TxtUser = new javax.swing.JLabel();
        TxtJadwal = new javax.swing.JLabel();
        TxtReport = new javax.swing.JLabel();
        TxtLogout = new javax.swing.JLabel();
        jMainMenu = new javax.swing.JPanel();
        BtnDataKandang = new javax.swing.JButton();
        BtnDataPegawai = new javax.swing.JButton();
        BtnDataKesehatan = new javax.swing.JButton();
        BtnDataPakan = new javax.swing.JButton();
        BtnDataPenyakit = new javax.swing.JButton();
        BtnDataKerja = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jStatus = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateLab = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timeLab = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        TxtDataKandang.setText("Data Kandang");

        TblDataKandang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblDataKandang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(TxtDataKandang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TxtDataKandang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TxtDashboard.setText("Dashboard");

        TxtUser.setText("User");

        TxtJadwal.setText("Jadwal");

        TxtReport.setText("Report");

        TxtLogout.setText("Logout");

        javax.swing.GroupLayout jMenuLayout = new javax.swing.GroupLayout(jMenu);
        jMenu.setLayout(jMenuLayout);
        jMenuLayout.setHorizontalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtJadwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jMenuLayout.setVerticalGroup(
            jMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(TxtDashboard)
                .addGap(45, 45, 45)
                .addComponent(TxtUser)
                .addGap(34, 34, 34)
                .addComponent(TxtJadwal)
                .addGap(41, 41, 41)
                .addComponent(TxtReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtLogout)
                .addGap(36, 36, 36))
        );

        BtnDataKandang.setText("Data Kandang");
        BtnDataKandang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataKandangActionPerformed(evt);
            }
        });

        BtnDataPegawai.setText("Data Pegawai");
        BtnDataPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPegawaiActionPerformed(evt);
            }
        });

        BtnDataKesehatan.setText("Data Kesehatan");
        BtnDataKesehatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataKesehatanActionPerformed(evt);
            }
        });

        BtnDataPakan.setText("Data Pakan");
        BtnDataPakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPakanActionPerformed(evt);
            }
        });

        BtnDataPenyakit.setText("Data Penyakit");
        BtnDataPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataPenyakitActionPerformed(evt);
            }
        });

        BtnDataKerja.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        BtnDataKerja.setText("Data Cek Kandang");
        BtnDataKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataKerjaActionPerformed(evt);
            }
        });

        jLabel5.setText("TUGAS");

        javax.swing.GroupLayout jMainMenuLayout = new javax.swing.GroupLayout(jMainMenu);
        jMainMenu.setLayout(jMainMenuLayout);
        jMainMenuLayout.setHorizontalGroup(
            jMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainMenuLayout.createSequentialGroup()
                        .addComponent(BtnDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jMainMenuLayout.createSequentialGroup()
                        .addComponent(BtnDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDataKerja, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMainMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(252, 252, 252))
        );
        jMainMenuLayout.setVerticalGroup(
            jMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainMenuLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDataKandang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDataKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDataPakan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnDataPenyakit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDataKerja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel1.setText("Avatar");

        dateLab.setText("Date");

        jLabel3.setText("Name");

        jLabel4.setText("Status");

        timeLab.setText("Time");

        javax.swing.GroupLayout jStatusLayout = new javax.swing.GroupLayout(jStatus);
        jStatus.setLayout(jStatusLayout);
        jStatusLayout.setHorizontalGroup(
            jStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStatusLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jStatusLayout.createSequentialGroup()
                        .addComponent(dateLab)
                        .addGap(50, 50, 50)
                        .addComponent(timeLab)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jStatusLayout.setVerticalGroup(
            jStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLab)
                    .addComponent(timeLab))
                .addGroup(jStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jStatusLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jStatusLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jLabel6.setText("2021 Perternakan Burung Puyuh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(688, 467));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDataKandangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataKandangActionPerformed
        // TODO add your handling code here:
        FormDataKandang formDataKandang = new FormDataKandang();
        formDataKandang.setVisible(true);
        formDataKandang.toFront();
        dispose();
    }//GEN-LAST:event_BtnDataKandangActionPerformed

    private void BtnDataPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPegawaiActionPerformed
        // TODO add your handling code here:
        FormDataPegawai formDataPegawai = new FormDataPegawai();
        formDataPegawai.setVisible(true);
        formDataPegawai.toFront();
        dispose();
    }//GEN-LAST:event_BtnDataPegawaiActionPerformed

    private void BtnDataKesehatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataKesehatanActionPerformed
        // TODO add your handling code here:
        FormDataKesehatan formDataKesehatan = new FormDataKesehatan();
        formDataKesehatan.setVisible(true);
        formDataKesehatan.toFront();
        dispose();
    }//GEN-LAST:event_BtnDataKesehatanActionPerformed

    private void BtnDataPakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPakanActionPerformed
        // TODO add your handling code here:
        FormDataPakan formDataPakan = new FormDataPakan();
        formDataPakan.setVisible(true);
        formDataPakan.toFront();
        dispose();
    }//GEN-LAST:event_BtnDataPakanActionPerformed

    private void BtnDataPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataPenyakitActionPerformed
        // TODO add your handling code here:
        FormDataPenyakit formDataPenyakit = new FormDataPenyakit();
        formDataPenyakit.setVisible(true);
        formDataPenyakit.toFront();
        dispose();
    }//GEN-LAST:event_BtnDataPenyakitActionPerformed

    private void BtnDataKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataKerjaActionPerformed
        // TODO add your handling code here:
        FormDataCekTernak formDataCekTernak = new FormDataCekTernak();
        formDataCekTernak.setVisible(true);
        formDataCekTernak.toFront();
    }//GEN-LAST:event_BtnDataKerjaActionPerformed

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
            java.util.logging.Logger.getLogger(FormMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDataKandang;
    private javax.swing.JButton BtnDataKerja;
    private javax.swing.JButton BtnDataKesehatan;
    private javax.swing.JButton BtnDataPakan;
    private javax.swing.JButton BtnDataPegawai;
    private javax.swing.JButton BtnDataPenyakit;
    private javax.swing.JTable TblDataKandang;
    private javax.swing.JLabel TxtDashboard;
    private javax.swing.JLabel TxtDataKandang;
    private javax.swing.JLabel TxtJadwal;
    private javax.swing.JLabel TxtLogout;
    private javax.swing.JLabel TxtReport;
    private javax.swing.JLabel TxtUser;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jMainMenu;
    private javax.swing.JPanel jMenu;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jStatus;
    private javax.swing.JLabel timeLab;
    // End of variables declaration//GEN-END:variables
}
