package com.example.moneykeeper.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Category::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun CategoriesDao(): CategoriesDao
}