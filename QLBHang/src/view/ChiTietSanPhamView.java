/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Utilities.DBConnection;
import entity.ChatLieu;
import entity.ChiTietSanPham;
import entity.DongSP;
import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.RootPaneUI;
import javax.swing.table.DefaultTableModel;
import repository.ChiTietSanPhamRepository;
import service.ChatLieuService;
import service.ChiTietSanPhamService;
import service.DongSPService;
import service.MauSacService;
import service.NSXService;
import service.SanPhamService;
import service.ServiceImpl.ChatLieuServiceImpl;
import service.ServiceImpl.ChiTietSanPhamServiceImpl;
import service.ServiceImpl.DongSPServiceImpl;
import service.ServiceImpl.MauSacServiceImpl;
import service.ServiceImpl.NSXServiceImpl;
import service.ServiceImpl.SanPhamServiceImpl;
import service.SizeService;
import service.ServiceImpl.SizeServiceImpl;

/**
 *
 * @author thong
 */
public class ChiTietSanPhamView extends javax.swing.JPanel {

    private DefaultComboBoxModel<ChatLieu> dtCL = new DefaultComboBoxModel();
    private DefaultComboBoxModel<MauSac> dtMS;
    private DefaultComboBoxModel<NhaSanXuat> dtNS = new DefaultComboBoxModel();
    private DefaultComboBoxModel<KichThuoc> dtKT = new DefaultComboBoxModel();
    private DefaultComboBoxModel<DongSP> dtDSP = new DefaultComboBoxModel();
    private DefaultComboBoxModel<SanPham> dtSP = new DefaultComboBoxModel();

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<ChiTietSanPham> listSanPhamCT = new ArrayList<>();
    private ChiTietSanPhamRepository SanPhamService = new ChiTietSanPhamRepository();
    private ChiTietSanPhamServiceImpl ctspsi = new ChiTietSanPhamService();
    private SanPhamServiceImpl spser = new SanPhamService();

    private MauSacServiceImpl msImpl = new MauSacService();
    private ChatLieuServiceImpl clImpl = new ChatLieuService();
    private SizeServiceImpl sizeImpl = new SizeService();
    private NSXServiceImpl nsxImpl = new NSXService();
    private DongSPServiceImpl dspImpl = new DongSPService();

    List<SanPham> listSP = ctspsi.getSP();
    List<ChatLieu> ListCL = ctspsi.getAllCL();
    List<MauSac> ListMS = ctspsi.getAllMs();
    List<KichThuoc> ListKT = ctspsi.getAllKT();
    List<NhaSanXuat> ListNSX = ctspsi.getAllNSX();
    List<DongSP> ListDSP = ctspsi.getAllDSP();

    List<DongSP> ListDSP1 = ctspsi.getAllDSP();
    List<NhaSanXuat> ListNSX1 = ctspsi.getAllNSX();
    String duongdananh = "";
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public ChiTietSanPhamView() {
        initComponents();
        tbCTSP.setModel(dtm);
        listSanPhamCT = ctspsi.getAll();
        String header[] = {"Ma CTSP", "Sản Phẩm", "NSX", "Màu Sắc", "Chất Liệu ", "Kích Thước", "Dòng Sản Phẩm", "Mô Tả", "Số Lượng Tồn", "Giá Nhập", "Giá Bán"};
        dtm.setColumnIdentifiers(header);
        dtCL = new DefaultComboBoxModel(ListCL.toArray());
        cbbChatLieu.setModel(dtCL);
        dtDSP = new DefaultComboBoxModel(ListDSP.toArray());
        cbbLoai.setModel(dtDSP);

        dtMS = new DefaultComboBoxModel(ListMS.toArray());
        cbbMau.setModel(dtMS);
        dtKT = new DefaultComboBoxModel(ListKT.toArray());
        cbbSzie.setModel(dtKT);
        dtNS = new DefaultComboBoxModel(ListNSX.toArray());
        cbbHang.setModel(dtNS);

        dtSP = new DefaultComboBoxModel(listSP.toArray());
        CBBsp.setModel(dtSP);

        dtDSP = new DefaultComboBoxModel(ListDSP1.toArray());
        cbbLoaiSearch.setModel(dtDSP);

        dtNS = new DefaultComboBoxModel(ListNSX1.toArray());
        cbbHangSearch.setModel(dtNS);
        loadData();

    }

    public SanPham ShowSp(String sp) {
        for (int i = 0; i < listSP.size(); i++) {
            SanPham sps = (SanPham) CBBsp.getItemAt(i);
            if (sp.equalsIgnoreCase(sps.getTenSP())) {
                return sps;
            }
        }
        return null;
    }

    public ChatLieu ShowCL(String cl) {
        for (int i = 0; i < ListCL.size(); i++) {
            ChatLieu cls = (ChatLieu) cbbChatLieu.getItemAt(i);
            if (cl.equalsIgnoreCase(cls.getTenCL())) {
                return cls;
            }
        }
        return null;
    }

    public MauSac ShowMauSac(String m) {
        for (int i = 0; i < ListMS.size(); i++) {
            MauSac ms = (MauSac) cbbMau.getItemAt(i);
            if (m.equalsIgnoreCase(ms.getTenMS())) {
                return ms;
            }
        }
        return null;
    }

    public NhaSanXuat ShowNSX(String nsx) {
        for (int i = 0; i < ListNSX.size(); i++) {
            NhaSanXuat Nsx = (NhaSanXuat) cbbHang.getItemAt(i);
            if (nsx.equalsIgnoreCase(Nsx.getTenNhaSanXuat())) {
                return Nsx;
            }
        }
        return null;
    }

    public DongSP ShowDSP(String dsp) {
        for (int i = 0; i < ListDSP.size(); i++) {
            DongSP Dsp = (DongSP) cbbLoai.getItemAt(i);
            if (dsp.equalsIgnoreCase(Dsp.getTenDongSP())) {
                return Dsp;
            }
        }
        return null;
    }

    public KichThuoc ShowSize(String size) {
        for (int i = 0; i < ListKT.size(); i++) {
            KichThuoc kt = (KichThuoc) cbbSzie.getItemAt(i);
            if (size.equalsIgnoreCase(kt.getTenKT())) {
                return kt;
            }
        }
        return null;
    }

    private void loadData() {
        List<ChiTietSanPham> ctsps = SanPhamService.getAll();
        dtm = (DefaultTableModel) tbCTSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietSanPham x : ctsps) {
            Object row[] = new Object[]{
                x.getMaCTSP(), x.getSanPham().getTenSP(), x.getNsx().getTenNhaSanXuat(), x.getMauSac().getTenMS(), x.getChatLieu().getTenCL(), x.getKichThuoc().getTenKT(), x.getDongSP().getTenDongSP(), x.getMoTa(), x.getSoLuongTon(), formatter.format(x.getGiaNhap()), formatter.format(x.getGiaBan())
            };
            dtm.addRow(row);
        }
    }
//    private void loadData(List<ChiTietSanPham> listSanPhamCT) {
//        dtm.setRowCount(0);
//        this.listSanPhamCT = ctspsi.getAll();
//        for (ChiTietSanPham ctsp : listSanPhamCT) {
//            dtm.addRow(ctsp.toDataRow());
//        }
//    }

    public SanPham fillsp(String sp) {
        for (int i = 0; i < listSP.size(); i++) {
            SanPham spp = (SanPham) CBBsp.getItemAt(i);
            if (sp.equalsIgnoreCase(spp.getTenSP())) {
                return spp;
            }
        }
        return null;
    }

    public ChatLieu fillcl(String cl) {
        for (int i = 0; i < ListCL.size(); i++) {
            ChatLieu clsp = (ChatLieu) cbbChatLieu.getItemAt(i);
            if (cl.equalsIgnoreCase(clsp.getTenCL())) {
                return clsp;
            }
        }
        return null;
    }

    public MauSac fillms(String ms) {
        for (int i = 0; i < ListMS.size(); i++) {
            MauSac mssp = (MauSac) cbbMau.getItemAt(i);
            if (ms.equalsIgnoreCase(mssp.getTenMS())) {
                return mssp;
            }
        }
        return null;
    }

    public DongSP filldsp(String dsp) {
        for (int i = 0; i < ListDSP.size(); i++) {
            DongSP dspp = (DongSP) cbbLoai.getItemAt(i);
            if (dsp.equalsIgnoreCase(dspp.getTenDongSP())) {
                return dspp;
            }
        }
        return null;
    }

    public NhaSanXuat fillnsx(String nsx) {
        for (int i = 0; i < ListNSX.size(); i++) {
            NhaSanXuat nsxs = (NhaSanXuat) cbbHang.getItemAt(i);
            if (nsx.equalsIgnoreCase(nsxs.getTenNhaSanXuat())) {
                return nsxs;
            }
        }
        return null;
    }

    public KichThuoc fillsize(String size) {
        for (int i = 0; i < ListKT.size(); i++) {
            KichThuoc ktsp = (KichThuoc) cbbSzie.getItemAt(i);
            if (size.equalsIgnoreCase(ktsp.getTenKT())) {
                return ktsp;
            }
        }
        return null;
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private double convertedToNumbers(String s) {
        String number = "";
        String[] array = s.replace(".", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        cbbLoaiSearch = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSanPhamSearch = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbbHangSearch = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBan = new javax.swing.JTextField();
        txtNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cbbLoai = new javax.swing.JComboBox();
        cbbMau = new javax.swing.JComboBox();
        cbbHang = new javax.swing.JComboBox();
        cbbSzie = new javax.swing.JComboBox();
        cbbChatLieu = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        lblAnh = new javax.swing.JLabel();
        btnAnh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        txtMCTSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CBBsp = new javax.swing.JComboBox();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTSP = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cbbLoaiSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiSearchItemStateChanged(evt);
            }
        });

        jLabel16.setText("Loại:");

        jLabel17.setText("Sản phẩm:");

        txtSanPhamSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSanPhamSearchCaretUpdate(evt);
            }
        });

        jLabel18.setText("Hãng:");

        cbbHangSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbHangSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHangSearchItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbLoaiSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtSanPhamSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(cbbHangSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLoaiSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(txtSanPhamSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHangSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1235, 706));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Sản phẩm: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Loại: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Hãng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Màu:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Size:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chất Liệu:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giá nhập:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Giá bán:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mô tả:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Số lượng:");

        txtBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBanKeyReleased(evt);
            }
        });

        txtNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhapKeyReleased(evt);
            }
        });

        cbbMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauActionPerformed(evt);
            }
        });

        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });

        cbbSzie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSzieActionPerformed(evt);
            }
        });

        cbbChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChatLieuActionPerformed(evt);
            }
        });

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane1.setViewportView(txtMota);

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAnh.setBackground(new java.awt.Color(255, 204, 0));
        btnAnh.setText("Thêm ảnh");
        btnAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnhMouseClicked(evt);
            }
        });
        btnAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/New.png"))); // NOI18N
        btnAdd.setText("  Thêm");
        btnAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Change.png"))); // NOI18N
        btnUpdate.setText("  Sửa");
        btnUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Refresh-icon.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/kisspng-computer-icons-plus-sign-clip-art-plus-sign-5b4bfbdff0b3a7.2950963015317063359859.jpg"))); // NOI18N
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã CTSP");

        CBBsp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBBspItemStateChanged(evt);
            }
        });

        txtMaSanPham.setEditable(false);
        txtMaSanPham.setForeground(new java.awt.Color(255, 204, 0));
        txtMaSanPham.setEnabled(false);

        jLabel3.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbbMau, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(97, 97, 97)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cbbSzie, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CBBsp, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel8)))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAnh)
                        .addGap(71, 71, 71)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(CBBsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbbMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6))
                                            .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(cbbSzie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnAnh)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Lọc sản phẩm");

        tbCTSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 41, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(1150, 1150, 1150))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseClicked
        // TODO add your handling code here:
        int row = tbCTSP.getSelectedRow();
        List<ChiTietSanPham> chiTietSanPhams = ctspsi.getAll();
        ChiTietSanPham ctsp = chiTietSanPhams.get(row);
        txtMCTSP.setText(ctsp.getMaCTSP());
        txtMota.setText(ctsp.getMoTa());
        txtSoLuong.setText(String.valueOf(ctsp.getSoLuongTon()));
        txtBan.setText(formatter.format(ctsp.getGiaBan()));
        txtNhap.setText(formatter.format(ctsp.getGiaNhap()));

        lblAnh.setIcon(ResizeImage(String.valueOf(ctsp.getAnhSp())));

        cbbLoai.setSelectedItem(filldsp(ctsp.getDongSP().getTenDongSP()));

        cbbChatLieu.setSelectedItem(fillcl(ctsp.getChatLieu().getTenCL()));

        cbbHang.setSelectedItem(fillnsx(ctsp.getNsx().getTenNhaSanXuat()));

        cbbMau.setSelectedItem(fillms(ctsp.getMauSac().getTenMS()));

        cbbSzie.setSelectedItem(fillsize(ctsp.getKichThuoc().getTenKT()));

        CBBsp.setSelectedItem(fillsp(ctsp.getSanPham().getTenSP()));
        jLabel3.setText(ctsp.getAnhSp());
    }//GEN-LAST:event_tbCTSPMouseClicked

    private void cbbLoaiSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiSearchItemStateChanged
        // TODO add your handling code here:
        List<DongSP> ListDSPSe = ctspsi.getAllDSP();
        int ma = 0;
        for (int i = 0; i < ListDSPSe.size(); i++) {
            DongSP dsp1 = (DongSP) cbbLoaiSearch.getItemAt(i);
            if (cbbLoaiSearch.getSelectedItem().toString().equalsIgnoreCase(dsp1.getTenDongSP())) {
                ma = dsp1.getMaDongSP();
            }
        }
        List<ChiTietSanPham> listCBBLC = SanPhamService.searchLoaiSP(ma);
        dtm = (DefaultTableModel) tbCTSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietSanPham x : listCBBLC) {
            Object row[] = new Object[]{
                x.getMaCTSP(), x.getSanPham().getTenSP(), x.getNsx().getTenNhaSanXuat(), x.getMauSac().getTenMS(), x.getChatLieu().getTenCL(), x.getKichThuoc().getTenKT(), x.getDongSP().getTenDongSP(), x.getMoTa(), x.getSoLuongTon(), formatter.format(x.getGiaNhap()), formatter.format(x.getGiaBan())
            };
            dtm.addRow(row);
        }
    }//GEN-LAST:event_cbbLoaiSearchItemStateChanged

    private void cbbHangSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHangSearchItemStateChanged
        // TODO add your handling code here:
        int ma = 0;
        for (int i = 0; i < ListNSX.size(); i++) {
            NhaSanXuat nsx = (NhaSanXuat) cbbHangSearch.getItemAt(i);
            if (cbbHangSearch.getSelectedItem().toString().equalsIgnoreCase(nsx.getTenNhaSanXuat())) {
                ma = nsx.getNhaSanXuat();
            }
        }
        List<ChiTietSanPham> listCBBL = SanPhamService.searchLoaiSP(ma);
        dtm = (DefaultTableModel) tbCTSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietSanPham x : listCBBL) {
            Object row[] = new Object[]{
                x.getMaCTSP(), x.getSanPham().getTenSP(), x.getNsx().getTenNhaSanXuat(), x.getMauSac().getTenMS(), x.getChatLieu().getTenCL(), x.getKichThuoc().getTenKT(), x.getDongSP().getTenDongSP(), x.getMoTa(), x.getSoLuongTon(), formatter.format(x.getGiaNhap()), formatter.format(x.getGiaBan())
            };
            dtm.addRow(row);
        }
    }//GEN-LAST:event_cbbHangSearchItemStateChanged

    private void txtSanPhamSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSanPhamSearchCaretUpdate
        // TODO add your handling code here:
        String txt = txtSanPhamSearch.getText().toString();
        List<ChiTietSanPham> listCBBL = SanPhamService.searchTenSP(txt);
        dtm = (DefaultTableModel) tbCTSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietSanPham x : listCBBL) {
            Object row[] = new Object[]{
                x.getMaCTSP(), x.getSanPham().getTenSP(), x.getNsx().getTenNhaSanXuat(), x.getMauSac().getTenMS(), x.getChatLieu().getTenCL(), x.getKichThuoc().getTenKT(), x.getDongSP().getTenDongSP(), x.getMoTa(), x.getSoLuongTon(), formatter.format(x.getGiaNhap()), formatter.format(x.getGiaBan())
            };
            dtm.addRow(row);
        }
    }//GEN-LAST:event_txtSanPhamSearchCaretUpdate

    private void CBBspItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBBspItemStateChanged
        // TODO add your handling code here:
        for (SanPham x : listSP) {
            if (x.getTenSP().equalsIgnoreCase(CBBsp.getSelectedItem().toString())) {
                txtMaSanPham.setText(x.getMaSP());
                break;
            }
        }
    }//GEN-LAST:event_CBBspItemStateChanged

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        SizeView sv = new SizeView();
        sv.show();
        // sv.setVisible(true);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            checkData();
            ChiTietSanPham ctsp = new ChiTietSanPham();
            List<ChatLieu> ListCL1 = clImpl.getAll();
            List<NhaSanXuat> ListNSX2 = nsxImpl.getAll();
            List<MauSac> ListMS2 = msImpl.getAll();
            List<KichThuoc> ListKT2 = sizeImpl.getAll();
            List<DongSP> ListDSP2 = dspImpl.getAll();
            List<SanPham> ListSP = spser.getAll();
            SanPham spp = null;
            MauSac mss = null;
            KichThuoc ktt = null;
            for (int i = 0; i < ListSP.size(); i++) {
                SanPham sp = (SanPham) CBBsp.getItemAt(i);
                if (CBBsp.getSelectedItem().toString().equalsIgnoreCase(sp.getTenSP())) {
                    spp = sp;
                }
            }
            for (int i = 0; i < ListMS2.size(); i++) {
                MauSac ms = (MauSac) cbbMau.getItemAt(i);
                if (cbbMau.getSelectedItem().toString().equalsIgnoreCase(ms.getTenMS())) {
                    mss = ms;
                }
            }
            for (int i = 0; i < ListKT2.size(); i++) {
                KichThuoc kt = (KichThuoc) cbbSzie.getItemAt(i);
                if (cbbSzie.getSelectedItem().toString().equalsIgnoreCase(kt.getTenKT())) {
                    ktt = kt;
                }
            }

            int row = tbCTSP.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
            }
            if (checkNumber(txtSoLuong.getText()) == false) {
                return;
            }
            if (convertedToNumbers(txtBan.getText()) < convertedToNumbers(txtNhap.getText())) {
                Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
                JOptionPane.showMessageDialog(this, "Giá bán nhỏ hơn giá nhập", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            if (SanPhamService.getOneByMaSP(spp, mss, ktt) != null) {
                Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có!", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            } else {
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn update sản phầm ?") == JOptionPane.YES_NO_CANCEL_OPTION) {
                    return;
                }
                ctsp.setGiaBan(convertedToNumbers(txtBan.getText()));
                ctsp.setGiaNhap(convertedToNumbers(txtNhap.getText()));
                ctsp.setSoLuongTon(Integer.valueOf(txtSoLuong.getText()));
                ctsp.setMoTa(txtMota.getText());
                ctsp.setAnhSp(duongdananh);
                for (int i = 0; i < ListSP.size(); i++) {
                    SanPham sp = (SanPham) CBBsp.getItemAt(i);
                    if (CBBsp.getSelectedItem().toString().equalsIgnoreCase(sp.getTenSP())) {
                        ctsp.setSanPham(sp);
                    }
                }
                for (int i = 0; i < ListCL1.size(); i++) {
                    ChatLieu cl = (ChatLieu) cbbChatLieu.getItemAt(i);
                    if (cbbChatLieu.getSelectedItem().toString().equalsIgnoreCase(cl.getTenCL())) {
                        ctsp.setChatLieu(cl);
                    }
                }
                for (int i = 0; i < ListMS2.size(); i++) {
                    MauSac ms = (MauSac) cbbMau.getItemAt(i);
                    if (cbbMau.getSelectedItem().toString().equalsIgnoreCase(ms.getTenMS())) {
                        ctsp.setMauSac(ms);
                    }
                }
                for (int i = 0; i < ListNSX2.size(); i++) {
                    NhaSanXuat nsx = (NhaSanXuat) cbbHang.getItemAt(i);
                    if (cbbHang.getSelectedItem().toString().equalsIgnoreCase(nsx.getTenNhaSanXuat())) {
                        ctsp.setNsx(nsx);
                    }
                }
                for (int i = 0; i < ListDSP2.size(); i++) {
                    DongSP dsp = (DongSP) cbbLoai.getItemAt(i);
                    if (cbbLoai.getSelectedItem().toString().equalsIgnoreCase(dsp.getTenDongSP())) {
                        ctsp.setDongSP(dsp);
                    }
                }
                for (int i = 0; i < ListKT2.size(); i++) {
                    KichThuoc kt = (KichThuoc) cbbSzie.getItemAt(i);
                    if (cbbSzie.getSelectedItem().toString().equalsIgnoreCase(kt.getTenKT())) {
                        ctsp.setKichThuoc(kt);
                    }
                }
                SanPhamService.update(ctsp, txtMCTSP.getText());
                loadData();
                Icon icon = new ImageIcon(getClass().getResource("/iImage/tichxanh.png"));
                JOptionPane.showMessageDialog(this, "Update sản phẩm Thành Công", "Thông báo", JOptionPane.INFORMATION_MESSAGE, icon);
                clear();
            }

        } catch (Exception e) {
            System.out.println(e);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Thất bại", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            checkData();
            ChiTietSanPham ctsp = new ChiTietSanPham();
            List<ChatLieu> ListCL = clImpl.getAll();
            List<NhaSanXuat> ListNSX2 = nsxImpl.getAll();
            List<MauSac> ListMS2 = msImpl.getAll();
            List<KichThuoc> ListKT2 = sizeImpl.getAll();
            List<DongSP> ListDSP2 = dspImpl.getAll();
            List<SanPham> ListSP = spser.getAll();

            SanPham spp = null;
            MauSac mss = null;
            KichThuoc ktt = null;
            for (int i = 0; i < ListSP.size(); i++) {
                SanPham sp = (SanPham) CBBsp.getItemAt(i);
                if (CBBsp.getSelectedItem().toString().equalsIgnoreCase(sp.getTenSP())) {
                    spp = sp;
                }
            }
            for (int i = 0; i < ListMS2.size(); i++) {
                MauSac ms = (MauSac) cbbMau.getItemAt(i);
                if (cbbMau.getSelectedItem().toString().equalsIgnoreCase(ms.getTenMS())) {
                    mss = ms;
                }
            }
            for (int i = 0; i < ListKT2.size(); i++) {
                KichThuoc kt = (KichThuoc) cbbSzie.getItemAt(i);
                if (cbbSzie.getSelectedItem().toString().equalsIgnoreCase(kt.getTenKT())) {
                    ktt = kt;
                }
            }

            if (checkNumber(txtSoLuong.getText()) == false) {
                return;
            }
            if (convertedToNumbers(txtBan.getText()) < convertedToNumbers(txtNhap.getText())) {
                Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
                JOptionPane.showMessageDialog(this, "Giá bán nhỏ hơn giá nhập", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            if (SanPhamService.getOneByMaSP(spp, mss, ktt) != null) {
                Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có!", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
                return;
            } else {

                ctsp.setGiaBan(convertedToNumbers(txtBan.getText()));
                ctsp.setGiaNhap(convertedToNumbers(txtNhap.getText()));
                ctsp.setSoLuongTon(Integer.valueOf(txtSoLuong.getText()));
                ctsp.setMoTa(txtMota.getText());
                ctsp.setAnhSp(jLabel3.getText());

                for (int i = 0; i < ListSP.size(); i++) {
                    SanPham sp = (SanPham) CBBsp.getItemAt(i);
                    if (CBBsp.getSelectedItem().toString().equalsIgnoreCase(sp.getTenSP())) {
                        ctsp.setSanPham(sp);
                    }
                }
                for (int i = 0; i < ListCL.size(); i++) {
                    ChatLieu cl = (ChatLieu) cbbChatLieu.getItemAt(i);
                    if (cbbChatLieu.getSelectedItem().toString().equalsIgnoreCase(cl.getTenCL())) {
                        ctsp.setChatLieu(cl);
                    }
                }
                for (int i = 0; i < ListMS2.size(); i++) {
                    MauSac ms = (MauSac) cbbMau.getItemAt(i);
                    if (cbbMau.getSelectedItem().toString().equalsIgnoreCase(ms.getTenMS())) {
                        ctsp.setMauSac(ms);
                    }
                }
                for (int i = 0; i < ListNSX2.size(); i++) {
                    NhaSanXuat nsx = (NhaSanXuat) cbbHang.getItemAt(i);
                    if (cbbHang.getSelectedItem().toString().equalsIgnoreCase(nsx.getTenNhaSanXuat())) {
                        ctsp.setNsx(nsx);
                    }
                }
                for (int i = 0; i < ListDSP2.size(); i++) {
                    DongSP dsp = (DongSP) cbbLoai.getItemAt(i);
                    if (cbbLoai.getSelectedItem().toString().equalsIgnoreCase(dsp.getTenDongSP())) {
                        ctsp.setDongSP(dsp);
                    }
                }
                for (int i = 0; i < ListKT2.size(); i++) {
                    KichThuoc kt = (KichThuoc) cbbSzie.getItemAt(i);
                    if (cbbSzie.getSelectedItem().toString().equalsIgnoreCase(kt.getTenKT())) {
                        ctsp.setKichThuoc(kt);
                    }
                }
                SanPhamService.add(ctsp);
                loadData();
                Icon icon = new ImageIcon(getClass().getResource("/iImage/tichxanh.png"));
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm Thành Công", "Thông báo", JOptionPane.INFORMATION_MESSAGE, icon);
                clear();
            }

        } catch (Exception e) {
            System.out.println(e);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Thất bại", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int row = tbCTSP.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phầm ?") == JOptionPane.YES_NO_CANCEL_OPTION) {
                    return;
                }
                String maCtsp = (String) tbCTSP.getValueAt(row, 0);
                SanPhamService.delete(maCtsp);
                loadData();
                clear();
            }
        } catch (Exception e) {
            System.out.println(e);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Thất bại", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhActionPerformed
        // TODO add your handling code here
        try {

            JFileChooser open = new JFileChooser("C:\\FPT\\DuAn1\\QLBHang\\src\\Image");
            open.setDialogTitle("Chọn Đường Dẫn Đến Ảnh Linh Kiện");
            open.showOpenDialog(null);
            File ftenAnh = open.getSelectedFile();
            duongdananh = ftenAnh.getAbsolutePath();
            lblAnh.setIcon(ResizeImage(String.valueOf(duongdananh)));
            jLabel3.setText(duongdananh);
            System.out.println(duongdananh);

        } catch (Exception e) {
            System.out.println("Chưa chọn ảnh");
            System.out.println(duongdananh);
        }
    }//GEN-LAST:event_btnAnhActionPerformed

    private void btnAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnhMouseClicked

    private void cbbChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChatLieuActionPerformed
        // TODO add your handling code here:
        //  cbbChatLieu.removeAllItems();
        // loadCbb();
    }//GEN-LAST:event_cbbChatLieuActionPerformed

    private void cbbSzieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSzieActionPerformed
        // TODO add your handling code here:
        // cbbSzie.removeAllItems();
        // loadCbb();
    }//GEN-LAST:event_cbbSzieActionPerformed

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed
        // TODO add your handling code here:
        //cbbHang.removeAllItems();
        //loadCbb();
    }//GEN-LAST:event_cbbHangActionPerformed

    private void cbbMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauActionPerformed
        // TODO add your handling code here:
        // cbbMau.removeAllItems();
        //loadCbb();
    }//GEN-LAST:event_cbbMauActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        txtBan.setText("");
        txtMCTSP.setText("");
        txtMaSanPham.setText("");
        txtMota.setText("");
        txtNhap.setText("");
        txtSoLuong.setText("");
        lblAnh.setIcon(null);
        cbbChatLieu.setSelectedIndex(0);
        CBBsp.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        cbbLoai.setSelectedIndex(0);
        cbbMau.setSelectedIndex(0);
        cbbSzie.setSelectedIndex(0);
        loadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhapKeyReleased
        // TODO add your handling code here:
        try {
            txtNhap.setText(formatter.format(convertedToNumbers(txtNhap.getText())));
        } catch (Exception e) {
            System.out.println(e);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Không điền chữ", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);

        }
    }//GEN-LAST:event_txtNhapKeyReleased

    private void txtBanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBanKeyReleased
        // TODO add your handling code here:
        try {
            txtBan.setText(formatter.format(convertedToNumbers(txtBan.getText())));
        } catch (Exception e) {
            System.out.println(e);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Không điền chữ", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }//GEN-LAST:event_txtBanKeyReleased

    public boolean checkNumber(String number) {
        String kytu = "\\d+";
        if (number.matches(kytu)) {
            if (Integer.valueOf(number) < 0) {
                JOptionPane.showMessageDialog(this, "Dự liệu không hợp lệ");
                return false;
            } else {
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
            return false;
        }
    }

    public boolean checkData() {
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Lượng Tồn không được để trống!");
            return false;
        } else if (txtNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Nhập không được để trống!");
            return false;
        } else if (txtBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Bán không được để trống!");
            return false;

        } else {
            return true;
        }
    }

    public void clear() {
        txtBan.setText("");
        txtMCTSP.setText("");
        txtMaSanPham.setText("");
        txtMota.setText("");
        txtNhap.setText("");
        txtSoLuong.setText("");
        lblAnh.setIcon(null);
        cbbChatLieu.setSelectedIndex(0);
        CBBsp.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        cbbLoai.setSelectedIndex(0);
        cbbMau.setSelectedIndex(0);
        cbbSzie.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBBsp;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAnh;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbbChatLieu;
    private javax.swing.JComboBox cbbHang;
    private javax.swing.JComboBox cbbHangSearch;
    private javax.swing.JComboBox cbbLoai;
    private javax.swing.JComboBox cbbLoaiSearch;
    private javax.swing.JComboBox cbbMau;
    private javax.swing.JComboBox cbbSzie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTable tbCTSP;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextField txtMCTSP;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtNhap;
    private javax.swing.JTextField txtSanPhamSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
