package com.palzzak.blur.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by yooas on 2018-01-07.
 */

@Entity(tableName = "Messages")
class Message(
        voiceId: Long,
        fileName: String,
        fileUrl: String,
        regDate: String,
        registId: Long,
        roomId: Long) {

    @PrimaryKey
    @ColumnInfo(name = "voiceId")
    var mVoiceId: Long = voiceId

    @ColumnInfo(name = "filename")
    var mFileName: String = fileName

    @ColumnInfo(name = "fileurl")
    var mFileUrl: String = fileUrl

    @ColumnInfo(name = "regdate")
    var mRegDate: String = regDate

    @ColumnInfo(name = "registId")
    var mRegistId: Long = registId

    @ColumnInfo(name = "roomId")
    var mRoomId: Long = roomId

}