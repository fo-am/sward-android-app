package am.fo.swardapp.drawing

import am.fo.swardapp.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class SpeciesCountCanvas @JvmOverloads constructor(context: Context,
                                                   attrs: AttributeSet? = null,
                                                   defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    var count=0

    private val barPaint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context,R.color.colorPopupBackground)
        style = Paint.Style.FILL
    }

    private val linePaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { c ->
            val bx = width/9.0f
            val by = height.toFloat()
            for (b in 0..9) {
                if ((b+1)<=count) c.drawRect(b*bx,0f,(b+1)*bx,by,barPaint)
                c.drawRect(b*bx,0f,(b+1)*bx,by,linePaint)
            }
        }
    }
}
