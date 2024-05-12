/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.source.tree.TryTree;
import connect.ConnectDB1;
import entity.LuongCongNhan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.CongNhan;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Tuan Kiet Admin
 */
public class ThongkeCNDAO {

    public List<String> getALLNamLuong() {
        List<String> lst = new ArrayList<String>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select NamLuong from LuongCongNhan group by NamLuong";
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

    public List<String> getALLThangLuong() {
        List<String> lst = new ArrayList<String>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select ThangLuong from LuongCongNhan group by ThangLuong";
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

    public List<LuongCongNhan> getALLLuong(String thang, String nam) {
        List<LuongCongNhan> lst = new ArrayList<LuongCongNhan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from LuongCongNhan  where ThangLuong='" + thang + "' and NamLuong='" + nam + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCN = rs.getString(2);
                String tenCN = rs.getString(3);
                int soluongsp = rs.getInt(4);
                int tongCaLam = rs.getInt(5);
                int thangluong = rs.getInt(6);
                int namLuong = rs.getInt(7);
                float tongLuong = rs.getFloat(8);
                CongNhan cn = new CongNhan(maCN, tenCN);
//                LuongCongNhan lg = new LuongCongNhan(maluong, cn, cn, soSPLamDuoc, tongCaLam, thangluong, namLuong, tongLuong);
                LuongCongNhan lg = new LuongCongNhan(cn, cn, soluongsp, tongCaLam, thangluong, namLuong, tongLuong);
                lst.add(lg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;

    }
      
}
