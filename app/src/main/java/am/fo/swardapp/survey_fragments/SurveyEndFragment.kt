package am.fo.swardapp.survey_fragments

import am.fo.swardapp.FieldActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_survey_end.*

class SurveyEndFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var fieldId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fieldId = it.getLong("field_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey_end, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_results.setOnClickListener {
            Intent(context, FieldActivity::class.java).let {
                it.putExtra("FIELD_ID", fieldId)
                context!!.startActivity(it)
            }
        }
    }

}