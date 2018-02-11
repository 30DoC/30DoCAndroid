package com.palzzak.blur.ui.question

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Question
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

    var firstCheck: Boolean = false
    var secondCheck: Boolean = false
    var thirdCheck: Boolean = false
    var fourthCheck: Boolean = false
    var fifthCheck: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        var mBox = findViewById<TextView>(R.id.question_description)
        var first = getResources().getString(R.string.question_description1)
        var second = getResources().getString(R.string.question_description2)

        var text = "<strong>$first</strong>\n$second"
        mBox.setText(fromHtml(text));

        mQuestionPresenter.printQuestionDescription()
        back_button.setOnClickListener(this)
        first_o_button.setOnClickListener(this)
        first_x_button.setOnClickListener(this)
        second_o_button.setOnClickListener(this)
        second_x_button.setOnClickListener(this)
        third_o_button.setOnClickListener(this)
        third_x_button.setOnClickListener(this)
        fourth_o_button.setOnClickListener(this)
        fourth_x_button.setOnClickListener(this)
        fifth_o_button.setOnClickListener(this)
        fifth_x_button.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.getId()) {

            R.id.back_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
            }

            R.id.first_o_button -> {
                firstCheck = true
                if (firstCheck) {
                    first_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    first_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.first_x_button -> {
                firstCheck = false
                if (!firstCheck) {
                    first_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    first_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.second_o_button -> {
                secondCheck = true
                if (secondCheck) {
                    second_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    second_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.second_x_button -> {
                secondCheck = false
                if (!secondCheck) {
                    second_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    second_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.third_o_button -> {
                thirdCheck = true
                if (thirdCheck) {
                    third_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    third_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.third_x_button -> {
                thirdCheck = false
                if (!thirdCheck) {
                    third_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    third_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.fourth_o_button -> {
                fourthCheck = true
                if (fourthCheck) {
                    fourth_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    fourth_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.fourth_x_button -> {
                fourthCheck = false
                if (!fourthCheck) {
                    fourth_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    fourth_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.fifth_o_button -> {
                fifthCheck = true
                if (fifthCheck) {
                    fifth_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    fifth_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.fifth_x_button -> {
                fifthCheck = false
                if (!fifthCheck) {
                    fifth_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    fifth_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            else -> {
            }
        }
    }



    fun fromHtml(source: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {

            Html.fromHtml(source)
        } else Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    }



    override fun printEditText(num: Int) {

    }

    override fun setQuestions(questions: ArrayList<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private fun Button.setImageResource(o_gray_button: Int) {}
