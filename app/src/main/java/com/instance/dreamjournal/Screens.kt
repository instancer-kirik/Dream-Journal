package com.instance.dreamjournal

import androidx.annotation.StringRes
import com.instance.dreamjournal.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object RecordingDetails : Screen("recordingDetails", R.string.recordingDetails)
}