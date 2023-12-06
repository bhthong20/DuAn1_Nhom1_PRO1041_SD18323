/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import Utilities.DBConnection;
import java.sql.Connection;

import entity.ChucVu;
import entity.NhanVien;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */


public class ChucVuRepository {
 private Connection conn;
 public  ChucVuRepository(){

         this.conn = DBConnection.getConnection();
 }
 public List<ChucVu> fillAll(){
      List<ChucVu> ds = new ArrayList<>();
        
        String sql = "SELECT ma,ten FROM ChucVu";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
               
                Integer ma = rs.getInt("ma");
                String ten = rs.getString("ten");
                ChucVu cv = new ChucVu(ma, ten);
                ds.add(cv);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ds; 
 }
 public ChucVu getOne(String Ma){
       ChucVu cv = null;
        Connection conn = DBConnection.getConnection();
        
        String sql = "SELECT ma,ten FROM ChucVu";
        try {
             PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, Ma);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
               
                Integer ma = rs.getInt("ma");
                String ten = rs.getString("ten");
              cv = new ChucVu(ma, ten);
        
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cv; 
 }
   public void insert(ChucVu domainModel)
    {
        String sql = "INSERT INTO [dbo].[ChucVu]\n" +
"           ([Ten])\n" +
"     VALUES\n" +
"           (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
          
            ps.setString(1, domainModel.getTenCV());
            
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      public void Update(ChucVu cv, int ma) {
        String sql = "update ChucVu set Ten = ? where Ma = ?";
        try {
           PreparedStatement ps = conn.prepareStatement(sql);
          
            ps.setString(1, cv.getTenCV());
            ps.setInt(2, ma);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void Delete(String ma) {
        String sql = "Delete from ChucVu where Ma= ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,ma);
            ps.execute();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
