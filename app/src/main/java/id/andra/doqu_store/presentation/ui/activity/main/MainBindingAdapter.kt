package id.andra.doqu_store.presentation.ui.activity.main

import android.view.View
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter

object MainBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:paddingHorizontal")
    fun setPaddingHorizontal(view: View, paddingHorizontal: Float) {
        val oldPadding = view.paddingTop
        ViewCompat.setPaddingRelative(view, paddingHorizontal.toInt(), oldPadding, paddingHorizontal.toInt(), oldPadding)
    }

    @JvmStatic
    @BindingAdapter("app:paddingVertical")
    fun setPaddingVertical(view: View, paddingVertical: Float) {
        val oldPadding = view.paddingStart
        ViewCompat.setPaddingRelative(view, oldPadding, paddingVertical.toInt(), oldPadding, paddingVertical.toInt())
    }
}