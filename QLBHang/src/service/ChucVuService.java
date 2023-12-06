/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ChucVu;
import java.util.ArrayList;
import java.util.List;
import repository.ChucVuRepository;
import service.ServiceImpl.ChucVuServiceImpl;

/**
 *
 * @author Admin
 */
public class ChucVuService implements ChucVuServiceImpl {

    private ChucVuRepository cvRepo;

    public ChucVuService() {
        this.cvRepo = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> findAll() {
        List<ChucVu> ds = new ArrayList<>();
        List<ChucVu> list = this.cvRepo.fillAll();

        for (ChucVu cv : list) {
            ChucVu vModel = new ChucVu(
                    cv.getMaCV(), cv.getTenCV());
            ds.add(vModel);
        }
        return ds;
    }

    @Override
    public void insert(ChucVu cv) {
        ChucVu cvDomainModel = new ChucVu(cv.getTenCV());
        this.cvRepo.insert(cvDomainModel);
    }

    @Override
    public void update(ChucVu cv) {
    {
        ChucVu cvDomainModel = new ChucVu(cv.getMaCV(), cv.getTenCV());
//         this.cvRepo.Update(cvDomainModel);
    }
    }

    @Override
    public void delete(String ma) {
        {
            this.cvRepo.Delete(ma);
        }
    }

}
