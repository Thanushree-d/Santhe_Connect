package com.example.santheconnect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AccountSettingsScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Account Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("• Change Password")

        Spacer(modifier = Modifier.height(12.dp))

        Text("• Manage Notifications")

        Spacer(modifier = Modifier.height(12.dp))

        Text("• Privacy Settings")

        Spacer(modifier = Modifier.height(12.dp))

        Text("• Language Preferences")

        Spacer(modifier = Modifier.height(12.dp))

        Text("• Theme Settings")
    }
}