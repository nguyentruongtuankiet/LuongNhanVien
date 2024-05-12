/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import connect.ConnectDB1;
import entity.PhongBan;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class PhongBanDAO {
    public List<PhongBan> getAllPB(){
        List<PhongBan> lstPB = new ArrayList<PhongBan>();
        try {
            ConnectDB1.getInstance();
            Connection conn = ConnectDB1.getConnection();
            String sql= "select * from PhongBan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maPB = rs.getString(1);
                String tenPB =rs.getString(2);
                PhongBan pb = new PhongBan(maPB, tenPB);
                lstPB.add(pb);
            }
        } catch (Exception e) {
        }
        return lstPB;
    }
    
    public boolean createPB(PhongBan pb){
        ConnectDB1.getInstance();
	Connection conn = ConnectDB1.getConnection();
	PreparedStatement pstm = null;
        try {
            String sql = "insert into PhongBan values(?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, pb.getMaPB());
            pstm.setString(2, pb.getTenPB());
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
    
    public void deletePB(String maPB){
        ConnectDB1.getInstance();
        Connection conn = ConnectDB1.getConnection();
        String sql = "delete from PhongBan where MaPB=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maPB);
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
    
   
}
