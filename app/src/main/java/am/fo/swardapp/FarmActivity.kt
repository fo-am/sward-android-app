package am.fo.swardapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farm.*

class FarmActivity : SwardActivity() {

    private val newFieldActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm)
        setSupportActionBar(toolbar)

        val adapter = FieldListAdapter(this)
        fields_recycler_view.adapter = adapter
        fields_recycler_view.layoutManager = LinearLayoutManager(this)

        swardViewModel.allFields.observe(this, Observer { fields ->
            // Update the cached copy of the words in the adapter.
            fields?.let { adapter.setFields(it) }
        })

        new_field_button.setOnClickListener {
            val intent = Intent(this@FarmActivity, NewFieldActivity::class.java)
            startActivityForResult(intent, newFieldActivityRequestCode)
        }

        download_data_button.setOnClickListener {
            startActivity(Intent(this, DownloadActivity::class.java))
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
