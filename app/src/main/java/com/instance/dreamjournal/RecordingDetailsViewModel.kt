package com.instance.dreamjournal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instance.dreamjournal.Constants.TAG
import com.instance.dreamjournal.ui.home.MyPlayer

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class RecordingDetailsViewModel@Inject constructor(private val myPlayer: MyPlayer) : ViewModel() {

    val middlePlayer = MutableStateFlow(MiddlePlayer())
    val mHandler = Handler(Looper.getMainLooper())
    var  speechToText: MutableLiveData<String> = MutableLiveData<String>();

    fun  getSpeechToText(): LiveData<String> {
        return speechToText;
    }

    fun setSpeechToText( text:String) {
        speechToText.setValue(text);
    }

    fun startSpeechToText(activity: Activity) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something");
        activity.startActivityForResult(intent, 1);

    }

    // ** Mediaplayer Funs **34

    fun initMediaPlayer(uri: Uri) {

        Log.d("$TAG RecordingDetailsViewModel", "initMediaPlayer: $uri")
        viewModelScope.launch {
            myPlayer.prepareMediaPlayer(uri)
            middlePlayer.emit(
                middlePlayer.value.copy(
                    duration = myPlayer.mediaPlayer.duration,
                    currentPosition = myPlayer.mediaPlayer.currentPosition,
                    isPlaying = false
                ))
        }

        myPlayer.mediaPlayer.setOnCompletionListener {
            viewModelScope.launch {
                middlePlayer.emit(
                    middlePlayer.value.copy(isPlaying = false, currentPosition = myPlayer.mediaPlayer.duration)
                )
            }
        }
    }

    fun rewindTenSeconds() {
        myPlayer.mediaPlayer.seekTo(myPlayer.mediaPlayer.currentPosition - 10000)
        updateCurrentPosition()
    }

    fun forwardTenSeconds() {
        myPlayer.mediaPlayer.seekTo(
            myPlayer.mediaPlayer.currentPosition + (min(
                10000,
                myPlayer.mediaPlayer.duration - myPlayer.mediaPlayer.currentPosition
            ))
        )
        updateCurrentPosition()
    }

    fun playMedia() {
        viewModelScope.launch {
            myPlayer.mediaPlayer.start()

            middlePlayer.emit(
                middlePlayer.value.copy(
                    isPlaying = true
                )
            )
        }

        mHandler.postDelayed(object : Runnable {

            override fun run() {
                if (middlePlayer.value.isPlaying) {
                    mHandler.postDelayed(this, 1000)
                    updateCurrentPosition()
                }
            }
        }, 0)
    }

    fun pauseMedia() {
        viewModelScope.launch {
            myPlayer.mediaPlayer.pause()
            mHandler.removeCallbacksAndMessages(null)
            middlePlayer.emit(
                middlePlayer.value.copy(
                    isPlaying = false
                )
            )
        }

    }

    private fun updateCurrentPosition() {
        viewModelScope.launch {
            middlePlayer.emit(
                middlePlayer.value.copy(
                    currentPosition = myPlayer.mediaPlayer.currentPosition
                )
            )
        }
    }
}

data class MiddlePlayer(
    val isPlaying: Boolean = false,
    val currentPosition: Int = 0,
    val duration: Int = 10
)