package com.palzzak.blur.ui.quiz

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.palzzak.blur.R
import com.palzzak.blur.network.data.QuizSet
import com.palzzak.blur.ui.chat.ChatActivity
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz_desc.*
import kotlinx.android.synthetic.main.result_subactivity.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.*
import javax.inject.Inject


class QuizActivity : DaggerAppCompatActivity(), QuizContract.View, View.OnClickListener {

    @Inject
    lateinit var mQuizPresenter: QuizPresenter

    @Inject
    lateinit var mSharedPrefs: SharedPreferences

    private var mMemberId: Long = -1L

    private val mAdapter = object: FragmentPagerAdapter(supportFragmentManager) {
        var mFragments: List<Fragment> = arrayListOf()
        override fun getItem(position: Int): Fragment = mFragments[position]
        override fun getCount(): Int = mFragments.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desc)

        mMemberId = mSharedPrefs.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
    }

    override fun onResume() {
        super.onResume()

        mQuizPresenter.init(mMemberId)
        startQuizAfterSeconds()
    }

    override fun printTextWithNumber(num: Int) {
        id_desc.text = String.format(getString(R.string.quiz_description), num)
    }

    private fun startQuizAfterSeconds() {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                launch(UI) {
                    transitToQuizScreen()
                }
            }

        }, 1000)
    }

    private fun transitToQuizScreen() {
        setContentView(R.layout.activity_quiz)
        id_quiz_pager.adapter = mAdapter
        id_quiz_pager.pageMargin
        id_prev_button.setOnClickListener(this)
        id_answer_false_button.setOnClickListener(this)
        id_answer_true_button.setOnClickListener(this)

        mQuizPresenter.loadQuiz()
        updatePageProgress(0)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.id_prev_button -> {
                if (id_quiz_pager.currentItem == 0) {
                    AlertDialogFactory.show(fragmentManager, AlertDialogFactory.DIALOG_QUIZ_TAG_QUIT)
                } else {
                    setCurrentItem(id_quiz_pager.currentItem - 1)
                }
            }
            else -> {
                mQuizPresenter.setQuizAnswer(id_quiz_pager.currentItem, v.id == R.id.id_answer_true_button)
                if (id_quiz_pager.currentItem + 1 >= id_quiz_pager.adapter.count) {
                    mQuizPresenter.submitMyAnswers(mMemberId)
                } else {
                    setCurrentItem(id_quiz_pager.currentItem + 1)
                }
            }
        }
    }

    override fun setQuestions(quizSet: QuizSet) {
        mAdapter.mFragments = QuizFragment.createFragments(quizSet.quizList)
        mAdapter.notifyDataSetChanged()
    }

    private fun setCurrentItem(page: Int) {
        id_quiz_pager.currentItem = page
        updatePageProgress(page)
    }

    private fun updatePageProgress(page: Int) {
        val oneBasedPage = page + 1
        id_page_number_text.text = "${oneBasedPage}/${id_quiz_pager.adapter.count}"
        val progress = (oneBasedPage.toDouble() / mAdapter.count * 100).toInt()
        ObjectAnimator.ofInt(id_page_progress, "progress", progress).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
        }.start()
    }

    override fun showResultScreen(result: Int) {
        setContentView(R.layout.result_subactivity)
        id_result_progress.mTextView = id_result_text

        ObjectAnimator.ofInt(id_result_progress, "progress", result).apply {
            duration = 1000
        }.start()

    }
    override fun congratulations() {
        id_result_desc_text.visibility = View.INVISIBLE
        Glide.with(this@QuizActivity)
                .asGif()
                .load(R.drawable.congratulations)
                .into(id_result_background_img)

        runBlocking {
            delay(2000L)
        }
    }

    override fun goToChatActivity(opponentId: Long, roomId: Long) {
        saveChatInfoPref(opponentId, roomId)

        val intent = Intent().apply {
            setClass(this@QuizActivity, ChatActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    private fun saveChatInfoPref(opponentId: Long, roomId: Long) {
        mSharedPrefs.edit().apply {
            putLong("opponentId", opponentId)
            putLong("roomId", roomId)
        }.apply()
    }
}

class ResultProgressBar(context: Context, attributeSet: AttributeSet): ProgressBar(context, attributeSet) {
    var mTextView: TextView? = null

    override fun setProgress(progress: Int) {
        super.setProgress(progress)
        mTextView?.text = "$progress%"
    }
}