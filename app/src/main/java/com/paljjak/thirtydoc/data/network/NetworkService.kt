package com.paljjak.thirtydoc.data.network

import org.json.JSONObject

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

abstract class NetworkService {
    fun logIn(id: String): ServiceStatus = ServiceStatus.WAITING
    abstract fun registerMyQuestions()
    abstract fun getSetOfQuestions(): JSONObject
    abstract fun getQuizResult(id: String, answer: List<Boolean>): JSONObject
    abstract fun requestChatSocket(id1: String, id2: String): JSONObject

}