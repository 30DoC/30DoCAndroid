package com.palzzak.blur.util

/**
 * Created by stevehan on 2018. 2. 7..
 */

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palzzak.blur.model.User

const val USER_PREFERENCES = "playerPreferences"
const val PREFERENCE_FIRST_QUESTION = "$USER_PREFERENCES.firstQuestion"
const val PREFERENCE_SECOND_QUESTION = "$USER_PREFERENCES.secondQuestion"
const val PREFERENCE_THIRD_QUESTION = "$USER_PREFERENCES.thirdQuestion"
const val PREFERENCE_FOURTH_QUESTION = "$USER_PREFERENCES.fourthQuestion"
const val PREFERENCE_FIFTH_QUESTION = "$USER_PREFERENCES.fifthQuestion"

@SuppressLint("CommitPrefEdits")
private fun Context.editUser() = getUserPreferences().edit()

private fun Context.getUserPreferences() =
        getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)


fun Context.rewrite() {
    editUser().clear().commit()
}

fun Context.isWritten(): Boolean = with(getUserPreferences()) {
    contains(PREFERENCE_FIRST_QUESTION)
            && contains(PREFERENCE_SECOND_QUESTION)
            && contains(PREFERENCE_THIRD_QUESTION)
            && contains(PREFERENCE_FOURTH_QUESTION)
            && contains(PREFERENCE_FIFTH_QUESTION)
}

fun Context.getUser() = with(getUserPreferences()) {
    User(getString(PREFERENCE_FIRST_QUESTION, null),
            getString(PREFERENCE_SECOND_QUESTION, null),
            getString(PREFERENCE_THIRD_QUESTION, null),
            getString(PREFERENCE_FOURTH_QUESTION, null),
            getString(PREFERENCE_FIFTH_QUESTION, null))
}

fun Context.saveUser(user: User) {
    with(user) {
        if (valid())
            editUser()
                    .putString(PREFERENCE_FIRST_QUESTION, firstQuestion)
                    .putString(PREFERENCE_SECOND_QUESTION, secondQuestion)
                    .putString(PREFERENCE_THIRD_QUESTION, thirdQuestion)
                    .putString(PREFERENCE_FOURTH_QUESTION, fourthQuestion)
                    .putString(PREFERENCE_FIFTH_QUESTION, fifthQuestion)
                    .apply()
    }
}


fun Context.inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean): View =
        LayoutInflater.from(this).inflate(resource, root, attachToRoot)