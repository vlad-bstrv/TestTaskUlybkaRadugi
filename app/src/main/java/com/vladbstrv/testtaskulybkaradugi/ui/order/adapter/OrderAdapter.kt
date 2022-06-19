package com.vladbstrv.testtaskulybkaradugi.ui.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladbstrv.testtaskulybkaradugi.databinding.FragmentOrderRecylerviewItemBinding
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

class OrderAdapter(val onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<OrderAdapter.RecyclerItemViewHolder>() {

    private var data: MutableList<DataEntity> = mutableListOf()
    var selectedItem = mutableListOf<DataEntity>()

    fun setData(data: List<DataEntity>) {
        this.data = data as MutableList<DataEntity>
        notifyDataSetChanged()
    }

    fun addItemToBottom() {
        if(selectedItem.isNotEmpty()) {
            data.addAll(selectedItem)
            notifyDataSetChanged()
        }
    }

    fun addItemToTop() {
        if(selectedItem.isNotEmpty()) {
            data.addAll(0, selectedItem)
            notifyDataSetChanged()
        }
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
                    checkBoxItem.setOnClickListener {
                        if (checkBoxItem.isChecked) {
                            selectedItem.add(data)
                        }
                    }
                    checkBoxItem.isChecked = false
                }
            }
        }
    }
}
