package com.instance.dreamjournal.ui.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.instance.dreamjournal.*
import com.instance.dreamjournal.Constants.TAG
import com.instance.dreamjournal.ui.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecordingListBottomSheet(viewModel: HomeViewModel, v2:RecordingDetailsViewModel, onClick: (item: Recording) -> Unit) {
    val context = LocalContext.current
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    //val selectedRecording = remember { mutableStateOf(Recording()) }
    //var selectedDateStuff by remember { mutableStateOf(listOf(Event())) }
    var noteText by remember { mutableStateOf("Hello from sheet") }
    val scope = rememberCoroutineScope()
    val tabHeight = 36.dp
    /*var selectedOption1 by remember {
        mutableStateOf(viewModel.getDayStatus(selectedDate.value).first)//THIS IS A STRING, NOT A DAYSTATUS
    }*/
    var resetStatusSelector = remember { mutableStateOf(true) }
    var expandedStatus = remember { mutableStateOf(false) }
    val modifier = Modifier
    if (sheetState.isAnimationRunning){
        Log.d(Constants.TAG, "BottomSheetContainer: isAnimationRunning")
        context.getActivity()?.let { it1 -> hideKeyboard(it1) }
       // viewModel.selectedColor.value = Color(0xFF55555E)
    }
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
                .background(MaterialTheme.colors.background)
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
                        .background(MaterialTheme.colors.background)
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
                    modifier = androidx.compose.ui.Modifier
                        .size(tabHeight)
                        .clip(
                            RoundedCornerShape(
                                (tabHeight / 2) ?: 0.dp,
                                (tabHeight / 2) ?: 0.dp,
                                tabHeight / 2,
                                tabHeight / 2,
                            )
                        )
                        .background(MaterialTheme.colors.background)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(color = Color.Red),
            contentAlignment = Alignment.Center
        ) {/////////////////////////////////////BOTTOM SHEET////////////////////////////////////////





            //I wonder if this updates when the selectedDate changes
            Column {
                Text(

                    text = "Bottom sheet",
                    fontSize = 20.sp,
                    modifier= Modifier.align(Alignment.CenterHorizontally)
                )
                //Text(text = "Selected date: ${selectedDate.value} Current status: $selectedOption1")
                //displays status selector

                /*MultiSelector(
                    options = DayStatus.getList(),
                    selectedOption = selectedOption1,
                    onOptionSelect = { option ->
                        selectedOption1 = option
                        setDayStatus(selectedDate.value,option,viewModel)

                        //also figure what to do about colors
                    },
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                )*/
                Row{
                    Button(onClick = {
                    viewModel.openColorPickerState.value = true
                    /*scope.launch {
                        sheetState.collapse()
                    }*/
                }) {
                    Text(text = "ColorPicker")
                    }

                    OutlinedButton(
                        onClick = {

                            expandedStatus.value = !expandedStatus.value
                        }
                    ) {
                        Text("HUH")
/*
                        if (expandedStatus.value) {

                            *//*DayStatusSpinner(
                                options = DayStatus.getList(),
                                selectedOption1 = selectedOption1,
                                onDone = {
                                    selectedOption1 = it
                                    setDayStatus(selectedDate.value, it, viewModel,)
                                    expandedStatus.value = false
                                    //resetStatusSelector.value = true
                                },
                                reset = resetStatusSelector.value,
                                //onReset = {expanded -> expanded.value = false
                            )
                            Log.d(TAG, "SpinnerResult: $selectedOption1")*//*
                            *//*Button(
                                onClick = { viewModel.openDialogState2.value = true },
                                modifier = Modifier.padding(2.dp)
                            ) { Text("Custom Status") }*//*
                        }else{
                            Text("Select Status")
                        }*/
                    }
                }
                LazyColumn( modifier.fillMaxWidth()){

                    itemsIndexed(viewModel.recordings.value) { ix, item ->
                        Row {
                            Text(ix.toString())

                            RecordingCard(item, onClickListener = { onClick(item) })/*event = item, onClick = {
                                viewModel.selectedEvent.value = item
                                viewModel.characterDialogState.value = true
                            *///viewModel.selectedEvent.value)
                            //Text(text = it.toString())
                        }
                    }
                    item{
                        TextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                            , colors = TextFieldDefaults.textFieldColors(   backgroundColor = grey200, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent, cursorColor = Color.Black, textColor = Color.Black)
                        )
                    }}

            }

        }

    },
    sheetBackgroundColor = Color.Transparent,
    sheetPeekHeight = 90.dp
    ) {
        //the bottom half of the screen, covered by sheet
        Box(
            modifier = Modifier
                .fillMaxSize().background(Color.Green),
            contentAlignment = Alignment.TopCenter//Center
        ) {
            // val view: View = LocalView.current
            Column {
                Text("Calendar")
                /*StaticCalendarForBottomSheet12(modifier = Modifier, data = stuff, repo = viewModel.generalRepository,
                    onClick={date,data->//onDateChange
                        selectedDate.value=date
                        selectedDateStuff = data.events
                        viewModel.selectedDate.value = date
                        viewModel.selectedEvent.value = data.events.firstOrNull()?: Event()
                        viewModel.selectedDayData.value = data
                        selectedOption1 = data.status
                        noteText = data.toString()
                        resetStatusSelector.value = true
                        expandedStatus.value = false
                        //viewModel.setCalendarStuff(date,strList)
                        scope.launch {
                            sheetState.expand()
                        }
                    }

                )*/

                Button(onClick = {
                    if (sheetState.isCollapsed) {
                        scope.launch {

                            sheetState.expand()
                        }
                    } else {
                        scope.launch {
                            sheetState.collapse()


                        }
                        context.getActivity()?.let { it1 -> hideKeyboard(it1) }
                    }

                }) {

                    Text(text = "Bottom sheet fraction: ${sheetState.progress.fraction}")
                }
                Spacer(modifier = Modifier.height(50.dp))
                Column(modifier = Modifier.align(Alignment.CenterHorizontally)){
                    //Text(LocalDate.now().toString())
                    //Text("Todays events are\n ${stuff[LocalDate.now()]?.toString()}")
                    Text("Selected Color: ${viewModel.selectedColor.value}")
                        //Text("Selected Day's color: ${viewModel.selectedDayData.value.color}")
                }
            }
        }
    }
}
/*fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = activity.getCurrentFocus()
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}*/
/*
@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetState.isAnimationRunningCompat(): Boolean {
    return this.currentValue.isAnimationRunning
}
@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetState.isAnimating(): Boolean {
    return currentValue.targetValue != currentValue.currentValue
}*/
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun BottomSheetState.getSwipeableState(): SwipeableState<BottomSheetValue> = swipeableState
