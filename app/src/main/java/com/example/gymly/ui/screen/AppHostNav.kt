package com.example.gymly.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymly.ui.navigation.BottomNavigationBar
import com.example.gymly.ui.navigation.Navigation
import com.example.gymly.ui.navigation.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppHostNav() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenRoute.Home.route
    val currentScreen = ScreenRoute.entries.find { it.route == currentRoute } ?: ScreenRoute.Home

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = currentScreen.title))
                },
                navigationIcon = {
                    if (currentScreen != ScreenRoute.Home && currentScreen != ScreenRoute.Clients && currentScreen != ScreenRoute.Library) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController = navController, innerPadding = innerPadding)
    }
}