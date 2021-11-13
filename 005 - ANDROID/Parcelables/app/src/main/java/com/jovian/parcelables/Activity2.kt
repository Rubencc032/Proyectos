package com.jovian.parcelables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.parcelables.databinding.Activity2Binding


class Activity2 : AppCompatActivity() {

    lateinit var binding: Activity2Binding

    companion object {
        const val KEY_EXTRA_RESULT_PARCELABLE: String = "MY_KEY_EXTRA_RESULT_PARCELABLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var myText:String? = null

        if(intent.hasExtra(KEY_EXTRA_RESULT_PARCELABLE)){
            val person = intent.getParcelableExtra<Person>(KEY_EXTRA_RESULT_PARCELABLE)
            person.let{person ->
                myText = "${person?.name} ${person?.surname}"
            }
        }

        binding.tvTop.text = myText?:"No user"
    }
}