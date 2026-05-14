package com.example.santheconnect.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel

import com.example.santheconnect.data.model.Location
import com.example.santheconnect.data.remote.FirebaseService
import com.example.santheconnect.utils.DummyData

import com.google.firebase.firestore.ListenerRegistration

class LocationViewModel : ViewModel() {


    private val firebaseService = FirebaseService()


    private var allLocations =
        mutableListOf<Location>()

    // ✅ FIRESTORE LISTENER
    private var listenerRegistration:
            ListenerRegistration? = null

    // ✅ SEARCH
    var searchQuery by mutableStateOf("")

    // ✅ CATEGORY FILTER
    var selectedCategory by mutableStateOf("all")

    // ✅ DAY FILTER
    var selectedDay by mutableStateOf("All")

    // ✅ LOADING
    var isRefreshing by mutableStateOf(false)

    // ✅ LOCATIONS STATE
    private val _locations =
        mutableStateOf<List<Location>>(emptyList())

    val locations: State<List<Location>>
            = _locations

    // ✅ FAVORITES
    private val _favoriteNames =
        mutableStateOf<Set<String>>(emptySet())

    val favoriteNames:
            State<Set<String>>
            = _favoriteNames

    // ✅ INIT
    init {

        // ✅ LOAD DUMMY DATA FIRST
        allLocations =

            DummyData.locations
                .distinctBy { it.name.trim() }
                .toMutableList()

        applyFilters()

        // ✅ FIREBASE REALTIME
        listenToFirebase()
    }

    // ✅ FIREBASE REALTIME LISTENER
    private fun listenToFirebase() {

        isRefreshing = true

        try {

            listenerRegistration =

                firebaseService.listenToLocations {

                        fetchedLocations ->

                    if (fetchedLocations.isNotEmpty()) {

                        allLocations =

                            fetchedLocations

                                .distinctBy {
                                    it.name.trim()
                                }

                                .toMutableList()

                    } else {

                        // ✅ FALLBACK
                        allLocations =

                            DummyData.locations

                                .distinctBy {
                                    it.name.trim()
                                }

                                .toMutableList()
                    }

                    applyFilters()

                    isRefreshing = false
                }

        } catch (e: Exception) {

            e.printStackTrace()

            isRefreshing = false
        }
    }

    // ✅ APPLY FILTERS
    fun applyFilters() {

        _locations.value =

            allLocations.filter { location ->

                // ✅ SEARCH
                val matchesSearch =

                    location.name
                        .trim()
                        .contains(

                            searchQuery.trim(),

                            ignoreCase = true
                        ) ||

                            location.description
                                .trim()
                                .contains(

                                    searchQuery.trim(),

                                    ignoreCase = true
                                )

                // ✅ CATEGORY
                val matchesCategory =

                    if (

                        selectedCategory
                            .trim()
                            .equals(
                                "all",
                                ignoreCase = true
                            )

                    ) {

                        true

                    } else {

                        location.type
                            .trim()
                            .equals(

                                selectedCategory.trim(),

                                ignoreCase = true
                            )
                    }

                // ✅ DAY FILTER FIXED
                val matchesDay =

                    if (

                        selectedDay
                            .trim()
                            .equals(
                                "All",
                                ignoreCase = true
                            )

                    ) {

                        true

                    } else {

                        location.day
                            .trim()
                            .equals(

                                selectedDay.trim(),

                                ignoreCase = true
                            )
                    }

                // ✅ FINAL RESULT
                matchesSearch &&
                        matchesCategory &&
                        matchesDay
            }
    }

    // ✅ TOGGLE FAVORITE
    fun toggleFavorite(
        locationName: String
    ) {

        val current =
            _favoriteNames.value.toMutableSet()

        if (
            current.contains(locationName)
        ) {

            current.remove(locationName)

        } else {

            current.add(locationName)
        }

        _favoriteNames.value = current
    }

    // ✅ ADD LOCATION
    fun addLocation(
        location: Location
    ) {

        try {

            firebaseService.addLocation(

                location = location,

                onSuccess = {

                    refreshData()
                }
            )

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    // ✅ REFRESH DATA
    fun refreshData() {

        isRefreshing = true

        try {

            firebaseService.getLocations {

                    fetchedLocations ->

                if (fetchedLocations.isNotEmpty()) {

                    allLocations =

                        fetchedLocations

                            .distinctBy {
                                it.name.trim()
                            }

                            .toMutableList()

                } else {

                    // ✅ FALLBACK
                    allLocations =

                        DummyData.locations

                            .distinctBy {
                                it.name.trim()
                            }

                            .toMutableList()
                }

                applyFilters()

                isRefreshing = false
            }

        } catch (e: Exception) {

            e.printStackTrace()

            isRefreshing = false
        }
    }

    // ✅ LOAD DATA
    fun loadData() {

        refreshData()
    }

    // ✅ FILTER DAY
    fun filterByDay(
        day: String
    ) {

        selectedDay = day.trim()

        applyFilters()
    }

    // ✅ FILTER CATEGORY
    fun filterByCategory(
        category: String
    ) {

        selectedCategory = category.trim()

        applyFilters()
    }

    // ✅ UPDATE SEARCH
    fun updateSearchQuery(
        query: String
    ) {

        searchQuery = query.trim()

        applyFilters()
    }

    // ✅ RESET FILTERS
    fun resetFilters() {

        searchQuery = ""

        selectedCategory = "all"

        selectedDay = "All"

        applyFilters()
    }

    // ✅ SEED DATABASE
    fun seedDatabase(
        onComplete: () -> Unit = {}
    ) {

        isRefreshing = true

        var count = 0

        val uniqueLocations =

            DummyData.locations
                .distinctBy { it.name.trim() }

        uniqueLocations.forEach { location ->

            firebaseService.addLocation(

                location = location,

                onSuccess = {

                    count++

                    if (
                        count == uniqueLocations.size
                    ) {

                        refreshData()

                        onComplete()
                    }
                }
            )
        }
    }

    // ✅ CLEAR LISTENER
    override fun onCleared() {

        super.onCleared()

        listenerRegistration?.remove()
    }
}