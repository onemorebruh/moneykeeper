package com.example.moneykeeper.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Category>>
    private val repository: CategoriesRepository

    init {
        val categoriesDao = Database.getDatabase(application).CategoriesDao()
        repository = CategoriesRepository(categoriesDao)
        readAllData = repository.readAllData
    }

    fun addCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(category)
        }
    }
}