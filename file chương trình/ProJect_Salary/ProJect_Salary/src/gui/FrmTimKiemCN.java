/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import dao.CongNhanDAO;
import dao.PhongBanDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entity.CongNhan;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.ObjectOutput;
import javax.swing.JComboBox;
import entity.PhongBan;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Tuan Kiet Admin
 */
public class FrmTimKiemCN extends javax.swing.JPanel {

    private CongNhanDAO cndao;
    private PhongBanDAO pbdao;
    DefaultTableModel modelCN;

    /**
     * Creates new form TimKiemCN
     */
    public FrmTimKiemCN() {
        try {
            ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        cndao = new CongNhanDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã Cn;Mã PB;Tên CN;Sđt;Địa Chỉ;Giới Tính;Ngày Sinh".split(";");
        modelCN = new DefaultTableModel(header, 0);
        jTable1.setModel(modelCN);
        loadComboBoxMa();
        loadComboBoxGT();
        loadComboBoxPB();
    }

    public void locCN() {
        if (cboMa.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboMa.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            String txtMa = cboMa.getSelectedItem().toString();
            List<CongNhan> lst = cndao.findCNbyMaCN(txtMa);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelCN.setRowCount(0);
            for (CongNhan cn : lst) {
                Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                    cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
            }
        }
    }

    public void locCNbyGT() {
        if (cboGT.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboGT.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            if (cboGT.getSelectedItem().equals("Nam")) {
                int check = 1;
                List<CongNhan> lst = cndao.findCNbyGT(check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            } else {
                int check = 0;
                List<CongNhan> lst = cndao.findCNbyGT(check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            }
        }
    }

    public void locCNPB() {
        if (cboPB.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboPB.getSelectedItem().equals("")) {
            modelCN.setRowCount(0);
        } else {
            String txtPB = cboPB.getSelectedItem().toString();
            List<CongNhan> lst = cndao.findCNbyPB(txtPB);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelCN.setRowCount(0);
            for (CongNhan cn : lst) {
                Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                    cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
            }
        }
    }

    public void locPBvsGT() {
        if (cboGT.getSelectedItem().equals("Tất cả") && cboPB.getSelectedItem().equals("Tất cả")) {
            readDatabase();
        } else if (cboGT.getSelectedItem().equals("Tất cả")) {
            locCNPB();
        } else if (cboPB.getSelectedItem().equals("Tất cả")) {
            locCNbyGT();
        } else {
            String txtPB = cboPB.getSelectedItem().toString();
            if (cboGT.getSelectedItem().equals("Nam")) {
                int check = 1;
                List<CongNhan> lst = cndao.findPBvsGT(txtPB, check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            } else {
                int check = 0;
                List<CongNhan> lst = cndao.findPBvsGT(txtPB, check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelCN.setRowCount(0);
                for (CongNhan cn : lst) {
                    Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                        cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                    modelCN.addRow(row);
                }
            }
        }
    }

    public void findTenCN() {
        String txtTen = this.txtTen.getText().toString().trim();
        List<CongNhan> lst = cndao.findTenCN(txtTen);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void findSdt() {
        String txtsdt = txtSđt.getText().toString().trim();
        List<CongNhan> lst = cndao.findSDT(txtsdt);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void findDiaChi() {
        String txtDiachi = txtDiaChi.getText().toString().trim();
        List<CongNhan> lst = cndao.findDiaChi(txtDiachi);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelCN.setRowCount(0);
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
    }

    public void xoaRong() {
        cboMa.setSelectedIndex(0);
        cboGT.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
        txtDiaChi.setText("");
        txtTen.setText("");
        txtSđt.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboMa = new javax.swing.JComboBox<>();
        txtSđt = new javax.swing.JTextField();
        cboGT = new javax.swing.JComboBox<>();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        cboPB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTestDiaChi = new javax.swing.JLabel();
        lblTestTenCN = new javax.swing.JLabel();
        lbltestSdt = new javax.swing.JLabel();
        lblTong = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnLoc1 = new custom_button.MyButton();
        btnTim1 = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();

        setForeground(new java.awt.Color(255, 51, 51));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("TÌM KIẾM CÔNG NHÂN");
        add(jLabel1);
        jLabel1.setBounds(474, 19, 286, 26);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã CN:");
        add(jLabel2);
        jLabel2.setBounds(242, 77, 50, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Sđt:");
        add(jLabel3);
        jLabel3.setBounds(242, 149, 40, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giới Tính:");
        add(jLabel4);
        jLabel4.setBounds(244, 221, 70, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên CN:");
        add(jLabel5);
        jLabel5.setBounds(650, 80, 70, 17);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Địa Chỉ:");
        add(jLabel6);
        jLabel6.setBounds(650, 150, 60, 20);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phòng Ban:");
        add(jLabel7);
        jLabel7.setBounds(650, 220, 80, 20);

        cboMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaItemStateChanged(evt);
            }
        });
        cboMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaActionPerformed(evt);
            }
        });
        add(cboMa);
        cboMa.setBounds(323, 70, 200, 30);

        txtSđt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSđt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSđtMouseClicked(evt);
            }
        });
        txtSđt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSđtActionPerformed(evt);
            }
        });
        add(txtSđt);
        txtSđt.setBounds(323, 142, 200, 30);

        cboGT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboGT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGTItemStateChanged(evt);
            }
        });
        cboGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGTActionPerformed(evt);
            }
        });
        add(cboGT);
        cboGT.setBounds(320, 214, 100, 31);

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenMouseClicked(evt);
            }
        });
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });
        add(txtTen);
        txtTen.setBounds(740, 70, 210, 30);

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiaChiMouseClicked(evt);
            }
        });
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        add(txtDiaChi);
        txtDiaChi.setBounds(740, 142, 210, 30);

        cboPB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPBItemStateChanged(evt);
            }
        });
        cboPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPBActionPerformed(evt);
            }
        });
        add(cboPB);
        cboPB.setBounds(742, 214, 140, 31);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CN", "Mã PB", "Tên CN", "Sđt", "Địa Chỉ", "Giới Tính", "Ngày Sinh"
            }
        ));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 370, 1215, 320);

        lblTestDiaChi.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestDiaChi);
        lblTestDiaChi.setBounds(740, 175, 348, 33);

        lblTestTenCN.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestTenCN);
        lblTestTenCN.setBounds(740, 106, 418, 33);

        lbltestSdt.setForeground(new java.awt.Color(255, 51, 51));
        add(lbltestSdt);
        lbltestSdt.setBounds(323, 175, 332, 33);

        lblTong.setForeground(new java.awt.Color(255, 51, 51));
        lblTong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTong.setToolTipText("");
        add(lblTong);
        lblTong.setBounds(368, 332, 462, 32);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel11.setText("Trợ giúp");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        add(jLabel11);
        jLabel11.setBounds(1100, 0, 90, 50);

        btnLoc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loc.png"))); // NOI18N
        btnLoc1.setText("Lọc");
        btnLoc1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnLoc1.setColorhover(new java.awt.Color(0, 255, 255));
        btnLoc1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoc1.setRadius(30);
        btnLoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoc1ActionPerformed(evt);
            }
        });
        add(btnLoc1);
        btnLoc1.setBounds(300, 290, 140, 50);

        btnTim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTim1.setText("Tìm Kiếm");
        btnTim1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnTim1.setColorhover(new java.awt.Color(0, 255, 255));
        btnTim1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTim1.setRadius(30);
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });
        add(btnTim1);
        btnTim1.setBounds(580, 290, 160, 50);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
        btnXoa.setText("Xóa Rỗng");
        btnXoa.setBorderColor(new java.awt.Color(102, 102, 102));
        btnXoa.setColorhover(new java.awt.Color(0, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa.setRadius(30);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa);
        btnXoa.setBounds(860, 290, 160, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cboMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaActionPerformed

    private void cboPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPBActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboPBActionPerformed

    private void txtSđtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSđtActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSđtActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTenActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSđtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSđtMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtTen.setText("");
        lblTestDiaChi.setText("");
        lblTestTenCN.setText("");
        btnTim1.setEnabled(true);
        btnLoc1.setEnabled(false);
        cboMa.setSelectedItem("");
        cboGT.setSelectedItem("");
        cboPB.setSelectedItem("");
    }//GEN-LAST:event_txtSđtMouseClicked

    private void txtTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSđt.setText("");
        lblTestDiaChi.setText("");
        lbltestSdt.setText("");
        btnTim1.setEnabled(true);
        btnLoc1.setEnabled(false);
        cboMa.setSelectedItem("");
        cboGT.setSelectedItem("");
        cboPB.setSelectedItem("");
    }//GEN-LAST:event_txtTenMouseClicked

    private void txtDiaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiaChiMouseClicked
        // TODO add your handling code here:
        txtTen.setText("");
        txtSđt.setText("");
        lbltestSdt.setText("");
        lblTestTenCN.setText("");
        btnTim1.setEnabled(true);
        btnLoc1.setEnabled(false);
        cboMa.setSelectedItem("");
        cboGT.setSelectedItem("");
        cboPB.setSelectedItem("");
    }//GEN-LAST:event_txtDiaChiMouseClicked

    private void cboGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGTActionPerformed

    private void cboGTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGTItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSđt.setText("");
        txtTen.setText("");
        lblTestDiaChi.setText("");
        lblTestTenCN.setText("");
        lbltestSdt.setText("");
        cboMa.setSelectedItem("");
        btnTim1.setEnabled(false);
        btnLoc1.setEnabled(true);
    }//GEN-LAST:event_cboGTItemStateChanged

    private void cboMaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSđt.setText("");
        txtTen.setText("");
        lblTestDiaChi.setText("");
        lblTestTenCN.setText("");
        lbltestSdt.setText("");
        cboGT.setSelectedItem("");
        cboPB.setSelectedItem("");
        btnTim1.setEnabled(false);
        btnLoc1.setEnabled(true);
    }//GEN-LAST:event_cboMaItemStateChanged

    private void cboPBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPBItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSđt.setText("");
        txtTen.setText("");
        lblTestDiaChi.setText("");
        lblTestTenCN.setText("");
        lbltestSdt.setText("");
        btnTim1.setEnabled(false);
        btnLoc1.setEnabled(true);
        cboMa.setSelectedItem("");
    }//GEN-LAST:event_cboPBItemStateChanged

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HelpCNTK().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void btnLoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoc1ActionPerformed
        // TODO add your handling code here:
        if (cboMa.getSelectedItem().equals("") && cboGT.getSelectedItem().equals("") && cboPB.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn Mã hoặc Giới Tính hoặc Phòng Ban của Công nhân để Lọc");
        }
        if (!cboMa.getSelectedItem().equals("")) {
            locCN();
        } else if (!cboGT.getSelectedItem().equals("") && cboPB.getSelectedItem().equals("")) {
            locCNbyGT();
        } else if (!cboPB.getSelectedItem().equals("") && cboGT.getSelectedItem().equals("")) {
            locCNPB();
        } else if (!cboPB.getSelectedItem().equals("") && !cboGT.getSelectedItem().equals("")) {
            locPBvsGT();
        }

    }//GEN-LAST:event_btnLoc1ActionPerformed

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
        //findTenNV();
       if (txtSđt.getText().equals("") && txtTen.getText().equals("") && txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn cần nhập Tên hoặc Số Điện Thoại hoặc Địa Chỉ để Tìm Kiếm");
        }
        if ((!txtSđt.getText().equals("")) && (txtTen.getText().equals("") && txtDiaChi.getText().equals(""))) {
            regexSđt();
            if (regexSđt()) {
                findSdt();
                if (modelCN.getRowCount() == 0) {
                    lbltestSdt.setText("Không có công nhân nào có số điện thoại này");
                }
            }
        }
        if ((!txtTen.getText().equals("")) && (txtSđt.getText().equals("") && txtDiaChi.getText().equals(""))) {
            regexTenCn();
            if (regexTenCn()) {
                findTenCN();
                if (modelCN.getRowCount() == 0) {
                    lblTestTenCN.setText("Không có công nhân nào có tên này");
                }
            }
        }
        if ((!txtDiaChi.getText().equals("")) && (txtTen.getText().equals("") && txtSđt.getText().equals(""))) {
            regexDiaChi();
            if (regexDiaChi()) {
                findDiaChi();
                if (modelCN.getRowCount() == 0) {
                    lblTestDiaChi.setText("Không có công nhân nào có địa chỉ này");
                }
            }
        }

    }//GEN-LAST:event_btnTim1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaRong();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnLoc1;
    private custom_button.MyButton btnTim1;
    private custom_button.MyButton btnXoa;
    private javax.swing.JComboBox<String> cboGT;
    private javax.swing.JComboBox<String> cboMa;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTestDiaChi;
    private javax.swing.JLabel lblTestTenCN;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel lbltestSdt;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSđt;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private void loadComboBoxMa() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMa.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getMaCN());
        }
        cboMa.setSelectedIndex(0);
    }

    private void loadComboBoxGT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboGT.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        model.addElement("Nam");
        model.addElement("Nữ");
        cboGT.setSelectedIndex(0);
    }

    private void loadComboBoxPB() {
        DefaultComboBoxModel modelPB = (DefaultComboBoxModel) cboPB.getModel();
        modelPB.removeAllElements();
        modelPB.addElement("");
        modelPB.addElement("Tất cả");
        try {
            List<PhongBan> lstpb = pbdao.getAllPB();
            for (PhongBan pb : lstpb) {
                modelPB.addElement(pb.getMaPB());
            }
            cboPB.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDatabase() {
        modelCN.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
        System.out.println(modelCN.getRowCount());
    }

    public boolean regexSđt() {
        String sdt = txtSđt.getText();
        Pattern pattern = Pattern.compile("^(0){1}[0-9]{9,10}$");
        if (sdt.equals("")) {
            lbltestSdt.setText("Số điện thoại không được để trống");
            return false;
        } else if (!pattern.matcher(sdt).find()) {
            lbltestSdt.setText("Số điện thoại phải bắt đầu bằng số 0 và có 10 đến 11 số");
            return false;
        } else {
            lbltestSdt.setText("");
            return true;
        }
    }

    public boolean regexDiaChi() {
        String diachi = txtDiaChi.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (diachi.equals("")) {
            lblTestDiaChi.setText("Địa chỉ không được để trống");
            return false;
        } else if (!pattern.matcher(diachi).find()) {
            lblTestDiaChi.setText("Đại chỉ phải bắt đầu bằng chữ viết Hoa mỗi từ");
            return false;
        } else {
            lblTestDiaChi.setText("");
            return true;
        }
    }

    public boolean regexTenCn() {
        String ten = txtTen.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (ten.equals("")) {
            lblTestTenCN.setText("Tên công nhân không được để trống");
            return false;
        } else if (!pattern.matcher(ten).find()) {
            lblTestTenCN.setText("Tên công nhân phải bắt đầu bằng chữ viết Hoa mỗi từ");
            return false;
        } else {
            lblTestTenCN.setText("");
            return true;
        }

    }
}
