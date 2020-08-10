package am.fo.swardapp.survey_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import am.fo.swardapp.SpeciesInfoActivity
import am.fo.swardapp.SwardActivity
import am.fo.swardapp.data.Record
import am.fo.swardapp.data.Sown
import am.fo.swardapp.data.SpeciesDesc
import android.content.Intent
import android.util.Log
import android.widget.ToggleButton
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_sample.*

class SurveySampleFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_survey_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sa = activity as SwardActivity

        // deactivate all species toggle button
        SpeciesDesc.speciesList.forEach { species ->
            val id = getResources().getIdentifier(species, "id", context!!.packageName)
            if (id==0) {
                Log.i("sward","no widget found for species: "+species)
            } else {
                val v: View = view.findViewById(id)
                v.visibility = View.GONE
            }
        }
        // reactivate the sown ones
        sa.swardViewModel.getSown(fieldId!!).observe(viewLifecycleOwner, Observer { sownList: List<Sown> ->
            sownList.forEach { sown ->
                // find widget for this species
                val id = getResources().getIdentifier(sown.species, "id", context!!.packageName)
                if (id == 0) {
                    Log.i(
                        "sward",
                        "survey_sample: no widget found for sewn species: " + sown.species
                    )
                } else {
                    val v: View = view.findViewById(id)
                    v.visibility = View.VISIBLE
                }
            }
        })

        sampleNum?.let { sampleNum ->
            cancel.setOnClickListener {
                val bundle = bundleOf(
                    "field_id" to fieldId,
                    "survey_id" to surveyId,
                    "sample_num" to sampleNum
                )
                findNavController().navigate(
                    R.id.action_surveySampleFragment_to_surveyMainFragment,
                    bundle
                )
            }
            save.setOnClickListener {
                // check the sown species for this field
                sa.swardViewModel.getSown(fieldId!!).observe(viewLifecycleOwner, Observer { sownList ->
                    sownList.forEach { sown ->
                        // find widget for this species
                        val id = getResources().getIdentifier(sown.species,"id", context!!.packageName)
                        if (id == 0) {
                            Log.i(
                                "sward",
                                "survey_sample: no widget found for sewn species: " + sown.species
                            )
                        } else {
                            val v: ToggleButton = view.findViewById(id)
                            if (v.isChecked) {
                                // add a record for this species
                                sa.swardViewModel.insertRecord(
                                    Record(
                                        surveyId!!,
                                        sown.species,
                                        sampleNum
                                    )
                                )
                            }
                        }
                    }

                    if (sampleNum < 9) {
                        // next sample
                        val bundle = bundleOf(
                            "field_id" to fieldId,
                            "survey_id" to surveyId,
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
                })
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