/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gui.FrmPhanCongCN;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Tuan Kiet Admin
 */
public class BangCongCN {

    public BangCongCN(CaLamViec maCa, CaLamViec tenCa, CongNhan maCN, CongNhan tenCN, CongDoan maCD, CongDoan tenCD, int soLuongLamDc, Date ngayCham, CongDoan trangThai, boolean coMat, boolean coPhep, boolean tangCa) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.soLuongLamDc = soLuongLamDc;
        this.ngayCham = ngayCham;
        this.trangThai = trangThai;
        this.coMat = coMat;
        this.coPhep = coPhep;
        this.tangCa = tangCa;
    }

    public BangCongCN() {
    }

    public CaLamViec getMaCa() {
        return maCa;
    }

    public void setMaCa(CaLamViec maCa) {
        this.maCa = maCa;
    }

    public CaLamViec getTenCa() {
        return tenCa;
    }

    public void setTenCa(CaLamViec tenCa) {
        this.tenCa = tenCa;
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

    public CongDoan getMaCD() {
        return maCD;
    }

    public void setMaCD(CongDoan maCD) {
        this.maCD = maCD;
    }

    public CongDoan getTenCD() {
        return tenCD;
    }

    public void setTenCD(CongDoan tenCD) {
        this.tenCD = tenCD;
    }

    public int getSoLuongLamDc() {
        return soLuongLamDc;
    }

    public void setSoLuongLamDc(int soLuongLamDc) {
        this.soLuongLamDc = soLuongLamDc;
    }

    public Date getNgayCham() {
        return ngayCham;
    }

    public void setNgayCham(Date ngayCham) {
        this.ngayCham = ngayCham;
    }

    public CongDoan getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(CongDoan trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isCoMat() {
        return coMat;
    }

    public void setCoMat(boolean coMat) {
        this.coMat = coMat;
    }

    public boolean isCoPhep() {
        return coPhep;
    }

    public void setCoPhep(boolean coPhep) {
        this.coPhep = coPhep;
    }

    public boolean isTangCa() {
        return tangCa;
    }

    public void setTangCa(boolean tangCa) {
        this.tangCa = tangCa;
    }

    @Override
    public String toString() {
        return "BangCongCN{" + "maCa=" + maCa + ", tenCa=" + tenCa + ", maCN=" + maCN + ", tenCN=" + tenCN + ", maCD=" + maCD + ", tenCD=" + tenCD + ", soLuongLamDc=" + soLuongLamDc + ", ngayCham=" + ngayCham + ", trangThai=" + trangThai + ", coMat=" + coMat + ", coPhep=" + coPhep + ", tangCa=" + tangCa + '}';
    }

    public BangCongCN(CaLamViec maCa, CaLamViec tenCa, CongNhan maCN, CongNhan tenCN, CongDoan maCD, CongDoan tenCD, int soLuongLamDc, Date ngayCham, boolean coMat, boolean coPhep, boolean tangCa) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.soLuongLamDc = soLuongLamDc;
        this.ngayCham = ngayCham;
        this.coMat = coMat;
        this.coPhep = coPhep;
        this.tangCa = tangCa;
    }



    public CaLamViec maCa;
    public CaLamViec tenCa;
    public CongNhan maCN;
    public CongNhan tenCN;
    public CongDoan maCD;
    public CongDoan tenCD;
    public int soLuongLamDc;
    public Date ngayCham;
    public CongDoan trangThai;
    private boolean coMat;
    private boolean coPhep;
    private boolean tangCa;

    public BangCongCN(CongNhan maCN, CongNhan tenCN) {
        this.maCN = maCN;
        this.tenCN = tenCN;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maCa);
        hash = 47 * hash + Objects.hashCode(this.maCN);
        hash = 47 * hash + Objects.hashCode(this.maCD);
        hash = 47 * hash + Objects.hashCode(this.ngayCham);
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
        final BangCongCN other = (BangCongCN) obj;
        if (!Objects.equals(this.maCa, other.maCa)) {
            return false;
        }
        if (!Objects.equals(this.maCN, other.maCN)) {
            return false;
        }
        if (!Objects.equals(this.maCD, other.maCD)) {
            return false;
        }
        return Objects.equals(this.ngayCham, other.ngayCham);
    }



   

}
