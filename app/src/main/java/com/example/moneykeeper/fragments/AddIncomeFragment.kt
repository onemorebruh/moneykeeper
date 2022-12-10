package com.example.moneykeeper.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.R
import com.example.moneykeeper.database.Income
import com.example.moneykeeper.database.IncomeViewModel


class AddIncomeFragment : Fragment() {

    private lateinit var myIncomeViewModel: IncomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)

        myIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        val saveIncomeButton = view.findViewById<Button>(R.id.saveIncomeButton)
        val incomeName = view.findViewById<EditText>(R.id.incomeName)

        saveIncomeButton.setOnClickListener {
            insertIntoIncome(incomeName.text.toString(), 0, null)
            findNavController().navigate(R.id.action_addIncomeFragment_to_incomesFragment)
        }

        return view
    }

    private fun insertIntoIncome(name: String, color: Int, icon: ByteArray?) {
        val income = Income(
            0,
            name,
            color,//TODO make color picker
            icon//TODO make icon picker
        )
        if (inputCheck(name)){

            myIncomeViewModel.addIncome(income)
            Toast.makeText(requireContext(), "success: ${income.name} income added", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String): Boolean {//TODO add color and icon when user is able to choose them
        return !(TextUtils.isEmpty(name))
    }
}