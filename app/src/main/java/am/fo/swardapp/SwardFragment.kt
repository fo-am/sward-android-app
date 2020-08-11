package am.fo.swardapp

import am.fo.swardapp.data.SwardViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class SwardFragment : Fragment() {

    lateinit var swardViewModel: SwardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swardViewModel = ViewModelProvider(this).get(SwardViewModel::class.java)
    }
}