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
import com.example.moneykeeper.database.IncomeListAdapter
import com.example.moneykeeper.database.IncomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncomesFragment : Fragment() {

    private var addButton: FloatingActionButton? = null
    private var listView: RecyclerView? = null
    private lateinit var myIncomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_incomes, container, false)

        val adapter = IncomeListAdapter()
        addButton = view!!.findViewById(R.id.addNewIncomeButton)
        //recycler view
        listView = view.findViewById(R.id.incomesView)
        listView!!.adapter = adapter
        listView!!.layoutManager = LinearLayoutManager(requireContext())

        //view model
        myIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]
        myIncomeViewModel.readAllData.observe(viewLifecycleOwner, Observer{ income ->
            adapter.setData(income)
        })

        addButton!!.setOnClickListener {
            findNavController().navigate(R.id.action_incomesFragment_to_addIncomeFragment)
        }

        return view
    }


}