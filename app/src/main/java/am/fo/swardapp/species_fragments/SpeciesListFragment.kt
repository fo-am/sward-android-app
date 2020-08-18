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
import am.fo.swardapp.SpeciesInfoActivity
import am.fo.swardapp.SwardFragment
import am.fo.swardapp.data.SpeciesDesc
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SpeciesListFragment : SwardFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SpeciesDesc.speciesList.forEach { species ->
            val id = resources.getIdentifier(species, "id", requireContext().packageName)
            if (id==0) {
                Log.i("sward", "no widget found for species: $species")
            } else {
                val v: View = view.findViewById(id)
                v.setOnClickListener {
                    Intent(activity, SpeciesInfoActivity::class.java).let {
                        it.putExtra("SPECIES", species)
                        startActivity(it)
                    }
                }
            }
        }
    }
}