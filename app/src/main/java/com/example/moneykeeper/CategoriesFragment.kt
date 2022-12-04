package com.example.moneykeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moneykeeper.database.CategoriesDao
import com.example.moneykeeper.database.Database
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CategoriesFragment : Fragment() {

    var addButton: FloatingActionButton? = null
    private var listLayout: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        addButton = view!!.findViewById(R.id.addButton)
        listLayout = view.findViewById<LinearLayout>(R.id.listLayout)
        lifecycleScope.launch {
            withContext(Dispatchers.IO){

                var db = Room.databaseBuilder(
                    requireContext(),
                    Database::class.java, "categories"
                ).build()

                categoriesDao = db.CategoriesDao()
                var categories = categoriesDao!!.getAll()
                categories.forEach {
                    val textView = TextView(context)
                    textView.text = it.name
                    withContext(Dispatchers.Main){
                        listLayout!!.addView(textView)
                    }
                }
            }
        }

        addButton!!.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_addCategoryFragment)
        }

        return view
    }

    companion object{
        var categoriesDao: CategoriesDao? = null
    }

}