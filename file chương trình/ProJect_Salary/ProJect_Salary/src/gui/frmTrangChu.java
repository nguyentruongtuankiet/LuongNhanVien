/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import gui.FrmTimKiemSanPham;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import menu.MenuItem;

/**
 *
 * @author ADMIN
 */
public class frmTrangChu extends javax.swing.JFrame {

    /**
     * Creates new form frmTrangChu
     */
    public frmTrangChu() {
        initComponents();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        excute();
        pnCenter.add(new pnFirst());
    }

    public void excute() {
        ImageIcon iconNV = new ImageIcon(getClass().getResource("/Image/user.png"));
        ImageIcon iconSP = new ImageIcon(getClass().getResource("/Image/product.png"));
        ImageIcon iconSub = new ImageIcon(getClass().getResource("/Image/sub_menu.png"));
        ImageIcon iconTC = new ImageIcon(getClass().getResource("/Image/home.png"));
        ImageIcon iconTK = new ImageIcon(getClass().getResource("/Image/statistic.png"));
        ImageIcon iconDP = new ImageIcon(getClass().getResource("/Image/department.png"));
        ImageIcon iconTD = new ImageIcon(getClass().getResource("/Image/education.png"));
        ImageIcon iconTG = new ImageIcon(getClass().getResource("/Image/support1.png"));
        //Tao menu con
        MenuItem subNV1 = new MenuItem("Cập Nhật", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmCapNhatNV());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subNV2 = new MenuItem("Tìm Kiếm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmTimKiemNV());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subNV3 = new MenuItem("Chấm Công", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmChamCongNV());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subNV4 = new MenuItem("Tính Lương", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmLuongNhanVien());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        //
        MenuItem subCN2 = new MenuItem("Tìm Kiếm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmTimKiemCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subCN3 = new MenuItem("Chấm Công", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmChamCongCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subCN4 = new MenuItem("Tính Lương", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmLuongCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subCN5 = new MenuItem("Phân công", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmPhanCongCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subCN1 = new MenuItem("Cập Nhật", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmCapNhatCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });

        //
//        MenuItem subSP = new MenuItem("Cập Nhật",null);
        MenuItem subSP = new MenuItem("Cập Nhật", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmSanPham());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subSP1 = new MenuItem("Công Đoạn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmCongDoanSanPham());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subSP2 = new MenuItem("Tìm Kiếm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new FrmTimKiemSanPham());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });

        MenuItem subTK1 = new MenuItem("Nhân Viên", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new ThongKeNV());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem subTK2 = new MenuItem("Công Nhân", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add(new ThongKeCN());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });

        //Add menu con vao menu chinh
        MenuItem menuTC = new MenuItem(iconTC, "TRANG CHỦ", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pnCenter.removeAll();
                pnCenter.add(new pnTrangChu());
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });

        MenuItem menuNV = new MenuItem(iconNV, "NHÂN VIÊN", null, subNV1, subNV2, subNV3, subNV4);
        MenuItem menuDP = new MenuItem(iconDP, "PHÒNG BAN", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add((new FrmPhongBan()));
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem menuTD = new MenuItem(iconTD, "TRÌNH ÐÔ", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add((new FrmTrinhDo()));
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
//        JScrollPane jcp = new JScrollPane();
//        jcp.add(new TroGiup);
        MenuItem menuTG = new MenuItem(iconTG, "TRỢ GIÚP", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnCenter.removeAll();
                pnCenter.add((new JScrollPane(add(new TroGiup()))));
                pnCenter.repaint();
                pnCenter.revalidate();
            }
        });
        MenuItem menuCN = new MenuItem(iconNV, "CÔNG NHÂN", null, subCN1, subCN2, subCN5, subCN3, subCN4);
        MenuItem menuSP = new MenuItem(iconSP, "SẢN PHẨM", null, subSP, subSP1, subSP2);
        MenuItem menuTK = new MenuItem(iconTK, "THỐNG KÊ", null, subTK1, subTK2);
        addMenu(menuTC, menuDP, menuTD, menuNV, menuCN, menuSP, menuTK);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenuMain.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        MenuMain.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnWest = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MenuMain = new javax.swing.JPanel();
        pnCenter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnNorth.setBackground(new java.awt.Color(0, 204, 255));
        pnNorth.setForeground(new java.awt.Color(255, 255, 255));
        pnNorth.setPreferredSize(new java.awt.Dimension(1650, 75));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHƯƠNG TRÌNH QUẢN LÝ LƯƠNG SẢN PHẨM");

        javax.swing.GroupLayout pnNorthLayout = new javax.swing.GroupLayout(pnNorth);
        pnNorth.setLayout(pnNorthLayout);
        pnNorthLayout.setHorizontalGroup(
            pnNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNorthLayout.createSequentialGroup()
                .addContainerGap(653, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(445, 445, 445))
        );
        pnNorthLayout.setVerticalGroup(
            pnNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNorthLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        getContentPane().add(pnNorth, java.awt.BorderLayout.PAGE_START);

        pnWest.setBackground(new java.awt.Color(51, 153, 255));
        pnWest.setPreferredSize(new java.awt.Dimension(250, 1015));

        jScrollPane1.setBorder(null);

        MenuMain.setBackground(new java.awt.Color(0, 153, 255));
        MenuMain.setForeground(new java.awt.Color(0, 153, 153));
        MenuMain.setLayout(new javax.swing.BoxLayout(MenuMain, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(MenuMain);

        javax.swing.GroupLayout pnWestLayout = new javax.swing.GroupLayout(pnWest);
        pnWest.setLayout(pnWestLayout);
        pnWestLayout.setHorizontalGroup(
            pnWestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnWestLayout.setVerticalGroup(
            pnWestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        getContentPane().add(pnWest, java.awt.BorderLayout.LINE_START);

        pnCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnCenter.setPreferredSize(new java.awt.Dimension(1200, 1015));
        pnCenter.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

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
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnNorth;
    private javax.swing.JPanel pnWest;
    // End of variables declaration//GEN-END:variables
}
