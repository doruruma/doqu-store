package id.andra.doqu_store.presentation.ui.fragment.home.adapter

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.andra.doqu_store.databinding.ItemProductBinding
import id.andra.doqu_store.domain.model.Product

class ProductRecyclerAdapter(
    private val act: Activity,
    private val items: List<Product> = listOf(),
) : RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(act.layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = items[position]
        Glide.with(act)
            .load(item.icon)
            .into(binding.imgLogo)
        binding.txtName.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

}