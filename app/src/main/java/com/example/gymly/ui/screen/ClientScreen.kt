package com.example.gymly.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gymly.data.entity.Client
import com.example.gymly.ui.viewmodel.ClientViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ClientScreen(viewModel: ClientViewModel = koinViewModel()) {
    val clients by viewModel.clients.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(text = "Clients", style = MaterialTheme.typography.headlineMedium)

        clients.forEach { client ->
            ClientItem(client)
        }

        Spacer(modifier = Modifier.height(16.dp))

        AddClientForm { newClient ->
            viewModel.addClient(newClient)
        }
    }
}

@Composable
fun ClientItem(client: Client) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlaceholderAvatar(name = client.fullName)
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = client.fullName, style = MaterialTheme.typography.bodyLarge)
            client.phoneNumber?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}

@Composable
fun PlaceholderAvatar(name: String) {
    val firstLetter = name.firstOrNull()?.uppercase() ?: ""
    val backgroundColor = Color.LightGray

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = firstLetter,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AddClientForm(onAddClient: (Client) -> Unit) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var instagramNickname by remember { mutableStateOf(TextFieldValue("")) }
    var telegramNickname by remember { mutableStateOf(TextFieldValue("")) }
    var whatsappNickname by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabeledTextField(label = "Full Name", value = fullName, onValueChange = { fullName = it })
        LabeledTextField(
            label = "Phone Number",
            value = phoneNumber,
            onValueChange = { phoneNumber = it })
        LabeledTextField(
            label = "Instagram",
            value = instagramNickname,
            onValueChange = { instagramNickname = it })
        LabeledTextField(
            label = "Telegram",
            value = telegramNickname,
            onValueChange = { telegramNickname = it })
        LabeledTextField(
            label = "WhatsApp",
            value = whatsappNickname,
            onValueChange = { whatsappNickname = it })

        Button(
            onClick = {
                val client = Client(
                    fullName = fullName.text,
                    phoneNumber = phoneNumber.text,
                    instagramNickname = instagramNickname.text,
                    telegramNickname = telegramNickname.text,
                    whatsappNickname = whatsappNickname.text
                )
                onAddClient(client)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Client")
        }
    }
}

