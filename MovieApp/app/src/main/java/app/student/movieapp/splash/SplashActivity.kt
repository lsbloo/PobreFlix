package app.student.movieapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import app.student.movieapp.HomeActivity
import app.student.movieapp.R
import app.student.movieapp.core.BaseActivity

class SplashActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
    }

    override fun onResume() {
        super.onResume()
        initHomeAppIfHasSession()
    }

    private fun initHomeAppIfHasSession(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java)
                .apply { putExtra(FLAG_HAS_SESSION, true) })
        }, INIT_HOME_ACTIVITY_TIME)
    }


    companion object {
        private const val INIT_HOME_ACTIVITY_TIME: Long = 2000
        const val FLAG_HAS_SESSION = "hasSession"
    }
}