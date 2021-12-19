package id.ac.ukdw.final_71190506

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class PendudukAdapter(val pCtx : Context, val layoutResId : Int, val pddkList :List<Penduduk> ): ArrayAdapter<Penduduk>(pCtx, layoutResId, pddkList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(pCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val tvNama : TextView = view.findViewById(R.id.tvNama)
        val tvNik : TextView = view.findViewById(R.id.tvNik)
        val tvUmur : TextView = view.findViewById(R.id.tvUmur)
        val tvAlamat : TextView = view.findViewById(R.id.tvAlamat)
        val tvEdit : TextView = view.findViewById(R.id.tvEdit)

        val penduduk = pddkList[position]


        tvEdit.setOnClickListener {
            showUpdateDialog(penduduk)
        }

        tvNama.text = penduduk.nama
        tvNik.text = penduduk.nik
        tvUmur.text = penduduk.umur.toString()
        tvAlamat.text = penduduk.alamat

        return view
    }

    private fun showUpdateDialog(penduduk: Penduduk) {
        val builder = AlertDialog.Builder(pCtx)
        builder.setTitle("Edit Data")

        val inflater = LayoutInflater.from(pCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val edtNIK = view.findViewById<EditText>(R.id.edtNIK)
        val edtNama = view.findViewById<EditText>(R.id.edtNama)
        val edtUmur = view.findViewById<EditText>(R.id.edtUmur)
        val edtAlamat = view.findViewById<EditText>(R.id.edtAlamat)

        //tangkap data
        edtNIK.setText(penduduk.nik)
        edtNama.setText(penduduk.nama)
        edtUmur.setText(penduduk.umur.toString())
        edtAlamat.setText(penduduk.alamat)

        builder.setView(view)

        //update data
        builder.setPositiveButton("Update"){p0,p1 ->
            val dbPddk = FirebaseDatabase.getInstance().getReference("penduduk")
            val nik = edtNIK.text.toString().trim()
            val nama = edtNama.text.toString().trim()
            val umur = edtUmur.text.toString().trim().toInt()
            val alamat = edtAlamat.text.toString().trim()

            if(nik.isEmpty()){
                edtNIK.error = "NIK tidak boleh kosong"
                edtNIK.requestFocus()
                return@setPositiveButton
            }
            if(nama.isEmpty()){
                edtNama.error = "Nama tidak boleh kosong"
                edtNama.requestFocus()
                return@setPositiveButton
            }
            if(umur==0){
                edtUmur.error = "Umur tidak boleh kosong"
                edtUmur.requestFocus()
                return@setPositiveButton
            }
            if(alamat.isEmpty()){
                edtAlamat.error = "Alamat tidak boleh kosong"
                edtAlamat.requestFocus()
                return@setPositiveButton
            }

            val penduduk = Penduduk(penduduk.id, nik, nama, umur, alamat)
            dbPddk.child(penduduk.id!!).setValue(penduduk)

            Toast.makeText(pCtx, "Data berhasil terupdate", Toast.LENGTH_SHORT).show()


        }
        //btn cancel
        builder.setNeutralButton("Cancel"){p0,p1->

        }
        //btn delete
        builder.setNegativeButton("Delete"){ p0, p1 ->
            val dbPddk = FirebaseDatabase.getInstance().getReference("penduduk").child(penduduk.id)
            dbPddk.removeValue()
            Toast.makeText(pCtx, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show()
        }
        val alert = builder.create()
        alert.show()
    }
}