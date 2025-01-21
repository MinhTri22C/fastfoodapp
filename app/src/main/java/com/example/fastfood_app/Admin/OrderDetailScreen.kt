import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fastfood_app.ViewModel.ProductViewModel
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetail(navController: NavHostController, donHangId: Int, viewModel: ProductViewModel = viewModel()) {
    val donHangDetail by viewModel.donHangDetail.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val userInfo by viewModel.userInfo.observeAsState()

    LaunchedEffect(donHangId) {
        viewModel.getChiTietDonHang(donHangId)
        donHangDetail?.let {
            viewModel.getUserInfo(it.userid)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết đơn hàng") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                isLoading -> CircularProgressIndicator()
                errorMessage.isNotEmpty() -> Text(text = errorMessage, color = Color.Red)
                donHangDetail != null -> {
                    Text("Đơn hàng ID: ${donHangDetail!!.donhangid}", fontWeight = FontWeight.Bold)
                    Text("User ID: ${donHangDetail!!.userid}")
                    Text("Mã đặt hàng: ${donHangDetail!!.dathangid}")
                    Text("Ngày đặt hàng: ${donHangDetail!!.Ngaydathang}")
                    Text("Tổng tiền: ${donHangDetail!!.Tongtien} VND")
                    Text(
                        "Trạng thái: ${donHangDetail!!.trangthai}",
                        color = if (donHangDetail!!.trangthai == "Hoàn thành") Color.Green else Color.Blue
                    )
                    userInfo?.let {
                        Text("Họ tên: ${it.hoTen}")
                        Text("Số điện thoại: ${it.soDienThoai}")
                        Text("Địa chỉ: ${it.diaChiId}")  // Hoặc lấy thêm thông tin địa chỉ chi tiết nếu có
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = {
                                donHangDetail?.let {
                                    viewModel.duyetDonHang(it.donhangid)
                                }
                            },
                            enabled = !isLoading  // Disable khi đang tải
                        ) {
                            Text("Duyệt đơn")
                        }

                        Button(
                            onClick = {
                                donHangDetail?.let {
                                    viewModel.huyDonHang(it.donhangid)
                                }
                            },
                            enabled = !isLoading  // Disable khi đang tải
                        ) {
                            Text("Huỷ đơn", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
