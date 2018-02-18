package com.palzzak.blur.ui.question

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.palzzak.blur.R
import com.palzzak.blur.network.response.Question
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Created by stevehan on 2018. 2. 1..
 */
class QuestionActivity : DaggerAppCompatActivity(), QuestionContract.View, View.OnClickListener {

    @Inject
    lateinit var mQuestionPresenter: QuestionPresenter

    @Inject
    lateinit var mSharedPrefs: SharedPreferences

    private lateinit var mListView: ListView
    private lateinit var myAdapter: MyAdapter

    private var mCareList: ArrayList<Question> = ArrayList()


    var isBlank: Boolean = false
    var buttonO: Boolean = false
    var buttonUnchecked: Boolean = true
    var buttonRegister: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mQuestionPresenter.init(mSharedPrefs.getLong(Constants.PREF_MEMBER_ID_KEY, -1L))

        mListView = findViewById(R.id.listView)

        for (i in 0..9) {
            mCareList.add(Question(
                    i.toString() + "번째" + " 질문입니다.",
                    true,
                    i,
                    Date(),
                    Date()
            ))
        }

        myAdapter = MyAdapter(this, mCareList)


        mListView.adapter = myAdapter

        mListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, long ->
            Toast.makeText(
                    applicationContext,
                    "ITEM CLICK = " + position,
                    Toast.LENGTH_SHORT
            ).show()

        }


        back_button.setOnClickListener(this)
        register_question_button.setOnClickListener(this)
    }


    inner class MyAdapter(questionActivity: QuestionActivity, mCareList: ArrayList<Question>) : BaseAdapter() {

        private val mInflater: LayoutInflater
        var myItems = mCareList


        init {
            mInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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

        override fun getViewTypeCount(): Int {
            return count
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val myArray = resources.getStringArray(R.array.question_hints)
            var holder: ViewHolder

            if (convertView == null) {
                holder = ViewHolder()
                convertView = mInflater.inflate(R.layout.listview_btn_item, null)
                holder.caption = convertView!!.findViewById(R.id.item_caption) as? EditText
                holder.oButton = convertView!!.findViewById(R.id.o_button) as? Button
                holder.xButton = convertView!!.findViewById(R.id.x_button) as? Button
                convertView.tag = holder
            } else {
                holder = convertView!!.tag as ViewHolder
            }


            holder.caption!!.hint = myArray[position]

            holder.oButton!!.tag = position
            holder.xButton!!.tag = position


            var buttonClickListener = View.OnClickListener { v ->

                when (v.id) {
                    R.id.o_button -> {
                        buttonO = true
                        buttonUnchecked = false

                        if (buttonO) {
                            holder.oButton!!.setBackground(getDrawable(R.drawable.o_purple_button))
                            holder.xButton!!.setBackground(getDrawable(R.drawable.x_gray_button))
                        }
                    }

                    R.id.x_button -> {
                        buttonO = false
                        buttonUnchecked = false

                        if (!buttonO) {
                            holder.oButton!!.setBackground(getDrawable(R.drawable.o_gray_button))
                            holder.xButton!!.setBackground(getDrawable(R.drawable.x_purple_button))
                        }
                    }
                }
            }


            holder.oButton!!.setOnClickListener(buttonClickListener)
            holder.xButton!!.setOnClickListener(buttonClickListener)


            if (myItems.get(position).question.equals("")) isBlank = true

            return convertView
        }


        inner class ViewHolder {
            var caption: EditText? = null
            var oButton: Button? = null
            var xButton: Button? = null
        }


    }


    override fun onClick(v: View) {


        when (v.getId()) {

            R.id.back_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
            }


            R.id.register_question_button -> {
                if (isBlank || buttonUnchecked) {
                    Toast.makeText(this, "질문을 모두 등록한 후 제출해주세요.", Toast.LENGTH_SHORT).show()
                } else {

                    if (buttonRegister) {
                        AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
                    } else {
                        buttonRegister = true
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


    override fun printDescription() {
        var first = getResources().getString(R.string.question_description1)
        var second = getResources().getString(R.string.question_description2)
        var text = "<strong>$first</strong>\n$second"
        question_description.text = fromHtml(text)
    }

    override fun setQuestions(questions: ArrayList<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

