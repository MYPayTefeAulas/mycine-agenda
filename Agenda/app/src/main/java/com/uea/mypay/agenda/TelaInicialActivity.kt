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

//    private lateinit var adapter: ArrayAdapter<Contato>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaInicialBinding.inflate(layoutInflater)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ListaContatosFragment())
            .commit()

        binding.bottomNavigationView4.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.ic_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, ListaContatosFragment())
                        .commit()
                    true
                }
                R.id.ic_ajustes -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, AjustesFragment())
                        .commit()
                    true
                }
                else ->
                    false
            }
        }

        setContentView(binding.root)
    }



}
