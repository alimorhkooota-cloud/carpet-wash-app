package com.carpetwash.app.data

data class Worker(val id: Int, val name: String, val phone: String, val code: String, val active: Boolean = true)
data class Customer(val id: Int, val name: String, val phone: String, val code: String)
data class CarpetOrder(val id: Int, val code: String, val customer: String, val pieces: Int, val status: String, val total: String)
