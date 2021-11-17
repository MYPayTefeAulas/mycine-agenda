package com.uea.mypay.agenda.repository.room.dao

import androidx.room.*
import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.repository.sqlite.COLUMN_ID
import com.uea.mypay.agenda.repository.sqlite.TABLE_CONTATO

@Dao
interface ContatoDao {
    @Insert
    fun insert(obj: Contato): Long

    @Update
    fun update(obj: Contato)

    @Delete
    fun delete(obj: Contato)

    @Query("""SELECT * FROM $TABLE_CONTATO WHERE $COLUMN_ID = :id""")
    fun contatoById(id: Long): Contato

    @Query("""SELECT * FROM $TABLE_CONTATO""")
    fun getAllContatos(): List<Contato>
}