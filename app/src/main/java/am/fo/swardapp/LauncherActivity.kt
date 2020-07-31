package am.fo.swardapp

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : SwardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_launcher)
        super.onCreate(savedInstanceState)
        setTitle(R.string.title_activity_launcher)

        version_name.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName)

    }
}