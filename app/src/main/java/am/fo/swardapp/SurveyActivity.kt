package am.fo.swardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farm.*
import kotlinx.android.synthetic.main.activity_species_info.*
import kotlinx.android.synthetic.main.activity_species_info.toolbar

class SurveyActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        setSupportActionBar(toolbar)

        val host = NavHostFragment.create(R.navigation.survey_nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.survey_fragment_container, host).setPrimaryNavigationFragment(host).commit()



        // if field id is passed in...
/*        val bundle = bundleOf("field_id" to fieldId)
        findNavController().navigate(R.id.action_surveyFieldFragment_to_surveyHowtoFragment,bundle)
*/

    }
}