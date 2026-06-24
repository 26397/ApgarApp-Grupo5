package com.example.apgarapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SaberMaisTeal = Color(0xFF005964)
val NormalGreen = Color(0xFF2E7D32)
val WarningYellow = Color(0xFFFBC02D)
val DangerRed = Color(0xFFD32F2F)

@Composable
fun SaberMaisScreen(
    onBackClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
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
                    text = "O QUE É O APGAR?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SaberMaisTeal,
                    letterSpacing = 1.2.sp
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaberMaisTeal.copy(alpha = 0.08f)),
                border = BorderStroke(1.dp, SaberMaisTeal.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "O Índice de Apgar é uma avaliação médica rápida realizada nos primeiros minutos de vida de um bebé para determinar o seu estado de saúde geral e a sua adaptação ao ambiente fora do útero. A avaliação é efetuada ao 1º e ao 5º minuto após o nascimento e mede cinco critérios essenciais.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                }
            }

            Text(
                text = "Como funciona a avaliação",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SaberMaisTeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Para cada um dos 5 parâmetros, o recém-nascido recebe uma pontuação de 0, 1 ou 2, resultando numa nota total que pode variar de 0 a 10. O acrónimo APGAR ajuda a memorizar os sinais avaliados:",
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CriterionItem(
                    letter = "A",
                    title = "Aparência (Cor da pele)",
                    description = "Bebés muito pálidos ou azulados recebem 0 ou 1, enquanto um corpo totalmente rosado pontua 2."
                )
                CriterionItem(
                    letter = "P",
                    title = "Pulso (Frequência cardíaca)",
                    description = "Mede os batimentos cardíacos. Abaixo de 100 batimentos por minuto vale 1, e acima de 100 vale 2."
                )
                CriterionItem(
                    letter = "G",
                    title = "Gestos e reações reflexas (Irritabilidade)",
                    description = "Avalia a resposta do bebé a estímulos, como uma leve palmada na sola do pé."
                )
                CriterionItem(
                    letter = "A",
                    title = "Atividade motora (Tónus muscular)",
                    description = "Observa a flexibilidade e os movimentos espontâneos. Músculos flácidos pontuam 0, ao passo que movimentos ativos pontuam 2."
                )
                CriterionItem(
                    letter = "R",
                    title = "Respiração (Esforço respiratório)",
                    description = "Avalia o ritmo e o choro do bebé. Um choro forte e regular garante a pontuação máxima."
                )
            }

            Text(
                text = "Interpretação dos Resultados",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SaberMaisTeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, bottom = 12.dp)
            )

            // 7 a 10 Pontos (Verde)
            ResultCard(
                scoreRange = "7 a 10 pontos",
                label = "Excelente vitalidade",
                color = NormalGreen,
                description = "É o resultado esperado. O bebé apresenta uma excelente vitalidade e boa adaptação à vida extrauterina."
            )

            // 4 a 6 Pontos (Amarelo)
            ResultCard(
                scoreRange = "4 a 6 pontos",
                label = "Atenção clínica",
                color = WarningYellow,
                description = "Indica uma adaptação mais lenta ou algum grau de dificuldade respiratória. Pode sugerir a necessidade de manobras de estimulação ou oxigénio."
            )

            // 0 a 3 Pontos (Vermelho)
            ResultCard(
                scoreRange = "0 a 3 pontos",
                label = "Emergência imediata",
                color = DangerRed,
                description = "Aponta para uma situação de emergência, exigindo intervenção e reanimação neonatal imediata."
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Nota Clínica",
                        tint = SaberMaisTeal,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Quando a pontuação é inferior a 7 no primeiro minuto, o teste é repetido a cada 5 minutos (até aos 20 minutos de vida) para monitorizar a evolução do recém-nascido.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
fun CriterionItem(letter: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(32.dp),
            shape = RoundedCornerShape(6.dp),
            color = SaberMaisTeal,
            contentColor = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = letter, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                text = description,
                fontSize = 13.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun ResultCard(scoreRange: String, label: String, color: Color, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = scoreRange, fontWeight = FontWeight.Bold, color = color, fontSize = 15.sp)
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = color.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = label.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = color,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            Text(
                text = description,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}