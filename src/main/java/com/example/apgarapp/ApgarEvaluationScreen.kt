package com.example.apgarapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val EvalTeal = Color(0xFF005964)
val ScoreGreen = Color(0xFF2E7D32)
val ScoreYellow = Color(0xFFFBC02D)
val ScoreRed = Color(0xFFD32F2F)

data class ApgarCriterion(
    val name: String,
    val option0: String,
    val option1: String,
    val option2: String
)

@Composable
fun ApgarEvaluationScreen(
    minuto: Int,
    isModoCorrecao: Boolean = false, // <-- Adicionada a flag para controlar o estado de edição
    onBackClick: () -> Unit,
    onSaveClick: (score: Int) -> Unit,
    onMenuClick: () -> Unit,
    onNovoRegistoClick: () -> Unit,
    onHistoricoClick: () -> Unit
) {
    val criteriaList: List<ApgarCriterion> = remember {
        listOf(
            ApgarCriterion(
                name = "Aparência (Cor de pele)",
                option0 = "0 - Pálido / Cianótico",
                option1 = "1 - Extremidades azuis",
                option2 = "2 - Completamente rosado"
            ),
            ApgarCriterion(
                name = "Frequência cardíaca",
                option0 = "0 - Ausente",
                option1 = "1 - < 100 bpm (Lenta)",
                option2 = "2 - >= 100 bpm (Rápida)"
            ),
            ApgarCriterion(
                name = "Resposta a estímulos",
                option0 = "0 - Sem resposta",
                option1 = "1 - Careta / Choro fraco",
                option2 = "2 - Choro forte / Espirro"
            ),
            ApgarCriterion(
                name = "Tónus muscular",
                option0 = "0 - Flácido",
                option1 = "1 - Alguma flexão",
                option2 = "2 - Movimentos ativos"
            ),
            ApgarCriterion(
                name = "Respiração",
                option0 = "0 - Ausente",
                option1 = "1 - Fraca, irregular",
                option2 = "2 - Respiração forte / Choro"
            )
        )
    }

    val selectedScores = remember {
        val map = androidx.compose.runtime.mutableStateMapOf<String, Int>()
        criteriaList.forEach { criterion: ApgarCriterion ->
            map[criterion.name] = -1
        }
        map
    }

    // Cronómetro em tempo real a contar a partir do minuto selecionado
    var segundosDecorridos by remember { mutableStateOf(minuto * 60) }
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            segundosDecorridos++
        }
    }

    val tempoFormatado = String.format("%02d:%02d", segundosDecorridos / 60, segundosDecorridos % 60)

    val pontuacaoTotal: Int = selectedScores.values.filter { score: Int -> score >= 0 }.sum()
    val todosPreenchidos: Boolean = selectedScores.values.none { score: Int -> score == -1 }

    val (scoreColor, scoreLabel) = when {
        !todosPreenchidos -> Color.Gray to "Incompleto"
        pontuacaoTotal >= 7 -> ScoreGreen to "Normal (7-10)"
        pontuacaoTotal >= 4 -> ScoreYellow to "Atenção (4-6)"
        else -> ScoreRed to "Crítico (0-3)"
    }

    val animatedScoreColor by animateColorAsState(targetValue = scoreColor, label = "Cor do Score")

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
                    icon = { Icon(Icons.Default.AddCircleOutline, contentDescription = "Registo") },
                    label = { Text("NOVO REGISTO", fontSize = 9.sp) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedCard(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp),
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
                    text = "APGAR - ${minuto}º MINUTO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = EvalTeal,
                    letterSpacing = 0.8.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = EvalTeal.copy(alpha = 0.15f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Cronómetro",
                            tint = EvalTeal,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = tempoFormatado,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = EvalTeal
                        )
                    }
                }
            }

            // Texto adaptável baseado na imagem image_338867.png para quando está no modo correção
            Text(
                text = if (isModoCorrecao) "CORRIGIR A PONTUAÇÃO PARA CADA CRITERIO" else "SELECIONE A PONTUAÇÃO PARA CADA CRITERIO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Critério", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EvalTeal)
                Text("Pontos", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EvalTeal)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                criteriaList.forEach { criterion: ApgarCriterion ->
                    val selectedValue: Int = selectedScores[criterion.name] ?: -1

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp)
                    ) {
                        Text(
                            text = criterion.name,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(0, 1, 2).forEach { score: Int ->
                                val labelText = when (score) {
                                    0 -> criterion.option0
                                    1 -> criterion.option1
                                    else -> criterion.option2
                                }
                                val isSelected = selectedValue == score

                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { selectedScores[criterion.name] = score },
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSelected) animatedScoreColor else MaterialTheme.colorScheme.surface,
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = if (isSelected) animatedScoreColor else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = labelText,
                                            fontSize = 9.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
                                            textAlign = TextAlign.Center,
                                            lineHeight = 11.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = animatedScoreColor.copy(alpha = 0.08f)),
                border = BorderStroke(1.5.dp, animatedScoreColor)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "PONTUAÇÃO TOTAL",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = animatedScoreColor,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = scoreLabel,
                            fontSize = 11.sp,
                            color = animatedScoreColor
                        )
                    }

                    Text(
                        text = if (todosPreenchidos) "$pontuacaoTotal" else "--",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = animatedScoreColor
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(10.dp).background(ScoreGreen, RoundedCornerShape(2.dp)))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Normal(7-10)", fontSize = 11.sp, color = ScoreGreen, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(10.dp).background(ScoreYellow, RoundedCornerShape(2.dp)))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Atenção(4-6)", fontSize = 11.sp, color = ScoreYellow, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(10.dp).background(ScoreRed, RoundedCornerShape(2.dp)))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Crítico(0-3)", fontSize = 11.sp, color = ScoreRed, fontWeight = FontWeight.Bold)
                }
            }

            if (todosPreenchidos && pontuacaoTotal < 7) {
                Surface(
                    color = ScoreRed.copy(alpha = 0.1f),
                    border = BorderStroke(1.dp, ScoreRed),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "⚠️ AVISO: Score < 7. Recomenda-se oxigenação e monitorização activa imediata.",
                        color = ScoreRed,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        criteriaList.forEach { criterion: ApgarCriterion ->
                            selectedScores[criterion.name] = -1
                        }
                    },
                    modifier = Modifier.weight(1.0f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.5.dp, EvalTeal),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = EvalTeal)
                ) {
                    Text("LIMPAR", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { onSaveClick(pontuacaoTotal) },
                    modifier = Modifier.weight(1.4f).height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EvalTeal),
                    enabled = todosPreenchidos
                ) {
                    Text(
                        // Modificado para exibir "GUARDAR A CORREÇÃO" caso a flag esteja ativa
                        text = if (isModoCorrecao) "GUARDAR A CORREÇÃO" else (if (minuto == 10) "GUARDAR E VOLTAR AO MENU" else "GUARDAR E AVANÇAR"),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}