package com.paljjak.thirtydoc.data.network

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

class NetworkService {
    fun logIn(id: String): ServiceStatus = ServiceStatus.WAITING
    fun getNumberOfQuestions(): Int = 10
}