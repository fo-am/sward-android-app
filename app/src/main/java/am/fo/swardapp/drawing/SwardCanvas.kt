package am.fo.swardapp.drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SwardCanvas constructor(context: Context,
                              attrs: AttributeSet? = null,
                              defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.STROKE
        }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawCircle(10.0f, 10.0f, 10.0f, paint)
            it.drawText("Insert graph here...",20.0f,20.0f,textPaint)
        }
    }
}
