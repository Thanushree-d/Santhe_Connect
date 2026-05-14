package com.example.santheconnect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.BookmarkBorder

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage

import com.example.santheconnect.data.model.Location
import com.example.santheconnect.ui.theme.PrimaryOrange
import com.example.santheconnect.ui.theme.Typography

@Composable
fun LocationCard(

    location: Location,

    isFavorite: Boolean = false,

    onToggleFavorite: () -> Unit = {},

    onClick: () -> Unit

) {

    // ✅ COLORS
    val textPrimary = Color(0xFF2E1F1A)

    val textSecondary = Color(0xFF6B7280)

    val highlightYellow = Color(0xFFFFF3CD)

    val highlightBrown = Color(0xFF8B5E00)

    val secondaryGreen = Color(0xFFE8F5E9)

    val successGreenText = Color(0xFF1B5E20)

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {

                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.Top

        ) {

            // ✅ IMAGE SECTION
            AsyncImage(

                model =

                    if (
                        location.imageUrl.startsWith("http")
                    )
                        location.imageUrl
                    else
                        "https://picsum.photos/400/300",

                contentDescription = location.name,

                modifier = Modifier
                    .size(110.dp)
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(
                        Color(0xFFF3F4F6)
                    ),

                contentScale = ContentScale.Crop,

                placeholder =
                    painterResource(
                        id = android.R.drawable.ic_menu_gallery
                    ),

                error =
                    painterResource(
                        id = android.R.drawable.ic_menu_report_image
                    )
            )

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            // ✅ RIGHT SIDE
            Column(

                modifier = Modifier.weight(1f)

            ) {

                // ✅ TITLE ROW
                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.Top

                ) {

                    Text(

                        text = location.name,

                        style =
                            Typography.titleMedium.copy(

                                fontSize = 20.sp,

                                fontWeight = FontWeight.Bold,

                                color = textPrimary
                            ),

                        maxLines = 1,

                        overflow = TextOverflow.Ellipsis,

                        modifier = Modifier.weight(1f)
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    IconButton(

                        onClick = {

                            onToggleFavorite()
                        },

                        modifier = Modifier.size(26.dp)

                    ) {

                        Icon(

                            imageVector =

                                if (isFavorite)
                                    Icons.Default.Bookmark
                                else
                                    Icons.Outlined.BookmarkBorder,

                            contentDescription = "Favorite",

                            tint =

                                if (isFavorite)
                                    PrimaryOrange
                                else
                                    textSecondary
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                // ✅ LOCATION
                Row(

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {

                    Icon(

                        imageVector =
                            Icons.Default.LocationOn,

                        contentDescription = null,

                        tint = textSecondary,

                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(

                        text =
                            "${location.lat}, ${location.lng}",

                        style =
                            Typography.bodySmall.copy(

                                fontSize = 13.sp,

                                color = textSecondary
                            ),

                        maxLines = 1,

                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // ✅ DESCRIPTION
                Text(

                    text =
                        if (location.description.isNotBlank())
                            location.description
                        else
                            "Traditional Karnataka Santhe market.",

                    style =
                        Typography.bodySmall.copy(

                            fontSize = 14.sp,

                            lineHeight = 20.sp,

                            color = textSecondary
                        ),

                    maxLines = 2,

                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // ✅ BOTTOM TAGS
                Row(

                    modifier = Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {

                    // ✅ SPECIAL CHIP
                    Surface(

                        modifier = Modifier.weight(1f),

                        shape =
                            RoundedCornerShape(100.dp),

                        color = highlightYellow

                    ) {

                        Text(

                            text =
                                "Special: ${
                                    if (location.specialty.isNotBlank())
                                        location.specialty
                                    else
                                        "Local Products"
                                }",

                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 7.dp
                            ),

                            style =
                                Typography.labelSmall.copy(

                                    fontSize = 11.sp,

                                    fontWeight =
                                        FontWeight.SemiBold,

                                    color = highlightBrown
                                ),

                            maxLines = 1,

                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    // ✅ DAY CHIP
                    Surface(

                        shape =
                            RoundedCornerShape(100.dp),

                        color = secondaryGreen

                    ) {

                        Text(

                            text =
                                "📅 ${
                                    if (location.day.isNotBlank())
                                        location.day
                                    else
                                        "Sunday"
                                }",

                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 7.dp
                            ),

                            style =
                                Typography.labelSmall.copy(

                                    fontSize = 11.sp,

                                    fontWeight =
                                        FontWeight.Bold,

                                    color = successGreenText
                                ),

                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}