package com.uea.mypay.agenda

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uea.mypay.agenda.databinding.ActivityTelaInicialBinding

class TelaInicialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaInicialBinding

    private lateinit var adapter: ArrayAdapter<Contato>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaInicialBinding.inflate(layoutInflater)

        incializaLista()


        binding.rvContatos.layoutManager = LinearLayoutManager(this)
        binding.rvContatos.adapter = ContatoAdapter(Agenda.listaContatos)
        binding.rvContatos.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        setContentView(binding.root)
    }

    private fun incializaLista() {
        Agenda.listaContatos.addAll(
            listOf(
                Contato("1 Carlos", "11111"),
                Contato("2 Milena", "22222"),
                Contato("3 Robert", "33333"),
                Contato("4 Roney" , "44444"),
                Contato("5 Varley", "55555"),
                Contato("6 Maria",  "33333"),
                Contato("7 Maria",  "33333"),
                Contato("8 Maria",  "33333"),
                Contato("9 Maria",  "33333"),
                Contato("10 Maria", "33333"),
                Contato("11 Maria", "33333"),
                Contato("12 Maria", "33333"),
                Contato("13 Maria", "33333"),
                Contato("14 Maria", "33333"),
                Contato("15 Maria", "33333"),
                Contato("16 Maria", "33333"),
                Contato("17 Maria", "33333"),
                Contato("18 Maria", "33333")
            )
        )
    }

}
