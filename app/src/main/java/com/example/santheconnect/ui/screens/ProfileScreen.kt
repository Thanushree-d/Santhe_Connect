package com.example.santheconnect.ui.screens

import android.net.Uri
import android.widget.Toast

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout

import androidx.compose.material.icons.filled.*

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

import coil.compose.AsyncImage

import com.example.santheconnect.ui.components.BottomNav
import com.example.santheconnect.ui.components.TopBarAdvanced

import com.example.santheconnect.ui.theme.*

import com.example.santheconnect.viewmodel.LocationViewModel

@Composable
fun ProfileScreen(

    navController: NavController,

    viewModel: LocationViewModel

) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    // ✅ PROFILE STATE
    var userName by remember {

        mutableStateOf("Tourist Explorer")
    }

    var userEmail by remember {

        mutableStateOf("explorer@santhe.in")
    }

    var imageUri by remember {

        mutableStateOf<Uri?>(null)
    }

    val favoriteCount =
        viewModel.favoriteNames.value.size

    // ✅ IMAGE PICKER
    val imagePicker =

        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.GetContent()

        ) { uri ->

            imageUri = uri
        }

    Scaffold(

        containerColor = BackgroundCream,

        topBar = {

            TopBarAdvanced(

                onNotificationClick = {

                    navController.navigate(
                        "notifications"
                    )
                }
            )
        },

        bottomBar = {

            BottomNav(navController)
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // ✅ PROFILE IMAGE
            Box(

                contentAlignment =
                    Alignment.BottomEnd

            ) {

                AsyncImage(

                    model =

                        imageUri
                            ?: "https://i.pravatar.cc/300",

                    contentDescription = "Avatar",

                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),

                    contentScale = ContentScale.Crop
                )

                Surface(

                    modifier = Modifier
                        .size(40.dp)
                        .offset(
                            x = (-4).dp,
                            y = (-4).dp
                        )
                        .clickable {

                            imagePicker.launch(
                                "image/*"
                            )
                        },

                    shape = CircleShape,

                    color = PrimaryOrange,

                    shadowElevation = 6.dp

                ) {

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.CameraAlt,

                            contentDescription = null,

                            tint = Color.White,

                            modifier =
                                Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ✅ NAME
            OutlinedTextField(

                value = userName,

                onValueChange = {

                    userName = it
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp),

                label = {

                    Text("User Name")
                },

                leadingIcon = {

                    Icon(
                        Icons.Default.Person,
                        contentDescription = null
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // ✅ EMAIL
            OutlinedTextField(

                value = userEmail,

                onValueChange = {

                    userEmail = it
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp),

                label = {

                    Text("Email")
                },

                leadingIcon = {

                    Icon(
                        Icons.Default.Email,
                        contentDescription = null
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // ✅ PROFILE STATS
            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.spacedBy(14.dp)

            ) {

                StatCard(
                    title = "Favorites",
                    value = favoriteCount.toString()
                )

                StatCard(
                    title = "Visits",
                    value = "24"
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // ✅ ACTIVITY SECTION
            ProfileSection("Activity") {

                ProfileItem(

                    icon = Icons.Default.History,

                    title = "My Visits"

                ) {

                    navController.navigate(
                        "my_visits"
                    )
                }

                ProfileItem(

                    icon = Icons.Default.Favorite,

                    title = "Saved Favorites"

                ) {

                    navController.navigate(
                        "saved_favorites"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ✅ SETTINGS SECTION
            ProfileSection("App Settings") {

                ProfileItem(

                    icon = Icons.Default.Settings,

                    title = "Account Settings"

                ) {

                    navController.navigate(
                        "account_settings"
                    )
                }

                ProfileItem(

                    icon =
                        Icons.AutoMirrored.Filled.Help,

                    title = "Help & Support"

                ) {

                    navController.navigate(
                        "help_support"
                    )
                }

                ProfileItem(

                    icon = Icons.Default.Info,

                    title = "About Santhe Connect"

                ) {

                    navController.navigate(
                        "about_santhe"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // ✅ SAVE BUTTON
            Button(

                onClick = {

                    Toast.makeText(

                        context,

                        "Profile Saved",

                        Toast.LENGTH_SHORT

                    ).show()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = PrimaryOrange
                )

            ) {

                Text(

                    text = "Save Profile",

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // ✅ LOGOUT BUTTON
            Button(

                onClick = {

                    Toast.makeText(

                        context,

                        "Logged Out Successfully",

                        Toast.LENGTH_SHORT

                    ).show()
                },

                colors = ButtonDefaults.buttonColors(

                    containerColor =
                        Color(0xFFFEE2E2)
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(16.dp)

            ) {

                Row(

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {

                    Icon(

                        Icons.AutoMirrored.Filled.Logout,

                        contentDescription = null,

                        tint = ErrorRed
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(

                        text = "Log Out",

                        color = ErrorRed,

                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(120.dp)
            )
        }
    }
}

@Composable
fun RowScope.StatCard(

    title: String,

    value: String

) {

    Card(

        modifier = Modifier.weight(1f),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Text(

                text = value,

                fontSize = 26.sp,

                fontWeight = FontWeight.Bold,

                color = PrimaryOrange
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = title
            )
        }
    }
}

@Composable
fun ProfileSection(

    title: String,

    content: @Composable ColumnScope.() -> Unit

) {

    Column(

        modifier = Modifier.fillMaxWidth()

    ) {

        Text(

            text = title,

            style = Typography.labelLarge.copy(

                fontWeight = FontWeight.Bold,

                color = TextSecondary,

                letterSpacing = 1.sp
            ),

            modifier = Modifier.padding(
                start = 4.dp,
                bottom = 12.dp
            )
        )

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )

        ) {

            Column {

                content()
            }
        }
    }
}

@Composable
fun ProfileItem(

    icon: ImageVector,

    title: String,

    onClick: () -> Unit

) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                onClick()
            }
            .padding(
                horizontal = 16.dp,
                vertical = 18.dp
            ),

        verticalAlignment =
            Alignment.CenterVertically

    ) {

        Box(

            modifier = Modifier
                .size(42.dp)
                .background(
                    PrimaryOrange.copy(alpha = 0.1f),
                    RoundedCornerShape(12.dp)
                ),

            contentAlignment =
                Alignment.Center

        ) {

            Icon(

                icon,

                contentDescription = null,

                tint = PrimaryOrange,

                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Text(

            text = title,

            style = Typography.bodyLarge.copy(

                fontWeight = FontWeight.SemiBold,

                color = TextPrimary
            ),

            modifier = Modifier.weight(1f)
        )

        Icon(

            Icons.Default.ChevronRight,

            contentDescription = null,

            tint = Color.LightGray
        )
    }
}