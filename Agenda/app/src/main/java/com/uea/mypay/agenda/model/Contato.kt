package com.uea.mypay.agenda.model
/**
 * Classe Contato para armazenar informações de contatos, é a parte do conjunto "lista de contatos"
 *
 *
 * @property nome o nome do contato
 * @property telefone o telefone do contato
 * @property favorito indica se é um contato favorito ou não
 *
 * @author Robert Luis Lara Ribeiro
 *<a href="mailto:robertlarabr@gmail.com">robertlarabr@gmail.com</a>
 */
data class Contato(var nome: String, var telefone: String, var favorito: Boolean=false) {


}

