package id.ac.ukdw.pertemuan5_71190506

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username")
        val password = intent.getIntExtra("password",0)
        val hasil = findViewById<TextView>(R.id.hasil)
        hasil.text = "Selamat Datang $username"


    }
}