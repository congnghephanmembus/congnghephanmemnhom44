USE QuanLyVeXeBuytNoiDo;
GO

SET NOCOUNT ON;
GO

/* ======================================================
   IDEMPOTENT SAMPLE DATA - MATCHES CURRENT REAL SCHEMA
   Schema differences handled:
   - ChuyenXe has NgayChay (no MaLich)
   - Ve has no LyDoHuy
   - No NhatKyHeThong table
   ====================================================== */

-- 1) TUYENXE
IF NOT EXISTS (SELECT 1 FROM dbo.TuyenXe WHERE MaTuyen='T001')
INSERT INTO dbo.TuyenXe (MaTuyen, TenTuyen, DiemDau, DiemCuoi, CuLy, TrangThai)
VALUES ('T001', N'Tuyến Bến xe Trung tâm - Đại học Quốc gia', N'Bến xe Trung tâm', N'Đại học Quốc gia', 12.50, N'Hoạt động');

IF NOT EXISTS (SELECT 1 FROM dbo.TuyenXe WHERE MaTuyen='T002')
INSERT INTO dbo.TuyenXe (MaTuyen, TenTuyen, DiemDau, DiemCuoi, CuLy, TrangThai)
VALUES ('T002', N'Tuyến Chợ Lớn - Khu Công nghệ cao', N'Chợ Lớn', N'Khu Công nghệ cao', 22.30, N'Hoạt động');

IF NOT EXISTS (SELECT 1 FROM dbo.TuyenXe WHERE MaTuyen='T003')
INSERT INTO dbo.TuyenXe (MaTuyen, TenTuyen, DiemDau, DiemCuoi, CuLy, TrangThai)
VALUES ('T003', N'Tuyến Sân bay - Bến Thành', N'Sân bay', N'Bến Thành', 9.80, N'Hoạt động');
GO

-- 2) TRAMDUNG
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR01')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR01', N'Bến xe Trung tâm', N'1 Đường A');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR02')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR02', N'Ngã tư Thủ Đức', N'2 Đường B');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR03')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR03', N'Đại học Quốc gia', N'3 Đường C');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR04')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR04', N'Chợ Lớn', N'4 Đường D');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR05')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR05', N'Ngã tư Bình Thái', N'5 Đường E');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR06')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR06', N'Khu Công nghệ cao', N'6 Đường F');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR07')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR07', N'Sân bay', N'7 Đường G');
IF NOT EXISTS (SELECT 1 FROM dbo.TramDung WHERE MaTram='TR08')
INSERT INTO dbo.TramDung (MaTram, TenTram, DiaChi) VALUES ('TR08', N'Bến Thành', N'8 Đường H');
GO

-- 3) CHITIETTUYEN
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T001' AND MaTram='TR01')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T001','TR01',1);
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T001' AND MaTram='TR02')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T001','TR02',2);
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T001' AND MaTram='TR03')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T001','TR03',3);

IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T002' AND MaTram='TR04')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T002','TR04',1);
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T002' AND MaTram='TR05')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T002','TR05',2);
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T002' AND MaTram='TR06')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T002','TR06',3);

IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T003' AND MaTram='TR07')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T003','TR07',1);
IF NOT EXISTS (SELECT 1 FROM dbo.ChiTietTuyen WHERE MaTuyen='T003' AND MaTram='TR08')
INSERT INTO dbo.ChiTietTuyen (MaTuyen, MaTram, ThuTuTram) VALUES ('T003','TR08',2);
GO

-- 4) XEBUYT
IF NOT EXISTS (SELECT 1 FROM dbo.XeBuyt WHERE MaXe='X001')
INSERT INTO dbo.XeBuyt (MaXe, BienSo, SucChua, LoaiXe, TrangThai)
VALUES ('X001','51B-12345',60,N'Xe 60 chỗ',N'Đang hoạt động');

IF NOT EXISTS (SELECT 1 FROM dbo.XeBuyt WHERE MaXe='X002')
INSERT INTO dbo.XeBuyt (MaXe, BienSo, SucChua, LoaiXe, TrangThai)
VALUES ('X002','51B-23456',40,N'Xe 40 chỗ',N'Đang hoạt động');

IF NOT EXISTS (SELECT 1 FROM dbo.XeBuyt WHERE MaXe='X003')
INSERT INTO dbo.XeBuyt (MaXe, BienSo, SucChua, LoaiXe, TrangThai)
VALUES ('X003','51B-34567',60,N'Xe 60 chỗ',N'Bảo trì');
GO

-- 5) NHANVIEN
IF NOT EXISTS (SELECT 1 FROM dbo.NhanVien WHERE MaNV='NV001')
INSERT INTO dbo.NhanVien (MaNV, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, Luong)
VALUES ('NV001','079123456789',N'Nguyễn Quản Trị','1990-01-10',N'Nam','0909000001','qtri@xebus.vn',N'Quản trị viên',25000000);

IF NOT EXISTS (SELECT 1 FROM dbo.NhanVien WHERE MaNV='NV002')
INSERT INTO dbo.NhanVien (MaNV, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, Luong)
VALUES ('NV002','079123456780',N'Trần Quản Lý','1992-03-15',N'Nữ','0909000002','qly@xebus.vn',N'Quản lý',18000000);

IF NOT EXISTS (SELECT 1 FROM dbo.NhanVien WHERE MaNV='NV003')
INSERT INTO dbo.NhanVien (MaNV, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, Luong)
VALUES ('NV003','079123456781',N'Lê Bán Vé','1995-07-20',N'Nam','0909000003','banve@xebus.vn',N'Nhân viên bán vé',12000000);

IF NOT EXISTS (SELECT 1 FROM dbo.NhanVien WHERE MaNV='NV004')
INSERT INTO dbo.NhanVien (MaNV, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, Luong)
VALUES ('NV004','079123456782',N'Phạm Tài Xế','1988-09-12',N'Nam','0909000004','taixe@xebus.vn',N'Tài xế',14000000);

IF NOT EXISTS (SELECT 1 FROM dbo.NhanVien WHERE MaNV='NV005')
INSERT INTO dbo.NhanVien (MaNV, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, Luong)
VALUES ('NV005','079123456783',N'Võ Phụ Xe','1996-11-05',N'Nữ','0909000005','phuxe@xebus.vn',N'Phụ xe',10000000);
GO

-- 6) TAIKHOAN (đảm bảo có tài khoản role để login)
-- plain passwords (theo hash bên dưới):
-- admin123 / manager123 / seller123

IF NOT EXISTS (SELECT 1 FROM dbo.TaiKhoan WHERE MaTaiKhoan='TK001')
INSERT INTO dbo.TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, VaiTro, MaNV, TrangThai, NgayTao, NguoiCap)
VALUES ('TK001','admin','$2a$10$V9w0M4M1oW4o0jv2y8v8wO3S6mUoOe8f2Hk7f2yP2M2G6NnJQ2u1G',N'QuanTriVien','NV001',N'Hoạt động',GETDATE(),NULL);

IF NOT EXISTS (SELECT 1 FROM dbo.TaiKhoan WHERE MaTaiKhoan='TK002')
INSERT INTO dbo.TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, VaiTro, MaNV, TrangThai, NgayTao, NguoiCap)
VALUES ('TK002','manager','$2a$10$7vV6Y7m7x8o4b3k2p1n0euXbD5wD3fQjG3uQ4J6uR3cE2pL0mT6dO',N'QuanLy','NV002',N'Hoạt động',GETDATE(),'TK001');

IF NOT EXISTS (SELECT 1 FROM dbo.TaiKhoan WHERE MaTaiKhoan='TK003')
INSERT INTO dbo.TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, VaiTro, MaNV, TrangThai, NgayTao, NguoiCap)
VALUES ('TK003','seller','$2a$10$0rJ8Q6m9u2Qf9p4o8k3nxeQ7nJ9XrE4cF2mL8xV2jH1uK7bP3mE5K',N'NhanVienBanVe','NV003',N'Hoạt động',GETDATE(),'TK001');
GO

-- 7) LICHCHAY
IF NOT EXISTS (SELECT 1 FROM dbo.LichChay WHERE MaLich='L001')
INSERT INTO dbo.LichChay (MaLich, MaTuyen, GioBatDau, GioKetThuc, TanSuatPhut)
VALUES ('L001','T001','05:00:00','21:00:00',15);

IF NOT EXISTS (SELECT 1 FROM dbo.LichChay WHERE MaLich='L002')
INSERT INTO dbo.LichChay (MaLich, MaTuyen, GioBatDau, GioKetThuc, TanSuatPhut)
VALUES ('L002','T002','05:30:00','22:00:00',20);

IF NOT EXISTS (SELECT 1 FROM dbo.LichChay WHERE MaLich='L003')
INSERT INTO dbo.LichChay (MaLich, MaTuyen, GioBatDau, GioKetThuc, TanSuatPhut)
VALUES ('L003','T003','04:30:00','23:00:00',10);
GO

-- 8) CHUYENXE (theo schema thật có NgayChay)
IF NOT EXISTS (SELECT 1 FROM dbo.ChuyenXe WHERE MaChuyen='C001')
INSERT INTO dbo.ChuyenXe (MaChuyen, MaTuyen, MaXe, NgayChay, GioKhoiHanh, GioDenDuKien, TrangThaiChuyen)
VALUES ('C001','T001','X001','2026-06-01','2026-06-01T06:00:00','2026-06-01T06:45:00',N'Chờ khởi hành');

IF NOT EXISTS (SELECT 1 FROM dbo.ChuyenXe WHERE MaChuyen='C002')
INSERT INTO dbo.ChuyenXe (MaChuyen, MaTuyen, MaXe, NgayChay, GioKhoiHanh, GioDenDuKien, TrangThaiChuyen)
VALUES ('C002','T002','X002','2026-06-01','2026-06-01T07:00:00','2026-06-01T08:10:00',N'Đang chạy');

IF NOT EXISTS (SELECT 1 FROM dbo.ChuyenXe WHERE MaChuyen='C003')
INSERT INTO dbo.ChuyenXe (MaChuyen, MaTuyen, MaXe, NgayChay, GioKhoiHanh, GioDenDuKien, TrangThaiChuyen)
VALUES ('C003','T003','X001','2026-06-01','2026-06-01T08:00:00','2026-06-01T08:35:00',N'Hoàn thành');
GO

-- 9) PHANCONGNHANVIEN
IF NOT EXISTS (SELECT 1 FROM dbo.PhanCongNhanVien WHERE MaChuyen='C001' AND MaNV='NV004')
INSERT INTO dbo.PhanCongNhanVien (MaChuyen, MaNV, VaiTro) VALUES ('C001','NV004',N'Tài xế');
IF NOT EXISTS (SELECT 1 FROM dbo.PhanCongNhanVien WHERE MaChuyen='C001' AND MaNV='NV005')
INSERT INTO dbo.PhanCongNhanVien (MaChuyen, MaNV, VaiTro) VALUES ('C001','NV005',N'Phụ xe');
IF NOT EXISTS (SELECT 1 FROM dbo.PhanCongNhanVien WHERE MaChuyen='C002' AND MaNV='NV004')
INSERT INTO dbo.PhanCongNhanVien (MaChuyen, MaNV, VaiTro) VALUES ('C002','NV004',N'Tài xế');
IF NOT EXISTS (SELECT 1 FROM dbo.PhanCongNhanVien WHERE MaChuyen='C003' AND MaNV='NV004')
INSERT INTO dbo.PhanCongNhanVien (MaChuyen, MaNV, VaiTro) VALUES ('C003','NV004',N'Tài xế');
GO

-- 10) KHACHHANG
IF NOT EXISTS (SELECT 1 FROM dbo.KhachHang WHERE MaKH='KH001')
INSERT INTO dbo.KhachHang (MaKH, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, DiaChi)
VALUES ('KH001','079888888881',N'Hoàng Minh A','2000-01-01',N'Nam','0911000001','kha@example.com',N'Thủ Đức');

IF NOT EXISTS (SELECT 1 FROM dbo.KhachHang WHERE MaKH='KH002')
INSERT INTO dbo.KhachHang (MaKH, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, DiaChi)
VALUES ('KH002','079888888882',N'Ngô Thị B','2001-02-02',N'Nữ','0911000002','khb@example.com',N'Quận 1');

IF NOT EXISTS (SELECT 1 FROM dbo.KhachHang WHERE MaKH='KH003')
INSERT INTO dbo.KhachHang (MaKH, CCCD, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, DiaChi)
VALUES ('KH003','079888888883',N'Đào Văn C','1999-03-03',N'Nam','0911000003','khc@example.com',N'Quận 3');
GO

-- 11) LOAIVE
IF NOT EXISTS (SELECT 1 FROM dbo.LoaiVe WHERE MaLoaiVe='VL01')
INSERT INTO dbo.LoaiVe (MaLoaiVe, TenLoaiVe, MoTa, DonGia) VALUES ('VL01', N'Vé lượt dưới 15km', N'Áp dụng tuyến ngắn', 5000);
IF NOT EXISTS (SELECT 1 FROM dbo.LoaiVe WHERE MaLoaiVe='VL02')
INSERT INTO dbo.LoaiVe (MaLoaiVe, TenLoaiVe, MoTa, DonGia) VALUES ('VL02', N'Vé lượt 15-25km', N'Áp dụng tuyến trung bình', 6000);
IF NOT EXISTS (SELECT 1 FROM dbo.LoaiVe WHERE MaLoaiVe='VL03')
INSERT INTO dbo.LoaiVe (MaLoaiVe, TenLoaiVe, MoTa, DonGia) VALUES ('VL03', N'Vé lượt trên 25km', N'Áp dụng tuyến dài', 7000);
IF NOT EXISTS (SELECT 1 FROM dbo.LoaiVe WHERE MaLoaiVe='VL04')
INSERT INTO dbo.LoaiVe (MaLoaiVe, TenLoaiVe, MoTa, DonGia) VALUES ('VL04', N'Vé lượt HSSV', N'Ưu đãi học sinh sinh viên', 3000);
IF NOT EXISTS (SELECT 1 FROM dbo.LoaiVe WHERE MaLoaiVe='VT01')
INSERT INTO dbo.LoaiVe (MaLoaiVe, TenLoaiVe, MoTa, DonGia) VALUES ('VT01', N'Vé tập 30 vé', N'Vé tháng/tập', 120000);
GO

-- 12) VE (schema thật không có LyDoHuy)
IF NOT EXISTS (SELECT 1 FROM dbo.Ve WHERE MaVe='V001')
INSERT INTO dbo.Ve (MaVe, MaLoaiVe, MaKH, MaTuyen, NgayMua, NgayBatDau, NgayHetHan, TrangThaiVe)
VALUES ('V001','VL01','KH001','T001',GETDATE(),NULL,NULL,N'Đã thanh toán');

IF NOT EXISTS (SELECT 1 FROM dbo.Ve WHERE MaVe='V002')
INSERT INTO dbo.Ve (MaVe, MaLoaiVe, MaKH, MaTuyen, NgayMua, NgayBatDau, NgayHetHan, TrangThaiVe)
VALUES ('V002','VL02','KH002','T002',GETDATE(),NULL,NULL,N'Chưa thanh toán');

IF NOT EXISTS (SELECT 1 FROM dbo.Ve WHERE MaVe='V003')
INSERT INTO dbo.Ve (MaVe, MaLoaiVe, MaKH, MaTuyen, NgayMua, NgayBatDau, NgayHetHan, TrangThaiVe)
VALUES ('V003','VL04','KH003',NULL,GETDATE(),'2026-06-01','2026-06-30',N'Đã thanh toán');

IF NOT EXISTS (SELECT 1 FROM dbo.Ve WHERE MaVe='V004')
INSERT INTO dbo.Ve (MaVe, MaLoaiVe, MaKH, MaTuyen, NgayMua, NgayBatDau, NgayHetHan, TrangThaiVe)
VALUES ('V004','VT01','KH001',NULL,GETDATE(),'2026-06-01','2026-06-30',N'Đã sử dụng');

IF NOT EXISTS (SELECT 1 FROM dbo.Ve WHERE MaVe='V005')
INSERT INTO dbo.Ve (MaVe, MaLoaiVe, MaKH, MaTuyen, NgayMua, NgayBatDau, NgayHetHan, TrangThaiVe)
VALUES ('V005','VL03','KH002','T003',GETDATE(),NULL,NULL,N'Đã huỷ');
GO

-- 13) THANHTOAN
IF NOT EXISTS (SELECT 1 FROM dbo.ThanhToan WHERE MaThanhToan='TT001')
INSERT INTO dbo.ThanhToan (MaThanhToan, MaVe, NgayThanhToan, SoTien, PhuongThuc, TrangThaiThanhToan)
VALUES ('TT001','V001',GETDATE(),5000,N'Tiền mặt',N'Thành công');

IF NOT EXISTS (SELECT 1 FROM dbo.ThanhToan WHERE MaThanhToan='TT002')
INSERT INTO dbo.ThanhToan (MaThanhToan, MaVe, NgayThanhToan, SoTien, PhuongThuc, TrangThaiThanhToan)
VALUES ('TT002','V003',GETDATE(),3000,N'Chuyển khoản',N'Thành công');

IF NOT EXISTS (SELECT 1 FROM dbo.ThanhToan WHERE MaThanhToan='TT003')
INSERT INTO dbo.ThanhToan (MaThanhToan, MaVe, NgayThanhToan, SoTien, PhuongThuc, TrangThaiThanhToan)
VALUES ('TT003','V004',GETDATE(),120000,N'Ví điện tử',N'Thành công');
GO

-- QUICK SUMMARY
SELECT 'TuyenXe' AS TableName, COUNT(*) AS TotalRows FROM dbo.TuyenXe
UNION ALL SELECT 'TramDung', COUNT(*) FROM dbo.TramDung
UNION ALL SELECT 'ChiTietTuyen', COUNT(*) FROM dbo.ChiTietTuyen
UNION ALL SELECT 'XeBuyt', COUNT(*) FROM dbo.XeBuyt
UNION ALL SELECT 'NhanVien', COUNT(*) FROM dbo.NhanVien
UNION ALL SELECT 'TaiKhoan', COUNT(*) FROM dbo.TaiKhoan
UNION ALL SELECT 'LichChay', COUNT(*) FROM dbo.LichChay
UNION ALL SELECT 'ChuyenXe', COUNT(*) FROM dbo.ChuyenXe
UNION ALL SELECT 'PhanCongNhanVien', COUNT(*) FROM dbo.PhanCongNhanVien
UNION ALL SELECT 'KhachHang', COUNT(*) FROM dbo.KhachHang
UNION ALL SELECT 'LoaiVe', COUNT(*) FROM dbo.LoaiVe
UNION ALL SELECT 'Ve', COUNT(*) FROM dbo.Ve
UNION ALL SELECT 'ThanhToan', COUNT(*) FROM dbo.ThanhToan;
GO
