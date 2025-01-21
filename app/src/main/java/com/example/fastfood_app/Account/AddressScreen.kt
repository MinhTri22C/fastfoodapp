package com.example.fastfood_app.Account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fastfood_app.R

@Composable
fun AddressScreen(navController:NavHostController) {
    var addresses by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val newAddress = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Pair<String, String>>("newAddress")
    newAddress?.observe(LocalLifecycleOwner.current) { address ->
        addresses = addresses + address // Cập nhật danh sách địa chỉ
    }
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
                    text = "Address",
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
            if (addresses.isEmpty()) {
                Text("No addresses yet.", modifier = Modifier.padding(16.dp))
            } else {
                addresses.forEach { (name, address) ->
                    AddressItem(name = name, address = address)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("AddnewAddress") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Add New Address",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AddressItem(name: String, address: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.homeicon),
            contentDescription = "Address Icon",
            tint = Color(0xFFFF6F00),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = address,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        RadioButton(
            selected = false,
            onClick = { /* TODO: Handle selection */ }
        )
    }
    Divider(color = Color.Gray, thickness = 0.5.dp)
}

