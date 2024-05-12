/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.source.tree.TryTree;
import connect.ConnectDB1;
import entity.BangCongCN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.LuongCongNhan;
import entity.CongNhan;
import entity.CaLamViec;
import entity.CongDoan;
import entity.SanPham;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Tuan Kiet Admin
 */
public class LuongCNDAO {

    public List<LuongCongNhan> getALLLuongCN1() {
        List<LuongCongNhan> listLuong = new ArrayList<LuongCongNhan>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "Select * From LuongCongNhan";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int maluong = rs.getInt(1);
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
                int soSPLamDuoc = rs.getInt(4);
                int tongCaLam = rs.getInt(5);
                int thangluong = rs.getInt(6);
                int namLuong = rs.getInt(7);
                float tongLuong = rs.getFloat(8);
                CongNhan cn = new CongNhan(maCN, tenCN);
                LuongCongNhan lg = new LuongCongNhan(maluong, cn, cn, soSPLamDuoc, tongCaLam, thangluong, namLuong, tongLuong);
                listLuong.add(lg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLuong;

    }
    
    public List<LuongCongNhan> getLuongCN1ThangNam(int thang, int nam) {
        List<LuongCongNhan> listLuong = new ArrayList<LuongCongNhan>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "Select * From LuongCongNhan where ThangLuong ="+thang+" and  NamLuong = "+nam;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int maluong = rs.getInt(1);
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
                int soSPLamDuoc = rs.getInt(4);
                int tongCaLam = rs.getInt(5);
                int thangluong = rs.getInt(6);
                int namLuong = rs.getInt(7);
                float tongLuong = rs.getFloat(8);
                CongNhan cn = new CongNhan(maCN, tenCN);
                LuongCongNhan lg = new LuongCongNhan(maluong, cn, cn, soSPLamDuoc, tongCaLam, thangluong, namLuong, tongLuong);
                listLuong.add(lg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLuong;

    }

    public List<LuongCongNhan> getALLLuongCN2() {
        List<LuongCongNhan> listLuong = new ArrayList<LuongCongNhan>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "Select * From LuongCongNhan";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
                int soSPLamDuoc = rs.getInt(4);
                int tongCaLam = rs.getInt(5);
                int thangluong = rs.getInt(6);
                int namLuong = rs.getInt(7);
                float tongLuong = rs.getFloat(8);
                CongNhan cn = new CongNhan(maCN, tenCN);
//                LuongCongNhan lg = new LuongCongNhan(maluong, cn, cn, soSPLamDuoc, tongCaLam, thangluong, namLuong, tongLuong);
                LuongCongNhan lg = new LuongCongNhan(cn, cn, soSPLamDuoc, tongCaLam, thangluong, namLuong, tongLuong);
                listLuong.add(lg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLuong;

    }

    public List<BangCongCN> getALLCNCC(String thang, String nam) {
        List<BangCongCN> lst = new ArrayList<BangCongCN>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select MaCN,TenCN from PhieuCong_CN where MONTH(NgayCham)='" + thang + "' and YEAR(NgayCham)= '" + nam + "' group by MaCN,TenCN";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCN = rs.getString(1);
                String tenCn = rs.getString(2);
                CongNhan cn = new CongNhan(maCN, tenCn);
                BangCongCN bc = new BangCongCN(cn, cn);
                lst.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public int findSLSPLamDuoc(String maCN,String thang) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select sum(SoSPChamCong) from PhieuCong_CN  where MaCN='" + maCN + "'and MONTH(NgayCham)='" + thang + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int sl = rs.getInt(1);
                return sl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int findSoCaLamDuoc(String maCN,String thang ) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select count(MaCa) from PhieuCong_CN  where MaCN='" + maCN + "'and MONTH(NgayCham)='" + thang + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int sl = rs.getInt(1);
                return sl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int findSoCaTang(String maCN,String thang) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select count(MaCn) from PhieuCong_CN  where Tangca=1 and MaCN='" + maCN + "'and MONTH(NgayCham)='" + thang + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int sl = rs.getInt(1);
                return sl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getALLNamCC() {
        List<String> lst = new ArrayList<String>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select YEAR(ngaycham)  from PhieuCong_CN group by YEAR(ngaycham)";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nam = rs.getString(1);
                lst.add(nam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<String> getALLThangCC() {
        List<String> lst = new ArrayList<String>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select month(ngaycham)  from PhieuCong_CN group by month(ngaycham)";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String thang = rs.getString(1);
                lst.add(thang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public Double getALLLuong(String macn) {
        double tong = 0;
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
                String sql = "SELECT SoSPChamCong,DonGiaCĐ,TangCa FROM PhieuCong_CN INNER JOIN CongDoan  ON PhieuCong_CN.MaCĐ=CongDoan.MaCĐ where MaCN='" + macn + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                double luong = 0;
                int sl = rs.getInt(1);
                double dongiasp = rs.getDouble(2);
                boolean tangca = rs.getBoolean(3);

                if (tangca == true) {
                    luong = dongiasp * 1.5 * sl;
                } else {
                    luong = dongiasp * sl;
                }
                tong += luong;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tong;
    }

    public boolean tinhLuongCN(LuongCongNhan lcn) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        PreparedStatement pstm = null;
        try {
            String sql = "insert into LuongCongNhan values(?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, lcn.getMaCN().getMaCN());
            pstm.setString(2, lcn.getTenCN().getTenCN());
            pstm.setInt(3, lcn.getSoSPLamDuoc());
            pstm.setInt(4, lcn.getTongCaLam());
            pstm.setInt(5, lcn.getThangLuong());
            pstm.setInt(6, lcn.getNamLuong());
            pstm.setDouble(7, lcn.getTongLuong());
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                pstm.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//    public List<LuongCongNhan> getALLCNTheoThangNam(String thang, String nam) {
//        List<LuongCongNhan> lst = new ArrayList<LuongCongNhan>();
//        try {
//            ConnectDB1.getInstance();
//            Connection conn = ConnectDB1.getConnection();
//            String sql = "select * from LuongCongNhan where MONTH(NgayCham)='" + thang + "' and YEAR(NgayCham)= '" + nam + "'";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String maCN = rs.getString(1);
//                String tenCn = rs.getString(2);
//                CongNhan cn = new CongNhan(maCN, tenCn);
//                BangCongCN bc = new BangCongCN(cn, cn);
//                lst.add(bc);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lst;
//    }
    public void deleteLuongCN(String maluong) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from luongcongnhan where maluong=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maluong);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
