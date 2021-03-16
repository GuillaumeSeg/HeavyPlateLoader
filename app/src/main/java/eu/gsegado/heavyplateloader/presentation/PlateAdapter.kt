package eu.gsegado.heavyplateloader.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.gsegado.heavyplateloader.R
import eu.gsegado.heavyplateloader.databinding.ItemPlateBinding

class PlateAdapter : RecyclerView.Adapter<PlateAdapter.ViewHolder>() {

    private var listPlates: List<PlateType> = emptyList()
    private var numberOfPlates = 0

    class ViewHolder private constructor(private val binding: ItemPlateBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(plate: PlateType) {
            binding.stopDisc.visibility = View.INVISIBLE
            binding.plate.visibility = View.VISIBLE
            binding.plateLabel.visibility = View.VISIBLE
            binding.plateLabel.setTextColor(Color.WHITE)

            when (plate) {
                PlateType.StopDisc -> {
                    binding.plate.visibility = View.INVISIBLE
                    binding.stopDisc.visibility = View.VISIBLE
                    binding.plateLabel.visibility = View.GONE
                }
                PlateType.Plate0kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight0kg5))
                    binding.plateLabel.setTextColor(Color.BLACK)
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_0_5)
                }
                PlateType.Plate1kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight1kg))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_1)
                }
                PlateType.Plate1kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight1kg5))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_1_5)
                }
                PlateType.Plate2kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight2kg))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_2)
                }
                PlateType.Plate2kg5 -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight2kg5))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_2_5)
                }
                PlateType.Plate5kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight5kg))
                    binding.plateLabel.setTextColor(Color.BLACK)
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_5)
                }
                PlateType.Plate10kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight10kg))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_10)
                }
                PlateType.Plate15kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight15kg))
                    binding.plateLabel.setTextColor(Color.BLACK)
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_15)
                }
                PlateType.Plate20kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight20kg))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_20)
                }
                PlateType.Plate25kg -> {
                    binding.plate.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.weight25kg))
                    binding.plateLabel.text = binding.root.context.getString(R.string.plate_value_25)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPlateBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPlates[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listPlates.size
    }

    fun setItems(items: List<PlateType>) {
        listPlates = items
        notifyDataSetChanged()
    }

    fun setNumberOfPlates(nb: Int) {
        numberOfPlates = nb
        notifyDataSetChanged()
    }

}