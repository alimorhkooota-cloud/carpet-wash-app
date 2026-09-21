package com.carpetwash.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.carpetwash.app.R

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painterResource(R.drawable.owner_placeholder), "صورة المالك", Modifier.size(130.dp).clip(CircleShape))
        Text("نظام غسيل السجاد", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 18.dp))
        Text("تسجيل دخول المالك أو العامل", modifier = Modifier.padding(bottom = 20.dp))
        OutlinedTextField(phone, { phone = it }, label = { Text("رقم الهاتف") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(pin, { pin = it }, label = { Text("الرمز السري") }, leadingIcon = { Icon(Icons.Default.Lock, null) }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth().padding(top = 10.dp), singleLine = true)
        Button(onClick = onLogin, modifier = Modifier.fillMaxWidth().padding(top = 18.dp), enabled = phone.isNotBlank() && pin.isNotBlank()) { Text("دخول") }
        Text("نسخة تجريبية تعمل محليًا", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 14.dp))
    }
}
