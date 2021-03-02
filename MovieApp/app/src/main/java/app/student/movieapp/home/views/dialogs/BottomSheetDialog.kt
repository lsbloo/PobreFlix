package app.student.movieapp.home.views.dialogs

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import app.student.movieapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment(){

    private var bottomSheetPeekHeight: Int?=null

    override fun getTheme() = R.style.Theme_MaterialComponents_Light_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_content,container,false)

        val bottomSheet = view.findViewById<ConstraintLayout>(R.id.constraintFather)
        bottomSheetPeekHeight = resources.getDimensionPixelSize(R.dimen.bottom_sheet_default_peek_height)
        return view
    }

    override fun onResume() {
        super.onResume()
        setUpBottomSheet()
    }

    private fun setUpBottomSheet(){
        val bottomSheetBehavior = BottomSheetBehavior.from(view?.parent as View)
        bottomSheetBehavior.peekHeight = bottomSheetPeekHeight!!
    }
}