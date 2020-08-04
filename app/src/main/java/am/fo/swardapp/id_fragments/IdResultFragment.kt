package am.fo.swardapp.id_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.fo.swardapp.R
import kotlinx.android.synthetic.main.fragment_id_result.*
import kotlinx.android.synthetic.main.fragment_id_result.view.*

class IdResultFragment : Fragment() {
    private var answer: String? = null
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private val ARG_ANSWER = "answer"

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
        val view:View = inflater.inflate(R.layout.fragment_id_result, container, false)

        answer?.let {
            view.some_text.setText(it)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IdResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ANSWER, answer)
                }
            }
    }
}