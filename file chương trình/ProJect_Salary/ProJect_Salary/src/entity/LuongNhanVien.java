/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class LuongNhanVien {
    public int maLuong;
    public NhanVien maNhanVien, hoTen;
    double luongCB, phuCap, heSoLuong;
    int tongNgayLam, tongNgayTangCa;
    int thangLuong;
    int namLuong;
    
    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }
    
    

    public int getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(int maLuong) {
        this.maLuong = maLuong;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public NhanVien getHoTen() {
        return hoTen;
    }

    public void setHoTen(NhanVien hoTen) {
        this.hoTen = hoTen;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public int getTongNgayLam() {
        return tongNgayLam;
    }

    public void setTongNgayLam(int tongNgayLam) {
        this.tongNgayLam = tongNgayLam;
    }

    public int getTongNgayTangCa() {
        return tongNgayTangCa;
    }

    public void setTongNgayTangCa(int tongNgayTangCa) {
        this.tongNgayTangCa = tongNgayTangCa;
    }

    public int getThangLuong() {
        return thangLuong;
    }

    public void setThangLuong(int thangLuong) {
        this.thangLuong = thangLuong;
    }

    public int getNamLuong() {
        return namLuong;
    }

    public void setNamLuong(int namLuong) {
        this.namLuong = namLuong;
    }

    public LuongNhanVien() {
    }

    public LuongNhanVien(int maLuong, NhanVien maNhanVien, NhanVien hoTen, double luongCB, double phuCap, double heSoLuong, int tongNgayLam, int tongNgayTangCa, int thangLuong, int namLuong) {
        this.maLuong = maLuong;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.luongCB = luongCB;
        this.phuCap = phuCap;
        this.heSoLuong = heSoLuong;
        this.tongNgayLam = tongNgayLam;
        this.tongNgayTangCa = tongNgayTangCa;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
    }

    public LuongNhanVien(NhanVien maNhanVien, NhanVien hoTen, double luongCB, double phuCap, double heSoLuong, int tongNgayLam, int tongNgayTangCa, int thangLuong, int namLuong) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.luongCB = luongCB;
        this.phuCap = phuCap;
        this.heSoLuong = heSoLuong;
        this.tongNgayLam = tongNgayLam;
        this.tongNgayTangCa = tongNgayTangCa;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
    }

    public LuongNhanVien(int maLuong, NhanVien maNhanVien, double luongCB, double phuCap, int tongNgayLam, int tongNgayTangCa, int thangLuong, int namLuong) {
        this.maLuong = maLuong;
        this.maNhanVien = maNhanVien;
        this.luongCB = luongCB;
        this.phuCap = phuCap;
        this.tongNgayLam = tongNgayLam;
        this.tongNgayTangCa = tongNgayTangCa;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
    }

    public LuongNhanVien(int maLuong, NhanVien maNhanVien, double luongCB, double phuCap, double heSoLuong, int tongNgayLam, int tongNgayTangCa, int thangLuong, int namLuong) {
        this.maLuong = maLuong;
        this.maNhanVien = maNhanVien;
        this.luongCB = luongCB;
        this.phuCap = phuCap;
        this.heSoLuong = heSoLuong;
        this.tongNgayLam = tongNgayLam;
        this.tongNgayTangCa = tongNgayTangCa;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
    }

    
    
    
    
    public double tinhLuong(){
        double tt;
//        maNhanVien.getNgayCT();
        if(getTongNgayLam()>20){
            tt = ((getTongNgayLam() * getLuongCB() * getHeSoLuong())/26)+ getPhuCap() + (((getLuongCB())/26/2) * tongNgayTangCa);
        }
        else{
            tt = ((getTongNgayLam() * getLuongCB() * getHeSoLuong())/26) + (((getLuongCB())/26/2) * tongNgayTangCa);
        }
        return tt;
    }

    @Override
    public String toString() {
        return "LuongNhanVien{" + "maLuong=" + maLuong + ", maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", luongCB=" + luongCB + ", phuCap=" + phuCap + ", heSoLuong=" + heSoLuong + ", tongNgayLam=" + tongNgayLam + ", tongNgayTangCa=" + tongNgayTangCa + ", thangLuong=" + thangLuong + ", namLuong=" + namLuong + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final LuongNhanVien other = (LuongNhanVien) obj;
        if (this.thangLuong != other.thangLuong) {
            return false;
        }
        if (this.namLuong != other.namLuong) {
            return false;
        }
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }
    
    
}
