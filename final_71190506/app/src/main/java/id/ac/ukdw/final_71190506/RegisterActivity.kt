package id.ac.ukdw.final_71190506

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        var edtEmail = findViewById<EditText>(R.id.etEmail)
        var edtPass = findViewById<EditText>(R.id.etPassword)

        var btnSignUp = findViewById<Button>(R.id.btnSignup)
        btnSignUp.setOnClickListener {
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
            registerUser(email, pass)
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            Intent(this@RegisterActivity, LoginActivity::class.java).also{
                startActivity(it)
            }
        }
    }

    //fungsi register user
    private fun registerUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
                //jika berhasil signup
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Intent(this@RegisterActivity, MenuActivity::class.java).also{
                        it.putExtra("Username", auth.currentUser!!.email)
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@RegisterActivity, MenuActivity::class.java).also {
                intent.putExtra("Username", auth.currentUser!!.email)
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}