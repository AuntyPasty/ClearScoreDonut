package daveho.co.auntypasty.clearscoredonut.usecase

import daveho.co.auntypasty.clearscoredonut.api.ServerInterface
import daveho.co.auntypasty.clearscoredonut.api.model.toClearScoreRangeDataDomain
import daveho.co.auntypasty.clearscoredonut.entity.ClearScoreRangeData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Implementation of use case to fetch the data.
 * In this case it's using the server interface to ge the data but can extended to fetch data from
 * local storage such as a database or Shared Preferences.
 */
class PojoClearScoreDataFetcher @Inject constructor(
    private val serverInterface: ServerInterface
) : ClearScoreDataFetcher {

    override fun getScore(): Observable<ClearScoreRangeData> =
        serverInterface.getScore()
            .map {
                it.toClearScoreRangeDataDomain()
            }
            .onErrorReturn { ClearScoreRangeData.INVALID }
}