/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.HoaDon;
import entity.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author thong
 */
public interface ThongKeServiceImpl {

    List<HoaDonChiTiet> getListTK();

    BigDecimal getListDoangThu();

    int getLisKhachHang();

    int getListhoaDon();

    BigDecimal getListTienLai();

    List<HoaDonChiTiet> searchDoanhThu();

    List<HoaDonChiTiet> searchHoaDon();

    List<HoaDonChiTiet> searchSoLuong();

    List<HoaDonChiTiet> searchTenSP(String tenSP);

    List<HoaDon> bieuDo();

    List<HoaDonChiTiet> searchNgay(String ngayTao, String ngaythanhToan);

    BigDecimal doangThuNgay(String ngayTao, String ngaythanhToan);

    BigDecimal getListDoangThuTheoMonth(String thang);

    BigDecimal getListTienLaiTheoMonth(String thang);

    int getListhoaDonTheoMonth(String thang);

    int getLisKhachHangTheoMonth(String thang);
    
    List<HoaDonChiTiet> getListTKTheoMonth(String thang);
    
    BigDecimal tienLaiNgay(String ngayTao, String ngaythanhToan) ;
}
