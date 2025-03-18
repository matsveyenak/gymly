package com.example.gymly.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymly.data.dao.ClientDao
import com.example.gymly.data.entity.Client

@Database(entities = [Client::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao

    companion object {
        fun getDatabaseBuilder(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "gymly.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}