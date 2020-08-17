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

        val adapter = FieldListAdapter(requireContext(),this)
        fields_recycler_view.adapter = adapter
        fields_recycler_view.layoutManager = LinearLayoutManager(requireContext())

        val sa: SwardActivity = activity as SwardActivity
        sa.swardViewModel.allFields.observe(viewLifecycleOwner, Observer { fields ->
            // Update the cached copy of the words in the adapter.
            fields?.let { adapter.setFields(it) }
        })

    }
}