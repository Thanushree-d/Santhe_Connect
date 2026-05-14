package com.example.santheconnect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AboutSantheScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "About Santhe Connect",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text =
                "Santhe Connect is a smart platform designed to help users discover Karnataka's traditional weekly markets."
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text =
                "The app provides market schedules, locations, reviews, specialties, and navigation assistance."
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text =
                "Version 1.0.0"
        )
    }
}