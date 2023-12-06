/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.MauSac;
import java.util.List;

/**
 *
 * @author admin
 */
public interface MauSacServiceImpl {
    List<MauSac> getAll();

    MauSac getOneMS(int ma);

    String add(MauSac ms);

    String update(MauSac ms, int ma);

    String delete(int ma);
}
