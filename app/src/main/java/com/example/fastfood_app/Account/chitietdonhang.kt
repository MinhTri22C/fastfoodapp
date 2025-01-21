package com.example.fastfood_app.Account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fastfood_app.R

@Composable
fun OrderDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Đảm bảo chiếm toàn bộ không gian màn hình
            .background(Color(0xFFFFF6E5)) // Màu nền vàng nhạt
            .padding(16.dp)
    ) {
        // Nút quay lại và tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth() // Đảm bảo row chiếm toàn bộ chiều ngang
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, // Biểu tượng quay lại
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() } // Quay lại màn hình trước đó
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Chi tiết đơn hàng",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Thông tin món ăn
        Row(
            modifier = Modifier
                .fillMaxWidth() // Đảm bảo chiếm toàn bộ chiều ngang
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.myy),
                contentDescription = "Ảnh món ăn",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Mỳ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "29 Nov, 01:20 pm",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "2 items",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "80.000đ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6E40)
                )
                Text(
                    text = "Đang giao",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007BFF)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Thuộc tính món ăn
        Column(
            modifier = Modifier.fillMaxWidth(), // Đảm bảo chiếm toàn bộ chiều ngang
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DetailItem(label = "Đặc điểm:", value = "Có cay")
            DetailItem(label = "Phương thức thanh toán:", value = "Tiền mặt khi nhận hàng")
            DetailItem(label = "Ghi chú:", value = "Vui lòng giao hàng trước 2 giờ chiều.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFF6E40),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(32.dp)
            ) {
                androidx.compose.material.Text("Cancel Order")
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Đảm bảo chiếm toàn bộ chiều ngang
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(0.6f) // Giới hạn không gian cho giá trị
        )

    }
}
