package com.example.fastfood_app.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fastfood_app.R

@Composable
fun ProfileScreen(navController:NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Red, // Màu nền của thanh trên cùng
                contentPadding = PaddingValues(start = 16.dp)
            ) {
                IconButton(onClick = { navController.navigate("Account") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back), // Icon quay lại
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "My profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center
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
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.avata), // Hình đại diện
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(60.dp))
                )
                Icon(
                    painter = painterResource(id = R.drawable.camera), // Icon camera
                    contentDescription = "Change Profile Picture",
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Trường Full Name
            Text(
                text = "Full Name",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {}, // Xử lý khi nhập
                placeholder = { Text(text = "Enter your Full Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Trường Date of Birth
            Text(
                text = "Date of Birth",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {}, // Xử lý khi nhập
                placeholder = { Text(text = "Enter your Date of Birth") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Trường Email
            Text(
                text = "Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {}, // Xử lý khi nhập
                placeholder = { Text(text = "Enter your Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Trường Phone Number
            Text(
                text = "Phone Number",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {}, // Xử lý khi nhập

                placeholder = { Text(text = "Enter your Phone Number") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nút cập nhật
            Button(
                onClick = { /* Xử lý cập nhật */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5722))
            ) {
                Text(
                    text = "Update Profile",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // Nội dung màn hình
            // Bạn có thể thêm OutlinedTextField, Button tại đây
        }
    }
}
