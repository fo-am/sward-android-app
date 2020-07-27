package am.fo.swardapp

import am.fo.swardapp.data.Field
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

import kotlinx.android.synthetic.main.activity_new_field.*

class NewFieldActivity : SwardActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_new_field)
        super.onCreate(savedInstanceState)

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

        new_field_soil_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        new_field_date.text = SimpleDateFormat("dd.MM.yyyy",Locale.UK).format(System.currentTimeMillis())
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            new_field_date.text = sdf.format(cal.time)
        }

        new_field_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        new_field_button_save.setOnClickListener {
            if (TextUtils.isEmpty(new_field_name.text)) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                swardViewModel.insertField(Field(
                    new_field_name.text.toString(),
                    new_field_date.text.toString(),
                    new_field_soil_type.selectedItemPosition, // maybe switch to id lookup??
                    new_field_notes.text.toString()))
                setResult(Activity.RESULT_OK)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "fo.am.wordlistsql.REPLY"
    }

}