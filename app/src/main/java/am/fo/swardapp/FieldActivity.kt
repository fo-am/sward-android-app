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