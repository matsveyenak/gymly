import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymly.R
import com.example.gymly.data.entity.Client
import com.example.gymly.ui.viewmodel.ClientViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    onSave: () -> Unit,
    onBack: () -> Unit,
    viewModel: ClientViewModel = koinViewModel()
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
            verticalArrangement = Arrangement.Top
        ) {
            var fullName by remember { mutableStateOf(TextFieldValue("")) }
            var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
            var instagramHandle by remember { mutableStateOf(TextFieldValue("")) }
            var telegramHandle by remember { mutableStateOf(TextFieldValue("")) }
            var whatsAppNumber by remember { mutableStateOf(TextFieldValue("")) }

            ClientTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full Name",
                icon = Icons.Filled.Person
            )
            Spacer(modifier = Modifier.height(8.dp))

            ClientTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number",
                icon = Icons.Filled.Phone
            )
            Spacer(modifier = Modifier.height(8.dp))

            SocialMediaTextFields(
                instagramHandle = instagramHandle,
                onInstagramChange = { instagramHandle = it },
                telegramHandle = telegramHandle,
                onTelegramChange = { telegramHandle = it },
                whatsAppNumber = whatsAppNumber,
                onWhatsAppChange = { whatsAppNumber = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val client = Client(
                    fullName = fullName.text,
                    phoneNumber = phoneNumber.text,
                    instagramNickname = instagramHandle.text,
                    telegramNickname = telegramHandle.text,
                    whatsappNickname = whatsAppNumber.text
                )
                viewModel.addClient(client)
                onSave()
            }) {
                Text(text = "Save Client")
            }
        }
    }
}

@Composable
fun ClientTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = label, Modifier.size(24.dp)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SocialMediaTextFields(
    instagramHandle: TextFieldValue,
    onInstagramChange: (TextFieldValue) -> Unit,
    telegramHandle: TextFieldValue,
    onTelegramChange: (TextFieldValue) -> Unit,
    whatsAppNumber: TextFieldValue,
    onWhatsAppChange: (TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Social Media",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ClientTextField(
            value = instagramHandle,
            onValueChange = onInstagramChange,
            label = "Instagram username",
            icon = ImageVector.vectorResource(id = R.drawable.ic_instagram)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ClientTextField(
            value = telegramHandle,
            onValueChange = onTelegramChange,
            label = "Telegram username",
            icon = ImageVector.vectorResource(id = R.drawable.ic_telegram)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ClientTextField(
            value = whatsAppNumber,
            onValueChange = onWhatsAppChange,
            label = "WhatsApp number",
            icon = ImageVector.vectorResource(id = R.drawable.ic_whatsapp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewClientScreenPreview() {
    AddClientScreen({}, {})
}