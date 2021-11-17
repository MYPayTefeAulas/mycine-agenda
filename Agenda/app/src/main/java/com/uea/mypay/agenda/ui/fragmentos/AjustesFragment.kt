package com.uea.mypay.agenda.ui.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.uea.mypay.agenda.R
import com.uea.mypay.agenda.databinding.FragmentAjustesBinding
import com.uea.mypay.agenda.enums.TipoOrdenacao
import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.repository.room.AppDatabase
import com.uea.mypay.agenda.utils.PrefsConstants
import org.jetbrains.anko.doAsync

/**
 * Classe AjustesFragment para ajustar as configurações do app
 *
 * Opções implementadas: tipo de ordenação da lista de contatos
 *
 * @author Robert Luis Lara Ribeiro
 *<a href="mailto:robertlarabr@gmail.com">robertlarabr@gmail.com</a>
 */
class AjustesFragment: Fragment() {
    private var _binding: FragmentAjustesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjustesBinding.inflate(inflater,container,false)
        val view = binding.root

        val config = requireActivity().getSharedPreferences(PrefsConstants.FILE_CONFIGURACOES,0)

        val tipoOrdenacao_str = config.getString(
            PrefsConstants.KEY_TIPO_ORDENACAO_CONTATOS,
            TipoOrdenacao.ALFABETICA_AZ.toString()
        )
        val tipoOrdenacao: TipoOrdenacao = TipoOrdenacao.valueOf(tipoOrdenacao_str!!)

        when (tipoOrdenacao){
            TipoOrdenacao.ALFABETICA_AZ -> binding.radioOrdenacaoAZ.isChecked = true
            TipoOrdenacao.ALFABETICA_ZA -> binding.radioOrdenacaoZA.isChecked = true
            TipoOrdenacao.ORDEM_INSERCAO -> binding.radioOrdenacaoInsercao.isChecked = true
        }

        binding.radioGroupOrdenacao.setOnCheckedChangeListener { _, checkedId ->
            val novoTipoOrdenacao = when(checkedId) {
                binding.radioOrdenacaoAZ.id ->  TipoOrdenacao.ALFABETICA_AZ.toString()
                binding.radioOrdenacaoZA.id ->  TipoOrdenacao.ALFABETICA_ZA.toString()
                binding.radioOrdenacaoInsercao.id ->  TipoOrdenacao.ORDEM_INSERCAO.toString()
                else -> TipoOrdenacao.ORDEM_INSERCAO.toString()
            }
            val editor =config.edit()
            editor.putString(PrefsConstants.KEY_TIPO_ORDENACAO_CONTATOS,novoTipoOrdenacao)
            editor.apply()
        }

        binding.btAdicionarPadrao.setOnClickListener{
            doAsync {
                val db = AppDatabase.getDatabase(requireContext())
                LISTA_CONTATOS_PADRAO.forEach{
                    db.contatoDao().insert(it)
                }
            }
        }

        return  view
    }
    companion object {
        val LISTA_CONTATOS_PADRAO = listOf(
            Contato(nome = "Genival Lima", telefone = "12345"),
            Contato(nome = "Luis Felipe INÁCIO", telefone = "12345"),
            Contato(nome = "Israel da Silva", telefone = "12345"),
            Contato(nome = "Vanessa Sobral", telefone = "33333"),
            Contato(nome = "José Augusto", telefone = "33333"),
            Contato(nome = "Pedro Henrique", telefone = "33333"),
            Contato(nome = "William Miguel", telefone = "12345"),
            Contato(nome = "Robert Luis", telefone = "12345"),
            Contato(nome = "Varlei Barbosa", telefone = "12345"),
            Contato(nome = "Sabrina de Souza", telefone = "12345"),
            Contato(nome = "Jéssica Rodrigues", telefone = "33333"),
            Contato(nome = "Ivan Carvalho", telefone = "12345"),
            Contato(nome = "Mario Mascarenhas", telefone = "33333"),
            Contato(nome = "MARIA CAROLINE", telefone = "12345"),
            Contato(nome = "RONEY JUNIOR", telefone = "12345"),
            Contato(nome = "Milena Dias", telefone = "12345"),
            Contato(nome = "Ecson Gama", telefone = "12345"),
            Contato(nome = "Maria Garcia", telefone = "33333"),
            Contato(nome = "RAIANE FERREIRA", telefone = "12345"),
            Contato(nome = "JAQUELINE LIMA", telefone = "12345"),
            Contato(nome = "Larissa Da Silva", telefone = "12345"),
            Contato(nome = "Erigeyce Gama", telefone = "12345"),
            Contato(nome = "Rodrigo Bernardino", telefone = "33333"),
            Contato(nome = "Narla Chagas", telefone = "12345"),
            Contato(nome = "Luiz Felipe de SOUZA", telefone = "12345"),
            Contato(nome = "Keitiane Nogueira", telefone = "12345"),
            Contato(nome = "Thalia de Souza", telefone = "12345"),
            Contato(nome = "José Santos", telefone = "33333"),
            Contato(nome = "Alex", telefone = "12345")
        )

    }
}