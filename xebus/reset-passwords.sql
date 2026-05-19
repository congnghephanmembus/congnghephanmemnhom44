USE QuanLyVeXeBuytNoiDo;
GO

-- Mật khẩu plaintext theo yêu cầu: admin123
DECLARE @plainPassword VARCHAR(100) = 'admin123';

UPDATE TaiKhoan
SET MatKhau = @plainPassword
WHERE TenDangNhap IN ('admin', 'manager', 'seller');

SELECT TenDangNhap,
       VaiTro,
       LEN(MatKhau) AS HashLen,
       LEFT(MatKhau, 4) AS HashPrefix,
       TrangThai
FROM TaiKhoan
WHERE TenDangNhap IN ('admin', 'manager', 'seller')
ORDER BY TenDangNhap;
GO
