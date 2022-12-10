package com.example.moneykeeper.database

import androidx.lifecycle.LiveData

class CategoriesRepository(private val categoriesDao: CategoriesDao) {
    val readAllData: LiveData<List<Category>> = categoriesDao.getAll()

    suspend fun getByName(name: String){
        categoriesDao.getOneByName(name)
    }

    suspend fun addCategory(category: Category){
        categoriesDao.insert(category)
    }
}