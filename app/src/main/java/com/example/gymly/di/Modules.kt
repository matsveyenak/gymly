package com.example.gymly.di

import com.example.gymly.data.db.AppDatabase
import com.example.gymly.data.db.AppDatabase.Companion.getDatabaseBuilder
import com.example.gymly.data.repository.ClientRepository
import com.example.gymly.ui.viewmodel.ClientViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { getDatabaseBuilder(get()) }

    // DAOs
    single { get<AppDatabase>().clientDao() }

    // Repositories
    single { ClientRepository(get()) }
}

val viewModelModule = module {
    viewModel { ClientViewModel(get()) }
}