package id.ac.ukdw.pertemuan9_71190506

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.pertemuan9_71190506.Session.LoginPref

class LoginActivity:  AppCompatActivity() {
    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnLogin: Button

    lateinit var session: LoginPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = LoginPref(this)

        if(session.isLoggedIn()){
            var i:Intent = Intent(applicationContext, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)

            finish()
        }

        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            var username = edtUsername.text.toString().trim()
            var email = edtEmail.text.toString().trim()

            if(username.isEmpty() && email.isEmpty()){
                Toast.makeText(this,"Login gagal, coba lagi", Toast.LENGTH_SHORT).show()

            }else{
                session.createLogginSession(username,email)
                var i : Intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}