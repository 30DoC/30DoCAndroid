package com.palzzak.blur.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.palzzak.blur.data.Message

/**
 * Created by yooas on 2018-01-19.
 */

@Database(entities = [ Message::class ], version = 1)
abstract class MessagesDatabase: RoomDatabase() {
    abstract fun messageDao(): MessageDao
}