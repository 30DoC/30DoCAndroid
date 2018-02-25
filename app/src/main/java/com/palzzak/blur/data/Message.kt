package com.palzzak.blur.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by yooas on 2018-01-07.
 */

@Entity(tableName = "Messages")
data class Message(
        @PrimaryKey
        @ColumnInfo(name = "voiceId")
        val voiceId: Long,

        @ColumnInfo(name = "filename")
        val filename: String,

        @ColumnInfo(name = "fileurl")
        val fileurl: String,

        @ColumnInfo(name = "regdate")
        val regdate: String,

        @ColumnInfo(name = "registId")
        val registId: Long,

        @ColumnInfo(name = "roomId")
        val roomId: Long) {
}