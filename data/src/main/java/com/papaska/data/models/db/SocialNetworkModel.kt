package com.papaska.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.entity.socialNetwork.SocialNetworkType

@Entity(tableName = "social_networks")
data class SocialNetworkModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val socialNetworkName: SocialNetworkName,
    val url: String,
    val description: String?,
    val type: SocialNetworkType,
)
