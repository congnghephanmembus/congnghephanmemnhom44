# TODO - Sửa đăng nhập plaintext theo yêu cầu

- [x] Thu thập schema thực tế từ SQL Server
- [x] Xác nhận nguyên nhân fail login do lệch cơ chế mã hóa mật khẩu
- [x] Sửa `SecurityConfig` dùng plaintext password encoder
- [x] Cập nhật `reset-passwords.sql` set mật khẩu plaintext cho admin/manager/seller
- [ ] Chạy SQL cập nhật mật khẩu
- [ ] Chạy Spring Boot app
- [ ] Test web `/login` cho 3 role và xác nhận redirect
- [ ] Test truy cập các màn hình chính theo role
- [ ] Tổng hợp kết quả test
