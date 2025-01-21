package com.example.fastfood_app.RegisterScreen

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fastfood_app.R
import com.example.fastfood_app.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController,viewModel: ProductViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    val regSuccess by viewModel.reginSuccess.observeAsState(false)
    var errorMessage by remember { mutableStateOf("") }
    val errorMessages by viewModel.errorMessage.observeAsState("")
    val isLoading by viewModel.isLoading.observeAsState(false)
    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailPattern.toRegex())
    }

    fun isValidInput(): Boolean {
        if (fullName.isBlank()) {
            errorMessage = "Tài khoản không hợp lệ"
            return false
        }
        if (user.isBlank()) {
            errorMessage = "Họ tên không được để trống"
            return false
        }
        if (email.isBlank() || !isValidEmail(email)) {
            errorMessage = "Email không hợp lệ"
            return false
        }
        if (password.isBlank()) {
            errorMessage = "Mật khẩu không được để trống"
            return false
        }
        return true
    }
    Scaffold (topBar = {
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
                            contentDescription = "Back",
                            tint = Color.Red
                        )
                    }
                    Text(text = "Trở về",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red)
                }
            },
            actions = {
                // Add more actions here if needed
            },
            modifier = Modifier.height(65.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black
            )
        )
    },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(50))
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 80.dp)
            )
            Text(
                text = "Đăng ký",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )
            Column(
                modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = {fullName=it  },
                    placeholder = { Text("Tài khoản") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
                OutlinedTextField(
                    value = user,
                    onValueChange = {user=it  },
                    placeholder = { Text("Họ tên người dùng") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)

                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { newEmail ->
                        email = newEmail
                        // Kiểm tra email khi người dùng nhập
                        errorMessage = if (isValidEmail(email)) "" else "Email không hợp lệ"
                    },
                    label = { Text("Nhập email") },
                    placeholder = { Text("Vui lòng nhập email") },
                    isError = errorMessage.isNotEmpty(), // Đánh dấu ô nhập liệu có lỗi
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Mật khẩu") },
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
                if(errorMessage.isNotEmpty()){
                    Text(text = errorMessage, color = Color.Red)
                }
                else{
                    if (errorMessages.isNotEmpty()) {
                        errorMessage = "Tài khoản đã tồn tại"
                        Text(text = errorMessage, color = Color.Red)
                    }
                }
                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    onClick = {
                        if (isValidInput()) {

                            viewModel.dangky(user,fullName,password,email)
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Black
                    ),
                    enabled = !isLoading

                ) {
                    Text(text = if (isLoading) "Đang đăng ký..." else "Đăng ký", color = Color.White)
                }
//                if(kiemtra==false){
//                    viewModel.dangky(user,fullName,password,email)
//                }
//                else{
//                    errorMessage = "người dung tồn tại"
//                }
                if(regSuccess){
                    navController.navigate("Login")
                }
            }
        }
    }
}