package com.thadocizn.brav.data

import androidx.room.*
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User

/**
 * Created by charles on 30,July,2019
 */
@Dao
interface MediatorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediator(mediator: Mediator)

    @Update
    fun updateMediator(mediator: Mediator)

    @Delete
    fun deleteMediator(mediator: Mediator)

}