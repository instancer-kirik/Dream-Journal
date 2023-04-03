package com.instance.dreamjournal

import android.net.Uri
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.instance.dreamjournal.Constants.TAG
import com.instance.dreamjournal.destinations.HomeScreenDestination
import com.instance.dreamjournal.destinations.RecordingDetailsScreenDestination
import com.instance.dreamjournal.ui.RecordingDetailsScreen

import com.instance.dreamjournal.ui.home.HomeScreen
import com.instance.dreamjournal.ui.home.HomeViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable

import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun MyMain(
    activity: MainActivity,
    homeViewModel: HomeViewModel = hiltViewModel(),
    recordingDetailsViewModel: RecordingDetailsViewModel = hiltViewModel(),

) {
    val navController = rememberNavController()
    homeViewModel.setContextAndFolderPath(LocalContext.current, "")
    homeViewModel.readRecordings()


    DestinationsNavHost(navGraph = NavGraphs.root)//NEW NAV ENTRY POINT
    {

/*
        composable(
            RecordingDetailsScreenDestination //+ "/{dayAndTime}/{duration}/{path}"
        ) { //backStackEntry ->

            recordingDetailsViewModel.initMediaPlayer(
                Uri.fromFile(
                    File(navBackStackEntry.arguments?.getString("path") ?: "")
                )
            )

            RecordingDetailsScreen(
                navBackStackEntry.arguments?.getString("dayAndTime") ?: "",
                navBackStackEntry.arguments?.getString("duration") ?: "",
                navBackStackEntry.arguments?.getString("path") ?: "",
                recordingDetailsViewModel
            ) { navController.popBackStack() }
        }
*/
        composable(
            HomeScreenDestination
        ) {
            val recordingList: List<Recording> =
                homeViewModel.recordings.collectAsState().value

            HomeScreen(

                viewModel = homeViewModel,
                //activity = activity,
                /*onClick = { item ->
                    Log.d("$TAG MyMain", "onClick: ${item.path}")
                    *//*navController.navigate(
                        "${RecordingDetailsScreenDestination.route}/${item.readableDayTime}/${item.duration}/${item.path}"
                    )*//*
                },*/
                recordingList = recordingList.toMutableList(),
                context = LocalContext.current,
                navigator = destinationsNavigator
            )
        }
     }}

/*

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
           val recordingList: List<Recording> =
                homeViewModel.recordings.collectAsState().value
            HomeScreen(

                viewModel = homeViewModel,
                activity = activity,
                onClick = { item ->
                    navController.navigate(
                        "${Screen.RecordingDetails.route}/${item.readableDayTime}/${item.duration}/${item.path}"
                    )
                },
                recordingList = recordingList.toMutableList(),
                context = LocalContext.current
            )
        }
        composable(
            "${Screen.RecordingDetails.route}/{dayAndTime}/{duration}/{path}"
        ) { backStackEntry ->

            recordingDetailsViewModel.initMediaPlayer(
                Uri.fromFile(
                    File(backStackEntry.arguments?.getString("path") ?: "")
                )
            )

            RecordingDetailsScreen(
                backStackEntry.arguments?.getString("dayAndTime") ?: "",
                backStackEntry.arguments?.getString("duration") ?: "",
                backStackEntry.arguments?.getString("path") ?: "",
                recordingDetailsViewModel
            ) { navController.popBackStack() }
        }
    }
}
*/
