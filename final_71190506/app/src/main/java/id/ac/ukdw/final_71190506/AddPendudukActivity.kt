package id.ac.ukdw.final_71190506

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class AddPendudukActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtNIK : EditText
    private lateinit var edtNama : EditText
    private lateinit var edtUmur : EditText
    private lateinit var edtAlamat : EditText

    private lateinit var btnSave : Button
    private lateinit var btnBackAdd : Button
    private lateinit var listPddk : ListView

    private lateinit var ref : DatabaseReference
    private lateinit var pddkList: MutableList<Penduduk>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_penduduk)
        ref = FirebaseDatabase.getInstance().getReference("penduduk")

        edtNIK = findViewById(R.id.edtNIK)
        edtNama = findViewById(R.id.edtNama)
        edtUmur = findViewById(R.id.edtUmur)
        edtAlamat = findViewById(R.id.edtAlamat)

        listPddk = findViewById(R.id.lv_penduduk)
        btnSave = findViewById(R.id.btnSave)
        btnBackAdd = findViewById(R.id.btnBackAdd)
        val profileName=intent.getStringExtra("Username")
        btnSave.setOnClickListener(this)

        pddkList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    pddkList.clear()
                    for(h in p0.children){
                        val penduduk = h.getValue(Penduduk::class.java)
                        if(penduduk != null){
                            pddkList.add(penduduk)
                        }
                    }
                    val adapter = PendudukAdapter(this@AddPendudukActivity, R.layout.item_penduduk, pddkList)
                    listPddk.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        btnBackAdd.setOnClickListener{
            Intent(this@AddPendudukActivity, MenuActivity::class.java).also {
                it.putExtra("Username", profileName)
                startActivity(it)
            }
        }
    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val nik = edtNIK.text.toString().trim()
        val nama = edtNama.text.toString().trim()
        val umur = edtUmur.text.toString().trim().toInt()
        val alamat = edtAlamat.text.toString().trim()

        if(nik.isEmpty()){
            edtNIK.error = "Isi NIK!"
            return
        }
        if(nama.isEmpty()){
            edtNama.error = "Isi Nama!"
            return
        }
        if(umur.equals(null)){
            edtUmur.error = "Isi Umur!"
            return
        }
        if(alamat.isEmpty()){
            edtAlamat.error = "Isi Alamat!"
            return
        }

        val pddkid = ref.push().key
        val pddk = Penduduk(pddkid!!, nik, nama, umur, alamat)

        if(pddkid != null){
            ref.child(pddkid).setValue(pddk).addOnCompleteListener{//ketika data berhasil ditambahkan
                Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}