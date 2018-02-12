package com.palzzak.blur.ui.chat

import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palzzak.blur.R
import com.palzzak.blur.data.Message

/**
 * Created by jaeyoonyoo on 2018. 2. 12..
 */
class ChatAdapter(private val mMemberId: Long): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    var mData: ArrayList<Message>? = null

    override fun getItemViewType(position: Int): Int {
        /*val id = mData?.get(position)?.mRegistId ?: return 0
        if (id == mMemberId) return 0
        return 1*/
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = if (viewType == 0) R.layout.item_chat_mine else R.layout.item_chat_opponent
        val itemView = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = mData?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = mData?.get(position) ?: return
        holder.setMessage(message)
    }

    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setMessage(message: Message) {
        }
    }
}