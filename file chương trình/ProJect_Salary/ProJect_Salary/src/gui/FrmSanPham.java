/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

/**
 *
 * @author admin
 */
import connect.ConnectDB1;
import javax.swing.table.DefaultTableModel;
import dao.SanPhamDAO;
import entity.SanPham;
import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FrmSanPham extends javax.swing.JPanel {
    
    DefaultTableModel modelSP;
    private SanPhamDAO spDao;
    private String duongDanAnh;
    private String anh;
    private ArrayList<SanPham> listSP;
    public String tempImage;

    /**
     * Creates new form CongDoanSanPham
     */
    public FrmSanPham() {
        initComponents();
        ConnectDB1.getInstance().connect();
        spDao = new SanPhamDAO();
        
        listSP = (ArrayList<SanPham>) spDao.getALLSP();
        modelSP = (DefaultTableModel) tblSanPham.getModel();
        DocSanPhamVaoTable();
//        themDuLieuVaoBang(listSP);

    }
    
    public void DocSanPhamVaoTable() {
        modelSP.setRowCount(0);
        List<SanPham> listSP = spDao.getALLSP();
        SanPham sanpham = new SanPham();
        
        for (SanPham sp : listSP) {
            
            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()};
            modelSP.addRow(row);
        }
    }
//     private void themDuLieuVaoBang(ArrayList<SanPham> listSP){
//        
//        for (SanPham sp : listSP) {
//            modelSP.addRow(new Object[] {
//                sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()              
//            });
//        }
//    }

    public void themSP() {
        txtMaSP.setText(spDao.getMaSPTuDong());
        String tenSP = txtTenSP.getText().trim();
        String thuongHieu = (String) cboThuongHieu.getSelectedItem();
        float donGia = Float.parseFloat(txtDonGia.getText().trim());
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        String donViTinh = cboDonViTinh.getSelectedItem().toString();
        
        String anh = tempImage.trim();
        
        int index = anh.lastIndexOf('\\');//lấy path 
       
        String name = anh.substring(index + 1);
      
        String hinhAnh = "\\\\src\\\\image\\\\" + name;
   
        List<SanPham> listSP = spDao.getALLSP();
        String maSP = txtMaSP.getText().trim();
        
        SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh, hinhAnh);
        //if(regexTenSP() && regexDonGia() && regexSoLuong()){
            if (listSP.contains(sp)) {
                JOptionPane.showMessageDialog(this, "TRÙNG MÃ SẢN PHẨM");
            } else {
                if (spDao.createSP(sp)) {
                    Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getThuongHieu(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh(), sp.getAnh()};
                    modelSP.addRow(row);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                }
            //}
        }
    }
    
    public void xoaSP() {
        int row = tblSanPham.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này ?", "Warning", JOptionPane.YES_NO_OPTION);
        if (ques == JOptionPane.YES_OPTION) {
            String maSP = (String) tblSanPham.getValueAt(row, 0);
            spDao.removeSP(maSP);
//                themDuLieuVaoBang(listSP);
            DocSanPhamVaoTable();
            
        }
        xoaTrang();
    }
    
    public void xoaTrang() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        cboThuongHieu.setSelectedItem("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        cboDonViTinh.setSelectedItem("");
        
    }
    
    public void clickTable() {
        int row = tblSanPham.getSelectedRow();
        txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
        txtTenSP.setText(modelSP.getValueAt(row, 1).toString());
        cboThuongHieu.setSelectedItem(modelSP.getValueAt(row, 2).toString());
        txtDonGia.setText(modelSP.getValueAt(row, 3).toString());
        txtSoLuong.setText(modelSP.getValueAt(row, 4).toString());
        cboDonViTinh.setSelectedItem(modelSP.getValueAt(row, 5).toString());
        lblAnh.setIcon(ResizeImage(String.valueOf(System.getProperty("user.dir") + modelSP.getValueAt(row, 6).toString())));
        
    }
    
    public void SuaSP() {
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
        }
        if (row >= 0) {
            String maSP = txtMaSP.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            String thuongHieu = (String) cboThuongHieu.getSelectedItem();
            float donGia = Float.parseFloat(txtDonGia.getText().trim());
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            String donViTinh = cboDonViTinh.getSelectedItem().toString();
              String anh = tempImage.trim();
        
        int index = anh.lastIndexOf('\\');//lấy path 
       
        String name = anh.substring(index + 1);
      
        String hinhAnh = "\\\\src\\\\image\\\\" + name;
            SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh, hinhAnh);
            
            int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa", "Attention!", JOptionPane.YES_NO_OPTION);
            //if(regexTenSP() && regexDonGia() && regexSoLuong()){
            if (ques == JOptionPane.YES_OPTION) {
                if (spDao.suaSanPham(sp)) {
//                 themDuLieuVaoBang(listSP);
                    DocSanPhamVaoTable();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            //}
            }
        }
    }
//     private void suaSanPham(SanPham SanPhamNew) {
//	spDao.suaSanPham(SanPhamNew);
//       // xoaDuLieuBang();
//        ArrayList<SanPham> listDichVuNew = (ArrayList<SanPham>) spDao.getALLSP();//lấy lại danh sách mới
//        themDuLieuVaoBang(listDichVuNew);
//	JOptionPane.showMessageDialog(this,"Sửa thành công.");	
//    }

    public void xoaDuLieuBang() {
        
        modelSP.getDataVector().removeAllElements();
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboDonViTinh = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        lblTBTen = new javax.swing.JLabel();
        lblTbDonGia = new javax.swing.JLabel();
        lblTbSoLuong = new javax.swing.JLabel();
        btnThem1 = new custom_button.MyButton();
        btnXoa1 = new custom_button.MyButton();
        btnSua1 = new custom_button.MyButton();
        jLabel14 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 1015));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sản Phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Sản Phẩm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số Lượng:");

        txtMaSP.setEditable(false);
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Thương Hiệu", "Đơn Giá", "Số Lượng", "Đơn Vị Tính", "Ảnh"
            }
        ));
        tblSanPham.setPreferredSize(new java.awt.Dimension(525, 720));
        tblSanPham.setRowHeight(30);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Thương Hiệu:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Đơn Giá:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Đơn Vị Tính:");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coca-Cola", "PepSi", "Sprite", "Fanta", "Sting", "Warrior", "Aquafina", "Danasi", "Nutriboost", "TH True Juice Milk", " " }));

        cboDonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thùng", "Lốc", "Chai", "Lon", " " }));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhsp2.jpg"))); // NOI18N

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btnChonAnh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonAnh)
                .addContainerGap())
        );

        lblTBTen.setForeground(new java.awt.Color(255, 51, 51));

        lblTbDonGia.setForeground(new java.awt.Color(255, 51, 51));

        lblTbSoLuong.setForeground(new java.awt.Color(255, 51, 51));

        btnThem1.setText("Thêm");
        btnThem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem1.setRadius(30);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa1.setText("Xóa ");
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa1.setRadius(30);
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnSua1.setText("Sửa");
        btnSua1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua1.setRadius(30);
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                    .addComponent(txtTenSP)
                                    .addComponent(txtDonGia)
                                    .addComponent(lblTBTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTbDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTbSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                .addComponent(txtSoLuong)
                                .addComponent(cboThuongHieu, 0, 248, Short.MAX_VALUE)
                                .addComponent(cboDonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(lblTBTen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(cboDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTbDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel14.setText("Trợ giúp");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(119, 119, 119)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(571, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        try {
//            JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
JFileChooser f = new JFileChooser("D:\\demo_netbean\\ProJect_Salary\\src\\image");


//            f.setDialogTitle("Mở file");
//            f.showOpenDialog(null);
//            File ftenanh = f.getSelectedFile();
//            duongDanAnh = ftenanh.getAbsolutePath();
//            if (duongDanAnh != null) {
//                lblAnh.setIcon(ResizeImage(String.valueOf(duongDanAnh)));
//                System.out.println(duongDanAnh);
//
//            }
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
           
            f.showOpenDialog(null);
            int r = f.showSaveDialog(null);
            tempImage = f.getSelectedFile().getAbsolutePath();
        
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, "bạn chưa chọn ảnh");
            System.out.print(duongDanAnh);
        }

    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        if(regexTenSP() && regexDonGia() && regexSoLuong()){
            themSP();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        xoaSP();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        if(regexTenSP() && regexDonGia() && regexSoLuong()){
        SuaSP();
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new HelpSP().setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
//    public boolean checkInput(String tenSP, float donGia, int soLuong) {
//        
////        if (!tenSP.matches()) {
////            JOptionPane.showMessageDialog(this, "Tên sản phẩm không đúng định dạng");
////            return false;
////        }
//        
//        if (tenSP.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tên sản phẩm Không được để trống");
//            txtTenSP.requestFocus();
//            return false;
//        }
//        if (donGia < 0) {
//            JOptionPane.showMessageDialog(this, "Đơn giá phải > 0");
//            txtDonGia.requestFocus();
//            return false;
//        }
//        if (soLuong < 0) {
//            JOptionPane.showMessageDialog(this, "Số lượng phải > 0");
//            txtSoLuong.requestFocus();
//            return false;
//        }
//        return true;
//    }
    public boolean regexTenSP(){
        String ten = txtTenSP.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\s[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if(ten.equals("")){
            lblTBTen.setText("*Tên công nhân không được để trống");
            txtTenSP.requestFocus();
            return false;
      
        }
        else if(!pattern.matcher(ten).find()){
            lblTBTen.setText("*Tên công nhân không đúng định dạng");
            txtTenSP.requestFocus();
            return false;
        }
        else{
            lblTBTen.setText("*");
            return true;
        }
    }
    public boolean regexDonGia(){
        Double donGia = Double.parseDouble(txtDonGia.getText());
        
         if(donGia<0){
            lblTbDonGia.setText("*Đơn giá phải >0");
            txtDonGia.requestFocus();
            return false;
            
        }
       
        else{
            lblTbDonGia.setText("*");
            return true;
        }
    }
    public boolean regexSoLuong(){
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        
        if(soLuong<0){
            lblTbSoLuong.setText("*Số lượng phải lớn hơn 0");
            txtSoLuong.requestFocus();
            return false;
        }
        else{
           lblTbSoLuong.setText("*");
           return true;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private custom_button.MyButton btnSua1;
    private custom_button.MyButton btnThem1;
    private custom_button.MyButton btnXoa1;
    private javax.swing.JComboBox<String> cboDonViTinh;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblTBTen;
    private javax.swing.JLabel lblTbDonGia;
    private javax.swing.JLabel lblTbSoLuong;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

}
