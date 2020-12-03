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

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_launcher)
        super.onCreate(savedInstanceState)
        setTitle("")
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.toms_icon)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        version_name.text = packageManager.getPackageInfo(packageName, 0).versionName
        title_image.setImageResource(R.drawable.sward)

        // none of the layout modes quite work, so turn the title image off in landscape for now
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            title_image.visibility = View.GONE
        }

    }
}