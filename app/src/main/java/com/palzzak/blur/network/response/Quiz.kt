package com.palzzak.blur.network.response

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by yooas on 2018-02-04.
 */
data class Quiz(
        val answer: Boolean,
        val question: String,
        val quizId: Long,
        val regDate: Date,
        val updateDate: Date) : Parcelable {
    constructor(source: Parcel) : this(
            1 == source.readInt(),
            source.readString(),
            source.readLong(),
            source.readSerializable() as Date,
            source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (answer) 1 else 0))
        writeString(question)
        writeLong(quizId)
        writeSerializable(regDate)
        writeSerializable(updateDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Quiz> = object : Parcelable.Creator<Quiz> {
            override fun createFromParcel(source: Parcel): Quiz = Quiz(source)
            override fun newArray(size: Int): Array<Quiz?> = arrayOfNulls(size)
        }
    }
}