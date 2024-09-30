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
    private lateinit var recordarUsuario: CheckBox

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
        recordarUsuario = findViewById(R.id.recordarusuario)

        val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        val usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        val passwordGuardada = preferencias.getString(resources.getString(R.string.password_usuario), "")

        if (usuarioGuardado != "" && passwordGuardada != ""){
            if (usuarioGuardado != null){
                startMainActivity(usuarioGuardado)
            }
        }

        btnIngresar.setOnClickListener {
            iniciarSesion()
        }

        btnCrearUsuario.setOnClickListener {
            crearUsuario()
        }

    }

    private fun iniciarSesion() {
        val usuarioText = usuario.text.toString()
        val passText = contraseña.text.toString()

        if (usuarioText.isNotEmpty() && passText.isNotEmpty()) {
            if (recordarUsuario.isChecked){
                val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuarioText).apply()
                preferencias.edit().putString(resources.getString(R.string.password_usuario), passText).apply()
            } else {
                val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                preferencias.edit().remove(resources.getString(R.string.nombre_usuario)).apply()
                preferencias.edit().remove(resources.getString(R.string.password_usuario)).apply()
            }

            startMainActivity(usuarioText)

        } else {
            Toast.makeText(
                this,
                "Por favor, complete ambos campos.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun crearUsuario() {
        val usuarioText = usuario.text.toString()
        val passText = contraseña.text.toString()

        if (usuarioText.isNotEmpty() && passText.isNotEmpty()) {

            Toast.makeText(
                this,
                "Usuario creado: $usuarioText\nContraseña: $passText",
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

    private fun startMainActivity (usuario: String){

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario), usuario)
        startActivity(intent)
        finish()

    }

}