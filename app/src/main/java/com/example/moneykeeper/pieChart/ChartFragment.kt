package com.example.moneykeeper.pieChart

import android.app.Activity
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.moneykeeper.*
import com.example.moneykeeper.fragments.MainFragment


class ChartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val metrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        Log.d("width", "$width")
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                PieChartYTTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            PieChart(//1440/500,
                                modifier = Modifier
                                    .size(500.dp),
                                input = MainFragment.categories as MutableList<PieChartInput>,
                                centerText = "",
                                radius = (width/3).toFloat(),
                                innerRadius = (width/6).toFloat()
                            )
                        }
                    }
                }
            }
        }
    }
}