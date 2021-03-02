package app.student.movieapp.core.managerFragments

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import app.student.movieapp.HomeActivity

class BaseBackPressedImpl(fragmentActivity: FragmentActivity,fragment: Fragment): BaseIOFragment {
    private val activity: FragmentActivity = fragmentActivity
    private val fragment: Fragment = fragment


    override fun onBackPressed(): Fragment {
        Log.i("TEST", "Found fragment: " + activity.supportFragmentManager.backStackEntryCount)
       // activity.supportFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if(fragment.tag == HomeActivity.TAG_SEARCH_MOVIES_FRAGMENT){
            activity.supportFragmentManager.beginTransaction().remove(fragment).commit()
            activity.supportFragmentManager.popBackStack()
            return fragment
        }
        return Fragment()
    }

}