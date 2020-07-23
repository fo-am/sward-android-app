package am.fo.swardapp

import am.fo.swardapp.data.Field
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FieldListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<FieldListAdapter.FieldViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var fields = emptyList<Field>() // Cached copy of words

    inner class FieldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fieldItemView: Button = itemView.findViewById(R.id.fieldButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        val itemView = inflater.inflate(R.layout.field_item, parent, false)
        return FieldViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        val current = fields[position]
        holder.fieldItemView.text = current.name
    }

    internal fun setWords(fields: List<Field>) {
        this.fields = fields
        notifyDataSetChanged()
    }

    override fun getItemCount() = fields.size
}