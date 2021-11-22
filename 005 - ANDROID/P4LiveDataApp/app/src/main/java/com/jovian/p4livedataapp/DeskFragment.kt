package com.jovian.p4livedataapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jovian.p4livedataapp.databinding.FragmentDeskBinding

class DeskFragment : Fragment() {

    //variable para recoger todos los elementos del fragmento
    private lateinit var binding:FragmentDeskBinding

    //cogemos todos los elementos de la vista. En principio fijo para todos los fragmentos
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout para este fragmento
        return FragmentDeskBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    //aqui empezamos a recoger info de la app y darle funcionalidad
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}