package am.fo.swardapp.species_fragments

import am.fo.swardapp.FieldActivity
import am.fo.swardapp.R
import am.fo.swardapp.SpeciesInfoActivity
import android.content.Intent
import android.os.Bundle
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

        fun goInfo(species:String) {
            Intent(getActivity(), SpeciesInfoActivity::class.java).let {
                it.putExtra("SPECIES", species)
                startActivity(it)
            }
        }

        grass_cocksfoot.setOnClickListener { goInfo("grass_cocksfoot") }
        grass_meadowfescue.setOnClickListener { goInfo("grass_meadowfescue") }
        grass_meadowfoxtail.setOnClickListener { goInfo("grass_meadowfoxtail") }
        grass_perennialryegrass.setOnClickListener { goInfo("grass_perennialryegrass") }
        grass_tallfescue.setOnClickListener { goInfo("grass_tallfescue") }
        grass_timothy.setOnClickListener { goInfo("grass_timothy") }
        herb_chicory.setOnClickListener { goInfo("herb_chicory") }
        herb_ribwort.setOnClickListener { goInfo("herb_ribwort") }
        herb_sheepsburnet.setOnClickListener { goInfo("herb_sheepsburnet") }
        herb_sheepsparsley.setOnClickListener { goInfo("herb_sheepsparsley") }
        herb_yarrow.setOnClickListener { goInfo("herb_yarrow") }
        legume_alsike.setOnClickListener { goInfo("legume_alsike") }
        legume_birdsfoottrefoil.setOnClickListener { goInfo("legume_birdsfoottrefoil") }
        legume_lucern.setOnClickListener { goInfo("legume_lucern") }
        legume_redclover.setOnClickListener { goInfo("legume_redclover") }
        legume_sainfoin.setOnClickListener { goInfo("legume_sainfoin") }
        legume_whiteclover.setOnClickListener { goInfo("legume_whiteclover") }
    }
}