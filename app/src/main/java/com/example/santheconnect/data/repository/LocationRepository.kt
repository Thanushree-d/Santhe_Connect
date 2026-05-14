package com.example.santheconnect.data.repository

import com.example.santheconnect.data.model.Location
import com.example.santheconnect.utils.DummyData

class LocationRepository {

    // Get all locations (currently from dummy data)
    fun getLocations(): List<Location> {
        return DummyData.locations
    }

    // Filter locations by day (important feature)
    fun getLocationsByDay(day: String): List<Location> {
        return DummyData.locations.filter {
            it.day.equals(day, ignoreCase = true)
        }
    }
}
