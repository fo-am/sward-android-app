package am.fo.swardapp

import am.fo.swardapp.data.SwardViewModel
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_farm.*

// base class for all activities here
abstract class SwardActivity : AppCompatActivity() {

    protected lateinit var swardViewModel: SwardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        swardViewModel = ViewModelProvider(this).get(SwardViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_normal, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_farm -> {
                startActivity(Intent(this, FarmActivity::class.java))
                true }
            R.id.action_species -> {
                startActivity(Intent(this, SpeciesActivity::class.java))
                true }
            R.id.action_identification -> {
                startActivity(Intent(this, IdentificationActivity::class.java))
                true }
            R.id.action_survey -> {
                //startActivity(Intent(this, FarmActivity::class.java))
                true }
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}