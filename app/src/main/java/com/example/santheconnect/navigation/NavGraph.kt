package com.example.santheconnect.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.santheconnect.ui.screens.*
import com.example.santheconnect.viewmodel.LocationViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    // ✅ Shared ViewModel
    val viewModel: LocationViewModel = viewModel()

    NavHost(

        navController = navController,

        startDestination = "splash"

    ) {

        // ✅ SPLASH SCREEN
        composable("splash") {

            SplashScreen(navController)
        }

        // ✅ HOME SCREEN
        composable("home") {

            HomeScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ MAP SCREEN
        composable(

            route = "map?locationName={locationName}",

            arguments = listOf(

                navArgument("locationName") {

                    type = NavType.StringType

                    nullable = true

                    defaultValue = ""
                }
            )

        ) { backStackEntry ->

            val locationName =

                backStackEntry.arguments
                    ?.getString("locationName") ?: ""

            MapScreen(

                navController = navController,

                viewModel = viewModel,

                targetLocationName = locationName
            )
        }

        // ✅ ADD MARKET SCREEN
        composable("add") {

            AddLocationScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ FAVORITES SCREEN
        composable("favorites") {

            ReviewScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ PROFILE SCREEN
        composable("profile") {

            ProfileScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ AI TIPS SCREEN
        composable("ai_tips") {

            AITipsScreen(navController)
        }

        // ✅ NOTIFICATION SCREEN
        composable("notifications") {

            NotificationScreen(navController)
        }

        // ✅ CALENDAR SCREEN
        composable("calendar") {

            CalendarScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ ACCOUNT SETTINGS SCREEN
        composable("account_settings") {

            AccountSettingsScreen(
                navController = navController
            )
        }

        // ✅ HELP & SUPPORT SCREEN
        composable("help_support") {

            HelpSupportScreen(
                navController = navController
            )
        }

        // ✅ ABOUT SANTHE SCREEN
        composable("about_santhe") {

            AboutSantheScreen(
                navController = navController
            )
        }

        // ✅ SAVED FAVORITES SCREEN
        composable("saved_favorites") {

            ReviewScreen(

                navController = navController,

                viewModel = viewModel
            )
        }

        // ✅ MY VISITS SCREEN
        composable("my_visits") {

            VisitsScreen(
                navController = navController
            )
        }

        // ✅ SETTINGS SCREEN
        composable("settings") {

            AccountSettingsScreen(
                navController = navController
            )
        }

        // ✅ ABOUT APP SCREEN
        composable("about") {

            AboutSantheScreen(
                navController = navController
            )
        }

        // ✅ DETAILS SCREEN
        composable(

            route = "details/{locationName}",

            arguments = listOf(

                navArgument("locationName") {

                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->

            val locationName = java.net.URLDecoder.decode(

                backStackEntry.arguments
                    ?.getString("locationName") ?: "",

                "UTF-8"
            )

            // ✅ SAFE LOCATION LOOKUP
            val location =

                viewModel.locations.value.find {

                    it.name.trim().equals(

                        locationName.trim(),

                        ignoreCase = true
                    )
                }

            if (location != null) {

                DetailsScreen(

                    navController = navController,

                    viewModel = viewModel,

                    location = location
                )

            } else {

                PlaceholderScreen(

                    navController = navController,

                    title = "Location Not Found",

                    description =
                        "The selected Santhe could not be found. Please try again from the home screen."
                )
            }
        }
    }
}