package com.jovian.activities2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.jovian.activities2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //para almacener los elementos de la vista


    private val userName: String = "Jorge"
    private val surName: String = "Victoria"
    companion object { //A companion objects acts like an static class in Java
        val KEY_EXTRA_NAME: String = "MY_KEY_EXTRA_NAME"
        val KEY_EXTRA_SURNAME: String = "MY_KEY_EXTRA_SURNAME"
        const val KEY_EXTRA_RESULT: String = "MY_KEY_EXTRA_VALUE"
    }

    //para recoger la informacion que nos devuelve la activity2
    @SuppressLint("SetTextI18n")
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            binding.tvGreeting.text = "Hello ${data?.getStringExtra(KEY_EXTRA_RESULT)?:"No return"}"
        }
    }

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
        //bundle para pasar datos
        val bundle = Bundle()
        //pasamos la pareja clave, valor
        bundle.putString(KEY_EXTRA_NAME, userName)
        bundle.putString(KEY_EXTRA_SURNAME, surName)
        intent.putExtras(bundle)

        /*alternativa
        val intent = Intent(this, Activity2::class.java).apply {
        putExtra(KEY_EXTRA_NAME, userName)
        putExtra(KEY_EXTRA_SURNAME, surName)
        }*/
        //tartActivity(intent)
        resultLauncher.launch(intent)
    }
}