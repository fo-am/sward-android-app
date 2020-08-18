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

import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
import am.fo.swardapp.species_fragments.SpeciesPhotoFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_species_info.*

class SpeciesInfoActivity : SwardActivity() {
    lateinit var speciesDesc: SpeciesDesc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species_info)
        setSupportActionBar(toolbar)

        speciesDesc = createSpeciesDesc(intent.getStringExtra("SPECIES"))

        species_name.setText(speciesDesc.name)
        species_name_lat.setText(speciesDesc.lat)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        species_images.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (species_images.currentItem == 0) {
            super.onBackPressed()
        } else {
            species_images.currentItem = species_images.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = speciesDesc.imgs.size
        override fun createFragment(position: Int): Fragment = SpeciesPhotoFragment(speciesDesc.imgs[position])
    }
}