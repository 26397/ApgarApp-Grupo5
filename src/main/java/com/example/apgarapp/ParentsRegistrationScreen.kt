package com.example.apgarapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val RegisterTeal = Color(0xFF005964)
val RegisterTealDark = Color(0xFF003840)
val RegisterTealLight = Color(0xFF1D7885)

@Composable
fun ParentsRegistrationScreen(
    onBackClick: () -> Unit,
    onSeguinteClick: (idHospitalar: String, paiNome: String, maeNome: String, dataHora: String, salaBloco: String) -> Unit,
    onMenuClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onHistoricoClick: () -> Unit
) {
    val context = LocalContext.current
    
    // Estados do Formulário
    var idHospitalar by remember { mutableStateOf("") }
    var nomePai by remember { mutableStateOf("") }
    var nomeMae by remember { mutableStateOf("") }
    var dataHoraParto by remember { mutableStateOf("") }
    var salaBloco by remember { mutableStateOf("") }

    // Estados de Erro para Validação
    var idHospitalarErro by remember { mutableStateOf(false) }
    var nomeMaeErro by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = onMenuClick,
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                    label = { Text("MENU", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Já estamos no fluxo de registo */ },
                    icon = { Icon(Icons.Default.AddCircleOutline, contentDescription = "Novo Registo") },
                    label = { Text("NOVO REGISTO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onAvaliacaoClick,
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Avaliação") },
                    label = { Text("AVALIAÇÃO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onHistoricoClick,
                    icon = { Icon(Icons.Default.History, contentDescription = "Histórico") },
                    label = { Text("HISTÓRICO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.MoreHoriz, contentDescription = "Mais") },
                    label = { Text("MAIS", fontSize = 10.sp) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedCard(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterStart),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Text(
                    text = "REGISTO DOS PAIS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = RegisterTeal,
                    letterSpacing = 1.2.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "DADOS DOS PAIS",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RegisterTeal,
                    modifier = Modifier.padding(top = 8.dp)
                )

                // ID Hospitalar (Obrigatório por regra de negócio)
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ID Hospitalar *",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (idHospitalarErro) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "(Obrigatório)",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                    OutlinedTextField(
                        value = idHospitalar,
                        onValueChange = { 
                            idHospitalar = it 
                            if (it.isNotEmpty()) idHospitalarErro = false
                        },
                        placeholder = { Text("Ex: HSP-2026-993", color = Color.Gray) },
                        singleLine = true,
                        isError = idHospitalarErro,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RegisterTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                    if (idHospitalarErro) {
                        Text(
                            text = "O ID Hospitalar é estritamente obrigatório.",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // Nome do Pai
                Column {
                    Text(
                        text = "Dados do Pai",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    OutlinedTextField(
                        value = nomePai,
                        onValueChange = { nomePai = it },
                        placeholder = { Text("Nome Completo", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RegisterTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                    Text(
                        text = "Confirme que está correto",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Nome da Mãe
                Column {
                    Text(
                        text = "Dados da Mãe *",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (nomeMaeErro) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
                    )
                    OutlinedTextField(
                        value = nomeMae,
                        onValueChange = { 
                            nomeMae = it 
                            if (it.isNotEmpty()) nomeMaeErro = false
                        },
                        placeholder = { Text("Nome Completo", color = Color.Gray) },
                        singleLine = true,
                        isError = nomeMaeErro,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RegisterTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                    if (nomeMaeErro) {
                        Text(
                            text = "Por favor, introduza o nome da mãe.",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    } else {
                        Text(
                            text = "Confirme que está correto",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp), color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))

                Text(
                    text = "DADOS DO PARTO",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RegisterTeal
                )

                // Data de Nascimento e Hora
                Column {
                    Text(
                        text = "Data de Nascimento e Hora",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    OutlinedTextField(
                        value = dataHoraParto,
                        onValueChange = { dataHoraParto = it },
                        placeholder = { Text("DD/MM/AAAA - HH:MM", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RegisterTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                // Sala/Bloco
                Column {
                    Text(
                        text = "SALA/BLOCO",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    OutlinedTextField(
                        value = salaBloco,
                        onValueChange = { salaBloco = it },
                        placeholder = { Text("Ex: Bloco B, Sala 3", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RegisterTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                    Text(
                        text = "Pode incluir andares/observações.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão Guardar rascunho (Visualmente idêntico à imagem, outline estilizado ou sólido secundário)
            Button(
                onClick = {
                    Toast.makeText(context, "Rascunho guardado com sucesso!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(45.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RegisterTealDark)
            ) {
                Text(
                    text = "Guardar rascunho",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botão SEGUINTE (Arredondado e preenchido)
            Button(
                onClick = {
                    idHospitalarErro = idHospitalar.trim().isEmpty()
                    nomeMaeErro = nomeMae.trim().isEmpty()

                    if (!idHospitalarErro && !nomeMaeErro) {
                        onSeguinteClick(idHospitalar, nomePai, nomeMae, dataHoraParto, salaBloco)
                    } else {
                        Toast.makeText(context, "Por favor, corrija os erros no formulário.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(45.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RegisterTeal)
            ) {
                Text(
                    text = "SEGUINTE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParentsRegistrationScreenPreview() {
    MaterialTheme {
        ParentsRegistrationScreen(
            onBackClick = {},
            onSeguinteClick = { _, _, _, _, _ -> },
            onMenuClick = {},
            onAvaliacaoClick = {},
            onHistoricoClick = {}
        )
    }
}