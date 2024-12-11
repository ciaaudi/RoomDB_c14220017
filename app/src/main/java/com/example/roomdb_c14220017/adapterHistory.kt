package com.example.roomdb_c14220017

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_c14220017.database.historyBarang

class adapterHistory(private val historyList: MutableList<historyBarang>) :
    RecyclerView.Adapter<adapterHistory.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val _tvItemBarang: TextView = itemView.findViewById(R.id.tvItemBarang)
        val _tvJumlahBarang: TextView = itemView.findViewById(R.id.tvJumlahBarang)
        val _tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_history_list, parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = historyList[position]

        holder._tvTanggal.text = history.tanggal
        holder._tvItemBarang.text = history.item
        holder._tvJumlahBarang.text = history.jumlah
    }

    fun isiData(history: List<historyBarang>) {
        historyList.clear()
        historyList.addAll(history)
        notifyDataSetChanged()
    }
}