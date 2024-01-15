package id.andra.doqu_store.presentation.bindingAdapter

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import id.andra.doqu_store.R

object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter("togglePasswordSrc")
    fun setSrc(view: ImageView, isShowPassword: Boolean) {
        val resId = if (isShowPassword)
            R.drawable.ic_eye
        else
            R.drawable.ic_eye_slash
        view.setImageDrawable(ContextCompat.getDrawable(view.context, resId))
    }

    @JvmStatic
    @BindingAdapter("passwordVisibility")
    fun setInputType(view: EditText, isShowPassword: Boolean) {
        if (isShowPassword)
            view.transformationMethod = HideReturnsTransformationMethod.getInstance()
        else
            view.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}