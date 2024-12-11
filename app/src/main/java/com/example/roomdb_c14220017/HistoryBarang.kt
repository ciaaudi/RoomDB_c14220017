package com.example.roomdb_c14220017

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_c14220017.database.daftarBelanjaDB
import com.example.roomdb_c14220017.database.historyBarang
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class HistoryBarang : AppCompatActivity() {
    private lateinit var DB: daftarBelanjaDB
    private lateinit var adapterHistory: adapterHistory

    private var arHistory: MutableList<historyBarang> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_barang)

        adapterHistory = adapterHistory(arHistory)
        val _rvHistory = findViewById<RecyclerView>(R.id.rvHistory)

        _rvHistory.layoutManager = LinearLayoutManager(this)
        _rvHistory.adapter = adapterHistory

        DB = daftarBelanjaDB.getDatabase(this)

        fetchHistoryData()
    }

    private fun fetchHistoryData() {
        CoroutineScope(Dispatchers.Main).async {
            val historyList = withContext(Dispatchers.IO) {
                DB.funhistoryBarang_DAO().selectAll()
            }
            adapterHistory.isiData(historyList)
        }
    }
}