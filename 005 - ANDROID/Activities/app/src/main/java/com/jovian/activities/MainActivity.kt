package com.jovian.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //para almacener los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cargamos el binding con los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //le ponemos el listener al boton y llamamos a la funcion
        binding.btnGo.setOnClickListener() { openActivity2()}
    }

    //funcion para llamar a la siguiente actividad
    private fun openActivity2() {

        //creamos el intent para poder ir a la otra actividad
        //le pasamos el contexto y que actividad llamamos
        val intent = Intent(this, Activity2::class.java)
        startActivity(intent)
    }
}