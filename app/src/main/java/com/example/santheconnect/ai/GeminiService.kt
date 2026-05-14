package com.example.santheconnect.ai

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object GeminiService {

    // 🔑 Replace with your actual Gemini API Key from Google AI Studio
    private const val API_KEY = "YOUR_GEMINI_API_KEY"

    private val model = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = API_KEY
    )

    suspend fun getRecommendation(userQuery: String): String {
        // ✅ FIX: Added Demo Mode if API Key is not provided
        if (API_KEY == "YOUR_GEMINI_API_KEY") {
            delay(1000) // Simulate network delay
            return when {
                userQuery.contains("food", ignoreCase = true) -> 
                    "For traditional food, I recommend visiting Maddur Santhe on Mondays for their famous Vadas, or Davanagere on Wednesdays for Benne Dosa!"
                userQuery.contains("market", ignoreCase = true) || userQuery.contains("sante", ignoreCase = true) -> 
                    "Anekal Santhe (Monday) is one of the oldest and largest rural markets near Bangalore. You can find everything from fresh greens to local crafts."
                userQuery.contains("silk", ignoreCase = true) || userQuery.contains("textile", ignoreCase = true) -> 
                    "Ramanagara (Thursday) is the biggest silk cocoon market in Asia. For handlooms, Doddaballapur on Tuesdays is the best choice."
                else -> "Karnataka has many amazing Santhes! You can explore Channapatna for toys on Wednesdays or Mysuru for royal crafts on Thursdays."
            }
        }

        return try {
            val response = withContext(Dispatchers.IO) {
                model.generateContent(
                    content {
                        text("You are a local Karnataka travel assistant. Suggest best local Santhe (weekly markets) in Karnataka based on this user query: $userQuery. Be concise and helpful.")
                    }
                )
            }
            response.text ?: "I couldn't find a specific recommendation for that. Try asking about food, toys, or silk markets!"
        } catch (e: Exception) {
            "I'm having trouble reaching my AI brain right now. Try asking me about 'food' or 'silk' for local tips!"
        }
    }
}
