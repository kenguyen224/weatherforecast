package com.example.kenv.weatherforecast.presentation.rcv

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.kenv.weatherforecast.R

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val lastIndex = parent.adapter?.itemCount ?: 0 - 1
        if (parent.getChildAdapterPosition(view) != lastIndex) {
            outRect.bottom = marginBottom
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            parent.children.forEach { view ->
                val params = view.layoutParams as RecyclerView.LayoutParams
                val top = view.bottom + params.bottomMargin + marginTop
                val bottom = top + it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }

    companion object {
        private const val marginBottom = 16
        private const val marginTop = 8
    }
}
