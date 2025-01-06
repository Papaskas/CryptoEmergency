package com.papaska.data.dataSources.db.socialNetwork

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.data.models.db.SocialNetworkModel
import com.papaska.data.models.db.SocialNetworkItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SocialNetworkDao {
    @Query("SELECT * FROM social_networks")
    fun getAll(): Flow<List<SocialNetworkModel>>

    @Query("SELECT * FROM social_networks where name = :socialNetworkName")
    fun getAllBySocialName(socialNetworkName: SocialNetworkName): Flow<List<SocialNetworkModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(vararg socialNetworkItem: SocialNetworkItemModel): Boolean

    @Update(SocialNetworkItemModel::class)
    suspend fun updateItemById(id: Int, url: String, description: String): Boolean

    @Delete(SocialNetworkItemModel::class)
    suspend fun deleteItem(id: Int): Boolean
}