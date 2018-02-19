package com.palzzak.blur.network

import com.palzzak.blur.network.response.QuizSet
import com.palzzak.blur.network.response.ServiceStatus
import com.palzzak.blur.network.response.SimpleQuiz
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

interface APIService {
    companion object {
        const val MIME_TYPE_JSON = "application/json; charset=utf-8"

        fun createSimpleRequestBody(body: String): RequestBody = RequestBody.create(MediaType.parse(MIME_TYPE_JSON), body)
    }

    @POST("/api/v1/member/login")
    fun signIn(@Body mobileId: RequestBody): Call<String>

    @POST("/api/v1/member/observeStatus")
    fun observeStatus(@Body memberId: RequestBody): Call<ServiceStatus>

    @GET("/api/v1/quiz/randomQuiz")
    fun randomQuiz(): Call<QuizSet>

    @POST("/api/v1/quiz/inquireQuiz")
    fun inquireQuiz(@Query("userId") memberId: Long): Call<List<SimpleQuiz>>

    @POST("/api/v1/quiz/registQuiz")
    fun registQuiz(@Query("userId") memberId: Long, @Body quizList: List<SimpleQuiz>): Call<ResponseBody>

    fun observeChat(memberId: Long, chatId: Long, offset: Long) = 1
    fun sendAudioRecord(memberId: Long, chatId: Long, timestamp: Long) = 1
    fun quit(memberId: Long, chatId: Long) {}
    fun submitMyAnswers(memberId: Long, questionId: Long) = 100
    fun chatJoinResponse(memberId: Long, chatId: Long, response: Boolean) {}
}