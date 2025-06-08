package np.com.bimalkafle.firebaseauthdemoapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import np.com.bimalkafle.firebaseauthdemoapp.ui.theme.green_primary
import np.com.bimalkafle.firebaseauthdemoapp.ui.theme.green_mint
import androidx.navigation.NavController
import np.com.bimalkafle.firebaseauthdemoapp.AuthState
import np.com.bimalkafle.firebaseauthdemoapp.AuthViewModel
import np.com.bimalkafle.firebaseauthdemoapp.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column() {
            Box(
                Modifier
                    .height(150.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                // CORES CRIADAS PARA O GRADIENTE
                                green_primary,
                                green_mint
                            )
                        )
                    )
                    .fillMaxWidth()
            ) { // IMAGEM DENTRO DO BOX
                Image(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "Icone da Home",
                    modifier = Modifier
                        .size(250.dp)
                        .offset(y = (50).dp)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        // Conteúdo principal centralizado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Bem-vindo",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = green_primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = " a home page!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = green_primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Você está conectado!",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Button(
                onClick = { authViewModel.signout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = green_primary.copy(alpha = 0.9f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = "DESCONECTAR",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Rodapé fixo no final da tela
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            green_primary,
                            green_mint
                        )
                    )
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "© 2025 | Letícia Guanaes Moreira",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Todos os direitos reservados.",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}