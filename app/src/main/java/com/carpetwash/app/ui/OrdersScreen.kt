package com.carpetwash.app.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.carpetwash.app.data.CarpetOrder

@Composable
fun OrdersScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val orders = remember { mutableStateListOf(CarpetOrder(1, "ORD-001", "عميل تجريبي", 2, "قيد الغسيل", "0")) }
    var showAdd by remember { mutableStateOf(false) }
    Scaffold(topBar = { TopAppBar(title = { Text("الطلبات") }, navigationIcon = { IconButton(onBack) { Icon(Icons.Default.ArrowBack, "رجوع") } }, actions = { IconButton({ showAdd = true }) { Icon(Icons.Default.Add, "إضافة") } }) }) { pad ->
        LazyColumn(Modifier.padding(pad).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(orders) { order ->
                Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text("طلب ${order.code}", style = MaterialTheme.typography.titleMedium); Text("العميل: ${order.customer}"); Text("عدد القطع: ${order.pieces}"); Text("الحالة: ${order.status}"); Text("المبلغ: ${order.total}"); Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) { IconButton({ val text = "مرحبًا، طلبك ${order.code} جاهز للاستلام ✅"; context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/?text=${Uri.encode(text)}"))) }) { Icon(Icons.Default.Share, "واتساب") } } } }
            }
        }
    }
    if (showAdd) AddOrderDialog({ showAdd = false }) { customer, pieces, total -> orders.add(CarpetOrder(orders.size + 1, "ORD-${(orders.size + 1).toString().padStart(3, '0')}", customer, pieces, "جديد", total)); showAdd = false }
}

@Composable
private fun AddOrderDialog(onDismiss: () -> Unit, onSave: (String, Int, String) -> Unit) {
    var customer by remember { mutableStateOf("") }; var pieces by remember { mutableStateOf("1") }; var total by remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("طلب جديد") }, text = { Column { OutlinedTextField(customer, { customer = it }, label = { Text("اسم العميل") }, singleLine = true); OutlinedTextField(pieces, { pieces = it.filter(Char::isDigit) }, label = { Text("عدد القطع") }, singleLine = true, modifier = Modifier.padding(top = 8.dp)); OutlinedTextField(total, { total = it }, label = { Text("المبلغ") }, singleLine = true, modifier = Modifier.padding(top = 8.dp)) } }, confirmButton = { Button({ if (customer.isNotBlank()) onSave(customer, pieces.toIntOrNull() ?: 1, total) }, enabled = customer.isNotBlank()) { Text("حفظ") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}
