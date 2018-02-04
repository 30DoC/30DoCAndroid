package com.palzzak.blur.network

import com.palzzak.blur.network.response.Quiz
import com.palzzak.blur.network.response.ServiceStatus
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

interface APIService {
    companion object {
        const val MIME_TYPE_JSON = "application/json; charset=utf-8"

        fun createSimpleRequestBody(body: String) = RequestBody.create(MediaType.parse(MIME_TYPE_JSON), body)
    }

    @POST("/api/v1/member/login")
    fun signIn(@Body mobileId: RequestBody): Call<String>

    @POST("/api/v1/member/observeStatus")
    fun observeStatus(@Body memberId: RequestBody): Call<ServiceStatus>

    fun getQuestions(memberId: Long): Call<List<Quiz>>
    fun observeChat(memberId: Long, chatId: Long, offset: Long) = 1
    fun sendAudioRecord(memberId: Long, chatId: Long, timestamp: Long) = 1
    fun quit(memberId: Long, chatId: Long) {}
    fun postAnswers(memberId: Long, questionId: Long) = 100
    fun chatJoinResponse(memberId: Long, chatId: Long, response: Boolean) {}
    fun updateMyQuestions(memberId: Long, questions: List<Quiz>) {}
}