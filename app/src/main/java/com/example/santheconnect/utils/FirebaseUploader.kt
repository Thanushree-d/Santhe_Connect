package com.example.santheconnect.utils

import android.util.Log

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUploader {

    private const val TAG = "FirebaseUploader"

    fun uploadDummyData() {

        val db = FirebaseFirestore.getInstance()

        val collection = db.collection("locations")

        Log.d(TAG, "Starting dummy data upload...")

        // ✅ LOOP THROUGH ALL 50 DATA
        DummyData.locations.forEach { location ->

            // ✅ CHECK DUPLICATE USING NAME
            collection
                .whereEqualTo("name", location.name)
                .get()

                .addOnSuccessListener { result ->

                    // ✅ UPLOAD ONLY IF NOT EXISTS
                    if (result.isEmpty) {

                        collection
                            .add(location)

                            .addOnSuccessListener {

                                Log.d(

                                    TAG,

                                    "Uploaded: ${location.name}"
                                )
                            }

                            .addOnFailureListener { e ->

                                Log.e(

                                    TAG,

                                    "Upload failed: ${location.name}"
                                )

                                Log.e(

                                    TAG,

                                    e.message ?: "Unknown error"
                                )
                            }

                    } else {

                        // ✅ SKIP DUPLICATES
                        Log.d(

                            TAG,

                            "Already exists: ${location.name}"
                        )
                    }
                }

                .addOnFailureListener { e ->

                    Log.e(

                        TAG,

                        "Check failed for ${location.name}"
                    )

                    Log.e(

                        TAG,

                        e.message ?: "Unknown error"
                    )
                }
        }

        Log.d(TAG, "Dummy upload process completed")
    }
}