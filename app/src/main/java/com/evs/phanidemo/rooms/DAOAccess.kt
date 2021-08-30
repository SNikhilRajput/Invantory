package com.example.room.mvvm.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.evs.phanidemo.model.InvantoryModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(invantory: InvantoryModel)

    @Query("SELECT sku FROM inventory")
    fun getSku() : LiveData<List<String>>

    @Query("SELECT manufacture FROM inventory")
    fun getManufacture() : LiveData<List<String>>

    @Query("SELECT * FROM inventory")
    fun getInvatory() : LiveData<List<InvantoryModel>>


    @Update
    fun updateItem(invantory: InvantoryModel) : Int

    @Query("SELECT * FROM inventory WHERE id= :Ids")
    fun getInventory(Ids: Int) : LiveData<InvantoryModel>


}
