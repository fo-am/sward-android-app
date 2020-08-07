package am.fo.swardapp.id_fragments

import am.fo.swardapp.IdentificationActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import am.fo.swardapp.SpeciesActivity
import am.fo.swardapp.SpeciesInfoActivity
import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
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
            val desc: SpeciesDesc = createSpeciesDesc(answer)
            species_name.setText(desc.name)
            species_name_lat.setText(desc.lat)
            species_photo.setImageResource(desc.img)
        }

        species_info.setOnClickListener {
            Intent(getActivity(), SpeciesInfoActivity::class.java).let {
                it.putExtra("SPECIES", answer)
                startActivity(it)
            }
        }

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