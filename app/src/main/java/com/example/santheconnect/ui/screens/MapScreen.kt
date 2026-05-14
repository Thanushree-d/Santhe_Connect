package com.example.santheconnect.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.data.model.Location
import com.example.santheconnect.ui.theme.*
import com.example.santheconnect.viewmodel.LocationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.google.accompanist.permissions.*

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: LocationViewModel,
    targetLocationName: String? = null
) {
    val locations = viewModel.locations.value
    var selectedLocation by remember { mutableStateOf<Location?>(null) }

    val markerStates = remember(locations) {
        locations.associate { it.name to MarkerState(position = LatLng(it.lat, it.lng)) }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(12.9716, 77.5946), // Bangalore default
            8f
        )
    }

    LaunchedEffect(targetLocationName, locations) {
        if (!targetLocationName.isNullOrEmpty()) {
            val target = locations.find { it.name.trim().equals(targetLocationName.trim(), ignoreCase = true) }
            if (target != null) {
                selectedLocation = target
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(
                        LatLng(target.lat, target.lng),
                        14f
                    ),
                    durationMs = 1500
                )
                markerStates[target.name]?.showInfoWindow()
            }
        }
    }

    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    Scaffold(
        topBar = {
            Surface(
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Explore Markets",
                        style = Typography.titleLarge,
                        color = TextPrimary
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = locationPermissionState.status.isGranted,
                    mapType = MapType.NORMAL
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = true
                )
            ) {
                locations.forEach { location ->
                    val markerState = markerStates[location.name] ?: MarkerState(position = LatLng(location.lat, location.lng))
                    
                    Marker(
                        state = markerState,
                        title = location.name,
                        snippet = location.specialty,
                        icon = BitmapDescriptorFactory.defaultMarker(
                            if (location.type.lowercase() == "food") BitmapDescriptorFactory.HUE_ORANGE 
                            else BitmapDescriptorFactory.HUE_GREEN
                        ),
                        onClick = {
                            selectedLocation = location
                            false
                        }
                    )
                }
            }

            // Professional Overlay Card
            AnimatedVisibility(
                visible = selectedLocation != null,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                selectedLocation?.let { location ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .shadow(12.dp, RoundedCornerShape(24.dp)),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        location.name, 
                                        style = Typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        "📍 ${location.address}",
                                        style = Typography.bodySmall,
                                        color = TextSecondary,
                                        fontSize = 13.sp
                                    )
                                }
                                IconButton(
                                    onClick = { selectedLocation = null },
                                    modifier = Modifier
                                        .background(SurfaceCream, CircleShape)
                                        .size(32.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Close, 
                                        contentDescription = "Close", 
                                        modifier = Modifier.size(16.dp),
                                        tint = TextPrimary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Surface(color = HighlightYellow, shape = RoundedCornerShape(100.dp)) {
                                    Text(
                                        "Special: ${location.specialty}",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = Typography.labelSmall,
                                        color = HighlightBrown,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Surface(color = SoftGreen, shape = RoundedCornerShape(100.dp)) {
                                    Text(
                                        "📅 ${location.day}",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = Typography.labelSmall,
                                        color = SuccessGreen,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = { navController.navigate("details/${location.name}") },
                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                            ) {
                                Text("View Full Details", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
