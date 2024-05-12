/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class PhanCong {

    /**
     * @return the trangThai
     */
    

    /**
     * @return the tenSP
     */
    public SanPham getTenSP() {
        return tenSP;
    }

    /**
     * @param tenSP the tenSP to set
     */
    public void setTenSP(SanPham tenSP) {
        this.tenSP = tenSP;
    }

    /**
     * @return the maPC
     */
  

    /**
     * @return the maCN
     */
    public CongNhan getMaCN() {
        return maCN;
    }

    /**
     * @param maCN the maCN to set
     */
    public void setMaCN(CongNhan maCN) {
        this.maCN = maCN;
    }

    /**
     * @return the tenCN
     */
    public CongNhan getTenCN() {
        return tenCN;
    }

    /**
     * @param tenCN the tenCN to set
     */
    public void setTenCN(CongNhan tenCN) {
        this.tenCN = tenCN;
    }

    /**
     * @return the maSP
     */
    public SanPham getMaSP() {
        return maSP;
    }

    /**
     * @param maSP the maSP to set
     */
    public void setMaSP(SanPham maSP) {
        this.maSP = maSP;
    }

    /**
     * @return the maCD
     */
    public CongDoan getMaCD() {
        return maCD;
    }

    /**
     * @param maCD the maCD to set
     */
    public void setMaCD(CongDoan maCD) {
        this.maCD = maCD;
    }

    /**
     * @return the tenCD
     */
    public CongDoan getTenCD() {
        return tenCD;
    }

    /**
     * @param tenCD the tenCD to set
     */
    public void setTenCD(CongDoan tenCD) {
        this.tenCD = tenCD;
    }
   
    private CongNhan maCN;
    private CongNhan tenCN;
    private SanPham maSP;
    private SanPham tenSP;
    private CongDoan maCD;
    private CongDoan tenCD;
   

    public PhanCong() {
    }

    public PhanCong(CongNhan maCN, CongNhan tenCN, SanPham maSP, SanPham tenSP, CongDoan maCD, CongDoan tenCD) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maCD = maCD;
        this.tenCD = tenCD;
    }

    

   
    

    

    

   
    
    
    
}
