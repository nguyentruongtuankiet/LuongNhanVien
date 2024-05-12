/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.CaLamViec;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Tuan Kiet Admin
 */
public class CaLamViecDAO {
    public List<CaLamViec> getAllCaLamViec(){
        List<CaLamViec> lstCa = new ArrayList<CaLamViec>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from CaLamViec";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maCa = rs.getString(1);
                String tenCa =rs.getString(2);
                String gioLam =rs.getNString(3);
                CaLamViec ca = new CaLamViec(maCa, tenCa, gioLam);
                lstCa.add(ca);
            }
        } catch (Exception e) {
        }
        return lstCa;
    }
}
