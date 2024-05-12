/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.SanPham;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;


/**
 *
 * @author admin
 */
public class SanPhamDAO {
    public List<SanPham> getALLSP(){
        List<SanPham> listSP = new ArrayList<SanPham>();
        
       
        try {
             ConnectDB1.getInstance();
             Connection con = ConnectDB1.getConnection();
             String sql = "Select * From SanPham";
             Statement stmt;
            stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                 String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 String anh = rs.getString(7);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh, anh);
                 listSP.add(sp);
          
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
           return listSP;
    }
    public boolean createSP(SanPham sp){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        
        String sql = "insert into SanPham values (?,?,?,?,?,?,?)";
        try {
               pstm = conn.prepareStatement(sql);
               pstm.setString(1, sp.getMaSP());
               pstm.setString(2, sp.getTenSP());
               pstm.setString(3, sp.getThuongHieu());
               pstm.setFloat(4, sp.getDonGia());
               pstm.setInt(5, sp.getSoLuong());
               pstm.setString(6, sp.getDonViTinh());
               pstm.setString(7, sp.getAnh());
               return pstm.executeUpdate()>0;
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return false;
    }
    public boolean removeSP(String maSP){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from SanPham where maSP=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }
    public boolean suaSanPham(SanPham sp){
         ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update SanPham set tenSP=?,thuongHieu=?,donGia=?,soLuong=?,donViTinh=?, Anh = ? where maSP=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getTenSP());
            stmt.setString(2, sp.getThuongHieu());
            stmt.setFloat(3, sp.getDonGia());
            stmt.setInt(4, sp.getSoLuong());
            stmt.setString(5, sp.getDonViTinh());
            stmt.setString(6, sp.getAnh());
            stmt.setString(7, sp.getMaSP());
            return stmt.executeUpdate() >0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<SanPham> timSanPhamTheoMa(String ma){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from SanPham where maSP= '"+ma+"'";
            try {
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
        }
            return listSP;
    }
    public List<SanPham> timSanPhamTheoDonViTinh(String DVT){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from SanPham where donViTinh= '"+DVT+"'";
            try {
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
        }
            return listSP;
    }
    
    public List<SanPham> timSanPhamTheoGia(float giaBD, float giaKT){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "select * from SanPham where DonGia >= "+giaBD+" and DonGia <="+giaKT+"";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }
    public List<SanPham> timSPTheoTen(String ten){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql= "select * from SanPham where tenSP like N'%"+ten+"%'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }
    public List<SanPham> timSPTheoThuongHieu(String ten){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql= "select * from SanPham where thuongHieu like N'%"+ten+"%'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }
    
    
    //phast sinh max
    public String getMaSPTuDong() {
        String maSP="";
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "select CONCAT('SP', RIGHT(CONCAT('00',ISNULL(right(max(maSP),2),0) + 1),2)) from SanPham where maSP like  'SP%'";
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) {
            maSP = rs.getString(1);			}
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return maSP;
    }
     public List<SanPham> timSanPhamTheoSoLuong(int soLuongBD, int soLuongKT){
        List<SanPham> listSP = new ArrayList<SanPham>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "select * from SanPham where SoLuong >= "+soLuongBD+" and SoLuong <="+soLuongKT+"";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSP = rs.getString(1);
                 String tenSP = rs.getString(2);
                 String thuongHieu = rs.getString(3);
                 Float donGia = rs.getFloat(4);
                 int soLuong = rs.getInt(5);
                 String donViTinh = rs.getString(6);
                 SanPham sp = new SanPham(maSP, tenSP, thuongHieu, donGia, soLuong, donViTinh);
                 listSP.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }
    
}
