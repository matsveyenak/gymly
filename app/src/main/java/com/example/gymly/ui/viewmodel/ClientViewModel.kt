package com.example.gymly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymly.data.entity.Client
import com.example.gymly.data.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClientViewModel(private val clientRepository: ClientRepository) : ViewModel() {

    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients = _clients.asStateFlow()

    init {
        viewModelScope.launch {
            clientRepository.getAllClients().collect { _clients.value = it }
        }
    }

    fun addClient(client: Client) {
        viewModelScope.launch {
            clientRepository.insertClient(client)
        }
    }
}