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

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            about_1.text = Html.fromHtml(getString(R.string.about_text),Html.FROM_HTML_MODE_LEGACY)
            about_2.text = Html.fromHtml(getString(R.string.about_2),Html.FROM_HTML_MODE_LEGACY)
            about_3.text = Html.fromHtml(getString(R.string.about_3),Html.FROM_HTML_MODE_LEGACY)
        } else {
            about_1.text = Html.fromHtml(getString(R.string.about_text))
            about_2.text = Html.fromHtml(getString(R.string.about_2))
            about_3.text = Html.fromHtml(getString(R.string.about_3))
        }

        about_1.movementMethod = LinkMovementMethod.getInstance()
        about_2.movementMethod = LinkMovementMethod.getInstance()
        about_3.movementMethod = LinkMovementMethod.getInstance()

    }

}
