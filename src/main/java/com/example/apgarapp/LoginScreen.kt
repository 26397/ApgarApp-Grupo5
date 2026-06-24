package com.example.apgarapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Paleta de Cores baseada no design da imagem image_abac88.png
val HospitalTeal = Color(0xFF005964)
val DarkBackground = Color(0xFF121212)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onEntrarClick: (utilizador: String, passe: String, hospital: String) -> Unit,
    onRecuperarPasseClick: () -> Unit,
    onSSOClick: () -> Unit
) {
    // Estados dos campos de texto e seleções
    var numeroUtilizador by remember { mutableStateOf("") }
    var palavraPasse by remember { mutableStateOf("") }
    var guardarSessao by remember { mutableStateOf(false) }
    
    // Estado do Dropdown de Hospital
    var hospitalSelecionado by remember { mutableStateOf("") }
    var dropdownExpandido by remember { mutableStateOf(false) }
    val hospitais = listOf("Hospital de Santa Maria", "Hospital São João", "Hospital Dona Estefânia", "Maternidade Alfredo da Costa")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho superior: Botão Voltar (<) e Título centralizado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Botão de Voltar estilizado como uma caixa quadrada contornada
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
                    text = "LOGIN",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = HospitalTeal,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ícone de Perfil/Avatar Teal correspondente ao wireframe
            Surface(
                modifier = Modifier.size(90.dp),
                shape = CircleShape,
                color = HospitalTeal.copy(alpha = 0.1f),
                border = BorderStroke(3.dp, HospitalTeal)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar de Utilizador",
                        modifier = Modifier.size(50.dp),
                        tint = HospitalTeal
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Subtítulo centralizado
            Text(
                text = "Aceda à sua conta",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = HospitalTeal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Formulário de Login
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                
                // Campo: nº Utilizador
                Column {
                    Text(
                        text = "nº Utilizador",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = numeroUtilizador,
                        onValueChange = { numeroUtilizador = it },
                        placeholder = { Text("Ex: 123456", color = Color.Gray) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HospitalTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                // Campo: PALAVRA-PASSE
                Column {
                    Text(
                        text = "PALAVRA-PASSE",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = palavraPasse,
                        onValueChange = { palavraPasse = it },
                        placeholder = { Text("Ex: 123456", color = Color.Gray) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HospitalTeal,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    )
                }

                // Campo: HOSPITAL (Seletor Dropdown)
                Column {
                    Text(
                        text = "HOSPITAL",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    
                    ExposedDropdownMenuBox(
                        expanded = dropdownExpandido,
                        onExpandedChange = { dropdownExpandido = !dropdownExpandido },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = if (hospitalSelecionado.isEmpty()) "Selecione o hospital" else hospitalSelecionado,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Abrir lista de hospitais"
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = HospitalTeal,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                                focusedTextColor = if (hospitalSelecionado.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onBackground,
                                unfocusedTextColor = if (hospitalSelecionado.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onBackground
                            )
                        )
                        
                        ExposedDropdownMenu(
                            expanded = dropdownExpandido,
                            onDismissRequest = { dropdownExpandido = false }
                        ) {
                            hospitais.forEach { hospital ->
                                DropdownMenuItem(
                                    text = { Text(hospital) },
                                    onClick = {
                                        hospitalSelecionado = hospital
                                        dropdownExpandido = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Linha de Opções: Guardar Sessão e Recuperar palavra-passe
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Guardar Sessão com seletor circular
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { guardarSessao = !guardarSessao }
                    ) {
                        RadioButton(
                            selected = guardarSessao,
                            onClick = { guardarSessao = !guardarSessao },
                            colors = RadioButtonDefaults.colors(selectedColor = HospitalTeal)
                        )
                        Text(
                            text = "Guardar sessão",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }

                    // Recuperar palavra-passe clicável
                    Text(
                        text = "Recuperar palavra-passe",
                        fontSize = 14.sp,
                        color = HospitalTeal,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onRecuperarPasseClick() }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botão Entrar Primário (Solid Teal)
            Button(
                onClick = {
                    onEntrarClick(numeroUtilizador, palavraPasse, hospitalSelecionado)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp), // Botão arredondado conforme o esboço
                colors = ButtonDefaults.buttonColors(containerColor = HospitalTeal)
            ) {
                Text(
                    text = "ENTRAR",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link Secundário: Autenticação SSO Hospitalar
            Text(
                text = "Autenticação SSO Hospitalar",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = HospitalTeal,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { onSSOClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onBackClick = {},
            onEntrarClick = { _, _, _ -> },
            onRecuperarPasseClick = {},
            onSSOClick = {}
        )
    }
}