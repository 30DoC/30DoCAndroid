package com.palzzak.blur.ui.question

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.SimpleQuiz
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*
import javax.inject.Inject


/**
 * Created by stevehan on 2018. 2. 1..
 */
class QuestionActivity: DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener{
    @Inject
    lateinit var mQuestionPresenter: QuestionPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    private val mAdapter = QuestionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        var mBox = findViewById<TextView>(R.id.question_description)
        var first = getResources().getString(R.string.question_description1)
        var second = getResources().getString(R.string.question_description2)

        var text = "<strong>$first</strong>\n$second"
        mBox.setText(fromHtml(text));

        val memberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mQuestionPresenter.loadInitialQuestionSet(memberId)

        back_button.setOnClickListener(this)
        id_question_recycler.layoutManager = LinearLayoutManager(this)
        id_question_recycler.adapter = mAdapter
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.back_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
            }
        }
    }



    fun fromHtml(source: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(source)
        } else Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    }


    override fun setQuestions(questions: List<SimpleQuiz>) {
        mAdapter.mData = questions
        mAdapter.notifyDataSetChanged()
    }
}
