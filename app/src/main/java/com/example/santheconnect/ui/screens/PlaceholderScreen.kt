package com.example.santheconnect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(

    navController: NavController,

    title: String,

    description: String

) {

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(title)
                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        }

                    ) {

                        Icon(

                            Icons.AutoMirrored.Filled.ArrowBack,

                            contentDescription = "Back"
                        )
                    }
                }
            )
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center

        ) {

            Text(

                text = title,

                style =
                    MaterialTheme.typography.headlineMedium,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(

                text = description,

                style =
                    MaterialTheme.typography.bodyLarge
            )
        }
    }
}