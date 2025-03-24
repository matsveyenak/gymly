package com.example.gymly.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.gymly.data.entity.Client
import com.example.gymly.ui.navigation.ScreenRoute
import com.example.gymly.ui.viewmodel.ClientViewModel


@Composable
fun ClientScreen(navController: NavHostController, viewModel: ClientViewModel) {
    val clients by viewModel.clients.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.AddNewClient.name) },
                icon = { Icon(Icons.Filled.Add, "Add Client") },
                text = { Text("Add Client") }
            )
        }
    ) { innerPadding ->
        if (clients.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No clients yet", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPadding.calculateBottomPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    ),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(clients) { client ->
                    ClientItem(client = client) {
                        // Navigate to client details
                    }
                }
            }
        }
    }
}

@Composable
fun ClientItem(client: Client, onClientClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable(onClick = onClientClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialsCircle(fullName = client.fullName) // Replace with actual profile picture
            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = client.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (!client.nextSession.isNullOrBlank()) {
                    Text(
                        text = "Next session: ${client.nextSession}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Go to ${client.fullName} profile"
            )
        }
    }
}

@Composable
fun InitialsCircle(fullName: String) {
    val initials = getInitials(fullName)
    Text(
        text = initials,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.White,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                androidx.compose.ui.graphics.Color("#007BFF".toColorInt()).copy(alpha = 0.8F)
            )
            .padding(16.dp)
    )
}

fun getInitials(name: String): String {
    if (name.isBlank()) return ""
    val parts = name.split(" ")
    return if (parts.size >= 2) {
        "${parts[0].first()}${parts[1].first()}"
    } else {
        parts[0].first().toString()
    }.uppercase()
}
