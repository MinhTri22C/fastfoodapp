package com.example.fastfood_app.Shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ThanhToan(navController:NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 0.dp), // Adjust padding here
                    verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text("Thanh toán", color = Color.White) } },
                modifier = Modifier.height(70.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )
            )
        },

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding(),

                )

                .background(color = Color.White)
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Địa chỉ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "102 Trần Trọng Cung, Phường Tân Thuận Đông,Quận 7, Thành Phố Hồ Chí Minh",
                        color = Color.Black,
                    )
                }
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Sản phẩm",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                }
                Column(modifier = Modifier.padding(0.dp))
                {
                    LazyRow(modifier = Modifier.fillMaxSize()) {
                        items(10) { index ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(100.dp)
                                    .padding(horizontal = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ){
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
                                        Text(
                                            text = "So Luong:10",
                                            style = TextStyle(fontSize = 16.sp, color = Color.Black)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Khuyến mãi",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Mừng năm mới",
                        color = Color.Black,
                    )
                }
            }
            item {
                val selectedPaymentMethod = remember { mutableStateOf("TienMat") }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Phương thức thanh toán",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp) ,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            RadioButton(
                                selected = selectedPaymentMethod.value == "TienMat",
                                onClick = { selectedPaymentMethod.value = "TienMat" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            Text(text = "Thanh toán bằng tiền mặt \n VND",
                                color = Color.Black)
                        }

                        // Thanh toán bằng thẻ tín dụng
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedPaymentMethod.value == "TheTinDung",
                                onClick = { selectedPaymentMethod.value = "TheTinDung" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            Text(text = "Thanh toán bằng thẻ tín dụng \nCard Visa",
                                color = Color.Black)
                        }

                        // Thanh toán qua ví điện tử
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedPaymentMethod.value == "ViDienTu",
                                onClick = { selectedPaymentMethod.value = "ViDienTu" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            Text(text = "Thanh toán qua ví điện tử \nMoMo",
                                color = Color.Black)
                        }
                    }
                }
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Chi tiết thanh toán",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier.padding(vertical = 8.dp) ,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(
                        text = "Tiền sản phẩm: ",
                        color = Color.Black,
                    )
                        Text(
                        text = " 100000",
                        color = Color.Black
               )
                    }
                    Row(modifier = Modifier.padding(vertical = 8.dp) ,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(
                            text = "Khuyến mãi: ",
                            color = Color.Black,
                        )
                        Text(
                            text = " 10%",
                            color = Color.Black,

                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 8.dp) ,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(
                            text = "Tổng tiền sản phẩm: ",
                            color = Color.Black,
                        )
                        Text(
                            text = " 90000",
                            color = Color.Black,

                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 8.dp) ,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(
                            text = "Phí giao hàng: ",
                            color = Color.Black,
                        )
                        Text(
                            text = " 9000",
                            color = Color.Black,

                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 8.dp) ,
                        horizontalArrangement = Arrangement.Start)
                    {
                        Text(
                            text = "Tổng thanh toán: ",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )

                        )
                        Text(
                            text = " 99000 VNĐ",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )
                        )
                    }
                }
            }
            item {
               Row (horizontalArrangement = Arrangement.SpaceBetween, // Căn chỉnh các phần tử con theo chiều dọc
                   modifier = Modifier.padding(5.dp)){
                   Button(onClick = {navController.navigate("Home")},
                   modifier = Modifier.fillMaxWidth(),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color.Red, // Màu nền nút, thay bằng màu mong muốn
                       contentColor = Color.White // Màu của văn bản trong nút
                   )) {
                   Text(text = "Đặt hàng")
               } }
            }
        }
    }
}