package com.example.moneykeeper.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoriesDao {

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid = :uid LIMIT 1")
    fun getOneById(uid: Int): Category

    @Insert
    fun insert(vararg : Category)

    @Delete
    fun delete(category: Category)
}