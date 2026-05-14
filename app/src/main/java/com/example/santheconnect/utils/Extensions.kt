package com.example.santheconnect.utils

fun String.capitalizeFirst(): String {
    return this.replaceFirstChar { it.uppercase() }
}