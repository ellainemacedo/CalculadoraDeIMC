package com.udemy.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.udemy.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btCalcular = binding.btnCalcular
        val mensagem = binding.txtMensagem

        btCalcular.setOnClickListener {

            // .text (Captura o que o usuario digitou)
            // .toString (converte para String)
            val editPeso = binding.edtPeso.text.toString()
            val editAltura = binding.edtAltura.text.toString()

            // Se uma dos campos estiver vazio, mostrar mensagem, senão, chamar o metódo calcular o imc
            if (editPeso.isEmpty()) {
                mensagem.setText("Informe seu Peso")
            } else if (editAltura.isEmpty()) {
                mensagem.setText("Informe sua Altura")
            } else {
                calculoDeIMC()
            }

        }

    }

    private fun calculoDeIMC() {

        val pesoID = binding.edtPeso
        val alturaID = binding.edtAltura
        val resultado = binding.txtMensagem

        // Para converter a string para Numeros Decimais
        // Float (para numeros fracionados)
        val peso = java.lang.Float.parseFloat(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())

        val imc = peso / (altura * altura)

        // TABELA IMC
        val Mensagem = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")

    }

    // Associar Menu
    // Botão direito do mouse -> Generate -> override methods -> onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflarMenu = menuInflater
        inflarMenu.inflate(R.menu.menu_principal, menu)

        return true
    }

    // Sobrescrita de metodo para criar funcionalidade ao botão reset do menu
    // Botão direito do mouse -> Generate -> override methods -> onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{

                val limparEditPeso = binding.edtPeso
                val limparEditAltura = binding.edtAltura
                val limparMensagem = binding.txtMensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }

        }
        return super.onOptionsItemSelected(item)
    }
}