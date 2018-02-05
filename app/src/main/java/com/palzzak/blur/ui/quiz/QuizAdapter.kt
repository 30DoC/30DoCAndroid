package com.palzzak.blur.ui.quiz

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.palzzak.blur.R

/**
 * Created by yooas on 2018-01-21.
 */
class QuizAdapter: RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
    private val mQuizzes: ArrayList<String> = ArrayList()

    init {
        for (i in 1..10) {
            mQuizzes.add("Q$i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_quiz, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mQuizzes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setText(mQuizzes[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val mTextView = view.findViewById<TextView>(R.id.id_card_text)

        fun setText(s: String) {
            mTextView.text = s
        }

    }
}