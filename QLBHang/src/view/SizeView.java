/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import entity.ChatLieu;
import entity.DongSP;
import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.ChatLieuService;
import service.DongSPService;
import service.MauSacService;
import service.NSXService;
import service.SanPhamService;
import service.ServiceImpl.ChatLieuServiceImpl;
import service.ServiceImpl.DongSPServiceImpl;
import service.ServiceImpl.MauSacServiceImpl;
import service.ServiceImpl.NSXServiceImpl;
import service.ServiceImpl.SanPhamServiceImpl;
import service.ServiceImpl.SizeServiceImpl;
import service.SizeService;

/**
 *
 * @author thong
 */
public class SizeView extends javax.swing.JFrame {

    private DefaultTableModel dtmSize = new DefaultTableModel();
    private DefaultTableModel dtmDongSP = new DefaultTableModel();
    private DefaultTableModel dtmNSX = new DefaultTableModel();
    private DefaultTableModel dtmMauSac = new DefaultTableModel();
    private DefaultTableModel dtmChatLieu = new DefaultTableModel();
    private DefaultTableModel dtmSP = new DefaultTableModel();
    //NSX
    private MauSacServiceImpl msImpl = new MauSacService();
    private ChatLieuServiceImpl clImpl = new ChatLieuService();
    private SizeServiceImpl sizeImpl = new SizeService();
    private NSXServiceImpl nsxImpl = new NSXService();
    private DongSPServiceImpl dspImpl = new DongSPService();
    private SanPhamServiceImpl spImpl = new SanPhamService();

    List<NhaSanXuat> ListNSX = new ArrayList<>();
    //CHẤT LIỆU
    List<ChatLieu> ListchatLieus = new ArrayList<>();
    //MÀU SẮC
    List<MauSac> listMS = new ArrayList<>();
    //DÒNG SP
    List<DongSP> ListDongSP = new ArrayList<>();
    //SIZE
    List<KichThuoc> ListSize = new ArrayList<>();
    //Sản phẩm
    List<SanPham> ListSP = new ArrayList<>();

    private String pathImage = null;

    /**
     * Creates new form SizeView
     */
    public SizeView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Thiết kế sản phẩm");
        tbChatLieu.setModel(dtmChatLieu);
        String[] chatLieu = {"Mã", "tên"};
        dtmChatLieu.setColumnIdentifiers(chatLieu);

        tbMau.setModel(dtmMauSac);
        String[] Mau = {"Mã", "tên"};
        dtmMauSac.setColumnIdentifiers(Mau);

        tbNSX.setModel(dtmNSX);
        String[] NSX = {"Mã", "tên"};
        dtmNSX.setColumnIdentifiers(NSX);

        tbSize.setModel(dtmSize);
        String[] Size = {"Mã", "tên"};
        dtmSize.setColumnIdentifiers(Size);

        tbaLoai.setModel(dtmDongSP);
        String[] DongSP = {"Mã", "tên"};
        dtmDongSP.setColumnIdentifiers(DongSP);

        tbSP.setModel(dtmSP);
        String[] SanPham = {"Mã", "tên"};
        dtmSP.setColumnIdentifiers(SanPham);

        ListNSX = nsxImpl.getAll();
        ListchatLieus = clImpl.getAll();
        ListSize = sizeImpl.getAll();
        listMS = msImpl.getAll();
        ListDongSP = dspImpl.getAll();
        ListSP = spImpl.getAll();

        loadData(listMS);
        loadChatLieu(ListchatLieus);
        loadLoai(ListDongSP);
        loadNSX(ListNSX);
        loadSize(ListSize);
        loadSanPham(ListSP);
//        fill(0);
//        fillCL(WIDTH);
//        fillLoai(1);
//        fillNSX(1);
//        fillSize(1);
    }

    //Màu Sắc
    private void loadData(List<MauSac> listMauSac) {
        dtmMauSac.setRowCount(0);
        for (MauSac mauSac : listMauSac) {
            dtmMauSac.addRow(mauSac.todataRow());
        }
    }

    private void fill(int index) {
        MauSac mauSac = listMS.get(index);
        txtMaMau.setText(String.valueOf(mauSac.getMaMS()));
        txtNameMau.setText(mauSac.getTenMS());
    }
    //Chất Liệu

    private void loadChatLieu(List<ChatLieu> chatLieus) {
        dtmChatLieu.setRowCount(0);
        for (ChatLieu chatLieu : chatLieus) {
            dtmChatLieu.addRow(chatLieu.todataRow());
        }
    }

    private void fillCL(int index) {
        ChatLieu cl = ListchatLieus.get(index);
        txtMaCL.setText(String.valueOf(cl.getMaCL()));
        txtNameCL.setText(cl.getTenCL());
    }
    //NSX

    private void loadNSX(List<NhaSanXuat> list) {
        dtmNSX.setRowCount(0);
        for (NhaSanXuat nhaSanXuat : ListNSX) {
            dtmNSX.addRow(nhaSanXuat.todataRow());
        }
    }

    private void fillNSX(int index) {
        NhaSanXuat nsx = ListNSX.get(index);
        txtMaHang.setText(String.valueOf(nsx.getNhaSanXuat()));
        txtNameHang.setText(nsx.getTenNhaSanXuat());
    }
    //Size

    private void loadSize(List<KichThuoc> listKichThuoc) {
        dtmSize.setRowCount(0);
        for (KichThuoc kichThuoc : listKichThuoc) {
            dtmSize.addRow(kichThuoc.todataRow());
        }
    }

    private void fillSize(int index) {
        KichThuoc kichThuoc = ListSize.get(index);
        txtMaSize.setText(String.valueOf(kichThuoc.getMaKT()));
        txtNameSize.setText(kichThuoc.getTenKT());
    }

    //Loai
    private void loadLoai(List<DongSP> list) {
        dtmDongSP.setRowCount(0);
        for (DongSP dongSP : list) {
            dtmDongSP.addRow(dongSP.todataRow());
        }
    }

    private void fillLoai(int index) {
        DongSP dongSP = ListDongSP.get(index);
        txtMaLoai.setText(String.valueOf(dongSP.getMaDongSP()));
        txtNameLoai.setText(dongSP.getTenDongSP());
    }

    //sản phẩm
    private void loadSanPham(List<SanPham> list) {
        dtmSP.setRowCount(0);
        for (SanPham SP : list) {
            dtmSP.addRow(SP.todataRowSanPham());
        }
    }

    private void fillSP(int index) {
        SanPham SP = ListSP.get(index);
        txtMaSP.setText(String.valueOf(SP.getMaSP()));
        txtNameSP.setText(SP.getTenSP());
    }

    public void clear() {
        txtMaCL.setText("");
        txtNameCL.setText("");
        txtMaHang.setText("");
        txtNameHang.setText("");
        txtMaLoai.setText("");
        txtNameLoai.setText("");
        txtMaMau.setText("");
        txtNameMau.setText("");
        txtMaSP.setText("");
        txtNameSP.setText("");
        txtMaSize.setText("");
        txtNameSize.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMau = new javax.swing.JTable();
        btnAddMau = new javax.swing.JButton();
        btnUpdateMau = new javax.swing.JButton();
        btnDeleteMau = new javax.swing.JButton();
        txtMaMau = new javax.swing.JTextField();
        txtNameMau = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNSX = new javax.swing.JTable();
        btnAddHang = new javax.swing.JButton();
        btnUpdateHang = new javax.swing.JButton();
        btnDeleteHang = new javax.swing.JButton();
        txtMaHang = new javax.swing.JTextField();
        txtNameHang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbaLoai = new javax.swing.JTable();
        btnAddLoai = new javax.swing.JButton();
        btnUpdateLoai = new javax.swing.JButton();
        btnDeleteLoai = new javax.swing.JButton();
        txtMaLoai = new javax.swing.JTextField();
        txtNameLoai = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbChatLieu = new javax.swing.JTable();
        btnAđdCL = new javax.swing.JButton();
        btnUpdateCL = new javax.swing.JButton();
        btnDeleteCL = new javax.swing.JButton();
        txtMaCL = new javax.swing.JTextField();
        txtNameCL = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSize = new javax.swing.JTable();
        btnaddSize = new javax.swing.JButton();
        btnUpdateSize = new javax.swing.JButton();
        btnDeleteSize = new javax.swing.JButton();
        txtMaSize = new javax.swing.JTextField();
        txtNameSize = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtNameSP = new javax.swing.JTextField();
        btnAddSP = new javax.swing.JButton();
        btnUpdateSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbSP = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Mã:");

        jLabel6.setText("Tên:");

        tbMau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMauMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbMau);

        btnAddMau.setText("Add");
        btnAddMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMauActionPerformed(evt);
            }
        });

        btnUpdateMau.setText("Sửa");
        btnUpdateMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMauActionPerformed(evt);
            }
        });

        btnDeleteMau.setText("Xóa");
        btnDeleteMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMauActionPerformed(evt);
            }
        });

        txtMaMau.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameMau, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnAddMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteMau)
                .addGap(74, 74, 74))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNameMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateMau)
                    .addComponent(btnAddMau)
                    .addComponent(btnDeleteMau))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Màu", jPanel2);

        jLabel7.setText("Mã:");

        jLabel8.setText("Tên:");

        tbNSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNSXMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbNSX);

        btnAddHang.setText("Add");
        btnAddHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHangActionPerformed(evt);
            }
        });

        btnUpdateHang.setText("Sửa");
        btnUpdateHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHangActionPerformed(evt);
            }
        });

        btnDeleteHang.setText("Xóa");
        btnDeleteHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHangActionPerformed(evt);
            }
        });

        txtMaHang.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameHang, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnAddHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteHang)
                .addGap(60, 60, 60))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNameHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddHang)
                    .addComponent(btnUpdateHang)
                    .addComponent(btnDeleteHang)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Hãng", jPanel3);

        jLabel9.setText("Mã:");

        jLabel10.setText("Tên:");

        tbaLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbaLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbaLoaiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbaLoai);

        btnAddLoai.setText("Add");
        btnAddLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLoaiActionPerformed(evt);
            }
        });

        btnUpdateLoai.setText("Sửa");
        btnUpdateLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateLoaiActionPerformed(evt);
            }
        });

        btnDeleteLoai.setText("Xóa");
        btnDeleteLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLoaiActionPerformed(evt);
            }
        });

        txtMaLoai.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnAddLoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateLoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteLoai)
                .addGap(59, 59, 59))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNameLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLoai)
                    .addComponent(btnUpdateLoai)
                    .addComponent(btnDeleteLoai)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Loại", jPanel4);

        jLabel11.setText("Mã:");

        jLabel12.setText("Tên:");

        tbChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChatLieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbChatLieu);

        btnAđdCL.setText("Add");
        btnAđdCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAđdCLActionPerformed(evt);
            }
        });

        btnUpdateCL.setText("Sửa");
        btnUpdateCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCLActionPerformed(evt);
            }
        });

        btnDeleteCL.setText("Xóa");
        btnDeleteCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCLActionPerformed(evt);
            }
        });

        txtMaCL.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaCL, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameCL, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnAđdCL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateCL)
                .addGap(86, 86, 86)
                .addComponent(btnDeleteCL)
                .addGap(85, 85, 85))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNameCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAđdCL)
                    .addComponent(btnUpdateCL)
                    .addComponent(btnDeleteCL)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chất liệu", jPanel5);

        jLabel3.setText("Mã:");

        jLabel4.setText("Tên:");

        tbSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSizeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSize);

        btnaddSize.setText("Add");
        btnaddSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddSizeActionPerformed(evt);
            }
        });

        btnUpdateSize.setText("Sửa");
        btnUpdateSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSizeActionPerformed(evt);
            }
        });

        btnDeleteSize.setText("Xóa");
        btnDeleteSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSizeActionPerformed(evt);
            }
        });

        txtMaSize.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameSize, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnaddSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateSize)
                .addGap(86, 86, 86)
                .addComponent(btnDeleteSize)
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaddSize)
                    .addComponent(btnUpdateSize)
                    .addComponent(btnDeleteSize)))
        );

        jTabbedPane1.addTab("Size", jPanel1);

        jLabel1.setText("Mã");

        jLabel2.setText("Tên");

        txtMaSP.setEnabled(false);

        btnAddSP.setText("Add");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnUpdateSP.setText("Sửa");
        btnUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSPActionPerformed(evt);
            }
        });

        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        tbSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbSP);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(txtNameSP))
                        .addContainerGap(129, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAddSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateSP)
                        .addGap(81, 81, 81)
                        .addComponent(btnXoaSP)
                        .addGap(80, 80, 80))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNameSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSP)
                    .addComponent(btnUpdateSP)
                    .addComponent(btnXoaSP))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCLActionPerformed
        // TODO add your handling code here:
        int row = tbChatLieu.getSelectedRow();
        ChatLieu chatLieu = ListchatLieus.get(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            int ma = Integer.parseInt(txtMaCL.getText());
            JOptionPane.showMessageDialog(this, clImpl.delete(ma));
            ListchatLieus = clImpl.getAll();
            loadChatLieu(ListchatLieus);
        }
    }//GEN-LAST:event_btnDeleteCLActionPerformed

    private void btnAddMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMauActionPerformed
        // TODO add your handling code here:
        if (txtNameMau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ten = txtNameMau.getText();
            MauSac mauSac = new MauSac(ten);
            JOptionPane.showMessageDialog(this, msImpl.add(mauSac));
            listMS = msImpl.getAll();
            loadData(listMS);
            clear();
        }
    }//GEN-LAST:event_btnAddMauActionPerformed

    private void btnUpdateMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMauActionPerformed
        // TODO add your handling code here:
        int row = tbMau.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameMau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            MauSac ms = listMS.get(row);
            int ma = Integer.parseInt(txtMaMau.getText());
            ms.setTenMS(txtNameMau.getText());
            JOptionPane.showMessageDialog(this, msImpl.update(ms, ma));
            listMS = msImpl.getAll();
            loadData(listMS);
            clear();
        }
    }//GEN-LAST:event_btnUpdateMauActionPerformed

    private void btnDeleteMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMauActionPerformed
        // TODO add your handling code here:
        int row = tbMau.getSelectedRow();
        MauSac mauSac = listMS.get(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            int ma = Integer.parseInt(txtMaMau.getText());
            JOptionPane.showMessageDialog(this, msImpl.delete(ma));
            listMS = msImpl.getAll();
            loadData(listMS);
            clear();
        }
    }//GEN-LAST:event_btnDeleteMauActionPerformed

    private void btnAddHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHangActionPerformed
        // TODO add your handling code here:
        if (txtNameHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ten = txtNameHang.getText();
            NhaSanXuat nhaSanXuat = new NhaSanXuat(ten);
            JOptionPane.showMessageDialog(this, nsxImpl.add(nhaSanXuat));
            ListNSX = nsxImpl.getAll();
            loadNSX(ListNSX);
            clear();
        }
    }//GEN-LAST:event_btnAddHangActionPerformed

    private void btnUpdateHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHangActionPerformed
        // TODO add your handling code here:
        int row = tbNSX.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            NhaSanXuat nsx = ListNSX.get(row);
            int ma = Integer.parseInt(txtMaHang.getText());
            nsx.setTenNhaSanXuat(txtNameHang.getText());
            JOptionPane.showMessageDialog(this, nsxImpl.update(nsx, ma));
            ListNSX = nsxImpl.getAll();
            loadNSX(ListNSX);
            clear();
        }
    }//GEN-LAST:event_btnUpdateHangActionPerformed

    private void btnDeleteHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHangActionPerformed
        // TODO add your handling code here:
        int row = tbNSX.getSelectedRow();
        NhaSanXuat nhaSanXuat = ListNSX.get(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            int ma = Integer.parseInt(txtMaHang.getText());
            JOptionPane.showMessageDialog(this, nsxImpl.delete(ma));
            ListNSX = nsxImpl.getAll();
            loadNSX(ListNSX);
            clear();
        }
    }//GEN-LAST:event_btnDeleteHangActionPerformed

    private void btnaddSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddSizeActionPerformed
        // TODO add your handling code here:
        if (txtNameSize.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ten = txtNameSize.getText();
            KichThuoc kt = new KichThuoc(ten);
            JOptionPane.showMessageDialog(this, sizeImpl.add(kt));
            ListSize = sizeImpl.getAll();
            loadSize(ListSize);
            clear();
        }
    }//GEN-LAST:event_btnaddSizeActionPerformed

    private void btnUpdateSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSizeActionPerformed
        // TODO add your handling code here:
        int row = tbSize.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameSize.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            KichThuoc kt = ListSize.get(row);
            int ma = Integer.parseInt(txtMaSize.getText());
            kt.setTenKT(txtNameSize.getText());
            JOptionPane.showMessageDialog(this, sizeImpl.update(kt, ma));
            ListSize = sizeImpl.getAll();
            loadSize(ListSize);
            clear();
        }
    }//GEN-LAST:event_btnUpdateSizeActionPerformed

    private void btnDeleteSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSizeActionPerformed
        // TODO add your handling code here:
        int row = tbSize.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            int ma = Integer.parseInt(txtMaSize.getText());
            JOptionPane.showMessageDialog(this, sizeImpl.delete(ma));
            ListSize = sizeImpl.getAll();
            loadSize(ListSize);
            clear();
        }
    }//GEN-LAST:event_btnDeleteSizeActionPerformed

    private void btnUpdateCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCLActionPerformed
        // TODO add your handling code here:
        int row = tbChatLieu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameCL.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            ChatLieu cl = ListchatLieus.get(row);
            int ma = Integer.parseInt(txtMaCL.getText());

            cl.setTenCL(txtNameCL.getText());
            JOptionPane.showMessageDialog(this, clImpl.update(cl, ma));
            ListchatLieus = clImpl.getAll();
            loadChatLieu(ListchatLieus);
            clear();
        }
    }//GEN-LAST:event_btnUpdateCLActionPerformed

    private void btnAđdCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAđdCLActionPerformed
        // TODO add your handling code here:
        if (txtNameCL.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ten = txtNameCL.getText();
            ChatLieu chatLieu = new ChatLieu(ten);
            JOptionPane.showMessageDialog(this, clImpl.add(chatLieu));
            ListchatLieus = clImpl.getAll();
            loadChatLieu(ListchatLieus);
            clear();
        }
    }//GEN-LAST:event_btnAđdCLActionPerformed

    private void btnAddLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLoaiActionPerformed
        // TODO add your handling code here:
        if (txtNameLoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ten = txtNameLoai.getText();
            DongSP dongSP = new DongSP(ten);
            JOptionPane.showMessageDialog(this, dspImpl.add(dongSP));
            ListDongSP = dspImpl.getAll();
            loadLoai(ListDongSP);
            clear();
        }
    }//GEN-LAST:event_btnAddLoaiActionPerformed

    private void btnUpdateLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateLoaiActionPerformed
        // TODO add your handling code here:
        int row = tbaLoai.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameLoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            DongSP dsp = ListDongSP.get(row);
            int ma = Integer.parseInt(txtMaLoai.getText());
            dsp.setTenDongSP(txtNameLoai.getText());
            JOptionPane.showMessageDialog(this, dspImpl.update(dsp, ma));
            ListDongSP = dspImpl.getAll();
            loadLoai(ListDongSP);
            clear();
        }
    }//GEN-LAST:event_btnUpdateLoaiActionPerformed

    private void btnDeleteLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLoaiActionPerformed
        // TODO add your handling code here:
        int row = tbaLoai.getSelectedRow();
        DongSP dongSP = ListDongSP.get(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            int ma = Integer.parseInt(txtMaLoai.getText());
            JOptionPane.showMessageDialog(this, dspImpl.delete(ma));
            ListDongSP = dspImpl.getAll();
            loadLoai(ListDongSP);
            clear();
        }
    }//GEN-LAST:event_btnDeleteLoaiActionPerformed

    private void tbaLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaLoaiMouseClicked
        // TODO add your handling code here:
        int index = tbaLoai.getSelectedRow();
        fillLoai(index);
    }//GEN-LAST:event_tbaLoaiMouseClicked

    private void tbNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNSXMouseClicked
        // TODO add your handling code here:
        int index = tbNSX.getSelectedRow();
        fillNSX(index);
    }//GEN-LAST:event_tbNSXMouseClicked

    private void tbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChatLieuMouseClicked
        // TODO add your handling code here:
        int index = tbChatLieu.getSelectedRow();
        fillCL(index);
    }//GEN-LAST:event_tbChatLieuMouseClicked

    private void tbMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMauMouseClicked
        // TODO add your handling code here:
        int index = tbMau.getSelectedRow();
        fill(index);
    }//GEN-LAST:event_tbMauMouseClicked

    private void tbSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSizeMouseClicked
        // TODO add your handling code here:
        int index = tbSize.getSelectedRow();
        fillSize(index);
    }//GEN-LAST:event_tbSizeMouseClicked

    private void tbSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPMouseClicked
        // TODO add your handling code here:
        int index = tbSP.getSelectedRow();
        fillSP(index);
    }//GEN-LAST:event_tbSPMouseClicked

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        // TODO add your handling code here:
        if (txtNameSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            String ma = txtMaSP.getText();
            String ten = txtNameSP.getText();
            SanPham sp = new SanPham(ma, ten);
            JOptionPane.showMessageDialog(this, spImpl.add(sp));
            ListSP = spImpl.getAll();
            loadSanPham(ListSP);
        }
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void btnUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSPActionPerformed
        // TODO add your handling code here:
        int row = tbSP.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        }
        if (txtNameSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền đầy đủ dữ liệu");
            return;
        } else {
            SanPham sp = ListSP.get(row);
            String ma = txtMaSP.getText();
            sp.setTenSP(txtNameSP.getText());
            JOptionPane.showMessageDialog(this, spImpl.update(sp, ma));
            ListSP = spImpl.getAll();
            loadSanPham(ListSP);
        }
    }//GEN-LAST:event_btnUpdateSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int row = tbSP.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
        } else {
            SanPham sp = ListSP.get(row);
            String ma = sp.getMaSP();
            JOptionPane.showMessageDialog(this, spImpl.delete(ma));
            ListSP = spImpl.getAll();
            loadSanPham(ListSP);
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SizeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHang;
    private javax.swing.JButton btnAddLoai;
    private javax.swing.JButton btnAddMau;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnAđdCL;
    private javax.swing.JButton btnDeleteCL;
    private javax.swing.JButton btnDeleteHang;
    private javax.swing.JButton btnDeleteLoai;
    private javax.swing.JButton btnDeleteMau;
    private javax.swing.JButton btnDeleteSize;
    private javax.swing.JButton btnUpdateCL;
    private javax.swing.JButton btnUpdateHang;
    private javax.swing.JButton btnUpdateLoai;
    private javax.swing.JButton btnUpdateMau;
    private javax.swing.JButton btnUpdateSP;
    private javax.swing.JButton btnUpdateSize;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnaddSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbChatLieu;
    private javax.swing.JTable tbMau;
    private javax.swing.JTable tbNSX;
    private javax.swing.JTable tbSP;
    private javax.swing.JTable tbSize;
    private javax.swing.JTable tbaLoai;
    private javax.swing.JTextField txtMaCL;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaMau;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSize;
    private javax.swing.JTextField txtNameCL;
    private javax.swing.JTextField txtNameHang;
    private javax.swing.JTextField txtNameLoai;
    private javax.swing.JTextField txtNameMau;
    private javax.swing.JTextField txtNameSP;
    private javax.swing.JTextField txtNameSize;
    // End of variables declaration//GEN-END:variables
}
