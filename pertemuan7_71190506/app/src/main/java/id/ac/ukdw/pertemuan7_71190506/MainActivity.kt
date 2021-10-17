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
        listKontak.add(Kontak("Mom", R.mipmap.elina,"+6287688665732",2))
        listKontak.add(Kontak("Dad", R.mipmap.dad,"+6287755667744",5))
        listKontak.add(Kontak("Mrs.Bob", R.mipmap.fifi,"+6285344667737",7))
        listKontak.add(Kontak("Eric", R.mipmap.elina,"+6280975744667",2))
        listKontak.add(Kontak("Joy", R.mipmap.joy,"+6289867456437",1))
        listKontak.add(Kontak("Silvi", R.mipmap.silvi,"+6280975764566",0))
        listKontak.add(Kontak("Rut", R.mipmap.rut,"+6287554980978",8))
        listKontak.add(Kontak("Gan", R.mipmap.elina,"+6280977553665",4))
        listKontak.add(Kontak("Pak Budi", R.mipmap.pakbudi,"+6283322679654",3))
        listKontak.add(Kontak("Bu Budi", R.mipmap.bubudi,"+6280987674587",3))
        listKontak.add(Kontak("Catlin", R.mipmap.catlin,"+6287008866547",4))
        listKontak.add(Kontak("Fifi", R.mipmap.fifi,"+6282324697488",2))
        listKontak.add(Kontak("Tata", R.mipmap.tata,"+6280986644776",5))








        val rcyKontak = findViewById<RecyclerView>(R.id.rcyKontak)
        rcyKontak.layoutManager = LinearLayoutManager(this)
        val adapter = KontakAdapter(listKontak,this)
        rcyKontak.adapter = adapter
    }
}