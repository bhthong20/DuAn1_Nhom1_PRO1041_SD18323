/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.HoaDon;
import entity.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import repository.ThongKeRpository;
import service.ServiceImpl.ThongKeServiceImpl;

/**
 *
 * @author thong
 */
public class ThongKeService implements ThongKeServiceImpl {

    private ThongKeRpository tkRpo = new ThongKeRpository();

    @Override
    public List<HoaDonChiTiet> getListTK() {
        return tkRpo.getListTK();
    }

    @Override
    public BigDecimal getListDoangThu() {
        return tkRpo.getListDoangThu();
    }

    @Override
    public int getLisKhachHang() {
        return tkRpo.getLisKhachHang();
    }

    @Override
    public int getListhoaDon() {
        return tkRpo.getListhoaDon();
    }

    @Override
    public List<HoaDonChiTiet> searchDoanhThu() {
        return tkRpo.searchDoanhThu();
    }

    @Override
    public List<HoaDonChiTiet> searchHoaDon() {
        return tkRpo.searchHoaDon();
    }

    @Override
    public List<HoaDonChiTiet> searchSoLuong() {
        return tkRpo.searchSoLuong();
    }

    @Override
    public List<HoaDonChiTiet> searchTenSP(String tenSP) {
        return tkRpo.searchTenSP(tenSP);
    }

    @Override
    public List<HoaDon> bieuDo() {
        return tkRpo.bieuDo();
    }

    @Override
    public List<HoaDonChiTiet> searchNgay(String ngayTao, String ngaythanhToan) {
        return tkRpo.searchNgay(ngayTao, ngaythanhToan);
    }

    @Override
    public BigDecimal doangThuNgay(String ngayTao, String ngaythanhToan) {
        return tkRpo.doangThuNgay(ngayTao, ngaythanhToan);
    }

    @Override
    public BigDecimal getListTienLai() {
        return tkRpo.getListTienLai();
    }

    @Override
    public BigDecimal getListDoangThuTheoMonth(String thang) {
        return tkRpo.getListDoangThuTheoMonth(thang);
    }

    @Override
    public BigDecimal getListTienLaiTheoMonth(String thang) {
        return tkRpo.getListTienLaiTheoMonth(thang);
    }

    @Override
    public int getListhoaDonTheoMonth(String thang) {
        return tkRpo.getListhoaDonTheoMonth(thang);
    }

    @Override
    public int getLisKhachHangTheoMonth(String thang) {
        return tkRpo.getLisKhachHangTheoMonth(thang);
    }

    @Override
    public List<HoaDonChiTiet> getListTKTheoMonth(String thang) {
        return tkRpo.getListTKTheoMonth(thang);
    }

    @Override
    public BigDecimal tienLaiNgay(String ngayTao, String ngaythanhToan) {
         return tkRpo.tienLaiNgay(ngayTao,ngaythanhToan);
    }
}
