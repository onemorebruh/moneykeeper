package com.example.moneykeeper.pieChart

import androidx.compose.ui.graphics.Color

data class Transfer(
    var uid: Int,
    var name: String,
    var value: Int,
    var category: Int,
    var color: Color,
    var icon: ByteArray
)