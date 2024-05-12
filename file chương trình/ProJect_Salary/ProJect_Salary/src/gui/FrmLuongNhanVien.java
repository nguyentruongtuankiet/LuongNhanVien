/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;
import java.sql.Connection;
import connect.ConnectDB1;
import dao.NhanVienHCDAO;
import dao.PhongBanDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entity.NhanVien;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.ObjectOutput;
import javax.swing.JComboBox;
import entity.PhongBan;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import dao.ChamCongNVDAO;
import dao.LuongNVDAO;
import dao.TrinhDoDao;
import entity.BangCongNV;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import entity.LuongNhanVien;
import java.util.Hashtable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author ADMIN
 */
public class FrmLuongNhanVien extends javax.swing.JPanel {
    LuongNVDAO luongNVDAO;
    TrinhDoDao trinhDoDao;
    DefaultTableModel modelNV;
    DefaultTableModel modelLuong;
    LocalDate today;
    
    /**
     * Creates new form pnLuongNhanVien
     */
    public FrmLuongNhanVien() {
        initComponents();
        today = LocalDate.now();
        try {
          ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        luongNVDAO = new LuongNVDAO();
        trinhDoDao = new TrinhDoDao();
        modelNV = (DefaultTableModel) tblNV.getModel();
        modelLuong = (DefaultTableModel) tblLuong.getModel();
        readTableNV();
        loadCboNgay();
        loadCboNam();
        getLuongCB();
        readTableLuong();
        
        
    }
    public void readTableLuong(){
        modelLuong.setRowCount(0);
        DecimalFormat df = new DecimalFormat("###,000");
        List<LuongNhanVien> lst = luongNVDAO.getAllLuong();
        for (LuongNhanVien lnv1 : lst) {
            Object[] row = {lnv1.getMaLuong(),lnv1.getMaNhanVien().getMaNV(),lnv1.getMaNhanVien().getHoTen(), df.format(lnv1.getLuongCB()),
                            lnv1.getTongNgayLam(),lnv1.getTongNgayTangCa(),df.format(lnv1.getPhuCap()),lnv1.getThangLuong(),lnv1.getNamLuong(),df.format(lnv1.tinhLuong())};
                    modelLuong.addRow(row);
        }
    }
    
    public void getLuongCB(){
        txtLuongCB.setEnabled(false);
        txtPhuCap.setEnabled(false);
        txtLuongCB.setText(3920000+"");
        
    }
    
    public void suaTTLuongCB(){
        txtLuongCB.setEnabled(true);
        txtLuongCB.setText("");
        txtLuongCB.requestFocus();

    }
    
    public void luuTT(){
        txtLuongCB.setEnabled(false);
        txtPhuCap.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Luu thanh cong");
    }
    
    public void readTableNV(){
        modelNV.setRowCount(0);
        List<NhanVien> lst = luongNVDAO.getAllNV();
        //SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
            modelNV.addRow(row);
        }
    }
    
    public void clickTable(){
        int row = tblNV.getSelectedRow();
        txtMaNV.setText(modelNV.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
//        if(modelNV.getValueAt(row, 3).toString().equals("Trung cấp")){
//            txtHeSoLuong.setText("2.10");
//        }
//        else if (modelNV.getValueAt(row, 3).toString().equals("Cao đẳng")){
//            txtHeSoLuong.setText("2.41");
//        }
//        else{
//            txtHeSoLuong.setText("2.72");
//        }
//        String hsl = modelNV.getValueAt(row, 3).toString();
          double d= trinhDoDao.getHeSoLuong(modelNV.getValueAt(row, 3).toString());
          txtHeSoLuong.setText(d+"");
        
    }
    
    void loadCboNgay(){
        DefaultComboBoxModel modelThang = (DefaultComboBoxModel) cboThang.getModel();
        modelThang.removeAllElements();
        modelThang.addElement("");
        for (int i = 0; i < 12; i++) {
            modelThang.addElement(i+1);
        }
    }
    
    void loadCboNam(){
        DefaultComboBoxModel modelNam = (DefaultComboBoxModel) cboNam.getModel();
        modelNam.removeAllElements();
        modelNam.addElement("");
        int year = today.getYear();
        modelNam.addElement(year-1);
        modelNam.addElement(year);
        modelNam.addElement(year+1);
        
    }
    
    void getTongSoNgayLam(){
        String maNV = txtMaNV.getText().trim();
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        
        int soNgayLam = luongNVDAO.getTongSoNgayLam(thang, nam, maNV);
        txtSoNgayLam.setText(soNgayLam+"");
        if(soNgayLam >20){
            txtPhuCap.setText(500000+"");
        }else{
            txtPhuCap.setText(0+"");
        }
    }
    
    void getSoNgayTangCa(){
        String maNV = txtMaNV.getText().trim();
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        
        int soNgayLam = luongNVDAO.getTongSoNgayTC(thang, nam, maNV);
        txtSoNgayTangCa.setText(soNgayLam+"");
    }
    
    void tinhLuong(){
        DecimalFormat df = new DecimalFormat("###,000");
        String maNV = txtMaNV.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        double luongCB = Double.parseDouble(txtLuongCB.getText().trim());
        double phuCap = Double.parseDouble(txtPhuCap.getText().trim());
        double hsl = Double.parseDouble(txtHeSoLuong.getText().trim());
        int soNgaylam = Integer.parseInt(txtSoNgayLam.getText().trim());
        int soNgayTC = Integer.parseInt(txtSoNgayTangCa.getText().trim());
        int thang =Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam =Integer.parseInt(cboNam.getSelectedItem().toString());
        NhanVien nv = new NhanVien(maNV, tenNV);
        LuongNhanVien lnv = new LuongNhanVien(nv, nv, luongCB, phuCap, hsl, soNgaylam, soNgayTC, thang, nam);
        List<LuongNhanVien> lst_check = luongNVDAO.getAllLuong();
        if(lst_check.contains(lnv)){
            JOptionPane.showMessageDialog(this, "Nhan vien da duoc tinh luong");
        }else{
            if(luongNVDAO.tinhLuongNV(lnv)){
                modelLuong.setRowCount(0);
                List<LuongNhanVien> lst = luongNVDAO.getAllLuong();
                for (LuongNhanVien lnv1 : lst) {
                    Object[] row = {lnv1.getMaLuong(),lnv1.getMaNhanVien().getMaNV(),lnv1.getMaNhanVien().getHoTen(), df.format(lnv1.getLuongCB()),
                            lnv1.getTongNgayLam(),lnv1.getTongNgayTangCa(),df.format(lnv1.getPhuCap()),lnv1.getThangLuong(),lnv1.getNamLuong(),df.format(lnv1.tinhLuong())};
                    modelLuong.addRow(row);
            }
                JOptionPane.showMessageDialog(this, "Tính luong thành công");
            }
        }
    }
    
    void xoaLuong(){
        int row = tblLuong.getSelectedRow();
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
        if(ques == JOptionPane.YES_OPTION){
            String ma = (String) tblLuong.getValueAt(row, 1);
            luongNVDAO.removeLuong(ma);
            JOptionPane.showMessageDialog(this, "xóa thành công");
            readTableLuong();
        }
    }
    
    void locLuongThangNam(){
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        modelLuong.setRowCount(0);
        DecimalFormat df = new DecimalFormat("###,000");
        List<LuongNhanVien> lst = luongNVDAO.locLuongThangNam(thang, nam);
        for (LuongNhanVien lnv1 : lst) {
            Object[] row = {lnv1.getMaLuong(),lnv1.getMaNhanVien().getMaNV(),lnv1.getMaNhanVien().getHoTen(), df.format(lnv1.getLuongCB()),
                            lnv1.getTongNgayLam(),lnv1.getTongNgayTangCa(),df.format(lnv1.getPhuCap()),lnv1.getThangLuong(),lnv1.getNamLuong(),df.format(lnv1.tinhLuong())};
                    modelLuong.addRow(row);
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

        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtHeSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLuongCB = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPhuCap = new javax.swing.JTextField();
        txtSoNgayLam = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSoNgayTangCa = new javax.swing.JTextField();
        cboThang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        btnLoc = new custom_button.MyButton();
        btnSuaTT = new custom_button.MyButton();
        btnLuuTT = new custom_button.MyButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLuong = new javax.swing.JTable();
        btnTinhLuong = new custom_button.MyButton();
        btnIn = new custom_button.MyButton();
        btnXoaLuong = new custom_button.MyButton();
        jLabel11 = new javax.swing.JLabel();

        setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mã nhân viên:");
        add(jLabel2);
        jLabel2.setBounds(10, 40, 100, 20);

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNV.setEnabled(false);
        add(txtMaNV);
        txtMaNV.setBounds(110, 30, 180, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tên nhân viên:");
        add(jLabel3);
        jLabel3.setBounds(10, 90, 100, 20);

        txtTenNV.setEditable(false);
        txtTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNV.setEnabled(false);
        add(txtTenNV);
        txtTenNV.setBounds(110, 80, 180, 30);

        txtHeSoLuong.setEditable(false);
        txtHeSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHeSoLuong.setEnabled(false);
        add(txtHeSoLuong);
        txtHeSoLuong.setBounds(370, 80, 80, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("HSL:");
        add(jLabel4);
        jLabel4.setBounds(320, 90, 40, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Lương cơ bản:");
        add(jLabel5);
        jLabel5.setBounds(10, 140, 100, 20);

        txtLuongCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtLuongCB);
        txtLuongCB.setBounds(110, 130, 180, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tháng:");
        add(jLabel6);
        jLabel6.setBounds(310, 140, 50, 20);

        txtPhuCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtPhuCap);
        txtPhuCap.setBounds(370, 30, 180, 30);

        txtSoNgayLam.setEditable(false);
        txtSoNgayLam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoNgayLam.setEnabled(false);
        add(txtSoNgayLam);
        txtSoNgayLam.setBounds(110, 180, 180, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Số ngày làm:");
        add(jLabel7);
        jLabel7.setBounds(20, 190, 100, 20);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Số ngày TC:");
        add(jLabel8);
        jLabel8.setBounds(20, 240, 100, 20);

        txtSoNgayTangCa.setEditable(false);
        txtSoNgayTangCa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoNgayTangCa.setEnabled(false);
        add(txtSoNgayTangCa);
        txtSoNgayTangCa.setBounds(110, 230, 180, 30);

        cboThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });
        add(cboThang);
        cboThang.setBounds(370, 130, 180, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Phụ cấp:");
        add(jLabel9);
        jLabel9.setBounds(300, 40, 70, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Năm:");
        add(jLabel10);
        jLabel10.setBounds(320, 190, 50, 20);

        cboNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });
        add(cboNam);
        cboNam.setBounds(370, 180, 180, 30);

        tblNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "PB", "Trình độ"
            }
        ));
        tblNV.setRowHeight(30);
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        add(jScrollPane1);
        jScrollPane1.setBounds(570, 10, 620, 250);

        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loc.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoc.setRadius(30);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });
        add(btnLoc);
        btnLoc.setBounds(330, 230, 90, 40);

        btnSuaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update1.png"))); // NOI18N
        btnSuaTT.setText("Sửa thông tin");
        btnSuaTT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSuaTT.setRadius(30);
        btnSuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTActionPerformed(evt);
            }
        });
        add(btnSuaTT);
        btnSuaTT.setBounds(60, 310, 160, 50);

        btnLuuTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        btnLuuTT.setText("Lưu");
        btnLuuTT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLuuTT.setRadius(30);
        btnLuuTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTTActionPerformed(evt);
            }
        });
        add(btnLuuTT);
        btnLuuTT.setBounds(270, 310, 110, 50);

        tblLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Luong", "Mã NV", "Tên NV", "Lương CB", "Số ngày làm", "Số ngày tăng ca", "Phụ cấp", "Tháng", "Năm", "Tổng lương"
            }
        ));
        tblLuong.setPreferredSize(new java.awt.Dimension(1250, 720));
        tblLuong.setRowHeight(30);
        jScrollPane2.setViewportView(tblLuong);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 380, 1200, 340);

        btnTinhLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tinhluong.png"))); // NOI18N
        btnTinhLuong.setText("Tính lương");
        btnTinhLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTinhLuong.setRadius(30);
        btnTinhLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhLuongActionPerformed(evt);
            }
        });
        add(btnTinhLuong);
        btnTinhLuong.setBounds(450, 310, 140, 50);

        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/in.png"))); // NOI18N
        btnIn.setText("In");
        btnIn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIn.setRadius(30);
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });
        add(btnIn);
        btnIn.setBounds(450, 230, 90, 40);

        btnXoaLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
        btnXoaLuong.setText("Xóa lương");
        btnXoaLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaLuong.setRadius(30);
        btnXoaLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLuongActionPerformed(evt);
            }
        });
        add(btnXoaLuong);
        btnXoaLuong.setBounds(630, 310, 160, 50);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel11.setText("Trợ giúp");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        add(jLabel11);
        jLabel11.setBounds(1070, 310, 90, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboThangActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        getTongSoNgayLam();
        getSoNgayTangCa();
        locLuongThangNam();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnSuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTActionPerformed
        // TODO add your handling code here:
        suaTTLuongCB();
    }//GEN-LAST:event_btnSuaTTActionPerformed

    private void btnLuuTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTTActionPerformed
        // TODO add your handling code here:
        luuTT();
    }//GEN-LAST:event_btnLuuTTActionPerformed

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
        tinhLuong();
    }//GEN-LAST:event_btnTinhLuongActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        int thang =Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam =Integer.parseInt(cboNam.getSelectedItem().toString());
        try {
             Hashtable map = new Hashtable();
        JasperReport rpt = JasperCompileManager.compileReport("src/myreport/rptTinhLuong.jrxml");
            ConnectDB1.getInstance().connect();
          Connection con = ConnectDB1.getConnection();
          map.put("thang", thang);
          map.put("nam", nam);
        JasperPrint p = JasperFillManager.fillReport(rpt,map, ConnectDB1.getConnection());
        JasperViewer.viewReport(p,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnXoaLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLuongActionPerformed
        // TODO add your handling code here:
        xoaLuong();
    }//GEN-LAST:event_btnXoaLuongActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HelpNVLuong().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnIn;
    private custom_button.MyButton btnLoc;
    private custom_button.MyButton btnLuuTT;
    private custom_button.MyButton btnSuaTT;
    private custom_button.MyButton btnTinhLuong;
    private custom_button.MyButton btnXoaLuong;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblLuong;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtLuongCB;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtPhuCap;
    private javax.swing.JTextField txtSoNgayLam;
    private javax.swing.JTextField txtSoNgayTangCa;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
