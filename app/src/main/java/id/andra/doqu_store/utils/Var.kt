package id.andra.doqu_store.utils

import androidx.navigation.navOptions
import id.andra.doqu_store.R

object Var {
    const val SUBSCRIBED_FLOW: Long = 5000L
    val NAV_OPTIONS = navOptions {
        anim {
            enter = R.anim.fade_in
            exit = R.anim.fade_out
        }
    }
}
