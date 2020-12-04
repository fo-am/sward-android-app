package am.fo.swardapp.drawing

import am.fo.swardapp.R
import am.fo.swardapp.data.Sown
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ToggleButton
import androidx.core.content.ContextCompat

class SpeciesSelector {

    var buttons = mutableMapOf<String,ToggleButton>()

    fun dpToPx(dp:Int):Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
    }

    fun buildFromSown(sownList: List<Sown>, ctx: Context, parent: GridLayout) {
        build(sownList.map{ it.species }.toTypedArray(),ctx,parent,false)
    }

    fun build(speciesList: Array<String>, ctx: Context, parent: GridLayout, addUnknown:Boolean=true) {
        parent.setAlignmentMode(GridLayout.ALIGN_BOUNDS)
        // default for 17 species
        var cols = 6
        // threshold number of cols on species count
        if (speciesList.size<=15) cols = 5
        if (speciesList.size<=12) cols = 4
        if (speciesList.size<=9) cols = 3

        parent.setColumnCount(cols)
        parent.setRowCount((speciesList.size / cols)+1)

        Log.i("sward","row count: "+speciesList.size / cols)

        speciesList.forEachIndexed { i,species ->
            val button = ToggleButton(ctx)
            buttons.put(species,button)
            button.text = ctx.resources.getString(ctx.resources.getIdentifier(species,"string", ctx.packageName))
            button.textSize = 12.0f

            val drawableId = ctx.resources.getIdentifier(species+"_button", "drawable", ctx.packageName)
            if (drawableId!=0) {
                val drawable = ContextCompat.getDrawable(ctx, drawableId)
                drawable?.setBounds(0, 0, dpToPx(80), dpToPx(80))
                button.setCompoundDrawables(null, drawable,null,null)
            }
            var pos=i
            if (addUnknown) pos+=1
            val layoutParams = GridLayout.LayoutParams()
            val col: Int = pos%cols
            val row: Int = pos/cols

            layoutParams.height = dpToPx(140)
            layoutParams.width = dpToPx(90)
            layoutParams.columnSpec = GridLayout.spec(col)
            layoutParams.rowSpec = GridLayout.spec(row)
            layoutParams.setGravity(Gravity.LEFT or Gravity.TOP)

            parent.addView(button, layoutParams)

        }

        // add the unknown button at the end
        if (addUnknown) {
            val button = ToggleButton(ctx)
    //            buttons.put("unknown", button)
            button.text = ctx.resources.getString(R.string.unknown_species)
            button.textSize = 12.0f

            button.setOnClickListener {
                buttons.forEach {
                    it.value.isChecked = button.isChecked
                }
            }

            val drawable = ContextCompat.getDrawable(ctx, R.drawable.thumb_q)
            drawable?.setBounds(0, 0, dpToPx(80), dpToPx(80))
            button.setCompoundDrawables(null, drawable, null, null)

            val layoutParams = GridLayout.LayoutParams()
            val col: Int = 0
            val row: Int = 0

            layoutParams.height = dpToPx(140)
            layoutParams.width = dpToPx(90)
            layoutParams.columnSpec = GridLayout.spec(col)
            layoutParams.rowSpec = GridLayout.spec(row)
            layoutParams.setGravity(Gravity.LEFT or Gravity.TOP)

            parent.addView(button, layoutParams)
        }
    }
}