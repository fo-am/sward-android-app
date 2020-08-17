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