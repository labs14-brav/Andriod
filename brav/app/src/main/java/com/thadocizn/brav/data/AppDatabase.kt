package com.thadocizn.brav.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User

/**
 * Created by charles on 30,July,2019
 */
@Database(entities = [User::class, Mediator::class, Case::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userDAO():UserDAO
    abstract fun mediatorDAO():MediatorDAO
    abstract fun caseDAO():CaseDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}