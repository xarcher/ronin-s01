> [API Specs](./HW01.md)
## Yêu cầu
### 1. API Search Flights
> Nhập vào thông tin địa điểm đến, địa điểm đi và ngày bay. Tìm kiếm tất cả các chuyến bay có trong ngày đó.
- Các thông tin cần thiết để tìm kiếm vé máy bay:
	- Loại vé: 
		- khứ hồi(return ticket) 
		- Vé một chiều(single ticket)
	- Địa kđiểm khởi hành
	 - Điểm đến
	- Ngày khởi hành
	- Số lượng hành khách(passenger)
		- Người lớn
		- Trẻ em
		- Em bé
	- Mã khuyến mãi(nếu có)

- Thông tin tìm kiếm trả về
	- Hạng vé
	- Địa điểm khởi hành
	- Điểm đến
	- Ngày khởi hành
	- Giá vé
	- Thời gian bay dự kiến
	- Thuế

### 2. API Book Flights
> Đặt vé máy sau khi đã tìm kiếm được chuyến bay phù hợp

- 2.1 API đặt vé
- Các thông tin cần thiết để  đặt vé máy bay
	- Hạng vé
	- Địa điểm khởi hành
	- Điểm đến
	- Ngày khởi hành
	- Khối lượng hành lý mua thêm(mặc định 10kg)
	- Tổng số tiền(Đã tính cả thuế)

- Thông tin sau khi đặt vé trả về
	- Mã giao dịch
	- Thời gian đặt vé
	- Tổng số tiền
	- Trạng thái(Đang chờ thanh toán, đã hủy, đặt thất bại, đặt thành công)

 
-  2.2 API Thanh toán
- Thông tin cần thiết để thanh toán vé máy bay:
	-  Mã giao dịch
	- Số tiền
	- Thông tin tài khoản ngân hàng thanh toán

- Thông tin sau khi thanh toán trả về
	- Mã giao dịch
	- Trạng thái(thanh toán thành công, thanh toán thất bại)
	- Thời gian thanh toán
	- ghi chú

### 3. API Get history of Booking
> Xem lại lịch sử các lần đặt vé
- Thông tin API xem lịch xử
	- Thông tin tìm kiếm
		- Thời gian bắt đầu
		- Thời gian kết thúc
		- Trạng thái vé
-  Thông tin trả về:
	- Mã giao dịch
	-  Thời gian mua vé
	- Thời gian thanh toán
	- Trạng thái vé
	- Trạng thái thanh toán
	- tổng số tiền
	- số lượng hành khách
	- Khối lượng hành lý mua thêm
	- Các thông tin chung của vé đã đặt
