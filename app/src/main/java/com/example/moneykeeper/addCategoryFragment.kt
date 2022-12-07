package com.example.moneykeeper

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.database.CategoriesViewModel
import com.example.moneykeeper.database.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addCategoryFragment : Fragment() {


    private lateinit var myCategoriesViewModel: CategoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)

        myCategoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)

        val saveCategoryButton = view.findViewById<Button>(R.id.saveCategoryButton)
        val categoryName = view.findViewById<EditText>(R.id.categoryName)

        saveCategoryButton.setOnClickListener {
            insertIntoCategories(categoryName.text.toString(), 0, null)
            findNavController().navigate(R.id.action_addCategoryFragment_to_categoriesFragment)
            }



        return view
    }

    private fun insertIntoCategories(name: String, color: Int, icon: ByteArray?) {
        var category = Category(
            0,
            name,
            color,//TODO make color picker
            icon//TODO make icon picker
        )
        if (inputCheck(name)){
            //insert
            myCategoriesViewModel.addCategory(category)
            Toast.makeText(requireContext(), "success: ${category.uid}th category added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String): Boolean {//TODO add color and icon when user is able to choose them
        return !(TextUtils.isEmpty(name))
    }

}