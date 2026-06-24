package com.example.apgarapp

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DetailsTeal = Color(0xFF005964)

@Composable
fun BirthDetailsScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onHistoricoClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(selected = false, onClick = onMenuClick, icon = { Icon(Icons.Default.Menu, "Menu") }, label = { Text("MENU", fontSize = 10.sp) })
                NavigationBarItem(selected = false, onClick = onNovoRegistoClick, icon = { Icon(Icons.Default.AddCircleOutline, "Registo") }, label = { Text("NOVO REGISTO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = onAvaliacaoClick, icon = { Icon(Icons.Default.PlayArrow, "Avaliação") }, label = { Text("AVALIAÇÃO", fontSize = 9.sp) })
                NavigationBarItem(selected = true, onClick = onHistoricoClick, icon = { Icon(Icons.Default.History, "Histórico") }, label = { Text("AVALIAÇÃO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.MoreHoriz, "Mais") }, label = { Text("MAIS", fontSize = 10.sp) })
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

            // Cabeçalho Superior
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
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
                    text = "DETALHES DO PARTO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DetailsTeal,
                    letterSpacing = 1.sp
                )
            }

            Text(
                text = "APGAR (1° / 5° / 10° MIN)",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Secção 1: DADOS DO REGISTO
            OutlinedCard(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("DADOS DO REGISTO", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DetailsTeal)

                    DetailRow("ID DO PROCESSO", "HOS-2024-0891")
                    DetailRow("DATA E HORA DO NASCIMENTO", "07/06/2026 14:22")
                    DetailRow("SEXO", "MASCULINO")
                    DetailRow("PESO", "3250 g")
                    DetailRow("IDADE GESTACIONAL", "39+1 SEMANAS")
                    DetailRow("BERÇO", "BERÇO 05")
                }
            }

            // Secção 2: EQUIPA QUE REALIZOU O PARTO
            OutlinedCard(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("EQUIPA QUE REALIZOU O PARTO", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DetailsTeal)

                    Column {
                        Text("MÉDICO(A)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        DetailRow("DR. RICARDO MENDES", "ORDEM N° 54321 📞")
                    }

                    Column {
                        Text("ENFERMEIRO(A)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        DetailRow("ENF. SOFIA ALMEIDA", "ORDEM N° 98765 📞")
                    }

                    Column {
                        Text("REGISTADO POR", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        DetailRow("DR. RICARDO MENDES (Médico)", "07/06/2026 14:22")
                    }

                    Column {
                        Text("ÚLTIMA ALTERAÇÃO", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        DetailRow("ENF. SOFIA ALMEIDA", "07/06/2026 14:30")
                    }
                }
            }

            // Botão Inferior: EXPORTAR DOCUMENTO
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DetailsTeal)
            ) {
                Text("EXPORTAR DOCUMENTO", fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}