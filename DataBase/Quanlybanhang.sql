USE
[master]
CREATE 
DATABASE [QUANLYQUANAO_ANGEL]
GO

USE [QUANLYQUANAO_ANGEL]
GO

CREATE TABLE Account(
	Id   INT
	IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(50),
	Pass NVARCHAR(50) DEFAULT NULL,
) 
go
CREATE TABLE ChucVu(
	Ma INT
	IDENTITY(1,1) PRIMARY KEY,
	Ten NVARCHAR(50) DEFAULT NULL
) 
go

-- NSX
CREATE TABLE NSX(
Ma INT
	IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO

-- MauSac
CREATE TABLE MauSac(
Ma INT
	IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO
-- DongSP
CREATE TABLE DongSP(
Ma INT
	IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO

-- Size
CREATE TABLE KichThuoc(
Ma INT
	IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO

-- ChatLieu
CREATE TABLE ChatLieu(
Ma INT
	IDENTITY(1,1) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO

-- SanPham
CREATE TABLE SanPham(
Ma varchar(6) PRIMARY KEY,
Ten NVARCHAR(30)
)
GO

-- ChiTietSP
CREATE TABLE ChiTietSP(
MaCTSP varchar(6)  PRIMARY KEY,
MaSP varchar(6) 
REFERENCES SanPham (Ma),
MaNsx INT
REFERENCES NSX (Ma),
MaMauSac INT
REFERENCES MauSac (Ma),
MaDongSP INT
REFERENCES DongSP (Ma),
MaChatLieu INT
REFERENCES ChatLieu (Ma),
MaSize INT
REFERENCES KichThuoc (Ma),
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuongTon INT,
GiaNhap DECIMAL(20,2) DEFAULT 0,
GiaBan DECIMAL(20,2) DEFAULT 0,
Anh VARCHAR(50)
)
GO

--KhachHang
CREATE TABLE KhachHang
(
    MaKhachHang   varchar(6) PRIMARY KEY,
    TenKhachHang   NVARCHAR(50),
    DiaChi         NVARCHAR( MAX),
    SoDienThoai    VARCHAR(15),
    Email          VARCHAR(50),
	 GioiTinh       BIT,
    TrangThai      INT
)
    GO

--NhanVien
CREATE TABLE NhanVien(
MaNhanVien   varchar(6) PRIMARY KEY,
TenNhanVien   NVARCHAR(50),
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
Email VARCHAR(50) NULL,
MatKhau INT
 REFERENCES Account (Id),
MaCV int
REFERENCES ChucVu (Ma),
TrangThai INT DEFAULT 0
)

--HoaDon
CREATE TABLE HoaDon(
MaHoaDon    BIGINT
        IDENTITY(1,1) PRIMARY KEY,
IdKH varchar(6)
	REFERENCES KhachHang (MaKhachHang),
IdNV  varchar(6)
	REFERENCES NhanVien (MaNhanVien),
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
TinhTrang INT DEFAULT 0,
GhiChu NVARCHAR(max) DEFAULT NULL,
Tongtien DECIMAL(20,2) null,
ThanhToan DECIMAL(20,2) null,
TienThua DECIMAL(20,2),
PhuongThucThanhToan  NVARCHAR(30)
)
GO

-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
MaHDCT    BIGINT
	 IDENTITY(1,1) PRIMARY KEY,
MaHoaDon BIGINT
REFERENCES HoaDon(MaHoaDon),
MaChiTietSP varchar(6)
REFERENCES ChiTietSP(MaCTSP),
SoLuong INT,
DonGia DECIMAL(20,3) DEFAULT 0,
TinhTrang int Default 0 null
)

-- Khuyenmai
CREATE TABLE PhieuGiamGia
(
    MaPhieu         VARCHAR(10) PRIMARY KEY,
    TenPhieu        NVARCHAR(20),
    NgayBatDau      DATE,
    NgayKetThuc     DATE,
    GiaTriGiamToiDa DECIMAL(20,2),
    HinhThucGiam    BIT, -- % hay gia tien
	MoTa			 NVARCHAR(Max),
    TrangThai       INT,

	MaSanPham       varchar(6)
        REFERENCES  ChiTietSP(MaCTSP)
)
    GO

select GiaTriGiamToiDa from PhieuGiamGia where MaSanPham = 'SPCT01'

select * from PhieuGiamGia
select * from SanPham
select * from KhachHang
select * from NhanVien
select * from ChucVu
select * from Account
Select * from HoaDon
select * from KichThuoc
select * from DongSP
select * from MauSac
select * from NSX
select * from ChatLieu
select * from ChiTietSP
select * from PhieuGiamGia
select  MaCTSP, SanPham.Ten,NSX.Ten,DongSP.Ten,ChatLieu.Ten,KichThuoc.Ten,MauSac.Ten,SoLuongTon,GiaBan,GiaTriGiamToiDa
                from ChiTietSP join SanPham on SanPham.Ma = ChiTietSP.MaSP
               				join NSX ON ChiTietSP.MaNsx = NSX.Ma
                			join DongSP ON ChiTietSP.MaDongSP = DongSP.Ma
                			join ChatLieu ON ChiTietSP.MaChatLieu = ChatLieu.Ma
                			join KichThuoc ON ChiTietSP.MaSize = KichThuoc.Ma
                			join MauSac ON ChiTietSP.MaMauSac = MauSac.Ma
							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ChiTietSP.MaCTSP

select  NhanVien.TenNhanVien,NhanVien.MaNhanVien,ChucVu.Ten 
from Account join NhanVien on Account.Id = NhanVien.MatKhau
			join ChucVu on NhanVien.MaCV = ChucVu.Ma
where UserName = 'thongbh' AND Pass = '123'


Select MaHoaDon, NgayTao, NhanVien.TenNhanVien, KhachHang.TenKhachHang 
from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien
			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang
where TinhTrang = 0
Insert into HoaDon(MaHoaDon,NgayTao,IdNV,IdKH) 
values('')

select Ten from DongSP
Select * from HoaDon
delete HoaDon where MaHoaDon = 4
select COUNT(DISTINCT MaHoaDon) from HoaDon
where TinhTrang = 1
select COUNT(DISTINCT  IdKH) from HoaDon
where TinhTrang = 1
update ChiTietSP set SoLuongTon = 100 where MaCTSP = 'SPCT01'

select  MaCTSP, SanPham.Ten as 'TênSP',NSX.Ten as 'TênNSX',DongSP.Ten as 'TênDongSP',ChatLieu.Ten as 'TênChatLieu',KichThuoc.Ten as 'TênKichThuoc',MauSac.Ten as 'TênMS',SoLuongTon,GiaBan
from ChiTietSP join SanPham on SanPham.Ma = ChiTietSP.MaSP
				join NSX ON ChiTietSP.MaNsx = NSX.Ma
				join DongSP ON ChiTietSP.MaDongSP = DongSP.Ma
				join ChatLieu ON ChiTietSP.MaChatLieu = ChatLieu.Ma
				join KichThuoc ON ChiTietSP.MaSize = KichThuoc.Ma
				join MauSac ON ChiTietSP.MaMauSac = MauSac.Ma
where DongSP.Ten like N'Quần%'

select ctsp.MaCTSP, sp.Ten, hdct.DonGia,hdct.SoLuong 
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
							join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
where hd.MaHoaDon=6 and TrangThai = 0

select Sum(DonGia * SoLuong) 
from HoaDonChiTiet join HoaDon on HoaDonChiTiet.MaHoaDon = HoaDon.MaHoaDon
where HoaDonChiTiet.MaHoaDon = 4 

select Sum(GiaTriGiamToiDa * SoLuong)
from HoaDonChiTiet join HoaDon on HoaDonChiTiet.MaHoaDon = HoaDon.MaHoaDon
					join ChiTietSP on HoaDonChiTiet.MaChiTietSP = ChiTietSP.MaCTSP
					join PhieuGiamGia on ChiTietSP.MaCTSP = PhieuGiamGia.MaSanPham
where HoaDonChiTiet.MaHoaDon = 130  

select * from HoaDonChiTiet


select * from HoaDon
select * from KhachHang
select * from NhanVien
Update HoaDon set IdKH = 'KH0002' , IdNV = 'NV0001' , NgayTao = '2020-10-10' , NgayThanhToan = '2020-10-10' , TinhTrang = 1 , TongTien = 300 , TienThua = 0, PhuongThucThanhToan =N'Tiền mặt'   where MaHoaDon = 8
update HoaDonChiTiet set TrangThai = 1 where MaHoaDon = 8

Delete HoaDon where MaHoaDon = 14


select * from ChiTietSP
select hdct.MaHoaDon,sp.Ten, ctsp.GiaNhap,ctsp.GiaBan,hdct.SoLuong,hdct.DonGia
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
							join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
where TrangThai = 1

update ChiTietSP set SoLuongTon = (SoLuongTon -2) where MaCTSP = 'SPCT01'

select * from HoaDonChiTiet


select * from HoaDon
	select COUNT(IdKH)
	from  HoaDon as hd join KhachHang on hd.IdKH = KhachHang.MaKhachHang
						join HoaDonChiTiet as hdct on hd.MaHoaDon = hdct.MaHDCT
	where hd.TinhTrang=1

	select COUNT(hd.MaHoaDon)
	from  HoaDon as hd
	where hd.TinhTrang=1

select COUNT(hdct.MaHoaDon)as'hoaDon'
               from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
                where hdct.TrangThai=1
select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(SoLuong*GiaBan) as'thanhtien'
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
				
where hdct.TrangThai=1
Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap
order by hoadonbanra desc

select *
from HoaDonChiTiet
where MaHoaDon =35

select hd.MaHoaDon,hdct.SoLuong,ctsp.MaSP,hd.TongTien
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
where hdct.TrangThai=1
select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(SoLuong*GiaBan) as'thanhtien'
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
where hdct.TrangThai=1
Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap
order by slbanra desc

select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(SoLuong*GiaBan) as'thanhtien'
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
where hd.TinhTrang=1
Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap

select COUNT(MaHoaDon)as'hoaDon' from HoaDon
                where TinhTrang = 1

select COUNT(IdKH)as'KhachHang', IdKH
from HoaDon
                where TinhTrang = 1
group by IdKH

Select * from HoaDon
select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ThanhToan) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
where hdct.TrangThai=1
Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa

select sum(ThanhToan) as 'ThanhToan'
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
where hdct.TrangThai=1 and hd.NgayThanhToan between '2023-07-25' and '2023-07-26'

select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ThanhToan) as'thanhtien',hd.NgayThanhToan,PhieuGiamGia.GiaTriGiamToiDa
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
							join SanPham as sp on ctsp.MaSP = sp.Ma
							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
where hdct.TrangThai=1 and hd.NgayThanhToan between '2023-07-25' and '2023-07-26'
Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,hd.NgayThanhToan,PhieuGiamGia.GiaTriGiamToiDa



select SUM(TongTien) from HoaDon

select EOMONTH(NgayThanhToan), SUM(TongTien)
from HoaDon
WHERE TinhTrang = 1
Group by  EOMONTH(NgayThanhToan)
select MONTH(NgayThanhToan), SUM(TongTien)
from HoaDon
WHERE TinhTrang = 1
Group by  MONTH(NgayThanhToan)

select MONTH(NgayThanhToan), COUNT(*)
from HoaDon
Group by  MONTH(NgayThanhToan)

select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',NgayTao,NgayThanhToan,TongTien,ThanhToan,TienThua,PhuongThucThanhToan,TinhTrang  
from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien
			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang

select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',NgayTao,NgayThanhToan,TongTien,ThanhToan,TienThua,PhuongThucThanhToan,TinhTrang  
from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien
			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang
where  NgayTao between '2023-07-18' and '2023-07-19'

select * from HoaDon
select *
from HoaDonChiTiet
select * from ChiTietSP
select MaHDCT,hdct.MaHoaDon,MaChiTietSP,sp.Ten,SoLuong,DonGia,sum(SoLuong*DonGia) as thanhtien
from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
						join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
						join SanPham as sp on ctsp.MaSP = sp.Ma
where hdct.MaHoaDon = 8
group by MaHDCT,hdct.MaHoaDon,MaChiTietSP,SoLuong,DonGia,sp.Ten

Update ChiTietSP  set SoLuongTon = SoLuongTon +(select SoLuong from HoaDonChiTiet where MaHDCT = 33) where MaCTSP = 'SPCT01' 
Update ChiTietSP  set SoLuongTon = SoLuongTon +(select SoLuong from HoaDonChiTiet where MaHoaDon = 33) where MaCTSP = 'SPCT01' 

SELECT MaCTSP as mactsp,sp.Ten as tenSP,NSX.Ten as tenNSX,ms.Ten as tenMS ,dsp.Ten as tenDSP, cl.Ten as tenCL,kt.Ten as tenKT,MoTa as mota,SoLuongTon,GiaNhap ,GiaBan,Anh
from ChiTietSP as ctsp join SanPham as sp on ctsp.MaSP = sp.Ma
                		join ChatLieu as cl on ctsp.MaChatLieu = cl.Ma
                		join MauSac as ms on ctsp.MaMauSac = ms.Ma
               			join DongSP as dsp on ctsp.MaDongSP = dsp.Ma
              			join NSX as nsx on ctsp.MaNsx = nsx.Ma
                		join KichThuoc as kt on ctsp.MaSize = kt.Ma
where MaCTSP like '' or sp.Ten like 'Áo Odin'

select * from PhieuGiamGia

Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten
from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP 
						join SanPham as sp on ctsp.MaSP =  sp.Ma

Select MaCTSP , Ten
from ChiTietSP join SanPham on ChiTietSP.MaSP = SanPham.Ma

Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten
from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP 
						join SanPham as sp on ctsp.MaSP =  sp.Ma
where  pgg.MaPhieu = ? or  pgg.TenPhieu= ?

Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten
from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP 
						join SanPham as sp on ctsp.MaSP =  sp.Ma
where  HinhThucGiam = ? 

Select pgg.MaPhieu, pgg.TenPhieu, NgayBatDau , NgayKetThuc , GiaTriGiamToiDa, HinhThucGiam, pgg.MoTa , TrangThai , MaSanPham , sp.Ten
from PhieuGiamGia as pgg join ChiTietSP as ctsp on pgg.MaSanPham = ctsp.MaCTSP 
						join SanPham as sp on ctsp.MaSP =  sp.Ma
where  NgayBatDau  between '2023-07-18' and '2023-07-19'
update ChiTietSP set SoLuongTon = (SoLuongTon - 1) where MaCTSP = 'SPCT02' and SoLuongTon > 0

select sp.Ten as'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra',SUM(SoLuong) as 'slbanra',ctsp.GiaBan,ctsp.GiaNhap,SUM(ctsp.GiaBan * SoLuong) as'thanhtien',PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon,SUM((ctsp.GiaBan * SoLuong)- (ctsp.GiaNhap*SoLuong) - (GiaTriGiamToiDa*SoLuong))
                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
                							join SanPham as sp on ctsp.MaSP = sp.Ma
                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
                where hdct.TrangThai=1
               Group by  sp.Ten,ctsp.GiaBan,ctsp.GiaNhap,PhieuGiamGia.GiaTriGiamToiDa,ctsp.SoLuongTon

select SUM( (GiaTriGiamToiDa*SoLuong) + (ctsp.GiaNhap*SoLuong))
                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
                where hd.TinhTrang=1
              
select COUNT(DISTINCT MaHoaDon) from HoaDon
where TinhTrang = 1 and MONTH(NgayTao) = '11'
select COUNT(DISTINCT  IdKH) from HoaDon
where TinhTrang = 1 and MONTH(NgayTao) = '11'
select SUM(ctsp.GiaBan * SoLuong)
                from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon
                           					join ChiTietSP as ctsp on hdct.MaChiTietSP = ctsp.MaCTSP
                							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
                where hd.TinhTrang=1 and MONTH(NgayTao) = '11'
select * from PhieuGiamGia
update ChiTietSP set SoLuongTon = (SoLuongTon - 300) where MaCTSP = 'SP00001' and 300< SoLuongTon

Select GiaBan,GiaNhap
from ChiTietSP 
where MaCTSP = 'SP00001' and 300< SoLuongTon

SELECT MaCTSP,sp.Ten,ms.Ten ,kt.Ten
                               from ChiTietSP as ctsp join SanPham as sp on ctsp.MaSP = sp.Ma
                              					join MauSac as ms on ctsp.MaMauSac = ms.Ma
                             							join KichThuoc as kt on ctsp.MaSize = kt.Ma
                             							join PhieuGiamGia on PhieuGiamGia.MaSanPham = ctsp.MaCTSP
                where MaPhieu = 'PGG001'