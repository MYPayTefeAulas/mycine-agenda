package com.uea.mypay.agenda.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.uea.mypay.agenda.model.Contato
import com.uea.mypay.agenda.R
import com.uea.mypay.agenda.databinding.ActivityTelaInicialBinding
import com.uea.mypay.agenda.ui.fragmentos.AjustesFragment
import com.uea.mypay.agenda.ui.fragmentos.ListaContatosFragment
/**
 * Classe TelaInicialActivity para conter todos os fragments principais: lista de contatos e ajustes
 *
 * Bottom Navigation permite escolher qual tela ver
 *
 * @author Robert Luis Lara Ribeiro
 *<a href="mailto:robertlarabr@gmail.com">robertlarabr@gmail.com</a>
 */
class TelaInicialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaInicialBinding.inflate(layoutInflater)

//        inicializaLista()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ListaContatosFragment())
            .commit()

        binding.bottomNavigationView4.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> {
                    loadFragments(ListaContatosFragment(), FRAGMENT_HOME)
                    true
                }
                R.id.ic_ajustes -> {
                    loadFragments(AjustesFragment(), FRAGMENT_AJUSTES)
                    true
                }
                else ->
                    false
            }
        }

        setContentView(binding.root)
    }

    //Carrega os fragments e os empilha
    private fun loadFragments(fragment: Fragment, tag: String){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment, tag)
            commit()
        }
    }


    companion object {
        private const val FRAGMENT_HOME = "FRAGMENT_HOME"
        private const val FRAGMENT_AJUSTES = "FRAGMENT_AJUSTES"
    }

}
