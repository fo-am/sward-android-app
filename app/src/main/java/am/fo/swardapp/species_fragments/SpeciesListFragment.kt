package am.fo.swardapp.species_fragments

import am.fo.swardapp.R
import am.fo.swardapp.SpeciesInfoActivity
import am.fo.swardapp.data.SpeciesDesc
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_species_list.*

class SpeciesListFragment : Fragment() {

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
            val id = getResources().getIdentifier(species, "id", context!!.packageName)
            if (id==0) {
                Log.i("sward","no widget found for species: "+species)
            } else {
                val v: View = view.findViewById(id)
                v.setOnClickListener {
                    Intent(getActivity(), SpeciesInfoActivity::class.java).let {
                        it.putExtra("SPECIES", species)
                        startActivity(it)
                    }
                }
            }
        }
    }
}