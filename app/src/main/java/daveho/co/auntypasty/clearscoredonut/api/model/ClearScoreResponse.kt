package daveho.co.auntypasty.clearscoredonut.api.model

import daveho.co.auntypasty.clearscoredonut.entity.ClearScoreRangeData

data class ClearScoreResponse(
    val accountIDVStatus: String,
    val creditReportInfo: CreditReportInfo,
    val dashboardStatus: String,
    val personaType: String,
    val coachingSummary: CoachingSummary,
    val augmentedCreditScore: Int? = null
) {
    companion object {
        val EMPTY = ClearScoreResponse(
            accountIDVStatus = "",
            creditReportInfo =  CreditReportInfo.EMPTY,
            dashboardStatus = "",
            personaType = "",
            coachingSummary = CoachingSummary.EMPTY,
            augmentedCreditScore = null
        )
    }
}

/**
 * Gets the score and min/max range of scores from the response data. There is a lot more data
 * but we do not care about that at the moment.
 */
fun ClearScoreResponse.toClearScoreRangeDataDomain(): ClearScoreRangeData {

    return ClearScoreRangeData(
        score = this.creditReportInfo.score,
        minScore = this.creditReportInfo.minScoreValue,
        maxScore = this.creditReportInfo.maxScoreValue
    )
}

// We can add more methods to get different domain objects from the response depending on requirements.