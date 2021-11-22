package id.ac.ukdw.pertemuan11_71190506

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class SeachingActivity: AppCompatActivity() {
    var firestore: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searching)


        firestore = FirebaseFirestore.getInstance()

        val edtSearchByNim = findViewById<EditText>(R.id.edtSearchByNim)
        val btnSearchByNim = findViewById<Button>(R.id.btnSearchByNim)

        val edtSearchByNama = findViewById<EditText>(R.id.edtSearchByNama)
        val btnSearchByNama = findViewById<Button>(R.id.btnSearchByNama)

        val edtSearchByIpk = findViewById<EditText>(R.id.edtSearchByIpk)
        val btnSearchByIpk = findViewById<Button>(R.id.btnSearchByipk)

        val txvOutput =  findViewById<TextView>(R.id.txvOutout)

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        btnSearchByNim.setOnClickListener {
            firestore?.collection("mahasiswa")?.document(edtSearchByNim.text.toString())?.get()!!
                .addOnSuccessListener { doc ->
                    txvOutput.setText((doc.data.toString()))
                }
        }

        btnSearchByNama.setOnClickListener {
            firestore?.collection("mahasiswa")?.whereEqualTo("nama",edtSearchByNama.text.toString())?.get()!!
                .addOnSuccessListener { docs ->
                    var output = "NAMA | NIM | IPK "
                    for(doc in docs){
                        output += "\n${doc["nama"]} | ${doc["nim"]} | ${doc["ipk"]}"
                    }
                    txvOutput.setText(output)
                }
        }

        btnSearchByIpk.setOnClickListener {
            System.out.println("ipkkkk")
            firestore?.collection("mahasiswa")?.whereEqualTo("ipk",edtSearchByIpk.text.toString())?.get()!!
                .addOnSuccessListener { docs ->
                    System.out.println(edtSearchByIpk.text.toString())
                    var output = "NAMA | NIM | IPK "
                    for(doc in docs){
                        output += "\n${doc["nama"]} | ${doc["nim"]} | ${doc["ipk"]}"
                    }
                    txvOutput.setText(output)
                }
        }
    }
}