package com.example.moneykeeper.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.R

class CategoriesListAdapter(): RecyclerView.Adapter<CategoriesListAdapter.CategoriesListHolder>() {

    private var categories = emptyList<Category>()

    class CategoriesListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val identificator: TextView = itemView.findViewById(R.id.uid_row)
        val categoryName: TextView = itemView.findViewById(R.id.name_row)
        val categoryIcon: ImageView = itemView.findViewById(R.id.transferIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  CategoriesListHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesListHolder, position: Int) {
        holder.identificator.text = categories[position].uid.toString()
        holder.categoryName.text = categories[position].name
        val bitmap = BitmapFactory.decodeByteArray(categories[position].icon, 0, categories[position].icon!!.size)
        holder.categoryIcon.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return  categories.size
    }

    fun setData(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }
}