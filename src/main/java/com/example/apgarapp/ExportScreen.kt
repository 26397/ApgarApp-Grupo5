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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val ExportTeal = Color(0xFF005964)

@Composable
fun ExportScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onAvaliacaoClick: () -> Unit,
    onHistoricoClick: () -> Unit
) {
    var relatorioApgar by remember { mutableStateOf(false) }
    var resumoParto by remember { mutableStateOf(false) }

    var formatoPdf by remember { mutableStateOf(false) }
    var formatoCsv by remember { mutableStateOf(false) }

    var incluirDados by remember { mutableStateOf(false) }
    var incluirAvaliacoes by remember { mutableStateOf(false) }
    var incluirEquipa by remember { mutableStateOf(false) }

    var destinoDispositivo by remember { mutableStateOf(false) }
    var destinoEmail by remember { mutableStateOf(false) }
    var destinoImprimir by remember { mutableStateOf(false) }

    var mostrarPopUp by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(selected = false, onClick = onMenuClick, icon = { Icon(Icons.Default.Menu, "Menu") }, label = { Text("MENU", fontSize = 10.sp) })
                NavigationBarItem(selected = false, onClick = onNovoRegistoClick, icon = { Icon(Icons.Default.AddCircleOutline, "Registo") }, label = { Text("NOVO REGISTO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = onAvaliacaoClick, icon = { Icon(Icons.Default.PlayArrow, "Avaliação") }, label = { Text("AVALIAÇÃO", fontSize = 9.sp) })
                NavigationBarItem(selected = false, onClick = onHistoricoClick, icon = { Icon(Icons.Default.History, "Histórico") }, label = { Text("HISTÓRICO", fontSize = 9.sp) })
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.MoreHoriz, "Mais") }, label = { Text("MAIS", fontSize = 10.sp) })
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
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
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
                    text = "TIPO DE DOCUMENTO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ExportTeal,
                    letterSpacing = 1.sp
                )
            }

            // Mudámos o parâmetro das lamba `{ it -> ... }` para `{ novoValor -> ... }` explicitamente
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column {
                    Text("TIPO DE DOCUMENTO", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    ExportCheckboxRow("RELATÓRIO DE AVALIAÇÃO APGAR", relatorioApgar) { novoValor -> relatorioApgar = novoValor }
                    ExportCheckboxRow("RESUMO DO PARTO", resumoParto) { novoValor -> resumoParto = novoValor }
                }

                Column {
                    Text("FORMATO", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    ExportCheckboxRow("PDF (RECOMENDADO)", formatoPdf) { novoValor -> formatoPdf = novoValor }
                    ExportCheckboxRow("CSV", formatoCsv) { novoValor -> formatoCsv = novoValor }
                }

                Column {
                    Text("CONTEÚDO A INCLUIR", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    ExportCheckboxRow("DADOS DO RECÉM-NASCIDO E PAIS", incluirDados) { novoValor -> incluirDados = novoValor }
                    ExportCheckboxRow("AVALIAÇÕES APGAR (1º, 5º E 10º MIN)", incluirAvaliacoes) { novoValor -> incluirAvaliacoes = novoValor }
                    ExportCheckboxRow("EQUIPA DO PARTO (MÉDICO E ENFERMEIRO)", incluirEquipa) { novoValor -> incluirEquipa = novoValor }
                }

                Column {
                    Text("DESTINO", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    ExportCheckboxRow("GUARDAR NO DISPOSITIVO", destinoDispositivo) { novoValor -> destinoDispositivo = novoValor }
                    ExportCheckboxRow("ENVIAR POR EMAIL", destinoEmail) { novoValor -> destinoEmail = novoValor }
                    ExportCheckboxRow("IMPRIMIR", destinoImprimir) { novoValor -> destinoImprimir = novoValor }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = { mostrarPopUp = true },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ExportTeal)
            ) {
                Text("EXPORTAR DOCUMENTO", fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Segurança",
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "DOCUMENTO EM CONFORMIDADE COM O RGPD",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (mostrarPopUp) {
                AlertDialog(
                    onDismissRequest = { mostrarPopUp = false },
                    title = { Text(text = "Sucesso", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                    text = { Text(text = "Exportado com sucesso", fontSize = 14.sp) },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                mostrarPopUp = false // Fecha o pop-up
                                onBackClick()        // Regressa ao Menu Principal através do callback nativo
                            }
                        ) {
                            Text(text = "OK", fontWeight = FontWeight.Bold, color = ExportTeal)
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    containerColor = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
fun ExportCheckboxRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(36.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = ExportTeal)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}