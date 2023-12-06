/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.DongSP;
import java.util.ArrayList;
import java.util.List;
import repository.HangRepository;
import service.ServiceImpl.DongSPServiceImpl;

/**
 *
 * @author admin
 */
public class DongSPService implements DongSPServiceImpl {

    private HangRepository spRep = new HangRepository();

    @Override
    public List<DongSP> getAll() {
        List<DongSP> listAll = new ArrayList<>();
        return listAll = spRep.getAll();
    }

    @Override
    public DongSP getOneDSP(int ma) {
        return spRep.getOneDSP(ma);
    }

    @Override
    public String add(DongSP dsp) {
        if (spRep.addSanPham(dsp)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(DongSP dsp, int ma) {
        if (spRep.updateSanPham(dsp, ma)) {
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

