package id.andra.doqu_store.presentation.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentProfileBinding
import id.andra.doqu_store.presentation.ui.activity.main.MainActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var act: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        act = requireActivity() as MainActivity
        setEventListeners()
        return binding.root
    }

    private fun setEventListeners() {
        binding.btnEditProfile.setOnClickListener {
            act.navigate(R.id.editProfileFragment)
        }
        binding.btnEditPassword.setOnClickListener {
            act.navigate(R.id.editPasswordFragment)
        }
    }

}