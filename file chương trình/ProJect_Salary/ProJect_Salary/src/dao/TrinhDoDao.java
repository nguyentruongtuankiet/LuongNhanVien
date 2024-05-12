/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.TrinhDo;
import java.util.List;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class TrinhDoDao {
    public List<TrinhDo> getAllTrinhDo(){
        List<TrinhDo> lst = new ArrayList<TrinhDo>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select * from TrinhDo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maTrinhDo = rs.getString(1);
                String tenTrinhDo = rs.getString(2);
                double hesoLuong = rs.getDouble(3);
                TrinhDo td = new TrinhDo(maTrinhDo, tenTrinhDo, hesoLuong);
                lst.add(td);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public boolean createTrinhDo(TrinhDo td){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into TrinhDo values(?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, td.getMaTrinhDo());
            pstm.setString(2, td.getTenTrinhDo());
            pstm.setDouble(3, td.getHesoLuong());
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
    
    public void deleteTrinhDo(String maTrinhDo){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from TrinhDo where MaTrinhDo=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maTrinhDo);
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
    
    public boolean updateTrinhDo(TrinhDo td){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "update TrinhDo set TenTrinhDo=?, HeSoLuong=? where MaTrinhDo=?";
        PreparedStatement stmt = null;
        try {
            stmt =conn.prepareStatement(sql);
            stmt.setString(1, td.getTenTrinhDo());
            stmt.setDouble(2, td.getHesoLuong());
            stmt.setString(3, td.getMaTrinhDo());
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
    
    public double getHeSoLuong(String maTD){
//        String hsl="";
        double hsl=0;
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql = "select HeSoLuong from TrinhDo where MaTrinhDo= N'"+maTD+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                hsl = rs.getDouble(1);
            }
            return hsl;
        } catch (Exception e) {
            e.printStackTrace();
            return hsl;
        }
    }
}
