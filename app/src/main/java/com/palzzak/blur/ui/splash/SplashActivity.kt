package com.palzzak.blur.ui.splash

import android.os.Bundle
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.palzzak.blur.ui.intro.IntroActivity
import com.palzzak.blur.network.ServiceStatus
import com.palzzak.blur.util.Constants
import java.util.*


/**
 * Created by yooas on 2018-01-11.
 */
class SplashActivity: DaggerAppCompatActivity(), SplashContract.View {
    @Inject
    lateinit var mSplashPresenter: SplashPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSplashPresenter.printInitialText()
        val mobileId = mSharedPref.getString(Constants.PREF_MOBILE_ID_KEY, "")
        val memberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mSplashPresenter.logIn(mobileId, memberId)
    }

    override fun printText(text: String) {
        id_textview.text = text
    }

    override fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun goToNextActivity(status: ServiceStatus) {
        var activity = IntroActivity::class.java

//        if (status == ServiceStatus.CHATTING) {
//            activity = ChatActivity::class.java
//        }

        Timer().schedule(object: TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, activity)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }

    override fun somethingIsWrong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveIdPreference(mobileId: String, memberId: Long) {
        mSharedPref.edit().putString(Constants.PREF_MOBILE_ID_KEY, mobileId).apply()
        mSharedPref.edit().putLong(Constants.PREF_MEMBER_ID_KEY, memberId).apply()
    }
}
