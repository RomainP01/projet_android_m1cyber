package com.sauce_hannibal.projet_android_m1cyber.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sauce_hannibal.projet_android_m1cyber.ui.components.NoNetwork
import com.sauce_hannibal.projet_android_m1cyber.ui.users.component.UserItem

@Composable
fun UsersScreen(onUserClick: (String) -> Unit) {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.offline) {
        NoNetwork()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(uiState.list) { item ->
                UserItem(item = item, onUserClick = onUserClick)
            }
        }
    }
}