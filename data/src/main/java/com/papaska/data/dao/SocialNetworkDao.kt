package com.papaska.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.data.models.db.SocialNetworkModel

@Dao
interface SocialNetworkDao {

    @Query("SELECT * FROM social_networks")
    suspend fun getAll(): List<SocialNetworkModel>?

    @Query("SELECT * FROM social_networks WHERE socialNetworkName = :name")
    suspend fun getAllBySocialNetworkName(name: SocialNetworkName): List<SocialNetworkModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(socialNetwork: SocialNetworkModel)

    @Update
    suspend fun update(socialNetwork: SocialNetworkModel)

    @Query("DELETE FROM social_networks WHERE id = :id")
    suspend fun delete(id: Int)

//    @Query("SELECT * FROM social_networks")
//    suspend fun getAll(): List<SocialNetworkModel>
//
//    @Query("SELECT * FROM social_networks WHERE socialNetworkName = :socialNetworkName")
//    suspend fun getAllByName(socialNetworkName: SocialNetworkName): SocialNetworkModel
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSocialNetwork(socialNetwork: SocialNetworkModel): Long
//
//    @Query("SELECT * FROM social_network_item WHERE socialNetworkName = :socialNetworkName")
//    suspend fun getSocialNetworkItems(socialNetworkName: SocialNetworkName): List<SocialNetworkItemModel>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertItem(socialNetworkItem: SocialNetworkItemModel): Long
//
//    @Query("SELECT * FROM social_networks networks INNER JOIN social_network_item sni ON networks.socialNetworkName = sni.socialNetworkName")
//    suspend fun getAllWithItems(): List<SocialNetworksModel>
//
//    @Query("DELETE FROM social_network_item WHERE id = :id")
//    suspend fun deleteItem(id: Int)
}