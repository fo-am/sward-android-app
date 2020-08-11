package am.fo.swardapp.survey_fragments

import am.fo.swardapp.FieldListAdapter
import am.fo.swardapp.R
import am.fo.swardapp.SwardActivity
import am.fo.swardapp.SwardFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farm.*

class SurveyFieldFragment : SwardFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FieldListAdapter(context!!,this)
        fields_recycler_view.adapter = adapter
        fields_recycler_view.layoutManager = LinearLayoutManager(context!!)

        val sa: SwardActivity = activity as SwardActivity
        sa.swardViewModel.allFields.observe(viewLifecycleOwner, Observer { fields ->
            // Update the cached copy of the words in the adapter.
            fields?.let { adapter.setFields(it) }
        })

    }
}