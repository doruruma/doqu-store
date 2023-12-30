package id.andra.doqu_store.presentation.ui.fragment.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentHomeBinding
import id.andra.doqu_store.presentation.ui.fragment.home.adapter.HomeViewPagerAdapter
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var pagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        pagerAdapter = HomeViewPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        var currentPage = 0
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    if (currentPage == pagerAdapter.itemCount)
                        currentPage = 0
                    binding.viewPager.setCurrentItem(currentPage++, true)
                }
            }
        }, 1000, 3000L)
        return binding.root
    }

}