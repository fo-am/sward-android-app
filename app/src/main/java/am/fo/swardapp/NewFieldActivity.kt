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
import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.drawing.SpeciesSelector
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_field.*
import java.util.*

class NewFieldActivity : SwardActivity() {

    val speciesSelector = SpeciesSelector()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_field)
        setSupportActionBar(toolbar)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.soil_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            new_field_soil_type.adapter = adapter
        }

       /* new_field_soil_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        } */

        new_field_date.text = DateWrangler.nowAsView()
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            new_field_date.text = DateWrangler.timeAsView(cal.time)
        }

        new_field_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        speciesSelector.build(SpeciesDesc.speciesList,this, species_list)

        new_field_button_save.setOnClickListener {
            if (TextUtils.isEmpty(new_field_name.text)) {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_field_not_saved,
                    Toast.LENGTH_LONG).show()
            } else {

                val field = Field(
                    new_field_name.text.toString(),
                    DateWrangler.dateViewToInternal(new_field_date.text.toString()),
                    new_field_soil_type.selectedItemPosition, // maybe switch to id lookup??
                    new_field_notes.text.toString()
                )

                val sown = mutableListOf<String>()
                // scan over species widgets, inserting them for this field where required
                SpeciesDesc.speciesList.forEach { species ->
                    speciesSelector.buttons[species]?.let {
                        if (it.isChecked) {
                            sown.add(species)
                        }
                    }
                }

                if (sown.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        R.string.unsown_field_not_saved,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    swardViewModel.insertFieldWithSpeciesSown(field, sown)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "fo.am.wordlistsql.REPLY"
    }

}