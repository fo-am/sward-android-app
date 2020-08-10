package am.fo.swardapp.data

import am.fo.swardapp.R

data class DescImg(val img: Int, val caption: Int)

// single place to get resources for each species
class SpeciesDesc constructor(val name: Int, val lat: Int, val img: Int, val imgs: Array<DescImg> ) {

    companion object {
        // other than the layouts - this is the only place where the species should be listed programatically
        // to make it easier to add species later on (and perhaps to have them stored in the database if required)
        val speciesList = arrayOf("grass_cocksfoot","grass_meadowfescue","grass_meadowfoxtail","grass_perennialryegrass",
            "grass_tallfescue","grass_timothy","herb_chicory","herb_ribwort","herb_sheepsburnet","herb_sheepsparsley",
            "herb_yarrow","legume_alsike","legume_birdsfoottrefoil","legume_lucern","legume_redclover","legume_sainfoin",
            "legume_whiteclover")

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
                    R.drawable.grass_meadowfescue_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_meadowfescue_plant,R.string.grass_meadowfescue_plant_cap),
                        DescImg(R.drawable.grass_meadowfescue_flower,R.string.grass_meadowfescue_flower_cap),
                        DescImg(R.drawable.grass_meadowfescue_stemsheath_annotated,R.string.grass_meadowfescue_stemsheath_annotated_cap)
                    )
                )
                "grass_meadowfoxtail" -> SpeciesDesc(
                    R.string.grass_meadowfoxtail,
                    R.string.grass_meadowfoxtail_lat,
                    R.drawable.grass_meadowfoxtail_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_meadowfoxtail_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_meadowfoxtail_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_meadowfoxtail_stemsheath_annotated,R.string.grass_cocksfoot_stem_cap)
                    )
                )
                "grass_perennialryegrass" -> SpeciesDesc(
                    R.string.grass_perennialryegrass,
                    R.string.grass_perennialryegrass_lat,
                    R.drawable.grass_perennialryegrass_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_perennialryegrass_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_perennialryegrass_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_perennialryegrass_stemsheath_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "grass_tallfescue" -> SpeciesDesc(
                    R.string.grass_tallfescue,
                    R.string.grass_tallfescue_lat,
                    R.drawable.grass_tallfescue_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_tallfescue_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_tallfescue_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_tallfescue_stemsheath_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "grass_timothy" -> SpeciesDesc(
                    R.string.grass_timothy,
                    R.string.grass_timothy_lat,
                    R.drawable.grass_timothy_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_timothy_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_timothy_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_timothy_stemsheath_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "herb_chicory" -> SpeciesDesc(
                    R.string.herb_chicory,
                    R.string.herb_chicory_lat,
                    R.drawable.herb_chicory_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.herb_chicory_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.herb_chicory_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.herb_chicory_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.herb_chicory_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "herb_ribwort" -> SpeciesDesc(
                    R.string.herb_ribwort,
                    R.string.herb_ribwort_lat,
                    R.drawable.herb_ribwort_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.herb_ribwort_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.herb_ribwort_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.herb_ribwort_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.herb_ribwort_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "herb_sheepsburnet" -> SpeciesDesc(
                    R.string.herb_sheepsburnet,
                    R.string.herb_sheepsburnet_lat,
                    R.drawable.herb_sheepsburnet_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.herb_sheepsburnet_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.herb_sheepsburnet_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.herb_sheepsburnet_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.herb_sheepsburnet_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "herb_sheepsparsley" -> SpeciesDesc(
                    R.string.herb_sheepsparsley,
                    R.string.herb_sheepsparsley_lat,
                    R.drawable.herb_sheepsparsley_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.herb_sheepsparsley_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.herb_sheepsparsley_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.herb_sheepsparsley_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.herb_sheepsparsley_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "herb_yarrow" -> SpeciesDesc(
                    R.string.herb_yarrow,
                    R.string.herb_yarrow_lat,
                    R.drawable.herb_yarrow_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.herb_yarrow_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.herb_yarrow_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.herb_yarrow_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.herb_yarrow_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "legume_alsike" -> SpeciesDesc(
                    R.string.legume_alsike,
                    R.string.legume_alsike_lat,
                    R.drawable.legume_alsike_leaf,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_alsike_leaf,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_alsike_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_alsike_seedling,R.string.grass_cocksfoot_stem_cap)
                    )
                )
                "legume_birdsfoottrefoil" -> SpeciesDesc(
                    R.string.legume_birdsfoottrefoil,
                    R.string.legume_birdsfootrefoil_lat,
                    R.drawable.legume_birdsfoottrefoil_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_birdsfoottrefoil_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_birdsfoottrefoil_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_birdsfoottrefoil_leaf,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.legume_birdsfoottrefoil_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "legume_lucern" -> SpeciesDesc(
                    R.string.legume_lucern,
                    R.string.legume_lucern_lat,
                    R.drawable.legume_lucerne_plant,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_lucerne_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_lucerne_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_lucerne_leaf_annotated,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.legume_lucerne_seedling,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "legume_redclover" -> SpeciesDesc(
                    R.string.legume_redclover,
                    R.string.legume_redclover_lat,
                    R.drawable.legume_redclover_leaf_1,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_redclover_leaf_1,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_redclover_leaf_2,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_redclover_flower,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.legume_redclover_seedling_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )
                "legume_sainfoin" -> SpeciesDesc(
                    R.string.legume_sainfoin,
                    R.string.legume_sainfoin_lat,
                    R.drawable.legume_sainfoin_leaf,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_sainfoin_leaf,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_sainfoin_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_sainfoin_seedling_annotated,R.string.grass_cocksfoot_stem_cap)
                    )
                )
                "legume_whiteclover" -> SpeciesDesc(
                    R.string.legume_whiteclover,
                    R.string.legume_whiteclover_lat,
                    R.drawable.legume_whiteclover_leaf,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.legume_whiteclover_leaf,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.legume_whiteclover_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.legume_whiteclover_seedling_annotated,R.string.grass_cocksfoot_stem_cap)
                    )
                )
                else -> SpeciesDesc(
                    R.string.species_not_found,
                    R.string.species_not_found,
                    R.drawable.legume_whiteclover_leaf,
                    arrayOf<DescImg>(
                        DescImg(R.drawable.grass_cocksfoot_plant,R.string.grass_cocksfoot_plant_cap),
                        DescImg(R.drawable.grass_cocksfoot_flower,R.string.grass_cocksfoot_flower_cap),
                        DescImg(R.drawable.grass_cocksfoot_stem,R.string.grass_cocksfoot_stem_cap),
                        DescImg(R.drawable.grass_cocksfoot_stemsheath_annotated,R.string.grass_cocksfoot_stemsheath_annotated_cap)
                    )
                )

        }
    }
}


