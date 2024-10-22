package com.example.grupo4_sem_kotlin

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class LoginActivity : AppCompatActivity() {

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCrearUsuario: Button
    private lateinit var recordarUsuario: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        setupWindowInsets()
        initializeViews()
        checkSavedCredentials()

        btnIngresar.setOnClickListener { iniciarSesion() }
        btnCrearUsuario.setOnClickListener { crearUsuario() }

        createNotificationChannel()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeViews() {
        usuario = findViewById(R.id.editUsuario)
        contrasena = findViewById(R.id.editContrasena)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario)
        recordarUsuario = findViewById(R.id.recordarusuario)
    }

    private fun checkSavedCredentials() {
        val preferencias = getSharedPreferences(getString(R.string.sp_credenciales), MODE_PRIVATE)
        val usuarioGuardado = preferencias.getString(getString(R.string.nombre_usuario), "")
        val passwordGuardada = preferencias.getString(getString(R.string.password_usuario), "")

        if (!usuarioGuardado.isNullOrEmpty() && !passwordGuardada.isNullOrEmpty()) {
            startMainActivity(usuarioGuardado)
        }
    }

    private fun iniciarSesion() {
        val usuarioText = usuario.text.toString()
        val passText = contrasena.text.toString()

        if (usuarioText.isNotEmpty() && passText.isNotEmpty()) {
            val usuarioBuscado = AppDatabase.getDatabase(this).usuarioDao().getUsuario(usuarioText, passText)

            if (usuarioBuscado != null) {
                if (recordarUsuario.isChecked) {
                    saveCredentials(usuarioText, passText)
                    showRememberUserNotification()
                }
                startMainActivity(usuarioText)
            } else {
                showToast("Credenciales incorrectas.")
            }
        } else {
            showToast("Por favor, complete ambos campos.")
        }
    }

    private fun crearUsuario() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun startMainActivity(usuario: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(getString(R.string.nombre_usuario), usuario)
        }
        startActivity(intent)
        finish()
    }

    private fun saveCredentials(usuario: String, contrasena: String) {
        val preferencias = getSharedPreferences(getString(R.string.sp_credenciales), MODE_PRIVATE)
        with(preferencias.edit()) {
            putString(getString(R.string.nombre_usuario), usuario)
            putString(getString(R.string.password_usuario), contrasena)
            apply()
        }
    }

    private fun createNotificationChannel() {

            val name = "Recordar Usuario"
            val descriptionText = "Notificación cuando el usuario elige recordar sus credenciales."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("recordar_usuario_channel", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


    @SuppressLint("MissingPermission")
    private fun showRememberUserNotification() {
        val builder = NotificationCompat.Builder(this, "recordar_usuario_channel")
            .setContentTitle("Usuario recordado")
            .setContentText("Se recordará el usuario para futuros inicios de sesión.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1001, builder.build())
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
