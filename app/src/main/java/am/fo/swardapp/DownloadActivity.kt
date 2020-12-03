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

import am.fo.swardapp.data.Settings
import am.fo.swardapp.data.SwardExport
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_download.*
import kotlinx.android.synthetic.main.activity_farm.toolbar
import java.io.File

class DownloadActivity : SwardActivity() {
    private val newPermissionRequestCode = 1
    private val exportFilename="sward-export.csv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        setSupportActionBar(toolbar)

        swardViewModel.settings.observe(this, {
            it?.let {
                email_address.setText(it.email)
            }
        })

/*        email_address.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(e: Editable?) {
                e?.let {
                    swardViewModel.setSettings(Settings(1, it.toString()))
                }
            }
        })
*/
        send_button.setOnClickListener {
            val email = email_address.text.toString()
            swardViewModel.setSettings(Settings(1, email))
            if (Build.VERSION.SDK_INT >= 23) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if (!hasPermissions(this, permissions)) {
                    ActivityCompat.requestPermissions(this, permissions, newPermissionRequestCode)
                } else {
                    export(email)
                }
            } else {
                export(email)
            }
            finish()
        }

        cancel_button.setOnClickListener {
            finish()
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            data_info.text = Html.fromHtml(getString(R.string.download_data), Html.FROM_HTML_MODE_LEGACY)
        } else {
            data_info.text = Html.fromHtml(getString(R.string.download_data))
        }

        data_info.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun export(email: String) {
        val fileLocation = File(getExternalFilesDir(null)!!.absolutePath, exportFilename)
        val filePath = getExternalFilesDir(null)!!.absolutePath +"/"+exportFilename
        val fileURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", fileLocation)

        swardViewModel.getExportData().observe(this, {
            SwardExport(it, filePath, resources, thunk = {
                val emailIntent = Intent(Intent.ACTION_SEND)

                // get the current email address if it exists
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))

                // are they happy to share the data?
                if (download_consent.isChecked) {
                    emailIntent.putExtra(Intent.EXTRA_CC, arrayOf(getString(R.string.duchy_email)))
                }

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.export_subject))
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.export_body))
                emailIntent.putExtra(Intent.EXTRA_STREAM, fileURI)
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                emailIntent.type = "message/rfc822"
                startActivity(emailIntent)
            }).export()
        })

    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.forEach {
                if (ActivityCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            newPermissionRequestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // get the email address
                    swardViewModel.settings.observe(this, {
                        it?.let {
                            // send when we/if have one
                            export(it.email)
                        }
                    })
                }
            }
            else -> {
                Toast.makeText(this,getString(R.string.permission_fail),Toast.LENGTH_LONG).show()
            }
        }
    }
}