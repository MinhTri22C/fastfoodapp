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
data class Order(
    val name: String,
    val date: String,
    val itemsCount: String,
    val price: String,
    val State : String,
    val imageRes: Int
)

@Composable
fun OrderScreen(navController: NavHostController) {
    val orders = listOf(
        Order("Mỳ", "29 Nov, 01:20 pm", "2 items", "80.000đ","Đang giao", R.drawable.myy),
        Order("Lemonade", "30 Nov, 02:30 pm", "1 item", "30.000đ","Đang giao", R.drawable.nuoc),
        Order("Pizza", "01 Dec, 12:00 pm", "3 items", "90.000đ","Đang giao", R.drawable.pizza),
        Order("Pizza", "01 Dec, 13:00 pm", "3 items", "50.000đ","Đang giao", R.drawable.hum),
        Order("Pizza", "01 Dec, 15:00 pm", "3 items", "60.000đ","Đang giao", R.drawable.garan),
        Order("Xúc Xích Đức", "01 Dec, 16:00 pm", "2 items", "30.000đ","Đang giao", R.drawable.xucxich)
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
                    color = Color(0xFFFF6E40),
                    modifier = Modifier
                        .clickable {}
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .background(
                            color = Color(0xFFFFE6D5),
                            shape = RoundedCornerShape(20.dp)
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Tab "Completed"
                Text(
                    text = "Đã nhận",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable { navController.navigate("Danhan") }
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
            items(orders) { order ->
                OrderItem(order)
            }
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Để hàng chiếm toàn bộ chiều ngang
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ảnh món ăn
        Image(
            painter = painterResource(id = order.imageRes),
            contentDescription = "Hình ảnh món ăn",
            modifier = Modifier
                .size(64.dp) // Kích thước hình ảnh
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        // Thông tin món ăn
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = order.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.date,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.itemsCount,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.State,
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
                text = order.price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6E40)
            )
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
                Text("Cancel Order")
            }
        }
    }
}
