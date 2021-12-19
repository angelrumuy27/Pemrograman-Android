package id.ac.ukdw.final_71190506

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.DataSnapshot
import java.util.*


class SearchActivity : AppCompatActivity() {
    lateinit var searchText: TextView
    private lateinit var listPddk : ListView
    private lateinit var ref : DatabaseReference
    private lateinit var pddkList: MutableList<Penduduk>
    private lateinit var pddkListSearch: MutableList<Penduduk>
    private lateinit var btnBack : Button
    private lateinit var btnGo : Button
//    var firestore: FirebaseFirestore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

//        firestore = FirebaseFirestore.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("penduduk")

        listPddk = findViewById(R.id.lv_pddk)
        searchText = findViewById(R.id.searchView)
        btnBack = findViewById(R.id.btnBackSearch)
        val profileName=intent.getStringExtra("Username")


        pddkList = mutableListOf()
        pddkListSearch = mutableListOf()


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    pddkList.clear()
                    for(h in p0.children){
                        val penduduk = h.getValue(Penduduk::class.java)
                        if(penduduk != null){
                            pddkList.add(penduduk)
                        }
                    }
                    val adapter = PendudukAdapter(this@SearchActivity, R.layout.item_penduduk, pddkList)
                    listPddk.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        btnBack.setOnClickListener{
            Intent(this@SearchActivity, MenuActivity::class.java).also {
                it.putExtra("Username", profileName)
                startActivity(it)
            }
        }

        //search data
        searchText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists()){
                            pddkListSearch.clear()
                            for(h in p0.children){
                                val pathNama = h.child("nama").getValue().toString()
                                    .lowercase(Locale.getDefault())
                                val pathNik = h.child("nik").getValue().toString()
                                    .lowercase(Locale.getDefault())
                                val pathAlamat = h.child("alamat").getValue().toString()
                                    .lowercase(Locale.getDefault())

                                val searchT = searchText.text.toString()
                                    .lowercase(Locale.getDefault())

                                if(pathNama.contains(searchT) or pathNik.contains(searchT) or pathAlamat.contains(searchT)){
                                    println("ini hasilnya "+h.child("nama")+" = "+searchText.text.toString())
                                    val penduduk = h.getValue(Penduduk::class.java)
                                    if(penduduk != null){
                                        pddkListSearch.add(penduduk)
                                    }
                                }

                            }
                            val adapter = PendudukAdapter(this@SearchActivity, R.layout.item_penduduk, pddkListSearch)
                            listPddk.adapter = adapter
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        }
}