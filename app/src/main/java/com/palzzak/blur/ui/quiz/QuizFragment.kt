package com.palzzak.blur.ui.quiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.data.SimpleQuiz

/**
 * Created by jaeyoonyoo on 2018. 2. 6..
 */
class QuizFragment: Fragment() {
    private lateinit var mQuiz: SimpleQuiz
    private var mIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuiz = arguments.getParcelable("quiz")
        mIndex = arguments.getInt("index")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.item_quiz, container, false)
        rootView.findViewById<TextView>(R.id.id_quiz_question_text).text = mQuiz.question
        return rootView
    }

    companion object {
        fun create(quiz: SimpleQuiz, index: Int) = QuizFragment().apply {
            arguments = Bundle().apply {
                putParcelable("quiz", quiz)
                putInt("index", index)
            }
        }

        fun createFragments(quizzes: List<SimpleQuiz>) = ArrayList<QuizFragment>().apply {
            quizzes.map {
                add(create(it, quizzes.indexOf(it) + 1))
            }
        }
    }
}