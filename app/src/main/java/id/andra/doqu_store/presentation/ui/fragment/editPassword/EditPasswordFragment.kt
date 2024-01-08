package id.andra.doqu_store.presentation.ui.fragment.editPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentEditPasswordBinding
import id.andra.doqu_store.presentation.ui.activity.main.MainActivity

class EditPasswordFragment : Fragment() {

    private lateinit var binding: FragmentEditPasswordBinding
    private lateinit var viewModel: EditPasswordViewModel
    private lateinit var act: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[EditPasswordViewModel::class.java]
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_password, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        act = requireActivity() as MainActivity
        setEventListeners()
        return binding.root
    }

    private fun setEventListeners() {
        binding.btnBack.setOnClickListener {
            act.navigateUp()
        }
    }

}