/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import dao.CongNhanDAO;
import entity.CongNhan;
import dao.PhongBanDAO;
import entity.PhongBan;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import jdk.jshell.execution.Util;

/**
 *
 * @author Tuan Kiet Admin
 */
public class FrmCapNhatCN extends javax.swing.JPanel {

    private CongNhanDAO cndao;
    private PhongBanDAO pbdao;
    DefaultTableModel modelCN;


    /**
     * Creates new form pnCapNhatCN
     */
    public FrmCapNhatCN() {
        try {
            ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        cndao = new CongNhanDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã CN;Mã PB; Tên CN;Sđt;Địa chỉ;Giới tính;Ngày sinh".split(";");
        modelCN = new DefaultTableModel(header, 0);
        jTable1.setModel(modelCN);

        readDatabase();
        readPB();
        btnXoa1.setEnabled(false);
        btnXoa1.setColorhover(Color.WHITE);
        btnXoa1.setColorclick(Color.WHITE);
        btnSua1.setColorhover(Color.WHITE);
        btnSua1.setColorclick(Color.WHITE);
        btnSua1.setEnabled(false);
    }

    public void themCN() {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateNgaySinh.getDate()));
        String maCN = txtMa.getText().trim();
        String tenCN = txtTenCn.getText().trim();
        boolean gioiTinh = cboGT.getSelectedItem() == "Nam" ? true : false;
        String sdt = txtSđt.getText().trim();
        String diaChi = txtDiachi.getText().trim();
        System.out.println(DateNgaySinh.toString());
        Date ns = Date.valueOf(df.format(DateNgaySinh.getDate()));
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        List<CongNhan> lst_check = cndao.getALLCN();
        CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ns, gioiTinh, pb);
        if (lst_check.contains(cn)) {
            JOptionPane.showMessageDialog(this, "TRÙNG MÃ CÔNG NHÂN");
        } else {
            if (cndao.createNV(cn)) {
                Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(),
                    cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
                modelCN.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm that bai");
            }
        }
        xoaTrang();
    }

    public void xoaTrang() {
        txtMa.setText("");
        txtTenCn.setText("");
        txtDiachi.setText("");
        txtSđt.setText("");
//        DateNgaySinh.setDate(null);
        cboGT.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);

    }

    public void clickTable() {
        int row = jTable1.getSelectedRow();
        txtMa.setText(modelCN.getValueAt(row, 0).toString());

        txtTenCn.setText(modelCN.getValueAt(row, 2).toString());
        try {
            DateNgaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String) modelCN.getValueAt(row, 6)));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCapNhatCN.class.getName()).log(Level.SEVERE, null, ex);
        }
        cboGT.setSelectedItem(modelCN.getValueAt(row, 5).toString());
        txtDiachi.setText(modelCN.getValueAt(row, 4).toString());
        txtSđt.setText(modelCN.getValueAt(row, 3).toString());
        cboPB.setSelectedItem(modelCN.getValueAt(row, 1).toString());
        txtMa.setEditable(false);
        lblTestMaCN.setText("");
        lblTestDiaChi.setText("");
        lblTestNgaySinh.setText("");
        lblTestTenCN.setText("");
        lbltestSdt.setText("");

    }

    public void xoa() {
        int row = jTable1.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
        if (ques == JOptionPane.YES_OPTION) {
            String ma = (String) jTable1.getValueAt(row, 0);
            cndao.removeCN(ma);
            readDatabase();
        }
//        txtMa.setEditable(true);
        xoaTrang();
        btnThem1.setEnabled(true);
        btnXoa1.setEnabled(false);
        btnSua1.setEnabled(false);
        
        btnXoa1.setColorhover(Color.WHITE);
        btnXoa1.setColorclick(Color.WHITE);
        btnSua1.setColorhover(Color.WHITE);
        btnSua1.setColorclick(Color.WHITE);
        
        btnThem1.setColorhover(Color.ORANGE);
        btnThem1.setColorclick(Color.RED);
    }

    public void SuaCN() {
        int row = jTable1.getSelectedRow();
        //////////////////////////////////////////////
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maCN = txtMa.getText().trim();
        String tenCN = txtTenCn.getText().trim();
        boolean gioiTinh = cboGT.getSelectedItem() == "Nam" ? true : false;
        String sdt = txtSđt.getText().trim();
        String diaChi = txtDiachi.getText().trim();
        Date ns = Date.valueOf(df.format(DateNgaySinh.getDate()));
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ns, gioiTinh, pb);
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa", "Attention!", JOptionPane.YES_NO_OPTION);
        if (ques == JOptionPane.YES_OPTION) {
            if (cndao.capNhat(cn)) {
                readDatabase();
                JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
            } else {
                JOptionPane.showMessageDialog(this, "Cap nhat that bai");
            }
        }
        txtMa.setEditable(true);
        btnThem1.setEnabled(true);
        btnSua1.setEnabled(false);
        btnXoa1.setEnabled(false);
        
        btnXoa1.setColorhover(Color.WHITE);
        btnXoa1.setColorclick(Color.WHITE);
        btnSua1.setColorhover(Color.WHITE);
        btnSua1.setColorclick(Color.WHITE);
        
        btnThem1.setColorhover(Color.ORANGE);
        btnThem1.setColorclick(Color.RED);
        xoaTrang();

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
        lblMaCn = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtSđt = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JLabel();
        txtPB = new javax.swing.JLabel();
        cboPB = new javax.swing.JComboBox<>();
        DateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtTenCn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cboGT = new javax.swing.JComboBox<>();
        lblTestMaCN = new javax.swing.JLabel();
        lblTestTenCN = new javax.swing.JLabel();
        lbltestSdt = new javax.swing.JLabel();
        lblTestNgaySinh = new javax.swing.JLabel();
        lblTestDiaChi = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnXoaTrang1 = new custom_button.MyButton();
        btnThem1 = new custom_button.MyButton();
        btnXoa1 = new custom_button.MyButton();
        btnSua1 = new custom_button.MyButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("CÔNG NHÂN");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 160, -1));

        lblMaCn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaCn.setText("Mã CN:");
        add(lblMaCn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 60, -1));

        lblSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSdt.setText("Sđt:");
        add(lblSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDiaChi.setText("Địa Chỉ:");
        add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 50, -1));

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });
        add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 59, 220, 32));

        txtSđt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtSđt, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 126, 220, 32));

        txtDiachi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtDiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 220, 34));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên CN:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, -1));

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgaySinh.setText("Ngày Sinh:");
        add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 70, -1));

        txtGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGioiTinh.setText("Giới Tính:");
        add(txtGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 60, -1));

        txtPB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPB.setText("Phòng Ban:");
        add(txtPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 80, -1));

        cboPB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cboPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 197, -1, 34));

        DateNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(DateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 126, 255, 32));

        txtTenCn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenCn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCnActionPerformed(evt);
            }
        });
        add(txtTenCn, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 59, 257, 32));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 324, 1200, 380));

        cboGT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboGT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGTActionPerformed(evt);
            }
        });
        add(cboGT, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 197, 80, 34));

        lblTestMaCN.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestMaCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 94, 410, 30));

        lblTestTenCN.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestTenCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 94, 460, 30));

        lbltestSdt.setForeground(new java.awt.Color(255, 51, 51));
        add(lbltestSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 380, 30));

        lblTestNgaySinh.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 164, 250, 30));

        lblTestDiaChi.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTestDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 370, 29));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel12.setText("Trợ giúp");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 100, 40));

        btnXoaTrang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
        btnXoaTrang1.setText("Xóa Trắng");
        btnXoaTrang1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnXoaTrang1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaTrang1.setRadius(30);
        btnXoaTrang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrang1ActionPerformed(evt);
            }
        });
        add(btnXoaTrang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 160, 50));

        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/create1.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnThem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem1.setRadius(30);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        add(btnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 130, 50));

        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa1.setRadius(30);
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });
        add(btnXoa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 130, 50));

        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update1.png"))); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.setBorderColor(new java.awt.Color(102, 102, 102));
        btnSua1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua1.setRadius(30);
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });
        add(btnSua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 130, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenCnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCnActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void cboGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGTActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        clickTable();
        btnSua1.setEnabled(true);
        btnXoa1.setEnabled(true);
        btnThem1.setEnabled(false); 
        
        btnThem1.setColorhover(Color.WHITE);
        btnThem1.setColorclick(Color.WHITE);
        btnSua1.setColorhover(Color.ORANGE);
        btnSua1.setColorclick(Color.RED);
        btnXoa1.setColorhover(Color.ORANGE);
        btnXoa1.setColorclick(Color.RED);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
            // TODO add your handling code here:
        new HelpCNCP().setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        regexDiaChi();
        regexMaCN();
        regexSđt();
        regexTenCn();
        regexNgaySinh();
        if (regexDiaChi() && regexMaCN() && regexSđt() && regexTenCn() && regexNgaySinh()) {
            themCN();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        xoa(); 
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        regexDiaChi();
        regexSđt();
        regexTenCn();
        regexNgaySinh();
        if (regexDiaChi() && regexSđt() && regexTenCn() && regexNgaySinh()) {
            SuaCN();
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoaTrang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrang1ActionPerformed
        // TODO add your handling code here:
        xoaTrang();
        btnSua1.setEnabled(false);
        btnXoa1.setEnabled(false);
        btnThem1.setEnabled(true);
        
        btnThem1.setColorhover(Color.ORANGE);
        btnThem1.setColorclick(Color.RED);
        
        btnXoa1.setColorhover(Color.WHITE);
        btnXoa1.setColorclick(Color.WHITE);
        btnSua1.setColorhover(Color.WHITE);
        btnSua1.setColorclick(Color.WHITE);
    }//GEN-LAST:event_btnXoaTrang1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgaySinh;
    private custom_button.MyButton btnSua1;
    private custom_button.MyButton btnThem1;
    private custom_button.MyButton btnXoa1;
    private custom_button.MyButton btnXoaTrang1;
    private javax.swing.JComboBox<String> cboGT;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblMaCn;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTestDiaChi;
    private javax.swing.JLabel lblTestMaCN;
    private javax.swing.JLabel lblTestNgaySinh;
    private javax.swing.JLabel lblTestTenCN;
    private javax.swing.JLabel lbltestSdt;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JLabel txtGioiTinh;
    private javax.swing.JTextField txtMa;
    private javax.swing.JLabel txtNgaySinh;
    private javax.swing.JLabel txtPB;
    private javax.swing.JTextField txtSđt;
    private javax.swing.JTextField txtTenCn;
    // End of variables declaration//GEN-END:variables

    private void readDatabase() {
        modelCN.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<CongNhan> lst = cndao.getALLCN();
        for (CongNhan cn : lst) {
            Object[] row = {cn.getMaCN(), cn.getMaPB().getMaPB(), cn.getTenCN(), cn.getSđt(), cn.getDiaChi(), cn.isGioiTinh() == true ? "Nam" : "Nữ", date.format(cn.getNgaySinh())};
            modelCN.addRow(row);
        }
        System.out.println(modelCN.getRowCount());
    }

    public void readPB() {
        DefaultComboBoxModel modelPB = (DefaultComboBoxModel) cboPB.getModel();
        modelPB.removeAllElements();
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

    public boolean regexMaCN() {
        String ma = txtMa.getText();
        Pattern pattern = Pattern.compile("^CN[0-9]{3,}$");
        if (ma.equals("")) {
            lblTestMaCN.setText("Mã công nhân không được để trống");
            return false;
        } else if (!pattern.matcher(ma).find()) {
            lblTestMaCN.setText("Mã công nhân bắt đầu bằng CN và số thứ tự công nhân (tối thiểu 3 số)");
            return false;
        } else {
            lblTestMaCN.setText("");
            return true;
        }
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
        String diachi = txtDiachi.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (diachi.equals("")) {
            lblTestDiaChi.setText("Địa chỉ không được để trống");
            return false;
        }
       else {
            lblTestDiaChi.setText("");
            return true;
        }
    }

    public boolean regexTenCn() {
        String ten = txtTenCn.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
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

    public boolean regexNgaySinh() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate date = LocalDate.now();
        String ngay = "" + date.getYear();

        Date date1 = Date.valueOf(df.format(DateNgaySinh.getDate()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date1);
        int namsn = Integer.parseInt(format);
        int namnay = Integer.parseInt(ngay);
        if (namnay - namsn < 18) {
            lblTestNgaySinh.setText("Tuổi công nhân phải đủ 18 trở lên");
            return false;
        } else {
            lblTestNgaySinh.setText("");
            return true;
        }
    }
}
