/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
//import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class NhanVien {
    String maNV,hoTen,diaChi, sdt;
    Date ngaySinh, ngayCT;
    boolean gioiTinh;
    PhongBan maPB;
    TrinhDo trinhDo;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public TrinhDo getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(TrinhDo trinhDo) {
        this.trinhDo = trinhDo;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayCT() {
        return ngayCT;
    }

    public void setNgayCT(Date ngayCT) {
        this.ngayCT = ngayCT;
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

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }
    

    public NhanVien(String maNV, String hoTen, String diaChi, String sdt, TrinhDo trinhDo, Date ngaySinh, Date ngayCT, boolean gioiTinh, PhongBan maPB) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trinhDo = trinhDo;
        this.ngaySinh = ngaySinh;
        this.ngayCT = ngayCT;
        this.gioiTinh = gioiTinh;
        this.maPB = maPB;
    }

    public NhanVien(String maNV, String hoTen, String sdt, PhongBan maPB) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.maPB = maPB;
    }
    
    public NhanVien(String maNV, String hoTen, PhongBan maPB , TrinhDo trinhDo) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.maPB = maPB;
        this.trinhDo = trinhDo;
    }

    public NhanVien(String maNV, String hoTen) {
        this.maNV = maNV;
        this.hoTen = hoTen;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", sdt=" + sdt + ", trinhDo=" + trinhDo + ", ngaySinh=" + ngaySinh + ", ngayCT=" + ngayCT + ", gioiTinh=" + gioiTinh + ", maPB=" + maPB + '}';
    }
    
    
}
