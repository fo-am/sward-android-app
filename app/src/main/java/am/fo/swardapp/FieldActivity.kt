package am.fo.swardapp

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_field.*

class FieldActivity : SwardActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_field)
        super.onCreate(savedInstanceState)

        val fieldId = intent.getIntExtra("FIELD_ID",0)

        swardViewModel.getField(fieldId).observe(this, Observer { field ->
            field?.let {
                toolbar.setTitle(field.name)
                field_date_sown.setText(field.dateSown)
            }
        })


    }
}