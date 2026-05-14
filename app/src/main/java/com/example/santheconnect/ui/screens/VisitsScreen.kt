package com.example.santheconnect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun VisitsScreen(

    navController: NavController

) {

    val visits = listOf(

        "KR Market Santhe",
        "Mysore Flower Market",
        "Udupi Fish Santhe",
        "Davangere Organic Market"
    )

    LazyColumn(

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(16.dp)

    ) {

        items(visits) { visit ->

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)

            ) {

                Text(

                    text = visit,

                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}