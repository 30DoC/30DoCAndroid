package com.palzzak.blur.ui.chat

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatContract {
    interface Presenter: BasePresenter<View> {

        fun observeRoom(roomId: Long, offset: Long)
    }

    interface View: BaseView<Presenter> {

    }
}