package com.uea.mypay.agenda.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uea.mypay.agenda.*
import com.uea.mypay.agenda.databinding.FragmentListaContatosBinding

class ListaContatosFragment: Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentListaContatosBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: ContatosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaContatosBinding.inflate(inflater, container, false)

        adapter = ContatosAdapter(mutableListOf(), ::onBtEditarClick)

        binding.rvContatos.layoutManager = LinearLayoutManager(context)
        binding.rvContatos.adapter = adapter
        binding.rvContatos.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))


        adapter.swapData(Agenda.listaContatos)

        inicializaLista()

        initTopBar()

        return binding.root
    }

    private fun initTopBar() {
        binding.toolbarListaContatos.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.toolbarContatosBusca -> {
                    val searchView = menuItem?.actionView as SearchView
                    searchView.queryHint = "Digite para pesquisar"
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
        val queryLowerCase = query.toString().lowercase()

        val listaFiltrada = Agenda.listaContatos.filter { contatoAtual ->
            contatoAtual.nome.lowercase().contains(queryLowerCase) ||
                    contatoAtual.telefone.lowercase().contains(queryLowerCase)
        }
        adapter.swapData(listaFiltrada)

        return true
    }

    override fun onResume() {
        super.onResume()
        adapter.swapData(Agenda.listaContatos)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inicializaLista() {
        Agenda.listaContatos.addAll(
            listOf(
                Contato("Genival Lima", "12345"),
                Contato("Luis Felipe INÁCIO", "12345"),
                Contato("Israel da Silva", "12345"),
                Contato("Vanessa Sobral", "33333"),
                Contato("José Augusto", "33333"),
                Contato("Pedro Henrique", "33333"),
                Contato("William Miguel", "12345"),
                Contato("Robert Luis", "12345"),
                Contato("Varlei Barbosa", "12345"),
                Contato("Sabrina de Souza", "12345"),
                Contato("Jéssica Rodrigues", "33333"),
                Contato("Ivan Carvalho", "12345"),
                Contato("Mario Mascarenhas", "33333"),
                Contato("MARIA CAROLINE", "12345"),
                Contato("RONEY JUNIOR", "12345"),
                Contato("Milena Dias", "12345"),
                Contato("Ecson Gama", "12345"),
                Contato("Maria Garcia", "33333"),
                Contato("RAIANE FERREIRA", "12345"),
                Contato("JAQUELINE LIMA", "12345"),
                Contato("Larissa Da Silva", "12345"),
                Contato("Erigeyce Gama", "12345"),
                Contato("Rodrigo Bernardino", "33333"),
                Contato("Narla Chagas", "12345"),
                Contato("Luiz Felipe de SOUZA", "12345"),
                Contato("Keitiane Nogueira", "12345"),
                Contato("Thalia de Souza", "12345"),
                Contato("José Santos", "33333"),
                Contato("Alex", "12345")
            )
        )
    }

    fun onBtEditarClick(indiceLista: Int) {
        val intent = Intent(context, EditarContatoActivity::class.java)
        intent.putExtra("indiceContato", indiceLista)
        startActivity(intent)
    }
}