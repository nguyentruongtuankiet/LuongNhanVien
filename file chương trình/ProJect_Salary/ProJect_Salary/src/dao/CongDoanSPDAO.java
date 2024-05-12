/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.CongDoan;
import java.util.List;
import entity.SanPham;
import java.util.ArrayList;
import connect.ConnectDB1;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class CongDoanSPDAO {

    private PreparedStatement pstm;
    public List<CongDoan> getCongDoan(){
           List<CongDoan> lstCD = new ArrayList<CongDoan>();
        try {
         
            
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "Select * From CongDoan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCD = rs.getString(1);
                String maSP = rs.getString(2);
                String tenSP = rs.getString(3);
                String tenCD = rs.getString(4);
                float donGiaCD = rs.getFloat(5);
                int soLuong = rs.getInt(6);
                int maRangBuoc = rs.getInt(7);
                
                SanPham sp = new SanPham(maSP, tenSP, soLuong);
                CongDoan cd = new CongDoan(maCD, tenCD, sp, sp, donGiaCD, sp.getSoLuong(), maRangBuoc);
                lstCD.add(cd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCD;
    }
     public boolean createCD(CongDoan cd){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        
        String sql = "insert into CongDoan values (?,?,?,?,?,?,?, ?)";
        try {
               pstm = conn.prepareStatement(sql);
               pstm.setString(1, cd.getMaCD());
               pstm.setString(2, cd.getMaSP().getMaSP());
               
               pstm.setString(3, cd.getTenSP().getTenSP());
               pstm.setString(4, cd.getTenCD());
               pstm.setFloat(5, cd.getDonGiaCD());
               pstm.setInt(6, cd.getSoLuong());
               pstm.setInt(7, cd.getMaRangBuoc());
               pstm.setBoolean(8, cd.kiemTraCongDoan());
               return pstm.executeUpdate()>0;
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return false;
    }
    public boolean removeCD(String maCD){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from CongDoan where maCĐ=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maCD);
          
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }
     public boolean editCD(CongDoan cd){
         ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update CongDoan set maSP=?,tenSP=?,tenCĐ=?,DonGiaCĐ=?,SoLuong=?,MaRangBuoc =? where maCĐ=?";
        PreparedStatement stmt = null;
        try {
            pstm = conn.prepareStatement(sql);
               
               pstm.setString(1, cd.getMaSP().getMaSP());
               
               pstm.setString(2, cd.getTenSP().getTenSP());
               pstm.setString(3, cd.getTenCD());
               pstm.setFloat(4, cd.getDonGiaCD());
               pstm.setInt(5, cd.getSoLuong());
               pstm.setInt(6, cd.getMaRangBuoc());
               
               pstm.setString(7, cd.getMaCD());
               return pstm.executeUpdate()>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<CongDoan> LayCacTPTrongCD(String maSanPham){
        List<CongDoan> lst1 = new ArrayList<CongDoan>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "select MaCĐ, TenCĐ, SoLuong, maRangBuoc, trangThai from CongDoan where MaSP = ?";
        try {
            
           PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String maCD = rs.getString(1);
                String tenCD = rs.getString(2);
                int soLuong = rs.getInt(3);
                int maRangBuoc = rs.getInt(4);
                boolean trangThai = rs.getBoolean(5);
                SanPham sp = new SanPham(soLuong);
                CongDoan cd = new CongDoan(maCD, tenCD, sp.getSoLuong(), maRangBuoc);
                lst1.add(cd);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst1;
        
    }
    
   
}
