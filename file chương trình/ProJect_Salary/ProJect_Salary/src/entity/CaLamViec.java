/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Tuan Kiet Admin
 */
public class CaLamViec {

    public CaLamViec() {
    }

    public CaLamViec(String maCa, String tenCa, String gioLam) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.gioLam = gioLam;
    }

    public CaLamViec(String maCa) {
        this.maCa = maCa;
    }

    public CaLamViec(String maCa, String tenCa) {
        this.maCa = maCa;
        this.tenCa = tenCa;
    }
    
    
    String maCa,tenCa,gioLam;

    @Override
    public String toString() {
        return "CaLamViec{" + "maCa=" + maCa + ", tenCa=" + tenCa + ", gioLam=" + gioLam + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maCa);
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
        final CaLamViec other = (CaLamViec) obj;
        return Objects.equals(this.maCa, other.maCa);
    }

    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public String getGioLam() {
        return gioLam;
    }

    public void setGioLam(String gioLam) {
        this.gioLam = gioLam;
    }
}
