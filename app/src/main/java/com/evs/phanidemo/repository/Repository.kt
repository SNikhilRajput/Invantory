package com.evs.phanidemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evs.phanidemo.model.InvantoryModel
import com.example.room.mvvm.room.InvantoryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository {

    companion object {

        var database: InvantoryDatabase? = null

        var skus: LiveData<List<String>>? = null
        var manufacturess: LiveData<List<String>>? = null
        var invantorya: LiveData<List<InvantoryModel>>?=null
        var inventoryModel: LiveData<InvantoryModel>?=null

        fun initializeDB(context: Context) : InvantoryDatabase {
            return InvantoryDatabase.getDataseClient(context)
        }

        fun insertData(context: Context,name:String,sku:String,qun:String,unit:String,manufactures:String) {

            database = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = InvantoryModel(name,sku,qun,unit,manufactures)
                database!!.invantoryDao().InsertData(loginDetails)
            }

        }

        fun updateData(context: Context,invantoryModel: InvantoryModel) {

            database = initializeDB(context)

            CoroutineScope(IO).launch {
                database!!.invantoryDao().updateItem(invantoryModel)
            }

        }

        fun getInventory(context: Context,id: Int):LiveData<InvantoryModel>? {

            database = initializeDB(context)
                inventoryModel=  database!!.invantoryDao().getInventory(id)
            return inventoryModel
        }

        fun getSku(context: Context) : LiveData<List<String>>? {

            database = initializeDB(context)

            skus = database!!.invantoryDao().getSku()

            return skus
        }

        fun getManufatures(context: Context) : LiveData<List<String>>? {

            database = initializeDB(context)

            manufacturess = database!!.invantoryDao().getManufacture()

            return manufacturess
        }

        fun getInvantory(context: Context) : LiveData<List<InvantoryModel>>? {

            database = initializeDB(context)

            invantorya = database!!.invantoryDao().getInvatory()

            return invantorya
        }

    }
}