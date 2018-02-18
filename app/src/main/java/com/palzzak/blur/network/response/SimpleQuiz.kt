package com.palzzak.blur.network.response

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by jaeyoonyoo on 2018. 2. 13..
 */
data class SimpleQuiz(
        var answer: Boolean,
        var question: String?) : Parcelable {
    constructor(source: Parcel) : this(
            1 == source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (answer) 1 else 0))
        writeString(question)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SimpleQuiz> = object : Parcelable.Creator<SimpleQuiz> {
            override fun createFromParcel(source: Parcel): SimpleQuiz = SimpleQuiz(source)
            override fun newArray(size: Int): Array<SimpleQuiz?> = arrayOfNulls(size)
        }
    }
}