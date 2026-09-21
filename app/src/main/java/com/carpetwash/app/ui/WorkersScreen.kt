package com.carpetwash.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carpetwash.app.data.Worker

@Composable
fun WorkersScreen(onBack: () -> Unit) {
    val workers = remember { mutableStateListOf(Worker(1, "عامل تجريبي", "0910000000", "W-001")) }
    var showAdd by remember { mutableStateOf(false) }
    Scaffold(topBar = { TopAppBar(title = { Text("العمال") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }, actions = { IconButton({ showAdd = true }) { Icon(Icons.Default.Add, "إضافة") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(workers) { worker ->
                Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text(worker.name, style = MaterialTheme.typography.titleMedium); Text("الكود: ${worker.code}"); Text("الهاتف: ${worker.phone}"); Text(if (worker.active) "الحالة: نشط" else "الحالة: غير نشط") } }
            }
        }
    }
    if (showAdd) AddWorkerDialog(onDismiss = { showAdd = false }, onSave = { name, phone -> workers.add(Worker(workers.size + 1, name, phone, "W-${(workers.size + 1).toString().padStart(3, '0')}")); showAdd = false })
}

@Composable
private fun AddWorkerDialog(onDismiss: () -> Unit, onSave: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }; var phone by remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("إضافة عامل") }, text = { Column { OutlinedTextField(name, { name = it }, label = { Text("اسم العامل") }, singleLine = true); OutlinedTextField(phone, { phone = it }, label = { Text("رقم الهاتف") }, singleLine = true, modifier = Modifier.padding(top = 8.dp)) } }, confirmButton = { Button(onClick = { if (name.isNotBlank()) onSave(name, phone) }, enabled = name.isNotBlank()) { Text("حفظ") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}
