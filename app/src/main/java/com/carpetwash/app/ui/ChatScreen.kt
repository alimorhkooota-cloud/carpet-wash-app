package com.carpetwash.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(onBack: () -> Unit) {
    var message by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf("مرحبًا، هذه دردشة خاصة مع العامل") }
    Scaffold(topBar = { TopAppBar(title = { Text("الدردشة مع العمال") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }) }) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            Text("اختر العامل من القائمة لاحقًا. هذه نسخة أولية محلية.", style = MaterialTheme.typography.bodySmall)
            LazyColumn(Modifier.weight(1f).fillMaxWidth().padding(vertical = 12.dp)) { items(messages) { Text(it, Modifier.fillMaxWidth().padding(8.dp), style = MaterialTheme.typography.bodyLarge) } }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(message, { message = it }, Modifier.weight(1f), placeholder = { Text("اكتب رسالة") }, singleLine = true)
                Button(onClick = { if (message.isNotBlank()) { messages.add(message); message = "" } }) { Text("إرسال") }
            }
        }
    }
}
