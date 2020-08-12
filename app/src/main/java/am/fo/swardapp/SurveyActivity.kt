package am.fo.swardapp

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_species_info.*

class SurveyActivity : SwardActivity() {

    var startFieldId: Long =-1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        setSupportActionBar(toolbar)
        val host = NavHostFragment.create(R.navigation.survey_nav_graph)
        startFieldId = intent.getLongExtra("FIELD_ID", -1L)

        supportFragmentManager.beginTransaction().replace(R.id.survey_fragment_container, host)
            .setPrimaryNavigationFragment(host).commit()

    }

    override fun onStart() {
        super.onStart()
        if (startFieldId!=-1L) {
            // if field id has been passed in skip the field selector fragment
            val bundle = bundleOf("field_id" to startFieldId)
            findNavController(this,R.id.survey_fragment_container).navigate(R.id.action_surveyFieldFragment_to_surveyHowtoFragment,bundle)
        }
    }
}