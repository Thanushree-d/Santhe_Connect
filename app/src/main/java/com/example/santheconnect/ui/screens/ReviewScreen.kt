package com.example.santheconnect.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.ui.components.BottomNav
import com.example.santheconnect.ui.components.LocationCard
import com.example.santheconnect.ui.components.TopBarAdvanced
import com.example.santheconnect.ui.theme.*
import com.example.santheconnect.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(navController: NavController, viewModel: LocationViewModel) {
    val favoriteNames = viewModel.favoriteNames.value
    val favoriteLocations = viewModel.locations.value.filter { favoriteNames.contains(it.name) }

    Scaffold(
        containerColor = BackgroundCream,
        topBar = { 
            TopBarAdvanced(onNotificationClick = {
                navController.navigate("notifications")
            }) 
        },
        bottomBar = { 
            Box(contentAlignment = Alignment.BottomCenter) {
                BottomNav(navController)
                FloatingActionButton(
                    onClick = { navController.navigate("add") },
                    containerColor = PrimaryOrange,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.offset(y = (-32).dp).size(56.dp),
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Text("+", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Light)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "My Favorites",
                style = Typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary
                ),
                modifier = Modifier.padding(24.dp)
            )

            if (favoriteLocations.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("❤️", fontSize = 64.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "No favorites added yet", 
                            style = Typography.bodyLarge,
                            color = TextSecondary
                        )
                        Text(
                            "Bookmark santhes to see them here", 
                            style = Typography.bodySmall,
                            color = TextSecondary.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(favoriteLocations, key = { it.name }) { location ->
                        LocationCard(
                            location = location,
                            isFavorite = true,
                            onToggleFavorite = { viewModel.toggleFavorite(location.name) },
                            onClick = { navController.navigate("details/${location.name}") }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(100.dp)) }
                }
            }
        }
    }
}
