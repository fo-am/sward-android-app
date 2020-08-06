package am.fo.swardapp

import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
import am.fo.swardapp.species_fragments.SpeciesPhotoFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_identification.*
import kotlinx.android.synthetic.main.activity_species_info.*
import kotlinx.android.synthetic.main.activity_species_info.toolbar

class SpeciesInfoActivity : SwardActivity() {
    lateinit var speciesDesc: SpeciesDesc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species_info)
        setSupportActionBar(toolbar)

        speciesDesc = createSpeciesDesc(intent.getStringExtra("SPECIES"))

        species_name.setText(speciesDesc.name)
        species_name_lat.setText(speciesDesc.lat)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        species_images.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (species_images.currentItem == 0) {
            super.onBackPressed()
        } else {
            species_images.currentItem = species_images.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = speciesDesc.imgs.size
        override fun createFragment(position: Int): Fragment = SpeciesPhotoFragment(speciesDesc.imgs.get(position))
    }
}