package com.example.moneykeeper

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.moneykeeper.database.CategoriesViewModel
import com.example.moneykeeper.database.Transfer
import com.example.moneykeeper.database.TransferViewModel

class MainFragment : Fragment() {

    private var incomeButton: Button? = null
    private var expenseButton: Button? = null
    private lateinit var myTransferViewModel: TransferViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        incomeButton = view.findViewById(R.id.addIncomeButton)
        expenseButton = view.findViewById(R.id.addExpenseButton)

        myTransferViewModel = ViewModelProvider(this)[TransferViewModel::class.java]

        incomeButton!!.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.income_dialog, null)
            val editTransactionName = dialogView.findViewById<EditText>(R.id.editTextTransactionName)
            val spinnerIncomes= dialogView.findViewById<Spinner>(R.id.spinnerIncomes)
            val editValue = dialogView.findViewById<EditText>(R.id.editTextNumber)
            val submitIncomeButton = dialogView.findViewById<Button>(R.id.submitIncomeButton)

            submitIncomeButton.setOnClickListener {

            }

        }
        return view
    }

    private fun insertTransaction(transactionName: String, income: String, value: String){
        var transfer = Transfer(
            0,
            transactionName,
            value,
            income,
            null,//TODO read color from categories
        )
        if (inputCheck(transactionName, income, value)){
            myTransferViewModel.addTransfer(transfer)
            Toast.makeText(requireContext(), "success: ${transfer.name} category added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(name: String, income: String, value: String): Boolean {//TODO add color and icon when user is able to choose them
        return !(TextUtils.isEmpty(name)) && !(TextUtils.isEmpty(income)) && !(TextUtils.isEmpty(value))
    }
}