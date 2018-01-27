package com.palzzak.blur.activities.splash

import android.os.Bundle
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.palzzak.blur.util.Constants
import java.util.*
import kotlin.reflect.KClass


/**
 * Created by yooas on 2018-01-11.
 */
class SplashActivity: DaggerAppCompatActivity(), SplashContract.View {
    @Inject
    lateinit var mSplashPresenter: SplashPresenter

    @Inject
    lateinit var mSharePref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSplashPresenter.printInitialText()
        mSplashPresenter.logIn(mSharePref.getString(Constants.PREF_MOBILE_ID_KEY, ""))
    }

    override fun printText(text: String) {
        id_textview.text = text
    }

    override fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun <T : Any> goToNextActivity(activity: KClass<T>) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, activity.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }

    override fun somethingIsWrong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveIdPreference(id: String) {
        mSharePref.edit().putString(Constants.PREF_MOBILE_ID_KEY, id).apply()
    }
}
