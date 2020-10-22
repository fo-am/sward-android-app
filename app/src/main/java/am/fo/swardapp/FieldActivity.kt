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

import am.fo.swardapp.data.DateWrangler
import am.fo.swardapp.data.Field
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*
import java.util.*

class FieldActivity : SwardActivity() {

    private var fieldId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        setSupportActionBar(toolbar)

        fieldId = intent.getLongExtra("FIELD_ID", 0)

        // set up the widgets
        swardViewModel.getBiodiversity(fieldId, 15).observe(this, Observer { surveys ->
            field_canvas_view.addData(surveys)
        })

        val cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                edit_field_date.text = DateWrangler.timeAsView(cal.time)
            }

        edit_field_date.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.soil_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            edit_field_soil_type.adapter = adapter
        }

        swardViewModel.getField(fieldId).observe(this, Observer { field ->
            field?.let {
                toolbar.title = field.name
                edit_field_name.setText(field.name)
                edit_field_date.text = DateWrangler.dateInternalToView(field.dateSown)
                edit_field_soil_type.setSelection(field.soilType)
                edit_field_notes.setText(field.notes)
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

    // all exits go through the gift shop, it seems
    override fun onPause() {
        super.onPause()
        val field = Field(
            edit_field_name.text.toString(),
            DateWrangler.dateViewToInternal(edit_field_date.text.toString()),
            edit_field_soil_type.selectedItemPosition,
            edit_field_notes.text.toString()
        )
        field.fieldId = fieldId
        swardViewModel.updateField(field)
    }

}