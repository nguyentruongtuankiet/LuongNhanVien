/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class BangCongNV {
    int maCong;
    NhanVien maNhanVien;
    NhanVien tenNhanVien;
    Date ngayCham;
    boolean diLam;
    boolean nghiPhep;
    boolean tangCa;

    public int getMaCong() {
        return maCong;
    }

    public void setMaCong(int maCong) {
        this.maCong = maCong;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public NhanVien getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(NhanVien tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgayCham() {
        return ngayCham;
    }

    public void setNgayCham(Date ngayCham) {
        this.ngayCham = ngayCham;
    }

    public boolean isDiLam() {
        return diLam;
    }

    public void setDiLam(boolean diLam) {
        this.diLam = diLam;
    }

    public boolean isNghiPhep() {
        return nghiPhep;
    }

    public void setNghiPhep(boolean nghiPhep) {
        this.nghiPhep = nghiPhep;
    }

    public boolean isTangCa() {
        return tangCa;
    }

    public void setTangCa(boolean tangCa) {
        this.tangCa = tangCa;
    }

    public BangCongNV() {
    }

    public BangCongNV(int maCong, NhanVien maNhanVien, NhanVien tenNhanVien, Date ngayCham, boolean diLam, boolean nghiPhep, boolean tangCa) {
        this.maCong = maCong;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayCham = ngayCham;
        this.diLam = diLam;
        this.nghiPhep = nghiPhep;
        this.tangCa = tangCa;
    }

    public BangCongNV(NhanVien maNhanVien, NhanVien tenNhanVien, Date ngayCham, boolean diLam, boolean nghiPhep, boolean tangCa) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayCham = ngayCham;
        this.diLam = diLam;
        this.nghiPhep = nghiPhep;
        this.tangCa = tangCa;
    }

    
    @Override
    public String toString() {
        return "BangCongNV{" + "maCong=" + maCong + ", maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", ngayCham=" + ngayCham + ", diLam=" + diLam + ", nghiPhep=" + nghiPhep + ", tangCa=" + tangCa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.maNhanVien);
        hash = 29 * hash + Objects.hashCode(this.ngayCham);
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
        final BangCongNV other = (BangCongNV) obj;
        if (!Objects.equals(this.maNhanVien, other.maNhanVien)) {
            return false;
        }
        return Objects.equals(this.ngayCham, other.ngayCham);
    }

    

    



     
    
    
}
