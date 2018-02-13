package com.palzzak.blur.ui.question

import android.widget.Button
import android.widget.EditText

/**
 * Created by stevehan on 2018. 2. 12..
 */
class ListViewItem {

    var question_text: EditText? = null
    var o_button: Button? = null
    var x_button: Button? = null

    constructor(question_text: EditText, o_button: Button, x_button: Button) {
        this.question_text = question_text
        this.o_button = o_button
        this.x_button = x_button
    }
}