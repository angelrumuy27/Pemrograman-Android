package id.ac.ukdw.pertemuan5_71190506

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(){
    private lateinit var btnLogin : Button
    private lateinit var username : EditText
    private  lateinit var password : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById<EditText>(R.id.edtUsername)
        password = findViewById<EditText>(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{

            val usrname = username.text.toString().trim()
            val pass = password.text.toString().trim()

            if (usrname.isEmpty()){
                username.error = "Username required"
            }else if (pass.isEmpty()){
                password.error = "Password is required"
            }else if(pass=="1234"){
                val i = Intent(this, Home::class.java)
                i.putExtra("username",usrname)
                i.putExtra("password",pass)
                startActivity(i)
            }else{
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_LONG).show()
            }
        }


    }



//    override fun onClick(p0: View) {
//
//        if (p0.id == R.id.btnLogin ){
//                val i = Intent(this, Home::class.java)
//                val username = findViewById<EditText>(R.id.edtUsername)
//                val password = findViewById<EditText>(R.id.edtPassword)
//                i.putExtra("username",username.text.toString())
//                i.putExtra("password",password.text.toString())
//                startActivity(i)
//            }else{
//
//        }
//    }


}