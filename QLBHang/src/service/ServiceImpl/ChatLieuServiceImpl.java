/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.ServiceImpl;

import entity.ChatLieu;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChatLieuServiceImpl {
    List<ChatLieu> getAll();

    ChatLieu getOneCL(int ma);

    String add(ChatLieu ct);

    String update(ChatLieu ct, int ma);

    String delete(int ten);
}
