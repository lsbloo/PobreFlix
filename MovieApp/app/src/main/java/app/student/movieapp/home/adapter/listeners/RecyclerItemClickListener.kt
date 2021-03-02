package app.student.movieapp.home.adapter.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(private val ctx: Context, private val recyclerView: RecyclerView,private val listener: OnItemClickListener ): RecyclerView.OnItemTouchListener {


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    private var mGestureDetector: GestureDetector? = null
    private val mListener: OnItemClickListener = listener

    init {
        mGestureDetector = GestureDetector(ctx,object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent?) {
                val child = recyclerView.findChildViewUnder(e!!.x, e.y)
                if(child!=null){
                    mListener.onItemClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        } )
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val childView = rv.findChildViewUnder(e.x, e.y)
        if (childView != null && mGestureDetector!!.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false
    }
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}