package com.example.santheconnect.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    val textSecondary = Color(0xFF6B7280)
    val primaryOrange = Color(0xFFF59E0B)

    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { 
            Text(
                "Search Santhe...", 
                color = textSecondary,
                fontSize = 15.sp
            ) 
        },
        leadingIcon = { 
            Icon(
                Icons.Default.Search, 
                contentDescription = null,
                tint = textSecondary,
                modifier = Modifier.size(22.dp)
            ) 
        },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(14.dp),
                spotColor = Color(0x0D000000)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = primaryOrange,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true
    )
}
