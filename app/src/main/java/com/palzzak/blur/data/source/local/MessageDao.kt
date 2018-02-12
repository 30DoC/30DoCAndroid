package com.palzzak.blur.data.source.local

import android.arch.persistence.room.*
import com.palzzak.blur.data.Message

/**
 * Created by yooas on 2018-01-19.
 */
@Dao
interface MessageDao {

    @Query("SELECT * FROM Messages")
    fun getMessages(): List<Message>

    @Query("SELECT * FROM Messages WHERE voiceId = :id")
    fun getMessageById(id: Long): Message?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Update
    fun updateMessage(message: Message): Int

    @Query("DELETE FROM Messages WHERE voiceId = :id")
    fun deleteMessageById(id: Long): Int

    @Query("DELETE FROM Messages")
    fun deleteMessages()
}