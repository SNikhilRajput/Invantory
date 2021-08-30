package com.evs.phanidemo.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evs.phanidemo.model.InvantoryModel
import com.evs.phanidemo.repository.Repository

class viewModel:ViewModel() {

    var skus: LiveData<List<String>>? = null
    var manufacturess: LiveData<List<String>>? = null
    var invantoryas:LiveData<List<InvantoryModel>>?=null
    var invantorya:LiveData<InvantoryModel>?=null

    fun insertData(context: Context, name:String,sku:String,qun:String,unit:String,manufactures:String) {
        Repository.insertData(context, name, sku,qun,unit,manufactures)
    }

    fun updateData(context: Context, invantoryModel: InvantoryModel) {
        Repository.updateData(context, invantoryModel)
    }

    fun getInventory(context: Context, id: Int):LiveData<InvantoryModel> {
        invantorya=Repository.getInventory(context, id)
        return invantorya!!
    }


    fun getSkus(context: Context) : LiveData<List<String>>? {
        skus = Repository.getSku(context)
        return skus
    }

    fun getManufacture(context: Context) : LiveData<List<String>>? {
        manufacturess = Repository.getManufatures(context)
        return manufacturess
    }

    fun getInvantory(context: Context) : LiveData<List<InvantoryModel>>? {
        invantoryas = Repository.getInvantory(context)
        return invantoryas
    }

}



