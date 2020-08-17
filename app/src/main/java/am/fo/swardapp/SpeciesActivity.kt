package am.fo.swardapp

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_identification.*

class SpeciesActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species)
        setSupportActionBar(toolbar)

        // doing things programatically seems to be a bad tradeoff
        // between being able to easily redesign things via visual tool
        // and convenience of reducing repetition...

        /*
        SpeciesDesc.speciesList.forEach { species ->
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            button.setCompoundDrawables(
                null, null,
                ContextCompat.getDrawable(
                    this,
                    resources.getIdentifier(species, "drawable", packageName)
                ),
                null
            )

            button.setOnClickListener {
                Intent(this, SpeciesInfoActivity::class.java).let {
                    it.putExtra("SPECIES", species)
                    startActivity(it)
                }
            }
            species_list.addView(button)

            val space = Space(this)
            space.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (30 * resources.getDisplayMetrics().density).toInt()
            )

            species_list.addView(space)
        }

         */
    }
}
