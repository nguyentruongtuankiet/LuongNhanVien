/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import entity.SanPham;
import java.util.ArrayList;
import entity.CongDoan;
import dao.CongDoanSPDAO;
import java.awt.Color;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class FrmCongDoanSanPham extends javax.swing.JPanel {

    DefaultTableModel modelSP;
    DefaultTableModel modalCD;
    private final SanPhamDAO spDao;
    private final CongDoanSPDAO cdDao;

    /**
     * Creates new form CongDoanSanPham
     */
    public FrmCongDoanSanPham() {
        initComponents();
        ConnectDB1.getInstance().connect();
        spDao = new SanPhamDAO();
        cdDao = new CongDoanSPDAO();
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        modalCD = (DefaultTableModel) tblCongDoan.getModel();
        DocSanPhamVaoTable();
        DocCongDoanVaoTable();
        txtTrangThai.setEnabled(false);

    }

    public void DocSanPhamVaoTable() {
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        for (SanPham sp : listSP) {
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.soLuongTT(), sp.getDonViTinh()};
            modelSP.addRow(row);
        }
    }

    public void DocCongDoanVaoTable() {
        modalCD.setRowCount(0);
        List<CongDoan> listCD = cdDao.getCongDoan();
        for (CongDoan cd : listCD) {

            Object[] row = {cd.getMaCD(), cd.getMaSP().getMaSP(), cd.getTenSP().getTenSP(), cd.getTenCD(), cd.getDonGiaCD(), cd.getSoLuong(), cd.getMaRangBuoc(), cd.kiemTraCongDoan() ? "đã hoàn thành" : "chưa hoàn thành"};
            modalCD.addRow(row);
        }
    }

    public void themCD() {

        String maCD = txtMaCD.getText().trim();
        String maSP = txtMaSP.getText().trim();
        String tenCD = txtTenCD.getText().trim();
        String tenSP = txtTenSP.getText().trim();
        float donGiaCD = Float.parseFloat(txtGiaCD.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int maRangBuoc = Integer.parseInt(cboMaRangBuoc.getSelectedItem().toString());

        SanPham sp = new SanPham(maSP, tenSP);
        CongDoan cd = new CongDoan(maCD, tenCD, sp, sp, donGiaCD, soLuong, maRangBuoc);
        List<CongDoan> listCD = cdDao.getCongDoan();

//        if (checkInput(maCD, tenCD, donGiaCD)) {
        if (listCD.contains(cd)) {
            JOptionPane.showMessageDialog(this, "TRÙNG MÃ CÔNG ĐOẠN");
        } else {
            if (cdDao.createCD(cd)) {
                Object[] row = {cd.getMaCD(), cd.getMaSP().getMaSP(), cd.getTenSP().getTenSP(), cd.getTenCD(), cd.getDonGiaCD(), cd.getSoLuong(), cd.getMaRangBuoc(), cd.kiemTraCongDoan() ? "đã hoàn thành" : "chưa hoàn thành"};
                modalCD.addRow(row);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
            // }
        }

    }

    public void suaCD() {
        int row = tblCongDoan.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
        }
        if (row >= 0) {
            String maCD = txtMaCD.getText().trim();
            String maSP = txtMaSP.getText().trim();
            String tenCD = txtTenCD.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            float donGiaCD = Float.parseFloat(txtGiaCD.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            int maRangBuoc = Integer.parseInt(cboMaRangBuoc.getSelectedItem().toString());
            Boolean trangThai = Boolean.parseBoolean(txtTrangThai.getText());

            SanPham sp = new SanPham(maSP, tenSP);
            CongDoan cd = new CongDoan(maCD, tenCD, sp, sp, donGiaCD, soLuong, maRangBuoc);
            int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa", "Attention!", JOptionPane.YES_NO_OPTION);
//            if (checkInput(maCD, tenCD, donGiaCD)) {
            if (ques == JOptionPane.YES_OPTION) {
                if (cdDao.editCD(cd)) {
                    DocCongDoanVaoTable();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
//                }
            }
        }
    }

    public void xoaTrang() {
        txtMaCD.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtTenCD.setText("");
        txtGiaCD.setText("");
        txtSoLuong.setText("");
        cboMaRangBuoc.setSelectedIndex(0);
    }

    public void xoaCD() {
        int row = tblCongDoan.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này ?", "Warning", JOptionPane.YES_NO_OPTION);
        if (ques == JOptionPane.YES_OPTION) {
            String maCD = (String) tblCongDoan.getValueAt(row, 0);

            cdDao.removeCD(maCD);
            DocCongDoanVaoTable();
        }
    }

// private void StateChangedTenCĐ(){
//     if(cboMaRangBuoc.getSelectedItem().toString().equals("1")){
//         txtTenCD.setSelectedIndex(0);
//     }
//     else if(cboMaRangBuoc.getSelectedItem().toString().equals("2")){
//          txtTenCD.setSelectedIndex(1);
//     }
////     else if(cboMaRangBuoc.getSelectedItem().toString().equals("3")){
////          txtTenCD.setSelectedIndex(2);
////     }else if(cboMaRangBuoc.getSelectedItem().toString().equals("4")){
////          txtTenCD.setSelectedIndex(3);
////     }else if(cboMaRangBuoc.getSelectedItem().toString().equals("5")){
////          txtTenCD.setSelectedIndex(4);
////     }else if(cboMaRangBuoc.getSelectedItem().toString().equals("6")){
////          txtTenCD.setSelectedIndex(5);
////     }
// }
    public boolean regexMaCD() {
        String ma = txtMaCD.getText();
        Pattern pattern = Pattern.compile("^(CD)[0-9]{2,4}(SP)[0-9]{2,4}$");
        if (ma.equals("")) {
            lblTbMaCD.setText("*Mã công đoạn không được để trống");
            txtMaCD.requestFocus();
            return false;
        } else if (!pattern.matcher(ma).find()) {
            lblTbMaCD.setText("*Mã công đoạn Bắt đầu bằng CD và stt của công đoạn kế đó là SP và stt của sp");
            txtMaCD.requestFocus();
            return false;
        } else {
            lblTbMaCD.setText("*");
            return true;
        }

    }

    public boolean regexDonGiaCD() {
        Double donGia = Double.parseDouble(txtGiaCD.getText());

        if (donGia < 0) {
            lblTbGiaCD.setText("*Giá công đoạn phải >0");
            txtGiaCD.requestFocus();
            return false;

        } else {
            lblTbGiaCD.setText("*");
            return true;
        }
    }

    public void locMaSP() {
        
        modalCD.setRowCount(0);
        List<CongDoan> listCD = cdDao.getCongDoan();
        for (CongDoan cd : listCD) {
            if(cd.getMaSP().getMaSP().equals(txtMaSP.getText())){
            Object[] row = {cd.getMaCD(), cd.getMaSP().getMaSP(), cd.getTenSP().getTenSP(), cd.getTenCD(), cd.getDonGiaCD(), cd.getSoLuong(), cd.getMaRangBuoc(), cd.kiemTraCongDoan() ? "đã hoàn thành" : "chưa hoàn thành"};
            modalCD.addRow(row);
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        txtGiaCD = new javax.swing.JTextField();
        cboMaRangBuoc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCongDoan = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaSP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        lblTbMaCD = new javax.swing.JLabel();
        lblTbTenCD = new javax.swing.JLabel();
        lblTbGiaCD = new javax.swing.JLabel();
        btnLoc = new custom_button.MyButton();
        btnThem1 = new custom_button.MyButton();
        btnXoa1 = new custom_button.MyButton();
        btnSua1 = new custom_button.MyButton();
        jLabel14 = new javax.swing.JLabel();
        txtTenCD = new javax.swing.JTextField();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Công Đoạn Sản Xuất");
        add(jLabel1);
        jLabel1.setBounds(380, 0, 480, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Công Đoạn:");
        add(jLabel2);
        jLabel2.setBounds(10, 90, 110, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Công Đoạn:");
        add(jLabel3);
        jLabel3.setBounds(10, 160, 110, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mã Sản Phẩm:");
        add(jLabel5);
        jLabel5.setBounds(360, 90, 100, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số Công Đoạn:");
        add(jLabel6);
        jLabel6.setBounds(360, 160, 100, 20);

        txtMaCD.setEditable(false);
        txtMaCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCDActionPerformed(evt);
            }
        });
        add(txtMaCD);
        txtMaCD.setBounds(138, 80, 170, 30);
        add(txtGiaCD);
        txtGiaCD.setBounds(140, 220, 170, 30);

        cboMaRangBuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", " " }));
        cboMaRangBuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaRangBuocItemStateChanged(evt);
            }
        });
        cboMaRangBuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMaRangBuocMouseClicked(evt);
            }
        });
        add(cboMaRangBuoc);
        cboMaRangBuoc.setBounds(550, 150, 80, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Giá Công Đoạn:");
        add(jLabel9);
        jLabel9.setBounds(10, 230, 100, 20);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Thương Hiệu", "Đơn Giá", "Số Lượng", "Đơn Vị Tính"
            }
        ));
        tblSanPham.setRowHeight(25);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        add(jScrollPane1);
        jScrollPane1.setBounds(678, 80, 570, 340);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Danh Sách Sản Phẩm");
        add(jLabel4);
        jLabel4.setBounds(1060, 40, 200, 30);

        tblCongDoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CD", "Mã SP", "Tên SP", "Tên CD", "Đơn Giá CD", "Số Lượng", "Số Công Đoạn", "TrangThai"
            }
        ));
        tblCongDoan.setRowHeight(30);
        tblCongDoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongDoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCongDoan);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 440, 1231, 290);
        add(jSeparator1);
        jSeparator1.setBounds(0, 430, 1231, 10);

        txtMaSP.setEditable(false);
        add(txtMaSP);
        txtMaSP.setBounds(460, 80, 170, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên Sản Phẩm");
        add(jLabel10);
        jLabel10.setBounds(10, 300, 100, 20);

        txtTenSP.setEditable(false);
        add(txtTenSP);
        txtTenSP.setBounds(140, 290, 170, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Số Lượng:");
        add(jLabel11);
        jLabel11.setBounds(360, 230, 70, 16);

        txtSoLuong.setEditable(false);
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        add(txtSoLuong);
        txtSoLuong.setBounds(460, 220, 170, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Trạng Thái:");
        add(jLabel7);
        jLabel7.setBounds(360, 300, 70, 20);
        add(txtTrangThai);
        txtTrangThai.setBounds(460, 290, 170, 30);

        lblTbMaCD.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTbMaCD);
        lblTbMaCD.setBounds(110, 120, 200, 20);

        lblTbTenCD.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTbTenCD);
        lblTbTenCD.setBounds(110, 190, 200, 20);

        lblTbGiaCD.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTbGiaCD);
        lblTbGiaCD.setBounds(120, 260, 190, 20);

        btnLoc.setText("Lọc");
        btnLoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoc.setRadius(30);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });
        add(btnLoc);
        btnLoc.setBounds(520, 370, 120, 50);

        btnThem1.setText("Thêm");
        btnThem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem1.setRadius(30);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        add(btnThem1);
        btnThem1.setBounds(70, 370, 120, 50);

        btnXoa1.setText("Xóa");
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa1.setRadius(30);
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });
        add(btnXoa1);
        btnXoa1.setBounds(230, 370, 120, 50);

        btnSua1.setText("Sửa");
        btnSua1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua1.setRadius(30);
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });
        add(btnSua1);
        btnSua1.setBounds(380, 370, 120, 50);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel14.setText("Trợ giúp");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        add(jLabel14);
        jLabel14.setBounds(30, 0, 90, 50);

        txtTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCDActionPerformed(evt);
            }
        });
        add(txtTenCD);
        txtTenCD.setBounds(140, 150, 170, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCDActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
        txtTenSP.setText(modelSP.getValueAt(row, 1).toString());
        txtSoLuong.setText(modelSP.getValueAt(row, 4).toString());
        String macd = cboMaRangBuoc.getSelectedItem().toString();
        int macd1 = Integer.parseInt(macd);
        String macd2;
        if (macd1 < 10) {
            macd2 = "CD0" + macd1;
        } else {
            macd2 = "CD" + macd1;
        }
        String masp = modelSP.getValueAt(row, 0).toString();
        txtMaCD.setText(macd2 + masp);

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void tblCongDoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongDoanMouseClicked
        // TODO add your handling code here:
        int row = tblCongDoan.getSelectedRow();
        txtMaCD.setText(modalCD.getValueAt(row, 0).toString());
        txtMaSP.setText(modalCD.getValueAt(row, 1).toString());
        txtTenSP.setText(modalCD.getValueAt(row, 2).toString());
        txtTenCD.setText(modalCD.getValueAt(row, 3).toString());
        txtGiaCD.setText(modalCD.getValueAt(row, 4).toString());
        txtSoLuong.setText(modalCD.getValueAt(row, 5).toString());
        cboMaRangBuoc.setSelectedItem(modalCD.getValueAt(row, 6).toString());
        txtTrangThai.setText(modalCD.getValueAt(row, 7).toString());

    }//GEN-LAST:event_tblCongDoanMouseClicked

    private void cboMaRangBuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMaRangBuocMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cboMaRangBuocMouseClicked

    private void cboMaRangBuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaRangBuocItemStateChanged
        // TODO add your handling code here:
//        StateChangedTenCĐ();
        String macd = cboMaRangBuoc.getSelectedItem().toString();
        int macd1 = Integer.parseInt(macd);
        String macd2;
        if (macd1 < 10) {
            macd2 = "CD0" + macd1;
        } else {
            macd2 = "CD" + macd1;
        }
        String masp = txtMaSP.getText();
        System.out.println();
        txtMaCD.setText(macd2 + masp);
    }//GEN-LAST:event_cboMaRangBuocItemStateChanged

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        if (regexDonGiaCD()) {
            themCD();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        locMaSP();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        xoaCD();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        suaCD();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new HelpCDSX().setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void txtTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnLoc;
    private custom_button.MyButton btnSua1;
    private custom_button.MyButton btnThem1;
    private custom_button.MyButton btnXoa1;
    private javax.swing.JComboBox<String> cboMaRangBuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTbGiaCD;
    private javax.swing.JLabel lblTbMaCD;
    private javax.swing.JLabel lblTbTenCD;
    private javax.swing.JTable tblCongDoan;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaCD;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
