/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import entity.NhanVien;
import entity.TrinhDo;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.PhongBan;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class NhanVienHCDAO {
    public List<NhanVien> getALLNV(){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public boolean createNV(NhanVien nv){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into NhanVienHanhChanh values(?,?,?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nv.getMaNV());
            pstm.setString(2, nv.getMaPB().getMaPB());
            pstm.setString(3, nv.getHoTen());
            pstm.setDate(4, nv.getNgaySinh());
            pstm.setDate(5, nv.getNgayCT());
            pstm.setString(6, nv.getDiaChi());
            pstm.setString(7, nv.getSdt());
            pstm.setBoolean(8, nv.isGioiTinh());
            pstm.setString(9, nv.getTrinhDo().getMaTrinhDo());
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
    public void removeNV(String manv){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from NhanVienHanhChanh where maNV=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, manv);
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
    public boolean capNhat(NhanVien nv){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update NhanVienHanhChanh set MaPB=?, TenNV=?,NgaySinh=?,NgayThamGiaCT=?,DiaChi=?,Sđt=?,GioiTinh=?,MaTrinhDo=? where MaNV=?";
        PreparedStatement stmt = null;
        try {
            stmt =conn.prepareStatement(sql);
            stmt.setString(1, nv.getMaPB().getMaPB());
            stmt.setString(2, nv.getHoTen());
            stmt.setDate(3, nv.getNgaySinh());
            stmt.setDate(4, nv.getNgayCT());
            stmt.setString(5, nv.getDiaChi());
            stmt.setString(6, nv.getSdt());
            stmt.setBoolean(7, nv.isGioiTinh());
            stmt.setString(8, nv.getTrinhDo().getMaTrinhDo());
            stmt.setString(9, nv.getMaNV());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<NhanVien> findNVbyMaNV(String ma){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where MaNV= '"+ma+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
//    public int  soNgayLD(){
//        int a=0;
//         try {
//            ConnectDB1.getInstance();
//            Connection conn = ConnectDB1.getConnection();
//            String sql= "SELECT PhieuChamCong_NV.MaNV, COUNT(PhieuChamCong_NV.DiLam) SoNgayLamDC\n" +
//            "FROM     NhanVienHanhChanh INNER JOIN\n" +
//"                  PhieuChamCong_NV ON NhanVienHanhChanh.MaNV = PhieuChamCong_NV.MaNV\n" +
//"where PhieuChamCong_NV.DiLam = 1 and PhieuChamCong_NV.MaNV='NV01'\n" +
//"group by PhieuChamCong_NV.MaNV";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                String maNV = rs.getString(1);
//                a = rs.getInt(2);
//            }
//            return a;
//            } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	return so;
//    }
    
    public List<NhanVien> findNVbyGT(int check){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where GioiTinh= " + check;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<NhanVien> findNVbyPB(String maPB){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where MaPB = '"+maPB+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<NhanVien> findPBvsGT(String maPB, int gt){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where MaPB = '"+maPB+"' and GioiTinh ="+gt;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<NhanVien> findTenNV(String ten){
        List<NhanVien> lst1 = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where TenNV like N'%"+ten+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst1.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }
    
    public List<NhanVien> findSDT(String sdt){
        List<NhanVien> lst1 = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where Sđt like N'%"+sdt+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt1 = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt1, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst1.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }
    
    public List<NhanVien> findDiaChi(String diachi){
        List<NhanVien> lst1 = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from NhanVienHanhChanh where DiaChi like N'%"+diachi+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(3);
                String diaChi = rs.getString(6);
                String sdt1 = rs.getString(7);
                Date ngSinh = rs.getDate(4);
                Date ngCT = rs.getDate(5);
                boolean gioiTinh = rs.getBoolean(8);
                TrinhDo trinhDo = new TrinhDo(rs.getString(9));
                PhongBan pb = new PhongBan(rs.getString(2));
                NhanVien nv = new NhanVien(maNV, tenNV, diaChi, sdt1, trinhDo, ngSinh, ngCT, gioiTinh, pb);
                lst1.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }
}
