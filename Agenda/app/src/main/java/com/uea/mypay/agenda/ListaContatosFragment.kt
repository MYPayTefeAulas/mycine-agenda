package com.uea.mypay.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.uea.mypay.agenda.databinding.FragmentListaContatosBinding

class ListaContatosFragment: Fragment() {

    private var _binding: FragmentListaContatosBinding? = null
    private  val binding get() = _binding!!

    private lateinit var adapter: ContatoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaContatosBinding.inflate(inflater, container, false)

        binding.rvContatos.layoutManager = LinearLayoutManager(context)
        binding.rvContatos.adapter = ContatoAdapter(Agenda.listaContatos)
        binding.rvContatos.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        incializaLista()

        return binding.root
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