package com.palzzak.blur.network.response

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by stevehan on 2018. 2. 6..
 */
data class Question(
        val question: String,
        val answer: Boolean,
        val questionId: Int,
        val regDate: Date,
        val updateDate: Date) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readSerializable() as Date,
            source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(question)
        writeInt((if (answer) 1 else 0))
        writeInt(questionId)
        writeSerializable(regDate)
        writeSerializable(updateDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Question> = object : Parcelable.Creator<Question> {
            override fun createFromParcel(source: Parcel): Question = Question(source)
            override fun newArray(size: Int): Array<Question?> = arrayOfNulls(size)
        }
    }
}