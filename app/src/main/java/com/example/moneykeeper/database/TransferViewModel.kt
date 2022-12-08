package com.example.moneykeeper.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransferViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Transfer>>
    private val repository: TransferRepository

    init {
        val transferDao = Database.getDatabase(application).TransferDao()
        repository = TransferRepository(transferDao)
        readAllData = repository.readAllData
    }

    fun addTransfer(transfer: Transfer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransfer(transfer)
        }
    }
}