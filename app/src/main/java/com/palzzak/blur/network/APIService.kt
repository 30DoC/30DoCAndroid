package com.palzzak.blur.network

import com.palzzak.blur.data.Message
import com.palzzak.blur.network.data.*
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
        private const val MIME_TYPE_JSON = "application/json; charset=utf-8"

        fun createSimpleRequestBody(body: String): RequestBody = RequestBody.create(MediaType.parse(MIME_TYPE_JSON), body)
    }

    @POST("/api/v1/member/login")
    fun logIn(@Query("uniqueKey") mobileId: String): Call<SimpleLong>

    @POST("/api/v1/member/observeStatus")
    fun observeStatus(@Query("userId") memberId: Long): Call<ServiceStatus>

    @GET("/api/v1/quiz/randomQuiz")
    fun randomQuiz(): Call<QuizSet>

    @POST("/api/v1/quiz/inquireQuiz")
    fun inquireQuiz(@Query("userId") memberId: Long): Call<List<SimpleQuiz>>

    @POST("/api/v1/quiz/registQuiz")
    fun registQuiz(@Body quizSet: QuizSet): Call<ServiceStatus>

    @POST("/api/v1/chatRoom/choice")
    fun choice(@Query("userId") memberId: Long): Call<SimpleLong>

    @POST("/api/v1/chatRoom/createRoom")
    fun createRoom(@Query("user1Id") member1Id: Long, @Query("user2Id") member2Id: Long): Call<SimpleLong>

    @POST("/api/v1/chatVoice/observeRoom")
    fun observeChat(@Query("roomId") roomId: Long, @Query("offset") offset: Long): Call<List<Message>>

    fun sendAudioRecord(memberId: Long, chatId: Long, timestamp: Long) = 1
    fun quit(memberId: Long, chatId: Long) {}
    fun submitMyAnswers(memberId: Long, questionId: Long) = 100
    fun chatJoinResponse(memberId: Long, chatId: Long, response: Boolean) {}
}