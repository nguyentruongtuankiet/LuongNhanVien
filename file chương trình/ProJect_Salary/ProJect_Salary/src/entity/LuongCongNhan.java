/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.CongNhan;
import java.util.Objects;

/**
 *
 * @author Tuan Kiet Admin
 */
public class LuongCongNhan {

    public LuongCongNhan(int maLuong, CongNhan maCN, CongNhan tenCN, int soSPLamDuoc, int tongCaLam, int thangLuong, int namLuong, float tongLuong) {
        this.maLuong = maLuong;
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.soSPLamDuoc = soSPLamDuoc;
        this.tongCaLam = tongCaLam;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
        this.tongLuong = tongLuong;
    }

    public LuongCongNhan() {
    }

    public int getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(int maLuong) {
        this.maLuong = maLuong;
    }

    public CongNhan getMaCN() {
        return maCN;
    }

    public void setMaCN(CongNhan maCN) {
        this.maCN = maCN;
    }

    public CongNhan getTenCN() {
        return tenCN;
    }

    public void setTenCN(CongNhan tenCN) {
        this.tenCN = tenCN;
    }

    public int getSoSPLamDuoc() {
        return soSPLamDuoc;
    }

    public void setSoSPLamDuoc(int soSPLamDuoc) {
        this.soSPLamDuoc = soSPLamDuoc;
    }

    public int getTongCaLam() {
        return tongCaLam;
    }

    public void setTongCaLam(int tongCaLam) {
        this.tongCaLam = tongCaLam;
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

    public float getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(float tongLuong) {
        this.tongLuong = tongLuong;
    }

    @Override
    public String toString() {
        return "LuongCongNhan{" + "maLuong=" + maLuong + ", maCN=" + maCN + ", tenCN=" + tenCN + ", soSPLamDuoc=" + soSPLamDuoc + ", tongCaLam=" + tongCaLam + ", thangLuong=" + thangLuong + ", namLuong=" + namLuong + ", tongLuong=" + tongLuong + '}';
    }

    public LuongCongNhan(CongNhan maCN, CongNhan tenCN, int soSPLamDuoc, int tongCaLam, int thangLuong, int namLuong, float tongLuong) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.soSPLamDuoc = soSPLamDuoc;
        this.tongCaLam = tongCaLam;
        this.thangLuong = thangLuong;
        this.namLuong = namLuong;
        this.tongLuong = tongLuong;
    }

    private int maLuong;
    private CongNhan maCN, tenCN;
    private int soSPLamDuoc;
    private int tongCaLam;
    private int thangLuong;
    private int namLuong;
    private float tongLuong;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.maCN);
        hash = 37 * hash + Objects.hashCode(this.tenCN);
        hash = 37 * hash + this.soSPLamDuoc;
        hash = 37 * hash + this.tongCaLam;
        hash = 37 * hash + this.thangLuong;
        hash = 37 * hash + this.namLuong;
        hash = 37 * hash + Float.floatToIntBits(this.tongLuong);
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
        final LuongCongNhan other = (LuongCongNhan) obj;
        if (this.soSPLamDuoc != other.soSPLamDuoc) {
            return false;
        }
        if (this.tongCaLam != other.tongCaLam) {
            return false;
        }
        if (this.thangLuong != other.thangLuong) {
            return false;
        }
        if (this.namLuong != other.namLuong) {
            return false;
        }
        if (Float.floatToIntBits(this.tongLuong) != Float.floatToIntBits(other.tongLuong)) {
            return false;
        }
        if (!Objects.equals(this.maCN, other.maCN)) {
            return false;
        }
        return Objects.equals(this.tenCN, other.tenCN);
    }


}
