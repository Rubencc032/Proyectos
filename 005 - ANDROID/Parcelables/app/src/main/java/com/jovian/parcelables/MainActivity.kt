package com.jovian.parcelables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.parcelables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY_EXTRA_RESULT_PARCELABLE: String = "MY_KEY_EXTRA_RESULT_PARCELABLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener{ sendParcelablePerson() }
    }

    private fun sendParcelablePerson() {
        val person = Person("Carlos", "Tarazona")
        val intent = Intent(this, Activity2::class.java).apply {
            putExtra(KEY_EXTRA_RESULT_PARCELABLE, person)
        }

        startActivity(intent)
    }
}