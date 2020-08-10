package am.fo.swardapp

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_identification.*
import kotlinx.android.synthetic.main.activity_identification.toolbar
import kotlinx.android.synthetic.main.activity_new_field.*

class IdentificationActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identification)
        setSupportActionBar(toolbar)

        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.id_fragment_container, host).setPrimaryNavigationFragment(host).commit()
        
    }
}