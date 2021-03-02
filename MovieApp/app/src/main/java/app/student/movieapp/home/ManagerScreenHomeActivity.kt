package app.student.movieapp.home

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Typeface
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

import app.student.movieapp.R


class ManagerScreenHomeActivity(){

    companion object {
        private const val MIN_SIZE_TOOLBAR_TXT=24F
        private const val MAX_SIZE_TOOLBAR_TXT=34F

         fun changeLenghtTitleToolbarByScreenDevice(height: Int, width: Int, titleToolbar: TextView, assets: AssetManager, resources: Resources, toolbar: Toolbar?): Toolbar? {
            if(height>=1600 && width>=900){
                titleToolbar.typeface =
                    Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
                titleToolbar.textSize = MAX_SIZE_TOOLBAR_TXT

                toolbar?.logo
                return toolbar!!
            }else if(height<=1200 && width<=750){

                titleToolbar.typeface =
                    Typeface.createFromAsset(assets, resources.getString(R.string.comic_sans))
                titleToolbar.textSize =MIN_SIZE_TOOLBAR_TXT

                toolbar?.logo
                return toolbar!!
            }
            return null
         }

    }
}