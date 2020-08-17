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

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*

class FieldActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        setSupportActionBar(toolbar)

        val fieldId = intent.getLongExtra("FIELD_ID",0)

        swardViewModel.getBiodiversity(fieldId,15).observe (this, Observer { surveys ->
            field_canvas_view.addData(surveys)
        })

        swardViewModel.getField(fieldId).observe(this, Observer { field ->
            field?.let {
                toolbar.setTitle(field.name)
                Log.i("sward",field.name)
                field_date_sown.setText(field.dateSown)
            }
        })

        field_survey.setOnClickListener {
            Intent(this, SurveyActivity::class.java).let {
                it.putExtra("FIELD_ID", fieldId)
                this.startActivity(it)
            }
        }

        field_results.setOnClickListener {
            Intent(this, FieldDetailActivity::class.java).let {
                it.putExtra("FIELD_ID", fieldId)
                this.startActivity(it)
            }
        }

        field_delete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.field_delete))
            builder.setMessage(getString(R.string.field_delete_body))
            builder.setPositiveButton(getString(R.string.field_delete_yes)) { dialog, which ->
                swardViewModel.deleteField(fieldId)
                startActivity(Intent(this, FarmActivity::class.java))
            }
            builder.setNegativeButton(getString(R.string.field_delete_no)) { dialog, which ->
                // don't need to to anything...
            }
            builder.show()
        }


    }
}