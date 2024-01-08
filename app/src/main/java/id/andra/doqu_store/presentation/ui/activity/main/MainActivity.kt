package id.andra.doqu_store.presentation.ui.activity.main

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.ActivityMainBinding
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.flow.StateFlow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var state: StateFlow<MainState>
    private var navBroadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        state = viewModel.state
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initNavController()
        setEventListeners()
    }

    override fun onResume() {
        super.onResume()
        initBroadcastReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterBroadcastReceiver()
    }

    private fun initBroadcastReceiver() {
        if (navBroadcastReceiver == null) {
            navBroadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    if (intent != null)
                        navigate(intent.getIntExtra("navId", 0))
                }
            }
            registerBroadcastReceiver(navBroadcastReceiver as BroadcastReceiver)
        }
    }

    private fun unregisterBroadcastReceiver() {
        if (navBroadcastReceiver != null)
            unregisterReceiver(navBroadcastReceiver)
        navBroadcastReceiver = null
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun registerBroadcastReceiver(
        broadcastReceiver: BroadcastReceiver,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            registerReceiver(
                broadcastReceiver,
                IntentFilter(Var.NAV_INTENT_FILTER),
                RECEIVER_NOT_EXPORTED
            )
        else
            registerReceiver(
                broadcastReceiver,
                IntentFilter(Var.NAV_INTENT_FILTER)
            )
    }

    fun navigate(id: Int) {
        if (navController.currentDestination?.id != id) {
            navController.navigate(
                resId = id, args = null, navOptions = Var.NAV_OPTIONS
            )
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            viewModel.onEvent(MainEvent.OnNavigationChanged(destination.id))
            viewModel.onEvent(MainEvent.OnNavIdChanged(destination.id))
        }
    }

    private fun setEventListeners() {
        binding.btnCart.setOnClickListener {
            navigate(R.id.cartFragment)
        }
        binding.btnHome.setOnClickListener {
            navigate(R.id.homeFragment)
        }
        binding.btnHistory.setOnClickListener {
            navigate(R.id.historyFragment)
        }
        binding.btnAnnouncement.setOnClickListener {
            navigate(R.id.announcementFragment)
        }
        binding.btnNotification.setOnClickListener {
            navigate(R.id.notificationFragment)
        }
        binding.btnProfile.setOnClickListener {
            navigate(R.id.profileFragment)
        }
    }

}