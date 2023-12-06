/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.HoaDonChiTiet;
import entity.KHToanCuc;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ServiceImpl.ThongKeServiceImpl;
import service.ThongKeService;

/**
 *
 * @author thong
 */
public class ThongKeView extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeView
     */
    private ThongKeServiceImpl tksi = new ThongKeService();
    DefaultTableModel model = new DefaultTableModel();
    private List<HoaDonChiTiet> chiTiets = tksi.getListTK();
    Locale locc = Locale.getDefault();
    NumberFormat nff = NumberFormat.getCurrencyInstance(locc);
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public ThongKeView() {
        initComponents();
        loadTB();
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        
////        BigDecimal dt = tksi.getListDoangThu();
        Locale loc = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
        lblDoangThu.setText(nf.format(tksi.getListDoangThu()));
        lblTienLai.setText(nf.format(tksi.getListTienLai()));
        int hd = tksi.getListhoaDon();
        int kh = tksi.getLisKhachHang();
        lblHoaDon.setText(String.valueOf(hd));
        lblKhachHang.setText(String.valueOf(kh));

    }

    private void loadTB() {
        List<HoaDonChiTiet> chiTiets = tksi.getListTK();
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet x : chiTiets) {
            Object row[] = new Object[]{
                x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
            };
            model.addRow(row);
        }
    }
    
    private void loadTBtheoThang() {
        List<HoaDonChiTiet> chiTiets = tksi.getListTKTheoMonth(cboThangSearch.getSelectedItem().toString());
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet x : chiTiets) {
            Object row[] = new Object[]{
                x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
            };
            model.addRow(row);
        }
    }

    private void loadTBDoanhThu() {
        List<HoaDonChiTiet> chiTiets = tksi.searchDoanhThu();
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet x : chiTiets) {
            Object row[] = new Object[]{
                x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
            };
            model.addRow(row);
        }
    }

    private void loadTBHoaDon() {
        List<HoaDonChiTiet> chiTiets = tksi.searchHoaDon();
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet x : chiTiets) {
            Object row[] = new Object[]{
                x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
            };
            model.addRow(row);
        }
    }

    private void loadTBSoLuong() {
        List<HoaDonChiTiet> chiTiets = tksi.searchSoLuong();
        model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet x : chiTiets) {
            Object row[] = new Object[]{
                x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
            };
            model.addRow(row);
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDoangThu = new javax.swing.JLabel();
        btnBaoCao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTensp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rboSoLuong = new javax.swing.JRadioButton();
        rboHoaDon = new javax.swing.JRadioButton();
        rboDoanhThu = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        cboThangSearch = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongKeLichSu = new javax.swing.JTable();
        jDCNgayStart = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDCNgayEnd = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        lblTongTienLichSu = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        timKiemDate = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTienLaiLichSu = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTienLai = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(207, 140));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/hoadon2.png"))); // NOI18N
        jLabel2.setText("Tổng hóa Đơn");
        jLabel2.setPreferredSize(new java.awt.Dimension(207, 49));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setPreferredSize(new java.awt.Dimension(207, 162));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/KhachHang.png"))); // NOI18N
        jLabel3.setText("Tổng khách Hàng");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachHang.setText("jLabel4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(248, 169, 50));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText(" Xuất Thống kê");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 153, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Thongke2.png"))); // NOI18N
        jLabel1.setText("Tổng doanh Thu");

        lblDoangThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoangThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoangThu.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDoangThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoangThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBaoCao.setBackground(new java.awt.Color(248, 169, 50));
        btnBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBaoCao.setText("Gửi Báo Cáo");
        btnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCaoActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtTensp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTenspCaretUpdate(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên sản phẩm:");

        buttonGroup1.add(rboSoLuong);
        rboSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rboSoLuong.setText("Số lượng");
        rboSoLuong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rboSoLuongItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rboHoaDon);
        rboHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rboHoaDon.setText("Hóa đơn");
        rboHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rboHoaDonItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rboDoanhThu);
        rboDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rboDoanhThu.setText("Doanh thu");
        rboDoanhThu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rboDoanhThuItemStateChanged(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(248, 169, 50));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Biểu đồ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboThangSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        cboThangSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangSearchItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tháng:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(rboSoLuong)
                .addGap(89, 89, 89)
                .addComponent(rboHoaDon)
                .addGap(96, 96, 96)
                .addComponent(rboDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboThangSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(rboSoLuong)
                    .addComponent(rboHoaDon)
                    .addComponent(rboDoanhThu)
                    .addComponent(jLabel7)
                    .addComponent(cboThangSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sản phẩm", "Số hóa đơn", "Số lượng", "Giá nhập", "Giá bán", "Giảm giá", "Tổng tiền", "Tiền lãi", "Số lương tồn"
            }
        ));
        jScrollPane1.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh thu", jPanel5);

        tblThongKeLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sản phẩm", "Số hóa đơn", "Số lượng", "Ngày Thanh Toán", "Giá nhập", "Giá bán", "Giảm giá", "Tổng tiền", "Tiền lãi"
            }
        ));
        jScrollPane2.setViewportView(tblThongKeLichSu);

        jLabel5.setText("Ngày bắt đầu:");

        jLabel6.setText("Ngày bắt đầu:");

        lblTongTienLichSu.setBackground(new java.awt.Color(102, 102, 102));
        lblTongTienLichSu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTienLichSu.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tiền lãi:");

        timKiemDate.setBackground(new java.awt.Color(248, 169, 50));
        timKiemDate.setText("Search");
        timKiemDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemDateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tổng tiền:");

        lblTienLaiLichSu.setBackground(new java.awt.Color(102, 102, 102));
        lblTienLaiLichSu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTienLaiLichSu.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jDCNgayStart, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jDCNgayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(timKiemDate))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTongTienLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(219, 219, 219)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTienLaiLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(timKiemDate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCNgayStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDCNgayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTienLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienLaiLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jTabbedPane1.addTab("Lịch sử doanh số", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));
        jPanel8.setPreferredSize(new java.awt.Dimension(207, 162));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Pay.png"))); // NOI18N
        jLabel9.setText("Tổng tiền lãi");

        lblTienLai.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTienLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienLai.setText("jLabel4");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        BieuDo bd = new BieuDo();
        bd.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCaoActionPerformed
        // TODO add your handling code here:

        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.starttls.requied", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.put("mail.smtp.ssl.protocols", "TLSv1.2");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);

        String accountName = "thongbt8@gmail.com";
        String accountPassword = "nygldtheqtlksmrm";
        Session s = Session.getInstance(p, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountName, accountPassword);
            }
        });
        String from = "thongbt8@gmail.com";
        String to = "iamanhne29@gmail.com";
        String subject = "Báo cáo thống kê";
        String body = "Doanh thu:" + lblDoangThu.getText() + "," + "Hóa đơn:" + lblHoaDon.getText() + "," + "Khách hàng:" + lblKhachHang.getText();
        try {
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
            MimeBodyPart contenPart = new MimeBodyPart();
            contenPart.setContent(body, "text/html; charset=utf-8");

            MimeBodyPart filepart = new MimeBodyPart();
            File f = new File("D://DanhSach.xlsx");
            FileDataSource source = new FileDataSource(f);
            filepart.setDataHandler(new DataHandler(source));
            filepart.setFileName(f.getName());
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(contenPart);
            mimeMultipart.addBodyPart(filepart);

            msg.setContent(mimeMultipart);
            Transport.send(msg);
            Icon icon = new ImageIcon(getClass().getResource("/iImage/tichxanh.png"));
            JOptionPane.showMessageDialog(this, "Gửi mail thành công", "OK", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnBaoCaoActionPerformed

    private void rboSoLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rboSoLuongItemStateChanged
        // TODO add your handling code here:
        loadTBSoLuong();
    }//GEN-LAST:event_rboSoLuongItemStateChanged

    private void rboHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rboHoaDonItemStateChanged
        // TODO add your handling code here
        loadTBHoaDon();
    }//GEN-LAST:event_rboHoaDonItemStateChanged

    private void rboDoanhThuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rboDoanhThuItemStateChanged
        // TODO add your handling code here:
        loadTBDoanhThu();
    }//GEN-LAST:event_rboDoanhThuItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet fSheet = workbook.createSheet("danhsach");
            XSSFRow row = null;
            Cell cell = null;
            row = fSheet.createRow(1);//tao dong trong ben tren

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Sản phẩm");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số hóa đơn");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số lượng");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Giá nhập");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Giá bán");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Giảm giá");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Số lương tồn");
            for (int i = 0; i < chiTiets.size(); i++) {
                row = fSheet.createRow(2 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getTenSP());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getHoaDonBan());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getSoLuongBanRa());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getMaCTSPCT().getGiaNhap());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getMaCTSPCT().getGiaBan());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getTienGiam());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getTongTien());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(chiTiets.get(i).getSoLuongTon());
            }
            File f = new File("D://DanhSach.xlsx");
            try {
                FileOutputStream stream = new FileOutputStream(f);
                workbook.write(stream);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Icon icon = new ImageIcon(getClass().getResource("/iImage/tichxanh.png"));
            JOptionPane.showMessageDialog(this, "Xuất thành công", "OK", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void timKiemDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemDateActionPerformed
        // TODO add your handling code here:
        try {
            if (jDCNgayStart.getDate().equals("") || jDCNgayEnd.getDate().equals("")) {
                JOptionPane.showMessageDialog(this, "Chưa chọn ngày!");
            }
            if (jDCNgayStart.getDate().before(jDCNgayEnd.getDate()) == false) {
                Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải hơn ngày kết thúc!", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String dateStart = sdf.format(jDCNgayStart.getDate());
                String dateEnd = sdf.format(jDCNgayEnd.getDate());
                List<HoaDonChiTiet> listHD = tksi.searchNgay(dateStart, dateEnd);
                model = (DefaultTableModel) tblThongKeLichSu.getModel();
                model.setRowCount(0);
                for (HoaDonChiTiet x : listHD) {
                    Object row[] = new Object[]{
                        x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), x.getNgayThanhToan(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai())
                    };
                    model.addRow(row);
                }
                Locale loc = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
                lblTongTienLichSu.setText(nf.format(tksi.doangThuNgay(dateStart, dateEnd)));
                lblTienLaiLichSu.setText(nf.format(tksi.tienLaiNgay(dateStart, dateEnd)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Thất bại", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_timKiemDateActionPerformed

    private void txtTenspCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTenspCaretUpdate
        // TODO add your handling code here:
        if (txtTensp.getText().length() == 0) {
            loadTB();
        } else {
            List<HoaDonChiTiet> chiTiets = tksi.searchTenSP(txtTensp.getText());
            model = (DefaultTableModel) tblThongKe.getModel();
            model.setRowCount(0);
            for (HoaDonChiTiet x : chiTiets) {
                Object row[] = new Object[]{
                    x.getTenSP(), x.getHoaDonBan(), x.getSoLuongBanRa(), formatter.format(x.getMaCTSPCT().getGiaNhap()), formatter.format(x.getMaCTSPCT().getGiaBan()), formatter.format(x.getTienGiam()), formatter.format(x.getTongTien()), formatter.format(x.getTienLai()), x.getSoLuongTon()
                };
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_txtTenspCaretUpdate

    private void cboThangSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangSearchItemStateChanged
        // TODO add your handling code here:
        try {
            if (cboThangSearch.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
                Locale loc = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
                lblDoangThu.setText(nf.format(tksi.getListDoangThu()));
                lblTienLai.setText(nf.format(tksi.getListTienLai()));
                int hd = tksi.getListhoaDon();
                int kh = tksi.getLisKhachHang();
                lblHoaDon.setText(String.valueOf(hd));
                lblKhachHang.setText(String.valueOf(kh));
                loadTB();
            } else {
                Locale loc = new Locale("nv", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
                lblDoangThu.setText(nf.format(tksi.getListDoangThuTheoMonth(cboThangSearch.getSelectedItem().toString())));
                lblTienLai.setText(nf.format(tksi.getListTienLaiTheoMonth(cboThangSearch.getSelectedItem().toString())));
                int hd = tksi.getListhoaDonTheoMonth(cboThangSearch.getSelectedItem().toString());
                int kh = tksi.getLisKhachHangTheoMonth(cboThangSearch.getSelectedItem().toString());
                lblHoaDon.setText(String.valueOf(hd));
                lblKhachHang.setText(String.valueOf(kh));
                loadTBtheoThang();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Icon icon = new ImageIcon(getClass().getResource("/iImage/Exitmini.png"));
            JOptionPane.showMessageDialog(this, "Thất bại", "Lỗi", JOptionPane.INFORMATION_MESSAGE, icon);
        }

    }//GEN-LAST:event_cboThangSearchItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaoCao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboThangSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDCNgayEnd;
    private com.toedter.calendar.JDateChooser jDCNgayStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDoangThu;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblTienLai;
    private javax.swing.JLabel lblTienLaiLichSu;
    private javax.swing.JLabel lblTongTienLichSu;
    private javax.swing.JRadioButton rboDoanhThu;
    private javax.swing.JRadioButton rboHoaDon;
    private javax.swing.JRadioButton rboSoLuong;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTable tblThongKeLichSu;
    private javax.swing.JButton timKiemDate;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
