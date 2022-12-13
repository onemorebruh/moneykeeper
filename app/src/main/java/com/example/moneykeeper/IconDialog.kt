package com.example.moneykeeper

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneykeeper.database.TransferListAdapter
import com.example.moneykeeper.database.TransferViewModel
import java.lang.reflect.Field


class IconDialog : AppCompatActivity() {

    private var tableView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icon_dialog)
        //TODO make icon choose dialog
    }
}