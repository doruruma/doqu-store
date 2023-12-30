package id.andra.doqu_store.presentation.ui.fragment.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentAnnouncementBinding

class AnnouncementFragment : Fragment() {

    private lateinit var viewModel: AnnouncementViewModel
    private lateinit var binding: FragmentAnnouncementBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[AnnouncementViewModel::class.java]
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_announcement,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

}