package com.example.room.mvvm.room

import android.content.Context
import androidx.room.*
import com.evs.phanidemo.model.InvantoryModel

@Database(entities = arrayOf(InvantoryModel::class), version = 1, exportSchema = false)
abstract class InvantoryDatabase : RoomDatabase() {

    abstract fun invantoryDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: InvantoryDatabase? = null

        fun getDataseClient(context: Context) : InvantoryDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, InvantoryDatabase::class.java, "InvantoryData")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}