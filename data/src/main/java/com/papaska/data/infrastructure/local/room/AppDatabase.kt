package com.papaska.data.infrastructure.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papaska.domain.constants.StringsConstants.DATABASE_VERSION
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.models.db.SocialNetworkModel

@Database(
    entities = [
        SocialNetworkModel::class,
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun socialNetworksDao(): SocialNetworkDao
}