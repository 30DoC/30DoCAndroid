package com.palzzak.blur.ui.question

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.palzzak.blur.R
import com.palzzak.blur.ui.quiz.QuizActivity
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by stevehan on 2018. 2. 1..
 */
class QuestionActivity: DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

    }

    override fun onClick(p0: View) {
        when (p0.id){
            R.id.id_start_button -> {
                val intent = Intent(this@QuestionActivity, QuizActivity::class.java)
                startActivity(intent)
            }

        }

    }
}
