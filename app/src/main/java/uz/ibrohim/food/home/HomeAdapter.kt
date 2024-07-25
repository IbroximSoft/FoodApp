package uz.ibrohim.food.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ibrohim.food.R
import uz.ibrohim.food.databinding.HomeItemBinding
import uz.ibrohim.food.home.response.Meal

class HomeAdapter(private val list: List<Meal>, val listener: OnItemClickListener):
    RecyclerView.Adapter<HomeAdapter.Vh>(), Filterable {
    private var filteredList: List<Meal> = list

    inner class Vh(private val itemAdapterItemBinding: HomeItemBinding) :
        RecyclerView.ViewHolder(itemAdapterItemBinding.root) {

        @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
        fun onBind(meal: Meal, position: Int) {
            itemAdapterItemBinding.apply {
                itemName.text = meal.strMeal
                itemDescription.text = meal.strCategory
                if (meal.strMealThumb.isNotEmpty()){
                    Glide.with(itemView.context).load(meal.strMealThumb).into(itemImage)
                }

                itemView.setOnClickListener {
                    listener.onItemClick(meal)
                }

                val to = position % 2
                val bool: Boolean = to == 0
                if (bool){
                    itemRelative.setBackgroundColor(itemView.context.resources.getColor(R.color.item_color))
                }else{
                    itemRelative.setBackgroundColor(itemView.context.resources.getColor(R.color.item_color_two))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(filteredList[position], position)
    }

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Meal>()
                if (constraint.isNullOrEmpty()) {
                    filteredList.addAll(list)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    for (item in list) {
                        if (item.strMeal.lowercase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<Meal>
                Log.d("dy_test",filteredList.size.toString())
                notifyDataSetChanged()
            }
        }
    }
}