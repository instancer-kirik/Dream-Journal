package com.instance.dreamjournal

import android.media.MediaMetadataRetriever
import android.os.Parcelable
import android.util.Log
import androidx.databinding.adapters.Converters
import androidx.room.*
import com.instance.dreamjournal.Constants.TAG
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
/*
@Entity(
    tableName = "recordings",
    indices = [
        Index(value = ["uuid"], unique = true),
        //Index(value = ["oid"])
        //Index(value = ["trakt_id"], unique = true),
        //Index(value = ["tmdb_id"])
    ],
    *//*CREATE TABLE "quests" ( "quest_id" INTEGER NOT NULL UNIQUE, "Quest_Name" INTEGER NOT NULL, "Description" INTEGER, "Reward" INTEGER, "ENCODED_OBJECTIVES_DATES_EVENTMARKERS_PLOTLINES" INTEGER, "SOURCE" INTEGER NOT NULL, "DATE" REAL, "SOURCE_USER_ID" INTEGER, "SOURCE_MEDIA" TEXT, "SOURCE_MODEL" TEXT, "PARTY_SIZE" INTEGER, "TRANSPO_LEVEL" INTEGER, "SPECIAL_TRANSPO" TEXT, "SPECIAL_EQUIPMENT" TEXT, "LOCATION" TEXT, "DURATION" REAL, "LEVEL" INTEGER, PRIMARY KEY("quest_id" AUTOINCREMENT) );
 INSERT INTO "main"."quests" VALUES('0','0','','','','0','','','','','','','','','','','');
 INSERT INTO "main"."quests" VALUES('1','first_quest','this is your context ALSO mode of transportation is -1(walkable) 0(skateboard) 1(bike) 2(car) 3(racecar) 4(rally) 5(offroad vehicle-clearance/snow) 6(truck) 7(drift) 8(SPECIAL_TRANSPO)','twenty chicken nuggets','a bunch of data here worry about this later. also source is -1(I created) 0(usercreated own) 1(pulled from movie/game/story 2(given by someone else)','0','7/7/2021','1','','','-1','0','','nothing','here','now','1');
 INSERT INTO "main"."quests" VALUES('2','0','','','','0','','','','','','','','','','','');

  foreignKeys = [
         ForeignKey(
             entity = ObjectiveEntity::class,
             parentColumns = arrayOf("rqid"),
             childColumns = arrayOf("oid"),
             onUpdate = ForeignKey.CASCADE,
             onDelete = ForeignKey.CASCADE

         )
     ]
 *//*
)*/
/*@TypeConverters(Converters::class)*/

@Parcelize
data class Recording(
    val duration: String,
    val readableDate: String,
    val readableDayTime: String,
    val date: Date,
    val path: String
) : Parcelable


fun convertFilesToRecordings(file: List<File>): List<Recording> {

    val mmr = MediaMetadataRetriever()
    val recordingList = file.map {
        val calendar = Calendar.getInstance()
        calendar.time = Date(it.lastModified())
        Log.d("$TAG Recording", "convertFilesToRecordings: ${it.path}")
        if (it.path == "/storage/emulated/0/Android/data/com.instance.dreamjournal/files/storage") {
            Log.d("$TAG Recording", "caught the bug ${it.path}")
            return@map emptyRecording()
        }
        if (it.path == "/storage/emulated/0/Android/data/com.instance.dreamjournal/files/my_recordings") {
            Log.d("$TAG Recording", "caught the bug ${it.path}")
            return@map emptyRecording()
        }
            mmr.setDataSource(it.path)
            val duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) ?: "0"

            val timeDuration = convertDurationToString(duration.toInt())

            return@map Recording(
                readableDate = "${calendar.get(Calendar.DAY_OF_MONTH)} ${
                    SimpleDateFormat(
                        "MMM",
                        Locale.getDefault()
                    ).format(calendar.time)
                }",
                readableDayTime = "${
                    SimpleDateFormat(
                        "EEEE",
                        Locale.getDefault()
                    ).format(calendar.time)
                } at " + String.format(
                    "%02d:%02d",
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE)
                ),
                duration = timeDuration,
                path = it.path,
                date = Date(it.lastModified())
            )
        }.toMutableList()
    mmr.release()
    return recordingList

}
fun convertDurationToString(duration: Int): String = String.format(
    "%02d:%02d",
    TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
    TimeUnit.MILLISECONDS.toSeconds(duration.toLong())
)

fun generateRecordingName(path: String?): String {
    return "${path}/RecordingEntry-${
        SimpleDateFormat("ddMMyyyy-HHmmss", Locale.getDefault()).format(
            Calendar.getInstance().time
        )
    }.m4a"
}
fun emptyRecording(): Recording {
    return Recording("0","0",SimpleDateFormat("ddMMyyyy-HHmmss", Locale.getDefault()).format(
        Calendar.getInstance().time
    ),Date(),"")
}
/*
@JsonClass(generateAdapter=true)
data class Recording @JvmOverloads constructor(
    @ColumnInfo(name = "uuid") @PrimaryKey val uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "datetime")  val datetime:String = ""){

}*/
