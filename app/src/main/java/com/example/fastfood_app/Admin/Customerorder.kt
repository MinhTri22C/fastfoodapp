package com.example.fastfood_app.Admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fastfood_app.Data.Don_Hang
import com.example.fastfood_app.Menu.BottomAppBar
import com.example.fastfood_app.R
import com.example.fastfood_app.ViewModel.ProductViewModel

@Composable
fun Customerorder(navController: NavHostController, viewModel: ProductViewModel = viewModel()) {
    viewModel.getDon_hang()
    val Don_HangList by viewModel.listDon_Hang.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    // Tạo một list các trạng thái đơn hàng để người dùng chọn
    val statusList = listOf("Hoàn thành", "Đang xử lý","Đang giao hàng","Đã huỷ")
    var selectedStatus by remember { mutableStateOf("Tất cả") }
    var isDropdownMenuVisible by remember { mutableStateOf(false) }
    // Khi chọn trạng thái, gọi hàm lọc
    LaunchedEffect(selectedStatus) {
        viewModel.filterDonHangByStatus(selectedStatus)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFFF9D23),
                contentPadding = PaddingValues(start = 16.dp)
            ) {
                IconButton(onClick = { navController.navigate("Homeadmin") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.adminback),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Customer Order",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                isDropdownMenuVisible = !isDropdownMenuVisible
            }) {
                Text("Lọc theo trạng thái")
            }

            // DropdownMenu chỉ hiển thị khi isDropdownMenuVisible là true
            if (isDropdownMenuVisible) {
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = { isDropdownMenuVisible = false },
                    content = {
                        statusList.forEach { status ->
                            DropdownMenuItem(onClick = {
                                selectedStatus = status
                                isDropdownMenuVisible = false // Ẩn menu khi chọn
                            }) {
                                Text(text = status)
                            }
                        }
                    }
                )
            }

            if (isLoading) {
                CircularProgressIndicator()
            } else if (Don_HangList.isEmpty()) {
                Text("Không có đơn hàng nào.", fontSize = 18.sp, color = Color.Gray)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(Don_HangList) { donHang ->
                        OrderItem(donHang,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderItem(donHang: Don_Hang,navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Đơn hàng ID: ${donHang.donhangid}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("User ID: ${donHang.userid}", fontSize = 14.sp)
            Text("Mã đặt hàng: ${donHang.dathangid}", fontSize = 14.sp)
            Text("Ngày đặt hàng: ${donHang.Ngaydathang}", fontSize = 14.sp)
            Text("Tổng tiền: ${donHang.Tongtien} VND", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                "Trạng thái: ${donHang.trangthai}",
                color = if (donHang.trangthai == "Hoàn thành") Color.Green else Color.Blue,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("OrderDetail/${donHang.donhangid}") }
                ) {
                    Text("Chi tiết")
                }
        }
    }
}
}
