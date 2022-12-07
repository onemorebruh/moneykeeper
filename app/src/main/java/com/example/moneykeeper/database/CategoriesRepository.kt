package com.example.moneykeeper.database

import androidx.lifecycle.LiveData

class CategoriesRepository(private val categoriesDao: CategoriesDao) {
    val readAllData: LiveData<List<Category>> = categoriesDao.getAll()

    suspend fun addCategory(category: Category){
        categoriesDao.insert(category)
    }
}