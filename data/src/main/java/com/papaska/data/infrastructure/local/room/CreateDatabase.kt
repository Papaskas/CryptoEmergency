package com.papaska.data.infrastructure.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Функция создания базы данных
 * */
fun<T : RoomDatabase> createDatabase(
    context: Context,
    database: Class<T>,
    name: String,
) = Room.databaseBuilder(
    context = context,
    klass = database,
    name = name,
).build()
