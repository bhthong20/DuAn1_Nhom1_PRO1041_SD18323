/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SanPhamRepository {

    public List<SanPham> getAll() {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[SanPham]";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<SanPham> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new SanPham(rs.getString(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public SanPham getOne(String ma) {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[SanPham] WHERE Ma = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new SanPham(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addSanPham(SanPham sp) {
        int check = 0;
        String query = "INSERT INTO [dbo].[SanPham]"
                + "           ([Ma]"
                + "           ,[Ten])"
                + "     VALUES (?,?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, "");
            ps.setObject(2, sp.getTenSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSanPham(SanPham sp, String ma) {
        int check = 0;
        String query = "UPDATE [dbo].[SanPham]"
                + "   SET [Ma] = ?"
                + "      ,[Ten] = ?"
                + " WHERE Ma =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            ps.setObject(3, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteSanPham(String ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[SanPham]"
                + "      WHERE Ma = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

//    public static void main(String[] args) {
//        SanPham sp = new SanPham(1, "Quan");
//        new SanPhamRepository().addSanPham(sp);
//        new SanPhamRepository().getAll().forEach(s -> System.out.println(s.toString()));
////        new RepositorySanPham().deleteSanPham("E9C88C2F-2260-429A-B22E-19BD089997FF");
//    }
}
