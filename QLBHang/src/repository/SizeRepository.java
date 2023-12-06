/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.KichThuoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SizeRepository {

    public List<KichThuoc> getAll() {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[KichThuoc]";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<KichThuoc> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new KichThuoc(rs.getInt(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public KichThuoc getOneSize(int ma) {
        String query = "SELECT [Ma]"
                + "      ,[Ten]"
                + "  FROM [dbo].[KichThuoc] WHERE Ma = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new KichThuoc(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addSanPham(KichThuoc kt) {
        int check = 0;
        String query = "INSERT INTO [dbo].[KichThuoc]"
                + "           ([Ten])"
                + "     VALUES(?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenKT());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSanPham(KichThuoc kt, int ma) {
        int check = 0;
        String query = "UPDATE [dbo].[KichThuoc]"
                + "   SET [Ten] = ?"
                + " WHERE Ma =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenKT());
            ps.setObject(2, ma);
            
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteSanPham(int ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[KichThuoc]"
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
        KichThuoc gv = new KichThuoc(101,"Size 100");
        boolean testdelete = new SizeRepository().addSanPham(gv);
        System.out.println(testdelete);
    }
}
