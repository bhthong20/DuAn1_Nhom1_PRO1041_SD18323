/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.NhaSanXuat;
import java.util.ArrayList;
import java.util.List;
import repository.NSXRepository;
import service.ServiceImpl.NSXServiceImpl;
/**
 *
 * @author admin
 */
public class NSXService implements NSXServiceImpl{
 private NSXRepository spRep = new NSXRepository();
    @Override
    public List<NhaSanXuat> getAll() {
List<NhaSanXuat> listAll = new ArrayList<>();
        return listAll = spRep.getAll();    }

    @Override
    public NhaSanXuat getOneNSX(int ma) {
return spRep.getOneNSX(ma);    }

    @Override
    public String add(NhaSanXuat nsx) {
if (spRep.addSanPham(nsx)) {
            return "Add thành công";
        }
        return "Add thất bại";    }

    @Override
    public String update(NhaSanXuat nsx, int ma) {
if (spRep.updateSanPham(nsx, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";    }

    @Override
    public String delete(int ten) {
if (spRep.deleteSanPham(ten)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }

    public void insert(NhaSanXuat nhaSanXuat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
    

