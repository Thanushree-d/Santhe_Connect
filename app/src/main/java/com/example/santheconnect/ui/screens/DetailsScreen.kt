package com.example.santheconnect.ui.screens

import android.content.Intent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*

import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

import coil.compose.AsyncImage

import com.example.santheconnect.data.model.Location
import com.example.santheconnect.viewmodel.LocationViewModel

@Composable
fun DetailsScreen(

    navController: NavController,

    viewModel: LocationViewModel,

    location: Location

) {

    val context = LocalContext.current

    val isFavorite =
        viewModel.favoriteNames.value.contains(location.name)

    Scaffold(

        containerColor = Color(0xFFFDF6EC)

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {

            // ✅ IMAGE HEADER
            Box {

                AsyncImage(

                    model = location.imageUrl,

                    contentDescription = location.name,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),

                    contentScale = ContentScale.Crop
                )

                // ✅ Gradient Overlay
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                        .background(

                            brush = Brush.verticalGradient(

                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.5f)
                                )
                            )
                        )
                )

                // ✅ TOP BUTTONS
                Row(

                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween

                ) {

                    // ✅ BACK BUTTON
                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        },

                        colors =
                            IconButtonDefaults.iconButtonColors(

                                containerColor =
                                    Color.White.copy(alpha = 0.9f)
                            )

                    ) {

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    Row {

                        // ✅ SHARE BUTTON
                        IconButton(

                            onClick = {

                                val shareIntent = Intent().apply {

                                    action = Intent.ACTION_SEND

                                    putExtra(

                                        Intent.EXTRA_TEXT,

                                        """
🌾 ${location.name}

📍 ${location.address}

⭐ Specialty: ${location.specialty}

📅 Santhe Day: ${location.day}

${location.description}
                                        """.trimIndent()
                                    )

                                    type = "text/plain"
                                }

                                context.startActivity(

                                    Intent.createChooser(
                                        shareIntent,
                                        "Share Santhe"
                                    )
                                )
                            },

                            colors =
                                IconButtonDefaults.iconButtonColors(

                                    containerColor =
                                        Color.White.copy(alpha = 0.9f)
                                )

                        ) {

                            Icon(
                                Icons.Outlined.Share,
                                contentDescription = "Share"
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        // ✅ FAVORITE BUTTON
                        IconButton(

                            onClick = {

                                viewModel.toggleFavorite(
                                    location.name
                                )
                            },

                            colors =
                                IconButtonDefaults.iconButtonColors(

                                    containerColor =
                                        Color.White.copy(alpha = 0.9f)
                                )

                        ) {

                            Icon(

                                imageVector =
                                    if (isFavorite)
                                        Icons.Default.Favorite
                                    else
                                        Icons.Outlined.FavoriteBorder,

                                contentDescription = "Favorite",

                                tint =
                                    if (isFavorite)
                                        Color.Red
                                    else
                                        Color.Black
                            )
                        }
                    }
                }
            }

            // ✅ CONTENT
            Surface(

                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-24).dp),

                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ),

                color = Color(0xFFFDF6EC)

            ) {

                Column(

                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()

                ) {

                    // ✅ TAGS
                    Row(

                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)

                    ) {

                        AssistChip(

                            onClick = { },

                            label = {

                                Text("📅 ${location.day}")
                            }
                        )

                        AssistChip(

                            onClick = { },

                            label = {

                                Text(
                                    "⭐ ${
                                        location.type.uppercase()
                                    }"
                                )
                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    // ✅ MARKET NAME
                    Text(

                        text = location.name,

                        style =
                            MaterialTheme.typography.headlineMedium,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    // ✅ LOCATION
                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFFE67E22)
                        )

                        Spacer(
                            modifier = Modifier.width(6.dp)
                        )

                        Text(
                            text =
                                "${location.lat}, ${location.lng}"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // ✅ SANTHE DAY CARD
                    Surface(

                        shape = RoundedCornerShape(14.dp),

                        color = Color(0xFFE8F5E9)

                    ) {

                        Text(

                            text =
                                "📅 Santhe Day: ${location.day}",

                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),

                            color = Color(0xFF1B5E20),

                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    HorizontalDivider()

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    // ✅ ABOUT
                    Text(

                        text = "About this Santhe",

                        style =
                            MaterialTheme.typography.titleLarge,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(

                        text =
                            location.description +

                                    "\n\nThis Santhe is one of Karnataka’s traditional weekly markets where local farmers, artisans, and small businesses sell authentic regional products.",

                        fontSize = 16.sp,

                        lineHeight = 24.sp
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // ✅ SPECIALTY CARD
                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(20.dp),

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    Color(0xFFFFF3CD)
                            )
                    ) {

                        Row(

                            modifier =
                                Modifier.padding(20.dp),

                            verticalAlignment =
                                Alignment.CenterVertically

                        ) {

                            Text(
                                text = "🛍️",
                                fontSize = 28.sp
                            )

                            Spacer(
                                modifier = Modifier.width(14.dp)
                            )

                            Column {

                                Text(
                                    text = "Market Specialty"
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(

                                    text = location.specialty,

                                    fontWeight = FontWeight.Bold,

                                    fontSize = 18.sp
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    // ✅ REVIEWS
                    Text(

                        text = "Visitor Reviews",

                        style =
                            MaterialTheme.typography.titleLarge,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    ReviewCard(
                        name = "Ravi Kumar",
                        review = "Amazing traditional market with authentic local products.",
                        rating = 5
                    )

                    ReviewCard(
                        name = "Anjali",
                        review = "Loved the atmosphere and fresh spices.",
                        rating = 4
                    )

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    // ✅ MAP BUTTON
                    Button(

                        onClick = {

                            navController.navigate(
                                "map?locationName=${location.name}"
                            )
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape =
                            RoundedCornerShape(16.dp)

                    ) {

                        Icon(
                            Icons.Default.Map,
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            "Explore on Map"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(50.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewCard(

    name: String,

    review: String,

    rating: Int

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                text = name,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "⭐".repeat(rating)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = review
            )
        }
    }
}