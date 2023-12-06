/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.ChiTietSanPham;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.KHToanCuc;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thong
 */
public class ThongKeRpository {

    public List<HoaDonChiTiet> getListTK() {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) - (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hdct.TrangThai=1\n"
                + "               Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }

    public BigDecimal getListDoangThu() {
        BigDecimal max = null;
        String sql = "select SUM(ctsp.GiaBan * SoLuong) as'thanhtien'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hdct.TrangThai=1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("thanhtien");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public BigDecimal getListTienLai() {
        BigDecimal max = null;
        String sql = "select SUM((ctsp.GiaBan * SoLuong) - (GiaTriGiamToiDa*SoLuong) - (ctsp.GiaNhap*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hdct.TrangThai=1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("tienlai");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public int getListhoaDon() {
        int max = 0;
        String sql = "select COUNT(DISTINCT MaHoaDon)as'hoaDon' from HoaDon\n"
                + "where TinhTrang = 1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("hoaDon");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public int getLisKhachHang() {
        int max = 0;
        String sql = "select COUNT(DISTINCT IdKH)as'So' from HoaDon\n"
                + "where TinhTrang = 1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("So");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public List<HoaDonChiTiet> searchSoLuong() {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) -(GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1 \n"
                + "Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon\n"
                + "order by slbanra desc";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }

    public List<HoaDonChiTiet> searchDoanhThu() {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong) -(ctsp.GiaNhap * SoLuong)- (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1\n"
                + "Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon\n"
                + "order by thanhtien desc";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }

    public List<HoaDonChiTiet> searchHoaDon() {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) - (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1\n"
                + "Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon\n"
                + "order by hoadonbanra desc";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }

    public List<HoaDonChiTiet> searchTenSP(String tenSP) {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) - (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1 and Ten like ?\n"
                + "Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenSP + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));
                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }

    public List<HoaDonChiTiet> searchNgay(String ngayTao, String ngaythanhToan) {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',hd.NgayThanhToan,PhieuGiamGia.GiaTriGiamToiDa,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) - (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1 and hd.NgayThanhToan between ? and ?\n"
                + "Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,hd.NgayThanhToan,PhieuGiamGia.GiaTriGiamToiDa";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngayTao);
            ps.setString(2, ngaythanhToan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));
                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getString("NgayThanhToan"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listTK;
    }

    public BigDecimal doangThuNgay(String ngayTao, String ngaythanhToan) {
        BigDecimal max = null;
        String sql = "select sum(ctsp.GiaBan * SoLuong) as 'ThanhToan'\n"
                + "from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "where hdct.TrangThai=1 and hd.NgayThanhToan between ? and ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngayTao);
            ps.setString(2, ngaythanhToan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("ThanhToan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
        return max;
    }

    public BigDecimal tienLaiNgay(String ngayTao, String ngaythanhToan) {
        BigDecimal max = null;
        String sql = "select SUM((ctsp.GiaBan * SoLuong) - (GiaTriGiamToiDa*SoLuong) - (ctsp.GiaNhap*SoLuong)) as'ThanhToan'\n"
                + "from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "where hdct.TrangThai=1 and hd.NgayThanhToan between ? and ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngayTao);
            ps.setString(2, ngaythanhToan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("ThanhToan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
        return max;
    }

    public List<HoaDon> bieuDo() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "select MONTH(NgayThanhToan), SUM(TongTien)\n"
                + "from HoaDon\n"
                + "WHERE TinhTrang = 1\n"
                + "Group by MONTH(NgayThanhToan)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listHD.add(new HoaDon(rs.getString(1), rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHD;
    }

    public BigDecimal getListDoangThuTheoMonth(String thang) {
        BigDecimal max = null;
        String sql = "select SUM(ctsp.GiaBan * SoLuong) as 'thanhtien'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hd.TinhTrang=1 and MONTH(NgayTao) = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("thanhtien");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public BigDecimal getListTienLaiTheoMonth(String thang) {
        BigDecimal max = null;
        String sql = "select SUM((ctsp.GiaBan * SoLuong) - (GiaTriGiamToiDa*SoLuong) - (ctsp.GiaNhap*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hdct.TrangThai=1 and MONTH(NgayTao) = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("tienlai");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public int getListhoaDonTheoMonth(String thang) {
        int max = 0;
        String sql = "select COUNT(DISTINCT MaHoaDon)as'hoaDon' from HoaDon\n"
                + "where TinhTrang = 1 and MONTH(NgayTao) = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("hoaDon");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public int getLisKhachHangTheoMonth(String thang) {
        int max = 0;
        String sql = "select COUNT(DISTINCT IdKH)as'So' from HoaDon\n"
                + "where TinhTrang = 1 and MONTH(NgayTao) = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("So");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public List<HoaDonChiTiet> getListTKTheoMonth(String thang) {
        List<HoaDonChiTiet> listTK = new ArrayList<>();
        String sql = "select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)-(ctsp.GiaNhap * SoLuong) - (GiaTriGiamToiDa*SoLuong)) as 'tienlai'\n"
                + "                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon\n"
                + "                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP\n"
                + "                							join SanPham as sp on ctsp.MaSP = sp.Ma\n"
                + "                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP\n"
                + "                where hdct.TrangThai=1 and MONTH(NgayTao) = ?\n"
                + "               Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTiet(ctsp, rs.getInt("slbanra"), rs.getString("Ten"), rs.getInt("hoadonbanra"), rs.getDouble("thanhtien"), rs.getDouble("GiaTriGiamToiDa"), rs.getInt("SoLuongTon"), rs.getDouble("tienlai")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTK;
    }
}
