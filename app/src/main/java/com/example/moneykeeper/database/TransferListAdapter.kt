package com.example.moneykeeper.database

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.R

class TransferListAdapter(): RecyclerView.Adapter<TransferListAdapter.TransferListHolder>() {

    private var transfers= emptyList<Transfer>()

    class TransferListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val transferView: ConstraintLayout = itemView.findViewById(R.id.transactionView)
        val transferName: TextView = itemView.findViewById(R.id.transactionName)
        val transferValue: TextView = itemView.findViewById(R.id.transactionValue)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false)
        return  TransferListHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransferListHolder, position: Int) {
        holder.transferName.text = transfers[position].name
        holder.transferValue.text = transfers[position].value.toString()
        if (Int.pasreInt(transfers[position].value) > 0){
            holder.transferValue.setBackgroundColor(Color.parseColor("#D33535"))
        }
        //todo paint transferView to category's color
    }

    override fun getItemCount(): Int {
        return transfers.size
    }

    fun setData(transfers: List<Transfer>){
        this.transfers = transfers
        notifyDataSetChanged()
    }
}