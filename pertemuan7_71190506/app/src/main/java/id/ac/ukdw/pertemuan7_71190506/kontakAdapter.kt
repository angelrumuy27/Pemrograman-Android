package id.ac.ukdw.pertemuan7_71190506

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class kontakAdapter(var listKontak: ArrayList<Kontak>): RecyclerView.Adapter<kontakAdapter.KontakHolder>() {
    class KontakHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(kontak: Kontak){
            view.findViewById<ImageView>(R.id.imgKontak).setImageResouce(kontak.)


        }

    }
}