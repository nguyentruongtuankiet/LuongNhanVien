/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import dao.CongNhanDAO;
import dao.CongDoanSPDAO;
import dao.PhanCongDAO;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong;

import entity.SanPham;
import java.util.ArrayList;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class FrmPhanCongCN extends javax.swing.JPanel {

    /**
     * Creates new form PhanCongCN
     */
    DefaultTableModel modelCD;
    DefaultTableModel modelCN;
    DefaultTableModel modelPC;
    private SanPhamDAO spDao;
    private CongNhanDAO cnDao;
    private CongDoanSPDAO cdDao;
    private PhanCongDAO pcDao;

    public FrmPhanCongCN() {
        initComponents();

        ConnectDB1.getInstance().connect();
        spDao = new SanPhamDAO();
        cnDao = new CongNhanDAO();
        cdDao = new CongDoanSPDAO();
        pcDao = new PhanCongDAO();
        modelCD = (DefaultTableModel) tblCongDoan.getModel();
        modelPC = (DefaultTableModel) tblPhanCong.getModel();

//        DocSanPhamVaoTable();
        loadComboBoxMa();
        loadComboBoxTen();
        loadComboBoxMaSP();
        loadComboBoxMaCD();
        loadComboBoxTenCD();
        loadPhanCong();

    }

    public void loadComboBoxMa() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaCN.getModel();
        model.removeAllElements();

        List<CongNhan> lst = cnDao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getMaCN());
        }
        cboMaCN.setSelectedIndex(0);
    }

    public void loadComboBoxMaCD() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaCD.getModel();
        model.removeAllElements();

        List<CongDoan> lst = cdDao.getCongDoan();
        for (CongDoan cd : lst) {
            model.addElement(cd.getMaCD());
        }
        cboMaCD.setSelectedIndex(0);
    }

    public void loadComboBoxTenCD() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenCD.getModel();
        model.removeAllElements();

        List<CongDoan> lst = cdDao.getCongDoan();
        for (CongDoan cd : lst) {
            model.addElement(cd.getTenCD());
        }
        cboTenCD.setSelectedIndex(0);
    }

    public void loadPhanCong() {
        modelPC.setRowCount(0);
        List<PhanCong> listPC = pcDao.getPhanCong();
        for (PhanCong pc : listPC) {
            Object[] row = {pc.getMaCD().getMaCD(), pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
            modelPC.addRow(row);
        }
    }

    public void ThemPhanCong() {

        String maCN = cboMaCN.getSelectedItem().toString();
        String tenCN = cboTenCN.getSelectedItem().toString();
        String maCD = cboMaCD.getSelectedItem().toString();
        String tenCD = cboTenCD.getSelectedItem().toString();
        String maSP = cboSanPham.getSelectedItem().toString();
        String tenSP = cboTenSP.getSelectedItem().toString();
        int soCD = Integer.parseInt(txtSoCD.getText());
        boolean trangThai;
        String tt = txtTrangThai.getText().toString();
        if (tt.equals("chưa hoàn thành")) {
            trangThai = false;
        } else {
            trangThai = true;
        }
        int soluong = Integer.parseInt(txtSoLuongCL.getText());
        CongNhan cn = new CongNhan(maCN, tenCN);
        CongDoan cd = new CongDoan(maCD, tenCD);
        int row1 = tblCongDoan.getSelectedRow();

        SanPham sp = new SanPham(maSP, tenSP);
        PhanCong pc = new PhanCong(cn, cn, sp, sp, cd, cd);
        List<PhanCong> listPC = pcDao.getPhanCong();

        if (trangThai == true) {
            JOptionPane.showMessageDialog(this, "CÔNG ĐOẠN ĐÃ ĐƯỢC HOÀN THÀNH");
        } else if (trangThai == false && soCD == 1) {
            System.out.println(trangThai);
            if (listPC.contains(pc)) {
                JOptionPane.showMessageDialog(this, "CÔNG ĐOẠN ĐÃ ĐƯỢC PHÂN CÔNG");
            } else {
                if (pcDao.createPC(pc)) {
                    Object[] row = {pc.getMaCD().getMaCD(), pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
                    modelPC.addRow(row);
                    JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG");
                }
            }
        } else if (trangThai == false) {
            System.out.println(trangThai);
            String ktr = modelCD.getValueAt(soCD - 2, 4).toString();
            System.out.println(trangThai);
            if (ktr.equals("đã hoàn thành")) {
                if (listPC.contains(pc)) {
                    JOptionPane.showMessageDialog(this, "CÔNG ĐOẠN ĐÃ ĐƯỢC PHÂN CÔNG");
                } else {
                    if (pcDao.createPC(pc)) {
                        Object[] row = {pc.getMaCD().getMaCD(), pc.getMaCN().getMaCN(), pc.getTenCN().getTenCN(), pc.getTenCD().getTenCD(), pc.getMaSP().getMaSP(), pc.getTenSP().getTenSP()};
                        modelPC.addRow(row);
                        JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "CÔNG ĐOẠN TRƯỚC ĐÓ CHƯA HOÀN THÀNH");
            }
        }
    }

    public void loadComboBoxMaSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();

        List<SanPham> lst = spDao.getALLSP();
        for (SanPham sp : lst) {
            model.addElement(sp.getMaSP());
        }
        cboSanPham.setSelectedIndex(0);
    }

    public void loadComboBoxTen() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenCN.getModel();
        model.removeAllElements();

        List<CongNhan> lst = cnDao.getALLCN();
        for (CongNhan cn : lst) {
            model.addElement(cn.getTenCN());
        }
        cboMaCN.setSelectedIndex(0);
    }

    public void layTenCNTheoMa() {
        String maCN = cboMaCN.getSelectedItem().toString();

        String listCN = pcDao.getTenCNTheoMa(maCN);
        cboTenCN.setSelectedItem(listCN);

    }

    public void layTenSPTheoMa() {
        String maSP = cboSanPham.getSelectedItem().toString();

        String listSP = pcDao.getTenSPTheoMa(maSP);
        cboTenSP.setSelectedItem(listSP);

    }

    public void layMaTheoTen() {
        String tenCN = cboTenCN.getSelectedItem().toString();

        String listCN = pcDao.getMaCNTheoTen(tenCN);
        cboMaCN.setSelectedItem(listCN);

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboMaCN = new javax.swing.JComboBox<>();
        cboMaCD = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTenCD = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhanCong = new javax.swing.JTable();
        btnPhanCong = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();
        btnSua = new custom_button.MyButton();
        txtSoLuongCL = new javax.swing.JTextField();
        cboTenCN = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCongDoan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        lblTenSP = new javax.swing.JLabel();
        cboTenSP = new javax.swing.JComboBox<>();
        txtSoCD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Phân Công Công Nhân");
        add(jLabel1);
        jLabel1.setBounds(510, 10, 250, 29);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mã Công Nhân:");
        add(jLabel3);
        jLabel3.setBounds(490, 80, 120, 22);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Mã Công Đoạn:");
        add(jLabel4);
        jLabel4.setBounds(490, 130, 120, 22);

        cboMaCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaCNActionPerformed(evt);
            }
        });
        add(cboMaCN);
        cboMaCN.setBounds(630, 70, 150, 35);

        cboMaCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(cboMaCD);
        cboMaCD.setBounds(630, 120, 150, 35);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Tên Công Nhân:");
        add(jLabel5);
        jLabel5.setBounds(890, 80, 120, 22);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tên Công Đoạn:");
        add(jLabel6);
        jLabel6.setBounds(890, 130, 120, 22);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Số Lượng Cần Làm:");
        add(jLabel7);
        jLabel7.setBounds(870, 190, 140, 22);

        cboTenCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenCDActionPerformed(evt);
            }
        });
        add(cboTenCD);
        cboTenCD.setBounds(1040, 120, 150, 35);
        add(jLabel8);
        jLabel8.setBounds(1201, 108, 158, 0);
        add(jLabel9);
        jLabel9.setBounds(1201, 108, 158, 0);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Trạng Thái: ");
        add(jLabel12);
        jLabel12.setBounds(520, 240, 80, 20);

        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangThai.setText("_");
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });
        add(txtTrangThai);
        txtTrangThai.setBounds(630, 230, 150, 35);

        tblPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MaCD", "MaCN", "TenCN", "TenCD", "MaSP", "TenSP"
            }
        ));
        tblPhanCong.setRowHeight(30);
        tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhanCong);

        add(jScrollPane2);
        jScrollPane2.setBounds(7, 351, 1200, 340);

        btnPhanCong.setText("Phân Công");
        btnPhanCong.setBorderColor(new java.awt.Color(102, 102, 102));
        btnPhanCong.setColorhover(new java.awt.Color(0, 255, 255));
        btnPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPhanCong.setRadius(30);
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });
        add(btnPhanCong);
        btnPhanCong.setBounds(560, 280, 110, 50);

        btnXoa.setText("Xóa");
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
        btnXoa.setBounds(790, 280, 110, 50);

        btnSua.setText("Sửa");
        btnSua.setBorderColor(new java.awt.Color(102, 102, 102));
        btnSua.setColorhover(new java.awt.Color(0, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setRadius(30);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        add(btnSua);
        btnSua.setBounds(1010, 280, 110, 50);

        txtSoLuongCL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtSoLuongCL);
        txtSoLuongCL.setBounds(1040, 180, 150, 35);

        cboTenCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTenCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenCNActionPerformed(evt);
            }
        });
        add(cboTenCN);
        cboTenCN.setBounds(1040, 70, 150, 35);

        tblCongDoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã CD", "Tên CD", "So Luong Can Lam", "Số công đoạn", "Trạng thái"
            }
        ));
        tblCongDoan.setRowHeight(25);
        tblCongDoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongDoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCongDoan);

        add(jScrollPane3);
        jScrollPane3.setBounds(10, 77, 460, 250);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Sản Phẩm");
        add(jLabel11);
        jLabel11.setBounds(20, 40, 70, 22);

        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });
        add(cboSanPham);
        cboSanPham.setBounds(100, 30, 150, 35);
        add(lblTenSP);
        lblTenSP.setBounds(265, 24, 0, 0);

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cboTenSP);
        cboTenSP.setBounds(270, 30, 180, 35);

        txtSoCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtSoCD);
        txtSoCD.setBounds(630, 180, 150, 35);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Số Công Đoạn:");
        add(jLabel2);
        jLabel2.setBounds(500, 190, 110, 22);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel14.setText("Trợ giúp");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        add(jLabel14);
        jLabel14.setBounds(1100, 0, 90, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void cboMaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaCNActionPerformed
        // TODO add your handling code here:
        layTenCNTheoMa();

    }//GEN-LAST:event_cboMaCNActionPerformed

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanCongActionPerformed
        // TODO add your handling code here:
        ThemPhanCong();

    }//GEN-LAST:event_btnPhanCongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaPC();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cboTenCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenCNActionPerformed
        // TODO add your handling code here:
        layMaTheoTen();

    }//GEN-LAST:event_cboTenCNActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
        String maSP = cboSanPham.getSelectedItem().toString();
        List<CongDoan> list = cdDao.LayCacTPTrongCD(maSP);
        modelCD.setRowCount(0);
        for (CongDoan congDoan : list) {

            Object[] row = {congDoan.getMaCD(), congDoan.getTenCD(), congDoan.getSoLuong(), congDoan.getMaRangBuoc(), congDoan.kiemTraCongDoan() ? "đã hoàn thành" : "chưa hoàn thành"};
            modelCD.addRow(row);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenSP.getModel();
        model.removeAllElements();
        List<SanPham> listTen = spDao.getALLSP();
        for (SanPham sp : listTen) {
            model.addElement(sp.getTenSP());
        }

        cboTenSP.setSelectedIndex(0);
        layTenSPTheoMa();

    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void tblCongDoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongDoanMouseClicked
//        TODO add your handling code here:
        int row = tblCongDoan.getSelectedRow();
        cboMaCD.setSelectedItem(modelCD.getValueAt(row, 0).toString());
        cboTenCD.setSelectedItem(modelCD.getValueAt(row, 1).toString());
        txtSoLuongCL.setText(modelCD.getValueAt(row, 2).toString());
        txtSoCD.setText(modelCD.getValueAt(row, 3).toString());
        txtTrangThai.setText(modelCD.getValueAt(row, 4).toString());
//        System.out.println( modelCD.getValueAt(row, 4).toString());

    }//GEN-LAST:event_tblCongDoanMouseClicked

    private void tblPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanCongMouseClicked
        // TODO add your handling code here:
        int row = tblPhanCong.getSelectedRow();
        cboMaCD.setSelectedItem(modelPC.getValueAt(row, 0).toString());
        cboMaCN.setSelectedItem(modelPC.getValueAt(row, 1).toString());
        cboTenCN.setSelectedItem(modelPC.getValueAt(row, 2).toString());
        cboTenCD.setSelectedItem(modelPC.getValueAt(row, 3).toString());

    }//GEN-LAST:event_tblPhanCongMouseClicked

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void cboTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenCDActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenCDActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new HelpCNPC().setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnPhanCong;
    private custom_button.MyButton btnSua;
    private custom_button.MyButton btnXoa;
    private javax.swing.JComboBox<String> cboMaCD;
    private javax.swing.JComboBox<String> cboMaCN;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboTenCD;
    private javax.swing.JComboBox<String> cboTenCN;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JTable tblCongDoan;
    private javax.swing.JTable tblPhanCong;
    private javax.swing.JTextField txtSoCD;
    private javax.swing.JTextField txtSoLuongCL;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables

    private void xoaPC() {
        int row = tblPhanCong.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này ?", "Warning", JOptionPane.YES_NO_OPTION);
        if (ques == JOptionPane.YES_OPTION) {
            String maCD = (String) tblPhanCong.getValueAt(row, 0);
            String maCN = (String) tblPhanCong.getValueAt(row, 1);

            pcDao.removePC(maCD, maCN);
            loadPhanCong();
        }
    }

//    private boolean rangBuocCD(CongDoan cd) {
//        
//        boolean trangthai = cd.kiemTraCongDoan();
//        if(trangthai == false && cd.getMaRangBuoc()== 1){
//            return true;
//            
//        }
//        else
//            return false;
//    }
}
