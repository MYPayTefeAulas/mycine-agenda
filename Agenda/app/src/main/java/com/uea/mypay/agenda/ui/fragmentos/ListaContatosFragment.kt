package com.uea.mypay.agenda.ui.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uea.mypay.agenda.*
import com.uea.mypay.agenda.databinding.FragmentListaContatosBinding
import com.uea.mypay.agenda.enums.TipoOrdenacao
import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.repository.room.AppDatabase
import com.uea.mypay.agenda.ui.EditarContatoActivity
import com.uea.mypay.agenda.utils.IntentsConstants
import com.uea.mypay.agenda.utils.PrefsConstants
import com.uea.mypay.agenda.viewmodel.ListaContatosViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Classe ListaContatosFragment para listar todos os contatos da agenda
 *
 * Botão EDITAR permite edição e ActionBar contém botão de busca
 *
 * @author Robert Luis Lara Ribeiro
 *<a href="mailto:robertlarabr@gmail.com">robertlarabr@gmail.com</a>
 */
class ListaContatosFragment: Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentListaContatosBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: ContatosAdapter

    private lateinit var viewModel: ListaContatosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaContatosBinding.inflate(inflater, container, false)

        adapter = ContatosAdapter(mutableListOf(), ::onBtEditarClick)

        binding.rvContatos.layoutManager = LinearLayoutManager(context)
        binding.rvContatos.adapter = adapter
        binding.rvContatos.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        doAsync {
            viewModel = ListaContatosViewModel(AppDatabase.getDatabase(requireContext()))
            val listaContatos = viewModel.getAllContatos()
            uiThread {
                carregaLista(listaContatos)
            }
        }

        initTopBar()

        return binding.root
    }

    private fun carregaLista(listaContatos: List<Contato>) {
        val config = requireActivity().getSharedPreferences(PrefsConstants.FILE_CONFIGURACOES, 0)
        val tipoOrdenacao_str = config.getString(PrefsConstants.KEY_TIPO_ORDENACAO_CONTATOS, TipoOrdenacao.ORDEM_INSERCAO.toString())
       val tipoOrdenacao: TipoOrdenacao = TipoOrdenacao.valueOf(tipoOrdenacao_str!!)

        when (tipoOrdenacao) {
            TipoOrdenacao.ALFABETICA_AZ -> {
                val listaOrdenada = listaContatos.sortedBy { it.nome }
                adapter.swapData(listaOrdenada)
            }
            TipoOrdenacao.ALFABETICA_ZA -> {
                val listaOrdenada = listaContatos.sortedByDescending {it.nome }
                adapter.swapData(listaOrdenada)
            }
            TipoOrdenacao.ORDEM_INSERCAO -> {
                adapter.swapData(listaContatos)
            }
        }
    }

    private fun initTopBar() {
        binding.toolbarListaContatos.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.toolbarContatosBusca -> {
                    val searchView = menuItem?.actionView as SearchView
                    searchView.queryHint = getString(R.string.digite_pesquisar)
                    searchView.setOnQueryTextListener(this)
                    true
                }
                else -> false
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return  onQueryTextSubmit(newText)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        val queryLowerCase = query.toString().lowercase()
//
//        val listaFiltrada = Agenda.listaContatos.filter { contatoAtual ->
//            contatoAtual.nome.lowercase().contains(queryLowerCase) ||
//                    contatoAtual.telefone.lowercase().contains(queryLowerCase)
//        }
//        adapter.swapData(listaFiltrada)

        return true
    }

    override fun onResume() {
        super.onResume()
        doAsync {
            val listaContatos = viewModel.getAllContatos()
            uiThread {
                carregaLista(listaContatos)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onBtEditarClick(indiceLista: Int) {
        val intent = Intent(context, EditarContatoActivity::class.java)
        intent.putExtra(IntentsConstants.INT_INDICE_CONTATO, indiceLista)
        startActivity(intent)
    }
}