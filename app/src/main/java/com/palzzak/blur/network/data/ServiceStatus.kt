package com.palzzak.blur.network.data

/**
 * Created by jaeyoonyoo on 2018. 2. 4..
 */
data class ServiceStatus(val status: String) {
    companion object {
        const val WAITING = "WAITING"
        const val CHATTING = "CHATTING"
        const val NONE = "NONE"
    }
}