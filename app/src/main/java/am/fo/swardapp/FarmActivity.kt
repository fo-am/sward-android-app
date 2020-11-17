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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        swardViewModel.allFields.observe(this, { fields ->
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

        delete_all.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.delete_all))
            builder.setMessage(getString(R.string.delete_all_body))
            builder.setPositiveButton(getString(R.string.field_delete_yes)) { dialog, which ->
                swardViewModel.deleteAll()
            }
            builder.setNegativeButton(getString(R.string.field_delete_no)) { dialog, which ->
                // don't need to to anything...
            }
            builder.show()
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
