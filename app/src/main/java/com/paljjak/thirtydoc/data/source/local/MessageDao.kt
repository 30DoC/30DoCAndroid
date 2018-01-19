package com.paljjak.thirtydoc.data.source.local

import android.arch.persistence.room.*
import com.paljjak.thirtydoc.data.Message

/**
 * Created by yooas on 2018-01-19.
 */
@Dao
interface MessageDao {

    @Query("SELECT * FROM Messages")
    fun getMessages(): List<Message>

    @Query("SELECT * FROM Messages WHERE entryid = :id")
    fun getMessageById(id: String): Message

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Update
    fun updateMessage(message: Message): Int

    @Query("DELETE FROM Messages WHERE entryid = :id")
    fun deleteMessageById(id: String): Int

    @Query("DELETE FROM Messages")
    fun deleteMessages()
}