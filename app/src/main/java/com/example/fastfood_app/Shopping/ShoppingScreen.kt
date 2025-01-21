package com.example.fastfood_app.Shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fastfood_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(navController:NavHostController){
    var quantity by remember { mutableStateOf(1) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 0.dp), // Adjust padding here
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // The IconButton is now positioned with no extra padding on the start
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Text(
                            text = "Giỏ hàng",
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(start = 8.dp) // Optional, adjust text position
                        )
                    }
                },
                actions = {
                    // Add more actions here if needed
                },
                modifier = Modifier.height(70.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar( modifier = Modifier.height(65.dp), // Đặt chiều cao theo mong muốn
                containerColor = Color.Red, // Màu nền cho BottomAppBar
                contentColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // Chiếm toàn bộ chiều rộng của BottomAppBar
                        .padding(horizontal = 16.dp), // Tùy chỉnh khoảng cách của Text
                    verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
                    horizontalArrangement = Arrangement.Center // Căn giữa theo chiều ngang
                ) {
                    Text(
                        text = "Tổng tiền: 1.500.000 VND",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    IconButton(onClick = {navController.navigate("ThanhToan")}) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shoping"
                        )
                    }
                }
            }
        }
    ){
        paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
                .background(color = Color.White)
        ){
            items(3) { index ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(400.dp)
                        .height(100.dp)
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, // Căn chỉnh các phần tử con theo chiều dọc
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hum),
                            contentDescription = "Hình ảnh món ăn",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                          Text(
                                text = "Burger Phô Mai",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                           Text(
                                text = "150.000 VND",
                                style = TextStyle(fontSize = 16.sp, color = Color.Black)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically,){
                                IconButton(onClick = {quantity++ },
                                    modifier = Modifier.padding(start = 2.dp)) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowUpward,
                                        contentDescription = "Add"
                                    )
                                }
                                Text(
                                    text = "$quantity",
                                    style = TextStyle(fontSize = 16.sp, color = Color.Black)
                                )
                                IconButton(onClick = { if (quantity > 1) quantity--},
                                    modifier = Modifier.padding(start = 2.dp)) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDownward,
                                        contentDescription = "Giam"
                                    )
                                }
                            }
                        }
                        IconButton(onClick = {},
                            modifier = Modifier.padding(start = 65.dp)
                                .height(20.dp)) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }

                    }
                }
            }
        }
    }
}