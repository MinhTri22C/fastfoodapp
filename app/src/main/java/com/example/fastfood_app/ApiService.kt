package com.example.fastfood_app


import com.example.fastfood_app.Data.DonHangResponse
import com.example.fastfood_app.Data.Don_Hang
import com.example.fastfood_app.Data.FavoriteResponse
import com.example.fastfood_app.Data.LoginRequest
import com.example.fastfood_app.Data.LoginResponse
import com.example.fastfood_app.Data.NguoiDung
import com.example.fastfood_app.Data.Product
import com.example.fastfood_app.Data.ProductCagResponse
import com.example.fastfood_app.Data.ProductCag_Ga
import com.example.fastfood_app.Data.ProductCag_My
import com.example.fastfood_app.Data.ProductCag_Nuoc
import com.example.fastfood_app.Data.ProductCag_Pizza
import com.example.fastfood_app.Data.ProductResponse
import com.example.fastfood_app.Data.RegRequest
import com.example.fastfood_app.Data.RegResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("sanpham/dssanpham.php")
    suspend fun getProducts(): Response<ProductResponse>


    @GET("sanpham/dssptheoloaiHum.php")
    suspend fun  getProductsCag():Response<ProductCagResponse>


    @GET("sanpham/dssanpham_my.php")
    suspend fun getProductCag_My():Response<ProductCag_My>

    @GET("sanpham/dssanpham_pizza.php")
    suspend fun getProductCag_Pizza():Response<ProductCag_Pizza>

    @GET("sanpham/dssanpham_ga.php")
    suspend fun getProductCag_Ga():Response<ProductCag_Ga>

    @GET("sanpham/dssanpham_nuoc.php")
    suspend fun getProductCag_Nuoc():Response<ProductCag_Nuoc>


    @POST("nguoidung/login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("yeuthich/dsspyeuthich.php") // Endpoint API của bạn
    suspend fun getFavoriteProducts(
        @Query("User_ID") userId: Int
    ): Call<FavoriteResponse>

    @GET("sanpham/chitietsanpham.php")
    suspend fun getProductDetails(@Query("id") favoriteId: Int): Product

    @GET("donhang/dsdonhang.php")
    suspend fun getDon_Hang(): Response<DonHangResponse>

    @GET("donhang/chitietdonhang.php")
    suspend fun getChiTietDonHang(@Query("Don_hang_ID") donHangId: Int): Response<Don_Hang>

    @POST("donhang/duyetdonhang.php")
    suspend fun duyetDonHang(@Query("Don_hang_ID") donHangId: Int): Response<Void>

    // API huỷ đơn hàng
    @POST("donhang/huydonhang.php")
    suspend fun huyDonHang(@Query("Don_hang_ID") donHangId: Int): Response<Void>
//
    @GET("nguoidung/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): Response<NguoiDung>

    @POST("nguoidung/dangky.php")
    suspend fun dangky(@Body regRequest: RegRequest): Response<RegResponse>


}
