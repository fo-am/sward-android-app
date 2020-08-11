package am.fo.swardapp.species_fragments

import am.fo.swardapp.R
import am.fo.swardapp.SwardFragment
import am.fo.swardapp.data.SpeciesDesc
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SpeciesSelectorFragment : SwardFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species_selector, container, false)
    }

    fun getSpeciesView(species: String): View {
        val id = getResources().getIdentifier(species, "id", context!!.packageName)
        if (id == 0) {
            Log.i("sward", "no widget found for species: " + species)
            error("no widget found for species: " + species)
        }
        return view!!.findViewById<View>(id)
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