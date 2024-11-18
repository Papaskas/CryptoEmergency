package com.cryptoemergency.cryptoemergency.api.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cryptoemergency.cryptoemergency.repository.database.SocialNetworksDao
import com.cryptoemergency.cryptoemergency.repository.database.SocialNetworksEntity

@Database(
    entities = [
        SocialNetworksEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun socialNetworkDao(): SocialNetworksDao
}
