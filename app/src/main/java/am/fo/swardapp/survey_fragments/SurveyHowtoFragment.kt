package am.fo.swardapp.survey_fragments

import am.fo.swardapp.R
import am.fo.swardapp.SwardFragment
import am.fo.swardapp.data.Survey
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_howto.*
import java.text.SimpleDateFormat
import java.util.*

class SurveyHowtoFragment : SwardFragment() {
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

            val c = Calendar.getInstance().getTime()
            val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            swardViewModel.insertSurvey(Survey(df.format(c),fieldId!!)).
                observe(viewLifecycleOwner, Observer { surveyId ->
                    Log.i("sward","made new survey ID is:"+surveyId)
                    val bundle = bundleOf(
                        "field_id" to fieldId,
                        "survey_id" to surveyId
                    )
                findNavController().navigate(R.id.action_surveyHowtoFragment_to_surveyMainFragment, bundle)
            })

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