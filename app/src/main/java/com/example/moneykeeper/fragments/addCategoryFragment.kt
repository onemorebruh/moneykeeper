package com.example.moneykeeper.fragments

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.R
import com.example.moneykeeper.database.CategoriesViewModel
import com.example.moneykeeper.database.Category
import com.example.moneykeeper.pieChart.PieChartInput
import java.io.ByteArrayOutputStream


class addCategoryFragment : Fragment() {


    private lateinit var myCategoriesViewModel: CategoriesViewModel
    private var selectedColor: View? = null
    private var selectedColorInt: androidx.compose.ui.graphics.Color = Color(0xFF000000)
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
            red700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#d50000"))
                selectedColorInt = Color(0xFFd50000)
                alertDialog.dismiss()
            }
            pink700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#c51162"))
                selectedColorInt = Color(0xFFc51162)
                alertDialog.dismiss()
            }
            purple700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#aa00ff"))
                selectedColorInt = Color(0xFFaa00ff)
                alertDialog.dismiss()
            }
            deepPurple700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#6200ea"))
                selectedColorInt = Color(0xFF6200ea)
                alertDialog.dismiss()
            }
            indigo700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#304ffe"))
                selectedColorInt = Color(0xFF304ffe)
                alertDialog.dismiss()
            }
            blue700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#2962ff"))
                selectedColorInt = Color(0xFF2962ff)
                alertDialog.dismiss()
            }
            lightBlue700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#0091ea"))
                selectedColorInt = Color(0xFF0091ea)
                alertDialog.dismiss()
            }
            cyan700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#00b8d4"))
                selectedColorInt = Color(0xFF00b8d4)
                alertDialog.dismiss()
            }
            teal700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#00bfa5"))
                selectedColorInt = Color(0xFF00bfa5)
                alertDialog.dismiss()
            }
            green700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#00c853"))
                selectedColorInt = Color(0xFF00c853)
                alertDialog.dismiss()
            }
            lightGreen700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#64dd17"))
                selectedColorInt = Color(0xFF64dd17)
                alertDialog.dismiss()
            }
            lime700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#aeea00"))
                selectedColorInt = Color(0xFFaeea00)
                alertDialog.dismiss()
            }
            yellow700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#ffd600"))
                selectedColorInt = Color(0xFFffd600)
                alertDialog.dismiss()
            }
            amber700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#ffab00"))
                selectedColorInt = Color(0xFFffab00)
                alertDialog.dismiss()
            }
            orange700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#ff6d00"))
                selectedColorInt = Color(0xFFff6d00)
                alertDialog.dismiss()
            }
            deepOrange700.setOnClickListener {
                setColor(android.graphics.Color.parseColor("#dd2c00"))
                selectedColorInt = Color(0xFFdd2c00)
                alertDialog.dismiss()
            }
        }

        selectedIcon!!.setOnClickListener {

            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.activity_icon_dialog, null)

            dialog.setView(dialogView)
            val alertDialog = dialog.create()

            alertDialog.show()
            // get icons
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
                selectedIconValue = convertSVGToByteArray(R.drawable.image_car)
                selectedIcon!!.setBackgroundResource(R.drawable.image_car)
                alertDialog.dismiss()
            }
            family.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_family)
                selectedIcon!!.setBackgroundResource(R.drawable.image_family)
                alertDialog.dismiss()
            }
            food.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_food)
                selectedIcon!!.setBackgroundResource(R.drawable.image_food)
                alertDialog.dismiss()
            }
            gadget.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_electronics)
                selectedIcon!!.setBackgroundResource(R.drawable.image_electronics)
                alertDialog.dismiss()
            }
            gift.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_gift)
                selectedIcon!!.setBackgroundResource(R.drawable.image_gift)
                alertDialog.dismiss()
            }
            grocery.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_groceries)
                selectedIcon!!.setBackgroundResource(R.drawable.image_groceries)
                alertDialog.dismiss()
            }
            health.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_health)
                selectedIcon!!.setBackgroundResource(R.drawable.image_health)
                alertDialog.dismiss()
            }
            home.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_home)
                selectedIcon!!.setBackgroundResource(R.drawable.image_home)
                alertDialog.dismiss()
            }
            other.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_wallet)
                selectedIcon!!.setBackgroundResource(R.drawable.image_wallet)
                alertDialog.dismiss()
            }
            question.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_question)
                selectedIcon!!.setBackgroundResource(R.drawable.image_question)
                alertDialog.dismiss()
            }
            subscribtion.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_subscriptions)
                selectedIcon!!.setBackgroundResource(R.drawable.image_subscriptions)
                alertDialog.dismiss()
            }
            transport.setOnClickListener {
                selectedIconValue = convertSVGToByteArray(R.drawable.image_transport)
                selectedIcon!!.setBackgroundResource(R.drawable.image_transport)
                alertDialog.dismiss()
            }
        }


        saveCategoryButton.setOnClickListener {
            if (selectedIconValue != null) {
                insertIntoCategories(
                    categoryName.text.toString(),
                    selectedColorInt.toArgb(),
                    selectedIconValue!!
                )
                findNavController().navigate(R.id.action_addCategoryFragment_to_categoriesFragment)
            }
            else{
                Toast.makeText(context, "please choose icon", Toast.LENGTH_LONG).show()
            }
        }



        return view
    }

    private fun setColor(color: Int) {
        //return color as Int
        selectedColor!!.setBackgroundColor(color)
    }

    private fun insertIntoCategories(name: String, color: Int, icon: ByteArray) {
        val category = Category(
            uid = 0,
            name = name,
            color = color,
            icon = icon
        )
        if (inputCheck(name, color, icon)){
            //insert
            myCategoriesViewModel.addCategory(category)
            Toast.makeText(requireContext(), "success: ${category.name} category added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, color: Int, icon: ByteArray?): Boolean {
        return (!(TextUtils.isEmpty(name)) && color != 0 && icon != null)
    }

    private fun convertSVGToByteArray(resId: Int): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), resId)
        drawable?.let {
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap?.let {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                bitmap.recycle()
            }
        }
        return byteArrayOutputStream.toByteArray()
    }

}