package am.fo.swardapp.data

import am.fo.swardapp.R

data class Trait(val name:String, val score:String="?", val agree:Int=0, val disagree:Int=0, val popup:Int=0)

data class SpeciesInfo(val traits:Array<Trait>) {

    companion object {
        val traitsList = arrayOf("protein","mineral","digestibility","persistence","yield_cut","yield_grazed",
                                 "tolerance","anthelminthic","waterlogging","fertility","drought","frost","bloat")

        fun createSpeciesInfo(id:String?) =
            when (id) {
                "legume_alsike" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",1,1,R.string.popup_alsike_protein),
                    Trait("mineral"),
                    Trait("digestibility","2",2,1,R.string.popup_alsike_digestibility),
                    Trait("persistence"),
                    Trait("yield_cut",    "1",2,0,0),
                    Trait("yield_grazed"),
                    Trait("tolerance"),
                    Trait("anthelminthic"),
                    Trait("waterlogging"),
                    Trait("fertility",    "y",0,0, R.string.popup_alsike_fertility),
                    Trait("drought",      "n",3,1,0),
                    Trait("frost",        "y",2,1,0),
                    Trait("bloat",        "n",2,0,0)))

                "legume_birdsfoottrefoil" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",0,0, R.string.popup_birdsfoottrefoil_protein),
                    Trait("mineral",      "y",0,0, R.string.popup_birdsfoottrefoil_mineral),
                    Trait("digestibility","2",5,3, R.string.popup_birdsfoottrefoil_digestibility),
                    Trait("persistence",  "2",0,0, R.string.popup_birdsfoottrefoil_persistence),
                    Trait("yield_cut",    "1",6,1,0),
                    Trait("yield_grazed", "1",3,0),
                    Trait("tolerance",    "n",1,0),
                    Trait("anthelminthic","y",4,1),
                    Trait("waterlogging", "y",2,0),
                    Trait("fertility",    "y",0,0, R.string.popup_birdsfoottrefoil_fertility),
                    Trait("drought",      "y",5,1),
                    Trait("frost"),
                    Trait("bloat",        "y",4,0)))

                "legume_lucerne" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",0,0, R.string.popup_lucerne_protein),
                    Trait("mineral"),
                    Trait("digestibility","2",6,3, R.string.popup_lucerne_digestibility),
                    Trait("persistence",  "2",4,1, R.string.popup_lucerne_persistence),
                    Trait("yield_cut",    "3",6,2),
                    Trait("yield_grazed", "2",2,1, R.string.popup_lucerne_yield_grazed),
                    Trait("tolerance",    "n",3,1),
                    Trait("anthelminthic","n",3,1),
                    Trait("waterlogging", "n",3,0),
                    Trait("fertility",    "n",0,0, R.string.popup_lucerne_fertility),
                    Trait("drought",      "y",9,0),
                    Trait("frost",        "n",1,0),
                    Trait("bloat",        "n",5,0)))

                "legume_redclover" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",7,2, R.string.popup_redclover_protein),
                    Trait("mineral",      "y", 0,0, R.string.popup_redclover_mineral),
                    Trait("digestibility","3",4,2),
                    Trait("persistence",  "1",4,0),
                    Trait("yield_cut",    "3",8,1),
                    Trait("yield_grazed", "3",3,0),
                    Trait("tolerance",    "n",1,0),
                    Trait("anthelminthic","?",1,0),
                    Trait("waterlogging", "n",1,0),
                    Trait("fertility",    "n",0,0, R.string.popup_redclover_fertility),
                    Trait("drought",      "n",5,2),
                    Trait("frost",        "?",0,0),
                    Trait("bloat",        "n",4,0)))

                "legume_sainfoin" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "1",5,2),
                    Trait("mineral"),
                    Trait("digestibility","2",0,0, R.string.popup_sainfoin_digestibility),
                    Trait("persistence",  "1",1,0),
                    Trait("yield_cut",    "1",1,0),
                    Trait("yield_grazed", "?",0,0),
                    Trait("tolerance",    "n",1,0),
                    Trait("anthelminthic","y",4,0),
                    Trait("waterlogging", "n",1,0),
                    Trait("fertility",    "n",0,0, R.string.popup_sainfoin_fertility),
                    Trait("drought",      "y",2,0),
                    Trait("frost",        "y",3,0),
                    Trait("bloat",        "y",3,0)))

                "legume_whiteclover" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",5,2),
                    Trait("mineral",      "y",0,0,R.string.popup_whiteclover_mineral),
                    Trait("digestibility","3",4,2),
                    Trait("persistence",  "3",3,0),
                    Trait("yield_cut",    "2",6,3),
                    Trait("yield_grazed", "3",2,1),
                    Trait("tolerance",    "y",5,0),
                    Trait("anthelminthic","n",2,1),
                    Trait("fertility",    "y",0,0, R.string.popup_whiteclover_fertility),
                    Trait("drought",      "n",7,0),
                    Trait("waterlogging", "y",4,0),
                    Trait("frost",        "?",0,0),
                    Trait("bloat",        "n",5,0)))

                "herb_chicory" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",3,1),
                    Trait("mineral",      "y",0,0,R.string.popup_chicory_mineral),
                    Trait("digestibility","3",4,1),
                    Trait("persistence",  "1",6,0),
                    Trait("yield_cut",    "2",1,R.string.popup_chicory_yield_cut),
                    Trait("yield_grazed", "3",1,0),
                    Trait("tolerance",    "n",3,0),
                    Trait("anthelminthic","y",9,1),
                    Trait("waterlogging", "?",0,0),
                    Trait("fertility"),
                    Trait("drought",      "y",6,0),
                    Trait("frost",        "y",1,0)))

                // plantain in the doc
                "herb_ribwort" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",2,0),
                    Trait("mineral",      "y",0,0,R.string.popup_plantain_mineral),
                    Trait("digestibility","2",2,1,R.string.popup_plantain_digestibility),
                    Trait("persistence",  "1",1,0),
                    Trait("yield_cut",    "3",1,1),
                    Trait("yield_grazed", "2",1,0,R.string.popup_plantain_yield_grazed),
                    Trait("tolerance",    "n",3,0),
                    Trait("anthelminthic","y",1,0),
                    Trait("waterlogging", "y",1,0),
                    Trait("fertility","y",0,0,R.string.popup_plantain_fertility),
                    Trait("drought",      "y",2,1),
                    Trait("frost",        "?",0,0)))

                "herb_sheepsburnet" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",0,0,R.string.popup_sheepsburnet_protein),
                    Trait("mineral"),
                    Trait("digestibility","?",0,0),
                    Trait("persistence",  "1",1,0),
                    Trait("yield_cut",    "1",1,0),
                    Trait("yield_grazed"),
                    Trait("tolerance",    "n",0,0,R.string.popup_tolerance),
                    Trait("anthelminthic","?",0,0),
                    Trait("waterlogging", "?",0,0),
                    Trait("fertility"),
                    Trait("drought",      "y",3,0),
                    Trait("frost",        "?",0,0)))

                "herb_sheepsparsley" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "?",0,0),
                    Trait("mineral"),
                    Trait("digestibility","?",0,0),
                    Trait("persistence",  "?",0,0),
                    Trait("yield_cut"),
                    Trait("yield_grazed"),
                    Trait("tolerance",    "n",0,0,R.string.popup_tolerance),
                    Trait("anthelminthic","?",0,0),
                    Trait("waterlogging", "?",0,0),
                    Trait("fertility"),
                    Trait("drought",      "?",0,0),
                    Trait("frost",        "?",0,0)))

                "herb_yarrow" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "?",0,0),
                    Trait("mineral",      "y",0,0, R.string.popup_yarrow_mineral),
                    Trait("digestibility","?",0,0),
                    Trait("persistence",  "?",0,0),
                    Trait("yield_cut"),
                    Trait("yield_grazed"),
                    Trait("tolerance",    "n",0,0,R.string.popup_tolerance),
                    Trait("anthelminthic","y",3,0),
                    Trait("waterlogging", "n",1,0),
                    Trait("fertility"),
                    Trait("drought",      "?",0,0),
                    Trait("frost",        "?",0,0)))

                "grass_cocksfoot" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",3,0),
                    Trait("mineral",      "y",0,0, R.string.popup_cocksfoot_mineral),
                    Trait("digestibility","3", 0,0,R.string.popup_cocksfoot_digestibility),
                    Trait("persistence",  "3",6,1),
                    Trait("yield_cut",    "2",1,0,R.string.popup_cocksfoot_yield_cut),
                    Trait("yield_grazed", "3",1,0),
                    Trait("tolerance",    "n",3,1),
                    Trait("anthelminthic","n",1,0),
                    Trait("waterlogging", "n",1,0),
                    Trait("fertility",    "n",0,0,R.string.popup_cocksfoot_fertility),
                    Trait("drought",      "y",5,3),
                    Trait("frost",        "?",0,0)))

                "grass_meadowfescue" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",1,0),
                    Trait("mineral"),
                    Trait("digestibility","2", 0,0,R.string.popup_meadowfescue_digestibility),
                    Trait("persistence",  "1",1,0),
                    Trait("yield_cut",    "2",1,1,R.string.popup_meadowfescue_yield_cut),
                    Trait("yield_grazed", "1",3,0),
                    Trait("tolerance",    "n",2,1),
                    Trait("anthelminthic","n",0,0,R.string.popup_anthelminthic),
                    Trait("waterlogging", "?",0,0),
                    Trait("fertility",    "?"),
                    Trait("drought",      "n",4,0),
                    Trait("frost",        "y",1,0)))

                "grass_meadowfoxtail" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",0,0,R.string.popup_meadowfoxtail_protein),
                    Trait("mineral"),
                    Trait("digestibility","2", 0,0,R.string.popup_meadowfoxtail_digestibility),
                    Trait("persistence",  "?",0,0),
                    Trait("yield_cut",    "1",1,0),
                    Trait("yield_grazed", "?",0,0),
                    Trait("tolerance",    "n",1,0),
                    Trait("anthelminthic","n",0,0,R.string.popup_anthelminthic),
                    Trait("waterlogging", "y",1,0),
                    Trait("fertility",    "?"),
                    Trait("drought",      "n",1,0),
                    Trait("frost",        "?",0,0)))

                "grass_perennialryegrass" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "3",5,0),
                    Trait("mineral"),
                    Trait("digestibility","3", 9,1),
                    Trait("persistence",  "3",2,2),
                    Trait("yield_cut",    "2",1,0,R.string.popup_ryegrass_yield_cut),
                    Trait("yield_grazed", "3",3,0),
                    Trait("tolerance",    "y",3,0),
                    Trait("anthelminthic","n",3,0),
                    Trait("waterlogging", "?",0,0),
                    Trait("fertility",    "n",0,0,R.string.popup_ryegrass_fertility),
                    Trait("drought",      "n",8,0),
                    Trait("frost",        "n",2,0)))

                "grass_tallfescue" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",4,1,R.string.popup_tallfescue_protein),
                    Trait("mineral"),
                    Trait("digestibility","2", 0,0, R.string.popup_tallfescue_digestibility),
                    Trait("persistence",  "3",7,1),
                    Trait("yield_cut",    "3",1,0),
                    Trait("yield_grazed", "3",4,0),
                    Trait("tolerance",    "y",2,0),
                    Trait("anthelminthic","n",0,0, R.string.popup_anthelminthic),
                    Trait("waterlogging", "y",1,0),
                    Trait("fertility"),
                    Trait("drought",      "y",6,2),
                    Trait("frost",        "y",1,0)))

                "grass_timothy" -> SpeciesInfo(arrayOf(
                    Trait("protein",      "2",0,0, R.string.popup_timothy_protein),
                    Trait("mineral"),
                    Trait("digestibility","2", 0,0, R.string.popup_timothy_digestibility),
                    Trait("persistence",  "2",1,0,R.string.popup_timothy_persistence),
                    Trait("yield_cut",    "2",2,0,R.string.popup_timothy_yield_cut),
                    Trait("yield_grazed", "2",1,0,R.string.popup_timothy_yield_grazed),
                    Trait("tolerance",    "n",1,0),
                    Trait("anthelminthic","n",0,0, R.string.popup_anthelminthic),
                    Trait("waterlogging", "y",3,0),
                    Trait("fertility",    "y",0,0,R.string.popup_timothy_fertility),
                    Trait("drought",      "y",1,1),
                    Trait("frost",        "y",3,0)))

                else -> SpeciesInfo(emptyArray())

            }
    }
}