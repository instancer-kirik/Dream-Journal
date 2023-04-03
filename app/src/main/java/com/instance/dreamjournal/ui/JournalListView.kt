package com.instance.dreamjournal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.instance.dreamjournal.ui.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@RootNavGraph(start=true)
@Destination
@Composable
fun JournalListView (viewModel: HomeViewModel = hiltViewModel(),
                     navigator: DestinationsNavigator){ val context = LocalContext.current


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
            Column(modifier = Modifier.background(Color.Red)) {
                Text("MMMMMMMMMM$padding")
            }
        }
    }