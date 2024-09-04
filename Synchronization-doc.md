**1. Đồng bộ hóa (Synchronization) trong Selenium WebDriver:**

   Đồng bộ hóa là quá trình đảm bảo rằng mã tự động hóa của bạn chạy đồng thời với các hành động và trạng thái của trang
   web. Một tình huống phổ biến trong kiểm thử tự động là khi trang web cần thời gian để tải kết quả sau khi bạn thực
   hiện một hành động, ví dụ: tìm kiếm chuyến bay hoặc khách sạn. Trang sẽ mất vài giây để tải dữ liệu và hiển thị kết
   quả. Nếu không có đồng bộ hóa, Selenium có thể gặp lỗi vì nó sẽ cố gắng tương tác với các phần tử trên trang trước
   khi chúng được tải hoàn toàn.
   
**2. Các phương pháp đồng bộ hóa trong Selenium:**
   
**`Implicit Wait (Chờ ngầm):`**
-Thiết lập thời gian chờ cho toàn bộ phiên làm việc. Selenium sẽ chờ một khoảng thời gian xác định trước khi báo lỗi nếu
không tìm thấy phần tử. Thời gian chờ tối đa được đặt trước, nhưng Selenium sẽ dừng chờ nếu phần tử xuất hiện trước thời
gian đó. 

-Ví dụ: Bạn có thể thiết lập thời gian chờ 5 giây, nhưng nếu phần tử xuất hiện sau 2 giây, Selenium sẽ tiếp tục
ngay sau đó mà không cần đợi hết 5 giây. 

    **Explicit Wait (Chờ tường minh):**
Chờ một điều kiện cụ thể xảy ra trước khi tiếp tục thực hiện các bước tiếp theo. Điều này thường được sử dụng khi cần
đợi một phần tử cụ thể hoặc một trạng thái cụ thể của trang web. 

    **`Thread.sleep:`**
Dừng thực thi mã trong một khoảng thời gian cố định. Tuy nhiên, điều này không linh hoạt và có thể dẫn đến thời gian chờ
không cần thiết nếu phần tử xuất hiện sớm hơn. 
   
**`Fluent Wait:`**
Kết hợp giữa chờ tường minh và kiểm tra điều kiện trong một khoảng thời gian lặp đi lặp lại. Có thể kiểm tra trạng thái
của phần tử nhiều lần trong thời gian chờ.

**3. Lưu ý khi sử dụng các phương pháp đồng bộ hóa:**

- Implicit Wait được sử dụng khi bạn cần áp dụng thời gian chờ cho toàn bộ phiên làm việc.
- Explicit Wait thường được sử dụng cho các trường hợp cụ thể khi cần đảm bảo phần tử đã sẵn sàng để tương tác. 
- Tránh lạm dụng Thread.sleep vì nó có thể làm tăng thời gian kiểm thử không cần thiết.