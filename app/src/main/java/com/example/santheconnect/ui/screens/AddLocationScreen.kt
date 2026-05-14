package com.example.santheconnect.ui.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.data.model.Location
import com.example.santheconnect.viewmodel.LocationViewModel
import com.example.santheconnect.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLocationScreen(navController: NavController, viewModel: LocationViewModel) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var specialty by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("Monday") }
    var type by remember { mutableStateOf("market") }
    var lat by remember { mutableStateOf("12.9716") }
    var lng by remember { mutableStateOf("77.5946") }
    var address by remember { mutableStateOf("") }

    val context = LocalContext.current
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Daily")
    val types = listOf("market", "food", "craft")

    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            TopAppBar(
                title = { Text("Add New Santhe", style = Typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 📸 Modern Image Upload Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .border(
                        border = BorderStroke(2.dp, PrimaryOrange.copy(alpha = 0.2f)),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { Toast.makeText(context, "Feature coming soon", Toast.LENGTH_SHORT).show() },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = PrimaryOrange
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Add Market Photo", style = Typography.labelLarge, color = TextPrimary)
                    Text("Professional quality image preferred", style = Typography.labelSmall, color = TextSecondary)
                }
            }

            // Form Fields
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Santhe Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange)
            )

            // Category Selection
            Column {
                Text("Category", style = Typography.labelLarge, color = TextPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    types.forEach { t ->
                        FilterChip(
                            selected = type == t,
                            onClick = { type = t },
                            label = { Text(t.uppercase(), fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PrimaryOrange,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            OutlinedTextField(
                value = specialty,
                onValueChange = { specialty = it },
                label = { Text("Special Item (e.g. Silk, Organic Ragi)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange)
            )
            
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("District / Area") },
                placeholder = { Text("e.g. Mandya, Karnataka") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (name.isBlank() || description.isBlank()) {
                        Toast.makeText(context, "Please fill basic details", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val location = Location(
                        name = name,
                        description = description,
                        specialty = specialty,
                        day = day,
                        type = type,
                        lat = lat.toDoubleOrNull() ?: 12.9716,
                        lng = lng.toDoubleOrNull() ?: 77.5946,
                        address = address.ifEmpty { "Karnataka, India" }
                    )
                    viewModel.addLocation(location)
                    Toast.makeText(context, "Market added successfully!", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
            ) {
                Text("Publish Santhe", style = Typography.titleMedium, color = Color.White)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
