package com.example.moneykeeper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.R
import com.example.moneykeeper.database.CategoriesDao
import com.example.moneykeeper.database.CategoriesListAdapter
import com.example.moneykeeper.database.CategoriesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoriesFragment : Fragment() {

    private var addButton: FloatingActionButton? = null
    private var listView: RecyclerView? = null
    private lateinit var myCategoriesViewModel: CategoriesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        val adapter = CategoriesListAdapter()
        addButton = view!!.findViewById(R.id.addButton)
        //recycler view
        listView = view.findViewById(R.id.listView)
        listView!!.adapter = adapter
        listView!!.layoutManager = LinearLayoutManager(requireContext())

        //view model
        myCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        myCategoriesViewModel.readAllData.observe(viewLifecycleOwner, Observer{ category ->
            adapter.setData(category)
        })

        addButton!!.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_addCategoryFragment)
        }

        return view
    }


    companion object{
        var categoriesDao: CategoriesDao? = null
    }

}