package com.example.moneykeeper.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface IncomeDao {

    @Query("SELECT * FROM income")
    fun getAll(): LiveData<List<Income>>


    @Query("SELECT * FROM income WHERE uid = :uid LIMIT 1")
    fun getOneById(uid: Int): Income

    @Query("SELECT * FROM income WHERE name = :name LIMIT 1")
    fun getOneByName(name: String): Income

    @Insert
    suspend fun insert(vararg : Income)

    @Delete
    fun delete(income: Income)
}