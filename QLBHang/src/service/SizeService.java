/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.KichThuoc;
import java.util.ArrayList;
import java.util.List;
import repository.SizeRepository;
import service.ServiceImpl.SizeServiceImpl;

/**
 *
 * @author admin
 */
public class SizeService implements SizeServiceImpl {

    private SizeRepository spRep = new SizeRepository();

    @Override
    public List<KichThuoc> getAll() {
        List<KichThuoc> listAll = new ArrayList<>();
        return listAll = spRep.getAll();
    }

    @Override
    public KichThuoc getOneSize(int ma) {
        return spRep.getOneSize(ma);
    }

    @Override
    public String add(KichThuoc kt) {
        if (spRep.addSanPham(kt)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(KichThuoc kt, int ma) {
        if (spRep.updateSanPham(kt, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(int ma) {
        if (spRep.deleteSanPham(ma)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }
}
