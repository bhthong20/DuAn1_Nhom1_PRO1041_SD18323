/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.ChiTietSanPham;
import entity.PhieuGiamGia;
import java.util.List;

/**
 *
 * @author thong
 */
public interface KhuyenMaiServiceImpl {

    List<PhieuGiamGia> listGetAll();

    List<ChiTietSanPham> listGetAllCTSP();

    void addPhieuGiamGia(PhieuGiamGia giamGia);

    void update(PhieuGiamGia giamGia, String ma);

    void delete(String ma);

    List<PhieuGiamGia> searchTen(String ma);

    List<PhieuGiamGia> searchhtg(Boolean htg);

    List<PhieuGiamGia> searchNgay(String ngaybd, String kt);
    
    PhieuGiamGia getOne1(String ma);

}
