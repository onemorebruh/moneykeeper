package com.example.moneykeeper.database

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.R

class IncomeListAdapter(): RecyclerView.Adapter<IncomeListAdapter.IncomeListHolder>() {

    private var incomes = emptyList<Income>()

    class IncomeListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val identificator: TextView =  itemView.findViewById(R.id.uid_row)
        val incomeName: TextView = itemView.findViewById(R.id.name_row)
        val incomeIcon: ImageView = itemView.findViewById(R.id.transferIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return IncomeListHolder(itemView)
    }

    override fun onBindViewHolder(holder: IncomeListHolder, position: Int) {
        holder.identificator.text = incomes[position].uid.toString()
        holder.incomeName.text = incomes[position].name
        val bitmap = BitmapFactory.decodeByteArray(incomes[position].icon, 0, incomes[position].icon!!.size)
        holder.incomeIcon.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return incomes.size
    }

    fun setData(incomes: List<Income>){
        this.incomes = incomes
        notifyDataSetChanged()
    }
}