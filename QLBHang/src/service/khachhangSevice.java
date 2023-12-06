/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.KhachHang;
import java.util.ArrayList;

import java.util.List;
import repository.KhachHangRepo;
import service.ServiceImpl.Ikhsv;

/**
 *
 * @author ADMIN
 */
public class khachhangSevice implements Ikhsv {

    private KhachHangRepo KhachHang = new KhachHangRepo() {
    };

    @Override
    public List<KhachHang> getAll() {
        return KhachHang.findAll();
    }

    @Override
    public boolean add(KhachHang kh) {
        return KhachHang.addDL(kh);
    }

    @Override
    public boolean update(String ma, KhachHang kh) {
        return KhachHang.update(ma, kh);
    }

    @Override
    public int deleteById(String Ma) {
        return KhachHang.deleteById(Ma);
    }

    @Override
    public List<KhachHang> phantrang(Integer phantu) {
        return KhachHang.phantrang(phantu);
    }

    @Override
    public List<KhachHang> timkiemphantrang(String mA, int phantu, int tt) {
        return KhachHang.timkiemphantrang(mA, phantu, tt);
    }

}
