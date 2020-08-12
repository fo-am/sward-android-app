package am.fo.swardapp.drawing

import am.fo.swardapp.R
import am.fo.swardapp.data.SurveyAndRecords
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SwardCanvas @JvmOverloads constructor(context: Context,
                              attrs: AttributeSet? = null,
                              defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    data class RenderItem(val date: String, val biodiversity: Int)
    var renderList = mutableListOf<RenderItem>()
    var maxBiodiversity = 0

    private val barPaint = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private val linePaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 40.0f
    }

    fun addData(surveyAndRecords: List<SurveyAndRecords>) {
        renderList.clear()
        maxBiodiversity=0
        surveyAndRecords.forEach {
            val biodiversity = mutableSetOf<String>()
            it.records.forEach {
                biodiversity.add(it.species)
            }
            if (biodiversity.size>maxBiodiversity) maxBiodiversity=biodiversity.size
            renderList.add(RenderItem(it.survey.time,biodiversity.size))
        }
        renderList.sortBy { it.date }
        invalidate()
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var count = 0.0f
        canvas?.let { c ->
            // make some normalised display coordinates @ 1000*1000 to fit all screens
            val sx = width/1000f
            val sy = height/1000f
            val bioScale=40f // pixels per species (max = 17*20)

            //c.drawCircle(500f*sx,500f*sy,500f*sx, linePaint)

            c.save()
            c.translate(50*sx,650*sy)
            c.rotate(-90.0f ,0.0f ,0.0f)
            c.drawText(resources.getString(R.string.field_canvas_y_axis), 0f, 0f, textPaint)
            c.restore()

            val xzero = 150
            val yzero = 750

            c.drawLine(xzero*sx,yzero*sy,xzero*sx,0*sy, linePaint)
            c.drawLine((xzero-30)*sx,yzero*sy,1000*sx,yzero*sy, linePaint)

            for (y in 0..(yzero/bioScale).toInt() step 5) {
                c.drawText(""+y, xzero-92f*sx, (yzero-68)-y*bioScale*sy, textPaint)
                c.drawLine(
                    (xzero-30)*sx,
                    (yzero-77)-y*bioScale*sy,
                    xzero*sx,
                    (yzero-77)-y*bioScale*sy, linePaint)
            }

            renderList.forEach {
                val x = xzero+count*50.0f
                val y = yzero

                c.save()
                c.translate(x-60*sx,(y+230)*sy)
                c.rotate(-70.0f ,0.0f ,0.0f)
                c.drawText(it.date, 0f, 0f, textPaint)
                c.restore()

                c.drawRect(x*sx,(y-it.biodiversity*bioScale)*sy,(x+35)*sx,y*sy,barPaint)
                c.drawRect(x*sx,(y-it.biodiversity*bioScale)*sy,(x+35)*sx,y*sy,linePaint)

                count+=1
            }
        }
    }
}
