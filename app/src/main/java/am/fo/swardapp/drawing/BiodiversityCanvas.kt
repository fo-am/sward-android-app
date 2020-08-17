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

    var renderList = listOf<SwardViewModel.BiodiversityItem>()
    var maxBiodiversity = 0

    private val barPaint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context,R.color.colorPopupBackground)
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

    fun addData(surveyAndRecords: List<SwardViewModel.BiodiversityItem>) {
        renderList=surveyAndRecords
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
