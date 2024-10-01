package com.example.grupo4_sem_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener{

            var email = etEmail.text.toString()
            var pass = etPassword.text.toString()
            if (email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            } else {
                var nuevoUsuario = Usuario(email, pass)
                AppDatabase.getDatabase(this).usuarioDao().insertUsuario(nuevoUsuario)
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

    }

}