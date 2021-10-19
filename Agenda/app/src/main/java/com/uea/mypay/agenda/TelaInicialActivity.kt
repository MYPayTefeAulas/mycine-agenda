package com.uea.mypay.agenda

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.uea.mypay.agenda.databinding.ActivityTelaInicialBinding

class TelaInicialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaInicialBinding

    private lateinit var adapter: ArrayAdapter<Contato>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaInicialBinding.inflate(layoutInflater)

        Agenda.listaContatos.add(Contato("Carlos", "11111"))
        Agenda.listaContatos.add(Contato("Milena", "22222"))
        Agenda.listaContatos.add(Contato("Robert", "33333"))
        Agenda.listaContatos.add(Contato("Roney" , "44444"))
        Agenda.listaContatos.add(Contato("Varley", "55555"))

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Agenda.listaContatos)
        binding.lvContatos.adapter = adapter
        binding.lvContatos.setOnItemClickListener { parent, view, position, id ->
//            val contato = adapter.getItem(position) // obtém o contato se quiser fazer algo com ele
//            Toast.makeText(this, "${contato!!.nome}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EditarContatoActivity::class.java)
            intent.putExtra("indiceContato", position)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}
