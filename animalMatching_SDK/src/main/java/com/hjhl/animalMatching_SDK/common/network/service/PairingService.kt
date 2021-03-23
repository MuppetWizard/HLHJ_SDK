package com.hjhl.animalMatching_SDK.common.network.service

import com.hjhl.animalMatching_SDK.common.model.MatchingMode
import retrofit2.http.POST
import retrofit2.http.Query

interface PairingService {

    @POST("zodiacpairing?")
    fun getPairingResult(@Query("sdk_key")key: String,
                         @Query("men") men: String,
                         @Query("women") women: String) :
            retrofit2.Call<MatchingMode>
}