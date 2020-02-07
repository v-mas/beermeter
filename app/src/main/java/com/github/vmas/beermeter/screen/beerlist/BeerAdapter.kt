package com.github.vmas.beermeter.screen.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vmas.beermeter.core.model.Beer
import com.github.vmas.beermeter.databinding.ItemBeerBinding

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */
class BeerAdapter(private val onItemSelected: (Beer) -> Unit) : ListAdapter<Beer, BeerAdapter.ViewHolder>(BeerDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent).apply {
            binding.onClick = onItemSelected
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(parent: ViewGroup) :
        BindingViewHolder<ItemBeerBinding>(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {

        fun bind(beer: Beer) {
            binding.beer = beer
            binding.executePendingBindings()
        }
    }

    abstract class BindingViewHolder<T : ViewDataBinding>(val binding: T) :
        RecyclerView.ViewHolder(binding.root)

    object BeerDiffer : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }
    }
}
