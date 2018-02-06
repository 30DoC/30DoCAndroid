package com.palzzak.blur.ui.quiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Quiz

/**
 * Created by jaeyoonyoo on 2018. 2. 6..
 */
class QuizFragment: Fragment() {
    private lateinit var mQuiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuiz = arguments.getParcelable("quiz")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.item_quiz, container, false)
        rootView.findViewById<TextView>(R.id.id_quiz_number_text).text = "Q${mQuiz.quizId}."
        rootView.findViewById<TextView>(R.id.id_quiz_question_text).text = mQuiz.question
        return rootView
    }

    companion object {
        fun create(quiz: Quiz): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putParcelable("quiz", quiz)
            fragment.arguments = args
            return fragment
        }

        fun createFragments(quizzes: List<Quiz>): List<QuizFragment> {
            val list = ArrayList<QuizFragment>()
            quizzes.map {
                list.add(create(it))
            }
            return list
        }
    }
}