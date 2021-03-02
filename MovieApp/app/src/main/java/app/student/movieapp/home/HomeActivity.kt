package app.student.movieapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import app.student.movieapp.core.BaseActivity
import app.student.movieapp.home.views.fragments.SearchMovieFragment
import app.student.movieapp.splash.SplashActivity

import app.student.movieapp.views.fragments.ListMoviesFragment
import kotlinx.android.synthetic.main.bottom_navigation.bottomNavigationView
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity() {

    private var toolbar: Toolbar?= null
    private var currentInstanceFragment: Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        if(intent.hasExtra(SplashActivity.FLAG_HAS_SESSION)){
            initComponents()
        }
        Thread.sleep(300)
    }
    private fun initComponents(){
        toolbar = findViewById(R.id.toolbar)
        currentInstanceFragment = fragmentCalled((ListMoviesFragment(this)),TAG_LIST_MOVIES_FRAGMENT)
        configureToolbar()
    }


    private fun configureToolbar(){
        toolbar_title.typeface = Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
        toolbar?.logo
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onBackPressed() {
        if(listenerBackPressed!=null){
            val fragment = listenerBackPressed!!.onBackPressed()
            if(fragment.tag == TAG_SEARCH_MOVIES_FRAGMENT){
                supportActionBar?.show()
               bottomNavigationView.selectedItemId = R.id.home
            }else {
                COUNTER_EXIT_THIS_ACTIVITY += 1
                exitApp()
            }
        }else{
            currentInstanceFragment?.activity!!.finish()
            super.onBackPressed()

        }

    }

    private fun exitApp(){
        if(COUNTER_EXIT_THIS_ACTIVITY>=2){
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.seach -> {
                    supportActionBar?.hide()
                    currentInstanceFragment = fragmentCalled(SearchMovieFragment(),TAG_SEARCH_MOVIES_FRAGMENT)
                }
                else -> it.hasSubMenu()
            }
            true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
         const val TAG_LIST_MOVIES_FRAGMENT="listmoviesfragment"
         const val TAG_SEARCH_MOVIES_FRAGMENT="searchmoviesfragment"
         private var COUNTER_EXIT_THIS_ACTIVITY=0
    }

}