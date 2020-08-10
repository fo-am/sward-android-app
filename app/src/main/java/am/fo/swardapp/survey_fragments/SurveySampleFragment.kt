package am.fo.swardapp.survey_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_sample.*

class SurveySampleFragment : Fragment() {
    private var fieldId: Long? = null
    private var sampleNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fieldId = it.getLong("field_id")
            sampleNum = it.getInt("sample_num")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleNum?.let { sampleNum ->
            cancel.setOnClickListener {
                val bundle = bundleOf(
                    "field_id" to fieldId,
                    "sample_num" to sampleNum
                )
                findNavController().navigate(
                    R.id.action_surveySampleFragment_to_surveyMainFragment,
                    bundle
                )
            }
            save.setOnClickListener {
                if (sampleNum < 9) {
                    // next sample
                    val bundle = bundleOf(
                        "field_id" to fieldId,
                        // strange way to add one to a number :)
                        "sample_num" to sampleNum + 1
                    )
                    findNavController().navigate(
                        R.id.action_surveySampleFragment_to_surveyMainFragment,
                        bundle
                    )
                } else {
                    // we are done...
                    val bundle = bundleOf("field_id" to fieldId)
                    findNavController().navigate(
                        R.id.action_surveySampleFragment_to_surveyEndFragment,
                        bundle
                    )
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fieldId: Long, sampleNum: Int) =
            SurveySampleFragment().apply {
                arguments = Bundle().apply {
                    putLong("field_id", fieldId)
                    putInt("sample_num", sampleNum)
                }
            }
    }
}