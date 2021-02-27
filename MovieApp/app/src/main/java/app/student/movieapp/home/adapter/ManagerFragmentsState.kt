package app.student.movieapp.home.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ManagerFragmentsState(@NonNull fragmentManager: FragmentManager,
                            @NonNull lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {

    private val listFragmentsHome: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return listFragmentsHome.size
    }

    fun addFragment(fragment: Fragment){
        listFragmentsHome.add(fragment)
    }
    override fun createFragment(position: Int): Fragment {
        return listFragmentsHome[position]
    }
}