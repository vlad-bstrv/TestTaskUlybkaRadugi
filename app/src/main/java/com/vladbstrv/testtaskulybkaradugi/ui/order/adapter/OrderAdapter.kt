package com.vladbstrv.testtaskulybkaradugi.ui.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladbstrv.testtaskulybkaradugi.databinding.FragmentOrderRecylerviewItemBinding
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

class OrderAdapter :
    RecyclerView.Adapter<OrderAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataEntity> = arrayListOf()

    fun setData(data: List<DataEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding = FragmentOrderRecylerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerItemViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                FragmentOrderRecylerviewItemBinding.bind(itemView).apply {
                    textViewIdPos.text = data.idPos.toString()
                    textViewIdRecord.text = data.idRecord.toString()
                    textViewNomRoute.text = data.nomRoute.toString()
                    textViewNomNakl.text = data.nomNakl
                    textViewNomZak.text = data.nomZak.toString()
                }
            }
        }
    }
}
