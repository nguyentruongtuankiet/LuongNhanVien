/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import connect.ConnectDB1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.PhanCong;
import entity.CongNhan;
import entity.CongDoan;
import entity.SanPham;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class PhanCongDAO {
   
    public List<PhanCong> getPhanCong(){
          List<PhanCong> listPC = new ArrayList<PhanCong>();
           ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "Select * From PhanCong";
            try {
             Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCD = rs.getString(1);
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
               
                String tenCD = rs.getString(4);
                String maSP = rs.getString(5);
                String tenSP = rs.getString(6);
                
                CongNhan cn = new CongNhan(maCN, tenCN);
                CongDoan cd = new CongDoan(maCD, tenCD);
                SanPham sp = new SanPham(maSP, tenSP);
                
                PhanCong pc = new PhanCong(cn, cn, sp, sp, cd, cd);
                listPC.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
           return listPC;
         
    }
   
    public boolean createPC(PhanCong pc ){
         ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        String sql = "insert into PhanCong values (?,?,?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, pc.getMaCD().getMaCD());
            pstm.setString(2, pc.getMaCN().getMaCN());
            pstm.setString(3, pc.getTenCN().getTenCN());
            pstm.setString(4, pc.getTenCD().getTenCD());
            pstm.setString(5, pc.getMaSP().getMaSP());
            pstm.setString(6, pc.getTenSP().getTenSP());
           
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean editPC(PhanCong pc){
         ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update PhanCong set maCN = ?, tenCN = ? , tenCD = ?, maSP = ?, tenSP = ? where maCĐ = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pc.getMaCN().getMaCN());
            stmt.setString(2, pc.getTenCN().getTenCN());
            stmt.setString(3, pc.getTenCD().getTenCD());
            stmt.setString(4, pc.getMaCD().getMaCD());
            stmt.setString(5, pc.getMaSP().getMaSP());
            stmt.setString(6, pc.getTenSP().getTenSP());
            
            
            return stmt.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean removePC(String maCD, String maCN){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from PhanCong where maCĐ = ? and maCN = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maCD);
            stmt.setString(2, maCN);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     public String getTenCNTheoMa(String maCongNhan){
        ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
        String sql = "select TenCN from CongNhan where MaCN = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maCongNhan);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("TenCN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
     
         public String getMaCNTheoTen(String tenCongNhan){
         ConnectDB1.getInstance();
          Connection conn = ConnectDB1.getConnection();
           String sql = "select MaCN from CongNhan where TenCN = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenCongNhan);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("MaCN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
         public String getTenSPTheoMa(String maSanPham){
        ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
        String sql = "select tenSP from SanPham where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("TenSP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
         public List<PhanCong> LayCacTPTrongPC(String tenSanPham){
        List<PhanCong> lst1 = new ArrayList<PhanCong>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "select maCĐ, maCN, TenCN, TenCD, maSP, TenSP from PhanCong where TenSP = ?";
        try {
            
           PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenSanPham);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String maCD = rs.getString(1);
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
                String tenCD = rs.getString(4);
                String maSP = rs.getString(5);
                String tenSP = rs.getString(6);
               CongDoan cd = new CongDoan(maCD, tenCD);
               CongNhan cn = new CongNhan(maCN, tenCN);
               SanPham sp = new SanPham(maSP, tenSP);
                PhanCong pc = new PhanCong(cn, cn, sp, sp, cd, cd);
                lst1.add(pc);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst1;
        
        
    }
}
