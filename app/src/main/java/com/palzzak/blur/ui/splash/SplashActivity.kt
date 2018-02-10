package com.palzzak.blur.ui.splash

import android.os.Bundle
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject
import android.content.Intent
import android.content.SharedPreferences
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.palzzak.blur.ui.intro.IntroActivity
import com.palzzak.blur.util.Constants


/**
 * Created by yooas on 2018-01-11.
 */
class SplashActivity: DaggerAppCompatActivity(), SplashContract.View {
    @Inject
    lateinit var mSplashPresenter: SplashPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    var mStatus = "null"

    override fun setStatus(status: String) {
        mStatus = status
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mobileId = mSharedPref.getString(Constants.PREF_MOBILE_ID_KEY, "")
        val memberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mSplashPresenter.logIn(mobileId, memberId)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in).apply {
            setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    while (mStatus == "null") {
                        mSplashPresenter.logIn(mobileId, memberId)
                    }
                    goToActivity(mStatus)
                }

                override fun onAnimationStart(animation: Animation?) {}

            })
        }
        id_intro_image.startAnimation(fadeInAnimation)
    }

    private fun goToActivity(status: String) {
        val intent = Intent().apply {
            when (status) {
                //ServiceStatus.CHATTING -> setClass(this@SplashActivity, ChatActivity::class,java)
                else -> setClass(this@SplashActivity, IntroActivity::class.java)
            }
        }
        startActivity(intent)
        finish()

    }

    override fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun somethingIsWrong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveIdPreference(mobileId: String, memberId: Long) {
        mSharedPref.edit().putString(Constants.PREF_MOBILE_ID_KEY, mobileId).apply()
        mSharedPref.edit().putLong(Constants.PREF_MEMBER_ID_KEY, memberId).apply()
    }
}
