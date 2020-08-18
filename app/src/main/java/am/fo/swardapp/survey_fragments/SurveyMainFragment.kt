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
            if (sampleNum<9) {
                survey_main.text =
                    String.format(resources.getString(R.string.survey_main_plural), 10 - sampleNum)
            } else {
                survey_main.text =
                    String.format(resources.getString(R.string.survey_main_single), 10 - sampleNum)
            }
            w_image.setImageResource(
                resources.getIdentifier(
                    "example_w_$sampleNum",
                "drawable",
                requireContext().packageName))
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