/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class TrinhDo {
    private String maTrinhDo;
    private String tenTrinhDo;
    private double hesoLuong;

    public String getMaTrinhDo() {
        return maTrinhDo;
    }

    public void setMaTrinhDo(String maTrinhDo) {
        this.maTrinhDo = maTrinhDo;
    }

    public String getTenTrinhDo() {
        return tenTrinhDo;
    }

    public void setTenTrinhDo(String tenTrinhDo) {
        this.tenTrinhDo = tenTrinhDo;
    }

    public double getHesoLuong() {
        return hesoLuong;
    }

    public void setHesoLuong(double hesoLuong) {
        this.hesoLuong = hesoLuong;
    }

    public TrinhDo(String maTrinhDo) {
        this.maTrinhDo = maTrinhDo;
    }

    public TrinhDo(String maTrinhDo, String tenTrinhDo, double hesoLuong) {
        this.maTrinhDo = maTrinhDo;
        this.tenTrinhDo = tenTrinhDo;
        this.hesoLuong = hesoLuong;
    }

    @Override
    public String toString() {
        return "TrinhDo{" + "maTrinhDo=" + maTrinhDo + ", tenTrinhDo=" + tenTrinhDo + ", hesoLuong=" + hesoLuong + '}';
    }
    
    
    
}
