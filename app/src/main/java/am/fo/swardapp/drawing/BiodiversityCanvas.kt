/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package am.fo.swardapp.drawing

import am.fo.swardapp.R
import am.fo.swardapp.data.DateWrangler
import am.fo.swardapp.data.SwardViewModel
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class BiodiversityCanvas @JvmOverloads constructor(context: Context,
                                                   attrs: AttributeSet? = null,
                                                   defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private var sownCount = 0
    private var renderList = listOf<SwardViewModel.BiodiversityItem>()
    private val bioScale=40f // pixels per species (max = 17*20)

    private val barPaint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context,R.color.colorPopupBackground)
        style = Paint.Style.FILL
    }

    private val sownBarPaint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context,R.color.colorButtonBorder)
        style = Paint.Style.FILL
    }

    private val linePaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = resources.getDimensionPixelSize(R.dimen.canvas_axis_width).toFloat()
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = resources.getDimensionPixelSize(R.dimen.canvas_text_size).toFloat()
    }

    private val smallTextPaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = resources.getDimensionPixelSize(R.dimen.canvas_small_text_size).toFloat()
    }

    fun addData(surveyAndRecords: List<SwardViewModel.BiodiversityItem>, sown: Int) {
        renderList=surveyAndRecords
        sownCount=sown
        invalidate()
    }

    fun drawBar(c:Canvas,sx:Float,sy:Float,x:Float,y:Float,count:Int,text:String,barPaint:Paint) {
        c.save()
        c.translate((x-30)*sx,(y+190)*sy)
        c.rotate(-70.0f ,0.0f ,0.0f)
        c.drawText(text, 0f, 0f, smallTextPaint)
        c.restore()
        c.drawRect(x*sx,(y-count*bioScale)*sy,(x+35)*sx,y*sy,barPaint)
        c.drawRect(x*sx,(y-count*bioScale)*sy,(x+35)*sx,y*sy,linePaint)
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var count = 0.0f
        canvas?.let { c ->
            // make some normalised display coordinates @ 1000*1000 to fit all screens
            val sx = width/1000f
            val sy = height/1000f

            //c.drawCircle(500f*sx,500f*sy,500f*sx, linePaint)

            c.save()
            c.translate(50*sx,650*sy)
            c.rotate(-90.0f ,0.0f ,0.0f)
            c.drawText(resources.getString(R.string.field_canvas_y_axis), 0f, 0f, textPaint)
            c.restore()

            val xzero = 150.0f
            val yzero = 750.0f

            c.drawLine(xzero*sx,yzero*sy,xzero*sx,0*sy, linePaint)
            c.drawLine((xzero-30)*sx,yzero*sy,1000*sx,yzero*sy, linePaint)

            for (y in 0..(yzero/bioScale).toInt() step 5) {
                val yy = y.toFloat()
                c.drawText(""+y,
                    (xzero-80f)*sx,
                    (yzero+15-yy*bioScale)*sy,
                    textPaint)
                c.drawLine(
                    (xzero-30)*sx,
                    (yzero-yy*bioScale)*sy,
                    xzero*sx,
                    (yzero-yy*bioScale)*sy, linePaint)
            }

            // render the first bar - the sown species
            val x = 20+xzero+count*50.0f
            val y = yzero
            drawBar(c,sx,sy,x,y,sownCount,resources.getString(R.string.field_canvas_sown),sownBarPaint)
            count+=1

            // draw bars for each survey
            renderList.forEach {
                val x = 20+xzero+count*50.0f
                drawBar(c,sx,sy,x,y,it.biodiversity,DateWrangler.dateInternalToView(it.date),barPaint)
                count+=1
            }
        }
    }
}
