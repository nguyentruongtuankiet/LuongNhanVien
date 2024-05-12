/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connect.ConnectDB1;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import entity.LuongNhanVien;
import java.text.DecimalFormat;
/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {
    public List<Object[]> getAllSLnv(){
        List<Object[]> lst = new ArrayList<>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "SELECT MaPB, COUNT(*) as SOLUONGNV FROM NhanVienHanhChanh GROUP BY MaPB";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maPB = rs.getString(1);
                int soLuong = rs.getInt(2);
                Object[] obj = {maPB, soLuong};
                lst.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
    
   public List<Object[]> getLuongNamThang(int thang, int nam){
       List<Object[]> lst = new ArrayList<>();
       try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "SELECT MaNV, TongLuong FROM  LuongNhanVienHanhChanh where ThangLuong = "+thang+" and NamLuong = "+nam;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                double luong = rs.getDouble(2);
                Object[] obj = {maNV,luong};
                lst.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
   }
   
   public List<Object[]> getLuongTable(int thang, int nam){
       DecimalFormat df = new DecimalFormat("###,000");
       List<Object[]> lst = new ArrayList<>();
       try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "SELECT MaNV,TenNV,SoNgayLamDuoc,SoNgayTangCa, TongLuong FROM  LuongNhanVienHanhChanh where ThangLuong = "+thang+" and NamLuong = "+nam;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                int soNgayLamDc = rs.getInt(3);
                int soNgayTangCa = rs.getInt(4);
                double luong = rs.getDouble(5);
                Object[] obj = {maNV,tenNV,soNgayLamDc,soNgayTangCa,df.format(luong)};
                lst.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
   }
   
   public List<Object[]> thongkeLuong(int thang, int nam){
       DecimalFormat df = new DecimalFormat("###,000");
       DecimalFormat df1 = new DecimalFormat("0.0");
       List<Object[]> lst = new ArrayList<>();
       try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select AVG([SoNgayLamDuoc]+0.0), \n" +
"		AVG([SoNgayTangCa]+0.0), \n" +
"		ROUND(SUM([TongLuong]),0),\n" +
"		ROUND(MAX([TongLuong]),0),\n" +
"		ROUND(MIN([TongLuong]),0)\n" +
"from LuongNhanVienHanhChanh where ThangLuong = "+thang+" and NamLuong="+nam;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                double songaylamdc = rs.getDouble(1);
                double songaytangca = rs.getDouble(2);
                double  tongluong = rs.getDouble(3);
                double luongcaonhat = rs.getDouble(4);
                double luongthapnhat = rs.getDouble(5);
                Object[] obj = {df1.format(songaylamdc),df1.format(songaytangca),df.format(luongcaonhat),df.format(luongthapnhat),df.format(tongluong)};
                lst.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
   }
}
