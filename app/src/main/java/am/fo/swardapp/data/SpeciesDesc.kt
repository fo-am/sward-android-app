package am.fo.swardapp.data

import am.fo.swardapp.R

data class DescImg(val img: Int, val caption: Int)

// single place to get resources for each species
class SpeciesDesc constructor(val name: Int, val lat: Int, val img: Int, val imgs: Array<DescImg>? = null ) {

    companion object {
        fun createSpeciesDesc(id: String?) =
            when (id) {
                "grass_cocksfoot" -> SpeciesDesc(
                    R.string.grass_cocksfoot,
                    R.string.grass_cocksfoot_lat,
                    R.drawable.grass_cocksfoot_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_cocksfoot_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_cocksfoot_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_cocksfoot_stem,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.grass_cocksfoot_stemsheath_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "grass_meadowfescue" -> SpeciesDesc(
                    R.string.grass_meadowfescue,
                    R.string.grass_meadowfescue_lat,
                    R.drawable.grass_meadowfescue_plant
                )
                "grass_meadowfoxtail" -> SpeciesDesc(
                    R.string.grass_meadowfescue,
                    R.string.grass_meadowfescue_lat,
                    R.drawable.grass_meadowfescue_plant
                )
                "grass_perennialryegrass" -> SpeciesDesc(
                    R.string.grass_perennialryegrass,
                    R.string.grass_perennialryegrass_lat,
                    R.drawable.grass_perennialryegrass_plant
                )
                "grass_tallfescue" -> SpeciesDesc(
                    R.string.grass_tallfescue,
                    R.string.grass_tallfescue_lat,
                    R.drawable.grass_tallfescue_plant
                )
                "grass_timothy" -> SpeciesDesc(
                    R.string.grass_timothy,
                    R.string.grass_timothy_lat,
                    R.drawable.grass_timothy_plant
                )
                "herb_chicory" -> SpeciesDesc(
                    R.string.herb_chicory,
                    R.string.herb_chicory_lat,
                    R.drawable.herb_chicory_plant
                )
                "herb_ribwort" -> SpeciesDesc(
                    R.string.herb_ribwort,
                    R.string.herb_ribwort_lat,
                    R.drawable.herb_ribwort_plant
                )
                "herb_sheepsburnet" -> SpeciesDesc(
                    R.string.herb_sheepsburnet,
                    R.string.herb_sheepsburnet_lat,
                    R.drawable.herb_sheepsburnet_plant
                )
                "herb_sheepsparsley" -> SpeciesDesc(
                    R.string.herb_sheepsparsley,
                    R.string.herb_sheepsparsley_lat,
                    R.drawable.herb_sheepsparsley_plant
                )
                "herb_yarrow" -> SpeciesDesc(
                    R.string.herb_yarrow,
                    R.string.herb_yarrow_lat,
                    R.drawable.herb_yarrow_plant
                )
                "legume_alsike" -> SpeciesDesc(
                    R.string.legume_alsike,
                    R.string.legume_alsike_lat,
                    R.drawable.legume_alsike_leaf
                )
                "legume_birdsfootrefoil" -> SpeciesDesc(
                    R.string.legume_birdsfoottrefoil,
                    R.string.legume_birdsfootrefoil_lat,
                    R.drawable.legume_birdsfoottrefoil_plant
                )
                "legume_lucern" -> SpeciesDesc(
                    R.string.legume_lucern,
                    R.string.legume_lucern_lat,
                    R.drawable.legume_lucerne_plant
                )
                "legume_redclover" -> SpeciesDesc(
                    R.string.legume_redclover,
                    R.string.legume_redclover_lat,
                    R.drawable.legume_redclover_leaf_1
                )
                "legume_sainfoin" -> SpeciesDesc(
                    R.string.legume_sainfoin,
                    R.string.legume_sainfoin_lat,
                    R.drawable.legume_sainfoin_leaf
                )
                "legume_whiteclover" -> SpeciesDesc(
                    R.string.legume_whiteclover,
                    R.string.legume_whiteclover_lat,
                    R.drawable.legume_whiteclover_leaf
                )
                else -> SpeciesDesc(
                    R.string.species_not_found,
                    R.string.species_not_found,
                    R.drawable.legume_whiteclover_leaf
                )

        }
    }
}


