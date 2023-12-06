/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.ChiTietSanPham;
import entity.PhieuGiamGia;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thong
 */
public class KhuyenMaiRepository {

    public List<PhieuGiamGia> listGetAll() {
        List<PhieuGiamGia> list = new ArrayList<>();
        String sql = "Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten\n"
                + "from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP \n"
                + "						join SanPham as sp on ctsp.MaSP =  sp.Ma";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham pham = new SanPham(rs.getString("Ten"));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString("MaSanPham"), pham);
                PhieuGiamGia pgg = new PhieuGiamGia(rs.getString("MaPhieu"), rs.getString("TenPhieu"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getDouble("GiaTriGiamToiDa"), rs.getBoolean("HinhThucGiam"), rs.getString("MoTa"), rs.getInt("TrangThai"), ctsp);
                list.add(pgg);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<ChiTietSanPham> listGetAllCTSP() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "Select MaCTSP , Ten\n"
                + "from ChiTietSP join SanPham on ChiTietSP.MaSP = SanPham.Ma";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham pham = new SanPham(rs.getString("Ten"));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString("MaCTSP"), pham);
                list.add(ctsp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
     public PhieuGiamGia getOne1(String ma) {
       PhieuGiamGia pgg = null;
        String sql = "Select HinhThucGiam\n"
                + "from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP \n"
                + "						join SanPham as sp on ctsp.MaSP =  sp.Ma"
                 + "						where MaSanPham = ?"
                ;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pgg = new PhieuGiamGia(rs.getBoolean("HinhThucGiam"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

      return pgg;
    }

    public void addPhieuGiamGia(PhieuGiamGia giamGia) throws SQLException {
        String sql = "Insert into PhieuGiamGia values(?,?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "");
        ps.setString(2, giamGia.getTenPhieu());
        ps.setString(3, giamGia.getNgayBatDau());
        ps.setString(4, giamGia.getNgayKetThuc());
        ps.setDouble(5, giamGia.getGiaTriGiam());
        ps.setBoolean(6, giamGia.getHinhThucGiam());
        ps.setString(7, giamGia.getMoTa());
        ps.setInt(8, giamGia.getTrangThai());
        ps.setString(9, giamGia.getCtsp().getMaCTSP());
        ps.executeUpdate();
    }

    public void update(PhieuGiamGia giamGia, String ma) throws SQLException {
        String sql = "update PhieuGiamGia set TenPhieu= ?,NgayBatDau= ?, NgayKetThuc =?, GiaTriGiamToiDa =?, HinhThucGiam =?, MoTa =?, TrangThai =?, MaSanPham =? where MaPhieu = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, giamGia.getTenPhieu());
        ps.setString(2, giamGia.getNgayBatDau());
        ps.setString(3, giamGia.getNgayKetThuc());
        ps.setDouble(4, giamGia.getGiaTriGiam());
        ps.setBoolean(5, giamGia.getHinhThucGiam());
        ps.setString(6, giamGia.getMoTa());
        ps.setInt(7, giamGia.getTrangThai());
        ps.setString(8, giamGia.getCtsp().getMaCTSP());
        ps.setString(9, ma);
        ps.executeUpdate();
    }

    public void delete(String ma) throws SQLException {
        String sql = "Delete PhieuGiamGia where MaPhieu = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ma);
        ps.executeUpdate();
    }

    public List<PhieuGiamGia> searchTen(String ma) {
        List<PhieuGiamGia> list = new ArrayList<>();
        String sql = "Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten\n"
                + "from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP \n"
                + "						join SanPham as sp on ctsp.MaSP =  sp.Ma\n"
                + "where  pgg.MaPhieu like ? or  pgg.TenPhieu like ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
             ps.setString(2, "%" + ma + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham pham = new SanPham(rs.getString("Ten"));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString("MaSanPham"), pham);
                PhieuGiamGia pgg = new PhieuGiamGia(rs.getString("MaPhieu"), rs.getString("TenPhieu"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getDouble("GiaTriGiamToiDa"), rs.getBoolean("HinhThucGiam"), rs.getString("MoTa"), rs.getInt("TrangThai"), ctsp);
                list.add(pgg);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<PhieuGiamGia> searchhtg(Boolean htg) {
        List<PhieuGiamGia> list = new ArrayList<>();
        String sql = "Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten\n"
                + "from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP \n"
                + "						join SanPham as sp on ctsp.MaSP =  sp.Ma\n"
                + "where  HinhThucGiam = ? ";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, htg);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham pham = new SanPham(rs.getString("Ten"));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString("MaSanPham"), pham);
                PhieuGiamGia pgg = new PhieuGiamGia(rs.getString("MaPhieu"), rs.getString("TenPhieu"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getDouble("GiaTriGiamToiDa"), rs.getBoolean("HinhThucGiam"), rs.getString("MoTa"), rs.getInt("TrangThai"), ctsp);
                list.add(pgg);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<PhieuGiamGia> searchNgay(String ngaybd, String kt) {
        List<PhieuGiamGia> list = new ArrayList<>();
        String sql = "Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten\n"
                + "from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP \n"
                + "						join SanPham as sp on ctsp.MaSP =  sp.Ma\n"
                + "where  NgayBatDau  between ? and ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ngaybd);
            ps.setString(2, kt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham pham = new SanPham(rs.getString("Ten"));
                ChiTietSanPham ctsp = new ChiTietSanPham(rs.getString("MaSanPham"), pham);
                PhieuGiamGia pgg = new PhieuGiamGia(rs.getString("MaPhieu"), rs.getString("TenPhieu"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getDouble("GiaTriGiamToiDa"), rs.getBoolean("HinhThucGiam"), rs.getString("MoTa"), rs.getInt("TrangThai"), ctsp);
                list.add(pgg);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
