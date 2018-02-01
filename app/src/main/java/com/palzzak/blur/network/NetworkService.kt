package com.palzzak.blur.network

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

class NetworkService {
    fun logIn(mobileId: String): String = "1"
    fun observeStatus(mobileId: String): ServiceStatus = ServiceStatus.WAITING
    fun observeChat(mobileId: String, chatId: String, offset: Int) = 1
    fun sendAudioRecord(mobileId: String, chatId: String, timestamp: Int) = 1
    fun quit(mobileId: String, charId: String) {}
    fun getQuestions(mobileId: String) = arrayListOf("Q1", "Q2")
    fun postAnswers(mobileId: String, questionId: String) = 100
    fun chatJoinResponse(mobileId: String, chatId: String, response: Boolean) {}
    fun updateMyQuestions(mobileId: String, questions: List<String>) {}
}