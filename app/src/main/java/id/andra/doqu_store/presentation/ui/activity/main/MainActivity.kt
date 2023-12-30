package id.andra.doqu_store.presentation.ui.activity.main

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        state = viewModel.state
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            viewModel.onEvent(MainEvent.OnNavigationChanged(destination.id))
        }
        setEventListener()
    }

    private fun navigate(id: Int) {
        if (navController.currentDestination?.id != id)
            navController.navigate(
                resId = id, args = null, navOptions = Var.NAV_OPTIONS
            )
    }

    private fun setEventListener() {
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
    }

}