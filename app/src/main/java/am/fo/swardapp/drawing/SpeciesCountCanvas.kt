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
        strokeWidth = resources.getDimensionPixelSize(R.dimen.canvas_box_width).toFloat()
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
