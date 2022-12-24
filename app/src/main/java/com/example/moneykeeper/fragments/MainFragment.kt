package com.example.moneykeeper.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moneykeeper.R
import com.example.moneykeeper.database.*
import com.example.moneykeeper.pieChart.ChartFragment

class MainFragment : Fragment() {

    private var incomeButton: Button? = null
    private var expenseButton: Button? = null
    private lateinit var myTransferViewModel: TransferViewModel
    private lateinit var myCategoriesViewModel: CategoriesViewModel
    private lateinit var myIncomeViewModel: IncomeViewModel
    private var selectedCategory: Category? = null//Category or Income
    private var selectedIncome: Income? = null
    private var selectedColor: Int = 0
    private var selectedIcon: ByteArray? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        incomeButton = view.findViewById(R.id.addIncomeButton)
        expenseButton = view.findViewById(R.id.addExpenseButton)



        myTransferViewModel = ViewModelProvider(this)[TransferViewModel::class.java]
        myCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        myIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]


        expenseButton!!.setOnClickListener {
            val categoryNames: MutableList<String> = mutableListOf<String>()
            val categories: MutableList<Category> = mutableListOf<Category>()
            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.expense_dialog, null)
            val editTransactionName = dialogView.findViewById<EditText>(R.id.editTextTransactionNameExpense)
            val spinnerExpense= dialogView.findViewById<Spinner>(R.id.spinnerExpenses)
            val editValue = dialogView.findViewById<EditText>(R.id.editTextNumberExpenses)
            val submitIncomeButton = dialogView.findViewById<Button>(R.id.submitExpensesButton)
            //spinner set array of values
            val spinnerArrayAdapter = ArrayAdapter<String>(requireContext(),
                R.layout.text_view, categoryNames)

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

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerExpense.adapter = spinnerArrayAdapter
            spinnerExpense.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(categories.size >= p2) {
                        selectedCategory = categories[p2]
                        selectedColor = categories[p2].color
                        selectedIcon = categories[p2].icon!!
                    } else {
                        Toast.makeText(context, "please add expenses firstly", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //do nothing
                }

            }

            dialog.setView(dialogView)
            val alertDialog = dialog.create()
            submitIncomeButton.setOnClickListener {
                insertTransaction(editTransactionName.text.toString(), selectedCategory!!, null, editValue.text.toString(), selectedColor, selectedIcon!!, true)
                alertDialog.dismiss()
            }
            alertDialog.show()





        }

        incomeButton!!.setOnClickListener {
            val incomes: MutableList<Income> = mutableListOf<Income>()
            val incomeNames: MutableList<String> = mutableListOf<String>()
            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.income_dialog, null)
            val editTransactionName = dialogView.findViewById<EditText>(R.id.editTextTransactionNameIncome)
            val spinnerIncome= dialogView.findViewById<Spinner>(R.id.spinnerIncomes)
            val editValue = dialogView.findViewById<EditText>(R.id.editTextNumberIncomes)
            val submitIncomeButton = dialogView.findViewById<Button>(R.id.submitIncomesButton)
            //spinner set array of values
            val spinnerArrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.text_view, incomeNames)
            //list of categories
            myIncomeViewModel.readAllData.observe(viewLifecycleOwner,
                Observer<List<Income?>> { terms ->
                    for (term in terms) {
                        incomeNames.add(term!!.name)
                        incomes.add(term)
                    }

                    //notifyDataSetChanged after update termsList variable here
                    spinnerArrayAdapter.notifyDataSetChanged()
                })

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerIncome.adapter = spinnerArrayAdapter
            spinnerIncome.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(incomes.size >= p2) {
                        selectedIncome = incomes[p2]
                        selectedColor = incomes[p2].color
                        selectedIcon = incomes[p2].icon
                    } else {
                        Toast.makeText(context, "please add incomes firstly", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //do nothing
                }

            }

            dialog.setView(dialogView)
            val alertDialog = dialog.create()
            submitIncomeButton.setOnClickListener {
                insertTransaction(editTransactionName.text.toString(), null,selectedIncome, editValue.text.toString(), selectedColor, selectedIcon!!, false)
                alertDialog.dismiss()
            }
            alertDialog.show()





        }
        val childFragment: Fragment = ChartFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.forChart, childFragment).commit()
        return view
    }

    private fun insertTransaction(transactionName: String, category: Category?, income: Income?, value: String, color: Int, icon: ByteArray, isExpense: Boolean){
        var actualValue: String? = null
        if ( (!(TextUtils.isEmpty(income?.uid.toString())) || !TextUtils.isEmpty(category?.uid.toString())) && !(TextUtils.isEmpty(value)) && (color != 0)){
            if (isExpense){//expense
                //check for minus
                if (value.toInt() < 0){
                    actualValue = value
                } else {
                    actualValue = ( value.toInt() * -1 ).toString()
                }

                val transfer = Transfer(
                    0,
                    transactionName,
                    actualValue,
                    category!!.uid.toString(),//here is String but required tu be an Int
                    null,
                    color,//read color from categories
                    icon
                )
                //check for being not empty as expense
                    myTransferViewModel.addTransfer(transfer)
                    Toast.makeText(requireContext(), "success: ${transfer.name} transaction added", Toast.LENGTH_LONG).show()

            }else {//income

                val transfer = Transfer(
                    0,
                    transactionName,
                    value,
                    null,
                    income!!.uid.toString(),
                    color,//read color from income,
                    icon
                )

                myTransferViewModel.addTransfer(transfer)
                Toast.makeText(
                    requireContext(),
                    "success: ${transfer.name} transaction added",
                    Toast.LENGTH_LONG
                ).show()
            }
            }else{
                Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
            }
    }
}


