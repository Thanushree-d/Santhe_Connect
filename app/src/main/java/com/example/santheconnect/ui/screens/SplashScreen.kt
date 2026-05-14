package com.example.santheconnect.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.R
import com.example.santheconnect.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        // Run animations concurrently
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = { t ->
                        // Cubic Overshoot helper
                        val s = 2.0f
                        val t1 = t - 1.0f
                        t1 * t1 * ((s + 1) * t1 + s) + 1.0f
                    }
                )
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
        delay(2500L)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BackgroundCream, Color.White)
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            // Premium Logo Container
            Surface(
                modifier = Modifier.size(120.dp),
                shape = RoundedCornerShape(28.dp),
                color = PrimaryOrange,
                shadowElevation = 12.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    // ✅ FIXED: Using the actual drawable name found in drawables folder
                    Image(
                        painter = painterResource(id = R.drawable.snathe_logo),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Santhe Connect",
                style = Typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary,
                    letterSpacing = 1.sp
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Authentic Karnataka Markets",
                style = Typography.bodyMedium.copy(
                    color = TextSecondary,
                    fontWeight = FontWeight.Medium
                )
            )
            
            Spacer(modifier = Modifier.height(64.dp))
            
            // Subtle Loading Progress
            LinearProgressIndicator(
                modifier = Modifier
                    .width(140.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(100.dp)),
                color = PrimaryOrange,
                trackColor = PrimaryOrange.copy(alpha = 0.1f)
            )
        }
    }
}
