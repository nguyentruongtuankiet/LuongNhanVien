/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

/**
 *
 * @author ADMIN
 */
public class HelpNVLuong extends javax.swing.JFrame {

    /**
     * Creates new form HelpNVLuong
     */
    public HelpNVLuong() {
        initComponents();
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
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 480));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("LƯƠNG NHÂN VIÊN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 20, 307, 45);

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel45.setText("4.4.Tình Lương");
        getContentPane().add(jLabel45);
        jLabel45.setBounds(30, 70, 120, 20);

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel46.setText("Cick chọn 1 nhân viên trên bảng nhân viên cần tính lương");
        getContentPane().add(jLabel46);
        jLabel46.setBounds(60, 100, 370, 20);

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel47.setText("Thông tin của nhân viên được chọn sẽ hiện trên các ô trống");
        getContentPane().add(jLabel47);
        jLabel47.setBounds(60, 140, 390, 20);

        jLabel48.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel48.setText("Chọn Tháng và Năm cần tính lương sau đó ấn Lọc để lọc ra Số ngày làm và Số ngày tăng ca của nhân viên đó");
        getContentPane().add(jLabel48);
        jLabel48.setBounds(60, 180, 690, 20);

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel49.setText("Chọn TÍNH LƯƠNG để tính lương cho nhân viên vừa chọn");
        getContentPane().add(jLabel49);
        jLabel49.setBounds(60, 220, 991, 20);

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel50.setText("Nhấn SỬA THÔNG TIN nếu nhân viên vừa chọn muốn sửa lại Hệ Số Lương và Phụ Cấp ");
        getContentPane().add(jLabel50);
        jLabel50.setBounds(60, 260, 580, 20);

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel51.setText("Nhấn Lưu để luu thông tin Hệ Số Lương và Phụ Cấp vừa nhập");
        getContentPane().add(jLabel51);
        jLabel51.setBounds(60, 300, 400, 20);

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel52.setText("Nút IN để in thông tin báo cáo về bảng lương của tất cả nhân viên trong bảng lương lên màn hình.");
        getContentPane().add(jLabel52);
        jLabel52.setBounds(60, 340, 620, 20);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HelpNVLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelpNVLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelpNVLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelpNVLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelpNVLuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    // End of variables declaration//GEN-END:variables
}
