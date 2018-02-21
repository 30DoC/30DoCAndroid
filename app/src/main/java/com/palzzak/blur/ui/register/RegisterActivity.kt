package com.palzzak.blur.ui.register

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.palzzak.blur.R
import com.palzzak.blur.network.response.SimpleQuiz
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject


/**
 * Created by stevehan on 2018. 2. 1..
 */
class RegisterActivity : DaggerAppCompatActivity(), RegisterContract.View, View.OnClickListener{
    @Inject
    lateinit var mRegisterPresenter: RegisterPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    private val mAdapter = RegisterAdapter()

    private var mMemberId = -1L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mMemberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mRegisterPresenter.loadInitialQuestionSet(mMemberId)

        id_back_button.setOnClickListener(this)
        id_register_button.setOnClickListener(this)
        id_question_recycler.layoutManager = LinearLayoutManager(this)
        id_question_recycler.adapter = mAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.id_back_button -> {
                AlertDialogFactory.show(fragmentManager, AlertDialogFactory.DIALOG_QUESTION_TAG_QUIT)
            }
            R.id.id_register_button -> {
                mRegisterPresenter.registQuiz(mMemberId, mAdapter.mData)
            }
        }
    }

    override fun setQuestions(questions: List<SimpleQuiz>) {
        mAdapter.mData = questions
        mAdapter.notifyDataSetChanged()
    }

    override fun finishActivity() {
        finish()
    }
}