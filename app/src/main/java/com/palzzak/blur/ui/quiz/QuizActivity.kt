package com.palzzak.blur.ui.quiz

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz_desc.*
import javax.inject.Inject
import android.view.animation.AccelerateDecelerateInterpolator


class QuizActivity : DaggerAppCompatActivity(), QuizContract.View {
    @Inject
    lateinit var mQuizPresenter: QuizPresenter

    val mAdapter: QuizAdapter = QuizAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desc)

        mQuizPresenter.printInitialText()
    }

    override fun printDescAndTransit(numberOfQuestions: Int) {
        id_desc.text = String.format(getString(R.string.quiz_description), numberOfQuestions)
        startQuizAfterSeconds()
    }

    private fun startQuizAfterSeconds() {
        object: CountDownTimer(5000, 1000) {
            var count = 5
            override fun onFinish() {
                transitToQuizScreen()
            }

            override fun onTick(p0: Long) {
                id_countdown.text = count.toString()
                count--
            }

        }.start()
    }

    private fun transitToQuizScreen() {
        setContentView(R.layout.activity_quiz)
        id_quiz_recycler.adapter = mAdapter
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(id_quiz_recycler)
        id_quiz_recycler.addItemDecoration(LinePagerIndicatorDecoration())
    }

    class LinePagerIndicatorDecoration : RecyclerView.ItemDecoration() {
        private val mPaint = Paint().apply {
            strokeCap = Paint.Cap.ROUND
            strokeWidth = mIndicatorStrokeWidth
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
        private val DP = Resources.getSystem().displayMetrics.density
        private val mIndicatorStrokeWidth = DP * 2
        private val mIndicatorItemLength = DP * 16F
        private val mIndicatorItemPadding = DP * 4F
        private val mIndicatorHeight = DP * 16F
        private val mColorActive = Color.WHITE
        private val mColorInactive = Color.GRAY
        private val mInterpolator = AccelerateDecelerateInterpolator()

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)

            val itemCount = parent.adapter.itemCount

            // center horizontally, calculate width and subtract half from center
            val totalLength = mIndicatorItemLength * itemCount
            val paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding
            val indicatorTotalWidth = totalLength + paddingBetweenItems
            val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

            // center vertically in the allotted space
            val indicatorPosY = parent.height - mIndicatorHeight / 2f

            drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount)

            val layoutManager = parent.layoutManager as LinearLayoutManager
            val activePosition = layoutManager.findFirstVisibleItemPosition()
            if (activePosition == RecyclerView.NO_POSITION) {
                return
            }

            // find offset of active page (if the user is scrolling)
            val activeChild = layoutManager.findViewByPosition(activePosition)
            val left = activeChild.left
            val width = activeChild.width

            // on swipe the active item will be positioned from [-width, 0]
            // interpolate offset for smooth animation
            val progress = mInterpolator.getInterpolation(left * -1 / width.toFloat())

            drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)
        }

        private fun drawHighlights(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, highlightPosition: Int, progress: Float, itemCount: Int) {

            mPaint.color = mColorActive

            // width of item indicator including padding
            val itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

            if (progress == 0F) {
                // no swipe, draw a normal indicator
                val highlightStart = indicatorStartX + itemWidth * highlightPosition;
                c.drawLine(highlightStart, indicatorPosY,
                        highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);
            } else {
                var highlightStart = indicatorStartX + itemWidth * highlightPosition;
                // calculate partial highlight
                val partialLength = mIndicatorItemLength * progress;

                // draw the cut off highlight
                c.drawLine(highlightStart + partialLength, indicatorPosY,
                        highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);

                // draw the highlight overlapping to the next item as well
                if (highlightPosition < itemCount - 1) {
                    highlightStart += itemWidth;
                    c.drawLine(highlightStart, indicatorPosY,
                            highlightStart + partialLength, indicatorPosY, mPaint);
                }
            }
        }

        private fun drawInactiveIndicators(c: Canvas, indicatorStartX: Float,
                                           indicatorPosY: Float, itemCount: Int) {
            mPaint.color = mColorInactive

            // width of item indicator including padding
            val itemWidth = mIndicatorItemLength + mIndicatorItemPadding

            var start = indicatorStartX
            for (i in 0 until itemCount) {
                // draw the line for every item
                c.drawLine(start, indicatorPosY, start + mIndicatorItemLength, indicatorPosY, mPaint)
                start += itemWidth
            }
        }
    }
}
