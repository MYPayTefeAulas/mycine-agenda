package com.uea.mypay.agenda

data class Contato(var nome: String, var telefone: String) {

    override fun toString():String{
        return "$nome ($telefone)"
    }
}

