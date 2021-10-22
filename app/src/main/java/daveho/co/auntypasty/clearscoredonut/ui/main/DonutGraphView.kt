package daveho.co.auntypasty.clearscoredonut.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat.getColor
import daveho.co.auntypasty.clearscoredonut.R
import daveho.co.auntypasty.clearscoredonut.ui.ClearScoreDataModel
import daveho.co.auntypasty.clearscoredonut.util.dpToPx

/**
 * This is a custom view to represent the score in the form of an arc within a circle.
 * The view is designed to be adaptable to any reasonable size allocated to it. There are no hardcoded dimensions for the
 * arcs or circles. The final width and height are derived from onSize changed and the shape parameters are derived from these.
 */
class DonutGraphView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    companion object {

        // Define the border circle diameter in terms of the max allowed width or height given to it
        // Define the Score circle diameter in terms of how it relates to the diameter of the border circle.
        const val BORDER_CIRCLE_DIAMETER_TO_MAX_DIMENSION_RATIO = 0.7f  // Must be less then 1
        const val SCORE_CIRCLE_DIMENSION_TO_BORDER_CIRCLE_RATIO = 0.9f

        // 0 degrees is at the 3 O'clock position
        const val ANGLE_DEAD_TOP = 270f
        const val DEGREES_IN_CIRCLE = 360f   // We should all know what this means
    }

    private var graphData: ClearScoreDataModel? = null
    private var borderCircleRadiusPx = 0f
    private var scoreCircleRadiusPx = 0f

    private var centreX = 0f
    private var centreY = 0f

    private val borderColour = getColor(context, R.color.border_circle)
    private val scoreColour = getColor(context, R.color.score_circle)

    private var baseArcRect = RectF()

    init {
        // Nothing to do here this time
    }

    fun setData(scores: ClearScoreDataModel) {
        this.graphData = scores
        invalidate()
    }

    // Called after the view is set up so use this point to set up bounds and dimensions
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Find whether the height or width is the smallest
        val lowestDimension = if (w < h) w.toFloat() else h.toFloat()
        borderCircleRadiusPx = (lowestDimension * BORDER_CIRCLE_DIAMETER_TO_MAX_DIMENSION_RATIO) / 2 // Divide by 2 to define the radius
        scoreCircleRadiusPx = (borderCircleRadiusPx * SCORE_CIRCLE_DIMENSION_TO_BORDER_CIRCLE_RATIO)

        // Define the centre. This will the centre of our circles/arcs
        centreX = w.toFloat() / 2
        centreY = h.toFloat() / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBorderCircle(canvas)
        graphData?.let {
            if (it.maxScore > 0) {
                drawScoreArc(canvas, it)
            }
        }
    }

    private fun drawBorderCircle(canvas: Canvas) {
        val circlePaint = Paint()
        circlePaint.color = borderColour
        circlePaint.strokeWidth = dpToPx(4)
        circlePaint.style = Paint.Style.STROKE
        circlePaint.isAntiAlias = true

        canvas.drawCircle(centreX, centreY, borderCircleRadiusPx, circlePaint)
    }

    private fun drawScoreArc(canvas: Canvas, graphData: ClearScoreDataModel) {
        // Set the rectangle around the arc based on our calculated required radius
        // As it's a circle we end up defining a square

        baseArcRect.set(
            centreX - scoreCircleRadiusPx,
            centreY - scoreCircleRadiusPx,
            centreX + scoreCircleRadiusPx,
            centreY + scoreCircleRadiusPx
        )

        val arcPaint = Paint()
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = dpToPx(8)
        arcPaint.isAntiAlias = true
        arcPaint.color = scoreColour
        arcPaint.strokeCap = Paint.Cap.ROUND

        // calculate the score as a fraction which will be used to define the number of degrees to sweep the arc
        val scoreAsFraction = (graphData.score - graphData.minScore).toFloat() / (graphData.maxScore - graphData.minScore).toFloat()
        val sweepAngle = DEGREES_IN_CIRCLE * scoreAsFraction

        canvas.drawArc(baseArcRect, ANGLE_DEAD_TOP, sweepAngle, false, arcPaint)
    }
}