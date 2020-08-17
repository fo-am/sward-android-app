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
import kotlinx.android.synthetic.main.activity_identification.*

class SpeciesActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)
        setSupportActionBar(toolbar)

        // doing things programatically seems to be a bad tradeoff
        // between being able to easily redesign things via visual tool
        // and convenience of reducing repetition...

        /*
        SpeciesDesc.speciesList.forEach { species ->
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            button.setCompoundDrawables(
                null, null,
                ContextCompat.getDrawable(
                    this,
                    resources.getIdentifier(species, "drawable", packageName)
                ),
                null
            )

            button.setOnClickListener {
                Intent(this, SpeciesInfoActivity::class.java).let {
                    it.putExtra("SPECIES", species)
                    startActivity(it)
                }
            }
            species_list.addView(button)

            val space = Space(this)
            space.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (30 * resources.getDisplayMetrics().density).toInt()
            )

            species_list.addView(space)
        }

         */
    }
}
