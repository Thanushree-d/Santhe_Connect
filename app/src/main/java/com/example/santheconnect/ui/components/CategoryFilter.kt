package com.example.santheconnect.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFilter(
    selected: String,
    onSelect: (String) -> Unit
) {
    val categories = listOf(
        "all" to Icons.Default.AutoAwesome,
        "food" to Icons.Default.Fastfood,
        "market" to Icons.Default.Storefront,
        "craft" to Icons.Default.Palette
    )

    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            color = Color(0xFFFB8C00) // Matching theme
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { (category, icon) ->
                FilterChip(
                    selected = selected == category,
                    onClick = { onSelect(category) },
                    label = { Text(category.uppercase()) },
                    leadingIcon = { Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    border = null,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF673AB7).copy(alpha = 0.1f),
                        selectedLabelColor = Color(0xFF673AB7),
                        selectedLeadingIconColor = Color(0xFF673AB7),
                        containerColor = Color(0xFFF5F5F5)
                    )
                )
            }
        }
    }
}
