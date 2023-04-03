package com.instance.dreamjournal.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.instance.dreamjournal.*
import com.instance.dreamjournal.Constants.TAG
import com.instance.dreamjournal.R
import com.instance.dreamjournal.destinations.RecordingDetailsScreenDestination
import com.instance.dreamjournal.ui.RecordingDetailsDisplay

import com.instance.dreamjournal.ui.components.RecordButton
import com.instance.dreamjournal.ui.components.RecordingCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.File

var recomposeState = mutableStateOf(false)
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
@Destination
@RootNavGraph(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
    rviewModel: RecordingDetailsViewModel = hiltViewModel(),
    //activity: MainActivity,
    context: Context = LocalContext.current,
    recordingList: MutableList<Recording> =
        viewModel.recordings.collectAsState().value.toMutableList(),
    sheetState: BottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    ),
    onClick: (item: Recording) -> Unit = { item ->
        Log.d(TAG, "HomeScreen: $item")
        viewModel.selectedRecording.value = item
        rviewModel.initMediaPlayer(
            Uri.fromFile(
                viewModel.selectedRecording.value?.path?.let { File(it) }
            )
        )

        /*navigator.navigate(
            "${RecordingDetailsScreenDestination.route}/${item.readableDayTime}/${item.duration}/${item.path}"
        )*/
    },
) {
    val scope = rememberCoroutineScope()
//    val sheetState = rememberBottomSheetState(
//        initialValue = BottomSheetValue.Collapsed
//    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    var expandedStatus = remember { mutableStateOf(false) }
    //val viewModel: MainViewModel = hiltViewModel()
    val recordingDetailsViewModel: RecordingDetailsViewModel = hiltViewModel()
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startRecording(viewModel, context = context)
    }
    if (recomposeState.value){
        recomposeState.value =false
    }
    viewModel.context= context
    val modifier = Modifier
    val tabHeight = 36.dp
    val recordingState = viewModel.recordingState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (recordingList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_results),
                    modifier = Modifier.padding(horizontal = 32.dp),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Text(
                    text = "No results found",
                    style = typography.body1,
                    color = colors.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        } else {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {
                    Box(modifier = modifier
                        .fillMaxWidth()
                        .height(tabHeight)
                        .background(Color.Transparent)) {
                        Box(
                            modifier = modifier
                                .zIndex(4f)
                                .clip(
                                    RoundedCornerShape(
                                        (tabHeight / 2) ?: 0.dp,
                                        (tabHeight / 2) ?: 0.dp,
                                        0.dp,
                                        0.dp
                                    )
                                )
                                .width(100.dp)
                                .height(tabHeight)
                                .align(Alignment.Center)
                                .background(color = darkGrey)

                            ,
                            contentAlignment = Alignment.Center
                        ){
                            Text("^^^",color = Color.Cyan)
                        }
                        Box(modifier = modifier
                            .align(Alignment.TopCenter)
                            .height(tabHeight / 2 + 2.dp)
                            .width(130.dp)
                            .background(colors.background)
                            .zIndex(1f))
                        Row(modifier = modifier
                            .clip(
                                RoundedCornerShape(
                                    (tabHeight / 2) ?: 0.dp,
                                    (tabHeight / 2) ?: 0.dp,
                                    tabHeight / 2,
                                    tabHeight / 2
                                )
                            )
                            .height(tabHeight)
                            .align(alignment = Alignment.Center)
                            .background(darkGrey)) {

                            Box(
                                modifier = modifier
                                    .size(tabHeight)
                                    .clip(
                                        RoundedCornerShape(
                                            (tabHeight / 2) ?: 0.dp,
                                            (tabHeight / 2) ?: 0.dp,
                                            tabHeight / 2,
                                            tabHeight / 2
                                        )
                                    )
                                    .background(colors.background)
                                    .zIndex(.3f))
                            Box(
                                modifier = modifier
                                    .zIndex(4f)
                                    .clip(
                                        RoundedCornerShape(
                                            (tabHeight / 2) ?: 0.dp,
                                            (tabHeight / 2) ?: 0.dp, 0.dp, 0.dp
                                        )
                                    )
                                    .width(100.dp)
                                    .height(tabHeight)
                                    .align(Alignment.CenterVertically)
                                    .background(color = purple400)

                                ,
                                contentAlignment = Alignment.Center
                            ){
                                Text("xxx",color = Color.Blue)
                            }
                            Box(
                                modifier = Modifier
                                    .size(tabHeight)
                                    .clip(
                                        RoundedCornerShape(
                                            (tabHeight / 2) ?: 0.dp,
                                            (tabHeight / 2) ?: 0.dp,
                                            tabHeight / 2,
                                            tabHeight / 2,
                                        )
                                    )
                                    .background(colors.background)
                            )
                        }}
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .background(color = viewModel.selectedColor.value),
                        contentAlignment = Alignment.Center
                    ) {/////////////////////////////////////BOTTOM SHEET////////////////////////////////////////

                       RecordingDetailsDisplay(recording =viewModel.selectedRecording.value?: emptyRecording() , viewModel = rviewModel) {

                       }

                        }



                },
                sheetBackgroundColor = Color.Transparent,
                sheetPeekHeight = 90.dp
            )   {
            LazyColumn {
                itemsIndexed(recordingList) { index, recording ->
                    when (recording) {
                        recordingList.last() -> {
                            RecordingCard(recording, onClickListener = { onClick(recording)
                            })
                            Box(Modifier.height(80.dp))
                        }
                        recordingList.first() -> {
                            Column {
                                Box {
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.homescreen_background),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(bottom = 16.dp)
                                            .clip(
                                                shape = RoundedCornerShape(
                                                    bottomEnd = 16.dp,
                                                    bottomStart = 16.dp,
                                                )
                                            )
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(start = 32.dp, bottom = 64.dp)
                                    ) {
                                        Text(
                                            text = "Hey,",
                                            style = typography.h5,
                                            color = colors.onSurface
                                        )
                                        Text(
                                            text = "Dreamer",
                                            style = typography.h2,
                                            color = colors.onSurface
                                        )
                                    }
                                    SortButton(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(end = 16.dp, bottom = 32.dp),
                                        onClick = {
                                            /*val bottomSheetFragment =
                                                SortMethodListSheetFragment()
                                            bottomSheetFragment.show(
                                                activity.supportFragmentManager,
                                                bottomSheetFragment.tag)*/

                                        })

                                }
                            }
                            RecordingCard(recording, onClickListener = {



                                onClick(recording)

                                scope.launch { sheetState.expand()  }




                            })
                        }
                        else -> {
                            RecordingCard(recording, onClickListener = { onClick(recording)
                                scope.launch { sheetState.expand()  }})
                        }
                    }
                }
            }}
        }
        if (sheetState.isCollapsed){//expandedStatus.value){
        RecordButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            isRecording = recordingState.value,
            onClickListener = onRecordPressed(
                //activity = activity,
                viewModel = viewModel,
                requestPermissionLauncher= requestPermissionLauncher
            )
        )}

    }
}


fun startRecording(viewModel: HomeViewModel, context: Context) {
    viewModel.startRecording(context = context)
}

@Composable
private fun SortButton(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        elevation = 2.dp,
        color = if (colors.isLight) {
            surfaceWhite
        } else {
            colors.surface
        },
        modifier = modifier
            .clip(shapes.small)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
        ) {
            Text(
                text = "Sort",
                style = typography.button,
                color = colors.primary,
                modifier = Modifier.padding(end = 4.dp)
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_sort),
                colorFilter = ColorFilter.tint(colors.primary),
                contentDescription = null
            )
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi

private fun onRecordPressed(
    //activity: MainActivity,
    viewModel: HomeViewModel,
    requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>
): () -> Unit {
    return {
        val isRecording = viewModel.recordingState.value

        // haptic feedback

        if (isRecording) {
           viewModel.stopRecording()
        } else {


            requestAudioRecording(viewModel,requestPermissionLauncher)
        }

    }
}

fun requestAudioRecording(viewModel: HomeViewModel,requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>) {

    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (viewModel.context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.RECORD_AUDIO
                    )
                } == PackageManager.PERMISSION_GRANTED
            ) {
                startRecording(viewModel, viewModel.context!!)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)// OLD ONE
            }
        } else {
            viewModel.context?.let { startRecording(viewModel, it) }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
