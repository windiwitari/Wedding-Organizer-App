/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.wo.app.view;

import com.wo.app.dao.KlienDao;
import com.wo.app.dao.TugasDao;
import com.wo.app.dao.VendorDao;
import com.wo.app.model.Klien;
import com.wo.app.model.Tugas;
import com.wo.app.model.Vendor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author windiwitari
 */
public class MainView extends javax.swing.JFrame {

    // DAO untuk setiap modul
    private final KlienDao klienDao;
    private final VendorDao vendorDao;
    private final TugasDao tugasDao;

    // Variabel untuk menyimpan ID yang dipilih dari tabel
    private ObjectId selectedKlienId;
    private ObjectId selectedVendorId;
    private ObjectId selectedTugasId;
    
    class KlienComboBoxItem {
        private final ObjectId id;
        private final String nama;

        public KlienComboBoxItem(ObjectId id, String nama) {
            this.id = id;
            this.nama = nama;
        }

        public ObjectId getId() {
            return id;
        }

        @Override
        public String toString() {
            return nama; // Teks ini yang akan ditampilkan di ComboBox
        }
    }
    
    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
         // Inisialisasi semua DAO
        klienDao = new KlienDao();
        vendorDao = new VendorDao();
        tugasDao = new TugasDao();

        // Muat data awal untuk setiap tab
        loadKlienData();
        resetForm();

        loadVendorData();
        resetFormVendor();

        populateKlienComboBox();
        resetFormTugas();;
    }
    
     // =========================================================================
    // == BAGIAN KODE UNTUK MANAJEMEN KLIEN
    // =========================================================================
    

    private void loadKlienData() {
        DefaultTableModel model = (DefaultTableModel) tabelKlien.getModel();
        model.setRowCount(0);
        List<Klien> klienList = klienDao.findAll();
        for (Klien klien : klienList) {
            model.addRow(new Object[]{
                klien.getId(), klien.getNamaLengkap(), klien.getEmail(),
                klien.getTanggalPernikahan(), klien.getLokasi()
            });
        }
        if (tabelKlien.getColumnCount() > 0) {
            tabelKlien.getColumnModel().getColumn(0).setMinWidth(0);
            tabelKlien.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }

    private void resetForm() {
        txtNama.setText("");
        txtEmail.setText("");
        txtTelepon.setText("");
        txtLokasi.setText("");
        dateChooserTanggal.setDate(null);
        spinnerTamu.setValue(0);
        selectedKlienId = null;
        btnSimpan.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnHapus.setEnabled(false);
        tabelKlien.clearSelection();
    }
    
     // =========================================================================
    // == BAGIAN KODE UNTUK MANAJEMEN VENDOR
    // =========================================================================

    private void loadVendorData() {
        DefaultTableModel model = (DefaultTableModel) tabelVendor.getModel();
        model.setRowCount(0);
        List<Vendor> vendorList = vendorDao.findAll();
        for (Vendor vendor : vendorList) {
            model.addRow(new Object[]{
                vendor.getId(), vendor.getNamaVendor(), vendor.getKategori(), vendor.getKontakPerson()
            });
        }
        if (tabelVendor.getColumnCount() > 0) {
            tabelVendor.getColumnModel().getColumn(0).setMinWidth(0);
            tabelVendor.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }

    private void resetFormVendor() {
        txtNamaVendor.setText("");
        txtTeleponVendor.setText("");
        txtEmailVendor.setText("");
        txtAreaDeskripsi.setText("");
        dateChooserTanggal.setDate(null);
        selectedVendorId = null;
        btnSimpanVendor.setEnabled(true);
        btnUpdateVendor.setEnabled(false);
        btnHapusVendor.setEnabled(false);
        tabelVendor.clearSelection();
    }
    
   
    // =========================================================================
    // BAGIAN KODE UNTUK MANAJEMEN TUGAS
    // =========================================================================
    
    private void populateKlienComboBox() {
        comboKlienTugas.removeAllItems();
        List<Klien> klienList = klienDao.findAll();
        for (Klien klien : klienList) {
            comboKlienTugas.addItem(new KlienComboBoxItem(klien.getId(), klien.getNamaLengkap()));
        }
    }

    private void loadTugasData(ObjectId clientId) {
        DefaultTableModel model = (DefaultTableModel) tabelTugas.getModel();
        model.setRowCount(0);
        if (clientId != null) {
            List<Tugas> tugasList = tugasDao.findByClientId(clientId);
            for (Tugas tugas : tugasList) {
                model.addRow(new Object[]{
                    tugas.getId(), tugas.getNamaTugas(), tugas.getTenggatWaktu(),
                    tugas.getStatus(), tugas.getPenanggungJawab()
                });
            }
        }
        if (tabelTugas.getColumnCount() > 0) {
            tabelTugas.getColumnModel().getColumn(0).setMinWidth(0);
            tabelTugas.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }

    private void resetFormTugas() {
        txtNamaTugas.setText("");
        dateTenggatWaktu.setDate(null);
        txtPenanggungJawab.setText("");
        selectedTugasId = null;
        tabelTugas.clearSelection();
        btnHapusTugas.setEnabled(false);
        btnTandaiSelesai.setEnabled(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Klien = new javax.swing.JPanel();
        txtTelepon = new javax.swing.JTextField();
        txtLokasi = new javax.swing.JTextField();
        dateChooserTanggal = new com.toedter.calendar.JDateChooser();
        spinnerTamu = new javax.swing.JSpinner();
        btnSimpan = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBaru = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKlien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Vendor = new javax.swing.JPanel();
        txtTeleponVendor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAreaDeskripsi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSimpanVendor = new javax.swing.JButton();
        btnUpdateVendor = new javax.swing.JButton();
        txtNamaVendor = new javax.swing.JTextField();
        btnHapusVendor = new javax.swing.JButton();
        txtEmailVendor = new javax.swing.JTextField();
        btnBaru1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelVendor = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comboKlienTugas = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelTugas = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtNamaTugas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPenanggungJawab = new javax.swing.JTextField();
        btnSimpanTugas = new javax.swing.JButton();
        btnHapusTugas = new javax.swing.JButton();
        btnTandaiSelesai = new javax.swing.JButton();
        dateTenggatWaktu = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBaru.setText("New");
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

        tabelKlien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKlienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKlien);

        jLabel1.setText("Nama");

        jLabel2.setText("Email");

        jLabel3.setText("Telepon");

        jLabel4.setText("Lokasi");

        jLabel5.setText("Tanggal");

        jLabel6.setText("Jumlah Tamu");

        javax.swing.GroupLayout KlienLayout = new javax.swing.GroupLayout(Klien);
        Klien.setLayout(KlienLayout);
        KlienLayout.setHorizontalGroup(
            KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KlienLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KlienLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(492, 492, 492))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KlienLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(KlienLayout.createSequentialGroup()
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KlienLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KlienLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(98, 98, 98)))
                            .addGroup(KlienLayout.createSequentialGroup()
                                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(17, 17, 17))))
                    .addGroup(KlienLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate)
                        .addGap(35, 35, 35)))
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(KlienLayout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(26, 26, 26)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnBaru))
                    .addComponent(dateChooserTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(spinnerTamu)
                    .addComponent(txtLokasi)
                    .addComponent(txtEmail)
                    .addComponent(txtTelepon)
                    .addComponent(txtNama))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KlienLayout.setVerticalGroup(
            KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KlienLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerTamu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(KlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBaru)
                    .addComponent(btnHapus)
                    .addComponent(btnUpdate))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Manajemen Klien", Klien);

        jLabel7.setText("Email");

        jLabel8.setText("Telepon");

        jLabel9.setText("Lokasi");

        btnSimpanVendor.setText("Simpan");
        btnSimpanVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanVendorActionPerformed(evt);
            }
        });

        btnUpdateVendor.setText("Update");
        btnUpdateVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateVendorActionPerformed(evt);
            }
        });

        btnHapusVendor.setText("Hapus");
        btnHapusVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusVendorActionPerformed(evt);
            }
        });

        btnBaru1.setText("New");
        btnBaru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaru1ActionPerformed(evt);
            }
        });

        tabelVendor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelVendorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelVendor);

        jLabel12.setText("Nama");

        javax.swing.GroupLayout VendorLayout = new javax.swing.GroupLayout(Vendor);
        Vendor.setLayout(VendorLayout);
        VendorLayout.setHorizontalGroup(
            VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendorLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendorLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(492, 492, 492))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendorLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(VendorLayout.createSequentialGroup()
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VendorLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendorLayout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(98, 98, 98)))
                            .addGroup(VendorLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(100, 100, 100))))
                    .addGroup(VendorLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateVendor)
                        .addGap(35, 35, 35)))
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(VendorLayout.createSequentialGroup()
                        .addComponent(btnSimpanVendor)
                        .addGap(26, 26, 26)
                        .addComponent(btnHapusVendor)
                        .addGap(18, 18, 18)
                        .addComponent(btnBaru1))
                    .addComponent(txtAreaDeskripsi, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(txtEmailVendor)
                    .addComponent(txtTeleponVendor)
                    .addComponent(txtNamaVendor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        VendorLayout.setVerticalGroup(
            VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VendorLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNamaVendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTeleponVendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmailVendor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtAreaDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(VendorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanVendor)
                    .addComponent(btnBaru1)
                    .addComponent(btnHapusVendor)
                    .addComponent(btnUpdateVendor))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Manajemen Vendor", Vendor);

        jLabel13.setText("Pilih Klien");

        tabelTugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tabelTugas);

        jLabel14.setText("Nama Tugas");

        txtNamaTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaTugasActionPerformed(evt);
            }
        });

        jLabel15.setText("Tenggat");

        jLabel16.setText("Penanggungjawab");

        btnSimpanTugas.setText("Simpan Tugas");
        btnSimpanTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTugasActionPerformed(evt);
            }
        });

        btnHapusTugas.setText("Hapus Tugas");
        btnHapusTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusTugasActionPerformed(evt);
            }
        });

        btnTandaiSelesai.setText("Tandai Selesai");
        btnTandaiSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTandaiSelesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboKlienTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(btnSimpanTugas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnHapusTugas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                        .addComponent(btnTandaiSelesai))
                                    .addComponent(txtNamaTugas)
                                    .addComponent(txtPenanggungJawab)
                                    .addComponent(dateTenggatWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(74, 74, 74))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(comboKlienTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNamaTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(dateTenggatWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPenanggungJawab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanTugas)
                    .addComponent(btnHapusTugas)
                    .addComponent(btnTandaiSelesai))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manajemen Tugas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaTugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaTugasActionPerformed

    private void btnSimpanTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTugasActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah klien sudah dipilih di ComboBox
        KlienComboBoxItem selectedKlien = (KlienComboBoxItem) comboKlienTugas.getSelectedItem();
        if (selectedKlien == null) {
            JOptionPane.showMessageDialog(this, "Pilih klien terlebih dahulu untuk menambahkan tugas!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validasi input (pastikan nama tugas tidak kosong)
        if (txtNamaTugas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Tugas tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 3. Buat objek Tugas baru
        Tugas tugas = new Tugas();
        
        // 4. Isi data dari form ke objek
        tugas.setIdKlien(selectedKlien.getId()); // Hubungkan tugas dengan klien
        tugas.setNamaTugas(txtNamaTugas.getText());
        tugas.setPenanggungJawab(txtPenanggungJawab.getText());
        tugas.setStatus("Belum Dikerjakan"); // Status default untuk tugas baru

        if (dateTenggatWaktu.getDate() != null) {
            tugas.setTenggatWaktu(dateTenggatWaktu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            tugas.setTenggatWaktu(LocalDate.now().plusDays(7)); // Default tenggat 7 hari jika kosong
        }
        
        // 5. Panggil DAO untuk menyimpan ke database
        tugasDao.create(tugas);
        
        // 6. Beri umpan balik dan refresh tampilan
        JOptionPane.showMessageDialog(this, "Tugas baru berhasil disimpan!");
        loadTugasData(selectedKlien.getId()); // Refresh tabel tugas
        resetFormTugas(); // Kosongkan form input tugas
    }//GEN-LAST:event_btnSimpanTugasActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
     Klien klien = new Klien();
        klien.setNamaLengkap(txtNama.getText());
        klien.setEmail(txtEmail.getText());
        klien.setNoTelepon(txtTelepon.getText());
        klien.setLokasi(txtLokasi.getText());
        klien.setJumlahTamu((Integer) spinnerTamu.getValue());
        if (dateChooserTanggal.getDate() != null) {
            klien.setTanggalPernikahan(dateChooserTanggal.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            klien.setTanggalPernikahan(LocalDate.now());
        }
        klienDao.create(klien);
        JOptionPane.showMessageDialog(this, "Data klien berhasil disimpan!");
        loadKlienData();
        populateKlienComboBox(); // Update juga combobox di tab tugas
        resetForm();    
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanVendorActionPerformed
        // TODO add your handling code here:
   // Langkah 2: Buat objek Vendor baru untuk diisi data
        Vendor vendor = new Vendor();
        
        // Langkah 3: Ambil semua data dari form dan masukkan ke objek
        vendor.setNamaVendor(txtNamaVendor.getText());
        vendor.setNoTelepon(txtTeleponVendor.getText());
        vendor.setEmail(txtEmailVendor.getText());
        vendor.setDeskripsiPaket(txtAreaDeskripsi.getText());
        
        
        
        // Langkah 4: Panggil DAO untuk menyimpan objek ke database MongoDB
        vendorDao.create(vendor);
        
        // Langkah 5: Tampilkan pesan bahwa penyimpanan berhasil
        JOptionPane.showMessageDialog(this, "Data vendor berhasil disimpan!");
        
        // Langkah 6: Refresh data di tabel agar data baru muncul
        loadVendorData();
        resetFormVendor();    
        
    }//GEN-LAST:event_btnSimpanVendorActionPerformed

    private void tabelKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKlienMouseClicked
        // TODO add your handling code here:
    int selectedRow = tabelKlien.getSelectedRow();
        if (selectedRow != -1) {
            selectedKlienId = (ObjectId) tabelKlien.getValueAt(selectedRow, 0);
            txtNama.setText(tabelKlien.getValueAt(selectedRow, 1).toString());
            txtEmail.setText(tabelKlien.getValueAt(selectedRow, 2).toString());
            LocalDate tanggal = (LocalDate) tabelKlien.getValueAt(selectedRow, 3);
            dateChooserTanggal.setDate(Date.from(tanggal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            txtLokasi.setText(tabelKlien.getValueAt(selectedRow, 4).toString());
            btnSimpan.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnHapus.setEnabled(true);
        }   
        
    }//GEN-LAST:event_tabelKlienMouseClicked

    private void tabelVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelVendorMouseClicked
        // TODO add your handling code here:
        
        int row = tabelVendor.getSelectedRow();
        if (row != -1) {
            selectedVendorId = (ObjectId) tabelVendor.getValueAt(row, 0);
            // ... (kode untuk mengambil data vendor dari DB dan menampilkannya di form) ...
            btnSimpanVendor.setEnabled(false);
            btnUpdateVendor.setEnabled(true);
            btnHapusVendor.setEnabled(true);
        }
        
    }//GEN-LAST:event_tabelVendorMouseClicked

    private void btnBaru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaru1ActionPerformed
        // TODO add your handling code here:
        resetFormVendor();
    }//GEN-LAST:event_btnBaru1ActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah ada klien yang dipilih dari tabel
        if (selectedKlienId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data klien yang akan dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan proses jika tidak ada yang dipilih
        }

        // 2. Tampilkan dialog konfirmasi
        int response = JOptionPane.showConfirmDialog(this,
                "Anda yakin ingin menghapus data klien ini? Semua tugas yang terkait juga akan tetap ada.", "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        // 3. Jika pengguna menekan "YES"
        if (response == JOptionPane.YES_OPTION) {
            // 4. Panggil DAO untuk menghapus data dari database
            if (klienDao.delete(selectedKlienId)) {
                // Jika berhasil
                JOptionPane.showMessageDialog(this, "Data klien berhasil dihapus!");
                loadKlienData();       // Refresh tabel klien
                populateKlienComboBox(); // Refresh combobox di tab tugas
                resetForm();           // Kosongkan form
            } else {
                // Jika gagal
                JOptionPane.showMessageDialog(this, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHapusVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusVendorActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah ada vendor yang dipilih dari tabel
        if (selectedVendorId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data vendor yang akan dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Tampilkan dialog konfirmasi
        int response = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data vendor ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        // 3. Jika pengguna menekan "YES"
        if (response == JOptionPane.YES_OPTION) {
            // 4. Panggil DAO untuk menghapus
            if (vendorDao.delete(selectedVendorId)) {
                JOptionPane.showMessageDialog(this, "Data vendor berhasil dihapus!");
                loadVendorData();    // Refresh tabel vendor
                resetFormVendor();   // Kosongkan form vendor
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data vendor!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusVendorActionPerformed

    private void btnHapusTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusTugasActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah ada tugas yang dipilih dari tabel
        if (selectedTugasId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data tugas yang akan dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 2. Tampilkan dialog konfirmasi
        int response = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus tugas ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        // 3. Jika pengguna menekan "YES"
        if (response == JOptionPane.YES_OPTION) {
            // 4. Panggil DAO untuk menghapus
            if (tugasDao.delete(selectedTugasId)) {
                JOptionPane.showMessageDialog(this, "Tugas berhasil dihapus!");
                
                // Refresh tabel tugas sesuai klien yang sedang dipilih
                KlienComboBoxItem selectedKlien = (KlienComboBoxItem) comboKlienTugas.getSelectedItem();
                if (selectedKlien != null) {
                    loadTugasData(selectedKlien.getId());
                }
                resetFormTugas(); // Kosongkan form tugas
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus tugas!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusTugasActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        //1. Periksa apakah ada klien yang dipilih dari tabel
        if (selectedKlienId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data klien yang akan diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 2. Buat objek Klien dan isi dengan data dari form
        Klien klien = new Klien();
        klien.setId(selectedKlienId); // PENTING: Set ID agar DAO tahu mana yang diupdate
        klien.setNamaLengkap(txtNama.getText());
        klien.setEmail(txtEmail.getText());
        klien.setNoTelepon(txtTelepon.getText());
        klien.setLokasi(txtLokasi.getText());
        klien.setJumlahTamu((Integer) spinnerTamu.getValue());
        if (dateChooserTanggal.getDate() != null) {
            klien.setTanggalPernikahan(dateChooserTanggal.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        // 3. Panggil DAO untuk mengupdate data di database
        if (klienDao.update(klien)) {
            JOptionPane.showMessageDialog(this, "Data klien berhasil diupdate!");
            loadKlienData();       // Refresh tabel
            populateKlienComboBox(); // Refresh combobox di tab tugas
            resetForm();           // Kosongkan form
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateVendorActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah ada vendor yang dipilih
        if (selectedVendorId == null) {
            JOptionPane.showMessageDialog(this, "Pilih vendor yang akan diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Buat objek Vendor dan isi dengan data dari form
        Vendor vendor = new Vendor();
        vendor.setId(selectedVendorId); // PENTING: Set ID vendor yang akan diupdate
        vendor.setNamaVendor(txtNamaVendor.getText());
        vendor.setNoTelepon(txtTeleponVendor.getText());
        vendor.setEmail(txtEmailVendor.getText());
        vendor.setDeskripsiPaket(txtAreaDeskripsi.getText());
        // Jika Anda menambahkan JDateChooser ke tab Vendor, tambahkan logikanya di sini

        // 3. Panggil DAO untuk update
        if (vendorDao.update(vendor)) {
            JOptionPane.showMessageDialog(this, "Data vendor berhasil diupdate!");
            loadVendorData();
            resetFormVendor();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data vendor!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateVendorActionPerformed

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_btnBaruActionPerformed

    private void btnTandaiSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTandaiSelesaiActionPerformed
        // TODO add your handling code here:
        // 1. Periksa apakah ada tugas yang dipilih dari tabel
        if (selectedTugasId == null) {
            JOptionPane.showMessageDialog(this, "Pilih tugas yang akan ditandai selesai!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Panggil DAO untuk mengupdate status tugas menjadi "Selesai"
        if (tugasDao.updateStatus(selectedTugasId, "Selesai")) {
            // Jika berhasil diupdate
            // Panggil method dari kelas SoundPlayer (Fitur Multimedia)
            com.wo.app.util.SoundPlayer.playNotificationSound();
            
            JOptionPane.showMessageDialog(this, "Tugas ditandai selesai!");

            // 3. Refresh tabel untuk menampilkan status yang baru
            KlienComboBoxItem selectedKlien = (KlienComboBoxItem) comboKlienTugas.getSelectedItem();
            if (selectedKlien != null) {
                loadTugasData(selectedKlien.getId());
            }
            resetFormTugas(); // Reset form dan state tombol
        } else {
            // Jika gagal
            JOptionPane.showMessageDialog(this, "Gagal mengupdate status tugas!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnTandaiSelesaiActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Klien;
    private javax.swing.JPanel Vendor;
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnBaru1;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusTugas;
    private javax.swing.JButton btnHapusVendor;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpanTugas;
    private javax.swing.JButton btnSimpanVendor;
    private javax.swing.JButton btnTandaiSelesai;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateVendor;
    private javax.swing.JComboBox<KlienComboBoxItem> comboKlienTugas;
    private com.toedter.calendar.JDateChooser dateChooserTanggal;
    private com.toedter.calendar.JDateChooser dateTenggatWaktu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner spinnerTamu;
    private javax.swing.JTable tabelKlien;
    private javax.swing.JTable tabelTugas;
    private javax.swing.JTable tabelVendor;
    private javax.swing.JTextField txtAreaDeskripsi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailVendor;
    private javax.swing.JTextField txtLokasi;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaTugas;
    private javax.swing.JTextField txtNamaVendor;
    private javax.swing.JTextField txtPenanggungJawab;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTeleponVendor;
    // End of variables declaration//GEN-END:variables
}