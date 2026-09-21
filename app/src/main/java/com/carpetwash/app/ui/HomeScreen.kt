package com.carpetwash.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onChat: () -> Unit, onLogout: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("لوحة التحكم") }, actions = { IconButton(onLogout) { Icon(Icons.Default.ExitToApp, "خروج") } }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            Text("مرحبًا بك في نظام غسيل السجاد", style = MaterialTheme.typography.titleLarge)
            Row(Modifier.fillMaxWidth().padding(top = 18.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatCard("الطلبات", "0", Modifier.weight(1f))
                StatCard("العمال", "0", Modifier.weight(1f))
                StatCard("الجاهزة", "0", Modifier.weight(1f))
            }
            Text("الأقسام", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 28.dp, bottom = 8.dp))
            OutlinedButton(onClick = {}, Modifier.fillMaxWidth()) { Icon(Icons.Default.People, null); Spacer(Modifier.width(8.dp)); Text("إدارة العمال") }
            OutlinedButton(onClick = {}, Modifier.fillMaxWidth().padding(top = 8.dp)) { Icon(Icons.Default.Person, null); Spacer(Modifier.width(8.dp)); Text("العملاء والطلبات") }
            Button(onClick = onChat, Modifier.fillMaxWidth().padding(top = 8.dp)) { Icon(Icons.Default.Chat, null); Spacer(Modifier.width(8.dp)); Text("الدردشة الخاصة مع العمال") }
        }
    }
}

@Composable private fun StatCard(title: String, value: String, modifier: Modifier) {
    Card(modifier) { Column(Modifier.padding(12.dp)) { Text(title, style = MaterialTheme.typography.labelMedium); Text(value, style = MaterialTheme.typography.headlineSmall) } }
}
