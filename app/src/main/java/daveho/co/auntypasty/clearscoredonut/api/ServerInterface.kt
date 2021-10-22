package daveho.co.auntypasty.clearscoredonut.api

import daveho.co.auntypasty.clearscoredonut.api.model.ClearScoreResponse
import daveho.co.auntypasty.clearscoredonut.util.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ServerInterface {

    @GET("endpoint.json")
    fun getScore(): Observable<ClearScoreResponse>
}