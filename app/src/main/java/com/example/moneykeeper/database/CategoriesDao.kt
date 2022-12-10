package com.example.moneykeeper.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoriesDao {

    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM category")
    fun getListOfAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid = :uid LIMIT 1")
    fun getOneById(uid: Int): Category

    @Query("SELECT * FROM category WHERE name = :name LIMIT 1")
    fun getOneByName(name: String): Category

    @Insert
    suspend fun insert(vararg : Category)

    @Delete
    fun delete(category: Category)
}