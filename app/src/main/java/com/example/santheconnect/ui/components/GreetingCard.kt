package com.example.santheconnect.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingCard(

    // ✅ REFRESH CALLBACK
    onRefresh: () -> Unit = {}

) {

    // ✅ COLORS
    val textPrimary = Color(0xFF2E1F1A)

    val textSecondary = Color(0xFF6B7280)

    val primaryOrange = Color(0xFFF59E0B)

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(24.dp)
            ),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )

    ) {

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            // ✅ BACKGROUND ICON
            Icon(

                imageVector = Icons.Default.LocationOn,

                contentDescription = null,

                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = 20.dp)
                    .graphicsLayer(alpha = 0.05f),

                tint = textPrimary
            )

            // ✅ MAIN CONTENT
            Column(

                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(0.78f)

            ) {

                // ✅ TITLE
                Text(

                    text = "Namaskara! 🙏",

                    style =
                        MaterialTheme.typography.headlineSmall.copy(

                            fontSize = 28.sp,

                            fontWeight = FontWeight.Bold,

                            color = textPrimary
                        )
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // ✅ DESCRIPTION
                Text(

                    text =
                        "Explore Karnataka’s authentic Santhe markets, local crafts, fresh foods, and cultural experiences.",

                    style =
                        MaterialTheme.typography.bodyMedium.copy(

                            fontSize = 17.sp,

                            lineHeight = 28.sp,

                            color = textSecondary
                        )
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // ✅ SYNC BUTTON
                Button(

                    onClick = {

                        // 🔥 CALL REFRESH FUNCTION
                        onRefresh()
                    },

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor = primaryOrange
                        ),

                    shape = RoundedCornerShape(16.dp),

                    contentPadding = PaddingValues(

                        horizontal = 28.dp,
                        vertical = 16.dp
                    )

                ) {

                    Text(

                        text = "Sync Latest Markets",

                        fontWeight = FontWeight.Bold,

                        fontSize = 16.sp,

                        color = Color.White
                    )
                }
            }
        }
    }
}