package com.example.moneykeeper.fragments

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.R
import com.example.moneykeeper.database.CategoriesViewModel
import com.example.moneykeeper.database.Category
import java.io.ByteArrayOutputStream


class addCategoryFragment : Fragment() {


    private lateinit var myCategoriesViewModel: CategoriesViewModel
    private var selectedColor: View? = null
    private var selectedColorInt: Int = 0
    private var selectedIcon: View? = null
    private var selectedIconValue: ByteArray? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)

        myCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        val saveCategoryButton = view.findViewById<Button>(R.id.saveCategoryButton)
        val categoryName = view.findViewById<EditText>(R.id.categoryName)
        selectedColor = view.findViewById(R.id.selectedColor)
        selectedIcon = view.findViewById(R.id.selectedIcon)

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

        selectedIcon!!.setOnClickListener {

            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.activity_icon_dialog, null)

            dialog.setView(dialogView)
            val alertDialog = dialog.create()

            alertDialog.show()
            // get colors
            val car = dialogView.findViewById<View>(R.id.imageCar)
            val family = dialogView.findViewById<View>(R.id.imageFamily)
            val food = dialogView.findViewById<View>(R.id.imageFood)
            val gadget = dialogView.findViewById<View>(R.id.imageGadjets)
            val gift = dialogView.findViewById<View>(R.id.imageGift)
            val grocery = dialogView.findViewById<View>(R.id.imageGroceries)
            val health = dialogView.findViewById<View>(R.id.imageHealth)
            val home = dialogView.findViewById<View>(R.id.imageHome)
            val other = dialogView.findViewById<View>(R.id.imageOther)
            val question = dialogView.findViewById<View>(R.id.imageQuestion)
            val subscribtion = dialogView.findViewById<View>(R.id.imageSubscribtion)
            val transport = dialogView.findViewById<View>(R.id.imageTransport)
            //set listeners
            car.setOnClickListener {
                alertDialog.dismiss()
            }
        }


        saveCategoryButton.setOnClickListener {
            insertIntoCategories(categoryName.text.toString(), selectedColorInt, null)
            findNavController().navigate(R.id.action_addCategoryFragment_to_categoriesFragment)
            }



        return view
    }

    private fun setColor(color: Int) {
        //return color as Int
        selectedColor!!.setBackgroundColor(color)
    }

    private fun insertIntoCategories(name: String, color: Int, icon: ByteArray?) {
        val category = Category(
            0,
            name,
            color,
            icon//TODO make icon picker
        )
        if (inputCheck(name, color)){
            //insert
            myCategoriesViewModel.addCategory(category)
            Toast.makeText(requireContext(), "success: ${category.name} category added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, color: Int): Boolean {//TODO add  icon when user is able to choose them
        return (!(TextUtils.isEmpty(name)) && color != 0)
    }

}