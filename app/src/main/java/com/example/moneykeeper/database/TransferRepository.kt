package com.example.moneykeeper.database

import androidx.lifecycle.LiveData

class TransferRepository(private val transferDao: TransferDao) {
    val readAllData: LiveData<List<Transfer>> = transferDao.getAll()

    suspend fun addTransfer(transfer: Transfer){
        transferDao.insert(transfer)
    }
}