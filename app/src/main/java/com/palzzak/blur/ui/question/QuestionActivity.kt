package com.palzzak.blur.ui.question

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
class QuestionActivity : DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener {
    @Inject
    lateinit var mQuestionPresenter: QuestionPresenter


    private val mAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
        var mFragments: List<Fragment> = arrayListOf()
        override fun getItem(position: Int): Fragment = mFragments[position]
        override fun getCount(): Int = mFragments.size
    }


    var isBlank1: Boolean = true
    var isBlank2: Boolean = true
    var isBlank3: Boolean = true
    var isBlank4: Boolean = true
    var isBlank5: Boolean = true


    var firstCheck: Boolean = false
    var secondCheck: Boolean = false
    var thirdCheck: Boolean = false
    var fourthCheck: Boolean = false
    var fifthCheck: Boolean = false
    var finalCheck: Boolean = false


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
        register_question_button.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        var getEditText1 = first_question.getText().toString();
        var getEditText2 = second_question.getText().toString();
        var getEditText3 = third_question.getText().toString();
        var getEditText4 = fourth_question.getText().toString();
        var getEditText5 = fifth_question.getText().toString();

        when (v.getId()) {

            R.id.back_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
            }

            R.id.first_o_button -> {
                isBlank1 = false
                firstCheck = true
                if (firstCheck) {
                    first_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    first_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.first_x_button -> {
                isBlank1 = false
                firstCheck = false
                if (!firstCheck) {
                    first_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    first_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.second_o_button -> {
                isBlank2 = false
                secondCheck = true
                if (secondCheck) {
                    second_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    second_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.second_x_button -> {
                isBlank2 = false
                secondCheck = false
                if (!secondCheck) {
                    second_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    second_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.third_o_button -> {
                isBlank3 = false
                thirdCheck = true
                if (thirdCheck) {
                    third_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    third_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.third_x_button -> {
                isBlank3 = false
                thirdCheck = false
                if (!thirdCheck) {
                    third_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    third_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.fourth_o_button -> {
                isBlank4 = false
                fourthCheck = true
                if (fourthCheck) {
                    fourth_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    fourth_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.fourth_x_button -> {
                isBlank4 = false
                fourthCheck = false
                if (!fourthCheck) {
                    fourth_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    fourth_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.fifth_o_button -> {
                isBlank5 = false
                fifthCheck = true
                if (fifthCheck) {
                    fifth_o_button.setBackground(getDrawable(R.drawable.o_purple_button))
                    fifth_x_button.setBackground(getDrawable(R.drawable.x_gray_button))
                }
            }

            R.id.fifth_x_button -> {
                isBlank5 = false
                fifthCheck = false
                if (!fifthCheck) {
                    fifth_o_button.setBackground(getDrawable(R.drawable.o_gray_button))
                    fifth_x_button.setBackground(getDrawable(R.drawable.x_purple_button))
                }
            }

            R.id.register_question_button -> {
                if ((getEditText1.equals("") || getEditText2.equals("") || getEditText3.equals("") || getEditText4.equals("") || getEditText5.equals(""))
                        ||
                        (isBlank1 || isBlank2 || isBlank3 || isBlank4 || isBlank5)
                ) {
                    Toast.makeText(this, "질문을 모두 등록한 후 제출해주세요.", Toast.LENGTH_SHORT).show()
                }

                else {

                    if (finalCheck) {
                        AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
                    } else {
                        finalCheck = true
                        var finishButtonControl = register_question_button.getLayoutParams() as ConstraintLayout.LayoutParams
                        finishButtonControl.width = 180
                        finishButtonControl.height = 180
                        register_question_button.setBackground(getDrawable(R.drawable.finish_register_button))
                    }
                }



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

