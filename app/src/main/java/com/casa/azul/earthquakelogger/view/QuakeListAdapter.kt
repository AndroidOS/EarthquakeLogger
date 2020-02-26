package com.casa.azul.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.casa.azul.earthquakelogger.R
import com.casa.azul.earthquakelogger.model.Feature
import com.casa.azul.earthquakelogger.view.ListFragmentDirections
import kotlinx.android.synthetic.main.quake_item.view.*


private const val TAG = "quakeListAdapter"

class QuakeListAdapter(val quakeList: ArrayList<Feature>) :
    RecyclerView.Adapter<QuakeListAdapter.QuakeViewHolder>() {

    fun updateQuakeList(newPhotosList: List<Feature>) {
        quakeList.clear()
        quakeList.addAll(newPhotosList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(
            R.layout.quake_item
            , parent, false
        )
        return QuakeViewHolder(view)
    }

    override fun getItemCount() = quakeList.size

    override fun onBindViewHolder(holder: QuakeViewHolder, position: Int) {


        holder.view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(position)
            Navigation.findNavController(it)
                .navigate(action)
        }

        holder.view.tv_quake_text.text = quakeList[position].properties?.place.toString()
        holder.view.tv_mag_txt.text = quakeList[position].properties?.mag.toString()


    }

    class QuakeViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}