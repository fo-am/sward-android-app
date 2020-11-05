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

package am.fo.swardapp

import am.fo.swardapp.data.DateWrangler
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
import am.fo.swardapp.data.SwardViewModel.SpeciesSurveyCount
import am.fo.swardapp.drawing.SpeciesCountCanvas
import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SpeciesDetailAdapter internal constructor (
    private var context: Context,
    var fragment: Fragment? = null
) : RecyclerView.Adapter<SpeciesDetailAdapter.SpeciesListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var speciesSurveyCounts = emptyMap<String,List<SpeciesSurveyCount>>()

    inner class SpeciesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val speciesName: TextView = itemView.findViewById(R.id.species_name)
        val surveyList: LinearLayout = itemView.findViewById(R.id.survey_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesListViewHolder {
        val itemView = inflater.inflate(R.layout.species_item, parent, false)
        return SpeciesListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpeciesListViewHolder, position: Int) {
        val species = speciesSurveyCounts.keys.toList()[position]
        val surveys: List<SpeciesSurveyCount>? = speciesSurveyCounts[species]
        val density = Resources.getSystem().displayMetrics.density

        holder.speciesName.setText(createSpeciesDesc(species).name)
        holder.surveyList.removeAllViews()

        surveys?.let { surveys ->
            for (speciesSurveyCount in surveys) {
                val surveyLayout = LinearLayout(context)
                surveyLayout.orientation = LinearLayout.HORIZONTAL
                surveyLayout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                surveyLayout.gravity = Gravity.CENTER_HORIZONTAL

                val surveyName = TextView(context)
                surveyName.text = DateWrangler.dateInternalToView(speciesSurveyCount.survey.time)
                surveyLayout.addView(surveyName)

                val s = Space(context)
                s.layoutParams = LinearLayout.LayoutParams(
                    (10 * density).toInt(),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                surveyLayout.addView(s)

                val surveyCount = SpeciesCountCanvas(context)
                surveyCount.layoutParams = LinearLayout.LayoutParams(
                    (15 * 9 * density).toInt(),
                    (15 * density).toInt()
                )
                surveyCount.count = speciesSurveyCount.count
                surveyLayout.addView(surveyCount)

                holder.surveyList.addView(surveyLayout)

            }
        }
    }

    internal fun setFields(surveyCounts: Map<String,List<SpeciesSurveyCount>>) {
        this.speciesSurveyCounts = surveyCounts
        notifyDataSetChanged()
    }

    override fun getItemCount() = speciesSurveyCounts.size
}