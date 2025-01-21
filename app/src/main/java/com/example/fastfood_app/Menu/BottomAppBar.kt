package com.example.fastfood_app.Menu

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BottomAppBar(navController: NavHostController){
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation(
        modifier = Modifier.height(60.dp),
        backgroundColor = Color.Red,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 12.sp) },
            selected = selectedItem == 0,
            onClick = {navController.navigate("Home") }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "ShoppingCart") },
            label = { Text("Cart", fontSize = 12.sp) },
            selected = selectedItem == 1,
            onClick = {navController.navigate("Shopping")}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") },
            label = { Text("Favorite", fontSize = 12.sp) },
            selected = selectedItem == 2,
            onClick = { navController.navigate("Favorites") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            label = { Text("Account", fontSize = 12.sp) },
            selected = selectedItem == 3,
            onClick = {navController.navigate("Account")}
        )
    }
}
