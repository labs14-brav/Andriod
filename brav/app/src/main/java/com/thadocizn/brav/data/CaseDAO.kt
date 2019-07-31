package com.thadocizn.brav.data

import androidx.room.*
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.User

/**
 * Created by charles on 30,July,2019
 */
@Dao
interface CaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCase(case: Case)

    @Update
    fun updateCase(case: Case)

    @Delete
    fun deleteCase(case: Case)

}