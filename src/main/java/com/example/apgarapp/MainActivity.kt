package com.example.apgarapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apgarapp.ui.theme.ApgarAppTheme

enum class AppScreen {
    Opening,
    Login,
    Menu,
    ParentsRegistration,
    BabyRegistration,
    Apgar1Min,
    Apgar5Min,
    Apgar10Min,
    SaberMais,
    Results,
    History,
    BirthDetails,
    Export
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApgarAppTheme(darkTheme = true) {

                var currentScreen by remember { mutableStateOf(AppScreen.Opening) }
                var isModoCorrecao by remember { mutableStateOf(false) }

                var idHospitalarGuardado by remember { mutableStateOf("") }
                var nomePaiGuardado by remember { mutableStateOf("") }
                var nomeMaeGuardado by remember { mutableStateOf("") }
                var dataHoraPartoGuardado by remember { mutableStateOf("") }
                var salaBlocoGuardado by remember { mutableStateOf("") }

                var nomeBebeGuardado by remember { mutableStateOf("") }
                var dataHoraBebeGuardado by remember { mutableStateOf("") }
                var sexoBebeGuardado by remember { mutableStateOf("") }
                var pesoBebeGuardado by remember { mutableStateOf("") }
                var idadeGestacionalBebeGuardado by remember { mutableStateOf("") }
                var bercoBebeGuardado by remember { mutableStateOf("") }

                var score1Minuto by remember { mutableStateOf(0) }
                var score5Minutos by remember { mutableStateOf(0) }
                var score10Minutos by remember { mutableStateOf(0) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        AppScreen.Opening -> {
                            OpeningScreen(
                                onEntrarClick = { currentScreen = AppScreen.Login },
                                onSaberMaisClick = { currentScreen = AppScreen.SaberMais }
                            )
                        }
                        AppScreen.SaberMais -> {
                            SaberMaisScreen(onBackClick = { currentScreen = AppScreen.Opening })
                        }
                        AppScreen.Login -> {
                            LoginScreen(
                                onBackClick = { currentScreen = AppScreen.Opening },
                                onEntrarClick = { utilizador, passe, hospital ->
                                    if (utilizador.isEmpty() || passe.isEmpty() || hospital.isEmpty()) {
                                        Toast.makeText(this@MainActivity, "Preencha os campos!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        currentScreen = AppScreen.Menu
                                    }
                                },
                                onRecuperarPasseClick = { },
                                onSSOClick = { }
                            )
                        }
                        AppScreen.Menu -> {
                            MenuScreen(
                                onBackClick = { currentScreen = AppScreen.Login },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onAvaliacaoApgarClick = { currentScreen = AppScreen.ParentsRegistration },
                                onResultadosClick = { currentScreen = AppScreen.Results },
                                onHistoricosClick = {currentScreen = AppScreen.History },
                                onExportarClick = {currentScreen = AppScreen.Export }
                            )
                        }
                        AppScreen.ParentsRegistration -> {
                            ParentsRegistrationScreen(
                                onBackClick = { currentScreen = AppScreen.Menu },
                                onSeguinteClick = { id, pai, mae, data, sala ->
                                    idHospitalarGuardado = id
                                    nomePaiGuardado = pai
                                    nomeMaeGuardado = mae
                                    dataHoraPartoGuardado = data
                                    salaBlocoGuardado = sala
                                    currentScreen = AppScreen.BabyRegistration
                                },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onAvaliacaoClick = { },
                                onHistoricoClick = { }
                            )
                        }
                        AppScreen.BabyRegistration -> {
                            BabyRegistrationScreen(
                                onBackClick = { currentScreen = AppScreen.ParentsRegistration },
                                onIniciaApgarClick = { nome, data, sexo, peso, idade, berco ->
                                    nomeBebeGuardado = nome
                                    dataHoraBebeGuardado = data
                                    sexoBebeGuardado = sexo
                                    pesoBebeGuardado = peso
                                    idadeGestacionalBebeGuardado = idade
                                    bercoBebeGuardado = berco
                                    currentScreen = AppScreen.Apgar1Min
                                },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onAvaliacaoClick = { },
                                onHistoricoClick = { }
                            )
                        }
                        AppScreen.Apgar1Min -> {
                            ApgarEvaluationScreen(
                                minuto = 1,
                                isModoCorrecao = isModoCorrecao,
                                onBackClick = {
                                    if (isModoCorrecao) {
                                        isModoCorrecao = false
                                        currentScreen = AppScreen.Results
                                    } else {
                                        currentScreen = AppScreen.BabyRegistration
                                    }
                                },
                                onSaveClick = { score ->
                                    score1Minuto = score
                                    currentScreen = AppScreen.Apgar5Min
                                },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { }
                            )
                        }
                        AppScreen.Apgar5Min -> {
                            ApgarEvaluationScreen(
                                minuto = 5,
                                isModoCorrecao = isModoCorrecao,
                                onBackClick = { currentScreen = AppScreen.Apgar1Min },
                                onSaveClick = { score ->
                                    score5Minutos = score
                                    currentScreen = AppScreen.Apgar10Min
                                },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { }
                            )
                        }
                        AppScreen.Apgar10Min -> {
                            ApgarEvaluationScreen(
                                minuto = 10,
                                isModoCorrecao = isModoCorrecao,
                                onBackClick = { currentScreen = AppScreen.Apgar5Min },
                                onSaveClick = { score ->
                                    score10Minutos = score
                                    if (isModoCorrecao) {
                                        isModoCorrecao = false
                                        currentScreen = AppScreen.Results
                                        Toast.makeText(this@MainActivity, "Todas as correções foram guardadas!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        currentScreen = AppScreen.Menu
                                        Toast.makeText(this@MainActivity, "Avaliação guardada com sucesso!", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { }
                            )
                        }
                        AppScreen.Results -> {
                            ResultsScreen(
                                onBackClick = { currentScreen = AppScreen.Menu },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onAvaliacaoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { },
                                onCorrigirAvaliacaoClick = {
                                    isModoCorrecao = true
                                    currentScreen = AppScreen.Apgar1Min
                                }
                            )
                        }
                        AppScreen.History -> {
                            HistoryScreen(
                                onBackClick = { currentScreen = AppScreen.Menu },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onAvaliacaoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onRowClick = { currentScreen = AppScreen.BirthDetails } // Navega ao clicar em qualquer registo da lista semanal
                            )
                        }

                        AppScreen.BirthDetails -> {
                            BirthDetailsScreen(
                                onBackClick = { currentScreen = AppScreen.History }, // Retrocede corretamente para a listagem do histórico
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onAvaliacaoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { currentScreen = AppScreen.History }
                            )
                        }
                        AppScreen.Export -> {
                            ExportScreen(
                                onBackClick = { currentScreen = AppScreen.Menu },
                                onMenuClick = { currentScreen = AppScreen.Menu },
                                onNovoRegistoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onAvaliacaoClick = { currentScreen = AppScreen.ParentsRegistration },
                                onHistoricoClick = { currentScreen = AppScreen.History }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpeningScreen(onEntrarClick: () -> Unit, onSaberMaisClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.apgar_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp).padding(bottom = 32.dp)
            )
            Text(text = "ApgarApp", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onEntrarClick, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                Text("ENTRAR", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = onSaberMaisClick, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                Text("SABER MAIS", fontSize = 18.sp)
            }
        }
    }
}