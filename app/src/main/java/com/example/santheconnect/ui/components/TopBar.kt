package com.example.santheconnect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.santheconnect.R
import com.example.santheconnect.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarAdvanced(
    onNotificationClick: () -> Unit = {}
) {
    Surface(
        color = BackgroundCream,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ APP LOGO (Corrected name from drawables)
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(14.dp),
                shadowElevation = 6.dp,
                color = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.snathe_logo),
                    contentDescription = "Santhe Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // ✅ APP TITLE
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Santhe Connect",
                    style = Typography.titleLarge.copy(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    ),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Authentic Karnataka Markets",
                    style = Typography.labelSmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                )
            }

            // ✅ NOTIFICATION BUTTON
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .size(46.dp)
                    .background(Color.White, RoundedCornerShape(14.dp))
            ) {
                Box {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = TextPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    // ✅ Notification Dot
                    Surface(
                        modifier = Modifier
                            .size(11.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp),
                        shape = RoundedCornerShape(50),
                        color = PrimaryOrange,
                        border = BorderStroke(2.dp, Color.White)
                    ) {}
                }
            }
        }
    }
}
