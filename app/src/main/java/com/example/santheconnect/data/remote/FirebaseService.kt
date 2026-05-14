package com.example.santheconnect.data.remote

import android.util.Log

import com.example.santheconnect.data.model.Location

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class FirebaseService {

    companion object {

        private const val TAG = "FirebaseService"

        private const val COLLECTION_NAME = "locations"
    }

    // ✅ Firestore instance
    private val db: FirebaseFirestore by lazy {

        FirebaseFirestore.getInstance()
    }

    // ✅ ADD LOCATION
    fun addLocation(

        location: Location,

        onSuccess: () -> Unit = {},

        onFailure: (Exception) -> Unit = {}

    ) {

        try {

            db.collection(COLLECTION_NAME)

                .add(location)

                .addOnSuccessListener {

                    Log.d(
                        TAG,
                        "Location added: ${location.name}"
                    )

                    onSuccess()
                }

                .addOnFailureListener { e ->

                    Log.e(
                        TAG,
                        "Add failed: ${e.message}"
                    )

                    onFailure(e)
                }

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Exception while adding: ${e.message}"
            )

            onFailure(e)
        }
    }

    // ✅ FETCH LOCATIONS
    fun getLocations(

        onResult: (List<Location>) -> Unit

    ) {

        try {

            db.collection(COLLECTION_NAME)

                .get()

                .addOnSuccessListener { result ->

                    val locations =
                        result.documents.mapNotNull { document ->

                            try {

                                document.toObject(
                                    Location::class.java
                                )

                            } catch (e: Exception) {

                                Log.e(
                                    TAG,
                                    "Parse error: ${e.message}"
                                )

                                null
                            }
                        }

                    Log.d(
                        TAG,
                        "Fetched ${locations.size} locations"
                    )

                    onResult(
                        locations.distinctBy { it.name }
                    )
                }

                .addOnFailureListener { e ->

                    Log.e(
                        TAG,
                        "Fetch failed: ${e.message}"
                    )

                    onResult(emptyList())
                }

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Exception while fetching: ${e.message}"
            )

            onResult(emptyList())
        }
    }

    // ✅ REALTIME LISTENER
    fun listenToLocations(

        onUpdate: (List<Location>) -> Unit

    ): ListenerRegistration? {

        return try {

            db.collection(COLLECTION_NAME)

                .addSnapshotListener { snapshot, error ->

                    if (error != null) {

                        Log.e(
                            TAG,
                            "Realtime error: ${error.message}"
                        )

                        onUpdate(emptyList())

                        return@addSnapshotListener
                    }

                    val locations =
                        snapshot?.documents?.mapNotNull { document ->

                            try {

                                document.toObject(
                                    Location::class.java
                                )

                            } catch (e: Exception) {

                                Log.e(
                                    TAG,
                                    "Realtime parse error: ${e.message}"
                                )

                                null
                            }

                        } ?: emptyList()

                    Log.d(
                        TAG,
                        "Realtime update: ${locations.size}"
                    )

                    onUpdate(
                        locations.distinctBy { it.name }
                    )
                }

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Listener failed: ${e.message}"
            )

            null
        }
    }
}