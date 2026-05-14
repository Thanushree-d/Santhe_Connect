package com.example.santheconnect.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.santheconnect.ai.GeminiService
import com.example.santheconnect.ui.theme.*
import kotlinx.coroutines.launch

data class ChatMessage(val text: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AITipsScreen(navController: NavController) {
    var userInput by remember { mutableStateOf("") }
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BackgroundCream,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = PrimaryOrange, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Assistant Tips", style = Typography.titleMedium)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {
                item {
                    ChatBubble(
                        ChatMessage(
                            "Namaskara! 🙏 I'm your Karnataka Market guide. Ask me about the best time to visit Anekal Santhe or what's special in Mandya today!",
                            false
                        )
                    )
                }

                items(chatMessages) { message ->
                    ChatBubble(message)
                }

                if (isLoading) {
                    item {
                        Box(modifier = Modifier.padding(8.dp)) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = PrimaryOrange, strokeWidth = 2.dp)
                        }
                    }
                }
            }

            // Input Area
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 16.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .navigationBarsPadding()
                        .imePadding()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                        placeholder = { Text("Type your question...", color = TextSecondary) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(28.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = BackgroundCream,
                            unfocusedContainerColor = BackgroundCream,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        maxLines = 3
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    FloatingActionButton(
                        onClick = {
                            if (userInput.isNotBlank() && !isLoading) {
                                val message = userInput
                                chatMessages.add(ChatMessage(message, true))
                                userInput = ""
                                isLoading = true
                                scope.launch {
                                    val response = GeminiService.getRecommendation(message)
                                    chatMessages.add(ChatMessage(response, false))
                                    isLoading = false
                                }
                            }
                        },
                        containerColor = PrimaryOrange,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.size(48.dp),
                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val bgColor = if (message.isUser) PrimaryOrange else Color.White
    val textColor = if (message.isUser) Color.White else TextPrimary
    val shape = if (message.isUser) {
        RoundedCornerShape(20.dp, 20.dp, 4.dp, 20.dp)
    } else {
        RoundedCornerShape(20.dp, 20.dp, 20.dp, 4.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Surface(
            color = bgColor,
            shape = shape,
            shadowElevation = if (message.isUser) 4.dp else 2.dp,
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                color = textColor,
                style = Typography.bodyMedium.copy(lineHeight = 22.sp)
            )
        }
    }
}
