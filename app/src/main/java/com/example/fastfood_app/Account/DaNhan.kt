package com.example.fastfood_app.Account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

// Data class for Order
data class danhan(
    val name: String,
    val date: String,
    val itemsCount: String,
    val price: String,
    val State : String,
    val imageRes: Int
)

@Composable
fun Danhan(navController: NavHostController) {
    val danhan = listOf(
        danhan("Mỳ", "29 Nov, 01:20 pm", "2 items", "20.000đ","Đã Nhận", R.drawable.myy),
        danhan("Lemonade", "30 Nov, 02:30 pm", "1 item", "15.000đ","Đã Nhận", R.drawable.nuoc),
        danhan("Pizza", "01 Dec, 12:00 pm", "3 items", "25.000đ","Đã Nhận", R.drawable.pizza)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF6E5)) // Màu nền vàng nhạt
    ) {
        // Nút quay lại và tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("Account") }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "My Orders",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                textAlign = TextAlign.Start
            )
        }

        // Tabs
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tab "Active"
                Text(
                    text = "Đang giao",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable {navController.navigate("OrderScreen")}
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .background(
                            color = Color(0xFFFFE6D5),
                            shape = RoundedCornerShape(20.dp)
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Tab "Completed"
                Text(
                    text = "Đã Nhận",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6E40),
                    modifier = Modifier
                        .clickable { /* Xử lý khi nhấp vào */ }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .background(
                            color = Color(0xFFFFF6E5),
                            shape = RoundedCornerShape(20.dp)
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Tab "Cancelled"
                Text(
                    text = "Đã huỷ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable { navController.navigate("dahuy") }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .background(
                            color = Color(0xFFFFF6E5),
                            shape = RoundedCornerShape(20.dp)
                        )
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // Order list in a horizontal layout
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(danhan) { danhan ->
                danhan(danhan)
            }
        }
    }
}

@Composable
fun danhan(danhan: danhan) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ảnh món ăn
        Image(
            painter = painterResource(id = danhan.imageRes),
            contentDescription = "Hình ảnh món ăn",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        // Thông tin món ăn
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = danhan.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = danhan.date,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = danhan.State,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = danhan.itemsCount,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Giá và nút
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = danhan.price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6E40)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

