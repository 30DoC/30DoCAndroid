package com.palzzak.blur.ui.question

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.palzzak.blur.R
import com.palzzak.blur.network.response.SimpleQuiz

/**
 * Created by yooas on 2018-02-18.
 */
class QuestionAdapter: RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    var mData: List<SimpleQuiz>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = mData?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(mData?.get(position))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mQuestionEdit = itemView.findViewById<EditText>(R.id.id_question_edit)
        val mTrueButton = itemView.findViewById<Button>(R.id.id_o_button)
        val mFalseButton = itemView.findViewById<Button>(R.id.id_x_button)

        fun setView(quiz: SimpleQuiz?) {
            mQuestionEdit.setText(quiz?.question)
        }
    }
}