package com.mvince.compose.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mvince.compose.ui.components.NoNetwork
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Détails") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        if (uiState.offline) {
            NoNetwork()
        } else {
            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = uiState.detail.avatar,
                    contentDescription = null
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = uiState.detail.name.orEmpty(),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        text = uiState.formattedUserSince,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        text = uiState.detail.location.orEmpty(),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}