package am.fo.swardapp

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*

class FieldActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_field)
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val field_id = intent.getIntExtra("FIELD_ID",0)

        swardViewModel.getField(field_id).observe(this, Observer { field ->
            field?.let {
                toolbar.setTitle(field.name)
                field_date_sown.setText(field.dateSown)
            }
        })


    }
}