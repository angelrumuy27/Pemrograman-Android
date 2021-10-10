package id.ac.ukdw.pertemuan6_71190506

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment

class FragmentB: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_b,container,false)
        val btnFrgA = v.findViewById<Button>(R.id.btnFrgB)
        btnFrgA.setOnClickListener {
            val i: Intent = Intent(activity, ThirdActivity::class.java).apply { startActivity(this) }
        }
        return v
    }
}