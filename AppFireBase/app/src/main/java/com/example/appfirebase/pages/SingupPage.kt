package com.example.appfirebase.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border // Importe o border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
// Não vamos mais usar TextFieldDefaults para as cores, então pode remover o import.
// import androidx.compose.material3.TextFieldDefaults
// import androidx.compose.material3.ExperimentalMaterial3Api // Remover se não for mais necessário

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Para usar Color.White, etc.
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appfirebase.AuthState
import com.example.appfirebase.AuthViewModel
import com.example.appfirebase.ui.theme.TFenceBlack
import com.example.appfirebase.ui.theme.TFenceGreenDark
import com.example.appfirebase.ui.theme.TFenceGreenPrimary
import com.example.appfirebase.ui.theme.TFenceLightGreenBackground
import com.example.appfirebase.ui.theme.TFenceWhite
import androidx.compose.material3.MaterialTheme // Adicione esta importação para MaterialTheme.shapes.small


// @OptIn(ExperimentalMaterial3Api::class) // Remover esta anotação
@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TFenceLightGreenBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "T-Fence - Cadastro",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TFenceGreenDark
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            },
            // REMOVENDO O BLOCO DE CORES QUE ESTAVA DANDO ERRO
            // Em vez disso, usaremos modificadores para o background e borda
            modifier = Modifier
                .background(TFenceWhite, shape = MaterialTheme.shapes.small)
                .border(1.dp, TFenceGreenDark, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Senha")
            },
            // REMOVENDO O BLOCO DE CORES QUE ESTAVA DANDO ERRO
            // Em vez disso, usaremos modificadores para o background e borda
            modifier = Modifier
                .background(TFenceWhite, shape = MaterialTheme.shapes.small)
                .border(1.dp, TFenceGreenDark, shape = MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.signup(email, password)
            }, enabled = authState.value != AuthState.Loading,
            colors = ButtonDefaults.buttonColors(containerColor = TFenceGreenPrimary),
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "Criar conta", fontSize = 20.sp, color = TFenceWhite)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "Já tem uma conta? Entrar", color = TFenceGreenDark)
        }
    }
}