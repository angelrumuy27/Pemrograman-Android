package id.ac.ukdw.pertemuan6_71190506

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentC: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_c,container,false)
        val btnFrgA = v.findViewById<Button>(R.id.btnFrgC)
        btnFrgA.setOnClickListener {
            val i: Intent = Intent(activity, MainActivity::class.java).apply { startActivity(this) }
        }
        return v
    }
}