/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package am.fo.swardapp.survey_fragments

import am.fo.swardapp.R
import am.fo.swardapp.SwardFragment
import am.fo.swardapp.data.Record
import am.fo.swardapp.data.Sown
import am.fo.swardapp.species_fragments.SpeciesSelectorFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_survey_sample.*

class SurveySampleFragment : SwardFragment() {
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

        val sf =
            childFragmentManager.findFragmentById(R.id.selector_fragment) as SpeciesSelectorFragment
        sf.hideAll()

        // note: we probably don't need to continually get the sown species for this field as it won't change
        // (although this is super safe - as we could conceivably make it editable in the lifecylce of these fragments
        // - but perhaps store in activity instead?)

        // reactivate the sown ones
        swardViewModel.getSown(fieldId!!)
            .observe(viewLifecycleOwner, { sownList: List<Sown> ->
                sownList.forEach { sown ->
                    sf.show(sown.species)
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
                swardViewModel.getSown(fieldId!!).observe(viewLifecycleOwner, { sownList ->
                    sownList.forEach { sown ->
                        val v = sf.getSpeciesView(sown.species) as ToggleButton
                        if (v.isChecked) {
                            // add a record for this species
                            Log.i("sward", "adding record for survey id: $surveyId")
                            swardViewModel.insertRecord(
                                Record(
                                    surveyId!!,
                                    sown.species,
                                    sampleNum
                                )
                            )
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