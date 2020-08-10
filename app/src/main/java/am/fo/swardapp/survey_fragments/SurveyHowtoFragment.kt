package am.fo.swardapp.survey_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_howto.*

class SurveyHowtoFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_survey_howto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_survey.setOnClickListener {
            val bundle = bundleOf("field_id" to fieldId)
            findNavController().navigate(R.id.action_surveyHowtoFragment_to_surveyMainFragment, bundle)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fieldId: Long) =
            SurveyHowtoFragment().apply {
                arguments = Bundle().apply {
                    putLong("field_id", fieldId)
                }
            }
    }
}