package com.palzzak.blur.ui.quiz

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Quiz

/**
 * Created by yooas on 2018-02-04.
 */
class QuizAdapter: RecyclerView.Adapter<QuizAdapter.ViewHolder>(){
    lateinit var mQuizzes: List<Quiz>
    private var mCurrentPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mQuizzes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = mQuizzes[position]
        holder.setQuiz(quiz, position + 1)
    }

    fun getCurrentPosition() = mCurrentPosition

    fun setNextPosition() {
        if (mCurrentPosition < mQuizzes.size) mCurrentPosition++
    }

    fun setPrevPosition() {
        if (mCurrentPosition > 0) mCurrentPosition--
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val mQuizNumberView = view.findViewById<TextView>(R.id.id_quiz_number_text)

        private val mQuizDescView = view.findViewById<TextView>(R.id.id_quiz_desc_text)
        fun setQuiz(quiz: Quiz, position: Int) {
            mQuizNumberView.text = "Q$position."
            mQuizDescView.text = quiz.question
        }

    }
}