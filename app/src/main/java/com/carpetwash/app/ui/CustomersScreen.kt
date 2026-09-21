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
    Scaffold(topBar = { TopAppBar(title = { Text("العملاء") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }, actions = { IconButton({}) { Icon(Icons.Default.Add, "إضافة") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { items(customers) { customer -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text(customer.name, style = MaterialTheme.typography.titleMedium); Text("الكود: ${customer.code}"); Text("الهاتف: ${customer.phone}") } } } }
    }
}
