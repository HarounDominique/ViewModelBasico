package com.example.prueba.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random


//En primer lugar, creamos un view model específico para el proyecto que hereda de ViewModel()

class DadoViewModel : ViewModel() {
    //Declaramos una variable? que almacenará el valor del dado, inicializándolo a 0
    private var _numero by mutableStateOf(0)

    //una forma de acceder de manera segura al valor '_numero'
    val numero get() = _numero

    //Declaramos el funcionamiento de la función encargada de escupir un número
    fun changeNumber() {
        _numero = Random.nextInt(1, 7)
    }
}

//Creamos una función que 'dibujará' el botón y el texto en pantalla
//No termino de entender la declaración de Modifier en esta función????????
@Composable
fun ButtonAndText(number: Int, changeNumber: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = changeNumber) {
            Text(text = "Lanzar dado")
        }
        Text(text = number.toString(), fontSize = 90.sp)
    }
}

@Composable
fun MainScreen() {
    //Declaramos una constante que encarna el view model personalizado
    val viewModel: DadoViewModel = viewModel()
    //Invocamos el método ButtonAndText y pasamos por parámetro el número de la constante
    //que declaramos antes y su función (cambio de número)
    ButtonAndText(number = viewModel.numero, changeNumber = { viewModel.changeNumber() })
}