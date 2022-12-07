package com.example.moneykeeper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun CategoriesDao(): CategoriesDao

    companion object{
        @Volatile
        private var INSTANCE: com.example.moneykeeper.database.Database? = null

        fun getDatabase(context: Context): com.example.moneykeeper.database.Database{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.moneykeeper.database.Database::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}