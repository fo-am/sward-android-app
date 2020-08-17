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

package am.fo.swardapp

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_species_info.*

class SurveyActivity : SwardActivity() {

    var startFieldId: Long =-1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        setSupportActionBar(toolbar)
        val host = NavHostFragment.create(R.navigation.survey_nav_graph)
        startFieldId = intent.getLongExtra("FIELD_ID", -1L)

        supportFragmentManager.beginTransaction().replace(R.id.survey_fragment_container, host)
            .setPrimaryNavigationFragment(host).commit()

    }

    override fun onStart() {
        super.onStart()
        if (startFieldId!=-1L) {
            // if field id has been passed in skip the field selector fragment
            val bundle = bundleOf("field_id" to startFieldId)
            findNavController(this,R.id.survey_fragment_container).navigate(R.id.action_surveyFieldFragment_to_surveyHowtoFragment,bundle)
        }
    }
}