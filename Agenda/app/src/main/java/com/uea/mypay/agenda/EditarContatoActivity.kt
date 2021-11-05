package com.uea.mypay.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uea.mypay.agenda.databinding.ActivityEditarContatoBinding
import com.uea.mypay.agenda.utils.IntentsConstants
/**
 * Classe EditarContatoActivity tela que abre ao apertar em EditarContatos, é chamada
 * a partir do Fragment ListaContatos
 *
 * Possui botão de salvar, deletar e marcar contato como favorito
 *
 * @author Robert Luis Lara Ribeiro
 *<a href="mailto:robertlarabr@gmail.com">robertlarabr@gmail.com</a>
 */
class EditarContatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarContatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarContatoBinding.inflate(layoutInflater)

        setTitle(getString(R.string.editar_contato))

        val indiceContato = intent.getIntExtra(IntentsConstants.INT_INDICE_CONTATO, -1)

        val nome: String = Agenda.listaContatos[indiceContato].nome
        val telefone: String = Agenda.listaContatos[indiceContato].telefone
        binding.agendaTxtTelefone.setText(telefone)
        binding.agendaTxtNome.setText(nome)
        binding.switchaContatoFavorito.isChecked = Agenda.listaContatos[indiceContato].favorito

        binding.agendaBtSalvar.setOnClickListener {
            Agenda.listaContatos[indiceContato].nome = binding.agendaTxtNome.text.toString()
            Agenda.listaContatos[indiceContato].telefone = binding.agendaTxtTelefone.text.toString()
            Toast.makeText(this, getString(R.string.contato_salvo_sucesso), Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btDeletar.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(this)
            dialog.setTitle(getString(R.string.deletar_contato))
            dialog.setMessage(getString(R.string.realmente_contato))
            dialog.setNegativeButton(getString(R.string.cancelar),null)
            dialog.setPositiveButton(getString(R.string.deletar)){ _, _ ->
                    Agenda.listaContatos.removeAt(indiceContato)
                    Toast.makeText(this, getString(R.string.contato_removido), Toast.LENGTH_SHORT).show()
                    finish()
                }
                dialog.show()
        }

        binding.switchaContatoFavorito.setOnCheckedChangeListener { _, ischecked ->
            Agenda.listaContatos[indiceContato].favorito = ischecked
        }

        setContentView(binding.root)
    }
}