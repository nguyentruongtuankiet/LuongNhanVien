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
import entity.PhanCong;
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
public class ChamCongCNDAO {

    public List<BangCongCN> getALLChamCongCN() {
        List<BangCongCN> listCC = new ArrayList<BangCongCN>();
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "Select * From PhieuCong_CN";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCa = rs.getString(1);
                String tenCa = rs.getString(2);
                String maCN = rs.getString(3);
                String tenCN = rs.getString(4);
                int soSP = rs.getInt(5);
                String maCĐ = rs.getString(6);
                String tenCĐ = rs.getString(7);
                Date ngaycham = rs.getDate(8);
                boolean dilam = rs.getBoolean(9);
                boolean nghiphep = rs.getBoolean(10);
                boolean tangca = rs.getBoolean(11);
                CaLamViec ca = new CaLamViec(maCa, tenCa);
                CongNhan cn = new CongNhan(maCN, tenCN);
                CongDoan cd = new CongDoan(maCĐ, tenCĐ);

                BangCongCN bc = new BangCongCN(ca, ca, cn, cn, cd, cd, soSP, ngaycham, dilam, nghiphep, tangca);
                listCC.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCC;

    }

    public int findSL(String maCĐ, String maCN) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from PhanCong INNER JOIN congdoan ON congdoan.macđ=phancong.macđ  where congdoan.macđ='" + maCĐ + "'and phancong.maCN='" + maCN + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int sl = rs.getInt(12);
                return sl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String layGioLamtheoca(String maCa) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from calamviec where maCa='" + maCa + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String temca = rs.getString(3);
                return temca;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String layTenCatheoMaca(String maCa) {
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from calamviec where maCa='" + maCa + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tenca = rs.getString(2);
                return tenca;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BangCongCN> getALLCC() {
        List<BangCongCN> lst = new ArrayList<BangCongCN>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from PhieuCong_CN";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maca = rs.getString(1);
                String tenca = rs.getString(2);
                String macn = rs.getString(3);
                String tencn = rs.getString(4);
                int sl = rs.getInt(5);
                String macd = rs.getString(6);
                String tencd = rs.getString(7);
                Date ngaycham = rs.getDate(8);
                boolean dl = rs.getBoolean(9);
                boolean np = rs.getBoolean(10);
                boolean tc = rs.getBoolean(11);
                CaLamViec clv = new CaLamViec(maca, tenca);
                CongNhan cn = new CongNhan(macn, tencn);
                CongDoan cd = new CongDoan(macd, tencd);
                BangCongCN bc = new BangCongCN(clv, clv, cn, cn, cd, cd, sl, ngaycham, dl, np, tc);
                lst.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public boolean craeateChamCong(BangCongCN bc, int soLuong) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        PreparedStatement pstm = null;
        int n = 0;
        try {
            pstm = conn.prepareStatement("Insert Into PhieuCong_CN values(?,?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, bc.getMaCa().getMaCa());
            pstm.setString(2, bc.getTenCa().getTenCa());
            pstm.setString(3, bc.getMaCN().getMaCN());
            pstm.setString(4, bc.getTenCN().getTenCN());
            pstm.setInt(5, bc.getSoLuongLamDc());
            pstm.setString(6, bc.getMaCD().getMaCD());
            pstm.setString(7, bc.getTenCD().getTenCD());
            pstm.setDate(8, bc.getNgayCham());
            pstm.setBoolean(9, bc.isCoMat());
            pstm.setBoolean(10, bc.isCoPhep());
            pstm.setBoolean(11, bc.isTangCa());

            n = pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCongDoan(BangCongCN bc, int soLuong) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        PreparedStatement pstm = null;
        int n = 0;
        try {

            pstm = conn.prepareStatement("Update CongDoan set SoLuong = ? Where maCĐ = ?");
            pstm.setInt(1, soLuong);
            pstm.setString(2, bc.getMaCD().getMaCD());
            n = pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCongDoanKhiXoa(String macd, int soLuong) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        PreparedStatement pstm = null;
        int n = 0;
        try {

            pstm = conn.prepareStatement("Update CongDoan set SoLuong = ? Where maCĐ = ?");
            pstm.setInt(1, soLuong);
            pstm.setString(2, macd);
            n = pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeChamCong(String macđ, String macn, String maca) {
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from PhieuCong_CN where macđ=? and macn=? and maca=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, macđ);
            stmt.setString(2, macn);
            stmt.setString(3, maca);
//            stmt.setString(4, ngaycham);
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

    public List<BangCongCN> locTheoNgay(Date index) {
        List<BangCongCN> lst = new ArrayList<BangCongCN>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from PhieuCong_CN where NgayCham = '" + index + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maca = rs.getString(1);
                String tenca = rs.getString(2);
                String macn = rs.getString(3);
                String tencn = rs.getString(4);
                int sl = rs.getInt(5);
                String macd = rs.getString(6);
                String tencd = rs.getString(7);
                Date ngaycham = rs.getDate(8);
                boolean dl = rs.getBoolean(9);
                boolean np = rs.getBoolean(10);
                boolean tc = rs.getBoolean(11);
                CaLamViec clv = new CaLamViec(maca, tenca);
                CongNhan cn = new CongNhan(macn, tencn);
                CongDoan cd = new CongDoan(macd, tencd);
                BangCongCN bc = new BangCongCN(clv, clv, cn, cn, cd, cd, sl, ngaycham, dl, np, tc);
                lst.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
}
