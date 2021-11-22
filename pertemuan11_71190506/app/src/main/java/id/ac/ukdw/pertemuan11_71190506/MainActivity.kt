package id.ac.ukdw.pertemuan11_71190506

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnShowData = findViewById<Button>(R.id.btnShowData)
        val edtNama = findViewById<EditText>(R.id.edtNamaMhs)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnShowData.setOnClickListener {
            var i = Intent(this,MahasiswalistActivity::class.java)
            startActivity(i)
            finish()
        }

        btnAdd.setOnClickListener {
            val mahasiswa = Mahasiswa(edtNama.text.toString(), edtNim.text.toString(), edtIpk.text.toString())
            firestore?.collection("mahasiswa")?.document(mahasiswa.NIM)?.set(mahasiswa)
            val text = "Data Berhasil Ditambahkan"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

        }

        btnSearch.setOnClickListener {

            val i = Intent(this, SeachingActivity::class.java)
            startActivity(i)
        }

    }
}