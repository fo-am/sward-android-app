package am.fo.swardapp.drawing

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

    private val barPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 40.0f
    }

    fun addData(surveyAndRecords: List<SurveyAndRecords>) {
        renderList.clear()
        surveyAndRecords.forEach {
            val biodiversity = mutableSetOf<String>()
            it.records.forEach {
                biodiversity.add(it.species)
            }
            renderList.add(RenderItem(it.survey.time,biodiversity.size))
        }
        renderList.sortBy { it.date }
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var count = 0.0f
        canvas?.let { canvas ->
            renderList.forEach {
                val x = count*40.0f
                val y = 400.0f

                canvas.save()
                canvas.translate(x,y)
                canvas.rotate(90.0f ,0.0f ,0.0f)
                canvas.drawText(it.date, 0f, 0f, textPaint)
                canvas.restore()

                canvas.drawRect(x,(y-50)-(it.biodiversity*20),x+35,y-50,barPaint)

                count+=1
            }
        }
    }
}
