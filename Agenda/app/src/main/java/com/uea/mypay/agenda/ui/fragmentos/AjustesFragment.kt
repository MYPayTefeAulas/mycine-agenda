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
import com.uea.mypay.agenda.utils.PrefsConstants
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
        return  view
    }
}