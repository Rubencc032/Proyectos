package com.jovian.activities2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jovian.activities2.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding //para almacener los elementos de la vista

    companion object { //A companion objects acts like an static class in Java
        const val KEY_EXTRA_NAME: String = "MY_KEY_EXTRA_NAME"
        const val KEY_EXTRA_SURNAME: String = "MY_KEY_EXTRA_SURNAME"
        const val KEY_EXTRA_RESULT: String = "MY_KEY_EXTRA_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cargamos el binding con los elementos de la vista
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable para almacenar el texto que recibamos
       var myText: String? = null

        //construimos la cadena
        if(intent.hasExtra(KEY_EXTRA_NAME))
            myText = "${intent.getStringExtra(KEY_EXTRA_NAME).toString()}"

        if(intent.hasExtra(KEY_EXTRA_SURNAME))
            myText = "${myText?:""} ${intent.getStringExtra(KEY_EXTRA_SURNAME).toString()}"

        //pasamos el texto a pantalla
        binding.tvTop.text = myText?:"No user"

        //ahora tenemsos que devolver el resultado
        binding.btnReturn.setOnClickListener{

            val text = binding.etName.text.toString()
            Log.i("valor", "$text")
            val returnIntent = Intent().apply {
                putExtra(KEY_EXTRA_RESULT, text)
            } //creates a new Intent with editText content as extra

            if (text != "")
                setResult(RESULT_OK, returnIntent) //The action went ok
            else
                setResult(RESULT_CANCELED, returnIntent)

            finish() //finish and close this activity

        }
    }
}