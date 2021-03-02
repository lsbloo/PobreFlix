package app.student.movieapp.home.adapter.listeners
import android.view.View
interface SearchMovieListenerClickAdapter {

    fun onItemClick(position: Int,  view: View?)
    fun onItemLongClick(position: Int,view: View?)
}