package com.example.gymly.data.repository

import com.example.gymly.data.dao.ClientDao
import com.example.gymly.data.entity.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) {

    // Fetch all clients as a Flow (Live updates)
    fun getAllClients(): Flow<List<Client>> = clientDao.getAllClients()

    // Fetch client by ID
    suspend fun getClientById(id: Int): Client? = clientDao.getClientById(id)

    // Insert a new client
    suspend fun insertClient(client: Client) {
        clientDao.insertClient(client)
    }

    // Update an existing client
    suspend fun updateClient(client: Client) {
        clientDao.updateClient(client)
    }

    // Delete a client
    suspend fun deleteClient(client: Client) {
        clientDao.deleteClient(client)
    }
}