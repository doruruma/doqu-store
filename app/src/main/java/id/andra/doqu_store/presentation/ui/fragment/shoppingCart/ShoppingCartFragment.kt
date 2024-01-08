package id.andra.doqu_store.presentation.ui.fragment.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.FragmentShoppingCartBinding
import id.andra.doqu_store.presentation.ui.activity.main.MainActivity

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var viewModel: ShoppingCartViewModel
    private lateinit var act: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShoppingCartViewModel::class.java]
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false)
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