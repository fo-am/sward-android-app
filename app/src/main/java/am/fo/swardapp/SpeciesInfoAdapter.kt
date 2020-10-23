package am.fo.swardapp

import am.fo.swardapp.data.SpeciesInfo
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
        val thumbImg: ImageView = itemView.findViewById(R.id.thumb)
        val traitButton: ConstraintLayout = itemView.findViewById(R.id.trait_top)
        val traitInfoContainer: ConstraintLayout = itemView.findViewById(R.id.trait_info)
        val traitTop: ConstraintLayout = itemView.findViewById(R.id.trait_top)
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

        holder.traitTop.setBackgroundResource( when(trait.score) {
            "1" -> R.drawable.button_thumb_down
            "2" -> R.drawable.button_thumb_mid
            "3" -> R.drawable.button_thumb_up
            "y" -> R.drawable.button_thumb_up
            "n" -> R.drawable.button_thumb_down
            else -> R.drawable.button
        })

        holder.traitName.text = res.getString(id)
        holder.traitInfo.text = if (trait.popup==0) {
            if (trait.agree==0 && trait.disagree==0) {
                res.getString(R.string.trait_no_studies)
            } else {
                res.getString(R.string.trait_studies,
                    trait.agree,
                    res.getQuantityString(R.plurals.studies, trait.agree),
                    trait.disagree,
                    res.getQuantityString(R.plurals.studies, trait.disagree))
            }
        } else {
            res.getString(trait.popup)
        }
        holder.traitInfoContainer.visibility = View.GONE


        holder.thumbImg.setImageResource(when (trait.score) {
            "?" -> R.drawable.thumb_q
            "1" -> R.drawable.thumb_down
            "2" -> R.drawable.thumb_mid
            "3" -> R.drawable.thumb_up
            "y" -> R.drawable.thumb_up
            "n" -> R.drawable.thumb_down
            else -> R.drawable.grass_cocksfoot_stemsheath_annotated
        })

        holder.traitButton.setOnClickListener {
            if (holder.traitInfoContainer.visibility == View.VISIBLE) {
                holder.traitInfoContainer.visibility = View.GONE
            } else {
                holder.traitInfoContainer.visibility = View.VISIBLE
            }
        }

    }

    internal fun setSpeciesInfo(info: SpeciesInfo) {
        this.speciesInfo = info
        notifyDataSetChanged()
    }

    override fun getItemCount() = speciesInfo.traits.size
}
