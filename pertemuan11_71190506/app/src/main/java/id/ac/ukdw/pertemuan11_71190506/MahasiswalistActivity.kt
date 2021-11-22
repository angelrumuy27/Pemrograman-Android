package id.ac.ukdw.pertemuan11_71190506


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MahasiswalistActivity: AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var optionDesc: Spinner
    lateinit var result: TextView
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswalist)

        firestore = FirebaseFirestore.getInstance()

        var btnBack = findViewById<Button>(R.id.btnBack)

        result = findViewById<TextView>(R.id.txvListData)

        option = findViewById<Spinner>(R.id.spOptionAsc)

        optionDesc= findViewById<Spinner>(R.id.spOptionDesc)

        btnBack.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        firestore?.collection("mahasiswa")?.get()!!
            .addOnSuccessListener { docs ->
                var output = "NAMA | NIM | IPK "
                for (doc in docs) {
                    output += "\n${doc["nama"]} | ${doc["nim"]} | ${doc["ipk"]}"
                }
                result.setText(output)
            }

        val options = arrayOf("nim", "nama", "ipk")
        optionDesc.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        optionDesc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                firestore?.collection("mahasiswa")
                    ?.orderBy(options.get(position), Query.Direction.DESCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = "NAMA | NIM | IPK "
                        for (doc in docs) {
                            output += "\n${doc["nama"]} | ${doc["nim"]} | ${doc["ipk"]}"
                        }
                        result.setText(output)
                    }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                firestore?.collection("mahasiswa")
                    ?.orderBy(options.get(position), Query.Direction.ASCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = "NAMA | NIM | IPK "
                        for (doc in docs) {
                            output += "\n${doc["nama"]} | ${doc["nim"]} | ${doc["ipk"]}"
                        }
                        result.setText(output)
                    }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }
    }
}

