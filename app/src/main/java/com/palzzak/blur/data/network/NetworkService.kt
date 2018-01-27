package com.palzzak.blur.data.network

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

class NetworkService {
    fun logIn(id: String): ServiceStatus = ServiceStatus.WAITING
    fun registerMyQuestions() {
        TODO("Retrofit")
    }
    fun getSetOfQuestions() {
        TODO("Retrofit")
    }
    fun getQuizResult(id: String, answer: List<Boolean>) = {
        TODO("Retrofit")
    }
    fun requestChatSocket(id1: String, id2: String) {
        TODO("Retrofit")
    }
}