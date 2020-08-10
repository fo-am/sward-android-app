package am.fo.swardapp

import am.fo.swardapp.data.Field
import am.fo.swardapp.survey_fragments.SurveyHowtoFragment
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class FieldListAdapter internal constructor (
    private var context: Context,
    var fragment: Fragment? = null
) : RecyclerView.Adapter<FieldListAdapter.FieldViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var fields = emptyList<Field>() // Cached copy of fields

    inner class FieldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fieldItemView: Button = itemView.findViewById(R.id.field_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        val itemView = inflater.inflate(R.layout.field_item, parent, false)
        return FieldViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        val current = fields[position]
        holder.fieldItemView.text = current.name
        holder.fieldItemView.setOnClickListener {
            if (fragment==null) {
                Intent(context, FieldActivity::class.java).let {
                    it.putExtra("FIELD_ID", current.fieldId)
                    context.startActivity(it)
                }
            } else {
                val bundle = bundleOf("field_id" to current.fieldId)
                fragment!!.findNavController().navigate(R.id.action_surveyFieldFragment_to_surveyHowtoFragment,bundle)
            }

        }
    }

    internal fun setFields(fields: List<Field>) {
        this.fields = fields
        notifyDataSetChanged()
    }

    override fun getItemCount() = fields.size
}