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
    Scaffold(topBar = { TopAppBar(title = { Text("العمال") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }, actions = { IconButton { showAdd = true } { Icon(Icons.Default.Add, "إضافة") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { items(workers) { worker -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text(worker.name, style = MaterialTheme.typography.titleMedium); Text("الكود: ${worker.code} | الهاتف: ${worker.phone}"); Text(if (worker.active) "نشط" else "غير نشط") } } } }
    }
    if (showAdd) AlertDialog(onDismissRequest = { showAdd = false }, title = { Text("إضافة عامل") }, text = { Text("سيتم ربط الإضافة بقاعدة البيانات في المرحلة التالية.") }, confirmButton = { Button({ showAdd = false }) { Text("حسنًا") } })
}
