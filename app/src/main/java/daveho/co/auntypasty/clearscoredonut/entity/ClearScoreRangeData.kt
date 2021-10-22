package daveho.co.auntypasty.clearscoredonut.entity

data class ClearScoreRangeData(
    val score: Int,
    val minScore: Int,
    val maxScore: Int
) {
    companion object {
        val INVALID  = ClearScoreRangeData(-1, -1, -1)
    }
}