package id.ac.ukdw.pertemuan7_71190506

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KontakAdapter (var listKontak: ArrayList<Kontak>, var context: Context): RecyclerView.Adapter<KontakAdapter.KontakHolder>(){
    class KontakHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(kontak: Kontak, context: Context){
            val foto = view.findViewById<ImageView>(R.id.imgKontak).setImageResource(kontak.foto)
            val nama = view.findViewById<TextView>(R.id.txtNama).setText(kontak.nama)
            view.setOnClickListener{
                val noHP = kontak.noHp
                val tertaut = kontak.tertaut
                val nama = kontak.nama
                val foto = kontak.foto
                val i = Intent(context, detailActivity::class.java)
                i.putExtra("nama",nama)
                i.putExtra("noHP",noHP)
                i.putExtra("tertaut",tertaut)
                i.putExtra("foto",foto)
                context.startActivity(i)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_kontak,parent,false)
        return KontakHolder(v)
    }

    override fun onBindViewHolder(holder: KontakHolder, position: Int) {
        holder.bind(listKontak[position], context)
    }

    override fun getItemCount(): Int {
        return listKontak.size
    }

}