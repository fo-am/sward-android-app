package am.fo.swardapp

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*

class FieldActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        setSupportActionBar(toolbar)

        val fieldId = intent.getLongExtra("FIELD_ID",0)

        swardViewModel.getSurveysAndRecords(fieldId).observe (this, Observer { surveys ->
            field_canvas_view.addData(surveys)
        })

/*
        swardViewModel.getFieldAndSownSpecies(fieldId).observe(this, Observer { fieldAndSown ->
            fieldAndSown?.let {
                toolbar.setTitle(fieldAndSown.field.name)
                field_date_sown.setText(fieldAndSown.field.dateSown)
                fieldAndSown.sownSpecies.forEach { sownSpecies ->
                    Log.i("sward",sownSpecies.species)
                }
            }
        })
    */
        swardViewModel.getField(fieldId).observe(this, Observer { field ->
            field?.let {
                toolbar.setTitle(field.name)
                Log.i("sward",field.name)
                field_date_sown.setText(field.dateSown)
            }
        })


    }
}