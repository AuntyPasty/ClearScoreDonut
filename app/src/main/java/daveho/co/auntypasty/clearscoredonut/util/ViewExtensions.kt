package daveho.co.auntypasty.clearscoredonut.util

import android.util.TypedValue
import android.view.View
import androidx.annotation.Dimension

/**
 * Helper method to convert DP to pixels
 */
fun View.dpToPx(@Dimension(unit = Dimension.DP) dp: Int): Float {
    val resources = this.resources
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
}