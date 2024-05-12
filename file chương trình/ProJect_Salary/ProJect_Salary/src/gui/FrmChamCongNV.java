/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;
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
import entity.BangCongNV;
import java.awt.Dimension;
import java.time.LocalDate;
/**
 *
 * @author ADMIN
 */
public class FrmChamCongNV extends javax.swing.JPanel {
    ChamCongNVDAO chamCongdao;
    DefaultTableModel modelNV;
    DefaultTableModel modelChamCong;
    java.util.Date datetoday = new java.util.Date();
    /**
     * Creates new form ChamCongNV
     */
    public FrmChamCongNV() {
        initComponents();
        try {
          ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chamCongdao = new ChamCongNVDAO();
        modelNV = (DefaultTableModel) tblNV.getModel();
        readTableNV();
        modelChamCong = (DefaultTableModel) tblCong.getModel();
        readTableChamCong();
        DateChamCong.setDate(datetoday);
        //tblCong.setPreferredScrollableViewportSize(tblCong.getPreferredSize());
        //clickTableChamCong();
        
    }

    public void readTableNV(){
        modelNV.setRowCount(0);
        List<NhanVien> lst = chamCongdao.getNVChamCong();
        //SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),nv.getMaPB().getMaPB(),nv.getSdt()};
            modelNV.addRow(row);
        }
        int a = modelNV.getRowCount();
        System.out.println(a);
        tblCong.setPreferredSize(new Dimension(1250,1000));
        //System.out.println(modelNV.getRowCount());
    }
    
    public void clickTable(){
        int row = tblNV.getSelectedRow();
        txtMaNv.setText(modelNV.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
        txtSDT.setText(modelNV.getValueAt(row, 3).toString());
        txtPB.setText(modelNV.getValueAt(row, 2).toString());
        
    }
    
    
    
    public void ktraChk(){
        if(chkDiLam.isSelected()==true && chkPhep.isSelected()==true){
            JOptionPane.showMessageDialog(this, "không hop le");
            chkDiLam.setSelected(false);
            chkPhep.setSelected(false);
        }
        
        if(chkPhep.isSelected()==true && chkTangCa.isSelected()==true){
            JOptionPane.showMessageDialog(this, "Không hop le");
            chkPhep.setSelected(false);
            chkTangCa.setSelected(false);
        }
        
        if(chkTangCa.isSelected()==true && chkDiLam.isSelected()==false){
            JOptionPane.showMessageDialog(this, "Không hop le");
            chkTangCa.setSelected(false);
        }
        
    }
    
    public void readTableChamCong(){
        modelChamCong.setRowCount(0);
        List<BangCongNV> lst = chamCongdao.getAllBC();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (BangCongNV nv : lst) {
            Object[] row = {nv.getMaCong(),nv.getMaNhanVien().getMaNV(),nv.getTenNhanVien().getHoTen(),date.format(nv.getNgayCham()),
                            nv.isDiLam(),nv.isNghiPhep(),nv.isTangCa()};
            modelChamCong.addRow(row);
        }
        System.out.println(modelChamCong.getRowCount());
    }
    
    public void clickTableChamCong(){
        int row = tblCong.getSelectedRow();
        txtMaNv.setText(modelChamCong.getValueAt(row, 1).toString());
        txtTenNV.setText(modelChamCong.getValueAt(row, 2).toString());
        try {
            DateChamCong.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelChamCong.getValueAt(row, 3)));
            
        } catch (ParseException ex) {
            Logger.getLogger(FrmCapNhatNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(modelChamCong.getValueAt(row, 4).toString());
        if(modelChamCong.getValueAt(row, 4).toString().equals("true")){
           chkDiLam.setSelected(true); 
        }
        else{
            chkDiLam.setSelected(false);
        }
        
        if(modelChamCong.getValueAt(row, 5).toString().equals("true")){
            chkPhep.setSelected(true);
        }
        else{
            chkPhep.setSelected(false);
        }
        if(modelChamCong.getValueAt(row, 6).toString().equals("true")){
            chkTangCa.setSelected(true);
        }
        else{
            chkTangCa.setSelected(false);
        }
        
        txtPB.setText("");
        txtSDT.setText("");
    }
    
    public void phanCong(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateChamCong.getDate()));
        String maNV = txtMaNv.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        Date ns = Date.valueOf(df.format(DateChamCong.getDate()));
        boolean diLam = chkDiLam.isSelected();
        boolean nghiphep = chkPhep.isSelected();
        boolean tangca = chkTangCa.isSelected();
        NhanVien nv1 = new NhanVien(maNV, tenNV);
        BangCongNV bcnv = new BangCongNV(nv1, nv1, ns, diLam, nghiphep, tangca);
        List<BangCongNV> lst_check = chamCongdao.checkCongNV(df.format(DateChamCong.getDate()));
       
        if(lst_check.contains(bcnv)){
            JOptionPane.showMessageDialog(null, "nhan vien duoc cham roi");
        }
       else{
            if(chamCongdao.chamCongNV(bcnv)){
//                Object[] row = {bcnv.getMaCong(),bcnv.getMaNhanVien().getMaNV(),bcnv.getTenNhanVien().getHoTen(),date.format(bcnv.getNgayCham()),
//                            bcnv.isDiLam(),bcnv.isNghiPhep(),bcnv.isTangCa()};
//                modelChamCong.addRow(row);
                    modelChamCong.setRowCount(0);
                     List<BangCongNV> lst = chamCongdao.getAllBC();
                    SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
                    for (BangCongNV nv : lst) {
                            Object[] row = {nv.getMaCong(),nv.getMaNhanVien().getMaNV(),nv.getTenNhanVien().getHoTen(),date1.format(nv.getNgayCham()),
                            nv.isDiLam(),nv.isNghiPhep(),nv.isTangCa()};
                             modelChamCong.addRow(row);
                    }
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        }
        
    }
    
    public void suaChamCong(){
        int row = tblCong.getSelectedRow();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maNv = txtMaNv.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        Date nC = Date.valueOf(df.format(DateChamCong.getDate()));
        boolean diLam = chkDiLam.isSelected();
        boolean nghiphep = chkPhep.isSelected();
        boolean tangca = chkTangCa.isSelected();
        NhanVien nv = new NhanVien(maNv, tenNV);
        BangCongNV bcnv = new BangCongNV(nv, nv, nC, diLam, nghiphep, tangca);
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa","Attention!",JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            if(chamCongdao.suaChamCong(bcnv)){
                readTableChamCong();
                JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
            }else{
                JOptionPane.showMessageDialog(this, "Cap nhat that bai");
            }
        }
        //xoaTrang();
        
    }
    
    public void locTheoNgay(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayCham = Date.valueOf(df.format(DateChamCong.getDate()));
        modelChamCong.setRowCount(0);
        List<BangCongNV> lst = chamCongdao.locTheoNgay(ngayCham);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        for (BangCongNV nv : lst) {
            Object[] row = {nv.getMaCong(),nv.getMaNhanVien().getMaNV(),nv.getTenNhanVien().getHoTen(),date.format(nv.getNgayCham()),
                            nv.isDiLam(),nv.isNghiPhep(),nv.isTangCa()};
            modelChamCong.addRow(row);
        }
    }
    
    public boolean regexNgaySinh() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate date = LocalDate.now();
        String nam = "" + date.getYear();
        String thang = "" + date.getMonthValue();
        String ngay = "" + date.getDayOfMonth();

        Date date1 = Date.valueOf(df.format(DateChamCong.getDate()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        simpleDateFormat1.applyPattern("MM");
        simpleDateFormat2.applyPattern("dd");
        String nam1 = simpleDateFormat.format(date1);
        String thang1 = simpleDateFormat1.format(date1);
        String ngay1 = simpleDateFormat2.format(date1);

        int namngaycham = Integer.parseInt(nam1);
        int thangngaycham = Integer.parseInt(thang1);
        int ngaycham = Integer.parseInt(ngay1);

        int namnay = Integer.parseInt(nam);
        int thangnay = Integer.parseInt(thang);
        int ngaynay = Integer.parseInt(ngay);

        if (namnay - namngaycham < 0) {
            JOptionPane.showMessageDialog(this, "Ngày chấm không được sau ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (namnay - namngaycham > 0) {
            JOptionPane.showMessageDialog(this, "Ngày chấm không được trước ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (namnay == namngaycham) {
            if (thangnay - thangngaycham < 0) {
                JOptionPane.showMessageDialog(this, "Ngày chấm không được sau ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (thangnay - thangngaycham > 0) {
                JOptionPane.showMessageDialog(this, "Ngày chấm không được trước ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (thangnay == thangngaycham) {
                if (ngaynay - ngaycham < 0) {
                    JOptionPane.showMessageDialog(this, "Ngày chấm không được sau ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else if (ngaynay - ngaycham > 0) {
                    JOptionPane.showMessageDialog(this, "Ngày chấm không được trước ngày hiện tại", "invalidation", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMaNv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPB = new javax.swing.JTextField();
        DateChamCong = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        chkDiLam = new javax.swing.JCheckBox();
        chkPhep = new javax.swing.JCheckBox();
        chkTangCa = new javax.swing.JCheckBox();
        myButton4 = new custom_button.MyButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCong = new javax.swing.JTable();
        btnSuaCong = new custom_button.MyButton();
        btnLoc = new custom_button.MyButton();
        btnLocAll = new custom_button.MyButton();
        jLabel11 = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("CHẤM CÔNG NHÂN VIÊN");
        add(jLabel1);
        jLabel1.setBounds(460, 10, 307, 45);

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "Phòng ban", "SÐT"
            }
        ));
        tblNV.setPreferredSize(new java.awt.Dimension(300, 400));
        tblNV.setRowHeight(30);
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 60, 570, 220);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Ngày chấm");
        add(jLabel2);
        jLabel2.setBounds(900, 80, 80, 30);

        txtMaNv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMaNv.setEnabled(false);
        add(txtMaNv);
        txtMaNv.setBounds(680, 80, 140, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Họ Tên:");
        add(jLabel3);
        jLabel3.setBounds(620, 140, 60, 30);

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenNV.setEnabled(false);
        add(txtTenNV);
        txtTenNV.setBounds(680, 140, 200, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("SÐT:");
        add(jLabel4);
        jLabel4.setBounds(620, 190, 60, 30);

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.setEnabled(false);
        add(txtSDT);
        txtSDT.setBounds(680, 190, 200, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("PB:");
        add(jLabel5);
        jLabel5.setBounds(620, 240, 40, 30);

        txtPB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPB.setEnabled(false);
        add(txtPB);
        txtPB.setBounds(680, 240, 60, 30);

        DateChamCong.setDateFormatString("yyyy-MM-dd");
        add(DateChamCong);
        DateChamCong.setBounds(990, 80, 138, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Mã NV:");
        add(jLabel6);
        jLabel6.setBounds(620, 80, 60, 30);

        chkDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkDiLam.setText("Có mặt/Vắng mặt");
        chkDiLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiLamActionPerformed(evt);
            }
        });
        add(chkDiLam);
        chkDiLam.setBounds(1000, 140, 150, 30);

        chkPhep.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkPhep.setText("Có Phép");
        chkPhep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhepActionPerformed(evt);
            }
        });
        add(chkPhep);
        chkPhep.setBounds(1000, 180, 110, 30);

        chkTangCa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkTangCa.setText("Tăng ca");
        chkTangCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTangCaActionPerformed(evt);
            }
        });
        add(chkTangCa);
        chkTangCa.setBounds(1000, 220, 85, 26);

        myButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add1.png"))); // NOI18N
        myButton4.setText("Chấm công");
        myButton4.setBorderColor(new java.awt.Color(102, 102, 102));
        myButton4.setColorhover(new java.awt.Color(0, 255, 255));
        myButton4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        myButton4.setRadius(30);
        myButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton4ActionPerformed(evt);
            }
        });
        add(myButton4);
        myButton4.setBounds(430, 300, 140, 50);

        jScrollPane2.setBorder(null);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(20, 15));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1250, 5000));
        jScrollPane2.setViewportView(tblCong);

        tblCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Công", "Mã NV", "Họ Tên", "Ngày Chấm", "Có mặt", "Nghỉ phép", "Tăng ca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCong.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCong.setPreferredSize(new java.awt.Dimension(1250, 10000));
        tblCong.setRowHeight(30);
        tblCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCong);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 370, 1200, 310);

        btnSuaCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update1.png"))); // NOI18N
        btnSuaCong.setText("Sửa công");
        btnSuaCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSuaCong.setRadius(30);
        btnSuaCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCongActionPerformed(evt);
            }
        });
        add(btnSuaCong);
        btnSuaCong.setBounds(220, 300, 160, 50);

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
        btnLoc.setBounds(810, 300, 140, 50);

        btnLocAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loc.png"))); // NOI18N
        btnLocAll.setText("Lọc tất cả");
        btnLocAll.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLocAll.setRadius(30);
        btnLocAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocAllActionPerformed(evt);
            }
        });
        add(btnLocAll);
        btnLocAll.setBounds(620, 300, 140, 50);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel11.setText("Trợ giúp");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        add(jLabel11);
        jLabel11.setBounds(1120, 0, 90, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void chkDiLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiLamActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkDiLamActionPerformed

    private void myButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton4ActionPerformed
        // TODO add your handling code here:
//        if(regexNgaySinh()){
//            phanCong();
//        }
        phanCong();
    }//GEN-LAST:event_myButton4ActionPerformed

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        // TODO add your handling code here:
        clickTable();
        
    }//GEN-LAST:event_tblNVMouseClicked

    private void chkPhepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhepActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkPhepActionPerformed

    private void chkTangCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTangCaActionPerformed
        // TODO add your handling code here:
        ktraChk();
    }//GEN-LAST:event_chkTangCaActionPerformed

    private void tblCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongMouseClicked
        // TODO add your handling code here:
        clickTableChamCong();
    }//GEN-LAST:event_tblCongMouseClicked

    private void btnSuaCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCongActionPerformed
        // TODO add your handling code here:
        suaChamCong();
    }//GEN-LAST:event_btnSuaCongActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        locTheoNgay();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnLocAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocAllActionPerformed
        // TODO add your handling code here:
        readTableChamCong();
    }//GEN-LAST:event_btnLocAllActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HelpChamCongNV().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChamCong;
    private custom_button.MyButton btnLoc;
    private custom_button.MyButton btnLocAll;
    private custom_button.MyButton btnSuaCong;
    private javax.swing.JCheckBox chkDiLam;
    private javax.swing.JCheckBox chkPhep;
    private javax.swing.JCheckBox chkTangCa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private custom_button.MyButton myButton4;
    private javax.swing.JTable tblCong;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtMaNv;
    private javax.swing.JTextField txtPB;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
