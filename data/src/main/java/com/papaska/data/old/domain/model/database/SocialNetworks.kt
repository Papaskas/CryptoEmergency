package com.papaska.data.old.domain.model.database

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks.NetworkName

@Entity(
    tableName = "socialNetworks",
    indices = [
        Index(value = ["networkName"]),
        Index(value = ["url"], unique = true),
    ],
)
data class SocialNetworksEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val networkName: NetworkName,
    val url: String,
    val description: String,
)

@Dao
interface SocialNetworksDao {
    @Query("SELECT * FROM socialNetworks")
    suspend fun getAll(): List<SocialNetworksEntity>?

    @Throws(SQLiteConstraintException::class)
    @Query("SELECT * FROM socialNetworks WHERE networkName IN (:networkName)")
    suspend fun getNetworksByName(networkName: NetworkName): List<SocialNetworksEntity>?

    @Throws(SQLiteConstraintException::class)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertNetwork(networkData: SocialNetworksEntity)

    @Throws(SQLiteConstraintException::class)
    @Query("UPDATE socialNetworks SET url = :url, description = :description WHERE uid = :uid")
    suspend fun updateNetworkByUid(uid: Int, url: String, description: String)

    @Throws(SQLiteConstraintException::class)
    @Query("DELETE FROM socialNetworks WHERE uid = :uid")
    suspend fun deleteNetworkByUid(uid: Int)
}


