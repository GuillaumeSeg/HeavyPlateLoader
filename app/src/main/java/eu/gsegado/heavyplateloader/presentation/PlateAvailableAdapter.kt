package eu.gsegado.heavyplateloader.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.gsegado.heavyplateloader.R
import eu.gsegado.heavyplateloader.databinding.ItemFrontPlateBinding
import eu.gsegado.heavyplateloader.presentation.PlateType.*

class PlateAvailableAdapter(private val vm: WeightLoaderViewModel) : RecyclerView.Adapter<PlateAvailableAdapter.ViewHolder>() {

    private var items: List<Pair<PlateType, Boolean>> = mutableListOf()

    class ViewHolder private constructor(private val binding: ItemFrontPlateBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(plate: Pair<PlateType, Boolean>) {
            if (plate.second) {
                binding.cross.visibility = View.INVISIBLE
                binding.plate.alpha = 1.0f
            } else {
                binding.cross.visibility = View.VISIBLE
                binding.plate.alpha = 0.5f
            }
            when (plate.first) {
                Plate25kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front25kg))
                }
                Plate20kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front20kg))
                }
                Plate15kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front15kg))
                }
                Plate10kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front10kg))
                }
                Plate5kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front5kg))
                }
                Plate2kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front2kg5))
                }
                Plate2kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front2kg))
                }
                Plate1kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front1kg5))
                }
                Plate1kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front1kg))
                }
                Plate0kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.front0kg5))
                }
                else -> {

                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFrontPlateBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            vm.onUserPressedPlate(item.first)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(list: List<Pair<PlateType, Boolean>>) {
        items = list
        notifyDataSetChanged()
    }
}