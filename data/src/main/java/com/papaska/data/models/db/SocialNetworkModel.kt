package com.papaska.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.papaska.core.entity.db.SocialNetworkName

@Entity(
    tableName = "social_networks",
    indices = [
        Index(value = ["socialNetworkName"], unique = true)
    ]
)
data class SocialNetworkModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val socialNetworkName: SocialNetworkName,
)

@Entity(tableName = "social_network_item")
data class SocialNetworkItemModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val socialNetworkName: SocialNetworkName,
    val url: String,
    val description: String,
)
