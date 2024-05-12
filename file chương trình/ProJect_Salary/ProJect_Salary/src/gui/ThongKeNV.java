/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import dao.ThongKeDAO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ADMIN
 */
public class ThongKeNV extends javax.swing.JPanel {
    private ThongKeDAO thongKeDAO;
    private DefaultTableModel modelThongke;
    LocalDate todayDate;
    /**
     * Creates new form ThongKeNV
     */
    public ThongKeNV() {
        initComponents();
         try {
            ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thongKeDAO = new ThongKeDAO();
        ThongKeSLNV(pnSoLuongNV);
//        ThongKeLuong(pnLuongNV);
        ThongKeLuongNull(pnLuongNV);
        todayDate = LocalDate.now();
        modelThongke = (DefaultTableModel) tblThongKeNV.getModel();
        loadComBoBoxThang();
        loadComboBoxNam();
    }
    
    public void loadComBoBoxThang(){
        DefaultComboBoxModel modelThang = (DefaultComboBoxModel) cboThang.getModel();
        modelThang.removeAllElements();
        modelThang.addElement("");
        for (int i = 0; i < 12; i++) {
            modelThang.addElement(i+1);
        }
    }
    
    public void loadComboBoxNam(){
        DefaultComboBoxModel modelNam = (DefaultComboBoxModel) cboNam.getModel();
        modelNam.removeAllElements();
        modelNam.addElement("");
        int year = todayDate.getYear();
        modelNam.addElement(year-1);
        modelNam.addElement(year);
        modelNam.addElement(year+1);
        
    }
    
    public void ThongKeLuongNull(JPanel item1){
        item1.removeAll();
        item1.setLayout(new GridBagLayout());
        JLabel lblNull = new JLabel("Không có dữ liệu");
        lblNull.setFont(new Font("Time New Roman",Font.BOLD,22));
        item1.add(lblNull);
        item1.validate();
        item1.repaint();
    }
    
    public void ThongKeSLNV(JPanel item1){
        List<Object[]> lst = thongKeDAO.getAllSLnv();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] item : lst) {
            dataset.setValue((String)item[0],(int)item[1]);
        }
        JFreeChart chartPie = ChartFactory.createPieChart("Số lượng nhân viên", dataset,true, true, false);
        ChartPanel chartPanel = new ChartPanel(chartPie);
        
        item1.removeAll();
        item1.setLayout(new CardLayout());
        item1.add(chartPanel);
        item1.validate();
        item1.repaint();
    }
    
    public void ThongKeLuong(JPanel item1){
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> lst = thongKeDAO.getLuongNamThang(thang,nam);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(lst.isEmpty()==false){
            for (Object[] item : lst) {
                dataset.addValue((double)item[1], "Nhân viên", (String)item[0]);
            }
            JFreeChart chartBar = ChartFactory.createBarChart("Thống kê lương nhân viên".toUpperCase(),
                "Nhân viên", "Lương", dataset,PlotOrientation.VERTICAL, false, true, false);
            ChartPanel chartPanel = new ChartPanel(chartBar);
        
            CategoryPlot categoryPlot = chartBar.getCategoryPlot();
            BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
            br.setMaximumBarWidth(.1); // set maximum width to 35% of chart
            item1.removeAll();
            item1.setLayout(new CardLayout());
            item1.add(chartPanel);
            item1.validate();
            item1.repaint();
        }
        else{
            ThongKeLuongNull(item1);
        }
    }
    
    public void readLuongTB(){
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> lst = thongKeDAO.thongkeLuong(thang, nam);
        if(lst.isEmpty()==false){
            for (Object[] obj : lst) {
                lbl1.setText((String) obj[0]);
                lbl2.setText((String) obj[1]);
                lbl3.setText((String) obj[2]);
                lbl4.setText((String) obj[3]);
                lbl5.setText((String) obj[4]);
                
            }
        }
        else{
            lbl1.setText("?");
            lbl2.setText("?");
            lbl3.setText("?");
            lbl4.setText("?");
            lbl5.setText("?");
        }
    }
    
    public void readTableThongKe(){
        int month = Integer.parseInt(cboThang.getSelectedItem().toString());
        int year = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> lst = thongKeDAO.getLuongTable(month, year);
        modelThongke.setRowCount(0);
        for (Object[] o1 : lst) {
            modelThongke.addRow(o1);
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

        pnSoLuongNV = new javax.swing.JPanel();
        pnLuongNV = new javax.swing.JPanel();
        cboThang = new javax.swing.JComboBox<>();
        cboNam = new javax.swing.JComboBox<>();
        myButton1 = new custom_button.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeNV = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setLayout(null);

        pnSoLuongNV.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnSoLuongNVLayout = new javax.swing.GroupLayout(pnSoLuongNV);
        pnSoLuongNV.setLayout(pnSoLuongNVLayout);
        pnSoLuongNVLayout.setHorizontalGroup(
            pnSoLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        pnSoLuongNVLayout.setVerticalGroup(
            pnSoLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        add(pnSoLuongNV);
        pnSoLuongNV.setBounds(20, 0, 560, 320);

        pnLuongNV.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnLuongNVLayout = new javax.swing.GroupLayout(pnLuongNV);
        pnLuongNV.setLayout(pnLuongNVLayout);
        pnLuongNVLayout.setHorizontalGroup(
            pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        pnLuongNVLayout.setVerticalGroup(
            pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        add(pnLuongNV);
        pnLuongNV.setBounds(600, 0, 600, 320);

        add(cboThang);
        cboThang.setBounds(120, 330, 140, 30);

        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });
        add(cboNam);
        cboNam.setBounds(380, 330, 140, 30);

        myButton1.setText("Thống kê");
        myButton1.setRadius(30);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });
        add(myButton1);
        myButton1.setBounds(550, 330, 110, 40);

        tblThongKeNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Số ngày làm", "Số ngày tăng ca", "Lương"
            }
        ));
        jScrollPane1.setViewportView(tblThongKeNV);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 380, 700, 320);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255)));
        jPanel1.setLayout(null);

        lbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl1.setText("?");
        jPanel1.add(lbl1);
        lbl1.setBounds(260, 70, 50, 31);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Số ngày làm trung bình: ");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 70, 220, 30);

        lbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl2.setText("?");
        jPanel1.add(lbl2);
        lbl2.setBounds(290, 110, 50, 31);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Số ngày tăng ca trung bình: ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 110, 250, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Lương cao nhất:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 150, 140, 30);

        lbl3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl3.setText("?");
        jPanel1.add(lbl3);
        lbl3.setBounds(180, 150, 210, 31);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Lương thấp nhất:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 190, 150, 30);

        lbl4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl4.setText("?");
        jPanel1.add(lbl4);
        lbl4.setBounds(190, 190, 200, 31);

        lbl5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl5.setText("?");
        jPanel1.add(lbl5);
        lbl5.setBounds(160, 230, 230, 31);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Tổng lương:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 230, 150, 30);

        add(jPanel1);
        jPanel1.setBounds(730, 330, 470, 370);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Năm:");
        add(jLabel1);
        jLabel1.setBounds(320, 330, 60, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tháng:");
        add(jLabel2);
        jLabel2.setBounds(50, 330, 60, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamActionPerformed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        readTableThongKe();
        ThongKeLuong(pnLuongNV);
        readLuongTB();
    }//GEN-LAST:event_myButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private custom_button.MyButton myButton1;
    private javax.swing.JPanel pnLuongNV;
    private javax.swing.JPanel pnSoLuongNV;
    private javax.swing.JTable tblThongKeNV;
    // End of variables declaration//GEN-END:variables
}
