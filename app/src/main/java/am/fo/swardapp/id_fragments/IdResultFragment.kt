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

package am.fo.swardapp.id_fragments

import am.fo.swardapp.IdentificationActivity
import am.fo.swardapp.R
import am.fo.swardapp.SpeciesInfoActivity
import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_id_result.*

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
            val i = Intent(getActivity(),IdentificationActivity::class.java)
            // stop going round and round by clearing the previous id activity
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
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