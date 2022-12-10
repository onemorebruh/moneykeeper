package com.example.moneykeeper

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moneykeeper.database.*

class MainFragment : Fragment() {

    private var incomeButton: Button? = null
    private var expenseButton: Button? = null
    private lateinit var myTransferViewModel: TransferViewModel
    private lateinit var myCategoriesViewModel: CategoriesViewModel
    private var selectedCategory: Category? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        incomeButton = view.findViewById(R.id.addIncomeButton)
        expenseButton = view.findViewById(R.id.addExpenseButton)

        var categoryNames: MutableList<String> = mutableListOf<String>()
        var categories: MutableList<Category> = mutableListOf<Category>()

        myTransferViewModel = ViewModelProvider(this)[TransferViewModel::class.java]
        myCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]


        expenseButton!!.setOnClickListener {//TODO fix border radius
            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.expense_dialog, null)
            val editTransactionName = dialogView.findViewById<EditText>(R.id.editTextTransactionName)
            val spinnerExprense= dialogView.findViewById<Spinner>(R.id.spinnerIncomes)
            val editValue = dialogView.findViewById<EditText>(R.id.editTextNumber)
            val submitIncomeButton = dialogView.findViewById<Button>(R.id.submitExpenseButton)
            //spinner set array of values
            val spinnerArrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.text_view, categoryNames)

            //get list of categories
            myCategoriesViewModel.readAllData.observe(viewLifecycleOwner,
                Observer<List<Category?>> { terms ->
                    for (term in terms) {
                        categoryNames.add(term!!.name)
                        categories.add(term)
                    }

                    //notifyDataSetChanged after update termsList variable here
                    spinnerArrayAdapter.notifyDataSetChanged()
                })

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerExprense.adapter = spinnerArrayAdapter
            spinnerExprense.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedCategory = categories[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //do nothing
                }

            }
            //TODO make it return selected category to selected category variable
            dialog.setView(dialogView)
            val alertDialog = dialog.create()
            submitIncomeButton.setOnClickListener {
                insertTransaction(editTransactionName.text.toString(), selectedCategory!!, editValue.text.toString())
                alertDialog.dismiss()
            }
            alertDialog.show()





        }
        return view
    }

    private fun insertTransaction(transactionName: String, category: Category, value: String){
        var actualValue: String? = null
        //check for minus
        if (value.toInt() < 0){
            actualValue = value
        } else {
            actualValue = ( value.toInt() * -1 ).toString()
        }

        val transfer = Transfer(
            0,
            transactionName,
            value,
            category.uid.toString(),//here is String but required tu be an Int
            null,//TODO read color from categories
        )
        if (inputCheck(transactionName, category, value)){
            myTransferViewModel.addTransfer(transfer)
            Toast.makeText(requireContext(), "success: ${transfer.name} transaction added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(name: String, category: Category, value: String): Boolean {
        return !(TextUtils.isEmpty(name)) && !(TextUtils.isEmpty(category.uid.toString())) && !(TextUtils.isEmpty(value))
    }
}