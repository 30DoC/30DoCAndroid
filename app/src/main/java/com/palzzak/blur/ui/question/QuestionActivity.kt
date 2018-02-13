package com.palzzak.blur.ui.question

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Question
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import javax.inject.Inject


/**
 * Created by stevehan on 2018. 2. 1..
 */
class QuestionActivity : DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener {

    @Inject
    lateinit var mQuestionPresenter: QuestionPresenter

    @Inject
    lateinit var mSharedPrefs: SharedPreferences

    private lateinit var myList: ListView
    private lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mQuestionPresenter.init(mSharedPrefs.getLong(Constants.PREF_MEMBER_ID_KEY, -1L))


        myList = findViewById(R.id.listView) as ListView
        myList.itemsCanFocus = true
        myAdapter = MyAdapter()
        myList.adapter = myAdapter


        back_button.setOnClickListener(this)
        register_question_button.setOnClickListener(this)
    }


    inner class MyAdapter : BaseAdapter() {
        private val mInflater: LayoutInflater
        var myItems = ArrayList<Question>()

        init {
            mInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            for (i in 0..9) {
                val listItem = Question("", true, 0, Date(), Date())
                myItems.add(listItem)
            }
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return myItems.size
        }

        override fun getItem(position: Int): Any {
            return myItems.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val holder: ViewHolder
            if (convertView == null) {
                holder = ViewHolder()
                convertView = mInflater.inflate(R.layout.listview_btn_item, null)
                holder.caption = convertView!!
                        .findViewById(R.id.item_caption) as EditText
                convertView.tag = holder
                val myArray = resources.getStringArray(R.array.question_hints)
                for (i in 0 until myArray.size) {
                    holder.caption!!.setHint(myArray[i])
                }
            } else {
                holder = convertView.tag as ViewHolder
            }

            holder.caption!!.id = position


            holder.caption!!.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val position = v.id
                    val Question = v as EditText
                    myItems.get(position).question = Question.getText().toString()
                }
            }

            return convertView
        }
    }

    internal inner class ViewHolder {
        var caption: EditText? = null
        var btnO: Button? = null
        var btnX: Button? = null
    }



    override fun onClick(v: View) {

        when (v.getId()) {

            R.id.back_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
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

    override fun printQuestionDescription() {
        var first = getResources().getString(R.string.question_description1)
        var second = getResources().getString(R.string.question_description2)
        var text = "<strong>$first</strong>\n$second"
        question_description.text = fromHtml(text)
    }

    override fun setQuestions(questions: ArrayList<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

