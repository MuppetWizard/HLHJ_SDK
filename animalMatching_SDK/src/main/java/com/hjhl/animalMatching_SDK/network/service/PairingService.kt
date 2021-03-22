package com.hjhl.animalMatching_SDK.network.service

import com.hjhl.animalMatching_SDK.api.ZMKit
import com.hjhl.animalMatching_SDK.model.MatchingMode
import retrofit2.http.POST
import retrofit2.http.Query

interface PairingService {

    @POST("zodiacpairing?sdk_key=${}")
    fun getPairingResult(@Query("men") men: String, @Query("women") women: String) : retrofit2.Call<MatchingMode>
}