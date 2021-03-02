package app.student.movieapp.home.adapter.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(private val listener: OnItemClickListener ): RecyclerView.OnItemTouchListener {

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    private val mListener: OnItemClickListener = listener

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val childView = rv.findChildViewUnder(e.x, e.y)
        if (childView != null) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false
    }
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}