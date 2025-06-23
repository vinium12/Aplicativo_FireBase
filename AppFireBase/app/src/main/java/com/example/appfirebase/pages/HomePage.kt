package com.example.appfirebase.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // Para configurar a cor do botão
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Importar Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appfirebase.AuthState
import com.example.appfirebase.AuthViewModel
import com.example.appfirebase.ui.theme.TFenceBlack // Importe suas novas cores
import com.example.appfirebase.ui.theme.TFenceGreenDark
import com.example.appfirebase.ui.theme.TFenceGreenPrimary
import com.example.appfirebase.ui.theme.TFenceLightGreenBackground
import com.example.appfirebase.ui.theme.TFenceWhite


@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
          }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TFenceLightGreenBackground), // Fundo verde claro
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título "T-Fence"
        Text(
            text = "T-Fence",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = TFenceGreenDark
        )
        Spacer(modifier = Modifier.height(20.dp))

        // "Home Page" como um subtítulo ou informação adicional
        Text(text = "Página Inicial", fontSize = 28.sp, color = TFenceBlack)
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                authViewModel.signout()
            },
            colors = ButtonDefaults.buttonColors(containerColor = TFenceGreenPrimary), // Cor do botão
            modifier = Modifier.height(50.dp) // Altura para o botão
        ) {
            Text(text = "Sair", fontSize = 20.sp, color = TFenceWhite) // Cor do texto do botão
        }
    }
}