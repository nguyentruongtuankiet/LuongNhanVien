/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import entity.CongNhan;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.PhongBan;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Tuan Kiet Admin
 */
public class CongNhanDAO {
     public List<CongNhan> getALLCN(){
        List<CongNhan> lst = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan nv = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
       public boolean createNV(CongNhan cn){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into CongNhan values(?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cn.getMaCN());
            pstm.setString(2, cn.getMaPB().getMaPB());
            pstm.setString(3, cn.getTenCN());
            pstm.setString(4, cn.getSđt());
            pstm.setString(5, cn.getDiaChi());
            pstm.setBoolean(6, cn.isGioiTinh());           
            pstm.setDate(7, cn.getNgaySinh());
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
        public void removeCN(String macn){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from CongNhan where maCN=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, macn);
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
         public boolean capNhat(CongNhan cn){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update CongNhan set MaPB=?, TenCN=?,Sđt=?,DiaChi=?,GioiTinh=?, NgaySinh=? where MaCN=?";
        PreparedStatement stmt = null;
        try {
            stmt =conn.prepareStatement(sql);
            stmt.setString(1, cn.getMaPB().getMaPB());
            stmt.setString(2, cn.getTenCN());
            stmt.setString(3, cn.getSđt() );
            stmt.setString(4, cn.getDiaChi());
            stmt.setBoolean(5, cn.isGioiTinh());
            stmt.setDate(6, cn.getNgaySinh());
            stmt.setString(7, cn.getMaCN());
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
       
    public List<CongNhan> findCNbyMaCN(String ma){
        List<CongNhan> lst = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where MaCN= '"+ma+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
      
    public List<CongNhan> findCNbyGT(int check){
        List<CongNhan> lst = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where GioiTinh="+check+"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<CongNhan> findCNbyPB(String maPB){
        List<CongNhan> lst = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where MaPB = '"+maPB+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<CongNhan> findPBvsGT(String maPB, int gt){
        List<CongNhan> lst = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where MaPB = '"+maPB+"' and GioiTinh ="+gt;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst;
    }
    
    public List<CongNhan> findTenCN(String ten){
        List<CongNhan> lst1 = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where TenCN like N'%"+ten+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt, diaChi, ngSinh, gioiTinh, pb);
                lst1.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }
    
    public List<CongNhan> findSDT(String sdt){
        List<CongNhan> lst1 = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where Sđt like N'%"+sdt+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt1 = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt1, diaChi, ngSinh, gioiTinh, pb);
                lst1.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }
    
    public List<CongNhan> findDiaChi(String diachi){
        List<CongNhan> lst1 = new ArrayList<CongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CongNhan where DiaChi like N'%"+diachi+"%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()){
                String maCN = rs.getString(1);
                String tenCN = rs.getString(3);
                String diaChi = rs.getString(5);
                String sdt1 = rs.getString(4);
                Date ngSinh = rs.getDate(7);
                boolean gioiTinh = rs.getBoolean(6);
                PhongBan pb = new PhongBan(rs.getString(2));
                CongNhan cn = new CongNhan(maCN, tenCN, sdt1, diaChi, ngSinh, gioiTinh, pb);
                lst1.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return lst1;
    }

}
