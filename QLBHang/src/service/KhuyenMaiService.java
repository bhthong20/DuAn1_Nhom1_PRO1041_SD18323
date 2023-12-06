/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ChiTietSanPham;
import entity.PhieuGiamGia;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.KhuyenMaiRepository;
import service.ServiceImpl.KhuyenMaiServiceImpl;

/**
 *
 * @author thong
 */
public class KhuyenMaiService implements KhuyenMaiServiceImpl {

    KhuyenMaiRepository kmRepo = new KhuyenMaiRepository();

    @Override
    public List<PhieuGiamGia> listGetAll() {
        return kmRepo.listGetAll();
    }

    @Override
    public List<ChiTietSanPham> listGetAllCTSP() {
        return kmRepo.listGetAllCTSP();
    }

    @Override
    public void addPhieuGiamGia(PhieuGiamGia giamGia) {
        try {
            kmRepo.addPhieuGiamGia(giamGia);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(PhieuGiamGia giamGia, String ma) {
        try {
            kmRepo.update(giamGia, ma);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String ma) {
        try {
            kmRepo.delete(ma);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PhieuGiamGia> searchTen(String ma) {
        return kmRepo.searchTen(ma);
    }

    @Override
    public List<PhieuGiamGia> searchhtg(Boolean htg) {
        return kmRepo.searchhtg(htg);
    }

    @Override
    public List<PhieuGiamGia> searchNgay(String ngaybd, String kt) {
        return kmRepo.searchNgay(ngaybd,kt);
    }

    @Override
    public PhieuGiamGia getOne1(String ma) {
        return kmRepo.getOne1(ma);
    }

}
