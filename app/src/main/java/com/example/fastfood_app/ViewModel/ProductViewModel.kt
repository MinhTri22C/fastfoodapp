package com.example.fastfood_app.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.fastfood_app.Data.Don_Hang
import com.example.fastfood_app.Data.FavoriteProduct
import com.example.fastfood_app.Data.LoginRequest
import com.example.fastfood_app.Data.LoginResponse
import com.example.fastfood_app.Data.NguoiDung
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.fastfood_app.Data.Product  // Replace with your Product model's package
import com.example.fastfood_app.Data.ProductResponse
import com.example.fastfood_app.Data.RegRequest

import com.example.fastfood_app.Data.RetrofitClient
import com.example.fastfood_app.Data.User
import com.example.fastfood_app.ProductDetail.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ProductViewModel : ViewModel() {
 private val _listProduct = MutableLiveData<List<Product>>()
 val listProduct: LiveData<List<Product>> = _listProduct
private val _listProductCagHum=MutableLiveData<List<Product>>()
 val listProductCagHum:LiveData<List<Product>> = _listProductCagHum
private val _listProductCag_My=MutableLiveData<List<Product>>()
 val listProductCag_My:LiveData<List<Product>> = _listProductCag_My
 private val _listProductCag_Pizza=MutableLiveData<List<Product>>()
 val listProductCag_Pizza:LiveData<List<Product>> = _listProductCag_Pizza
 private val _listProductCag_Ga=MutableLiveData<List<Product>>()
 val listProductCag_Ga:LiveData<List<Product>> = _listProductCag_Ga
 private val _listProductCag_Nuoc=MutableLiveData<List<Product>>()
 val listProductCag_Nuoc:LiveData<List<Product>> = _listProductCag_Nuoc
 private val _loginSuccess = MutableLiveData<Boolean>()
 val loginSuccess: LiveData<Boolean> = _loginSuccess
 val _userData = MutableLiveData<User?>()
 private val repository = ProductRepository()
//
private val _reginSuccess = MutableLiveData<Boolean>()
 val reginSuccess: LiveData<Boolean> = _reginSuccess



 private val _isLoading = MutableLiveData<Boolean>()
 val isLoading: LiveData<Boolean> = _isLoading
 private val _errorMessage = MutableLiveData<String>()
 val errorMessage: LiveData<String> = _errorMessage


 //Lấy tất cả sản phẩm
 fun getAllProducts() {
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProducts()
    if (response.isSuccessful) {
     _listProduct.value = response.body()?.productList ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
 private val _listDon_Hang = MutableLiveData<List<Don_Hang>>()
 val listDon_Hang: LiveData<List<Don_Hang>> = _listDon_Hang
 //
 fun getDon_hang() {
  _isLoading.value = true
  _errorMessage.value = ""

  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getDon_Hang()
    if (response.isSuccessful) {
     _listDon_Hang.value = response.body()?.donhang_list ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
 //dangky
 fun dangky(username: String, password: String,name:String,email:String) {
  _isLoading.value = true
  _errorMessage.value = "" // Reset lỗi trước khi thực hiện yêu cầu

  val regRequest = RegRequest(username, name,password,email) // Tạo đối tượng LoginRequest
  Log.d("DangKy", "Đang gửi yêu cầu đăng ký: $regRequest")
  viewModelScope.launch {
   try {
    // Gọi API sử dụng suspend function
    val regResponse = RetrofitClient.api.dangky(regRequest)
    Log.d("DangKy", "Phản hồi từ API: ${regResponse.body()}")
    if (regResponse.isSuccessful) {
     val responseBody = regResponse.body()
     if (responseBody?.status!!) {
      // Nếu `status` từ API là true
      Log.d("DangKy", "Đăng ký thành công!")
      _reginSuccess.value = true
     } else {
      Log.d("DangKy", "Đăng ký thất bại: ${responseBody?.message}")
      // Nếu `status` là false
      _errorMessage.value = responseBody?.message ?: "Đăng nhập thất bại"
     }
    } else {
     _errorMessage.value = regResponse.message() ?: "Lỗi không xác định"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Lỗi: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }


 fun filterDonHangByStatus(status: String) {
  _isLoading.value = true
  _errorMessage.value = ""

  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getDon_Hang()
    if (response.isSuccessful) {
     val allDonHangList = response.body()?.donhang_list ?: emptyList()
     // Lọc đơn hàng theo trạng thái
     val filteredList = if (status.isEmpty()) {
      allDonHangList
     } else {
      allDonHangList.filter { it.trangthai == status }
     }
     _listDon_Hang.value = filteredList
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
//
fun duyetDonHang(donHangId: Int) {
 _isLoading.value = true
 viewModelScope.launch {
  try {
   val response = RetrofitClient.api.duyetDonHang(donHangId)
   if (response.isSuccessful) {
    // Cập nhật lại trạng thái đơn hàng nếu cần
    // Xử lý UI sau khi duyệt đơn
   } else {
    // Xử lý lỗi khi duyệt đơn không thành công
   }
  } catch (e: Exception) {
   // Xử lý ngoại lệ
  } finally {
   _isLoading.value = false
  }
 }
}

 fun huyDonHang(donHangId: Int) {
  _isLoading.value = true
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.huyDonHang(donHangId)
    if (response.isSuccessful) {
     // Cập nhật lại trạng thái đơn hàng nếu cần
     // Xử lý UI sau khi huỷ đơn
    } else {
     // Xử lý lỗi khi huỷ đơn không thành công
    }
   } catch (e: Exception) {
    // Xử lý ngoại lệ
   } finally {
    _isLoading.value = false
   }
  }
 }
 //
 private val _userInfo = MutableLiveData<NguoiDung>()
 val userInfo: LiveData<NguoiDung> = _userInfo

 fun getUserInfo(userId: Int) {
  _isLoading.value = true
  _errorMessage.value = ""

  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getUserById(userId)
    if (response.isSuccessful) {
     _userInfo.value = response.body()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
//
 private val _donHangDetail = MutableLiveData<Don_Hang?>()
 val donHangDetail: LiveData<Don_Hang?> = _donHangDetail

 fun getChiTietDonHang(donHangId: Int) {
  _isLoading.value = true
  _errorMessage.value = ""

  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getChiTietDonHang(donHangId)
    if (response.isSuccessful) {
     _donHangDetail.value = response.body()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }

 //

// private val _chiTietDonHang = MutableLiveData<Don_Hang?>()
// val chiTietDonHang: LiveData<Don_Hang?> = _chiTietDonHang
//
// fun getChiTietDonHang(donHangId: Int) {
//  viewModelScope.launch {
//   _isLoading.value = true
//   try {
//    val response = RetrofitClient.api.getChiTietDonHang(donHangId)
//    if (response.isSuccessful && response.body() != null) {
//     _chiTietDonHang.value = response.body()
//    } else {
//     _chiTietDonHang.value = null
//     _errorMessage.value = "Không tìm thấy chi tiết đơn hàng."
//    }
//   } catch (e: Exception) {
//    _errorMessage.value = "Lỗi: ${e.message}"
//   } finally {
//    _isLoading.value = false
//   }
//  }
// }


 //

 fun getProductsCagHum() {
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProductsCag()
    if (response.isSuccessful) {
     _listProductCagHum.value = response.body()?.productListCag ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
//


 fun getProductsCag_My() {
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProductCag_My()
    if (response.isSuccessful) {
    _listProductCag_My.value=response.body()?.productCag_My ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }

 fun getProductsCag_Pizza(){
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProductCag_Pizza()
    if (response.isSuccessful) {
     _listProductCag_Pizza.value=response.body()?.productCag_Pizza ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }

 fun getProductsCag_Ga(){
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProductCag_Ga()
    if (response.isSuccessful) {
     _listProductCag_Ga.value=response.body()?.productCag_Ga ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }

 fun getProductsCag_Nuoc(){
  _isLoading.value = true
  _errorMessage.value = ""

  // Gọi API trong một coroutine
  viewModelScope.launch {
   try {
    val response = RetrofitClient.api.getProductCag_Nuoc()
    if (response.isSuccessful) {
     _listProductCag_Nuoc.value=response.body()?.productCag_Nuoc ?: emptyList()
    } else {
     _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Failure: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }


 fun login(username: String, password: String) {
  _isLoading.value = true
  _errorMessage.value = "" // Reset lỗi trước khi thực hiện yêu cầu

  val loginRequest = LoginRequest(username, password) // Tạo đối tượng LoginRequest

  viewModelScope.launch {
   try {
    // Gọi API sử dụng suspend function
    val loginResponse = RetrofitClient.api.login(loginRequest)

    if (loginResponse.isSuccessful) {
     val responseBody = loginResponse.body()
     if (responseBody?.status!!) {
      // Nếu `status` từ API là true
      _loginSuccess.value = true
      _userData.value=responseBody.data
     } else {
      // Nếu `status` là false
      _errorMessage.value = responseBody?.message ?: "Đăng nhập thất bại"
     }
    } else {
     _errorMessage.value = loginResponse.message() ?: "Lỗi không xác định"
    }
   } catch (e: Exception) {
    _errorMessage.value = "Lỗi: ${e.message}"
   } finally {
    _isLoading.value = false
   }
  }
 }
 fun productDetail(productId: Int) = liveData(Dispatchers.IO) {
  try {
   // Gọi API từ repository để lấy thông tin sản phẩm
   val product = repository.getProductDetails(productId)
   emit(product)  // Trả dữ liệu cho UI thông qua LiveData
  } catch (e: HttpException) {
   emit(null)  // Trả null nếu có lỗi khi gọi API
  }
 }
}

