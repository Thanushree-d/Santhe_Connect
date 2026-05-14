package com.example.santheconnect.ui.screens

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

import com.example.santheconnect.ui.components.AIInsightsCard
import com.example.santheconnect.ui.components.BottomNav
import com.example.santheconnect.ui.components.CategoryFilter
import com.example.santheconnect.ui.components.DayFilter
import com.example.santheconnect.ui.components.EmptyState
import com.example.santheconnect.ui.components.GreetingCard
import com.example.santheconnect.ui.components.LocationCard
import com.example.santheconnect.ui.components.SearchBar
import com.example.santheconnect.ui.components.TopBarAdvanced

import com.example.santheconnect.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

    navController: NavController,

    viewModel: LocationViewModel

) {

    val locations by viewModel.locations

    val favoriteNames by viewModel.favoriteNames

    Scaffold(

        containerColor = Color(0xFFF8F5F0),

        topBar = {

            TopBarAdvanced(

                onNotificationClick = {

                    navController.navigate(
                        "notifications"
                    )
                }
            )
        },

        bottomBar = {

            BottomNav(navController)
        },


    ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentPadding = PaddingValues(

                start = 16.dp,

                end = 16.dp,

                top = 16.dp,

                bottom = 120.dp
            ),

            verticalArrangement =
                Arrangement.spacedBy(14.dp)

        ) {

            // ✅ GREETING
            item {

                GreetingCard(

                    onRefresh = {

                        viewModel.refreshData()
                    }
                )
            }

            // ✅ AI CARD
            item {

                AIInsightsCard(

                    onGetTipsClick = {

                        navController.navigate(
                            "ai_tips"
                        )
                    }
                )
            }

            // ✅ SEARCH
            item {

                SearchBar(

                    query =
                        viewModel.searchQuery,

                    onQueryChange = {

                        viewModel.searchQuery = it

                        viewModel.applyFilters()
                    }
                )
            }

            // ✅ CATEGORY FILTER
            item {

                CategoryFilter(

                    selected =
                        viewModel.selectedCategory,

                    onSelect = {

                        viewModel.selectedCategory = it

                        viewModel.applyFilters()
                    }
                )
            }

            // ✅ DAY FILTER
            item {

                DayFilter(

                    viewModel = viewModel
                )
            }

            // ✅ EMPTY STATE
            if (locations.isEmpty()) {

                item {

                    EmptyState(

                        day = viewModel.selectedDay,

                        onExploreClick = {

                            viewModel.selectedDay = "All"

                            viewModel.selectedCategory = "all"

                            viewModel.searchQuery = ""

                            viewModel.applyFilters()
                        }
                    )
                }

            } else {

                // ✅ LOCATION LIST
                items(

                    items = locations,

                    key = {

                        it.name
                    }

                ) { location ->

                    LocationCard(

                        location = location,

                        isFavorite =
                            favoriteNames.contains(
                                location.name
                            ),

                        onToggleFavorite = {

                            viewModel.toggleFavorite(
                                location.name
                            )
                        },

                        onClick = {

                            val encodedName =

                                URLEncoder.encode(

                                    location.name,

                                    StandardCharsets.UTF_8.toString()
                                )

                            navController.navigate(

                                "details/$encodedName"
                            )
                        }
                    )
                }
            }

            // ✅ EXTRA SPACE FOR FAB
            item {

                Spacer(

                    modifier =
                        Modifier.height(100.dp)
                )
            }
        }
    }
}