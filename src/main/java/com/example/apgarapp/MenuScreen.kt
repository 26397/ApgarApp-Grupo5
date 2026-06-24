package com.example.apgarapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores degradê baseadas no design da imagem image_ac19da.png
val MenuTealDark = Color(0xFF003840)
val MenuTealMediumDark = Color(0xFF004F5A)
val MenuTealMedium = Color(0xFF1D7885)
val MenuTealLight = Color(0xFF2A8B99)
val MenuTealSoft = Color(0xFF46A2B0)

@Composable
fun MenuScreen(
    onBackClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onAvaliacaoApgarClick: () -> Unit,
    onResultadosClick: () -> Unit,
    onHistoricosClick: () -> Unit,
    onExportarClick: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                    label = { Text("MENU", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { 
                        selectedTab = 1
                        onNovoRegistoClick()
                    },
                    icon = { Icon(Icons.Default.AddCircleOutline, contentDescription = "Novo Registo") },
                    label = { Text("NOVO REGISTO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { 
                        selectedTab = 2
                        onAvaliacaoApgarClick()
                    },
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Avaliação") },
                    label = { Text("AVALIAÇÃO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { 
                        selectedTab = 3
                        onHistoricosClick()
                    },
                    icon = { Icon(Icons.Default.History, contentDescription = "Histórico") },
                    label = { Text("HISTÓRICO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 4,
                    onClick = { selectedTab = 4 },
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                // Botão de Voltar quadrado e contornado tal como no wireframe
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
                    text = "MENU PRINCIPAL",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = HospitalTeal,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botão 1: NOVO REGISTO (Escuro)
                Button(
                    onClick = onNovoRegistoClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MenuTealDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("NOVO REGISTO", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                // Botão 2: AVALIAÇÃO APGAR (Médio-Escuro)
                Button(
                    onClick = onAvaliacaoApgarClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MenuTealMediumDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("AVALIAÇÃO APGAR", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                // Botão 3: RESULTADOS (Médio)
                Button(
                    onClick = onResultadosClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MenuTealMedium),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("RESULTADOS", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                // Botão 4: HISTÓRICOS (Claro)
                Button(
                    onClick = onHistoricosClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MenuTealLight),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("HISTÓRICOS", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                // Botão 5: EXPORTAR (Suave)
                Button(
                    onClick = onExportarClick,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MenuTealSoft),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("EXPORTAR", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone de Perfil Circular
                    Surface(
                        modifier = Modifier.size(54.dp),
                        shape = CircleShape,
                        color = HospitalTeal.copy(alpha = 0.1f),
                        border = BorderStroke(2.dp, HospitalTeal)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profissional",
                                modifier = Modifier.size(32.dp),
                                tint = HospitalTeal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Detalhes do Profissional (conforme imagem image_ac19da.png)
                    Column {
                        Text(
                            text = "Dr. Carlos Alberto",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Médico",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "Bloco de Partos",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MaterialTheme {
        MenuScreen(
            onBackClick = {},
            onNovoRegistoClick = {},
            onAvaliacaoApgarClick = {},
            onResultadosClick = {},
            onHistoricosClick = {},
            onExportarClick = {}
        )
    }
}