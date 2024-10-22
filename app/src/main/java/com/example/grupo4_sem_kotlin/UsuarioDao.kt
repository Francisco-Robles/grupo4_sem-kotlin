package com.example.grupo4_sem_kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios_entity WHERE email = :email AND password = :password")
    fun getUsuario(email: String, password: String): Usuario?

}