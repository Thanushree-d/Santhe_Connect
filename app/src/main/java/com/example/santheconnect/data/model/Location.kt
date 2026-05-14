package com.example.santheconnect.data.model

data class Location(
    val name: String = "",
    val description: String = "",
    val specialty: String = "",
    val day: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val type: String = "",
    val imageUrl: String = "",
    val address: String = "Karnataka, India",
    val distance: String = "12.5 km"
)
