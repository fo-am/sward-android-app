package am.fo.swardapp.id_fragments

import am.fo.swardapp.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_id_grass_auricle.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class IdGrassAuricleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_grass_auricle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_second.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}