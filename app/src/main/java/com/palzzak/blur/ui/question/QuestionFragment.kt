package com.palzzak.blur.ui.question

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.palzzak.blur.R

/**
 * Created by stevehan on 2018. 2. 7..
 */
class QuestionFragment : Fragment() {
    private var firstQuestion: EditText? = null
    private var secondQuestion: EditText? = null
    private var thirdQuestion: EditText? = null
    private var fourthQuestion: EditText? = null
    private var fifthQuestion: EditText? = null

    private var edit: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(R.layout.fragment_question, container, false)

        return contentView
    }


}