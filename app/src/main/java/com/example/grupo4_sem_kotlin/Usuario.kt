package com.example.grupo4_sem_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios_entity")
data class Usuario(
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
