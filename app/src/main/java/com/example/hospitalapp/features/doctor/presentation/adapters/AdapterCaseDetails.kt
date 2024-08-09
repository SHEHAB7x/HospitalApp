import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.databinding.ItemCaseDetailsTabBinding

class AdapterCaseDetails(private val context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<AdapterCaseDetails.Holder>() {
    var list = listOf("Case", "Medical record", "Medical Measurement")
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCaseDetailsTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class Holder(private val binding: ItemCaseDetailsTabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String, position: Int) {
            binding.tabTxt.text = text
            if (position == selectedPosition) {
                binding.tabTxt.background = ContextCompat.getDrawable(context, R.drawable.edit_item_green)
                binding.tabTxt.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                binding.tabTxt.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tabTxt.background = ContextCompat.getDrawable(context, R.drawable.edit_item)
            }

            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                notifyItemChanged(selectedPosition)
                listener.onItemClicked(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}
