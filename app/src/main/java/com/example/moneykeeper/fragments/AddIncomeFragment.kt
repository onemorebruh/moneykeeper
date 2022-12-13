package com.example.moneykeeper.fragments

import android.app.AlertDialog
import android.graphics.Color
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
    private var selectedColor: View? = null
    private var selectedColorInt: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)

        myIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        val saveIncomeButton = view.findViewById<Button>(R.id.saveIncomeButton)
        val incomeName = view.findViewById<EditText>(R.id.incomeName)
        selectedColor = view.findViewById<View>(R.id.selectedIncomeColor)

        saveIncomeButton.setOnClickListener {
            insertIntoIncome(incomeName.text.toString(), 0, null)
            findNavController().navigate(R.id.action_addIncomeFragment_to_incomesFragment)
        }

        selectedColor!!.setOnClickListener {

            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.activity_color_dialog, null)

            dialog.setView(dialogView)
            val alertDialog = dialog.create()

            alertDialog.show()
            // get colors
            val red700 = dialogView.findViewById<View>(R.id.red700)
            val pink700 = dialogView.findViewById<View>(R.id.pink700)
            val purple700 = dialogView.findViewById<View>(R.id.purple700)
            val deepPurple700 = dialogView.findViewById<View>(R.id.deepPurple700)
            val indigo700 = dialogView.findViewById<View>(R.id.indigo700)
            val blue700 = dialogView.findViewById<View>(R.id.blue700)
            val lightBlue700 = dialogView.findViewById<View>(R.id.lightBlue700)
            val cyan700 = dialogView.findViewById<View>(R.id.cyan700)
            val teal700 = dialogView.findViewById<View>(R.id.teal700)
            val green700 = dialogView.findViewById<View>(R.id.green700)
            val lightGreen700 = dialogView.findViewById<View>(R.id.lightGreen700)
            val lime700 = dialogView.findViewById<View>(R.id.lime700)
            val yellow700 = dialogView.findViewById<View>(R.id.yellow700)
            val amber700 = dialogView.findViewById<View>(R.id.amber700)
            val orange700 = dialogView.findViewById<View>(R.id.orange700)
            val deepOrange700 = dialogView.findViewById<View>(R.id.deepOrange700)
            //set listeners
            red700.setOnClickListener { setColor(Color.parseColor("#d50000"))
                selectedColorInt = Color.parseColor("#d50000")
                alertDialog.dismiss()
            }
            pink700.setOnClickListener { setColor(Color.parseColor("#c51162"))
                selectedColorInt = Color.parseColor("#c51162")
                alertDialog.dismiss()
            }
            purple700.setOnClickListener { setColor(Color.parseColor("#aa00ff"))
                selectedColorInt = Color.parseColor("#aa00ff")
                alertDialog.dismiss()
            }
            deepPurple700.setOnClickListener { setColor(Color.parseColor("#6200ea"))
                selectedColorInt = Color.parseColor("#6200ea")
                alertDialog.dismiss()
            }
            indigo700.setOnClickListener { setColor(Color.parseColor("#304ffe"))
                selectedColorInt = Color.parseColor("#304ffe")
                alertDialog.dismiss()
            }
            blue700.setOnClickListener { setColor(Color.parseColor("#2962ff"))
                selectedColorInt = Color.parseColor("#2962ff")
                alertDialog.dismiss()
            }
            lightBlue700.setOnClickListener { setColor(Color.parseColor("#0091ea"))
                selectedColorInt = Color.parseColor("#0091ea")
                alertDialog.dismiss()
            }
            cyan700.setOnClickListener { setColor(Color.parseColor("#00b8d4"))
                selectedColorInt = Color.parseColor("#00b8d4")
                alertDialog.dismiss()
            }
            teal700.setOnClickListener { setColor(Color.parseColor("#00bfa5"))
                selectedColorInt = Color.parseColor("#00bfa5")
                alertDialog.dismiss()
            }
            green700.setOnClickListener { setColor(Color.parseColor("#00c853"))
                selectedColorInt = Color.parseColor("#00c853")
                alertDialog.dismiss()
            }
            lightGreen700.setOnClickListener { setColor(Color.parseColor("#64dd17"))
                selectedColorInt = Color.parseColor("#64dd17")
                alertDialog.dismiss()
            }
            lime700.setOnClickListener { setColor(Color.parseColor("#aeea00"))
                selectedColorInt = Color.parseColor("#aeea00")
                alertDialog.dismiss()
            }
            yellow700.setOnClickListener { setColor(Color.parseColor("#ffd600"))
                selectedColorInt = Color.parseColor("#ffd600")
                alertDialog.dismiss()
            }
            amber700.setOnClickListener { setColor(Color.parseColor("#ffab00"))
                selectedColorInt = Color.parseColor("#ffab00")
                alertDialog.dismiss()
            }
            orange700.setOnClickListener { setColor(Color.parseColor("#ff6d00"))
                selectedColorInt = Color.parseColor("#ff6d00")
                alertDialog.dismiss()
            }
            deepOrange700.setOnClickListener { setColor(Color.parseColor("#dd2c00"))
                selectedColorInt = Color.parseColor("#dd2c00")
                alertDialog.dismiss()
            }





        }

        return view
    }

    private fun setColor(color: Int) {// i thought it would do more things
        //TODO rewrite without setColor function
        //return color as Int
        selectedColor!!.setBackgroundColor(color)
    }

    private fun insertIntoIncome(name: String, color: Int, icon: ByteArray?) {
        val income = Income(
            0,
            name,
            color,//
            icon//TODO make icon picker
        )
        if (inputCheck(name, color)){

            myIncomeViewModel.addIncome(income)
            Toast.makeText(requireContext(), "success: ${income.name} income added", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, color: Int): Boolean {//TODO add icon when user is able to choose them
        return (!(TextUtils.isEmpty(name)) && color != 0)
    }
}