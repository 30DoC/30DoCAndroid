package com.palzzak.blur.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

interface APIService {
    @FormUrlEncoded
    @POST("/api/v1/member/login")
    fun signIn(@Field("uniqueKey") mobileId: String): Call<Long>

    @FormUrlEncoded
    @POST("/api/v1/member/observeStatus")
    fun observeStatus(@Field("memberId") memberId: Long): Call<String>

    fun observeChat(mobileId: String, chatId: String, offset: Int) = 1
    fun sendAudioRecord(mobileId: String, chatId: String, timestamp: Int) = 1
    fun quit(mobileId: String, charId: String) {}
    fun getQuestions(mobileId: String) = arrayListOf("Q1", "Q2")
    fun postAnswers(mobileId: String, questionId: String) = 100
    fun chatJoinResponse(mobileId: String, chatId: String, response: Boolean) {}
    fun updateMyQuestions(mobileId: String, questions: List<String>) {}
}