package com.example.apgarapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val HistoryTeal = Color(0xFF005964)
val HistoryTabSelectedBg = Color(0xFF7CB2DD)

// Cores dos Scores para as barras do gráfico
val BarGreen = Color(0xFF2E7D32)
val BarYellow = Color(0xFFFBC02D)
val BarRed = Color(0xFFD32F2F)

data class PartoRegistro(
    val score: String,
    val scoreColor: Color,
    val dataHora: String,
    val idHospitalar: String,
    val nomeMae: String
)

@Composable
fun HistoryScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onRowClick: () -> Unit
) {
    // 0 = Semanal, 1 = Mensal
    var activeTab by remember { mutableStateOf(0) }

    val listaPartos = remember {
        listOf(
            PartoRegistro("8/10", BarGreen, "07/05/2026 14:22", "HOS-2026-001", "Ana Ferreira"),
            PartoRegistro("10/10", BarGreen, "02/05/2026 08:22", "HOS-2026-055", "Maria Santos"),
            PartoRegistro("6/10", BarYellow, "02/05/2026 20:35", "HOS-2026-008", "Lucas Martins"),
            PartoRegistro("9/10", BarGreen, "28/04/2026 08:22", "HOS-2026-087", "João Oliveira"),
            PartoRegistro("4/10", BarYellow, "26/04/2026 10:15", "HOS-2026-065", "Beatriz Almeida")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(selected = false, onClick = onMenuClick, icon = { Icon(Icons.Default.Menu, "Menu") }, label = { Text("MENU", fontSize = 10.sp) })
                NavigationBarItem(selected = false, onClick = onNovoRegistoClick, icon = { Icon(Icons.Default.AddCircleOutline, "Registo") }, label = { Text("NOVO REGISTO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = onAvaliacaoClick, icon = { Icon(Icons.Default.PlayArrow, "Avaliação") }, label = { Text("AVALIAÇÃO", fontSize = 9.sp) })
                NavigationBarItem(selected = true, onClick = { }, icon = { Icon(Icons.Default.History, "Histórico") }, label = { Text("HISTÓRICO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.MoreHoriz, "Mais") }, label = { Text("MAIS", fontSize = 10.sp) })
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

            // Cabeçalho de Título Dinâmico
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedCard(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp).align(Alignment.CenterStart),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }

                Text(
                    text = if (activeTab == 0) "HISTÓRICO SEMANAL" else "HISTÓRICO MENSAL",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HistoryTeal,
                    letterSpacing = 1.sp
                )
            }

            // Alternador de Abas (Semanal / Mensal)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(if (activeTab == 0) HistoryTabSelectedBg else Color.Transparent, shape = RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
                        .clickable { activeTab = 0 },
                    contentAlignment = Alignment.Center
                ) {
                    Text("SEMANAL", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (activeTab == 0) Color.White else MaterialTheme.colorScheme.onBackground)
                }
                Box(modifier = Modifier.fillMaxHeight().width(1.dp).background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(if (activeTab == 1) HistoryTabSelectedBg else Color.Transparent, shape = RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp))
                        .clickable { activeTab = 1 },
                    contentAlignment = Alignment.Center
                ) {
                    Text("MENSAL", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (activeTab == 1) Color.White else MaterialTheme.colorScheme.onBackground)
                }
            }

            // Seletor de Período Cronológico
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) { Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Anterior", modifier = Modifier.size(16.dp)) }
                Text(text = if (activeTab == 0) "26 ABR - 1 MAI 2026" else "MAIO DE 2026", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = {}) { Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Seguinte", modifier = Modifier.size(16.dp)) }
            }

            Text(
                text = "RESUMO DOS RESULTADOS",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            if (activeTab == 0) {
                // VISÃO SEMANAL: Tabela de Partos Registados
                Column(modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f))) {
                    listaPartos.forEach { parto ->
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable { onRowClick() }.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = parto.score, color = parto.scoreColor, fontWeight = FontWeight.Bold, fontSize = 13.sp, modifier = Modifier.width(50.dp), textAlign = TextAlign.Center)
                            Box(modifier = Modifier.width(1.dp).height(28.dp).background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)))
                            Column(modifier = Modifier.weight(2f).padding(start = 8.dp)) {
                                Text(parto.dataHora, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(parto.idHospitalar, fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                            }
                            Box(modifier = Modifier.width(1.dp).height(28.dp).background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)))
                            Text(text = parto.nomeMae, fontSize = 12.sp, modifier = Modifier.weight(1.5f).padding(start = 8.dp))
                            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Detalhes", modifier = Modifier.padding(horizontal = 4.dp))
                        }
                        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = HistoryTeal)) {
                    Text("VER MAIS PARTOS", fontWeight = FontWeight.Bold, color = Color.White)
                }
            } else {
                // VISÃO MENSAL: Gráfico Resumo dos Resultados (Barras Horizontais Dinâmicas)
                Text(
                    text = "DISTRIBUIÇÃO POR PONTUAÇÃO (APGAR 5° MIN)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = HistoryTeal,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 24.dp),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Barra 1: Normal (7-10) -> Exemplo: 14 Partos
                    GraphicBarRow(label = "Normal (7-10)", count = 14, percentage = 0.7f, color = BarGreen)

                    // Barra 2: Atenção (4-6) -> Exemplo: 4 Partos
                    GraphicBarRow(label = "Atenção (4-6)", count = 4, percentage = 0.2f, color = BarYellow)

                    // Barra 3: Crítico (0-3) -> Exemplo: 2 Partos
                    GraphicBarRow(label = "Crítico (0-3)", count = 2, percentage = 0.1f, color = BarRed)
                }

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HistoryTeal)
                ) {
                    Text("VER MAIS DETALHES DO MÊS", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun GraphicBarRow(label: String, count: Int, percentage: Float, color: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "$count partos", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Spacer(modifier = Modifier.height(6.dp))
        // Contentor da Barra de Progresso do Gráfico
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(percentage) // Define a largura proporcional ao valor
                    .fillMaxHeight()
                    .background(color, shape = RoundedCornerShape(4.dp))
            )
        }
    }
}