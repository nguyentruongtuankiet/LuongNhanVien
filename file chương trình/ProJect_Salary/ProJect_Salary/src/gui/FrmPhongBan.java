/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connect.ConnectDB1;
import dao.PhongBanDAO;
import entity.PhongBan;
import java.io.ObjectOutput;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FrmPhongBan extends javax.swing.JPanel {
    PhongBanDAO phongBanDAO;
    DefaultTableModel modelPB;
    /**
     * Creates new form pnPhongBan
     */
    public FrmPhongBan() {
        initComponents();
        try {
          ConnectDB1.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        phongBanDAO = new PhongBanDAO();
        modelPB = (DefaultTableModel) tblPB.getModel();
        readTablePB();
        
    }
    
    public void readTablePB(){
        modelPB.setRowCount(0);
        List<PhongBan> lst = phongBanDAO.getAllPB();
        for (PhongBan pb : lst) {
            Object[] row = {pb.getMaPB(),pb.getTenPB()};
            modelPB.addRow(row);
        }
    }
    
    public void clickTable(){
        int row = tblPB.getSelectedRow();
        txtMaPB.setText(modelPB.getValueAt(row, 0).toString());
        txtTenPB.setText(modelPB.getValueAt(row, 1).toString());
    }
    
    public void themPB(){
        String maPB = txtMaPB.getText().trim();
        String tenPB = txtTenPB.getText().trim();
        PhongBan pb = new PhongBan(maPB, tenPB);
        List<PhongBan> lst_check = phongBanDAO.getAllPB();
        if(lst_check.contains(pb)){
            JOptionPane.showMessageDialog(null, "Trùng mã phòng ban");
        }else{
            if(phongBanDAO.createPB(pb)){
                readTablePB();
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                xoaTrang();
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
                xoaTrang();
            }
        }
    }
    
    public void xoaTrang(){
        txtMaPB.setText("");
        txtTenPB.setText("");
    }
    
    public void XoaPB(){
        int row = tblPB.getSelectedRow();
	int ques = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Warning", JOptionPane.YES_NO_OPTION);
	if(ques==JOptionPane.YES_OPTION) {
            String ma = (String) tblPB.getValueAt(row, 0);
		phongBanDAO.deletePB(ma);
		readTablePB();
		}
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
        txtMaPB = new javax.swing.JTextField();
        txtTenPB = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnXoa = new custom_button.MyButton();
        btnThem = new custom_button.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPB = new javax.swing.JTable();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("PHÒNG BAN");
        add(jLabel1);
        jLabel1.setBounds(520, 43, 160, 29);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mã Phòng Ban:");
        add(jLabel2);
        jLabel2.setBounds(270, 120, 140, 20);

        txtMaPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPBActionPerformed(evt);
            }
        });
        add(txtMaPB);
        txtMaPB.setBounds(410, 110, 180, 40);

        txtTenPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPBActionPerformed(evt);
            }
        });
        add(txtTenPB);
        txtTenPB.setBounds(410, 190, 180, 40);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Tên Phòng Ban:");
        add(jLabel3);
        jLabel3.setBounds(270, 200, 140, 20);

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setRadius(45);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa);
        btnXoa.setBounds(770, 190, 140, 40);

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setRadius(45);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem);
        btnThem.setBounds(770, 110, 140, 40);

        tblPB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Phòng Ban", "Tên Phòng Ban"
            }
        ));
        tblPB.setRowHeight(30);
        tblPB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPB);

        add(jScrollPane1);
        jScrollPane1.setBounds(150, 310, 960, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPBActionPerformed

    private void txtTenPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPBActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        XoaPB();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        themPB();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblPBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPBMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblPBMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom_button.MyButton btnThem;
    private custom_button.MyButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPB;
    private javax.swing.JTextField txtMaPB;
    private javax.swing.JTextField txtTenPB;
    // End of variables declaration//GEN-END:variables
}
