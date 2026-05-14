package com.example.santheconnect.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import com.example.santheconnect.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayFilter(

    viewModel: LocationViewModel

) {

    // ✅ DAYS LIST
    val days = listOf(

        "All",

        "Monday",

        "Tuesday",

        "Wednesday",

        "Thursday",

        "Friday",

        "Saturday",

        "Sunday"
    )

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {

        // ✅ TITLE
        Text(

            text = "Filter by Day",

            style = MaterialTheme.typography.labelLarge,

            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 6.dp
            ),

            color = Color(0xFFF59E0B),

            fontWeight = FontWeight.Bold
        )

        // ✅ DAY CHIPS
        LazyRow(

            contentPadding = PaddingValues(
                horizontal = 12.dp
            ),

            horizontalArrangement =
                Arrangement.spacedBy(10.dp)

        ) {

            items(days) { day ->

                // ✅ SELECTED STATE
                val isSelected =

                    viewModel.selectedDay
                        .trim()
                        .equals(
                            day.trim(),
                            ignoreCase = true
                        )

                FilterChip(

                    selected = isSelected,

                    onClick = {

                        // ✅ FILTER DAY
                        viewModel.filterByDay(day)
                    },

                    label = {

                        Text(

                            text = day,

                            fontWeight =
                                FontWeight.SemiBold
                        )
                    },

                    border = null,

                    colors =

                        FilterChipDefaults.filterChipColors(

                            // ✅ SELECTED COLORS
                            selectedContainerColor =
                                Color(0xFFF59E0B),

                            selectedLabelColor =
                                Color.White,

                            // ✅ UNSELECTED COLORS
                            containerColor =
                                Color(0xFFF3F4F6),

                            labelColor =
                                Color(0xFF374151)
                        )
                )
            }
        }
    }
}