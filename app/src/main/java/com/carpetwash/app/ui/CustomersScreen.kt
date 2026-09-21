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
import com.carpetwash.app.data.Customer

@Composable
fun CustomersScreen(onBack: () -> Unit) {
    val customers = remember { mutableStateListOf(Customer(1, "عميل تجريبي", "0920000000", "C-001")) }
    var showAdd by remember { mutableStateOf(false) }
    Scaffold(topBar = { TopAppBar(title = { Text("العملاء") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }, actions = { IconButton({ showAdd = true }) { Icon(Icons.Default.Add, "إضافة") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { items(customers) { c -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text(c.name, style = MaterialTheme.typography.titleMedium); Text("الكود: ${c.code}"); Text("الهاتف: ${c.phone}") } } } }
    }
    if (showAdd) AddCustomerDialog({ showAdd = false }) { name, phone -> customers.add(Customer(customers.size + 1, name, phone, "C-${(customers.size + 1).toString().padStart(3, '0')}")); showAdd = false }
}

@Composable
private fun AddCustomerDialog(onDismiss: () -> Unit, onSave: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }; var phone by remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("إضافة عميل") }, text = { Column { OutlinedTextField(name, { name = it }, label = { Text("اسم العميل") }, singleLine = true); OutlinedTextField(phone, { phone = it }, label = { Text("رقم الهاتف") }, singleLine = true, modifier = Modifier.padding(top = 8.dp)) } }, confirmButton = { Button({ if (name.isNotBlank()) onSave(name, phone) }, enabled = name.isNotBlank()) { Text("حفظ") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}
