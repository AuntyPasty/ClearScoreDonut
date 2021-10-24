package daveho.co.auntypasty.clearscoredonut.api

import daveho.co.auntypasty.clearscoredonut.api.model.ClearScoreResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ServerInterface {

    @GET("endpoint.json")
    fun getScore(): Observable<ClearScoreResponse>
}