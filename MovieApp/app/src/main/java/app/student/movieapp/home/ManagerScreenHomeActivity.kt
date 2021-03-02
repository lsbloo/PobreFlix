package app.student.movieapp.home

import android.app.Activity
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Typeface
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import app.student.movieapp.HomeActivity
import app.student.movieapp.R
import app.student.movieapp.home.views.fragments.SearchMovieFragment
import app.student.movieapp.views.fragments.ListMoviesFragment


class ManagerScreenHomeActivity() {

    companion object {
        private const val MIN_SIZE_TOOLBAR_TXT = 24F
        private const val MAX_SIZE_TOOLBAR_TXT = 34F

        fun changeLenghtTitleToolbarByScreenDevice(
            height: Int,
            width: Int,
            titleToolbar: TextView,
            assets: AssetManager,
            resources: Resources,
            toolbar: Toolbar?
        ): Toolbar? {
            if (height >= 1600 && width >= 900) {
                titleToolbar.typeface =
                    Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
                titleToolbar.textSize = MAX_SIZE_TOOLBAR_TXT

                toolbar?.logo
                return toolbar!!
            } else if (height <= 1200 && width <= 750) {

                titleToolbar.typeface =
                    Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
                titleToolbar.textSize = MIN_SIZE_TOOLBAR_TXT

                toolbar?.logo
                return toolbar!!
            }
            return null
        }

        fun changeBottomSheetDialogDetailMovie(height: Int,width: Int, activity: Activity): Fragment? {
            if (height >= 1600 && width >= 900) {
                val homeActivity = activity as HomeActivity
                val searchMoviesFragment = SearchMovieFragment(R.layout.fragment_bottom_sheet_content)
                 return homeActivity.fragmentCalled(
                     searchMoviesFragment,
                    HomeActivity.TAG_SEARCH_MOVIES_FRAGMENT
                )
            }else if (height <= 1200 && width <= 750) {
                val homeActivity = activity as HomeActivity
                val searchMoviesFragment = SearchMovieFragment(R.layout.fragment_bottom_sheet_content_min)
                return homeActivity.fragmentCalled(
                    searchMoviesFragment,
                    HomeActivity.TAG_SEARCH_MOVIES_FRAGMENT
                )
            }

            return null
        }


        fun changeLayoutListMoviesByScreenDevice(
            height: Int,
            width: Int,
            activity: Activity
        ): Fragment? {
            if (height >= 1600 && width >= 900) {
                val homeActivity = activity as HomeActivity
                val listMoviesFragment = ListMoviesFragment(
                    activity.baseContext,
                    R.layout.fragment_movie_list,
                    R.layout.movie_item
                )
                return homeActivity.fragmentCalled(
                    listMoviesFragment,
                    HomeActivity.TAG_LIST_MOVIES_FRAGMENT
                )
            } else if (height <= 1200 && width <= 750) {
                val homeActivity = activity as HomeActivity
                val listMoviesFragment = ListMoviesFragment(
                    activity.baseContext,
                    R.layout.fragment_movie_list_min,
                    R.layout.movie_item_min
                )
                return homeActivity.fragmentCalled(
                    listMoviesFragment,
                    HomeActivity.TAG_LIST_MOVIES_FRAGMENT
                )
            }
            return null
        }
    }
}