/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.ChucVu;
import entity.DangNhap;
import java.util.ArrayList;
import java.util.List;
import entity.NhanVien;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.DangNhapRepository;
import repository.NhanVienRepository;
import service.NhanVienService;
import service.ServiceImpl.NhanVienServiceImpl;

/**
 *
 * @author thong
 */
public class NhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVien
     */
    private NhanVienServiceImpl nvs = new NhanVienService();
    DefaultTableModel model = new DefaultTableModel();
    DefaultComboBoxModel<ChucVu> dfCV;
    List<NhanVien> listNV = nvs.getAllNhanVien();
    List<ChucVu> ListCV = nvs.getALLCV();

    public NhanVienView() {
        initComponents();
        List<NhanVien> nv = nvs.phantrang(0);
        dfCV = new DefaultComboBoxModel(ListCV.toArray());
        cbbChucVu.setModel(dfCV);

        loadTB();
    }

    public void loadTB() {
        List<NhanVien> lstNV = nvs.phantrang(TienLui);
        model = (DefaultTableModel) tbnNhanVien.getModel();
        model.setRowCount(0);
        for (NhanVien x : lstNV) {
            Object roww[] = new Object[]{
                x.getMaNV(), x.getTenNV(), x.getNgaySinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getChucVu().getTenCV(), x.getGioiTinh(), x.getDangNhap().getUserName(), x.getDangNhap().getPassWord(), x.getTrangThai() == 0 ? "Đi làm" : "Không đi làm"
            };
            model.addRow(roww);
        }

    }

    public ChucVu fillCV(String cv) {
        for (int i = 0; i < ListCV.size(); i++) {
            ChucVu cvu = (ChucVu) cbbChucVu.getItemAt(i);
            if (cv.equalsIgnoreCase(cvu.getTenCV())) {
                return cvu;
            }
        }
        return null;
    }

    public void clearfrom() {
        this.txtDiaChi.setText("");
        this.txtMaNV.setText("");
        this.txtTen.setText("");
        this.txtEmail.setText("");
        this.txtMK.setText("");
        this.txtNgaySinh.setDate(null);
        this.txtSDT.setText("");
        this.r_Nam.setSelected(true);

        this.cbbChucVu.setSelectedIndex(0);
        this.r_DangLam.setSelected(true);

    }

    public boolean checksdt() {

        if (txtSDT.getText().matches("[0,+84][\\d]{9}")) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "vui lòng nhập đúng số điện thoại");

        return false;
    }
//    public  boolean checkNgay(){
//        SimpleDateFormat s = new SimpleDateFormat();
//        s.applyPattern("dd-MM-yyyy");
//        try {
//            Date ngay = s.parse(txtNgaySinh.getText().toString());
//              return true;
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(null, "Sai đinh dang ngay sinh");
//              return false;
//        }
//    }

    public boolean checkten() {
        String paramater = "^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*";

        if (txtTen.getText().matches(paramater)) {
            return true;
        }
        if (txtTen.getText().length() > 2) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Tên Sai Định Dạng ( Phải Là chữ và lớn hơn 2 kí Tự)");

        return false;
    }

    public boolean checkmail() {
        if (!isValidEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "mail không đúng định dạng");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra email không được viết hoa
        if (email.matches(".*[A-Z].*")) {
            return false;
        }

        String lowercaseEmail = email.toLowerCase();
        String regex = "^[a-z0-9._%+-]+(\\.[a-z0-9._%+-]+)*@[a-z0-9.-]+\\.[a-z]{2,}$";
        boolean hasConsecutiveDots = lowercaseEmail.contains("..");
        return lowercaseEmail.matches(regex) && !hasConsecutiveDots;
    }

    /**
     * x. This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        r_DangLam = new javax.swing.JRadioButton();
        r_nghiViec = new javax.swing.JRadioButton();
        r_Nam = new javax.swing.JRadioButton();
        r_Nu = new javax.swing.JRadioButton();
        cbbChucVu = new javax.swing.JComboBox();
        txtMaNV = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtMK = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        bttUpdate = new javax.swing.JButton();
        bttAdd = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnChucVu = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lui = new javax.swing.JButton();
        tien = new javax.swing.JButton();
        soTrang = new javax.swing.JLabel();

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin nhân viên");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên nhân viên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Số điện thoại:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Chức vụ:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng thái:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mật khẩu:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Giới tính:");

        buttonGroup1.add(r_DangLam);
        r_DangLam.setText("Đang làm việc");
        r_DangLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_DangLamActionPerformed(evt);
            }
        });

        buttonGroup1.add(r_nghiViec);
        r_nghiViec.setSelected(true);
        r_nghiViec.setText("Nghỉ việc");
        r_nghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_nghiViecActionPerformed(evt);
            }
        });

        buttonGroup2.add(r_Nam);
        r_Nam.setSelected(true);
        r_Nam.setText("Nam");

        buttonGroup2.add(r_Nu);
        r_Nu.setText("Nữ");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton4.setBackground(new java.awt.Color(255, 204, 51));
        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        bttUpdate.setBackground(new java.awt.Color(255, 204, 51));
        bttUpdate.setText("Sửa");
        bttUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttUpdateActionPerformed(evt);
            }
        });

        bttAdd.setBackground(new java.awt.Color(255, 204, 51));
        bttAdd.setText("Thêm");
        bttAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttAddActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 51));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnChucVu.setBackground(new java.awt.Color(102, 255, 255));
        btnChucVu.setText("Thêm Chức Vụ");
        btnChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChucVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(bttAdd)
                .addGap(18, 18, 18)
                .addComponent(bttUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(btnChucVu)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên đăng nhập :");

        txtTenDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMK, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(cbbChucVu, 0, 219, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(r_DangLam)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(r_Nam)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(r_Nu)
                                    .addComponent(r_nghiViec))))))
                .addGap(187, 187, 187)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(r_DangLam)
                            .addComponent(r_nghiViec))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(r_Nam)
                                .addComponent(r_Nu))
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        tbnNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "SĐT", "Email", "Chức vụ", "Giới tính", "Tên đăng nhập", "Mật khẩu", "Trạng thái"
            }
        ));
        tbnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnNhanVien);

        jLabel1.setText("Tìm kiếm:");

        lui.setText("<");
        lui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luiActionPerformed(evt);
            }
        });

        tien.setText(">");
        tien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tienActionPerformed(evt);
            }
        });

        soTrang.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(587, 587, 587)
                        .addComponent(lui)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soTrang)
                    .addComponent(tien)
                    .addComponent(lui))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void r_DangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_DangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_DangLamActionPerformed

    private void r_nghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_nghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_nghiViecActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.clearfrom();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bttUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttUpdateActionPerformed
        try {
            int row = tbnNhanVien.getSelectedRow();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần sửa!");
                return;
            }

            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật thông tin ?") == JOptionPane.NO_OPTION) {
                return;
            }
            NhanVien viewModel = new NhanVien();
            viewModel.setMaNV(txtMaNV.getText());
            viewModel.setTenNV(txtTen.getText());
            if (r_Nam.isSelected()) {
                viewModel.setGioiTinh("Nam");
            } else {
                viewModel.setGioiTinh("Nữ");
            }

            viewModel.setNgaySinh(sdf.format(txtNgaySinh.getDate()));
            viewModel.setDiaChi(txtDiaChi.getText());
            viewModel.setSdt(txtSDT.getText());
            viewModel.setEmail(txtEmail.getText());
            DangNhap dn = new DangNhap(txtTenDN.getText(), txtMK.getText());

            viewModel.setDangNhap(new DangNhapRepository().getOneByMaNhanVien(txtMaNV.getText()));
            new DangNhapRepository().update(dn, new DangNhapRepository().getOneByMaNhanVien(txtMaNV.getText()).getId());
            for (int i = 0; i < ListCV.size(); i++) {
                ChucVu cvu = (ChucVu) cbbChucVu.getItemAt(i);
                if (cbbChucVu.getSelectedItem().toString().equalsIgnoreCase(cvu.getTenCV())) {
                    viewModel.setChucVu(cvu);
                }
            }

            if (r_DangLam.isSelected()) {
                viewModel.setTrangThai(0);
            } else {
                viewModel.setTrangThai(1);
            }
            new NhanVienRepository().update(viewModel, txtMaNV.getText().toString());
            listNV = nvs.getAllNhanVien();
            loadTB();
            JOptionPane.showMessageDialog(this, "Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "loi");
        }


    }//GEN-LAST:event_bttUpdateActionPerformed

    private void bttAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttAddActionPerformed
        // TODO add your handling code here:
        try {
            NhanVien viewModel = new NhanVien();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            viewModel.setTenNV(txtTen.getText());
            if (r_Nam.isSelected()) {
                viewModel.setGioiTinh("Nam");
            } else {
                viewModel.setGioiTinh("Nữ");
            }

            if (new DangNhapRepository().getOneByUserName(txtTenDN.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã có trên hệ thống");
                txtTenDN.requestFocus();
                return;
            }

            viewModel.setNgaySinh(sdf.format(txtNgaySinh.getDate()));
            viewModel.setDiaChi(txtDiaChi.getText());
            viewModel.setSdt(txtSDT.getText());
            viewModel.setEmail(txtEmail.getText());
            DangNhap dn = new DangNhap(txtTenDN.getText(), txtMK.getText());

            for (int i = 0; i < ListCV.size(); i++) {
                ChucVu cvu = (ChucVu) cbbChucVu.getItemAt(i);
                if (cbbChucVu.getSelectedItem().toString().equalsIgnoreCase(cvu.getTenCV())) {
                    viewModel.setChucVu(cvu);
                }
            }

            if (r_DangLam.isSelected()) {
                viewModel.setTrangThai(0);
            } else {
                viewModel.setTrangThai(1);
            }

            if (txtTen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "không được trống Tên");
                txtTen.requestFocus();
                return;
            }

//            if (!checkNgay()) {
//                txtNgaySinh.requestFocus();
//                return;
//            }
            if (!checksdt()) {
                txtSDT.requestFocus();
                return;
            }

            if (!checkten()) {
                txtTen.requestFocus();
                return;
            }
            if (!checkmail() == true) {
                txtEmail.requestFocus();
                return;
            }
            if (txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Email");
                txtEmail.requestFocus();
                return;
            }

            if (txtMK.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vùi lòng nhập mật khẩu");
                txtMK.requestFocus();
                return;
            }

            if (txtDiaChi.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "không được trống địa chỉ");

                txtDiaChi.requestFocus();

                return;
            }

            if (txtSDT.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vùi lòng nhập SDT");
                txtSDT.requestFocus();
                return;
            } else {

                new DangNhapRepository().insert(dn);
                viewModel.setDangNhap(new DangNhapRepository().getOneByMaPass(txtMK.getText().toString()));
                nvs.them(viewModel);
                listNV = nvs.getAllNhanVien();
                loadTB();
                JOptionPane.showMessageDialog(this, "Thành Công");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "loi");
        }


    }//GEN-LAST:event_bttAddActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Integer row = tbnNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chon dong can xoa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá  khỏi CSDL?") == JOptionPane.NO_OPTION) {
            return;
        }
        String ma = (String) model.getValueAt(row, 0);

        try {
            new NhanVienRepository().delete(ma);
            new DangNhapRepository().deleteByMaNhanVien((String) model.getValueAt(row, 8));

            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTB();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKActionPerformed

    private void tbnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnNhanVienMouseClicked
        Integer row = tbnNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi");
            return;
        }
        String ma = (String) model.getValueAt(row, 0);

        NhanVien nv = new NhanVienRepository().getOne(ma);

        loadDataOnTable(nv);
    }//GEN-LAST:event_tbnNhanVienMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        //tim kiem theo ma va ten 
        String txtSearch = this.txtTimKiem.getText().toString();
        List<NhanVien> lstNV = new NhanVienRepository().timKiemTheoTenHoacMa(txtSearch);
        model = (DefaultTableModel) tbnNhanVien.getModel();
        model.setRowCount(0);
        for (NhanVien x : lstNV) {
            Object roww[] = new Object[]{
                x.getMaNV(), x.getTenNV(), x.getNgaySinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getChucVu().getTenCV(), x.getGioiTinh(), x.getDangNhap().getPassWord(), x.getTrangThai() == 0 ? "Đi làm" : "Không đi làm"
            };
            model.addRow(roww);
        }


    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTenDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDNActionPerformed

    private void btnChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChucVuActionPerformed
        // TODO add your handling code here:
        ChucVuView cv = new ChucVuView();
        cv.show();
    }//GEN-LAST:event_btnChucVuActionPerformed
    private int TienLui = 0;
    private int so = 1;
    private void tienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tienActionPerformed
        // TODO add your handling code here:
        TienLui += 5;

        System.out.println(TienLui);
        if (TienLui > 0) {
            so += 1;
            int b = TienLui;

            soTrang.setText(String.valueOf(so));
            List<NhanVien> kh = nvs.phantrang(b);
            loadTB();
        }
    }//GEN-LAST:event_tienActionPerformed

    private void luiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luiActionPerformed
        // TODO add your handling code here:
        System.out.println(TienLui);

        if (TienLui > 0) {
            so -= 1;
            TienLui -= 5;
            int b = TienLui;
            soTrang.setText(String.valueOf(so));
            List<NhanVien> kh = nvs.phantrang(b);
            loadTB();

        } else {
            int b = 0;
            List<entity.NhanVien> kh = nvs.phantrang(b);

        }

    }//GEN-LAST:event_luiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChucVu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton bttAdd;
    private javax.swing.JButton bttUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbbChucVu;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lui;
    private javax.swing.JRadioButton r_DangLam;
    private javax.swing.JRadioButton r_Nam;
    private javax.swing.JRadioButton r_Nu;
    private javax.swing.JRadioButton r_nghiViec;
    private javax.swing.JLabel soTrang;
    private javax.swing.JTable tbnNhanVien;
    private javax.swing.JButton tien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
  private Date forma(String ngay) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Ngày chưa được đưa vào input!");
        }
        return date1;
    }

    private void loadDataOnTable(NhanVien nv) {
        txtTen.setText(nv.getTenNV());
        txtDiaChi.setText(nv.getDiaChi());
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        txtMK.setText(nv.getDangNhap().getPassWord());
        txtTenDN.setText(nv.getDangNhap().getUserName());
        txtNgaySinh.setDate(forma(nv.getNgaySinh()));
        txtMaNV.setText(nv.getMaNV());

        cbbChucVu.getModel().setSelectedItem(nv.getChucVu().getTenCV().toString());

        if (nv.getGioiTinh().equalsIgnoreCase("Nam")) {
            r_Nam.setSelected(true);
        } else {
            r_Nu.setSelected(true);
        }

        if (nv.getTrangThai() == 1) {
            r_DangLam.setSelected(true);
        } else {
            r_nghiViec.setSelected(true);
        }
    }
}
