/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.util.List;
import entity.NhanVien;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.PhongBan;
import entity.TrinhDo;
import java.sql.Connection;
import java.sql.*;
import entity.LuongNhanVien;
/**
 *
 * @author ADMIN
 */
public class LuongNVDAO {
    
    public List<NhanVien> getAllNV(){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select MaNV,TenNV,MaPB,MaTrinhDo from NhanVienHanhChanh";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                TrinhDo trinhDo = new TrinhDo(rs.getString(4));
                PhongBan pb = new PhongBan(rs.getString(3));
                NhanVien nv = new NhanVien(maNV, tenNV, pb, trinhDo);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public int getTongSoNgayLam(int month, int year, String maNV){
        int a = 0;
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "SELECT NhanVienHanhChanh.MaNV, count(PhieuChamCong_NV.DiLam) as soNgayDiLam\n" +
"FROM     NhanVienHanhChanh INNER JOIN PhieuChamCong_NV ON NhanVienHanhChanh.MaNV = PhieuChamCong_NV.MaNV\n" +
"where PhieuChamCong_NV.DiLam = 1 AND MONTH(PhieuChamCong_NV.NgayCham )= "+month+" AND NhanVienHanhChanh.MaNV = '"+maNV+"' AND YEAR(PhieuChamCong_NV.NgayCham) = "+year+" \n" +
"group by NhanVienHanhChanh.MaNV, NhanVienHanhChanh.MaTrinhDo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
//                String maNV1 = rs.getString(1);
//                String tenNV = rs.getString(2);
//                String trinhDo = rs.getString(4);
//                PhongBan pb = new PhongBan(rs.getString(3));
//                NhanVien nv = new NhanVien(maNV, tenNV, pb, trinhDo);
                  a = rs.getInt(2);
                
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
            return a;
        }
    }
    
    public int getTongSoNgayTC(int month, int year, String maNV){
        int a = 0;
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "SELECT NhanVienHanhChanh.MaNV, count(PhieuChamCong_NV.TangCa) as soNgayTangCa\n" +
"FROM     NhanVienHanhChanh INNER JOIN PhieuChamCong_NV ON NhanVienHanhChanh.MaNV = PhieuChamCong_NV.MaNV\n" +
"where PhieuChamCong_NV.TangCa = 1 AND MONTH(PhieuChamCong_NV.NgayCham )= "+month+" AND NhanVienHanhChanh.MaNV = '"+maNV+"' AND YEAR(PhieuChamCong_NV.NgayCham) = "+year+" \n" +
"group by NhanVienHanhChanh.MaNV, NhanVienHanhChanh.MaTrinhDo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
//                String maNV1 = rs.getString(1);
//                String tenNV = rs.getString(2);
//                String trinhDo = rs.getString(4);
//                PhongBan pb = new PhongBan(rs.getString(3));
//                NhanVien nv = new NhanVien(maNV, tenNV, pb, trinhDo);
                  a = rs.getInt(2);
                
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
            return a;
        }
    }
    
    public boolean tinhLuongNV(LuongNhanVien lnv){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into LuongNhanVienHanhChanh values(?,?,?,?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, lnv.getMaNhanVien().getMaNV());
            pstm.setString(2, lnv.getHoTen().getHoTen());
            pstm.setDouble(3, lnv.getLuongCB());
            pstm.setInt(4, lnv.getTongNgayTangCa());
            pstm.setInt(5, lnv.getTongNgayLam());
            pstm.setDouble(6, lnv.getHeSoLuong());
            pstm.setDouble(7, lnv.getPhuCap());
            pstm.setInt(8, lnv.getThangLuong());
            pstm.setInt(9, lnv.getNamLuong());
            pstm.setDouble(10, lnv.tinhLuong());
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try{
                pstm.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void removeLuong(String maNV){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from LuongNhanVienHanhChanh where maNV=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<LuongNhanVien> getAllLuong(){
        List<LuongNhanVien> lst = new ArrayList<LuongNhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from LuongNhanVienHanhChanh";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int maLuong = rs.getInt(1);
                String maNV = rs.getString(2);
                String tenNV = rs.getString(3);
                double luongCB = rs.getDouble(4);
                int tangCa = rs.getInt(5);
                int diLam = rs.getInt(6);
                double heSoLuong = rs.getDouble(7);
                double phuCap = rs.getDouble(8);
                int thang = rs.getInt(9);
                int nam = rs.getInt(10);
                NhanVien nv = new NhanVien(maNV,tenNV);
                //LuongNhanVien lnv = new LuongNhanVien(maLuong, nv, luongCB, phuCap, diLam, tangCa, thang, nam);
                LuongNhanVien lnv1 = new LuongNhanVien(maLuong, nv,nv, luongCB, phuCap, heSoLuong, diLam, tangCa, thang, nam);
                lst.add(lnv1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<LuongNhanVien> locLuongThangNam(int thang, int nam){
        List<LuongNhanVien> lst = new ArrayList<LuongNhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from LuongNhanVienHanhChanh where ThangLuong ="+thang+" and  NamLuong = "+nam;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int maLuong = rs.getInt(1);
                String maNV = rs.getString(2);
                String tenNV = rs.getString(3);
                double luongCB = rs.getDouble(4);
                int tangCa = rs.getInt(5);
                int diLam = rs.getInt(6);
                double heSoLuong = rs.getDouble(7);
                double phuCap = rs.getDouble(8);
                int thang1 = rs.getInt(9);
                int nam1 = rs.getInt(10);
                NhanVien nv = new NhanVien(maNV,tenNV);
                //LuongNhanVien lnv = new LuongNhanVien(maLuong, nv, luongCB, phuCap, diLam, tangCa, thang, nam);
                LuongNhanVien lnv1 = new LuongNhanVien(maLuong, nv,nv, luongCB, phuCap, heSoLuong, diLam, tangCa, thang1, nam1);
                lst.add(lnv1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
}
