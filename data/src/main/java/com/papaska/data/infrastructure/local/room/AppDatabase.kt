package com.papaska.data.infrastructure.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.models.db.SocialNetworkModel
import com.papaska.data.models.db.SocialNetworkItemModel

@Database(entities = [
    SocialNetworkModel::class,
    SocialNetworkItemModel::class,
], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun socialNetworksDao(): SocialNetworkDao
}