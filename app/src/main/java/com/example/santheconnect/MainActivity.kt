package com.example.santheconnect

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.santheconnect.navigation.NavGraph
import com.example.santheconnect.ui.theme.SantheConnectTheme
import com.example.santheconnect.utils.FirebaseUploader

import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        
        initializeFirebase()

        // ✅ Upload dummy data safely
        uploadInitialData()

        // ✅ Compose UI
        setContent {

            SantheConnectTheme {

                NavGraph()
            }
        }
    }

    // ✅ FIREBASE INIT
    private fun initializeFirebase() {

        try {

            if (FirebaseApp.getApps(this).isEmpty()) {

                FirebaseApp.initializeApp(this)

                Log.d(
                    "MainActivity",
                    "Firebase initialized successfully"
                )

            } else {

                Log.d(
                    "MainActivity",
                    "Firebase already initialized"
                )
            }

        } catch (e: Exception) {

            Log.e(

                "MainActivity",

                "Firebase initialization failed: ${e.message}"
            )
        }
    }

    // ✅ INITIAL DUMMY DATA UPLOAD
    private fun uploadInitialData() {

        try {

            val db = FirebaseFirestore.getInstance()

            db.collection("locations")

                .get()

                .addOnSuccessListener { snapshot ->

                    Log.d(

                        "MainActivity",

                        "Firestore docs count: ${snapshot.size()}"
                    )

                    // ✅ Upload if less than 50 records
                    if (snapshot.size() < 50) {

                        FirebaseUploader.uploadDummyData()

                        Log.d(

                            "MainActivity",

                            "Uploading missing Santhe data..."
                        )

                    } else {

                        Log.d(

                            "MainActivity",

                            "All Santhe data already uploaded"
                        )
                    }
                }

                .addOnFailureListener { e ->

                    Log.e(

                        "MainActivity",

                        "Firestore read failed: ${e.message}"
                    )
                }

        } catch (e: Exception) {

            Log.e(

                "MainActivity",

                "Upload process failed: ${e.message}"
            )
        }
    }
}