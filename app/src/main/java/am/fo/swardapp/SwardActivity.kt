/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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

    lateinit var swardViewModel: SwardViewModel

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
                startActivity(Intent(this, SurveyActivity::class.java))
                true }
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}