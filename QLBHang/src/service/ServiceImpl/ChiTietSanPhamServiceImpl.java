/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.ChatLieu;
import entity.ChiTietSanPham;
import entity.DongSP;
import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChiTietSanPhamServiceImpl {

    List<ChiTietSanPham> getAll();

    List<MauSac> getAllMs();
    
    List<SanPham> getSP();

    List<ChatLieu> getAllCL();

    List<NhaSanXuat> getAllNSX();

    List<DongSP> getAllDSP();
    
    List<KichThuoc> getAllKT();

    ChiTietSanPham getOne(String ma);
    
    ChiTietSanPham getOneByMaSP(SanPham MaSP, MauSac MaMauSac, KichThuoc MaSize);

    void add(ChiTietSanPham ctsp);

    String update(ChiTietSanPham ctsp, String ma);

    String delete(String ma);

    void updateSoLuong(ChiTietSanPham ctsp, String ma);
    
    List<ChiTietSanPham> searchLoaiSP(int MaDSSP);
    
    List<ChiTietSanPham> searchTenSP(String tenSea);
}
