package am.fo.swardapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.room.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

//////////////////////////////////////////////////////////////////

@Entity(tableName="sward_table")
data class Sward(@PrimaryKey(autoGenerate = true) val id: Int,
                 @ColumnInfo(name = "name") val name: String)

@Dao
interface SwardDao {
    @Query("SELECT * from sward_table ORDER BY name ASC")
    fun getAlphabetizedName(): LiveData<List<Sward>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Sward)

    @Query("DELETE FROM sward_table")
    suspend fun deleteAll()
}

/////////////////////////////////////////////////////////////////////

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        boombutton.setOnClickListener { view ->
            Snackbar.make(view, "YOU PRESSED BOOM", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
