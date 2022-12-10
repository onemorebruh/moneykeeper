package com.example.moneykeeper.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Income>>
    private val repository: IncomeRepository

    init {
        val incomeDao = Database.getDatabase(application).IncomeDao()
        repository = IncomeRepository(incomeDao)
        readAllData = repository.readAllData
    }

    fun addIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncome(income)
        }
    }
}