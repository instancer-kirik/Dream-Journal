package com.instance.dreamjournal

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.time.LocalDate
import java.util.*
import kotlin.properties.Delegates

class GeneralRepository(db:AppDatabase) {
    val jDao = db.journalDao()
    var selectedEntry by Delegates.observable(0) { property, oldValue, newValue ->
        Log.d(TAG,"SELECTED $property IS NOW $newValue")
    }
    suspend fun getAllEntries(): Array<EntryEntity>{
        return withContext(Dispatchers.IO){
            jDao.getItAll()
        }
    }
   /* suspend fun getRecordingAtDate(date: LocalDate): Array<Recording>?{
        return withContext(Dispatchers.IO){
            jDao.getRecordingOn(date)
        }
    }*/
    fun getRecordings(directory: String): List<Recording> {
       Log.d("$TAG GeneralRepository", "getRecordings: $directory")
        val file = File(directory)
        return convertFilesToRecordings(file.listFiles()?.toList() ?: listOf())
    }
/*suspend fun getAtDate(){

    val c: Calendar = Calendar.getInstance()
    val dateToday: Date = c.time
    val dateTodayInLong: Long = dateToday.time
    return withContext(Dispatchers.IO){
        jDao.getGivenDate(dateTodayInLong)
    }
}*/
}