package com.example.roomdb_c14220017

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_c14220017.database.daftarBelanja
import com.example.roomdb_c14220017.database.daftarBelanjaDB
import com.example.roomdb_c14220017.database.historyBarang
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class adapterDaftar(private val daftarBelanja: MutableList<daftarBelanja>) :
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val _tvItemBarang: TextView = itemView.findViewById(R.id.tvItemBarang)
        val _tvJumlahBarang: TextView = itemView.findViewById(R.id.tvJumlahBarang)
        val _tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)

        val _btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val _btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val _btnSelesai: Button = itemView.findViewById(R.id.btnSelesai)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val daftar = daftarBelanja[position]

        holder._tvTanggal.text = daftar.tanggal
        holder._tvItemBarang.text = daftar.item
        holder._tvJumlahBarang.text = daftar.jumlah

        holder._btnEdit.setOnClickListener {
            onItemClickCallBack.editData(daftar)
        }

        holder._btnDelete.setOnClickListener {
            onItemClickCallBack.delData(daftar)
        }

        // ketika btnSelesai di klik maka item akan move dari daftarBelanja ke historyBarang, dan dihapus dari daftarBelanja, dia juga hanya akan ditampilkan di HistoryBarang

        holder._btnSelesai.setOnClickListener {
            val context = holder.itemView.context
            CoroutineScope(Dispatchers.IO).launch {
                val db = daftarBelanjaDB.getDatabase(context)
                val history = historyBarang(
                    tanggal = daftar.tanggal ?: "",
                    item = daftar.item ?: "",
                    jumlah = daftar.jumlah ?: ""
                )
                db.funhistoryBarang_DAO().insert(history) // Move data to historyBarang table
                db.fundaftarBelanjaDAO().delete(daftar) // Delete data from daftarBelanja table

                withContext(Dispatchers.Main) {
                    daftarBelanja.removeAt(position) // Remove item from list in adapter
                    notifyItemRemoved(position)
                    val intent = Intent(context, HistoryBarang::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    interface OnItemClickCallBack {
        fun delData(dtBelanja: daftarBelanja)
        fun editData(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }
}