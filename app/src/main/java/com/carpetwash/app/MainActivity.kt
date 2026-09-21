package com.carpetwash.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carpetwash.app.ui.LoginScreen
import com.carpetwash.app.ui.HomeScreen
import com.carpetwash.app.ui.ChatScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CarpetWashApp() }
    }
}

@Composable
private fun CarpetWashApp() {
    val nav = rememberNavController()
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        NavHost(navController = nav, startDestination = "login") {
            composable("login") { LoginScreen { nav.navigate("home") { popUpTo("login") { inclusive = true } } } }
            composable("home") { HomeScreen(onChat = { nav.navigate("chat") }, onLogout = { nav.navigate("login") { popUpTo(0) } }) }
            composable("chat") { ChatScreen(onBack = { nav.popBackStack() }) }
        }
    }
}
