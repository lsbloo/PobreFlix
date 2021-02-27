package app.student.movieapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import app.student.movieapp.search.views.SearchMovieFragment

import app.student.movieapp.views.fragments.ListMoviesFragment
import kotlinx.android.synthetic.main.bottom_navigation.bottomNavigationView
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    private var toolbar: Toolbar?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        toolbar = findViewById(R.id.toolbar)
        configureToolbar()
        fragmentCalled((ListMoviesFragment(this)))
    }


    private fun configureToolbar(){
        toolbar_title.typeface = Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
        toolbar?.logo
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun fragmentCalled(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHome, fragment).commit()
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home -> fragmentCalled((ListMoviesFragment(this@HomeActivity)))
                R.id.seach -> fragmentCalled(SearchMovieFragment())
                else -> it.hasSubMenu()
            }
            true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }

}