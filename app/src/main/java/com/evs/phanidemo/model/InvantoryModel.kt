package com.evs.phanidemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "inventory")
data class InvantoryModel(

    @ColumnInfo(name = "name")
    var name:String?=null,

    @ColumnInfo(name = "sku")
    var sku:String?=null,

    @ColumnInfo(name = "qun")
    var qun:String?=null,

    @ColumnInfo(name = "unit")
    var unit:String?=null,



    @ColumnInfo(name = "manufacture")
    var manufacture:String?=null
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}