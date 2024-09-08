package com.example.grupo4_sem_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var usuario: EditText
    private lateinit var contraseña: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCrearUsuario: Button
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuario = findViewById(R.id.editUsuario)
        contraseña = findViewById(R.id.editContraseña)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario)
        checkBox = findViewById(R.id.checkbox)

        btnIngresar.setOnClickListener {
            iniciarSesion()
        }

        btnCrearUsuario.setOnClickListener {
            crearUsuario()
        }

    }

    private fun iniciarSesion() {
        val usuarioText = usuario.text.toString()
        val contraseñaText = contraseña.text.toString()

        if (usuarioText.isNotEmpty() && contraseñaText.isNotEmpty()) {
            if (checkBox.isChecked) {
                if (verificarCredenciales(usuarioText, contraseñaText)) {
                    /*Toast.makeText(
                        this,
                        "Inicio de sesión exitoso.",
                        Toast.LENGTH_SHORT
                    ).show()*/

                var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(
                        this,
                        "Usuario o contraseña incorrectos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Por favor, marque el CheckBox para iniciar sesión.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Por favor, complete ambos campos.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun verificarCredenciales(usuario: String, contraseña: String): Boolean {

        return usuario == "pepe12@gmail.com" && contraseña == "12345"

        btnCrearUsuario.setOnClickListener {
            crearUsuario()
        }
    }

    private fun crearUsuario() {
        val usuarioText = usuario.text.toString()
        val contraseñaText = contraseña.text.toString()

        if (usuarioText.isNotEmpty() && contraseñaText.isNotEmpty()) {

            Toast.makeText(
                this,
                "Usuario creado: $usuarioText\nContraseña: $contraseñaText",
                Toast.LENGTH_SHORT
            ).show()

            usuario.text.clear()
            contraseña.text.clear()
        } else {
            Toast.makeText(
                this,
                "Por favor, complete ambos campos para crear un usuario.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}