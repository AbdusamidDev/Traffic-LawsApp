package developer.abdusamid.trafficlawsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.databinding.SpinnerItemBinding

class CustomSpinner(private val list: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val itemView: View =
            p1 ?: LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)

        val binding = SpinnerItemBinding.bind(itemView)

        binding.txt.text = list[p0]

        return itemView
    }

}