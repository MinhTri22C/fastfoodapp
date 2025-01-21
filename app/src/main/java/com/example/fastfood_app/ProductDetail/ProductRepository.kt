package com.example.fastfood_app.ProductDetail

import com.example.fastfood_app.Data.Product
import com.example.fastfood_app.Data.RetrofitClient

class ProductRepository {

    private val apiService = RetrofitClient.api

    // Lấy thông tin sản phẩm từ API
    suspend fun getProductDetails(favoriteId: Int): Product {
        return apiService.getProductDetails(favoriteId)
    }
}
