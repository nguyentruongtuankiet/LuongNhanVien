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
import dao.TrinhDoDao;
import java.io.ObjectOutput;
import javax.swing.JComboBox;
import entity.PhongBan;
import entity.TrinhDo;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author ADMIN
 */
public class FrmCapNhatNV extends javax.swing.JPanel {
    private NhanVienHCDAO nvhcdao;
    private PhongBanDAO pbdao;
    private TrinhDoDao tddao;
    DefaultTableModel modelNV;
    java.util.Date datetoday = new java.util.Date();
    /**
     * Creates new form pnCapNhatNV
     */
    public FrmCapNhatNV() {
        try{
            ConnectDB1.getInstance().connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        nvhcdao = new NhanVienHCDAO();
        pbdao = new PhongBanDAO();
        tddao = new TrinhDoDao();
        String[] header = "Mã NV;Tên NV; Ngày sinh; Ngày TGCT;giới tính;địa chỉ;SĐT;Mã PB;trình độ".split(";");
        modelNV = new DefaultTableModel(header, 0);
        tableNV.setModel(modelNV);
        DateNgSinh.setDate(datetoday);
        DateNgCT.setDate(datetoday);
        
        
        readDatabase();
        readPB();
        readTrinhDo();
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setColorhover(Color.WHITE);
        btnXoa.setColorclick(Color.WHITE);
        btnSua.setColorhover(Color.WHITE);
        btnSua.setColorclick(Color.WHITE);
    }
    
    public void readTrinhDo(){
        DefaultComboBoxModel modelTD = (DefaultComboBoxModel) cboTrinhDo.getModel();
        modelTD.removeAllElements();
        try {
            List<TrinhDo> lst = tddao.getAllTrinhDo();
            for (TrinhDo td : lst) {
                modelTD.addElement(td.getMaTrinhDo());
            }
            cboPB.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readPB(){
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
    
    public boolean check_input(String maNV, String tenNV, String sdt, String diaChi, Date ns, Date nl){
        Calendar cal_ns = Calendar.getInstance();
        Calendar cal_nl = Calendar.getInstance();
        Calendar cal_curr = Calendar.getInstance();
        cal_curr.getTime();
        cal_ns.setTime(ns);
        cal_nl.setTime(nl);
        
        int year_ns = cal_ns.get(Calendar.YEAR);
        int year_nl = cal_nl.get(Calendar.YEAR);
        if(maNV.isEmpty()){
            JOptionPane.showMessageDialog(null, "mã nhân viên không được để trống");
            return false;
        }else if(!maNV.matches("^(NV|QL)\\d{2,4}$")){
            JOptionPane.showMessageDialog(null, "mã nhân viên sai định dạng");
            txtMaNV.requestFocus();
            return false;
        }else if(tenNV.isEmpty()){
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống");
            txtTenNV.requestFocus();
            return false;
        }else if(sdt.isEmpty()){
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
            txtSDT.requestFocus();
            return false;
        }else if(sdt.matches("^0\\d{10}$")){
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 kí số");
            txtSDT.requestFocus();
            return false;
        }else if(diaChi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
            return false;
        }else if(year_nl - year_ns <18){
            JOptionPane.showMessageDialog(null, "nhân viên không đủ tuổi");
            return false;
        }else if(cal_nl.after(cal_curr)){
            JOptionPane.showMessageDialog(null, "ngày làm phải nhỏ hơn ngày hiên tại");
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean regexMaNV(){
        String ma = txtMaNV.getText();
        
        if(ma.equals("")){
            lblMaNVerror.setText("Mã nhân viên không được để trống");
            txtMaNV.setText("");
            txtMaNV.requestFocus();
            return false;
        }
        else if(!ma.matches("^(NV|QL)\\d{2,4}$")){
            lblMaNVerror.setText("Mã nhân viên bắt đầu bằng NV và kí số");
            return false;
        }else{
            lblMaNVerror.setText("");
            return true;
        }
    }
    
//    public boolean regexTenNV(){
//        String ten = txtTenNV.getText();
//        if(ten.equals("")){
//            lblTenerror.setText("Tên nhân viên không được để trống");
//            txtTenNV.setText("");
//            return false;
//        }else if(!ten.matches("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$")){
//            lblTenerror.setText("Tên nhân viên không chứa kí tự đặc biệt");
//            txtTenNV.setText("");
//           
//            return false;
//        }else{
//            lblTenerror.setText("");
//            return true;
//        }
//    }
     public boolean regexTenNV() {
        String ten = txtTenNV.getText();
        Pattern pattern = Pattern.compile("^[A-ZĐ][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[A-Z][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (ten.equals("")) {
            lblTenerror.setText("Tên công nhân không được để trống");
            return false;
        } else if (!pattern.matcher(ten).find()) {
            lblTenerror.setText("Tên công nhân phải bắt đầu bằng chữ viết Hoa mỗi từ");
            return false;
        } else {
            lblTenerror.setText("");
            return true;
        }
    }
    
//    public boolean regexDiaChi(){
//        String diachi = txtDiaChi.getText();
//        if(diachi.equals("")){
//            lblDCerror.setText("Địa chỉ không được để trống");
//            txtDiaChi.setText("");
//            
//            return false;
//        }else if(!diachi.matches("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")){
//            lblDCerror.setText("Địa chỉ không chứa kí tự đặc biệt");
//            txtDiaChi.setText("");
//            
//            return false;
//        }else{
//            lblDCerror.setText("");
//            return true;
//        }
//    }
       public boolean regexDiaChi() {
        String diachi = txtDiaChi.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\,(/)ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽếềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)*$");
        if (diachi.equals("")) {
            lblDCerror.setText("Địa chỉ không được để trống");
            return false;
        } else {
            lblDCerror.setText("");
            return true;
        }
    }
    
    public boolean regexSdt(){
        String sdt = txtSDT.getText().trim();
        if(sdt.isEmpty()){
            lblSÐTerror.setText("SDT không được để trống");
            
            return false;
        }else if(!(sdt.length() > 0 && sdt.matches("(03|08|09)\\d{8}"))){
            lblSÐTerror.setText("SDT phải có 10 kí số");
            
            return false;
        }else{
            lblSÐTerror.setText("");
            return true;
        }
    }
    
    public boolean regexNgayThang(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date ns = Date.valueOf(df.format(DateNgSinh.getDate()));
        Date nl = Date.valueOf(df.format(DateNgCT.getDate()));
        Calendar cal_ns = Calendar.getInstance();
        Calendar cal_nl = Calendar.getInstance();
        Calendar cal_curr = Calendar.getInstance();
        cal_curr.getTime();
        cal_ns.setTime(ns);
        cal_nl.setTime(nl);
        
        int year_ns = cal_ns.get(Calendar.YEAR);
        int year_nl = cal_nl.get(Calendar.YEAR);
        if(year_nl - year_ns <18){
            lblNSerror.setText("Tuổi nhân viên phải từ 18 trở lên");
            return false;
        }else if(cal_nl.after(cal_curr)){
            lblNCTerror.setText("ngày làm phải nhỏ hơn ngày hiên tại");
            return false;
        }else{
            lblNSerror.setText("");
            lblNCTerror.setText("");
            return true;
        }
    }
    
    public void themNV(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateNgSinh.getDate()));
        String maNV = txtMaNV.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        boolean gioiTinh = cboGioiTinh.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        System.out.println(DateNgSinh.toString());
        Date ns = Date.valueOf(df.format(DateNgSinh.getDate()));
        Date nCT = Date.valueOf(df.format(DateNgCT.getDate()));
        String matrinhDo = cboTrinhDo.getSelectedItem().toString();
        String pb1 = cboPB.getSelectedItem().toString();
        PhongBan pb = new PhongBan(pb1);
        TrinhDo trinhDo = new TrinhDo(matrinhDo);
        List<NhanVien> lst_check = nvhcdao.getALLNV();
//        if(check_input(maNV, tenNV, sdt, diaChi, ns, nCT)){
            NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ns, nCT, gioiTinh, pb);
            if(lst_check.contains(nv)){
                JOptionPane.showMessageDialog(this, "TRÙNG MÃ NHÂN VIÊN");
            }else{
                if(nvhcdao.createNV(nv)){
                    Object[] row = {nv.getMaNV(),nv.getHoTen(),date.format(nv.getNgaySinh()),date.format(nv.getNgayCT()),
                        nv.isGioiTinh()==true?"Nam":"Nữ",nv.getDiaChi(),nv.getSdt(),nv.getMaPB().getMaPB(),nv.getTrinhDo().getMaTrinhDo()};
                    modelNV.addRow(row);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
//            }
        xoaTrang();
        }
    }
    
    public void xoaTrang(){
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        DateNgSinh.setDate(null);
        DateNgCT.setDate(null);
        cboGioiTinh.setSelectedIndex(0);
        cboPB.setSelectedIndex(0);
        cboTrinhDo.setSelectedIndex(0);
        txtMaNV.setEditable(true);
        txtMaNV.setEnabled(true);
        
        
    }
    
    public void clickTable(){ 
        int row = tableNV.getSelectedRow();
        txtMaNV.setText(modelNV.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
        try {
            DateNgSinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelNV.getValueAt(row, 2)));
            DateNgCT.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String)modelNV.getValueAt(row, 3)));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCapNhatNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        cboGioiTinh.setSelectedItem(modelNV.getValueAt(row, 4).toString());
        txtDiaChi.setText(modelNV.getValueAt(row, 5).toString());
        txtSDT.setText(modelNV.getValueAt(row, 6).toString());
        cboPB.setSelectedItem(modelNV.getValueAt(row, 7).toString());
        cboTrinhDo.setSelectedItem(modelNV.getValueAt(row, 8).toString());
        txtMaNV.setEditable(false);
        txtMaNV.setEditable(false);
        
    }
    
    public void xoa(){
        int row = tableNV.getSelectedRow();
	int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            String ma = (String) tableNV.getValueAt(row, 0);
		nvhcdao.removeNV(ma);
		readDatabase();
		}
	xoaTrang();
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        
        btnXoa.setColorhover(Color.WHITE);
        btnXoa.setColorclick(Color.WHITE);
        btnSua.setColorhover(Color.WHITE);
        btnSua.setColorclick(Color.WHITE);
        
        btnThem.setColorhover(Color.ORANGE);
        btnThem.setColorclick(Color.RED);
    }
    
    public void SuaNV(){
        int row = tableNV.getSelectedRow();
        //////////////////////////////////////////////
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maNV = txtMaNV.getText().trim();
        String tenNV = txtTenNV.getText().trim();
        boolean gioiTinh = cboGioiTinh.getSelectedItem()=="Nam"?true:false;
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        System.out.println(DateNgSinh.toString());
        Date ns = Date.valueOf(df.format(DateNgSinh.getDate()));
        Date nCT = Date.valueOf(df.format(DateNgCT.getDate()));
        String matrinhDo = cboTrinhDo.getSelectedItem().toString();
        String pb1 = cboPB.getSelectedItem().toString();
        TrinhDo trinhDo = new TrinhDo(matrinhDo);
        PhongBan pb = new PhongBan(pb1);
        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ns, nCT, gioiTinh, pb);
        int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa","Attention!",JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            if(check_input(maNV, tenNV, sdt, diaChi, ns, nCT)){
                if(nvhcdao.capNhat(nv)){
                    readDatabase();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                }else{
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }
        }
        txtMaNV.setEditable(true);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        
        btnXoa.setColorhover(Color.WHITE);
        btnXoa.setColorclick(Color.WHITE);
        btnSua.setColorhover(Color.WHITE);
        btnSua.setColorclick(Color.WHITE);
        
        btnThem.setColorhover(Color.ORANGE);
        btnThem.setColorclick(Color.RED);
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
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboPB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DateNgSinh = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        DateNgCT = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboTrinhDo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        btnSua = new custom_button.MyButton();
        btnThem = new custom_button.MyButton();
        btnXoa = new custom_button.MyButton();
        jLabel10 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        lblDCerror = new javax.swing.JLabel();
        lblMaNVerror = new javax.swing.JLabel();
        lblTenerror = new javax.swing.JLabel();
        lblNSerror = new javax.swing.JLabel();
        lblNCTerror = new javax.swing.JLabel();
        lblSÐTerror = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnXoaTrang = new custom_button.MyButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setText("NHÂN VIÊN HÀNH CHÍNH");
        add(jLabel1);
        jLabel1.setBounds(500, 26, 310, 26);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Nhân Viên:");
        add(jLabel2);
        jLabel2.setBounds(280, 80, 100, 20);

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        add(txtMaNV);
        txtMaNV.setBounds(410, 70, 140, 30);

        txtTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });
        add(txtTenNV);
        txtTenNV.setBounds(670, 70, 290, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Họ tên:");
        add(jLabel3);
        jLabel3.setBounds(610, 80, 48, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính:");
        add(jLabel4);
        jLabel4.setBounds(980, 230, 60, 20);

        cboPB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QL", "HC" }));
        cboPB.setPreferredSize(new java.awt.Dimension(64, 21));
        add(cboPB);
        cboPB.setBounds(870, 220, 100, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");
        add(jLabel5);
        jLabel5.setBounds(613, 151, 48, 20);

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        add(txtDiaChi);
        txtDiaChi.setBounds(673, 140, 290, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày sinh:");
        add(jLabel6);
        jLabel6.setBounds(280, 150, 80, 20);

        DateNgSinh.setDateFormatString("yyyy-MM-dd");
        DateNgSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(DateNgSinh);
        DateNgSinh.setBounds(410, 140, 138, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày tham gia CT:");
        add(jLabel7);
        jLabel7.setBounds(280, 230, 120, 20);

        DateNgCT.setDateFormatString("yyyy-MM-dd");
        DateNgCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(DateNgCT);
        DateNgCT.setBounds(410, 220, 138, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("SÐT:");
        add(jLabel8);
        jLabel8.setBounds(613, 192, 48, 20);

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        add(txtSDT);
        txtSDT.setBounds(673, 181, 97, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Phòng ban:");
        add(jLabel9);
        jLabel9.setBounds(790, 230, 80, 20);

        cboTrinhDo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trung cấp", "Cao đẳng", "Đại học" }));
        add(cboTrinhDo);
        cboTrinhDo.setBounds(675, 221, 110, 30);

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

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
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
        btnSua.setBounds(730, 290, 130, 50);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/create1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(102, 102, 102));
        btnThem.setColorhover(new java.awt.Color(0, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setRadius(30);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem);
        btnThem.setBounds(310, 290, 130, 50);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
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
        btnXoa.setBounds(510, 290, 130, 50);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Trình độ:");
        add(jLabel10);
        jLabel10.setBounds(613, 231, 60, 20);

        cboGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setPreferredSize(new java.awt.Dimension(64, 21));
        add(cboGioiTinh);
        cboGioiTinh.setBounds(1050, 220, 88, 30);

        lblDCerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblDCerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblDCerror);
        lblDCerror.setBounds(970, 140, 220, 30);

        lblMaNVerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblMaNVerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblMaNVerror);
        lblMaNVerror.setBounds(280, 100, 300, 30);

        lblTenerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblTenerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblTenerror);
        lblTenerror.setBounds(610, 100, 380, 30);

        lblNSerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblNSerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblNSerror);
        lblNSerror.setBounds(280, 180, 260, 30);

        lblNCTerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblNCTerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblNCTerror);
        lblNCTerror.setBounds(280, 260, 260, 30);

        lblSÐTerror.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSÐTerror.setForeground(new java.awt.Color(255, 51, 51));
        add(lblSÐTerror);
        lblSÐTerror.setBounds(780, 180, 280, 30);

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

        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/remove.png"))); // NOI18N
        btnXoaTrang.setText("Xóa Trắng");
        btnXoaTrang.setBorderColor(new java.awt.Color(102, 102, 102));
        btnXoaTrang.setColorhover(new java.awt.Color(0, 255, 255));
        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaTrang.setRadius(30);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });
        add(btnXoaTrang);
        btnXoaTrang.setBounds(940, 290, 150, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        regexTenNV();
        regexDiaChi();
        regexSdt();
        regexNgayThang();
        if(regexTenNV() && regexDiaChi() && regexSdt() && regexNgayThang()){
            SuaNV();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        regexMaNV();
        regexTenNV();
        regexDiaChi();
        regexSdt();
        regexNgayThang();
        if(regexMaNV() && regexTenNV() && regexDiaChi() && regexSdt() && regexNgayThang()){
            themNV();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        // TODO add your handling code here:
        clickTable();
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false); 
        
        btnThem.setColorhover(Color.WHITE);
        btnThem.setColorclick(Color.WHITE);
        btnSua.setColorhover(Color.ORANGE);
        btnSua.setColorclick(Color.RED);
        btnXoa.setColorhover(Color.ORANGE);
        btnXoa.setColorclick(Color.RED);
    }//GEN-LAST:event_tableNVMouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HelpNVCP().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
        xoaTrang();
        txtMaNV.requestFocus();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnXoaTrang.setEnabled(true);
        btnThem.setEnabled(true);
        
        btnThem.setColorhover(Color.ORANGE);
        btnThem.setColorclick(Color.RED);
        
        btnXoa.setColorhover(Color.WHITE);
        btnXoa.setColorclick(Color.WHITE);
        btnSua.setColorhover(Color.WHITE);
        btnSua.setColorclick(Color.WHITE);
    }//GEN-LAST:event_btnXoaTrangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgCT;
    private com.toedter.calendar.JDateChooser DateNgSinh;
    private custom_button.MyButton btnSua;
    private custom_button.MyButton btnThem;
    private custom_button.MyButton btnXoa;
    private custom_button.MyButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboPB;
    private javax.swing.JComboBox<String> cboTrinhDo;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDCerror;
    private javax.swing.JLabel lblMaNVerror;
    private javax.swing.JLabel lblNCTerror;
    private javax.swing.JLabel lblNSerror;
    private javax.swing.JLabel lblSÐTerror;
    private javax.swing.JLabel lblTenerror;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
