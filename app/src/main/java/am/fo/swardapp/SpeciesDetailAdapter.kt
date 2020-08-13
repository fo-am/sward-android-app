package am.fo.swardapp

import am.fo.swardapp.data.SwardViewModel.SpeciesSurveyCount
import android.content.Context
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
        val surveys: List<SpeciesSurveyCount> = speciesSurveyCounts[species]!!
        holder.speciesName.text = species

        for (speciesSurveyCount in surveys) {
            val surveyLayout = LinearLayout(context)
            surveyLayout.setOrientation(LinearLayout.HORIZONTAL);
            surveyLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val surveyName = TextView(context)
            surveyName.text=speciesSurveyCount.survey.time
            surveyLayout.addView(surveyName)

            val s = Space(context)
            s.layoutParams = LinearLayout.LayoutParams(
                10,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            surveyLayout.addView(s)

            val surveyCount = TextView(context)
            surveyCount.text=speciesSurveyCount.count.toString()
            surveyLayout.addView(surveyCount)

            holder.surveyList.addView(surveyLayout)

        }
    }

    internal fun setFields(surveyCounts: Map<String,List<SpeciesSurveyCount>>) {
        this.speciesSurveyCounts = surveyCounts
        notifyDataSetChanged()
    }

    override fun getItemCount() = speciesSurveyCounts.size
}