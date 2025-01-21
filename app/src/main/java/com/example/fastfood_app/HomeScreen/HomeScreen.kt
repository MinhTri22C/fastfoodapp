package com.example.fastfood_app.RegisterScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.fastfood_app.Data.Product
import com.example.fastfood_app.R
import com.example.fastfood_app.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,viewModel: ProductViewModel= viewModel()) {
    viewModel.getAllProducts()
    viewModel.getProductsCagHum()
    viewModel.getProductsCag_My()
    viewModel.getProductsCag_Pizza()
    viewModel.getProductsCag_Ga()
    viewModel.getProductsCag_Nuoc()
    val productList by viewModel.listProduct.observeAsState(emptyList())
    val productListCagHum by viewModel.listProductCagHum.observeAsState(emptyList())
    val productList_My by viewModel.listProductCag_My.observeAsState(emptyList())
    val productcag_Pizza by viewModel.listProductCag_Pizza.observeAsState(emptyList())
    val productcag_Ga by viewModel.listProductCag_Ga.observeAsState(emptyList())
    val productcag_Nuoc by viewModel.listProductCag_Nuoc.observeAsState(emptyList())

    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    LaunchedEffect(productList,productListCagHum,productList_My) {
        Log.d("ProductScreen", "Product List: $productList")
        Log.d("productListCagHum", "productListCagHum List: $productListCagHum")
        Log.d("productList_My", "productList_My List: $productList_My")
        Log.d("productcag_Pizza", "productcag_Pizza List: $productcag_Pizza")
    }

    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    com.example.fastfood_app.Menu.TopAppBar()
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Nút tìm kiếm
                        IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                            Icon(
                                imageVector = if (isSearchVisible) Icons.Default.Close else Icons.Default.Search,
                                contentDescription = if (isSearchVisible) "Close Search" else "Open Search",
                                tint = Color.White
                            )
                        }

                        // Hiển thị ô tìm kiếm nếu trạng thái là "hiện"
                        if (isSearchVisible) {
                            BasicTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                modifier = Modifier
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .fillMaxWidth(0.8f),
                                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                                decorationBox = { innerTextField ->
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            text = "Search here...",
                                            color = Color.Gray,
                                            style = LocalTextStyle.current
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            com.example.fastfood_app.Menu.BottomAppBar(navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding())

                .background(color = Color.White)
        ) {
            item {
                // Phần LazyRow đầu tiên
                LazyRow(
                    modifier = Modifier.padding(top = 25.dp)
                ) {
                    items(4) { index ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.banner1),
                                    contentDescription = "Hình ảnh banner",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }



            item {
                Spacer(modifier = Modifier.height(25.dp))
                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Loại sản phẩm ",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            item {
                // LazyRow thứ 2
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    item {
                        Card(
                            onClick = {navController.navigate("ProductType")},
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.hum),
                                    contentDescription = "Hình ảnh loại",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    item {
                        Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                            .padding(horizontal = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.myy),
                                contentDescription = "Hình ảnh món ăn",
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    }
                    item {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.khoaitay),
                                    contentDescription = "Hình ảnh món ăn",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    item {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.garan),
                                    contentDescription = "Hình ảnh món ăn",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    item {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pizza),
                                    contentDescription = "Hình ảnh món ăn",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    item {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.nuoc),
                                    contentDescription = "Hình ảnh món ăn",
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }




            item {
                Spacer(modifier = Modifier.height(25.dp))
                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Sản phảm khuyến mãi",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            item {
                // LazyRow thứ 2
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productList.size) { index ->
                        ProductItem(product = productList[index])
                    }
                }
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

                // Thêm Text cho phần LazyRow thứ 2
                Box(
                    modifier = Modifier.fillMaxWidth() // Đảm bảo chiếm toàn bộ chiều rộng
                ) {
                    Text(
                        text = "Tất cả sản phẩm",
                        color = Color.Black,
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.align(Alignment.Center) // Căn giữa văn bản
                    )
                }
            }//
            item {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))

                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Burger",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }//
            item {
                // LazyRow thứ 3
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productListCagHum.size) { index ->
                        ProductItem(product = productListCagHum[index])
                        }
                    }
                }



            item {
                Spacer(modifier = Modifier.height(25.dp))

                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Mỳ",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }//


            item {
                // LazyRow thứ 3
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productList_My.size) { index ->

                                ProductItem(product = productList_My[index])
                    }
                }
            }//



            item {
                Spacer(modifier = Modifier.height(25.dp))

                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Pizza",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }//
            item {
                // LazyRow thứ 3
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productcag_Pizza.size) { index ->
                      ProductItem(product = productcag_Pizza[index])
                    }
                }
            }//




            item {
                Spacer(modifier = Modifier.height(25.dp))

                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = "Gà Rán",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }//
            item {
                // LazyRow thứ 3
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productcag_Ga.size) { index ->
ProductItem(product = productcag_Ga[index]  )
                    }
                }
            }//

            item {
                Spacer(modifier = Modifier.height(25.dp))

                // Thêm Text cho phần LazyRow thứ 2
                Text(
                    text = " Nước uống",
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }//
            item {
                // LazyRow thứ 3
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    items(productcag_Nuoc.size) { index ->
               ProductItem(product = productcag_Nuoc[index])
                    }
                }
            }//
        }
    }
}



@Composable
fun ProductItem(product: Product) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(170.dp)
            .height(170.dp)
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Căn giữa nội dung trong Box
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(5.dp)
                ) {
                    AsyncImage(
                        model = product.imageUrl,  // URL của ảnh
                        contentDescription = "Hình ảnh món ăn",
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = product.name,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "${product.price} VND",
                            style = TextStyle(fontSize = 16.sp, color = Color.Black)
                        )
                    }
                }
            }
        }
    }
}