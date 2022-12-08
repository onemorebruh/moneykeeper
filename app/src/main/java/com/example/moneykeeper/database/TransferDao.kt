package com.example.moneykeeper.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TransferDao {

    @Query("SELECT * FROM transfer")
    fun getAll(): LiveData<List<Transfer>>

    @Query("SELECT * FROM transfer WHERE uid = :uid LIMIT 1")
    fun getOneById(uid: Int): Transfer

    @Insert
    suspend fun insert(vararg : Transfer)

    @Delete
    fun delete(category: Transfer)
}