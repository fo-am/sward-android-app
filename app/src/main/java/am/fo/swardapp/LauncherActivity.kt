package am.fo.swardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LauncherActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_launcher)
        super.onCreate(savedInstanceState)
    }
}