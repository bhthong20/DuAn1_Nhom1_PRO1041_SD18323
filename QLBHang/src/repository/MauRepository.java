/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MauRepository {

    public List<MauSac> getAll() {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[MauSac]";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<MauSac> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new MauSac(rs.getInt(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public MauSac getOneMau(int ma) {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[MauSac] WHERE Ma = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new MauSac(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addSanPham(MauSac kt) {
        int check = 0;
        String query = "INSERT INTO [dbo].[MauSac]"
                + "           ([Ten])"
                + "     VALUES(?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenMS());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSanPham(MauSac kt, int ma) {
        int check = 0;
        String query = "UPDATE [dbo].[MauSac]"
                + "   SET [Ten] = ?"
                + " WHERE Ma =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenMS());
            ps.setObject(2, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteSanPham(int ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[MauSac]"
                + "      WHERE Ma = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
 public static void main(String[] args) {
        MauSac gv = new MauSac(1,"Bennel");
        boolean testdelete = new MauRepository().addSanPham(gv);
        System.out.println(testdelete);
    }
}
