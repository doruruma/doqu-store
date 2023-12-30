package id.andra.doqu_store.presentation.ui.fragment.home.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.andra.doqu_store.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentImageBinding.inflate(inflater, container, false)
        Glide.with(this)
            .load("https://picsum.photos/400/300")
            .into(binding.imgCarousel)
        return binding.root
    }

}