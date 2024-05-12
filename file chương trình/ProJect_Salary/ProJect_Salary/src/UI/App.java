package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class App extends JFrame {

	private JPanel pnlContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public App() throws IOException {
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 617);
		pnlContent = new JPanel();
		pnlContent.setForeground(new Color(0, 204, 255));
		pnlContent.setFont(new Font("Dialog", Font.PLAIN, 15));
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlContent.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlContent);

		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(Color.WHITE);
		pnlTop.setPreferredSize(new Dimension(10, 50));
		pnlContent.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(null);

		JLabel lblLogo = new JLabel();
//		lblLogo.setIcon(scaleImage(new ImageIcon(ImageIO.read(new File("image\\\\logoHead.png"))), 100, 100));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblLogo.setBounds(0, 0, 208, 50);
		pnlTop.add(lblLogo);

		JPanel pnlTopCenter = new JPanel();
		pnlTopCenter.setBackground(SystemColor.textInactiveText);
		pnlTopCenter.setPreferredSize(new Dimension(10, 40));
		pnlTopCenter.setBounds(10, 10, 626, 38);
		pnlTop.add(pnlTopCenter);
		pnlTopCenter.setLayout(null);
		
		JLabel lblTrangChu = new JLabel("Trang Chủ");
		lblTrangChu.setForeground(new Color(255, 51, 51));
		lblTrangChu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangChu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTrangChu.setBounds(222, 10, 184, 28);
		pnlTopCenter.add(lblTrangChu);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setForeground(new Color(0, 204, 255));
		pnlCenter.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlCenter.setBackground(new Color(248, 243, 236));
		pnlContent.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(null);

		JButton btnDangNhap = new JButton("\r\nĐăng Nhập\r\n");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDangNhap.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnDangNhap.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDangNhap.setBounds(160, 458, 146, 49);
		btnDangNhap.setBackground(Color.WHITE);
		pnlCenter.add(btnDangNhap);

		JButton btnDangKy = new JButton("Thoát");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
//		btnDangKy.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				new FormDangKy().setVisible(true);
//			}
//		});
		btnDangKy.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDangKy.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnDangKy.setBackground(Color.WHITE);
		btnDangKy.setBounds(334, 461, 139, 46);
		pnlCenter.add(btnDangKy);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(App.class.getResource("/Image/bannerApp.jpg")));
		lblNewLabel.setBounds(10, 10, 626, 433);
		pnlCenter.add(lblNewLabel);
	}
}
