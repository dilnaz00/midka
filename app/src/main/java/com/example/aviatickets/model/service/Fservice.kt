package com.example.aviatickets.model.service

import com.example.aviatickets.model.entity.Offer
import retrofit2.Response
import retrofit2.http.GET

interface Fservice {
    @GET("offer_list")
    suspend fun getOffers(): Response<List<Offer>>
}
