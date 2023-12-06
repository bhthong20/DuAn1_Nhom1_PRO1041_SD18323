/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ChatLieu;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import repository.ChatLieuRepository;
import service.ServiceImpl.ChatLieuServiceImpl;

/**
 *
 * @author admin
 */
public class ChatLieuService implements ChatLieuServiceImpl {

    private ChatLieuRepository spRep = new ChatLieuRepository();

    @Override
    public List<ChatLieu> getAll() {
        List<ChatLieu> cls = new ArrayList<>();
        return cls = spRep.getAll();
    }

    @Override
    public ChatLieu getOneCL(int ma) {
        return spRep.getOneCL(ma);
    }

    @Override
    public String add(ChatLieu ct) {
        if (spRep.addChatLieu(ct)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(ChatLieu ct, int ma) {
        if (spRep.updateChatLieu(ct, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(int ten) {
        if (spRep.deleteChatLieu(ten)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }
}
