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

package am.fo.swardapp.species_fragments

import am.fo.swardapp.R
import am.fo.swardapp.data.DescImg
import am.fo.swardapp.data.SpeciesDesc
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_species_photo.*

class SpeciesPhotoFragment : Fragment() {
    private lateinit var image: DescImg

    companion object {
        private const val SPECIES = "species"
        private const val IMAGE_NUM = "image_num"
        fun newInstance(species: String, image_num:Int) = SpeciesPhotoFragment().apply {
            arguments = Bundle(2).apply {
                putString(SPECIES, species)
                putInt(IMAGE_NUM, image_num)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        arguments?.let {
            image = SpeciesDesc.createSpeciesDesc(it.getString(SPECIES)).imgs[it.getInt(IMAGE_NUM)]
        }
        return inflater.inflate(R.layout.fragment_species_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img.setImageResource(image.img)
        caption.setText(image.caption)
        if (image.credit!=-1) {
            photo_credit.text = getString(R.string.credit,getString(image.credit))
        }
    }
}