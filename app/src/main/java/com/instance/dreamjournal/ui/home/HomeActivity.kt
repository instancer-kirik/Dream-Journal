/*
package com.instance.dreamjournal.ui.home

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instance.dreamjournal.DreamJournalTheme
import com.instance.dreamjournal.MacawMain
import com.instance.dreamjournal.MyMain
import com.instance.dreamjournal.RecordingDetailsViewModel

import kotlinx.coroutines.ExperimentalCoroutinesApi


//IMPORTED MAIN ACTIVITY XX
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
class HomeActivity : AppCompatActivity() {

    val homeViewModel: HomeViewModel =hiltViewModel()
    private val recordingDetailsViewModel by viewModel<RecordingDetailsViewModel>()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startRecording()
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DreamJournalTheme() {
                Surface(color = colors.background) {
                    MyMain(homeViewModel, recordingDetailsViewModel, this)
                }
            }
        }

        homeViewModel.folderPath = externalCacheDir?.absolutePath
            ?: throw IllegalStateException("externalCacheDir is null! LILLOOO!")
    }

    fun requestAudioRecording() {
        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    private fun startRecording() {
        homeViewModel.startRecording()
    }

}
*/
