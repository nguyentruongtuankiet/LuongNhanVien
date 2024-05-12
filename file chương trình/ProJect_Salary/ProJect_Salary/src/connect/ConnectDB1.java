/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class ConnectDB1 {
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost;databaseName=QuanLyLuongSanPham";

    private static Connection connection;
    private static ConnectDB1 instance = new ConnectDB1();

    public static ConnectDB1 getInstance() {
            return instance;
    }
    
    public static boolean connect() {
            try
            {
                    connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
                    if(connection != null)
                    {
                            System.out.println("Connect Database Success");
                            DatabaseMetaData databaseMetaData = (DatabaseMetaData)connection.getMetaData();
                            System.out.println("Driver name: " + databaseMetaData.getDriverName());
                            System.out.println("Driver version: " + databaseMetaData.getDriverVersion());
                            System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
                            System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
                    }
                    return true;
            }
            catch(Exception ex)
            {
                    ex.printStackTrace();
                    return false;
            }
    }

    public static boolean disconnect() {
            try
            {
                    if(connection != null)
                            connection.close();
                    return true;
            }
            catch(Exception ex)
            {
                    ex.printStackTrace();
                    return false;
            }
    }

    public static Connection getConnection() {
            return connection;
    }
    
    public static void main(String[] args) {
        if(connect()==true){
            System.out.println("ket noi thanh cong");
        }
        else{
            System.out.println("ket noi that bai");
        }
    }
}
