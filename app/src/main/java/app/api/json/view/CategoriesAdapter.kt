package app.api.json.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.api.json.databinding.ItemBinding
import app.api.json.model.Categories
import app.api.json.model.Category

class CategoriesAdapter(
    private val context: Context,
    private val categories: Categories,
    private val listener: Listener? = null
): RecyclerView.Adapter<CategoriesAdapter.ItemViewHolder>() {

    interface Listener {
        fun onItemClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(newCategories: Categories) {
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.title.text = category.title
            binding.imageView.setImageResource(category.image)
            binding.card.setOnClickListener {
                listener?.let {
                    listener.onItemClick(category)
                }
            }
        }
    }

}