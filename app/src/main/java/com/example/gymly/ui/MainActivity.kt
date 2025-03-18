package com.example.gymly.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.gymly.ui.screen.ClientScreen
import com.example.gymly.ui.theme.GymlyTheme
import com.example.gymly.ui.viewmodel.ClientViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymlyTheme {
                Surface {
                    val clientViewModel: ClientViewModel = koinViewModel()
                    ClientScreen(viewModel = clientViewModel)
                }
            }
        }
    }
}
