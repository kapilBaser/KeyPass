package com.yogeshpaliyal.keypass.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogeshpaliyal.keypass.data.AccountModel
import kotlinx.coroutines.flow.Flow


/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 30-01-2021 21:43
*/

@Dao
abstract class DbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateAccount(accountModel: AccountModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateAccount(accountModel: List<AccountModel>)


    @Query("SELECT * from account")
    abstract fun getAllAccounts() : Flow<List<AccountModel>>

    @Query("SELECT * from account where tags = :tag")
    abstract fun getAllAccounts(tag: String) : Flow<List<AccountModel>>

    @Query("SELECT * from account where id = :id")
    abstract fun getAccount(id: Long?) : AccountModel?

    @Query("SELECT DISTINCT tags from account")
    abstract fun getTags() : Flow<List<String>>

    @Query("DELETE from account where id = :id")
    abstract fun deleteAccount(id: Long?)


}