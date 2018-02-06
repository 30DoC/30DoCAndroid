package com.palzzak.blur.ui.question

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Question
import com.palzzak.blur.ui.quiz.QuizActivity
import dagger.android.support.DaggerAppCompatActivity



/**
 * Created by stevehan on 2018. 2. 1..
 */
class QuestionActivity: DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener{

    fun fromHtml(source: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {

            Html.fromHtml(source)
        } else Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        var mBox = findViewById<TextView>(R.id.question_description)
        var first = R.string.question_description1
        var second = R.string.question_description2

        var text = "<bold>{$first}</bold>\n{$second}"
        mBox.setText(fromHtml(text));


    }

    override fun onClick(p0: View) {
        when (p0.id){
            R.id.id_start_button -> {
                val intent = Intent(this@QuestionActivity, QuizActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun printEditText(num: Int) {

    }

    override fun setQuestions(questions: ArrayList<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
