package am.fo.swardapp.id_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_id_legume_leaves.*

class IdLegumeLeavesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_legume_leaves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        two.setOnClickListener {
            //findNavController().navigate(R.id.action_IdFirstFragment_to_IdLegumeLeavesFragment)
        }
    }

}