package id.andra.doqu_store.utils

import androidx.navigation.navOptions

object Var {
    const val SUBSCRIBED_FLOW: Long = 5000L
    val NAV_OPTIONS = navOptions {
        anim {
            enter = android.R.animator.fade_in
            exit = android.R.animator.fade_out
        }
    }
}
