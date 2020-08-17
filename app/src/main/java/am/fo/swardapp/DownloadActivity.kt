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

import am.fo.swardapp.data.SwardExport
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
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


        send_button.setOnClickListener {

            if (Build.VERSION.SDK_INT >= 23) {
                val PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if (!hasPermissions(this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(this, PERMISSIONS, newPermissionRequestCode)
                } else {
                    export()
                }
            } else {
                export()
            }
        }
    }

    fun export() {
        val fileLocation = File(getExternalFilesDir(null)!!.getAbsolutePath(), exportFilename)
        val filePath = getExternalFilesDir(null)!!.getAbsolutePath()+"/"+exportFilename
        val fileURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", fileLocation)

        swardViewModel.getExportData().observe(this, Observer {
            SwardExport(it, filePath, thunk = {
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.export_subject))
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.export_body))
                    putExtra(Intent.EXTRA_STREAM, fileURI)
                }
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                emailIntent.type = "text/plain"
                startActivity(emailIntent)
            }).export()
        })

    }

    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
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
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    export()
                }
            }
            else -> {
                Toast.makeText(this,getString(R.string.permission_fail),Toast.LENGTH_LONG).show()
            }
        }
    }
}