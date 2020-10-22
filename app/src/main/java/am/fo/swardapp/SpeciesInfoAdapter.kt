package am.fo.swardapp

import am.fo.swardapp.data.SpeciesInfo
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SpeciesInfoAdapter internal constructor (
    private var context: Context,
    var fragment: Fragment? = null
) : RecyclerView.Adapter<SpeciesInfoAdapter.SpeciesInfoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var speciesInfo = SpeciesInfo(arrayOf())

    inner class SpeciesInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val traitName: TextView = itemView.findViewById(R.id.trait_name)
        val traitInfo: TextView = itemView.findViewById(R.id.more_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesInfoAdapter.SpeciesInfoViewHolder {
        val traitView = inflater.inflate(R.layout.trait, parent, false)
        return SpeciesInfoViewHolder(traitView)
    }

    override fun onBindViewHolder(holder: SpeciesInfoViewHolder, position: Int) {
        val trait = speciesInfo.traits[position]
        var res=context.resources
        val name = "trait_${trait.name}"
        val id = res.getIdentifier(name, "string", context.packageName)

        holder.traitName.text = res.getString(id)
        holder.traitInfo.text = if (trait.popup==0) {
            res.getString(R.string.trait_studies,trait.agree,trait.disagree)
        } else {
            res.getString(trait.popup)
        }
    }

    internal fun setSpeciesInfo(info: SpeciesInfo) {
        this.speciesInfo = info
        notifyDataSetChanged()
    }

    override fun getItemCount() = speciesInfo.traits.size
}
