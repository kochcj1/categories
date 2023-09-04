package app.api.categories.view.items

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.api.categories.databinding.ItemBinding
import app.api.categories.model.items.Item
import app.api.categories.model.items.Items
import com.squareup.picasso.Picasso

class ItemsAdapter(
    private val context: Context,
    private val items: Items,
    private val listener: Listener? = null
): RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    interface Listener {
        fun onItemClick(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: Items) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.title.text = item.title
            if (item.photoUrl.isNotEmpty()) {
                Picasso.with(context).load(item.photoUrl).into(binding.imageView)
            }
            binding.card.setOnClickListener {
                listener?.let {
                    listener.onItemClick(item)
                }
            }
        }
    }

}