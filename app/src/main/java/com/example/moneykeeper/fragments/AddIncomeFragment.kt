package com.example.moneykeeper.fragments

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moneykeeper.R
import com.example.moneykeeper.database.Income
import com.example.moneykeeper.database.IncomeViewModel
import java.io.ByteArrayOutputStream


class AddIncomeFragment : Fragment() {

    private lateinit var myIncomeViewModel: IncomeViewModel
    private var selectedColor: View? = null
    private var selectedColorInt: Int = 0
    private var selectedIcon: View? = null
    private var selectedIconValue: ByteArray? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)

        myIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        val saveIncomeButton = view.findViewById<Button>(R.id.saveIncomeButton)
        val incomeName = view.findViewById<EditText>(R.id.incomeName)
        selectedColor = view.findViewById<View>(R.id.selectedIncomeColor)
        selectedIcon = view.findViewById<View>(R.id.selectedIncomeIcon)

        saveIncomeButton.setOnClickListener {
            if (selectedIconValue != null) {
                insertIntoIncome(incomeName.text.toString(), selectedColorInt, selectedIconValue!!)
                findNavController().navigate(R.id.action_addIncomeFragment_to_incomesFragment)
            }else{
                Toast.makeText(context, "please choose icon", Toast.LENGTH_LONG).show()
            }
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
            red700.setOnClickListener {
                setColor(Color.parseColor("#d50000"))
                selectedColorInt = Color.parseColor("#d50000")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            pink700.setOnClickListener { setColor(Color.parseColor("#c51162"))
                selectedColorInt = Color.parseColor("#c51162")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            purple700.setOnClickListener { setColor(Color.parseColor("#aa00ff"))
                selectedColorInt = Color.parseColor("#aa00ff")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            deepPurple700.setOnClickListener { setColor(Color.parseColor("#6200ea"))
                selectedColorInt = Color.parseColor("#6200ea")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            indigo700.setOnClickListener { setColor(Color.parseColor("#304ffe"))
                selectedColorInt = Color.parseColor("#304ffe")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            blue700.setOnClickListener { setColor(Color.parseColor("#2962ff"))
                selectedColorInt = Color.parseColor("#2962ff")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            lightBlue700.setOnClickListener { setColor(Color.parseColor("#0091ea"))
                selectedColorInt = Color.parseColor("#0091ea")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            cyan700.setOnClickListener { setColor(Color.parseColor("#00b8d4"))
                selectedColorInt = Color.parseColor("#00b8d4")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            teal700.setOnClickListener { setColor(Color.parseColor("#00bfa5"))
                selectedColorInt = Color.parseColor("#00bfa5")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            green700.setOnClickListener { setColor(Color.parseColor("#00c853"))
                selectedColorInt = Color.parseColor("#00c853")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            lightGreen700.setOnClickListener { setColor(Color.parseColor("#64dd17"))
                selectedColorInt = Color.parseColor("#64dd17")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            lime700.setOnClickListener { setColor(Color.parseColor("#aeea00"))
                selectedColorInt = Color.parseColor("#aeea00")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            yellow700.setOnClickListener { setColor(Color.parseColor("#ffd600"))
                selectedColorInt = Color.parseColor("#ffd600")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            amber700.setOnClickListener { setColor(Color.parseColor("#ffab00"))
                selectedColorInt = Color.parseColor("#ffab00")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            orange700.setOnClickListener { setColor(Color.parseColor("#ff6d00"))
                selectedColorInt = Color.parseColor("#ff6d00")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
            }
            deepOrange700.setOnClickListener { setColor(Color.parseColor("#dd2c00"))
                selectedColorInt = Color.parseColor("#dd2c00")
                alertDialog.dismiss()
                Log.d("msg", "$selectedColorInt")
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

        return view
    }

    private fun setColor(color: Int) {// i thought it would do more things
        //TODO rewrite without setColor function
        //return color as Int
        selectedColor!!.setBackgroundColor(color)
    }

    private fun insertIntoIncome(name: String, color: Int, icon: ByteArray) {
        val income = Income(
            0,
            name,
            color,
            icon
        )
        if (inputCheck(name, color)){

            myIncomeViewModel.addIncome(income)
            Toast.makeText(requireContext(), "success: ${income.name} income added", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(requireContext(), "error: some of fields are empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, color: Int): Boolean {
        return (!(TextUtils.isEmpty(name)) && color != 0)
    }

    private fun convertSVGToByteArray(resId: Int): ByteArray {//TODO check for size change(svg it converted to image here)
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