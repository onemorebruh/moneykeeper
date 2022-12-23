package com.example.moneykeeper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneykeeper.*
import com.example.moneykeeper.pieChart.PieChart
import com.example.moneykeeper.pieChart.PieChartInput
import com.example.moneykeeper.pieChart.PieChartYTTheme

class AccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                            PieChart(
                                modifier = Modifier
                                    .size(500.dp),
                                input = listOf(
                                    PieChartInput(
                                        color = brightBlue,
                                        value = 29,
                                        description = "Python"
                                    ),
                                    PieChartInput(
                                        color = purple,
                                        value = 21,
                                        description = "Swift"
                                    ),
                                    PieChartInput(
                                        color = blueGray,
                                        value = 32,
                                        description = "JavaScript"
                                    ),
                                    PieChartInput(
                                        color = redOrange,
                                        value = 18,
                                        description = "Java"
                                    ),
                                    PieChartInput(
                                        color = green,
                                        value = 12,
                                        description = "Ruby"
                                    ),
                                    PieChartInput(
                                        color = orange,
                                        value = 38,
                                        description = "Kotlin"
                                    ),
                                ) as MutableList<PieChartInput>,
                                centerText = ""
                            )
                        }
                    }
                }
            }
        }
    }
}