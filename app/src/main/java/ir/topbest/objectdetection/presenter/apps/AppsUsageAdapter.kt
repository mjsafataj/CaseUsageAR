package ir.topbest.objectdetection.presenter.apps

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.topbest.objectdetection.R
import ir.topbest.objectdetection.databinding.AppUsageItemBinding
import ir.topbest.objectdetection.domain.models.AppUsageModel

class AppsUsageAdapter : RecyclerView.Adapter<AppsUsageAdapter.MyViewHolder>() {

    var items : List<AppUsageModel> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.app_usage_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.binding.app=items[position]
    }

    override fun getItemCount() = items.size

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = AppUsageItemBinding.bind(view)
    }
}



