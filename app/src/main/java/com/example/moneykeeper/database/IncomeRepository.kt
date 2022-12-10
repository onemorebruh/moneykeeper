package com.example.moneykeeper.database

import androidx.lifecycle.LiveData

class IncomeRepository(private var incomeDao: IncomeDao) {
    val readAllData: LiveData<List<Income>> = incomeDao.getAll()

    suspend fun getById(uid: Int){
        incomeDao.getOneById(uid)
    }

    suspend fun addIncome(income: Income){
        incomeDao.insert(income)
    }
}