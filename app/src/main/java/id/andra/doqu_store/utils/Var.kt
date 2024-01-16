package id.andra.doqu_store.utils

import androidx.navigation.navOptions
import id.andra.doqu_store.R

object Var {
    const val PREF_TOKEN: String = "pref_token"
    const val PREF_REFRESH_TOKEN: String = "pref_refresh_token"
    const val CLIENT_TIME_OUT: Long = 60L
    const val SUBSCRIBED_FLOW: Long = 5000L
    val NAV_OPTIONS = navOptions {
        anim {
            enter = R.anim.fade_in
            exit = R.anim.fade_out
        }
    }
}
