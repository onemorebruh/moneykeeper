package com.example.moneykeeper.database

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.R


class TransferListAdapter(): RecyclerView.Adapter<TransferListAdapter.TransferListHolder>() {

    private var transfers= emptyList<Transfer>()

    class TransferListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val transferName: TextView = itemView.findViewById(R.id.transactionName)
        val transferValue: TextView = itemView.findViewById(R.id.transactionValue)
        val transferIcon: ImageView = itemView.findViewById(R.id.row_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false)
        return  TransferListHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransferListHolder, position: Int) {
        holder.transferName.text = transfers[position].name
        holder.transferValue.text = transfers[position].value.toString()
        if (transfers[position].value.toInt() < 0){
            holder.transferValue.setTextColor(Color.parseColor("#D33535"))
        }else {
            holder.transferValue.setTextColor((Color.parseColor("#4CAF50")))
        }
        // paint transferView to category's color
        holder.transferName.setTextColor(transfers[position].categoryColor)
        val bitmap = BitmapFactory.decodeByteArray(transfers[position].categoryIcon, 0, transfers[position].categoryIcon.size)
        holder.transferIcon.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return transfers.size
    }

    fun setData(transfers: List<Transfer>){
        this.transfers = transfers
        notifyDataSetChanged()
    }
}