/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangRepo {

  
    public List<KhachHang> findAll() {
        List<KhachHang> customers = new ArrayList();

        try {
            Connection connection = DBConnection.getConnection();
            String query = " select MaKhachHang,TenKhachHang,DiaChi,SoDienThoai,Email,GioiTinh,TrangThai  from KhachHang ";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String ma = rs.getString("MaKhachHang");
                String ten = rs.getString("TenKhachHang");
                String diachi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");

                boolean gioitinh = rs.getBoolean("GioiTinh");
                Integer trangthai = rs.getInt("TrangThai");

                KhachHang customer = new KhachHang();
                customer.setMaKH(ma);
                customer.setTenKH(ten);
                customer.setDiaChi(diachi);
                customer.setSdt(sdt);
                customer.setEmail(email);

                customer.setGioiTinh(gioitinh);
                customer.setTrangThai(trangthai);

                customers.add(customer);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi" + ex.toString());
        }

        return customers;
    }

  
    public boolean addDL(KhachHang kh) {
        try {
            Connection connection = DBConnection.getConnection();

            //2.viết câu truy vấn
            String query = "INSERT INTO KhachHang (MaKhachHang,TenKhachHang,DiaChi,SoDienThoai,Email,GioiTinh,TrangThai) VALUES("
                    + "''"
                    + ","
                    + "N'" + kh.getTenKH() + "'"
                    + ","
                    + "N'" + kh.getDiaChi() + "'"
                    + ","
                    + "'" + kh.getSdt() + "'"
                    + ","
                    + "'" + kh.getEmail() + "'"
                    + ","
                    + kh.isgioitinh()
                    + ","
                    + kh.getTrangThai()
                    + ")";
            System.out.println(query);
            Statement st = connection.createStatement();
            //3.thực thi câu lệnh
            int affectedRow = st.executeUpdate(query);
            System.out.println(affectedRow);
            st.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi");
            return false;
        }
    }


    public boolean update(String ma, KhachHang kh) {
        try {
            Connection connection = DBConnection.getConnection();

            //2.viết câu truy vấn
            String query = "UPDATE KhachHang" + " SET" + " " + "TenKhachHang=N'"
                    + kh.getTenKH() + "'"
                    + "," + "DiaChi="
                    + "N'" + kh.getDiaChi() + "'"
                    + "," + "SoDienThoai="
                    + "'" + kh.getSdt() + "'"
                    + "," + "Email="
                    + "'" + kh.getEmail() + "'"
                    + "," + "GioiTinh="
                    + kh.isgioitinh()
                    + "," + "TrangThai="
                    + kh.getTrangThai()
                    + "WHERE MaKhachHang =" + "'" + ma + "'";
            System.out.println(query);
            Statement st = connection.createStatement();
            //3.thực thi câu lệnh
            int affectedRow = st.executeUpdate(query);
            System.out.println(affectedRow);
            st.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi" + e.toString());
            return false;
        }
    }

    public int deleteById(String Ma) {
        int result = -1;
        try {
            Connection connection = DBConnection.getConnection();
            String query = "DELETE FROM KhachHang WHERE MaKhachHang = " + "'" + Ma + "'";
            System.out.println(query);
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("Lỗi");
        }

        return result;
    }

    public List<KhachHang> phantrang(Integer phantu) {
        List<KhachHang> customers = new ArrayList();
        KhachHang kh = new KhachHang();

        try {
            Connection connection = DBConnection.getConnection();

            String where_condition = "select MaKhachHang,TenKhachHang,DiaChi,SoDienThoai,Email,GioiTinh,TrangThai ,  count(0) over() as total_row \n"
                    + "from KhachHang \n"
                    + "\n"
                    + "order by MaKhachHang\n"
                    + "offset " + phantu + " rows fetch next 5 rows only";
            String query = where_condition;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println(query);
            while (rs.next()) {
                String ma = rs.getString("MaKhachHang");
                String ten = rs.getString("TenKhachHang");
                String diachi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                boolean gioitinh = rs.getBoolean("GioiTinh");
                Integer trangthai = rs.getInt("TrangThai");
                KhachHang customer = new KhachHang();
                customer.setMaKH(ma);
                customer.setTenKH(ten);
                customer.setDiaChi(diachi);
                customer.setSdt(sdt);
                customer.setEmail(email);

                customer.setGioiTinh(gioitinh);
                customer.setTrangThai(trangthai);

                customers.add(customer);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi" + ex.toString());
        }

        return customers;
    }


    public List<KhachHang> timkiemphantrang(String mA, int phantu, int tt) {
        List<KhachHang> customers = new ArrayList();
        KhachHang kh = new KhachHang();

        try {
            Connection connection = DBConnection.getConnection();
            String tthai = "trangthai";

            if (tt == -1) {
                tthai = tthai;
            } else {
                tthai = String.valueOf(tt);
            }
            String where_condition = "where (MaKhachHang like '%" + mA + "%' or TenKhachHang like '%" + mA + "%' or DiaChi like '%" + mA + "%' )" + "and trangthai like   " + tthai + " ";
            String phantrang = "order by MaKhachHang\n"
                    + "offset " + phantu + " rows fetch next 5 rows only";
            String query = "select MaKhachHang,TenKhachHang,DiaChi,SoDienThoai,Email,GioiTinh,TrangThai from KhachHang " + where_condition
                    + phantrang;
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String ma = rs.getString("MaKhachHang");
                String ten = rs.getString("TenKhachHang");
                String diachi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                boolean gioitinh = rs.getBoolean("GioiTinh");
                Integer trangthai = rs.getInt("TrangThai");

                KhachHang customer = new KhachHang();
                customer.setMaKH(ma);
                customer.setTenKH(ten);
                customer.setDiaChi(diachi);
                customer.setSdt(sdt);
                customer.setEmail(email);

                customer.setGioiTinh(gioitinh);
                customer.setTrangThai(trangthai);

                customers.add(customer);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi" + ex.toString());
        }

        return customers;
    }
}
