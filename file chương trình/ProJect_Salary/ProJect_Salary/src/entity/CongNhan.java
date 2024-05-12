/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Tuan Kiet Admin
 */
public class CongNhan {

    public CongNhan(String maCN, String tenCN, String sđt, String diaChi, Date ngaySinh, boolean gioiTinh, PhongBan maPB) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.sđt = sđt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maPB = maPB;
    }

    public CongNhan(String maCN, String tenCN) {
        this.maCN = maCN;
        this.tenCN = tenCN;
    }

    public CongNhan(String maCN) {
        this.maCN = maCN;
    }


    String maCN, tenCN, sđt, diaChi;
    Date ngaySinh;
    boolean gioiTinh;
    PhongBan maPB;





    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public PhongBan getMaPB() {
        return maPB;
    }

    public void setMaPB(PhongBan maPB) {
        this.maPB = maPB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.maCN);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CongNhan other = (CongNhan) obj;
        return Objects.equals(this.maCN, other.maCN);
    }

    @Override
    public String toString() {
        return "CongNhan{" + "maCN=" + maCN + ", tenCN=" + tenCN + ", s\u0111t=" + sđt + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", maPB=" + maPB + '}';
    }
  
}
