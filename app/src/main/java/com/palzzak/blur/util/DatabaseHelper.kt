package com.palzzak.blur.util

import android.content.Context
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by stevehan on 2018. 2. 7..
 */
class DatabaseHelper private constructor(
        context: Context
) : SQLiteOpenHelper(context.applicationContext, "blur.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val resources: Resources = context.resources


}

inline fun SQLiteDatabase.transact(transaction: SQLiteDatabase.() -> Unit) {
    try {
        beginTransaction()
        transaction()
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}