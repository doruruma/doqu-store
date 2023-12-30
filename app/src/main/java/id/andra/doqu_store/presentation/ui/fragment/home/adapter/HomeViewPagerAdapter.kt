package id.andra.doqu_store.presentation.ui.fragment.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.andra.doqu_store.presentation.ui.fragment.home.components.ImageFragment

class HomeViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return ImageFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }

}