package am.fo.swardapp.survey_fragments

import am.fo.swardapp.R
import am.fo.swardapp.SwardFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_main.*

class SurveyMainFragment : SwardFragment() {
    private var fieldId: Long? = null
    private var surveyId: Long? = null
    private var sampleNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fieldId = it.getLong("field_id")
            surveyId = it.getLong("survey_id")
            sampleNum = it.getInt("sample_num")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleNum?.let { sampleNum ->
            survey_main.text =
                String.format(resources.getString(R.string.survey_main), 10 - sampleNum)
            w_image.setImageResource(getResources().getIdentifier(
                "example_w_"+sampleNum.toString(),
                "drawable",
                context!!.packageName))
            sample_button.text =
                String.format(resources.getString(R.string.survey_sample_button), sampleNum)
            sample_button.setOnClickListener {
                val bundle = bundleOf(
                    "field_id" to fieldId,
                    "survey_id" to surveyId,
                    "sample_num" to sampleNum
                )
                findNavController().navigate(
                    R.id.action_surveyMainFragment_to_surveySampleFragment,
                    bundle
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fieldId: Long, sampleNum: Int) =
            SurveyMainFragment().apply {
                arguments = Bundle().apply {
                    putLong("field_id", fieldId)
                    putInt("sample_num", sampleNum)
                }
            }
    }

}