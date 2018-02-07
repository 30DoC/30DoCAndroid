package com.palzzak.blur.model

/**
 * Created by stevehan on 2018. 2. 7..
 */

import android.os.Parcel
import android.os.Parcelable

data class User(
        val firstQuestion: String?,
        val secondQuestion: String?,
        val thirdQuestion: String?,
        val fourthQuestion: String?,
        val fifthQuestion: String?
) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(firstQuestion)
            writeString(secondQuestion)
            writeString(thirdQuestion)
            writeString(fourthQuestion)
            writeString(fifthQuestion)
        }
    }

    fun valid() = !(
            firstQuestion.isNullOrEmpty()
            || secondQuestion.isNullOrEmpty()
            || thirdQuestion.isNullOrEmpty()
            || fourthQuestion.isNullOrEmpty()
            || fifthQuestion.isNullOrEmpty()
            )

    override fun describeContents() = 0

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {

            override fun createFromParcel(parcel: Parcel) = with(parcel) {
                User(firstQuestion = readString(),
                        secondQuestion = readString(),
                        thirdQuestion = readString(),
                        fourthQuestion = readString(),
                        fifthQuestion = readString())
            }

            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}