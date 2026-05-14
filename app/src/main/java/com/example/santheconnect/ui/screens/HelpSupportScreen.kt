package com.example.santheconnect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HelpSupportScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Help & Support",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("📞 Contact Support")

        Spacer(modifier = Modifier.height(12.dp))

        Text("📧 support@santheconnect.com")

        Spacer(modifier = Modifier.height(12.dp))

        Text("❓ FAQs")

        Spacer(modifier = Modifier.height(12.dp))

        Text("🔧 App Troubleshooting")

        Spacer(modifier = Modifier.height(12.dp))

        Text("🌐 Visit Help Center")
    }
}