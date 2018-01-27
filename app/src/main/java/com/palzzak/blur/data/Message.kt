package com.palzzak.blur.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by yooas on 2018-01-07.
 */

@Entity(tableName = "Messages")
class Message(id: String, type: Int, date: Long, message: String) {

    @PrimaryKey
    @ColumnInfo(name = "entryid")
    var mId: String = id

    @ColumnInfo(name = "type")
    var mType: Int = type

    @ColumnInfo(name = "date")
    var mDate: Long = date

    @ColumnInfo(name = "message")
    var mMessage: String = message

}