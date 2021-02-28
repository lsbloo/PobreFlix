package app.student.movieapp.core

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import app.student.movieapp.R
import app.student.movieapp.core.managerFragments.BaseIOFragment

open class BaseActivity: AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager

    var listenerBackPressed: BaseIOFragment? = null

    fun setOnBackPressedListener(listener: BaseIOFragment){
        listenerBackPressed  = listener
    }

    fun getBaseFragmentManager() = fragmentManager

     fun fragmentCalled(fragment: Fragment, tag: String): Boolean {
        getBaseFragmentManager().beginTransaction().apply {
            add(R.id.fragmentHome, fragment, tag).commit()
            addToBackStack(null)
        }
        return true
    }
}