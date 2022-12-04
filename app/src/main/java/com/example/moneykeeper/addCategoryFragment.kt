package com.example.moneykeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.database.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addCategoryFragment : Fragment() {





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)

        val saveCategoryButton = view.findViewById<Button>(R.id.saveCategoryButton)
        val categoryName = view.findViewById<EditText>(R.id.categoryName)

        saveCategoryButton.setOnClickListener {
            var category = Category(
                0,
                categoryName.text.toString(),
                0,//TODO make color picker
                null//TODO make icon picker
            )
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    //TODO add category to db
                    CategoriesFragment.categoriesDao!!.insert(category)
                }
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_addCategoryFragment_to_categoriesFragment)
                }
            }

        }

        return view
    }
    companion object {
        var selectedColor : View? = null
        var selectedIcon : View? = null
    }
}