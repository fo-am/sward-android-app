package am.fo.swardapp

import am.fo.swardapp.data.Field
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class FieldListAdapter internal constructor (
    private var context: Context
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
            Intent(context, FieldActivity::class.java).let {
                it.putExtra("FIELD_ID", current.fieldId)
                context.startActivity(it)
            }
        }
    }

    internal fun setFields(fields: List<Field>) {
        this.fields = fields
        notifyDataSetChanged()
    }

    override fun getItemCount() = fields.size
}