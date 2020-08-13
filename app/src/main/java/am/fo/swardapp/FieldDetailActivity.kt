package am.fo.swardapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_field_detail.*

class FieldDetailActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_detail)
        setSupportActionBar(toolbar)

        val fieldId = intent.getLongExtra("FIELD_ID",0)

        val adapter = SpeciesDetailAdapter(this)
        species_recycler_view.adapter = adapter
        species_recycler_view.layoutManager = LinearLayoutManager(this)

        swardViewModel.getSpeciesAndSurveyCounts(fieldId).observe(this, Observer { species ->
            species?.let { adapter.setFields(it) }
        })
    }
}