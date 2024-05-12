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

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class FrmTimKiemSanPham extends javax.swing.JPanel {
    DefaultTableModel modelSP;
    private  SanPhamDAO spDao;
    /**
     * Creates new form TimKiemSanPham
     */
    public FrmTimKiemSanPham() {
        initComponents();
         ConnectDB1.getInstance().connect();
         spDao = new SanPhamDAO();
        
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        DocSanPhamVaoTable();
        loadComboBoxMaSP();
        dayDuLieuDonGia();
        dayDuLieuLenSoLuong();
        dayDuLieuDonViTinh();
       
    }
     public void DocSanPhamVaoTable(){
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        for (SanPham sp : listSP) {
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
        }
    }
     public void locSPTheoMa(){
        if(cboMaSP.getSelectedItem().equals("Tất cả")){
            DocSanPhamVaoTable();
        }
        else if(cboMaSP.getSelectedItem().equals("")){
            modelSP.setRowCount(0);
        }
        else{
            String txtMa = cboMaSP.getSelectedItem().toString();
            List<SanPham> lst = spDao.timSanPhamTheoMa(txtMa);
         
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
        }
    }
     public void dayDuLieuDonGia(){
         DefaultComboBoxModel model = (DefaultComboBoxModel) cboDGFrom.getModel();
          model.addElement("");
          model.addElement(3000);
          model.addElement(5000);
          model.addElement(7000);
          model.addElement(9000);
          model.addElement(11000);
          DefaultComboBoxModel model1 = (DefaultComboBoxModel) cboDGTo.getModel();
           model1.addElement("");
          model1.addElement(8000);
          model1.addElement(10000);
          model1.addElement(12000);
          model1.addElement(14000);
          model1.addElement(16000);
     }
     public void dayDuLieuLenSoLuong(){
         DefaultComboBoxModel model = (DefaultComboBoxModel) cboSoLuongFrom.getModel();
          model.addElement("");
          model.addElement(0);
          model.addElement(51);
          model.addElement(101);
          model.addElement(201);
        

          DefaultComboBoxModel model1 = (DefaultComboBoxModel) cboSLTo.getModel();
           model1.addElement("");
          model1.addElement(50);
          model1.addElement(100);
          model1.addElement(150);
          model1.addElement(200);
          model1.addElement(250);
          model1.addElement(300);
 
     }
     public void dayDuLieuDonViTinh(){
         DefaultComboBoxModel model = (DefaultComboBoxModel) cboDonViTinh.getModel();
           model.addElement("");
          model.addElement("Thùng");
          model.addElement("Chai");
          model.addElement("Lốc");
          model.addElement("Lon");
 
     }
      public void locSPTheoDonViTinh(){
          
          
          
            String txtThuongHieu = cboDonViTinh.getSelectedItem().toString();
            List<SanPham> lst = spDao.timSanPhamTheoDonViTinh(txtThuongHieu);
         
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
        
    }
     public void locDonGia(){
         
         float txtTu = Float.parseFloat( cboDGFrom.getSelectedItem().toString());
         float txtDen = Float.parseFloat( cboDGTo.getSelectedItem().toString());
         
            List<SanPham> lst = spDao.timSanPhamTheoGia(txtTu, txtDen);
           
          
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
     }
      public void locSoLuong(){
      
         int txtTu = Integer.parseInt( cboSoLuongFrom.getSelectedItem().toString());
         int txtDen = Integer.parseInt( cboSLTo.getSelectedItem().toString());
         
            List<SanPham> lst = spDao.timSanPhamTheoSoLuong(txtTu, txtDen);
           
          
            modelSP.setRowCount(0);
            for (SanPham sp : lst) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
            }
     }
     
     public void loadComboBoxMaSP(){
         DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaSP.getModel();
         model.removeAllElements();
         model.addElement("");
         model.addElement("Tất cả");
         List<SanPham> listSP = spDao.getALLSP();
         for (SanPham sp : listSP) {
             model.addElement(sp.getMaSP());
         }
         cboMaSP.setSelectedIndex(0);
     }
     public void timKiemSanPhamTheoTen(){
         String txtTenSP = this.txtTenSP.getText().toString().trim();
         List<SanPham> listSP = spDao.timSPTheoTen(txtTenSP);
         modelSP.setRowCount(0);
         for (SanPham sp : listSP) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
         }
     }
     public void timKiemSanPhamTheoThuongHieu(){
         String txtThuongHieu = this.txtThuongHieu.getText().toString().trim();
          List<SanPham> listSP = spDao.timSPTheoThuongHieu(txtThuongHieu);
         modelSP.setRowCount(0);
         for (SanPham sp : listSP) {
              Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()};
            modelSP.addRow(row);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboMaSP = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        txtThuongHieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cboDGFrom = new javax.swing.JComboBox<>();
        cboDGTo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboSoLuongFrom = new javax.swing.JComboBox<>();
        cboSLTo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboDonViTinh = new javax.swing.JComboBox<>();
        btnTim = new custom_button.MyButton();
        btnLoc = new custom_button.MyButton();
        jLabel14 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 1015));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tìm Kiếm Sản Phẩm");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 420, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 100, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Sản Phẩm:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 100, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Thương Hiệu:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 90, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Đơn Giá Từ: ");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 80, 16));

        cboMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", " " }));
        cboMaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMaSPMouseClicked(evt);
            }
        });
        cboMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaSPActionPerformed(evt);
            }
        });
        add(cboMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 300, 30));

        txtTenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenSPMouseClicked(evt);
            }
        });
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });
        add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 291, 30));

        txtThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThuongHieuMouseClicked(evt);
            }
        });
        add(txtThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 291, 30));

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
        tblSanPham.setRowHeight(30);
        jScrollPane1.setViewportView(tblSanPham);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 319, 1194, 400));

        cboDGFrom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboDGFromMouseClicked(evt);
            }
        });
        cboDGFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDGFromActionPerformed(evt);
            }
        });
        add(cboDGFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 80, 30));

        cboDGTo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboDGToMouseClicked(evt);
            }
        });
        add(cboDGTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 101, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Đến");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 66, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số Lượng Từ:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 90, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Đến");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 66, -1));

        cboSoLuongFrom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSoLuongFromMouseClicked(evt);
            }
        });
        add(cboSoLuongFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 170, 80, 30));

        cboSLTo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSLToMouseClicked(evt);
            }
        });
        add(cboSLTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 101, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Đơn vị tính:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 80, -1));

        cboDonViTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboDonViTinhMouseClicked(evt);
            }
        });
        add(cboDonViTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 88, 30));

        btnTim.setText("Tìm");
        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTim.setRadius(30);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 140, 50));

        btnLoc.setText("Lọc");
        btnLoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoc.setRadius(30);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });
        add(btnLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 140, 50));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel14.setText("Trợ giúp");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cboDGFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDGFromActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboDGFromActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if(!cboMaSP.getSelectedItem().equals("")){
             locSPTheoMa();
        }
         else if(!cboDonViTinh.getSelectedItem().equals("")){
             locSPTheoDonViTinh();
         }
          else if((!(cboSoLuongFrom.getSelectedItem().equals(""))) &&(!(cboSLTo.getSelectedItem().equals(""))) ){
               locSoLuong();
         }
         else if((!(cboDGTo.getSelectedItem().equals(""))) &&(!(cboDGTo.getSelectedItem().equals(""))) ){
               locDonGia();
         }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if((!txtTenSP.getText().equals(""))&&(txtThuongHieu.getText().equals(""))){
            timKiemSanPhamTheoTen();
        }
        else if((!txtThuongHieu.getText().equals(""))&&(txtTenSP.getText().equals(""))){
            timKiemSanPhamTheoThuongHieu();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new HelpSPTK().setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void cboMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaSPActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_cboMaSPActionPerformed

    private void cboMaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMaSPMouseClicked
        // TODO add your handling code here:
           cboDGFrom.setSelectedItem("");
        cboDGTo.setSelectedItem("");
        cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
        cboDonViTinh.setSelectedItem("");
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboMaSPMouseClicked

    private void cboDGFromMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDGFromMouseClicked
        // TODO add your handling code here:
        cboMaSP.setSelectedItem("");
        cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
        cboDonViTinh.setSelectedItem("");
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboDGFromMouseClicked

    private void cboDGToMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDGToMouseClicked
        // TODO add your handling code here:
        cboMaSP.setSelectedItem("");
        cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
        cboDonViTinh.setSelectedItem("");
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboDGToMouseClicked

    private void cboSoLuongFromMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSoLuongFromMouseClicked
        // TODO add your handling code here:
        cboMaSP.setSelectedItem("");
        cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
        cboDonViTinh.setSelectedItem("");
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboSoLuongFromMouseClicked

    private void cboSLToMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSLToMouseClicked
        // TODO add your handling code here:
        cboMaSP.setSelectedItem("");
          cboDGFrom.setSelectedItem("");
        cboDGTo.setSelectedItem("");
     
        cboDonViTinh.setSelectedItem("");
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboSLToMouseClicked

    private void txtThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThuongHieuMouseClicked
        // TODO add your handling code here:
         cboMaSP.setSelectedItem("");
          
          cboDGFrom.setSelectedItem("");
        cboDGTo.setSelectedItem("");
      cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
       
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(false);
        btnTim.setEnabled(true);
    }//GEN-LAST:event_txtThuongHieuMouseClicked

    private void txtTenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenSPMouseClicked
        // TODO add your handling code here:
         cboMaSP.setSelectedItem("");
          
          cboDGFrom.setSelectedItem("");
        cboDGTo.setSelectedItem("");
      cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
       
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(false);
        btnTim.setEnabled(true);
    }//GEN-LAST:event_txtTenSPMouseClicked

    private void cboDonViTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDonViTinhMouseClicked
        // TODO add your handling code here:
         cboMaSP.setSelectedItem("");
          
          cboDGFrom.setSelectedItem("");
        cboDGTo.setSelectedItem("");
      cboSoLuongFrom.setSelectedItem("");
        cboSLTo.setSelectedItem("");
       
        txtTenSP.setText("");
        txtThuongHieu.setText("");
        btnLoc.setEnabled(true);
        btnTim.setEnabled(false);
    }//GEN-LAST:event_cboDonViTinhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnLoc;
    private custom_button.MyButton btnTim;
    private javax.swing.JComboBox<String> cboDGFrom;
    private javax.swing.JComboBox<String> cboDGTo;
    private javax.swing.JComboBox<String> cboDonViTinh;
    private javax.swing.JComboBox<String> cboMaSP;
    private javax.swing.JComboBox<String> cboSLTo;
    private javax.swing.JComboBox<String> cboSoLuongFrom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThuongHieu;
    // End of variables declaration//GEN-END:variables
}
