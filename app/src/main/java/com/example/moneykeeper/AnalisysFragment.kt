package com.example.moneykeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.database.CategoriesViewModel
import com.example.moneykeeper.database.TransferListAdapter
import com.example.moneykeeper.database.TransferViewModel

class AnalisysFragment : Fragment() {

    private var listView: RecyclerView? = null
    private lateinit var myTransferViewModel: TransferViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_analisys, container, false)
        val adapter = TransferListAdapter()
        listView = view.findViewById(R.id.transfersView)
        myTransferViewModel = ViewModelProvider(this)[TransferViewModel::class.java]
        myTransferViewModel.readAllData.observe(viewLifecycleOwner, Observer { transfer ->
            adapter.setData(transfer)
        })
        listView!!.adapter = adapter
        listView!!.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}