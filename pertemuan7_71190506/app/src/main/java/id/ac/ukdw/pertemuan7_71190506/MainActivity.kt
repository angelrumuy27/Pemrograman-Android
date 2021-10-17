package id.ac.ukdw.pertemuan7_71190506

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listKontak = arrayListOf<Kontak>()
        listKontak.add(Kontak("Mom", R.mipmap.elina))
        listKontak.add(Kontak("Dad", R.mipmap.elina))
        listKontak.add(Kontak("Mom", R.mipmap.elina))
        listKontak.add(Kontak("Mom", R.mipmap.elina))


        val rcyKontak = findViewById<RecyclerView>(R.id.rcyKontak)
        rcyKontak.layoutManager = LinearLayoutManager(this)
        val adapter = KontakAdapter(listKontak,this)
        rcyKontak.adapter = adapter
    }
}