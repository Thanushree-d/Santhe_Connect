package com.example.santheconnect.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AIInsightsCard(onGetTipsClick: () -> Unit) {
    // UI Palette
    val deepGreen = Color(0xFF064E3B) // Darker, professional emerald
    val primaryOrange = Color(0xFFF59E0B)
    val accentYellow = Color(0xFFFBBF24)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = Color(0x1A000000)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = deepGreen)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(0.7f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = accentYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "GEMINI AI INSIGHTS",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp
                        )
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                Text(
                    text = "Personalized tips for Karnataka's traditional bazaars.",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onGetTipsClick,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryOrange),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Get AI Tips ✨",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }
            }
            
            // Robot/AI Illustration placeholder using Emoji
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
            ) {
                Text(
                    text = "🤖", 
                    fontSize = 72.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}
