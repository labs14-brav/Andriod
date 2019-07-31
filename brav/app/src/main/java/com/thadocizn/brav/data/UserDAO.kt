package com.thadocizn.brav.data

import androidx.room.*
import com.thadocizn.brav.models.User

/**
 * Created by charles on 30,July,2019
 */
@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}