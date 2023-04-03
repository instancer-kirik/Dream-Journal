package com.instance.dreamjournal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.instance.dreamjournal.ui.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun JournalEntryView (
    //viewModel: RoomQuestViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,


    ) {
    Scaffold(

        topBar = { MyToolbar(navigator) },
        floatingActionButton = {
           // AddQuestEntityFloatingActionButtonC()
        }
    ) { padding ->

        /* if (recomposeState.value){
            recomposeState.value =false
        }*/
        /*if (uViewModel.characterDialogState.value) {
            AddQuestEntityOnCharacterAlertDialog()
        }*/
        Column(modifier = Modifier.background(Color.Cyan)) {
            Text("BBBBBBBBBBBB$padding")
        }
    }
}
    @Composable
    fun MyToolbar(navigator: DestinationsNavigator) {
        TopAppBar(
            title = { Text(text = "MAIN") },
            actions = {

                var expanded by remember { mutableStateOf(false) }
                var expanded2 by remember { mutableStateOf(false) }
                Row {
                    OutlinedButton(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            expanded2 = !expanded2
                        }
                    ) {

                        if (expanded2) {
                            Text("TTTTTTTTT")
                        }else Text("DEBUG")
                    }
                }
            }
        )
    }
