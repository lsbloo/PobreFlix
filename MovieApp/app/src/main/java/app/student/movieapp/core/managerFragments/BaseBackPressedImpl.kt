package app.student.movieapp.core.managerFragments

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class BaseBackPressedImpl(fragmentActivity: FragmentActivity): BaseIOFragment {
    private val activity: FragmentActivity = fragmentActivity


    override fun onBackPressed() {
        activity.supportFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}