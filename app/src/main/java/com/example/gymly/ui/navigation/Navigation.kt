package com.example.gymly.ui.navigation

import AddClientScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gymly.R
import com.example.gymly.ui.screen.ClientScreen
import com.example.gymly.ui.screen.ClientsScreen
import com.example.gymly.ui.screen.HomeScreen
import com.example.gymly.ui.screen.LibraryScreen
import com.example.gymly.ui.viewmodel.ClientViewModel
import org.koin.androidx.compose.koinViewModel

enum class ScreenRoute(val route: String, @StringRes val title: Int) {
    Home("Home", R.string.home_title),
    Clients("Clients", R.string.clients_title),
    Library("Library", R.string.library_title),
    AddNewClient("AddNewClient", R.string.add_new_client_title)
}
@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    val viewModel: ClientViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.name,
        modifier = Modifier.padding(innerPadding) // Apply padding to the NavHost
    ) {
        composable(ScreenRoute.Home.name) {
            HomeScreen()
        }
        composable(ScreenRoute.Clients.name) {
            ClientScreen(navController, viewModel)
        }
        composable(ScreenRoute.Library.name) {
            LibraryScreen()
        }
        composable(ScreenRoute.AddNewClient.name) {
            AddClientScreen(
                onSave = { navController.navigateUp() },
                onBack = { navController.navigateUp() } // Go back to the previous screen
            )
        }
    }
}