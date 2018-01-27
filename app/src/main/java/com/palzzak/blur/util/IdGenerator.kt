package com.palzzak.blur.util

import com.mifmif.common.regex.Generex

/**
 * Created by yooas on 2018-01-11.
 */
class IdGenerator {
    companion object {
        private val REGEX = "([a-z][0-9]){5}"
        private val generex = Generex(REGEX)

        fun createRandomId(): String {
            val id = generex.random()
            AppLogger.d("Generated ID : $id")
            return id
        }
    }
}