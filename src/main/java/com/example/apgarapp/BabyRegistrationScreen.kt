package com.example.apgarapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BabyTeal = Color(0xFF005964)
val BabyTealDark = Color(0xFF003840)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabyRegistrationScreen(
    onBackClick: () -> Unit,
    onIniciaApgarClick: (nome: String, dataHora: String, sexo: String, peso: String, idadeGestacional: String, berco: String) -> Unit,
    onMenuClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onHistoricoClick: () -> Unit
) {
    val context = LocalContext.current
    
    var nomeBebe by remember { mutableStateOf("") }
    var dataHoraBebe by remember { mutableStateOf("") }
    var sexoSelecionado by remember { mutableStateOf("") } // "MASCULINO" ou "FEMININO"
    var pesoGrama by remember { mutableStateOf("") }
    var idadeGestacional by remember { mutableStateOf("") }
    var numeroBerco by remember { mutableStateOf("") }

    var dropdownExpandido by remember { mutableStateOf(false) }
    val opcoesIdadeGestacional = listOf(
        "37+0 semanas", "37+6 semanas", "38+0 semanas", "38+6 semanas", 
        "39+0 semanas", "39+1 semanas", "40+0 semanas", "41+0 semanas"
    )

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
                    text = "REGISTO DO BEBÉ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BabyTeal,
                    letterSpacing = 1.2.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "DADOS DO RECÉM-NASCIDO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BabyTeal,
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Dados do Bebé (Nome)
                Column {
                    Text(
                        text = "Dados do Bebe",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    OutlinedTextField(
                        value = nomeBebe,
                        onValueChange = { nomeBebe = it },
                        placeholder = { Text("Nome Completo (se ja atribuido)", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BabyTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                // Data de Nascimento e Hora
                Column {
                    Text(
                        text = "Data de Nascimento e Hora",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    OutlinedTextField(
                        value = dataHoraBebe,
                        onValueChange = { dataHoraBebe = it },
                        placeholder = { Text("DD/MM/AAAA - HH:MM", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BabyTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                Column {
                    Text(
                        text = "SEXO",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { sexoSelecionado = "MASCULINO" }
                        ) {
                            RadioButton(
                                selected = (sexoSelecionado == "MASCULINO"),
                                onClick = { sexoSelecionado = "MASCULINO" },
                                colors = RadioButtonDefaults.colors(selectedColor = BabyTeal)
                            )
                            Text(
                                text = "MASCULINO",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(32.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { sexoSelecionado = "FEMININO" }
                        ) {
                            RadioButton(
                                selected = (sexoSelecionado == "FEMININO"),
                                onClick = { sexoSelecionado = "FEMININO" },
                                colors = RadioButtonDefaults.colors(selectedColor = BabyTeal)
                            )
                            Text(
                                text = "FEMININO",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                // Peso (Grama)
                Column {
                    Text(
                        text = "PESO (G)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    OutlinedTextField(
                        value = pesoGrama,
                        onValueChange = { pesoGrama = it },
                        placeholder = { Text("Ex: 3250", color = Color.Gray) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BabyTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                Column {
                    Text(
                        text = "IDADE GESTACIONAL (SEMANAS)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    
                    ExposedDropdownMenuBox(
                        expanded = dropdownExpandido,
                        onExpandedChange = { dropdownExpandido = !dropdownExpandido },
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                    ) {
                        OutlinedTextField(
                            value = if (idadeGestacional.isEmpty()) "Ex: 39+1" else idadeGestacional,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Selecionar Idade"
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BabyTeal,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                                focusedTextColor = if (idadeGestacional.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onBackground,
                                unfocusedTextColor = if (idadeGestacional.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onBackground
                            )
                        )
                        
                        ExposedDropdownMenu(
                            expanded = dropdownExpandido,
                            onDismissRequest = { dropdownExpandido = false }
                        ) {
                            opcoesIdadeGestacional.forEach { opcao ->
                                DropdownMenuItem(
                                    text = { Text(opcao) },
                                    onClick = {
                                        idadeGestacional = opcao
                                        dropdownExpandido = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Número de Identificação do Berço
                Column {
                    Text(
                        text = "NUMERO DE IDENTIFICAÇÃO DO BERÇO",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    OutlinedTextField(
                        value = numeroBerco,
                        onValueChange = { numeroBerco = it },
                        placeholder = { Text("Ex: Berço 05", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BabyTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (sexoSelecionado.isEmpty()) {
                        Toast.makeText(context, "Por favor, selecione o sexo do bebé.", Toast.LENGTH_SHORT).show()
                    } else {
                        onIniciaApgarClick(nomeBebe, dataHoraBebe, sexoSelecionado, pesoGrama, idadeGestacional, numeroBerco)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BabyTeal)
            ) {
                Text(
                    text = "INICIA AVALIAÇÃO APGAR",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BabyRegistrationScreenPreview() {
    MaterialTheme {
        BabyRegistrationScreen(
            onBackClick = {},
            onIniciaApgarClick = { _, _, _, _, _, _ -> },
            onMenuClick = {},
            onAvaliacaoClick = {},
            onHistoricoClick = {}
        )
    }
}