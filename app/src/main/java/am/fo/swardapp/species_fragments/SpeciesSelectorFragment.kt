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
import am.fo.swardapp.data.SpeciesDesc
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SpeciesSelectorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species_selector, container, false)
    }

    fun getSpeciesView(species: String): View {
        val id = resources.getIdentifier(species, "id", requireContext().packageName)
        if (id == 0) {
            Log.i("sward", "no widget found for species: $species")
            error("no widget found for species: $species")
        }
        return requireView().findViewById<View>(id)
    }

    fun hideAll() {
        // deactivate all species toggle button
        SpeciesDesc.speciesList.forEach { species ->
            getSpeciesView(species).visibility = View.GONE
        }
    }

    fun show(species: String) {
        getSpeciesView(species).visibility = View.VISIBLE
    }

}