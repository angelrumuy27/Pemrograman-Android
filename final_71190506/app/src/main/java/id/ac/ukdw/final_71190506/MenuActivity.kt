package id.ac.ukdw.final_71190506

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth = FirebaseAuth.getInstance()

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnAddDelUp = findViewById<Button>(R.id.btnAddDelUp)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)

        val profileName=intent.getStringExtra("Username")

        tvUsername.setText(profileName)

        btnLogout.setOnClickListener{
            auth.signOut()
                Intent(this@MenuActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                    Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
                }
        }

        btnSearch.setOnClickListener {
            Intent(this@MenuActivity, SearchActivity::class.java).also {
                it.putExtra("Username", auth.currentUser!!.email)
                startActivity(it)
            }
        }
        btnAddDelUp.setOnClickListener {
            Intent(this@MenuActivity, AddPendudukActivity::class.java).also {
                it.putExtra("Username", auth.currentUser!!.email)
                startActivity(it)
            }
        }
    }
}