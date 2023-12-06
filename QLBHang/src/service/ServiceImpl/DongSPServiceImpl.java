/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.DongSP;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DongSPServiceImpl {
    List<DongSP> getAll();

    DongSP getOneDSP(int ma);

    String add(DongSP dsp);

    String update(DongSP dsp, int ma);

    String delete(int ma);
}
