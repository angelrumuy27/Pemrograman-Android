package id.ac.ukdw.pertemuan7_71190506

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nama = intent.getStringExtra("nama")
        val noHP =intent.getStringExtra("noHP")
        val tertaut = intent.getIntExtra("noHP",0)
        val foto = intent.getIntExtra("foto",0)

        val showNama = findViewById<TextView>(R.id.txtShowNama)
        val showNoHp = findViewById<TextView>(R.id.txtShowNoHp)
        val showTertaut = findViewById<TextView>(R.id.txtShowTertaut)
        val showFoto = findViewById<ImageView>(R.id.imgShowKontak)

        showNama.text = nama
        showNoHp.text = noHP
        showTertaut.setText(tertaut)
        showFoto.setImageResource(foto)
    }

}