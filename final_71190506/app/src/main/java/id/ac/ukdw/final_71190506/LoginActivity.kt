package id.ac.ukdw.final_71190506

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnlogin = findViewById<Button>(R.id.btnLogin)
        var edtEmail = findViewById<EditText>(R.id.etEmail)
        var edtPass = findViewById<EditText>(R.id.etPassword)

        btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val pass = edtPass.text.toString().trim()
            if (email.isEmpty()){
                edtEmail.error = "Email tidak boleh kosong"
                edtEmail.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edtEmail.error = "Email tidak valid"
                edtEmail.requestFocus()
                return@setOnClickListener
            }
            if(pass.isEmpty()){
                edtPass.error = "Password tidak boleh kosong"
                edtPass.requestFocus()
                return@setOnClickListener
            }
            if(pass.length<6){
                edtPass.error = "Password harus lebih dari 6 karakter"
                edtPass.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,pass)
        }

    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){
                if(it.isSuccessful){//jika login sukses
                    Intent(this@LoginActivity, MenuActivity::class.java).also{intent->
                        intent.putExtra("Username", auth.currentUser!!.email)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        }
                }else{//jika login gagal
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        //jika user saat ini tidak null/ sudah login maka intent ke menuactivity
        if(auth.currentUser != null){
            Intent(this@LoginActivity, MenuActivity::class.java).also { intent ->
                intent.putExtra("Username", auth.currentUser!!.email)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}