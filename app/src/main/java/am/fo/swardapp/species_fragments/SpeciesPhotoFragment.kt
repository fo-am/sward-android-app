package am.fo.swardapp.species_fragments

import am.fo.swardapp.R
import am.fo.swardapp.data.DescImg
import am.fo.swardapp.data.SpeciesDesc
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_species_photo.*

class SpeciesPhotoFragment(val image: DescImg) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img.setImageResource(image.img)
        caption.setText(image.caption)
    }
}