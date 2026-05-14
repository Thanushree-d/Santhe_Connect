package com.example.santheconnect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person

import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(

    navController: NavController

) {

    val currentRoute =
        navController.currentBackStackEntryAsState()
            .value?.destination?.route

    val primaryOrange = Color(0xFFF59E0B)

    val textPrimary = Color(0xFF2E1F1A)

    val textSecondary = Color(0xFF6B7280)

    Box(

        modifier = Modifier.fillMaxWidth()

    ) {

        // ✅ MAIN BOTTOM BAR
        NavigationBar(

            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .navigationBarsPadding(),

            containerColor = Color.White,

            tonalElevation = 10.dp

        ) {

            BottomItem(

                selected = currentRoute == "home",

                label = "Home",

                selectedIcon = Icons.Default.Home,

                unselectedIcon = Icons.Outlined.Home,

                primaryOrange = primaryOrange,

                textPrimary = textPrimary,

                textSecondary = textSecondary

            ) {

                navController.navigate("home")
            }

            BottomItem(

                selected =
                    currentRoute?.startsWith("map") == true,

                label = "Map",

                selectedIcon = Icons.Default.Map,

                unselectedIcon = Icons.Outlined.Map,

                primaryOrange = primaryOrange,

                textPrimary = textPrimary,

                textSecondary = textSecondary

            ) {

                navController.navigate("map")
            }

            // ✅ EMPTY CENTER SPACE
            Box(
                modifier = Modifier.weight(1f)
            )

            BottomItem(

                selected = currentRoute == "favorites",

                label = "Saved",

                selectedIcon = Icons.Default.Bookmark,

                unselectedIcon =
                    Icons.Outlined.BookmarkBorder,

                primaryOrange = primaryOrange,

                textPrimary = textPrimary,

                textSecondary = textSecondary

            ) {

                navController.navigate("favorites")
            }

            BottomItem(

                selected = currentRoute == "profile",

                label = "Profile",

                selectedIcon = Icons.Default.Person,

                unselectedIcon = Icons.Outlined.Person,

                primaryOrange = primaryOrange,

                textPrimary = textPrimary,

                textSecondary = textSecondary

            ) {

                navController.navigate("profile")
            }
        }

        // ✅ FLOATING CENTER BUTTON
        IconButton(

            onClick = {

                navController.navigate("add")
            },

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-18).dp)
                .size(62.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = CircleShape
                )
                .background(
                    color = primaryOrange,
                    shape = CircleShape
                )

        ) {

            Text(

                text = "+",

                color = Color.White,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(

    selected: Boolean,

    label: String,

    selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,

    unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,

    primaryOrange: Color,

    textPrimary: Color,

    textSecondary: Color,

    onClick: () -> Unit

) {

    NavigationBarItem(

        selected = selected,

        onClick = onClick,

        icon = {

            Icon(

                imageVector =

                    if (selected)
                        selectedIcon
                    else
                        unselectedIcon,

                contentDescription = label,

                modifier = Modifier.size(22.dp)
            )
        },

        label = {

            Text(

                text = label,

                fontSize = 10.sp,

                fontWeight =

                    if (selected)
                        FontWeight.Bold
                    else
                        FontWeight.Medium
            )
        },

        colors = NavigationBarItemDefaults.colors(

            selectedIconColor = primaryOrange,

            selectedTextColor = textPrimary,

            unselectedIconColor = textSecondary,

            unselectedTextColor = textSecondary,

            indicatorColor = Color(0xFFFFF3CD)
        )
    )
}