package com.example.santheconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.ui.theme.*
import com.example.santheconnect.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavController, viewModel: LocationViewModel) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Daily")
    val allLocations = viewModel.locations.value

    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Market Calendar", style = Typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(days) { day ->
                val marketsForDay = allLocations.filter { it.day.equals(day, ignoreCase = true) }
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = PrimaryOrange, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = day, 
                                style = Typography.titleMedium,
                                color = TextPrimary
                            )
                        }
                        
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                        
                        if (marketsForDay.isEmpty()) {
                            Text("No major markets scheduled.", color = TextSecondary, style = Typography.bodyMedium)
                        } else {
                            marketsForDay.forEach { market ->
                                Text(
                                    text = "• ${market.name}",
                                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                    color = TextPrimary,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                Text(
                                    text = market.address,
                                    style = Typography.bodySmall,
                                    color = TextSecondary,
                                    modifier = Modifier.padding(start = 12.dp, bottom = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    val notifications = listOf(
        "Welcome to Santhe Connect! 🌽 Explore Karnataka's vibrant local markets.",
        "Today's Highlight: Don't miss the Anekal Santhe for fresh organic Ragi.",
        "Travel Tip: Best time for KR Market is early morning at 5:00 AM.",
        "Saved Market Update: Maddur market is opening tomorrow!"
    )

    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications", style = Typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        if (notifications.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No notifications yet", color = TextSecondary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notifications) { note ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                modifier = Modifier.size(40.dp),
                                shape = RoundedCornerShape(10.dp),
                                color = PrimaryOrange.copy(alpha = 0.1f)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.NotificationsNone, contentDescription = null, tint = PrimaryOrange)
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = note, 
                                style = Typography.bodyMedium,
                                color = TextPrimary,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(navController: NavController, title: String) {
    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            TopAppBar(
                title = { Text(title, style = Typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🏗️", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("$title feature coming soon!", style = Typography.bodyLarge, color = TextSecondary)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Go Back")
                }
            }
        }
    }
}
