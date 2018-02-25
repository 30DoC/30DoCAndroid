package com.palzzak.blur.ui.register

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.palzzak.blur.R
import com.palzzak.blur.network.data.SimpleQuiz

/**
 * Created by yooas on 2018-02-18.
 */
class RegisterAdapter : RecyclerView.Adapter<RegisterAdapter.ViewHolder>(), View.OnClickListener{
    var mData: List<SimpleQuiz> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent,false)
        itemView.findViewById<Button>(R.id.id_o_button).setOnClickListener(this)
        itemView.findViewById<Button>(R.id.id_x_button).setOnClickListener(this)
        itemView.findViewById<EditText>(R.id.id_question_edit).apply {
            addTextChangedListener(MultipleEditTextWatcher(this, mData))
        }
        return ViewHolder(itemView)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = mData[position]
        holder.itemView.tag = position
        if (quiz.question == null) {
            holder.setHint(holder.itemView.resources.getStringArray(R.array.questions_hint)[position])
        } else {
            holder.setPreference(quiz)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mQuestionEdit = itemView.findViewById<EditText>(R.id.id_question_edit)
        private val mAnswerRadioGroup = itemView.findViewById<RadioGroup>(R.id.id_answer_radio_group)

        fun setPreference(quiz: SimpleQuiz) {
            mQuestionEdit.setText(quiz.question)
            mAnswerRadioGroup.check(if (quiz.answer == true) R.id.id_o_button else R.id.id_x_button)
        }
        fun setHint(hint: String) {
            mQuestionEdit.hint = hint
        }


    }

    override fun onClick(v: View) {
        val item = v.parent.parent as View
        mData[item.tag as Int].answer = v.id == R.id.id_o_button
    }

    class MultipleEditTextWatcher(val editText: EditText, val data: List<SimpleQuiz>): TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val item = editText.parent as View
            data[item.tag as Int].question = s.toString()
        }

    }
}