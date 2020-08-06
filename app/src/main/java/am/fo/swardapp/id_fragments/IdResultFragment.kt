package am.fo.swardapp.id_fragments

import am.fo.swardapp.IdentificationActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import am.fo.swardapp.SpeciesActivity
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_id_result.*
import kotlinx.android.synthetic.main.fragment_id_result.view.*

class IdResultFragment : Fragment() {
    private var answer: String? = null
    private val ARG_ANSWER = "answer"
    class Desc(val name: Int, val lat: Int, val img: Int )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answer = it.getString(ARG_ANSWER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        answer?.let {
            val desc: Desc = when (answer) {
                    "grass_cocksfoot" -> Desc(R.string.grass_cocksfoot,R.string.grass_cocksfoot_lat,R.drawable.grass_cocksfoot_plant)
                    "grass_meadowfescue" -> Desc(R.string.grass_meadowfescue,R.string.grass_meadowfescue_lat,R.drawable.grass_meadowfescue_plant)
                    "grass_meadowfoxtail" -> Desc(R.string.grass_meadowfescue,R.string.grass_meadowfescue_lat,R.drawable.grass_meadowfescue_plant)
                    "grass_perennialryegrass" -> Desc(R.string.grass_perennialryegrass,R.string.grass_perennialryegrass_lat,R.drawable.grass_perennialryegrass_plant)
                    "grass_tallfescue" -> Desc(R.string.grass_tallfescue,R.string.grass_tallfescue_lat,R.drawable.grass_tallfescue_plant)
                    "grass_timothy" -> Desc(R.string.grass_timothy,R.string.grass_timothy_lat,R.drawable.grass_timothy_plant)
                    "legume_alsike" -> Desc(R.string.legume_alsike,R.string.legume_alsike_lat,R.drawable.legume_alsike_leaf)
                    "legume_birdsfootrefoil" -> Desc(R.string.legume_birdsfootrefoil,R.string.legume_birdsfootrefoil_lat,R.drawable.legume_birdsfoottrefoil_plant)
                    "legume_lucern" -> Desc(R.string.legume_lucern,R.string.legume_lucern_lat,R.drawable.legume_lucerne_plant)
                    "legume_redclover" -> Desc(R.string.legume_redclover,R.string.legume_redclover_lat,R.drawable.legume_redclover_leaf_1)
                    "legume_sainfoin" -> Desc(R.string.legume_sainfoin,R.string.legume_sainfoin_lat,R.drawable.legume_sainfoin_leaf)
                    "legume_whiteclover" -> Desc(R.string.legume_whiteclover,R.string.legume_whiteclover_lat,R.drawable.legume_whiteclover_leaf)
                    else -> Desc(R.string.legume_whiteclover,R.string.legume_whiteclover_lat,R.drawable.legume_whiteclover_leaf)
            }

            species_name.setText(desc.name)
            species_name_lat.setText(desc.lat)
            species_photo.setImageResource(desc.img)
        }

        species_info.setOnClickListener {}

        id_again.setOnClickListener {
            startActivity(Intent(getActivity(),IdentificationActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(answer: String) =
            IdResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ANSWER, answer)
                }
            }
    }
}