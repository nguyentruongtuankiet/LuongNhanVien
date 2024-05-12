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
import java.sql.Connection;
import java.sql.*;
import entity.BangCongNV;
/**
 *
 * @author ADMIN
 */
public class ChamCongNVDAO {
    public List<NhanVien> getNVChamCong(){
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select MaNV,TenNV,MaPB,Sđt from NhanVienHanhChanh";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String sdt = rs.getString(4);
                PhongBan pb = new PhongBan(rs.getString(3));
                NhanVien nv = new NhanVien(maNV, tenNV, sdt, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public boolean chamCongNV(BangCongNV bcnv){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into PhieuChamCong_NV values(?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, bcnv.getMaNhanVien().getMaNV());
            pstm.setString(2, bcnv.getTenNhanVien().getHoTen());
            pstm.setDate(3, bcnv.getNgayCham());
            pstm.setBoolean(4, bcnv.isDiLam());
            pstm.setBoolean(5, bcnv.isNghiPhep());
            pstm.setBoolean(6, bcnv.isTangCa());
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
    
    
    public List<BangCongNV> getAllBC(){
        List<BangCongNV> lst = new ArrayList<BangCongNV>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from PhieuChamCong_NV";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int maCong = rs.getInt(1);
                String maNV = rs.getString(2);
                String tenNV = rs.getString(3);
                Date ngayCham = rs.getDate(4);
                boolean diLam = rs.getBoolean(5);
                boolean phep = rs.getBoolean(6);
                boolean tangCa =rs.getBoolean(7);
                NhanVien nv = new NhanVien(maNV, tenNV);
                BangCongNV bc = new BangCongNV(maCong, nv, nv, ngayCham, diLam, phep, tangCa);
                lst.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<BangCongNV> checkCongNV(String ngayCham){
        List<BangCongNV> lst = new ArrayList<BangCongNV>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from PhieuChamCong_NV where NgayCham = '"+ngayCham+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int maCong = rs.getInt(1);
                String maNV = rs.getString(2);
                String tenNV = rs.getString(3);
                Date ngayCham1 = rs.getDate(4);
                boolean diLam = rs.getBoolean(5);
                boolean phep = rs.getBoolean(6);
                boolean tangCa =rs.getBoolean(7);
                NhanVien nv = new NhanVien(maNV, tenNV);
                BangCongNV bc = new BangCongNV(maCong, nv, nv, ngayCham1, diLam, phep, tangCa);
                lst.add(bc);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return lst;
    }
    
    public boolean suaChamCong(BangCongNV bc){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update PhieuChamCong_NV set DiLam=?,NghiPhep=?,TangCa=? where MaNV=? and NgayCham=?";
        PreparedStatement stmt = null;
        try {
            stmt =conn.prepareStatement(sql);
            stmt.setBoolean(1, bc.isDiLam());
            stmt.setBoolean(2, bc.isNghiPhep());
            stmt.setBoolean(3, bc.isTangCa());
            stmt.setString(4, bc.getMaNhanVien().getMaNV());
            stmt.setDate(5, bc.getNgayCham());
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
    
    public List<BangCongNV> locTheoNgay(Date index){
        List<BangCongNV> lst = new ArrayList<BangCongNV>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from PhieuChamCong_NV where NgayCham = '"+index+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int maCong = rs.getInt(1);
                String maNV = rs.getString(2);
                String tenNV = rs.getString(3);
                Date ngayCham = rs.getDate(4);
                boolean diLam = rs.getBoolean(5);
                boolean phep = rs.getBoolean(6);
                boolean tangCa =rs.getBoolean(7);
                NhanVien nv = new NhanVien(maNV, tenNV);
                BangCongNV bc = new BangCongNV(maCong, nv, nv, ngayCham, diLam, phep, tangCa);
                lst.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
}

