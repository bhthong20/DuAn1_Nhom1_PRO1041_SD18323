/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ChatLieu;
import entity.ChiTietSanPham;
import entity.DongSP;
import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import repository.ChiTietSanPhamRepository;
import service.ServiceImpl.ChiTietSanPhamServiceImpl;


/**
 *
 * @author admin
 */
public class ChiTietSanPhamService implements ChiTietSanPhamServiceImpl {

    private ChiTietSanPhamRepository ChiTietSPRes = new ChiTietSanPhamRepository();

    @Override
    public List<ChiTietSanPham> getAll() {
        
        return ChiTietSPRes.getAll();
    }

    @Override
    public ChiTietSanPham getOne(String ma) {
        return ChiTietSPRes.getOne(ma);
    }

    @Override
    public void add(ChiTietSanPham ctsp) {
        ChiTietSPRes.add(ctsp);
    }

    @Override
    public String update(ChiTietSanPham ctsp, String ma) {
        if (ChiTietSPRes.update(ctsp, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(String ma) {
        if (ChiTietSPRes.delete(ma)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }

    @Override
    public void updateSoLuong(ChiTietSanPham ctsp, String ma) {
        ChiTietSPRes.updateSoLuong(ctsp, ma);
    }

    @Override
    public List<MauSac> getAllMs() {
        return ChiTietSPRes.getMS();
    }

    @Override
    public List<ChatLieu> getAllCL() {
        return ChiTietSPRes.getCL();
    }

    @Override
    public List<NhaSanXuat> getAllNSX() {
        return ChiTietSPRes.getNSX();
    }

    @Override
    public List<DongSP> getAllDSP() {
        return ChiTietSPRes.getDSP();
    }

    @Override
    public List<KichThuoc> getAllKT() {
        return ChiTietSPRes.getSize();
    }

    @Override
    public List<SanPham> getSP() {
        return ChiTietSPRes.getSP();
    }

    @Override
    public List<ChiTietSanPham> searchLoaiSP(int MaDSSP) {
        return ChiTietSPRes.searchLoaiSP(MaDSSP);
    }

    @Override
    public List<ChiTietSanPham> searchTenSP(String tenSea) {
        return ChiTietSPRes.searchTenSP(tenSea);
    }

    @Override
    public ChiTietSanPham getOneByMaSP(SanPham MaSP, MauSac MaMauSac, KichThuoc MaSize) {
        return ChiTietSPRes.getOneByMaSP(MaSP, MaMauSac, MaSize);
    }



}
