package com.example.fastfood_app.Data

import android.content.Context
import android.content.SharedPreferences

class UserSession(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    // Lưu thông tin người dùng vào SharedPreferences
    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putInt("User_ID", user.userId)
        editor.putString("Username", user.username)
        editor.putString("Email", user.email)
        editor.putString("Role", user.role)
        editor.putString("Password", user.pass)
        editor.apply()
    }

    // Lấy thông tin người dùng từ SharedPreferences
    fun getUser(): User? {
        val userId = sharedPreferences.getInt("User_ID", -1)
        if (userId != -1) {
            val username = sharedPreferences.getString("Username", null) ?: ""
            val email = sharedPreferences.getString("Email", null) ?: ""
            val role = sharedPreferences.getString("Role", null) ?: ""
            val pass = sharedPreferences.getString("Password", null) ?: ""
            return User(userId, username, email, role, pass)
        }
        return null
    }

    // Kiểm tra xem người dùng đã đăng nhập hay chưa
    fun isLoggedIn(): Boolean {
        return getUser() != null
    }

    // Đăng xuất người dùng (xóa thông tin người dùng)
    fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}