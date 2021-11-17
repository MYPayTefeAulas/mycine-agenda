package com.uea.mypay.agenda.repository.room

import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.repository.ContatoDataSource

class ContatoRepository (database: AppDatabase) : ContatoDataSource {
    private val contatoDao = database.contatoDao()

    override fun contatoById(id: Long): Contato {
        return contatoDao.contatoById(id)
    }

    override fun save(obj: Contato) {
        if(obj.id == 0L) {
            val id = insert(obj)
            obj.id = id
        } else {
            update(obj)
        }
    }

    override fun insert(obj: Contato): Long {
        return contatoDao.insert(obj)
    }

    override fun update(obj: Contato) {
        return contatoDao.update(obj)
    }

    override fun delete(obj: Contato) {
        return contatoDao.delete(obj)
    }

    override fun getAllContatos(): List<Contato> {
        return contatoDao.getAllContatos()
    }
}