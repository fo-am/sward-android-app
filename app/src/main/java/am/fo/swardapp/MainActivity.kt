package am.fo.swardapp

import am.fo.swardapp.data.Field
import am.fo.swardapp.data.SwardViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : SwardActivity() {

    private val newFieldActivityRequestCode = 1
    private val fieldActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        val adapter = FieldListAdapter(this)
        fields_recycler_view.adapter = adapter
        fields_recycler_view.layoutManager = LinearLayoutManager(this)

        swardViewModel.allFields.observe(this, Observer { fields ->
            // Update the cached copy of the words in the adapter.
            fields?.let { adapter.setFields(it) }
        })

        new_field_button.setOnClickListener {
            val intent = Intent(this@MainActivity, NewFieldActivity::class.java)
            startActivityForResult(intent, newFieldActivityRequestCode)
        }

    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newFieldActivityRequestCode && resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                applicationContext,
                R.string.empty_field_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
