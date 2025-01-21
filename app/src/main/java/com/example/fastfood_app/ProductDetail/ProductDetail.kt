package com.example.fastfood_app.ProductDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
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
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fastfood_app.Menu.TopAppBar
import com.example.fastfood_app.R
import com.example.fastfood_app.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun productDetail(navController: NavHostController,viewModel: ProductViewModel = viewModel()) {
    val checkedState1 = remember { mutableStateOf(true) }
    val checkedState2 = remember { mutableStateOf(false) }
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
                            text = "Chi tiết sản phẩm",
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
                .background(color = Color.White)
        ) {
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.myy),
                    contentDescription = "Product Image", // Consider providing a more specific description
                    modifier = Modifier.fillMaxSize().height(90.dp)
                )
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Column(modifier = Modifier.fillMaxSize())
                {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                        text = "Mỳ ý sốt cay",
                        color = Color.Black,
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Black),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                        IconButton(onClick = {},
                            modifier = Modifier.padding(start = 200.dp)) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Add"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(), // Chiếm toàn bộ chiều rộng của màn hình
                        verticalAlignment = Alignment.CenterVertically, // Căn chỉnh các phần tử theo chiều dọc
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Loại: ",
                            color = Color.Black,
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Checkbox(
                            checked = checkedState1.value,
                            onCheckedChange = { checkedState1.value = it },
                            enabled = false
                        )
                        Text(
                            text = "Cay",
                            color = Color.Black,
                            style = TextStyle(fontSize = 18.sp)
                        )
                        Checkbox(
                            checked = checkedState2.value,
                            onCheckedChange = { checkedState2.value = it },
                            enabled = false
                        )
                        Text(
                            text = "Không Cay",
                            color = Color.Black,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }

                }
            }
            item {
                val Size = remember { mutableStateOf("Size") }
                Column(modifier = Modifier.padding(8.dp)) {
                   Text(
                        text = "Size :",
                        color = Color.Black,
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,  // Centers children horizontally
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp) ,
                            horizontalArrangement = Arrangement.Center,  // Centers children horizontally
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = Size.value == "Nhỏ",
                                onClick = { Size.value = "Nhỏ" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            androidx.compose.material3.Text(
                                text = "Nhỏ",
                                color = Color.Black
                            )
                        }

                        // Thanh toán bằng thẻ tín dụng
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),horizontalArrangement = Arrangement.Center,  // Centers children horizontally
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = Size.value == "Vừa",
                                onClick = { Size.value = "Vừa" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            androidx.compose.material3.Text(
                                text = "Vừa",
                                color = Color.Black
                            )
                        }

                        // Thanh toán qua ví điện tử
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),horizontalArrangement = Arrangement.Center,  // Centers children horizontally
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = Size.value == "Lớn",
                                onClick = { Size.value = "Lớn" },
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            androidx.compose.material3.Text(
                                text = "Lớn",
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            item {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Số lượng  ",
                        color = Color.Black,
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    IconButton(
                        onClick = {quantity++ },
                        modifier = Modifier.padding(start = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = "Add"
                        )
                    }
                    Text(
                        text = "$quantity",
                        style = TextStyle(fontSize = 16.sp, color = Color.Black)
                    )
                    IconButton(
                        onClick = { if (quantity > 1) quantity--},
                        modifier = Modifier.padding(start = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDownward,
                            contentDescription = "Giam"
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(), // Chiếm toàn bộ chiều rộng của màn hình
                    verticalAlignment = Alignment.CenterVertically, // Căn chỉnh các phần tử theo chiều dọc
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = "100000000 VNĐ ",
                        color = Color.Red,
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    IconButton(
                        onClick = {navController.navigate("Shopping")},
                        modifier = Modifier.padding(start = 180.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.Red
                        )
                    }
                }
            }
            item {
                Text(
                    text = " là món ăn đặc biệt, được chế biến từ những nguyên liệu tươi ngon và chọn lọc kỹ càng. Bánh mì được nướng giòn với lớp vỏ ngoài vàng ươm, bên trong là những lát thịt nướng thơm ngon, mềm mại. Kết hợp cùng rau sống tươi ngon, dưa leo giòn và sốt đặc biệt, mang đến một hương vị hấp dẫn khó quên.\n" +

                            "Sản phẩm không chỉ cung cấp năng lượng cho ngày dài làm việc mà còn là một lựa chọn hoàn hảo cho những ai yêu thích hương vị đậm đà, dễ ăn và đầy đủ dinh dưỡng. Món ăn này phù hợp cho bữa sáng, bữa trưa hoặc làm món ăn nhẹ khi bạn cần bổ sung năng lượng. ",
                    color = Color.Black,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {

                Text(
                    text = "Đánh giá",
                    color = Color.Black,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 140.dp)// Căn giữa văn bản
                )
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
        }
    }
}
