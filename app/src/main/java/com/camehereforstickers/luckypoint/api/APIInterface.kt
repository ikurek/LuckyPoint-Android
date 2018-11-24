package com.camehereforstickers.luckypoint.api

import com.camehereforstickers.luckypoint.model.RankModel
import com.camehereforstickers.luckypoint.model.TicketModel
import com.camehereforstickers.luckypoint.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIInterface {

    @GET("/api/RankController")
    fun getAllRanks(): Call<List<RankModel>>

    @GET("/api/RankController/{id}")
    fun getRankById(@Path("id") id: Int): Call<RankModel>

    @GET("/api/TicketController")
    fun getAllTickets(): Call<List<TicketModel>>

    @GET("/api/TicketController/{id}")
    fun getTicketById(@Path("id") id: Int): Call<TicketModel>

    @POST("/api/TicketController")
    fun postTicket(@Body ticket: TicketModel): Call<TicketModel>

    @POST("/api/UserController")
    fun postUser(@Body user: UserModel): Call<UserModel>
}