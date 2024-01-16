package id.andra.doqu_store.presentation.ui.fragment.home

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentHomeBinding
import id.andra.doqu_store.presentation.ui.fragment.home.adapter.HomeViewPagerAdapter
import id.andra.doqu_store.presentation.ui.fragment.home.adapter.ProductRecyclerAdapter
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var pagerAdapter: HomeViewPagerAdapter
    private lateinit var productAdapter: ProductRecyclerAdapter
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        pref = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        editor = pref.edit()
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
        viewModel.onEvent(
            HomeEvent.OnLoad(token = "Bearer ${pref.getString(Var.PREF_TOKEN, "")}")
        )
        observeState()
        return binding.root
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                if (it.products.isNotEmpty()) {
                    productAdapter = ProductRecyclerAdapter(requireActivity(), it.products)
                    binding.productRecyclerView.layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.productRecyclerView.adapter = productAdapter
                }
            }
        }
    }

}