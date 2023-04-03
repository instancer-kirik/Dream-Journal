
package com.instance.dreamjournal.ui.home

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instance.dreamjournal.AppDatabase
import com.instance.dreamjournal.Constants.TAG


import com.instance.dreamjournal.GeneralRepository
import com.instance.dreamjournal.Recording
import com.instance.dreamjournal.generateRecordingName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    val appDatabase: AppDatabase, val homeRepository: GeneralRepository): ViewModel() {
    var recorder: MediaRecorder? = null
    var recordings = MutableStateFlow<List<Recording>>(emptyList())
    val recordingState = MutableStateFlow(false)
    var selectedColor = mutableStateOf(Color(0,0,0))
    var openColorPickerState= mutableStateOf(false)
    var context: Context? = null
    private var folderPath: String = ""
    private var folderName: String = "my_recordings"
    var selectedRecording = MutableStateFlow<Recording?>(null)

    // ** Recording Funs **
    fun readRecordings() {
        Log.d("$TAG HomeViewModel", "readRecordings: $folderPath")
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(homeRepository.getRecordings("$folderPath/$folderName"))
        }
    }
    fun setSelectedRecording(recording: Recording){
        selectedRecording.value = recording
    }
    fun setContextAndFolderPath(context: Context, folderPath: String){
        this.context = context
        this.folderPath = context.getExternalFilesDir(/*folderPath*/null)?.path+""

    }
    fun sortRecordingsByDuration() {

        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(
                recordings.value.sortedBy {
                    it.duration
                })
        }

    }

    fun sortRecordingsByDate() {
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(
                recordings.value.sortedBy {
                    it.date
                })
        }

    }



    fun startRecording(context: Context) {
        //setContextAndFolderPath(context: Context, )
        folderPath= context.getExternalFilesDir(null)?.path+""
        val fileName = generateRecordingName("$folderPath/$folderName")
        Log.d("HomeViewModel", "path $folderPath filename: $fileName")

        val RECORDER_SAMPLE_RATE = 44100
        /*val exoPlayer = remember {
            ExoPlayer.Builder(context)
                .build()
                .apply {
                    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                    val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                        context,
                        defaultDataSourceFactory
                    )
                    val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(uri))

                    setMediaSource(source)
                    prepare()
                }
        }

        exoPlayer.playWhenReady = true
        exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

        DisposableEffect(
            AndroidView(factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
            })
        ) {
            onDispose { exoPlayer.release() }
        }
*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            recorder = MediaRecorder(context).apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setOutputFile(fileName)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(16 * 44100)
                setAudioSamplingRate(44100)
            }
        }else {
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setOutputFile(fileName)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(16 * 44100)
                setAudioSamplingRate(44100)
            }
        }
        //player = ExoPlayer(context).Builder(context).build()


        try {
            recorder?.prepare()
            recorder?.start()
            viewModelScope.launch {
                recordingState.emit(true)
            }
        } catch (e: IOException) {
            Log.d("HomeViewModel", " record prepare() failed $e")
            viewModelScope.launch {
                recordingState.emit(false)
            }
        }
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            reset()
            release()
        }
        recorder = null
        viewModelScope.launch {
            recordingState.emit(false)
        }

        readRecordings()
    }


}