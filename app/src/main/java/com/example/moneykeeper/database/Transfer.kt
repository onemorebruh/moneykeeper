package com.example.moneykeeper.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey


@Entity(tableName = "transfer",
    foreignKeys = [ForeignKey(entity = Category::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("category"),
        onDelete = CASCADE),
    ForeignKey(entity = Income::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("income"),
        onDelete = NO_ACTION)
    ]
)
data class Transfer(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name= "income") val income: String?,
    @ColumnInfo(name = "category_color") val categoryColor: Int// set color from categories
)