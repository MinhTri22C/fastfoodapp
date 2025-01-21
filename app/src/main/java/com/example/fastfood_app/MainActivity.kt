package com.example.fastfood_app


import OrderDetail
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fastfood_app.Account.AccountScreen
import com.example.fastfood_app.Account.AddnewAddress
import com.example.fastfood_app.Account.AddressScreen
import com.example.fastfood_app.Account.Danhan
import com.example.fastfood_app.Account.OrderDetailScreen
import com.example.fastfood_app.Account.OrderScreen
import com.example.fastfood_app.Account.PasswordSettingScreen
import com.example.fastfood_app.Account.ProfileScreen
import com.example.fastfood_app.Account.dahuy
import com.example.fastfood_app.Admin.Customerorder
import com.example.fastfood_app.Admin.HomeAdminScreen
import com.example.fastfood_app.FavoritesScreen.FavoritesScreen
import com.example.fastfood_app.HomeScreen.ProductTypeScreen
import com.example.fastfood_app.RegisterScreen.HomeScreen

import com.example.fastfood_app.ProductDetail.productDetail

import com.example.fastfood_app.RegisterScreen.RegisterScreen
import com.example.fastfood_app.ScreenLogin.ScreenLogin
import com.example.fastfood_app.Shopping.ShoppingScreen
import com.example.fastfood_app.Shopping.ThanhToan
import com.example.fastfood_app.ui.theme.Fastfood_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fastfood_appTheme {
               MyApp();
                }
            }
        }
    }
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Login" +
                ""
    ) {
//        composable("Test"){ view(viewModels) }
        composable("Login") { ScreenLogin(navController) }
        composable("Register") { RegisterScreen(navController) }
        composable("Shopping"){ ShoppingScreen(navController) }
        composable("Home") { HomeScreen(navController) }
        composable("ProductDetail"){ productDetail(navController) }
        composable("Account"){ AccountScreen(navController) }
        composable("Profile"){ ProfileScreen(navController) }
        composable("AddressScreen"){ AddressScreen(navController) }
        composable("AddnewAddress"){ AddnewAddress(navController) }
        composable("PasswordSetting"){ PasswordSettingScreen(navController) }
        composable("Homeadmin"){HomeAdminScreen(navController)}
        composable("Customerorder"){ Customerorder(navController) }
        composable("Favorites"){ FavoritesScreen(navController) }
        composable("ThanhToan"){ ThanhToan(navController) }
        composable("ProductType"){ ProductTypeScreen(navController) }
        composable("CustomerOrder") { Customerorder(navController) }
        composable("OrderDetail/{donHangId}") { backStackEntry ->
            val donHangId = backStackEntry.arguments?.getString("donHangId")?.toIntOrNull() ?: 0
            OrderDetail(navController, donHangId)
        }
        composable("OrderScreen"){OrderScreen(navController)}
        composable("Danhan"){Danhan(navController)}
        composable("OrderScreen"){OrderScreen(navController)}
        composable("dahuy"){ dahuy(navController) }
        composable("OrderDetailScreen"){OrderDetailScreen(navController)}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyApp() {

    val navController = rememberNavController()
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationComponent(navController)
        }
    }
}


