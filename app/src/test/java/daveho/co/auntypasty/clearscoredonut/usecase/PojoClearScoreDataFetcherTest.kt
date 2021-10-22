package daveho.co.auntypasty.clearscoredonut.usecase

import daveho.co.auntypasty.clearscoredonut.api.ServerInterface
import daveho.co.auntypasty.clearscoredonut.api.model.ClearScoreResponse
import daveho.co.auntypasty.clearscoredonut.api.model.CreditReportInfo
import daveho.co.auntypasty.clearscoredonut.entity.ClearScoreRangeData
import io.reactivex.Observable
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.net.ConnectException

class PojoClearScoreDataFetcherTest {



    @Test
    fun `test successful api call returns correct data`() {
        // Given
        val dummyCreditInfo = CreditReportInfo.EMPTY.copy(score = 333, minScoreValue = 0, maxScoreValue = 1000)
        val dummySuccessResult = ClearScoreResponse.EMPTY.copy(creditReportInfo = dummyCreditInfo)

        val mockServerInterface = mock<ServerInterface> { on { getScore() } doReturn Observable.just(dummySuccessResult) }
        val sut = PojoClearScoreDataFetcher(mockServerInterface)
        val expected = ClearScoreRangeData(333, 0, 1000)

        //When
        val result = sut.getScore().test()

        //then
        result.assertNoErrors()
        result.assertValue(expected)
    }

    @Test
    fun `test failed api call returns correct ERROR data`() {
        // Given
        val mockServerInterface = mock<ServerInterface> { on { getScore() } doReturn Observable.error(ConnectException()) }
        val sut = PojoClearScoreDataFetcher(mockServerInterface)
        val expected = ClearScoreRangeData.INVALID

        //When
        val result = sut.getScore().test()

        //then
        result.assertNoErrors()
        result.assertValue(expected)
    }
}