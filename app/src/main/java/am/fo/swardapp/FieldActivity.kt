package am.fo.swardapp

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*
import kotlinx.android.synthetic.main.activity_field.toolbar
import kotlinx.android.synthetic.main.activity_new_field.*

class FieldActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        setSupportActionBar(toolbar)

        val fieldId = intent.getLongExtra("FIELD_ID",0)
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
        swardViewModel.getFieldWithSurveysAndSpecies(fieldId).observe(this, Observer { fieldAndSurveys ->
            fieldAndSurveys?.let {
                toolbar.setTitle(fieldAndSurveys.field.name)
                Log.i("sward",fieldAndSurveys.field.name)
                field_date_sown.setText(fieldAndSurveys.field.dateSown)
                fieldAndSurveys.surveysAndRecords.forEach { surveyAndRecords ->
                    Log.i("sward", "survey: "+ surveyAndRecords.survey.time)
                    surveyAndRecords.records.forEach { species ->
                        Log.i("sward", "recorded species: "+species.species)
                    }
                }
            }
        })


    }
}