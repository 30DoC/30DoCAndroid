package com.palzzak.blur.ui.question

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
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

        mListView.setVerticalScrollBarEnabled(false)


        back_button.setOnClickListener(this)
        register_question_button.setOnClickListener(this)

        mListView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                mListView.setVerticalScrollBarEnabled(true)
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int,
                                  visibleItemCount: Int, totalItemCount: Int) {

            }
        })


    }


    inner class MyAdapter(questionActivity: QuestionActivity, mCareList: ArrayList<Question>) : BaseAdapter() {

        private val mInflater: LayoutInflater
        var myItems = mCareList

        var questionTextMap = HashMap<Int, String>()
        var buttonCheckedMap = HashMap<Int, Boolean?>()



        init {
            mInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            for (i in 0..9) {
                questionTextMap[i] = ""
                buttonCheckedMap[i] = null
            }
            notifyDataSetChanged()
        }

        fun checkEdittextFull(): Boolean {
            for (entry in questionTextMap) {
                if (entry.value.equals("")) return false
            }
            return true
        }

        fun checkOXisFull(): Boolean {
            for (entry in buttonCheckedMap) {
                if (entry.value == null) return false
            }
            return true
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


            holder.caption!!.setText(questionTextMap!!.get(position))
            holder.caption!!.hint = myArray[position]

            holder.oButton!!.tag = buttonCheckedMap!!.get(position)
            holder.xButton!!.tag = buttonCheckedMap!!.get(position)


            var buttonClickListener = View.OnClickListener { v ->

                when (v.id) {

                    R.id.o_button -> {
                        buttonO = true
                        buttonCheckedMap.put(position, buttonO)
                        checkOXisFull()

                        if (buttonO) {
                            holder.oButton!!.setBackground(getDrawable(R.drawable.o_purple_button))
                            holder.xButton!!.setBackground(getDrawable(R.drawable.x_gray_button))
                        }
                    }

                    R.id.x_button -> {
                        buttonO = false
                        buttonCheckedMap.put(position, buttonO)
                        checkOXisFull()

                        if (!buttonO) {
                            holder.oButton!!.setBackground(getDrawable(R.drawable.o_gray_button))
                            holder.xButton!!.setBackground(getDrawable(R.drawable.x_purple_button))
                        }
                    }
                }
            }


            holder.oButton!!.setOnClickListener(buttonClickListener)
            holder.xButton!!.setOnClickListener(buttonClickListener)

            holder.caption!!.addTextChangedListener(object : TextWatcher {

                override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

                }

                override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int,
                                               arg3: Int) {
                }

                override fun afterTextChanged(arg0: Editable) {
                    questionTextMap.put(position, holder.caption!!.getText().toString())

                }
            })


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

                if (myAdapter.checkEdittextFull() && myAdapter.checkOXisFull()) {
                    if (buttonRegister) {
                        AlertDialogFactory.show(fragmentManager, Constants.DIALOG_QUESTION_TAG_QUIT)
                    } else {
                        buttonRegister = true
                        var finishButtonControl = register_question_button.getLayoutParams() as ConstraintLayout.LayoutParams
                        finishButtonControl.width = 180
                        finishButtonControl.height = 180
                        register_question_button.setBackground(getDrawable(R.drawable.finish_register_button))
                    }

                } else {

                    Toast.makeText(this, "질문을 모두 등록한 후 제출해주세요.", Toast.LENGTH_SHORT).show()
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

