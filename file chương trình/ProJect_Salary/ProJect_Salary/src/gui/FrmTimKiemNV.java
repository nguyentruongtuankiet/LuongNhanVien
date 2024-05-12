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
import java.awt.Color;
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
 * @author ADMIN
 */
public class FrmTimKiemNV extends javax.swing.JPanel {
    private NhanVienHCDAO nvhcdao;
    private PhongBanDAO pbdao;
    DefaultTableModel modelNV;
    /**
     * Creates new form TimKiemNV
     */
    public FrmTimKiemNV() {
        try{
            ConnectDB1.getInstance().connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        nvhcdao = new NhanVienHCDAO();
        pbdao = new PhongBanDAO();
        String[] header = "Mã NV;Tên NV; Ngày sinh; Ngày TGCT;giới tính;địa chỉ;SĐT;Mã PB;trình độ".split(";");
        modelNV = new DefaultTableModel(header, 0);
        tableNV.setModel(modelNV);
        loadComboBoxMa();
        loadComboBoxGT();
        loadComboBoxPB();
    }
    
    public void loadComboBoxMa(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaNV.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        List<NhanVien> lst = nvhcdao.getALLNV();
        for (NhanVien nv : lst) {
            model.addElement(nv.getMaNV());
        }
        cboMaNV.setSelectedIndex(0);
    }
    
    public void loadComboBoxGT(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboGioiTinh1.getModel();
        model.removeAllElements();
        model.addElement("");
        model.addElement("Tất cả");
        model.addElement("Nam");
        model.addElement("Nữ");
        cboGioiTinh1.setSelectedIndex(0);
    }
    
    public void loadComboBoxPB(){
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
    public void readDatabase(){
        modelNV.setRowCount(0);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        List<NhanVien> lst = nvhcdao.getALLNV();
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
            modelNV.addRow(row);
        }
        System.out.println(modelNV.getRowCount());
    }
    
    public void locNv(){
        if(cboMaNV.getSelectedItem().equals("Tất cả")){
            readDatabase();
        }
        else if(cboMaNV.getSelectedItem().equals("")){
            modelNV.setRowCount(0);
        }
        else{
            String txtMa = cboMaNV.getSelectedItem().toString();
            List<NhanVien> lst = nvhcdao.findNVbyMaNV(txtMa);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelNV.setRowCount(0);
            for (NhanVien nv : lst) {
              Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                modelNV.addRow(row);  
            }
        }
    }
    
    public void locNVbyGT(){
        if (cboGioiTinh1.getSelectedItem().equals("Tất cả")){
            readDatabase();
        }
        else if(cboGioiTinh1.getSelectedItem().equals("")){
            modelNV.setRowCount(0);
        }
        else {
            if(cboGioiTinh1.getSelectedItem().equals("Nam")){
                int check = 1;
                List<NhanVien> lst = nvhcdao.findNVbyGT(check);
                 SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelNV.setRowCount(0);
                 for (NhanVien nv : lst) {
                    Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                    nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                    modelNV.addRow(row);  
                 }
            }
            else{
                int check =0;
                List<NhanVien> lst = nvhcdao.findNVbyGT(check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelNV.setRowCount(0);
                 for (NhanVien nv : lst) {
                    Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                    nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                    modelNV.addRow(row);  
                 }
            }
        }
    }
    
    public void locNVPB(){
        if(cboPB.getSelectedItem().equals("Tất cả")){
            readDatabase();
        }
        else if(cboPB.getSelectedItem().equals("")){
            modelNV.setRowCount(0);
        }
        else{
            String txtPB = cboPB.getSelectedItem().toString();
            List<NhanVien> lst = nvhcdao.findNVbyPB(txtPB);
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            modelNV.setRowCount(0);
            for (NhanVien nv : lst) {
                   Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                   nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                   modelNV.addRow(row);  
                 }
        }
    }
    
    public void locPBvsGT(){
        if(cboGioiTinh1.getSelectedItem().equals("Tất cả")){
            locNVPB();
        }else if(cboPB.getSelectedItem().equals("Tất cả")){
            locNVbyGT();
        }else{
            String txtPB = cboPB.getSelectedItem().toString();
            if(cboGioiTinh1.getSelectedItem().equals("Nam")){
                int check = 1;
                List<NhanVien> lst = nvhcdao.findPBvsGT(txtPB, check);
                 SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelNV.setRowCount(0);
                 for (NhanVien nv : lst) {
                    Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                    nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                    modelNV.addRow(row);  
                 }
            }
            else{
                int check =0;
                List<NhanVien> lst = nvhcdao.findPBvsGT(txtPB, check);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                modelNV.setRowCount(0);
                 for (NhanVien nv : lst) {
                    Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                    nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                    modelNV.addRow(row);  
                 }
            }
        }
    }
    
    public void findTenNV(){
        String txtTen = txtHoTen.getText().toString().trim();
        List<NhanVien> lst = nvhcdao.findTenNV(txtTen);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelNV.setRowCount(0);
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
            nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
            modelNV.addRow(row);  
            }
    }
    
    public void findSdt(){
        String txtsdt = txtSdt.getText().toString().trim();
        List<NhanVien> lst = nvhcdao.findSDT(txtsdt);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelNV.setRowCount(0);
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
            nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
            modelNV.addRow(row);  
            }
    }
    
    public void findDiaChi(){
         String txtDiachi = txtDiaChi.getText().toString().trim();
        List<NhanVien> lst = nvhcdao.findDiaChi(txtDiachi);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        modelNV.setRowCount(0);
        for (NhanVien nv : lst) {
            Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
            nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
            modelNV.addRow(row);  
            }
    }
    
    public void xoaRong(){
        cboMaNV.setSelectedIndex(0);
        cboGioiTinh1.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
    }
    public boolean regexTenNV() {
        String ten = txtHoTen.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (ten.equals("")) {
            JOptionPane.showMessageDialog(this,"Tên nhân viên không được để trống");
            return false;
        } else if (!pattern.matcher(ten).find()) {
            JOptionPane.showMessageDialog(this,"Tên nhân viên phải bắt đầu bằng chữ viết Hoa mỗi từ");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean regexDiaChi() {
        String diachi = txtDiaChi.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9(/)(,)+ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (diachi.equals("")) {
            JOptionPane.showMessageDialog(this,"Địa chỉ không được để trống");
            return false;
        } 
         else {
            return true;
        }
    }
    
    public boolean regexSdt(){
        String sdt = txtSdt.getText();
        if(sdt.equals("")){
            JOptionPane.showMessageDialog(this,"sdt không được để trống");
            return false;
        }else if(!sdt.matches("^0\\d{9}$")){
            JOptionPane.showMessageDialog(this,"SDT phải có 10 kí số");   
            return false;
        }else{
            return true;
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
        cboMaNV = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboGioiTinh1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboPB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        btnLoc = new custom_button.MyButton();
        btnTim = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 1015));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("TÌM KIẾM NHÂN VIÊN");
        add(jLabel1);
        jLabel1.setBounds(500, 26, 310, 26);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ Tên:");
        add(jLabel2);
        jLabel2.setBounds(580, 90, 60, 20);

        cboMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboMaNV.setPreferredSize(new java.awt.Dimension(64, 21));
        cboMaNV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaNVItemStateChanged(evt);
            }
        });
        add(cboMaNV);
        cboMaNV.setBounds(320, 80, 160, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("SÐT:");
        add(jLabel3);
        jLabel3.setBounds(270, 150, 40, 20);

        txtSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSdtMouseClicked(evt);
            }
        });
        add(txtSdt);
        txtSdt.setBounds(320, 140, 160, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã Nhân Viên:");
        add(jLabel4);
        jLabel4.setBounds(210, 90, 100, 20);

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHoTenMouseClicked(evt);
            }
        });
        add(txtHoTen);
        txtHoTen.setBounds(650, 80, 220, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");
        add(jLabel5);
        jLabel5.setBounds(590, 150, 60, 20);

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiaChiMouseClicked(evt);
            }
        });
        add(txtDiaChi);
        txtDiaChi.setBounds(650, 140, 220, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính:");
        add(jLabel6);
        jLabel6.setBounds(250, 220, 60, 20);

        cboGioiTinh1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboGioiTinh1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboGioiTinh1.setPreferredSize(new java.awt.Dimension(64, 21));
        cboGioiTinh1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGioiTinh1ItemStateChanged(evt);
            }
        });
        add(cboGioiTinh1);
        cboGioiTinh1.setBounds(320, 210, 160, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phòng ban:");
        add(jLabel7);
        jLabel7.setBounds(570, 220, 80, 20);

        cboPB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboPB.setPreferredSize(new java.awt.Dimension(64, 21));
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
        cboPB.setBounds(650, 210, 140, 30);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1200, 502));

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên NV ", "Ngày sinh", "Ngày TGCT", "Giới tính", "Địa chỉ", "SÐT", "Mã PB", "Trình độ"
            }
        ));
        tableNV.setPreferredSize(new java.awt.Dimension(1250, 480));
        tableNV.setRowHeight(40);
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNV);

        add(jScrollPane2);
        jScrollPane2.setBounds(0, 362, 1200, 340);

        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loc.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.setBorderColor(new java.awt.Color(102, 102, 102));
        btnLoc.setColorhover(new java.awt.Color(0, 255, 255));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoc.setRadius(30);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });
        add(btnLoc);
        btnLoc.setBounds(340, 290, 140, 50);

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTim.setText("Tìm Kiếm");
        btnTim.setBorderColor(new java.awt.Color(102, 102, 102));
        btnTim.setColorhover(new java.awt.Color(0, 255, 255));
        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTim.setRadius(30);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        add(btnTim);
        btnTim.setBounds(560, 290, 160, 50);

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
        btnXoa.setBounds(800, 290, 160, 50);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/support1.png"))); // NOI18N
        jLabel11.setText("Trợ giúp");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        add(jLabel11);
        jLabel11.setBounds(1120, 20, 90, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void cboPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPBActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tableNVMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if(cboMaNV.getSelectedItem().equals("") && cboGioiTinh1.getSelectedItem().equals("") && cboPB.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn cần chọn Mã hoặc Giới Tính hoặc Phòng Ban của Nhân Viên để Lọc");
        }
        if(!cboMaNV.getSelectedItem().equals("")){
            locNv();
        }
        else if (cboGioiTinh1.getSelectedItem().equals("") && !cboPB.getSelectedItem().equals("")){
            locNVPB();
            
        }
        else if(cboPB.getSelectedItem().equals("") && !cboGioiTinh1.getSelectedItem().equals("")){
            locNVbyGT();
        }else if(!cboPB.getSelectedItem().equals("") && !cboGioiTinh1.getSelectedItem().equals("")){
            locPBvsGT();
        }
       
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        //findTenNV();
        if (txtSdt.getText().equals("") && txtHoTen.getText().equals("") && txtDiaChi.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn cần nhập Tên hoặc Số Điện Thoại hoặc Địa Chỉ để Tìm Kiếm");
        }
        if((!txtSdt.getText().equals(""))&&(txtHoTen.getText().equals("") && txtDiaChi.getText().equals(""))){
            if(regexSdt()){
                findSdt();
                if(modelNV.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "không có nhân viên có sdt này");
                }
            }
        }
        if((!txtHoTen.getText().equals(""))&&(txtSdt.getText().equals("") && txtDiaChi.getText().equals(""))){
            if(regexTenNV()){
                findTenNV();
                if(modelNV.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "không có nhân viên có tên này");
                }
            }
        }
        if((!txtDiaChi.getText().equals(""))&&(txtSdt.getText().equals("") && txtHoTen.getText().equals(""))){
                findDiaChi();
                if(modelNV.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "không có nhân viên có Địa Chỉ này");
                }
            
        }
        
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaRong();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSdtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSdtMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtHoTen.setText("");
        btnTim.setEnabled(true);
        btnLoc.setEnabled(false);
        cboMaNV.setSelectedItem("");
        cboGioiTinh1.setSelectedItem("");
        cboPB.setSelectedItem("");
        
        btnLoc.setColorclick(Color.WHITE);
        btnLoc.setColorhover(Color.WHITE);
        btnTim.setColorclick(Color.ORANGE);
        btnTim.setColorhover(Color.RED);
    }//GEN-LAST:event_txtSdtMouseClicked

    private void txtHoTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHoTenMouseClicked
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSdt.setText("");
        btnTim.setEnabled(true);
        btnLoc.setEnabled(false);
        cboMaNV.setSelectedItem("");
        cboGioiTinh1.setSelectedItem("");
        cboPB.setSelectedItem("");
        
        
        btnLoc.setColorclick(Color.WHITE);
        btnLoc.setColorhover(Color.WHITE);
        btnTim.setColorclick(Color.ORANGE);
        btnTim.setColorhover(Color.RED);
    }//GEN-LAST:event_txtHoTenMouseClicked

    private void txtDiaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiaChiMouseClicked
        // TODO add your handling code here:
        txtSdt.setText("");
        txtHoTen.setText("");
        btnTim.setEnabled(true);
        btnLoc.setEnabled(false);
        cboMaNV.setSelectedItem("");
        cboGioiTinh1.setSelectedItem("");
        cboPB.setSelectedItem("");
        
        btnLoc.setColorclick(Color.WHITE);
        btnLoc.setColorhover(Color.WHITE);
        btnTim.setColorclick(Color.ORANGE);
        btnTim.setColorhover(Color.RED);
    }//GEN-LAST:event_txtDiaChiMouseClicked

    private void cboMaNVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaNVItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtHoTen.setText("");
        cboGioiTinh1.setSelectedItem("");
        cboPB.setSelectedItem("");
        btnTim.setEnabled(false);
        btnLoc.setEnabled(true);
        
        btnLoc.setColorclick(Color.ORANGE);
        btnLoc.setColorhover(Color.RED);
        btnTim.setColorclick(Color.WHITE);
        btnTim.setColorhover(Color.WHITE);
    }//GEN-LAST:event_cboMaNVItemStateChanged

    private void cboGioiTinh1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGioiTinh1ItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtHoTen.setText("");
        cboMaNV.setSelectedItem("");
        btnTim.setEnabled(false);
        btnLoc.setEnabled(true);
        
        btnLoc.setColorclick(Color.ORANGE);
        btnLoc.setColorhover(Color.RED);
        btnTim.setColorclick(Color.WHITE);
        btnTim.setColorhover(Color.WHITE);
    }//GEN-LAST:event_cboGioiTinh1ItemStateChanged

    private void cboPBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPBItemStateChanged
        // TODO add your handling code here:
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtHoTen.setText("");
        cboMaNV.setSelectedItem("");
        btnTim.setEnabled(false);
        btnLoc.setEnabled(true);
        
        btnLoc.setColorclick(Color.ORANGE);
        btnLoc.setColorhover(Color.RED);
        btnTim.setColorclick(Color.WHITE);
        btnTim.setColorhover(Color.WHITE);
    }//GEN-LAST:event_cboPBItemStateChanged

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HelpNVTK().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnLoc;
    private custom_button.MyButton btnTim;
    private custom_button.MyButton btnXoa;
    private javax.swing.JComboBox<String> cboGioiTinh1;
    private javax.swing.JComboBox<String> cboMaNV;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
