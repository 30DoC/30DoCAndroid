package com.palzzak.blur.network

import com.palzzak.blur.network.pojo.Quiz
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

    fun getQuestions(memberId: Long): Call<List<Quiz>>
    fun observeChat(memberId: Long, chatId: Long, offset: Long) = 1
    fun sendAudioRecord(memberId: Long, chatId: Long, timestamp: Long) = 1
    fun quit(memberId: Long, chatId: Long) {}
    fun postAnswers(memberId: Long, questionId: Long) = 100
    fun chatJoinResponse(memberId: Long, chatId: Long, response: Boolean) {}
    fun updateMyQuestions(memberId: Long, questions: List<Quiz>) {}
}