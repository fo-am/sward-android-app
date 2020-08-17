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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_field_detail.*

class FieldDetailActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_detail)
        setSupportActionBar(toolbar)

        val fieldId = intent.getLongExtra("FIELD_ID",0)

        val adapter = SpeciesDetailAdapter(this)
        species_recycler_view.adapter = adapter
        species_recycler_view.layoutManager = LinearLayoutManager(this)

        swardViewModel.getSpeciesAndSurveyCounts(fieldId).observe(this, Observer { species ->
            species?.let { adapter.setFields(it) }
        })
    }
}