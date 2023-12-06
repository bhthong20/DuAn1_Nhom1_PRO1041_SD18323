/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import repository.SanPhamRepository;
import service.ServiceImpl.SanPhamServiceImpl;

/**
 *
 * @author admin
 */
public class SanPhamService implements SanPhamServiceImpl{
    private SanPhamRepository spRep = new SanPhamRepository();
    @Override
    public List<SanPham> getAll() {
         List<SanPham> listAll = new ArrayList<>();
        return listAll = spRep.getAll();
    }

    @Override
    public SanPham getOne(String ma) {
        return spRep.getOne(ma);
    }

    @Override
    public String add(SanPham sp) {
        if (spRep.addSanPham(sp)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(SanPham sp, String ma) {
        if (spRep.updateSanPham(sp, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(String ma) {
        if (spRep.deleteSanPham(ma)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }   
    
}
