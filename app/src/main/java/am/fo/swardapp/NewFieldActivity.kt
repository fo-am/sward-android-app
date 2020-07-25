package am.fo.swardapp

import am.fo.swardapp.data.Field
import am.fo.swardapp.data.SwardViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class NewFieldActivity : SwardActivity() {

    private lateinit var editWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_new_field)
        super.onCreate(savedInstanceState)

        editWordView = findViewById(R.id.field_name)

        val spinner: Spinner = findViewById(R.id.field_soil_type)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.soil_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        val button = findViewById<Button>(R.id.field_button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val word = editWordView.text.toString()
                swardViewModel.insert(Field(word,"","",""))
                setResult(Activity.RESULT_OK)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "fo.am.wordlistsql.REPLY"
    }

}