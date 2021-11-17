package com.uea.mypay.agenda.viewmodel

import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.repository.room.AppDatabase
import com.uea.mypay.agenda.repository.room.ContatoRepository

class ListaContatosViewModel(database: AppDatabase) {
    private val contatoRepository = ContatoRepository(database)

    fun getAllContatos(): List<Contato> {
        return contatoRepository.getAllContatos()
    }
}