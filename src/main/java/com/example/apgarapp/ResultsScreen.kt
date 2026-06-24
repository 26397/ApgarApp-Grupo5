package com.example.apgarapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val ResultsTealColor = Color(0xFF005964)
val TableHeaderBg = Color(0xFFCCCCCC)
val ValueGreen = Color(0xFF2E7D32)
val ValueYellow = Color(0xFFFBC02D)
val ValueRed = Color(0xFFD32F2F)

@Composable
fun ResultsScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onHistoricoClick: () -> Unit,
    onCorrigirAvaliacaoClick: () -> Unit
) {
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
                    selected = false,
                    onClick = onNovoRegistoClick,
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
                    modifier = Modifier.size(40.dp).align(Alignment.CenterStart),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Text(
                    text = "RESULTADO",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ResultsTealColor,
                    letterSpacing = 1.2.sp
                )
            }

            Text(
                text = "RESUMO DOS RESULTADOS",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
            )

            Text(
                text = "DETALHES POR CRITERIO",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(TableHeaderBg).padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Critério", modifier = Modifier.weight(2f).padding(start = 8.dp), fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
                    Text("1º MIN", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                    Text("5º MIN", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                    Text("10º MIN", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                }

                Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))

                TableRowView(criterio = "Aparência(Cor de pele)", v1 = "1", c1 = ValueYellow, v2 = "2", c2 = ValueGreen, v3 = "2", c3 = ValueGreen)
                TableRowView(criterio = "Frequência cardíaca", v1 = "1", c1 = ValueYellow, v2 = "2", c2 = ValueGreen, v3 = "2", c3 = ValueGreen)
                TableRowView(criterio = "Resposta a estímulos", v1 = "0", c1 = ValueRed, v2 = "1", c2 = ValueYellow, v3 = "2", c3 = ValueGreen)
                TableRowView(criterio = "Tono muscular", v1 = "1", c1 = ValueYellow, v2 = "1", c2 = ValueYellow, v3 = "2", c3 = ValueGreen)
                TableRowView(criterio = "Respiração", v1 = "2", c1 = ValueGreen, v2 = "2", c2 = ValueGreen, v3 = "2", c3 = ValueGreen)

                Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TOTAL", modifier = Modifier.weight(2f).padding(start = 8.dp), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text("5", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ValueYellow)
                    Text("8", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ValueGreen)
                    Text("10", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ValueGreen)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onCorrigirAvaliacaoClick,
                    modifier = Modifier.weight(1.1f).height(45.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.5.dp, ResultsTealColor),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ResultsTealColor)
                ) {
                    Text("CORRIGIR AVALIAÇÃO", fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1.1f).height(45.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.5.dp, ResultsTealColor),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ResultsTealColor)
                ) {
                    Text("IMPRIMIR/EXPORTAR", fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(0.65f).height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ResultsTealColor)
            ) {
                Text("ARQUIVAR REGISTO", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TableRowView(criterio: String, v1: String, c1: Color, v2: String, c2: Color, v3: String, c3: Color) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = criterio, modifier = Modifier.weight(2f).padding(start = 8.dp), fontSize = 13.sp)
            Text(text = v1, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = c1)
            Text(text = v2, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = c2)
            Text(text = v3, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = c3)
        }
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f))
    }
}