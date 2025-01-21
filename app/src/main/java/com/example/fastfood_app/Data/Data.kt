package com.example.fastfood_app.Data

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("San_pham_ID") val id: String,
    @SerializedName("Ten_san_pham") val name: String,
    @SerializedName("Mo_ta") val description: String,
    @SerializedName("Gia") val price: String,
    @SerializedName("Co_cay") val hasTree: String,
    @SerializedName("Duong_Dan") val imageUrl: String,
    @SerializedName("Ten_loai_san_pham") val category: String,
    @SerializedName("Ten_Size") val size: String
)
data class ProductResponse(
    @SerializedName("dssanpham") val productList: List<Product>
)
data class ProductCagResponse(
    @SerializedName("dssanphamtheoloai") val productListCag: List<Product>

)
data class ProductCag_My(
    @SerializedName("dssanpham_my") val productCag_My:List<Product>
)

data class ProductCag_Pizza(
    @SerializedName("dssanpham_pizza") val productCag_Pizza:List<Product>
)
data class ProductCag_Ga(
    @SerializedName("dssanpham_ga") val productCag_Ga:List<Product>
)

data class ProductCag_Nuoc(
    @SerializedName("dssanpham_nuoc") val productCag_Nuoc:List<Product>
)

data class LoginRequest(
    @SerializedName("Username") val username: String,
    @SerializedName("Mat_khau") val password: String
)
data class LoginResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data:User?
 )
data class User(
    @SerializedName("User_ID") val userId: Int,
    @SerializedName("Username") val username: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Vai_tro") val role: String,
    @SerializedName("Mat_khau") val pass:String
)





data class FavoriteProduct(
    @SerializedName("SPYT_ID") val favoriteId: Int,
    @SerializedName("User_ID") val userId: Int,
    @SerializedName("Ten_san_pham") val productName: String,
    @SerializedName("Duong_Dan") val imageUrl: String
)

data class FavoriteResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<FavoriteProduct>?
)
data class Don_Hang(
    @SerializedName("Don_hang_ID") val donhangid: Int,
    @SerializedName("User_ID") val userid: Int,  // Xóa khoảng trắng dư thừa
    @SerializedName("Dat_Hang_ID") val dathangid: Int,  // Xóa khoảng trắng dư thừa
    @SerializedName("Ngay_dat_hang") val Ngaydathang: String,
    @SerializedName("Tong_tien") val Tongtien: String,
    @SerializedName("Trang_thai") val trangthai: String,
    @SerializedName("Thanh_Toan_ID") val thanhtoanid: Int,
)

data class DonHangResponse(
    @SerializedName("danhsachdonhang") val donhang_list: List<Don_Hang>  // Đổi tên trường thành đúng với JSON
)

data class NguoiDung(
    @SerializedName("User_ID") val userId: Int,
    @SerializedName("Username") val username: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Mat_khau") val matKhau: String,
    @SerializedName("Vai_tro") val vaiTro: String,
    @SerializedName("So_dien_thoai") val soDienThoai: String,
    @SerializedName("DiaChi_ID") val diaChiId: Int,
    @SerializedName("Ngay_sinh") val ngaySinh: String,
    @SerializedName("Ho_ten") val hoTen: String,
    @SerializedName("Ngay_dang_ky") val ngayDangKy: String
)

data class RegRequest(
    @SerializedName("Ho_ten") val hoten: String,
    @SerializedName("Mat_khau") val password: String,
    @SerializedName("Username") val username: String,
    @SerializedName("Email") val email: String

)
data class RegResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
)