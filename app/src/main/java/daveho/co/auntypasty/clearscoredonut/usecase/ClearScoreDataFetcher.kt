package daveho.co.auntypasty.clearscoredonut.usecase

import daveho.co.auntypasty.clearscoredonut.entity.ClearScoreRangeData
import io.reactivex.Observable

/**
 * Use case to get the data
 */
interface ClearScoreDataFetcher {

    fun getScore(): Observable<ClearScoreRangeData>
}