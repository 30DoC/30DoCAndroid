package com.palzzak.blur.ui.intro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.palzzak.blur.R
import com.palzzak.blur.ui.register.RegisterActivity
import com.palzzak.blur.ui.quiz.QuizActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

class IntroActivity: DaggerAppCompatActivity(), IntroContract.View, View.OnClickListener{
    @Inject
    lateinit var mIntroPresenter: IntroPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        id_start_button.setOnClickListener(this)
        id_create_mine_button.setOnClickListener(this)

        arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE).map {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(it), 0);
            }
        }
    }

    override fun onClick(p0: View) {
        when (p0.id){
            R.id.id_start_button -> {
                val intent = Intent(this@IntroActivity, QuizActivity::class.java)
                startActivity(intent)
            }
            R.id.id_create_mine_button -> {
                val intent = Intent(this@IntroActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

    }
}