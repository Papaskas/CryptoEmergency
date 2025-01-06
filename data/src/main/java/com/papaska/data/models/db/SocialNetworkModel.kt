package com.papaska.data.models.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.ColumnInfo
import androidx.room.Index
import androidx.room.PrimaryKey
import com.papaska.core.entity.db.SocialNetworkName

@Entity(
    tableName = "social_networks",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class SocialNetworkModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: SocialNetworkName,
)

@Entity(tableName = "social_network_item")
data class SocialNetworkItemModel(
    @PrimaryKey(autoGenerate = true) val itemId: Int,
    @ColumnInfo(index = true) val name: SocialNetworkName,
    val url: String,
    @Ignore val description: String,
)
