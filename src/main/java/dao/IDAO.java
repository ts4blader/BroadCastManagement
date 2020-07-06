package dao; //Data access objects

import java.util.List;

interface IDAO<T> {
	//Lấy tất cả các hàng dữ liệu của bảng, trả về danh sách đối tượng (vd: kênh)
	List<T>		getAll ();
	
	//Nhận vào 1 id, trả về hàng đầu tiên của bảng có mã trùng với id truyền vào - lưu ý: ko dùng được với bảng Lich Phat Song, vì lịch PS có đến 4 trường làm khóa chính
	T			getByID (String id);
	
	//Nhận vào 1 đối tượng (vd: kênh) và lưu vào DB, trả về true nếu thao tác thành công, false nếu thất bại
	boolean 	save (T t);
	
	//Nhận vào 1 đối tượng (vd: kênh) và cập nhật đối tượng có khóa trùng với đối tượng truyền vào, trả về true nếu thao tác thành công, false nếu thất bại
	boolean		update (T t);
	
	//Nhận vào 1 đối tượng, xóa tất cả hàng dữ liệu mà thỏa  mọi giá trị của đối tượng truyền vào (vd: mã = mã, tên = tên, ...).
	boolean 	delete (T t);
	
	//Nhận vào 1 đối tượng, trả về 1 danh sách đối tượng mà thỏa mọi giá trị của đối tượng truyền vào (vd: mã = mã, tên = tên, ...).
	List<T>		getByObj (T t);
	
	// trả về số lượng dòng dữ liệu hiện có trong bảng dữ liệu.
	int 		getNumberOfRows();
}
