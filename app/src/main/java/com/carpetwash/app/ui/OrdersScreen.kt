package com.carpetwash.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carpetwash.app.data.CarpetOrder

@Composable
fun OrdersScreen(onBack: () -> Unit) {
    val orders = remember { listOf(CarpetOrder(1, "ORD-001", "عميل تجريبي", 2, "قيد الغسيل", "0")) }
    Scaffold(topBar = { TopAppBar(title = { Text("الطلبات") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { items(orders) { order -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text("طلب ${order.code}", style = MaterialTheme.typography.titleMedium); Text("العميل: ${order.customer}"); Text("عدد القطع: ${order.pieces}"); Text("الحالة: ${order.status}"); Text("المبلغ: ${order.total}") } } } }
    }
}
