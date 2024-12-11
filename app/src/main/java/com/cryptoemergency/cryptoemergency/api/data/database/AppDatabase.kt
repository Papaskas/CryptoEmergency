package com.cryptoemergency.cryptoemergency.api.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cryptoemergency.cryptoemergency.api.domain.model.database.SocialNetworksDao
import com.cryptoemergency.cryptoemergency.api.domain.model.database.SocialNetworksEntity

@Database(
    entities = [
        SocialNetworksEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun socialNetworkDao(): SocialNetworksDao
}
